package com.jimmy.springmq.controller;

import com.jimmy.springmq.handle.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.xml.ws.Response;

@Controller
public class MessageController {

    @Autowired
    private Sender sender;


    @GetMapping(path="/send")
    public String sendMsg(){
        sender.send();
        return "index" ;
    }
}
