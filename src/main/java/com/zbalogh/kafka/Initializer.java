package com.zbalogh.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Initializer implements ApplicationRunner {

    @Value("${message.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) {
        kafkaTemplate.send(topicName, "Hello World!");
        log.info("Published message to topic: {}.", topicName);
    }

    @KafkaListener(topics = "mytopic", groupId = "myGroup")
    public void listen(String message) {
        log.info("Received Messasge: {}", message);
    }

}
