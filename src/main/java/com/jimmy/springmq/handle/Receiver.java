package com.jimmy.springmq.handle;

import com.jimmy.springmq.service.RedisService;
import com.jimmy.springmq.websocket.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        }else {
            for (WebSocketServer webSocketServer : webSockets) {
                try {
                    List<String> dataList = redisService.lrange("c01",0,-1);
                    String newMsg = new String();
                    if(dataList.size()!=0){
                        for(String newMg: dataList){
                            newMsg += newMg + "|";
                        }
                        webSocketServer.send(newMsg);
                        redisService.del("c01");
                    }else{
                        webSocketServer.send(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

