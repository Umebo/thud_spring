package com.igniemie.thud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "t_game")
@NoArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;
    private String gameUUID;
    private String player1;
    private String player2;
    @Enumerated(EnumType.STRING)
    private GameStatus status;
}