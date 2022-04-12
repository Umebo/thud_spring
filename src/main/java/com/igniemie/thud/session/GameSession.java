package com.igniemie.thud.session;

import com.igniemie.thud.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class GameSession {

    private Game game = null;
    //TODO zamiast tablicy obiekt Board
    private Board board;
    private PlayerType winner = null;

    public boolean isStarted(){
        return this.game != null;
    }

    public String getGameUUID(){
        return game.getGameUUID();
    }

}
