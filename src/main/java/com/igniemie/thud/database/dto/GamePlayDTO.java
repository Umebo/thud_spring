package com.igniemie.thud.database.dto;

import com.igniemie.thud.model.PlayerType;
import lombok.Data;

@Data
public class GamePlayDTO {

    private PlayerType type;
    private int[] from;
    private int[] to;
    private String gameUUID;
}
