package btt.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void connectEvent(SessionConnectEvent event){
        log.info("socket connected");
    }

    @MessageMapping(value = "/chat")
    public void chat(SimpMessageHeaderAccessor header, Principal principal){
        log.info(header.getUser().toString());
        log.info(principal.getName());
        String username = principal.getName();
        simpMessagingTemplate.convertAndSendToUser(username, "/sub/test", "asdf");
    }

}
