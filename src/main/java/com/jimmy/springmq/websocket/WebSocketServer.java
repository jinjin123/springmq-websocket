package com.jimmy.springmq.websocket;

import java.io.IOException;

import com.jimmy.springmq.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/ws/{client}")
@Component
public class WebSocketServer {
    @Autowired
    private RedisService redisService;

    private Session session;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    public static CopyOnWriteArraySet<WebSocketServer> webSockets = new CopyOnWriteArraySet<WebSocketServer>();
    public static Map<String, Session> clients = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void OnOpen(@PathParam("client")String client,Session session)throws InterruptedException {
        logger.info("WebSocket start connecting:" + client);
        this.session = session;
        clients.put(client,session);
        webSockets.add(this);
    }

    @OnClose
    public void onClose(@PathParam("client")String client,Session s){
        try{
            logger.info("关闭WebSocket,id=" + client);
            try{
                clients.remove(client);
                webSockets.remove(this);
                if(this.session != null){
                    this.session.close();
                }
            }catch (IllegalStateException e){
                logger.info("WebSocket端断开连接");
            }
        }catch (Exception e){
            logger.error("WebSocket关闭连接出错",e);
        }
    }

    public  void send(String msg){
        try {
            this.session.getBasicRemote().sendText(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
