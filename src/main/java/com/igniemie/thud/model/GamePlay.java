package com.igniemie.thud.model;

import lombok.Data;

@Data
public class GamePlay {

    private PlayerType type;
    private Integer dimX;
    private Integer dimY;
    private String gameUUID;
}
