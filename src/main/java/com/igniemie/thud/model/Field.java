package com.igniemie.thud.model;

public class Field {
    private int pos_x;
    private int pos_y;
    private String text;

    public Field(int pos_x, int pos_y, String text) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.text = text;
    }

    public Field() {
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
