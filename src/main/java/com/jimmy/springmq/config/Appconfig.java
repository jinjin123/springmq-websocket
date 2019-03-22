package com.jimmy.springmq.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class Appconfig {

    public static final String routingKey = "rk.c01.tenant";
    public static final String Exchange = "topic_c01";

    @Bean
    public Queue initQueue(){
        return new Queue("c01-cmdb",true);
    }


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic_c01",true,false);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(initQueue()).to(topicExchange()).with(routingKey);
    }


    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

