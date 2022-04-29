package com.example.messanger.controller;

import com.example.messanger.domain.Message;
import com.example.messanger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageProducerController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/message/create")
    public void createMessage(@RequestBody Message message){
        messageService.createMessage(message);
    }

}
