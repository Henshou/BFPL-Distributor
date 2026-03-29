package com.distributor.distributor.services;

import com.distributor.distributor.model.ParsedLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {
    private final KafkaPublisherService kafkaPublisherService;
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(KafkaPublisherService kafkaPublisherService, ObjectMapper objectMapper) {
        this.kafkaPublisherService = kafkaPublisherService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "processor-topic", groupId = "distributor-group")
    public void consumeParsedLog(String message) {
        try{
            ParsedLog log = objectMapper.readValue(message, ParsedLog.class);
            System.out.println("Received parsed log: " + log);

            kafkaPublisherService.publishParsedLog(log);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
