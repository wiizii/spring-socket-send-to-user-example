package btt.chat.stomp;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class CustomStompHandshakeHandler extends DefaultHandshakeHandler {
    private Long anonymousCounter = 0L;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Principal principal = request.getPrincipal();
        if(principal != null){
            return new StompPrincipal(principal.getName());
        }
        this.anonymousCounter++;
        return new StompPrincipal("익명"+String.format("%04d",anonymousCounter));
    }

}
