package com.distributor.distributor.services;

import com.distributor.distributor.model.TelemetryEvent;
import tools.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TelemetryPublisherService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public TelemetryPublisherService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishTelemetry(TelemetryEvent telemetryEvent) {
        try {
            String message = objectMapper.writeValueAsString(telemetryEvent);
            kafkaTemplate.send("dq-metrics-topic", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
