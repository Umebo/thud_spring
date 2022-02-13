package com.igniemie.thud.database.dto;

import com.igniemie.thud.model.Player;
import lombok.Data;

@Data
public class ConnectRequest {
    private Player player;
    private String gameUUID;
}
