package com.example.messanger.controller;

import com.example.messanger.service.TopicService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
public class TopicController {

    @Autowired
    private KafkaTemplate<String, String> template;

    @Autowired
    private TopicService topicService;

    @PostMapping("/chat/create")
    @ResponseStatus(code = HttpStatus.OK)
    public void createChat(@RequestBody Map<String, String> topicName) throws ExecutionException, InterruptedException {
        NewTopic topic = new NewTopic(topicName.get("topicName"), 10, (short) 1);
        List<NewTopic> topics = new ArrayList<>();
        topics.add(topic);
        System.out.println(topicName);
        topicService.createTopic(topics);
    }

    @GetMapping("/chat/topics")
    public Set<String> getTopics() throws ExecutionException, InterruptedException {
        return topicService.getTopics();
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("test");
        return "OK";
    }

}
