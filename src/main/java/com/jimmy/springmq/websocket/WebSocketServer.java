package com.jimmy.springmq.websocket;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/ws/{client}")
@Component
public class WebSocketServer {
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
        clients.remove(client);
    }

    public  void send(String msg){
        try {
            this.session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
