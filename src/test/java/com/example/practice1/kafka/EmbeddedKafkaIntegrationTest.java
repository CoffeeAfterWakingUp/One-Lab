package com.example.practice1.kafka;


import com.example.practice1.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka
public class EmbeddedKafkaIntegrationTest {

    @Autowired
    public KafkaTemplate<Long, String> template;

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;


    @Test
    public void givenEmbeddedKafkaBroker_whenSendingToSimpleProducer_thenMessageReceived() throws Exception {
        Long msgId = 1L;
        Author author = new Author(msgId, "Yann", "Martin");
        String msg = author.toString();
        producer.sendAuthor(msgId, msg);
        consumer.getLatch().await(10, TimeUnit.SECONDS);

        String actual = consumer.getPayload();

        assertThat(actual, containsString(String.valueOf(msgId)));
        assertThat(actual, containsString(msg));
        assertThat(actual, containsString("test-topic"));
    }


}
