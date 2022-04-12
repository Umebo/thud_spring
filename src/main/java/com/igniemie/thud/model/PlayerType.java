package com.igniemie.thud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayerType {
    DWARF(1),
    TROLL(2);

    private Integer value;
}
