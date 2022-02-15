package com.igniemie.thud.database.dto;

import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.PlayerType;
import lombok.Data;

@Data
public class GameDTO {

    private Game game;
    private int[][] board;
    private PlayerType winner;
}
