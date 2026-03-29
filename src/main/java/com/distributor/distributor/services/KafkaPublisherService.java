package com.distributor.distributor.services;

import com.distributor.distributor.model.ParsedLog;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaPublisherService {
    private final KafkaTemplate<String , String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaPublisherService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishParsedLog(ParsedLog log) {
        try {
            String json = objectMapper.writeValueAsString(log);

            String topic = log.getOutput().getFirst();

            kafkaTemplate.send(topic, json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
