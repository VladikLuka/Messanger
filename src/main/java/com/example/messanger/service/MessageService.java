package com.example.messanger.service;

import com.example.messanger.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void createMessage(Message message){
        kafkaTemplate.send(message.getTopic(), message.getMessage());
    }

}
