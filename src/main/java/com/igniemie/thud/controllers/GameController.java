package com.igniemie.thud.controllers;

import com.fasterxml.jackson.databind.node.TextNode;
import com.igniemie.thud.database.IGameDAO;
import com.igniemie.thud.database.dto.ConnectRequest;
import com.igniemie.thud.exception.InvalidParamException;
import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.service.IGameService;
import com.igniemie.thud.session.GameSession;
import com.igniemie.thud.session.PlayerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    IGameService gameService;

    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    PlayerSession playerSession;

    @Resource
    GameSession gameSession;

    @PostMapping("/start")
    public Game start(@RequestBody Player player) {
        gameService.createGame(player.getNickname());
        return gameSession.getGame();
    }

    @PostMapping("/connect")
    public Game connect(@RequestBody ConnectRequest request) throws InvalidParamException {
        return gameService.connectToGame(request.getPlayer(), request.getGameUUID());
    }

    @PostMapping("/show")
    public int[][] show()  {
        return this.gameSession.showBoard();
    }

/*
    @GetMapping("/start")
    public Game getGame(@RequestBody String gameUUID) {
        return this.gameService.getGameById(gameUUID);
    }
*/

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
        model.addAttribute("gameId", this.gameSession.getGameUUID());
        return "play";
    }

}
