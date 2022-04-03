package com.igniemie.thud.session;

import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.GameStatus;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.model.PlayerType;
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
    private int[][] board = new int[15][15];
    private PlayerType winner = null;

    public boolean isStarted(){
        return this.game != null;
    }

    public String getGameUUID(){
        return game.getGameUUID();
    }

}
