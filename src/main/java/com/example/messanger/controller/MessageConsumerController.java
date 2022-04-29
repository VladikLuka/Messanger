package com.example.messanger.controller;

import com.example.messanger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageConsumerController {

    @Autowired
    private MessageService messageService;

    @Autowired
    ConcurrentKafkaListenerContainerFactory factory;
    

}
