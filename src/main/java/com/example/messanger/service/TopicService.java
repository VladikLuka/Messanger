package com.example.messanger.service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Component
public class TopicService {

    @Autowired
    private AdminClient adminClient;

    public void createTopic(List<NewTopic> topicName) throws ExecutionException, InterruptedException {
        CreateTopicsResult topics = adminClient.createTopics(topicName);
        System.out.println(topics);
        System.out.println(topics.values());
        topics.all().get();
    }

    public Set<String> getTopics() throws ExecutionException, InterruptedException {
        ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
        listTopicsOptions.listInternal(true);

        return adminClient.listTopics(listTopicsOptions).names().get();
    }

}
