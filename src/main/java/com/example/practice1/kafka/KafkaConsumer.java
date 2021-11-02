package com.example.practice1.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private static final String TOPIC = "test-topic";


    @KafkaListener(topics = TOPIC, groupId = "testId")
    public void receive(ConsumerRecord<Long, String> record) {
        log.info("Received Message: {}, {}", record.key(), record.value());
    }

}
