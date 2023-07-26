package com.omaima.chatspringboot.chat;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage( @Payload ChatMessage chatMessage){
        return chatMessage;
    }


    //This allows us to establish a connection between the user nd the webSocket
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUer(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());  //Add username in websocket session
        return chatMessage;
    }
}
