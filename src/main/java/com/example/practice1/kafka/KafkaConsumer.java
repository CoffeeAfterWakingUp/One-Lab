package com.example.practice1.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class KafkaConsumer {

    private static final String TOPIC = "test-topic";

    private final CountDownLatch latch = new CountDownLatch(1);
    private String payload = null;

    @KafkaListener(topics = TOPIC, groupId = "testId")
    public void receive(ConsumerRecord<Long, String> record) {
        log.info("Received Message: {}, {}", record.key(), record.value());
        setPayload(record.toString());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
