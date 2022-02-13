package com.igniemie.thud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
public class Player {

    private int id;
    private String nickname;

    public Player(String nickname) {
        this.nickname = nickname;
    }

}
