package com.igniemie.thud.session;

import com.igniemie.thud.model.Player;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class PlayerSession {

    private Player player = null;

    public boolean isLogged() {
        return this.player != null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) { this.player = player; }
}
