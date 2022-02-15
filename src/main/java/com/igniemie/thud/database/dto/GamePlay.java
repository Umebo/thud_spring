package com.igniemie.thud.database.dto;

import com.igniemie.thud.model.PlayerType;
import lombok.Data;

@Data
public class GamePlay {

    private PlayerType type;
    private Integer dimX;
    private Integer dimY;
    private String gameUUID;
}
