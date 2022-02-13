package com.igniemie.thud.controllers;

import com.igniemie.thud.database.IGameDAO;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.service.IGameService;
import com.igniemie.thud.session.GameSession;
import com.igniemie.thud.session.PlayerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    IGameService gameService;

    @Resource
    PlayerSession playerSession;

    @Resource
    GameSession gameSession;

    @RequestMapping(value = "")
    public String startNewGame() {
        Player player = this.playerSession.getPlayer();
        this.gameService.createGame(player.getNickname());
        return "redirect:/game/play";
    }

    @RequestMapping(value = "/play")
    public String play(Model model) {
        model.addAttribute("logged", this.playerSession.isLogged());
        model.addAttribute("player", this.playerSession.getPlayer());
        model.addAttribute("gameId", this.gameSession.getGameId());
        return "play";
    }

}
