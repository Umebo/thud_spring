package com.igniemie.thud.model;

import lombok.Data;

@Data
public class Board {
    private int[][] tiles;
    private static int[][] EMPTY_TILES = {
            {0,0},{0,1},{0,2},{0,3},{0,4},{0,10},{0,11},{0,12},{0,13},{0,14},
            {1,0},{1,1},{1,2},{1,3},{1,11},{1,12},{1,13},{1,14},
            {2,0},{2,1},{2,2},{2,12},{2,13},{2,14},
            {3,0},{3,1},{3,13},{3,14},
            {4,0},{4,14},
            {10,0},{10,14},
            {11,0},{11,1},{11,13},{11,14},
            {12,0},{12,1},{12,2},{12,12},{12,13},{12,14},
            {13,0},{13,1},{13,2},{13,3},{13,11},{13,12},{13,13},{13,14},
            {14,0},{14,1},{14,2},{14,3},{14,4},{14,10},{14,11},{14,12},{14,13},{14,14},
    };
    private static int[][] DWARF_POSITIONS = {
            {0,5},{0,6},{0,8},{0,9},
            {1,4},{1,10},{2,3},{2,11},{3,2},{3,12},{4,1},{4,13},
            {5,0},{5,14},{6,0},{6,14},{8,0},{8,14},{9,0},{9,14},
            {10,1},{10,13},{11,2},{11,12},{12,3},{12,11},{13,4},{13,10},
            {14,5},{14,6},{14,8},{14,9}
    };
    private static int[][] TROLLS_POSITIONS = {
            {6,6},{6,7},{6,8},
            {7,6},{7,8},
            {8,6},{8,7},{8,8}
    };
    private static int[] THUD_POSITION = {7,7};

    public Board () {
        this.tiles = new int[15][15];
        for (int[] tile : EMPTY_TILES) {
            setTile(tile, -1);
        }
        for (int[] tile : DWARF_POSITIONS) {
            setTile(tile, 1);
        }
        for (int[] tile : TROLLS_POSITIONS) {
            setTile(tile, 2);
        }
        setTile(THUD_POSITION, -1);
    }

    public void setTile (int[] coordinates, int value) {
        this.tiles[coordinates[0]][coordinates[1]] = value;
    }

    public int getTile (int[] coordinates) {
        return tiles[coordinates[0]][coordinates[1]];
    }
}
