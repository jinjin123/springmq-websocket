package com.jimmy.springmq.handle;

import com.jimmy.springmq.service.RedisService;
import com.jimmy.springmq.websocket.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.jimmy.springmq.websocket.WebSocketServer.*;


@Component
@RabbitListener(queues="c01-cmdb")
public class Receiver {
    @Autowired
    private RedisService redisService;

    @RabbitHandler
    public void process(String msg) throws InterruptedException{
        System.out.println("Reciver:"+ msg);
        if(webSockets.size() == 0){
            try {
                redisService.lpush("c01",msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(WebSocketServer webSocketServer : webSockets){
            try{
                webSocketServer.send(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

