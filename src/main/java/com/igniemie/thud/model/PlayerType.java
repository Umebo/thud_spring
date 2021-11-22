package com.igniemie.thud.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlayerType {
    D("dwarf"),
    T("troll");

    private String type;

    public String getType() {
        return type;
    }
}
