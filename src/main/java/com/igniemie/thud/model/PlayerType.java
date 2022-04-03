package com.igniemie.thud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayerType {
    TROLL(1),
    DWARF(2);

    private Integer value;
}
