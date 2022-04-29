package com.example.messanger.controller;

import com.example.messanger.test.CustomKafkaListenerProperty;
import com.example.messanger.test.CustomKafkaListenerRegistrar;
import com.example.messanger.test.KafkaConsumerAssignmentResponse;
import com.example.messanger.test.KafkaConsumerResponse;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class KafkaConsumerRegistryController {

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Autowired
    private CustomKafkaListenerRegistrar customKafkaListenerRegistrar;

    @GetMapping("/showConsumers")
    public List<KafkaConsumerResponse> getConsumerIds() {
        return kafkaListenerEndpointRegistry.getListenerContainerIds()
                .stream()
                .map(this::createKafkaConsumerResponse)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConsumer(@RequestParam String topic, @RequestParam String listenerClass) {
        customKafkaListenerRegistrar.registerCustomKafkaListener(null,
                CustomKafkaListenerProperty.builder()
                        .topic(topic)
                        .listenerClass(listenerClass)
                        .build(),
                true);
    }

    private KafkaConsumerResponse createKafkaConsumerResponse(String consumerId) {
        MessageListenerContainer listenerContainer =
                kafkaListenerEndpointRegistry.getListenerContainer(consumerId);
        return KafkaConsumerResponse.builder()
                .consumerId(consumerId)
                .groupId(listenerContainer.getGroupId())
                .listenerId(listenerContainer.getListenerId())
                .active(listenerContainer.isRunning())
                .assignments(Optional.ofNullable(listenerContainer.getAssignedPartitions())
                        .map(topicPartitions -> topicPartitions.stream()
                                .map(this::createKafkaConsumerAssignmentResponse)
                                .collect(Collectors.toList()))
                        .orElse(null))
                .build();
    }

    private KafkaConsumerAssignmentResponse createKafkaConsumerAssignmentResponse(
            TopicPartition topicPartition) {
        return KafkaConsumerAssignmentResponse.builder()
                .topic(topicPartition.topic())
                .partition(topicPartition.partition())
                .build();
    }

}
