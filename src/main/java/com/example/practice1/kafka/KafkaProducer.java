package com.example.practice1.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducer {

    private final KafkaTemplate<Long, String> kafkaTemplate;

    private static final String TOPIC = "test-topic";

    @Autowired
    public KafkaProducer(KafkaTemplate<Long, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAuthor(Long msgId, String msg) {
        kafkaTemplate.send(TOPIC, msgId, msg)
                .addCallback(
                        result -> log.info("Message sent to topic: {}, {}", msgId, msg),
                        ex -> log.error("Failed to send message", ex)
                );
    }
}
