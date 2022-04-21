package btt.chat.stomp;

import lombok.AllArgsConstructor;

import java.security.Principal;

@AllArgsConstructor
public class StompPrincipal implements Principal {
    String name;

    @Override
    public String getName() {
        return this.name;
    }
}
