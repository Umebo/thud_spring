package com.igniemie.thud.session;

import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.GameStatus;
import com.igniemie.thud.model.Player;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class GameSession {

    //TODO move board logic to game session
    // game.setBoard(new int[3][3]);

    private Game game = null;
    private int[][] board = new int[3][3];

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame(){
        return this.game;
    }

    public int[][] showBoard(){
        return this.board;
    }

    public boolean isStarted(){
        return this.game != null;
    }

    public String getGameUUID(){
        return game.getGameUUID();
    }

}
