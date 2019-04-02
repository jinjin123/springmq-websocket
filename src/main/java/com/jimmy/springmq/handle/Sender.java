package com.jimmy.springmq.handle;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jimmy.springmq.config.Appconfig;
import com.jimmy.springmq.utils.JsonUtil;

@Component
public class Sender {
    @Autowired
    private RabbitTemplate amqpTemplate;

    public void send(){
        String content = "{'z':'b','a':'c','d':'a','a':'r'}";
        System.out.println("Sender:" + content);
        this.amqpTemplate.convertAndSend(Appconfig.Exchange,Appconfig.routingKey,content);
    }
}
