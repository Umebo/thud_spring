package com.igniemie.thud.board;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Board {
    char [][] board = new char[15][15];

    @Override
    public String toString() {
        return "Board{" +
                "board=" + Arrays.toString(board) +
                '}';
    }
}
