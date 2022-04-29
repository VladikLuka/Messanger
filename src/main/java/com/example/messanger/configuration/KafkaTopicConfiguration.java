package com.example.messanger.configuration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Properties;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public AdminClient adminClient(){
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        return AdminClient.create(properties);
    }

    @Bean
    public KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry(){
        return new KafkaListenerEndpointRegistry();
    }
}
