package com.api.conta.producer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Component
public class ContaProducer {
 
	@Value("${topic.name.producer}")
    private String topicName;
	
    private final KafkaTemplate kafkaTemplate;
 
    public ContaProducer(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
 
    public void send(final @RequestBody String order) {
        final String mensageKey = UUID.randomUUID().toString();
        kafkaTemplate.send(topicName, mensageKey, order);
    }
}
