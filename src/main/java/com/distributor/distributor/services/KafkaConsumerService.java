package com.distributor.distributor.services;

import com.distributor.distributor.model.ParsedLog;

import com.distributor.distributor.model.TelemetryEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {
    private final KafkaPublisherService kafkaPublisherService;
    private final ObjectMapper objectMapper;
    private final TelemetryPublisherService telemetryPublisherService;

    public KafkaConsumerService(KafkaPublisherService kafkaPublisherService, ObjectMapper objectMapper, TelemetryPublisherService telemetryPublisherService) {
        this.kafkaPublisherService = kafkaPublisherService;
        this.objectMapper = objectMapper;
        this.telemetryPublisherService = telemetryPublisherService;
    }

    @KafkaListener(topics = "processor-topic", groupId = "distributor-group")
    public void consumeParsedLog(String message,
                                 @Header("processor_publish_time") byte[] processorPublishBytes,
                                 @Header("collector_start_time") byte[] collectorStartBytes,
                                 @Header("source_file_name") byte[] fileBytes,
                                 @Header("record_id") byte[] recordIdBytes,
                                 @Header("batch_id") byte[] batchIdBytes) {
        try{
            long publishTime = Long.parseLong(new String(processorPublishBytes));
            long collectorStartTime = Long.parseLong(new String(collectorStartBytes));
            long receiveTime = System.currentTimeMillis();
            String fileName = new String(fileBytes);
            String recordId = new String(recordIdBytes);
            String batchId = new String(batchIdBytes);

            TelemetryEvent messagingDelayEvent = new TelemetryEvent(
                    recordId,
                    batchId,
                    fileName,
                    "distributor",
                    "messaging.delay.processor.distributor",
                    receiveTime - publishTime,
                    System.currentTimeMillis()
            );

            telemetryPublisherService.publishTelemetry(messagingDelayEvent);

            long processingStartTime = System.currentTimeMillis();

            ParsedLog log = objectMapper.readValue(message, ParsedLog.class);

            System.out.println("Received parsed log: " + log);

            kafkaPublisherService.publishParsedLog(log);

            long processingEndTime = System.currentTimeMillis();

            TelemetryEvent processingTimeEvent = new TelemetryEvent(
                    recordId,
                    batchId,
                    fileName,
                    "distributor",
                    "distributor.record.process.time",
                    processingEndTime - processingStartTime,
                    System.currentTimeMillis()
            );

            telemetryPublisherService.publishTelemetry(processingTimeEvent);

            TelemetryEvent dataLatencyEvent = new TelemetryEvent(
                    recordId,
                    batchId,
                    fileName,
                    "distributor",
                    "data.latency.e2e",
                    processingEndTime - collectorStartTime,
                    System.currentTimeMillis()
            );

            telemetryPublisherService.publishTelemetry(dataLatencyEvent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
