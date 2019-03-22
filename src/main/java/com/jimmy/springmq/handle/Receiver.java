package com.jimmy.springmq.handle;

import com.jimmy.springmq.websocket.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.jimmy.springmq.websocket.WebSocketServer.*;


@Component
@RabbitListener(queues="c01-cmdb")
public class Receiver {

    @RabbitHandler
    public void process(String msg) throws InterruptedException{
        System.out.println("Reciver:"+ msg);

        for(WebSocketServer webSocketServer : webSockets){
            try{
                webSocketServer.send(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

