package com.igniemie.thud.service.impl;

import com.igniemie.thud.model.Board;
import com.igniemie.thud.model.PlayerType;
import com.igniemie.thud.service.IGameService;
import com.igniemie.thud.session.GameSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    GameSession gameSession;

    public Board initialiseBoard() {
        return new Board();
    }

    public void makeAMove(int[] from, int[] to) {
        Board board = this.gameSession.getBoard();
        board.setTile(to, board.getTile(from));
        board.setTile(from, 0);
    }
}
