package com.igniemie.thud.controllers;

import com.igniemie.thud.model.Board;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "board")
public class BoardController {

    private final Board board = new Board();
/*
    @GetMapping
    public String cokolwiek() {
        return "cokolwiek";
    }
*/
    @GetMapping
    public String board(Model model) {
        model.addAttribute("board", this.board.toString());
        return "board";
    }
}
