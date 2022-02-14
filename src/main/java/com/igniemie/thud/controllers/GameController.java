package com.igniemie.thud.controllers;

import com.igniemie.thud.database.dto.ConnectRequest;
import com.igniemie.thud.database.dto.GameDTO;
import com.igniemie.thud.exception.InvalidParamException;
import com.igniemie.thud.model.Game;
import com.igniemie.thud.database.dto.GamePlay;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.service.IGameService;
import com.igniemie.thud.session.GameSession;
import com.igniemie.thud.session.PlayerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    public GameController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/start")
    public Game start(@RequestBody Player player) {
        gameService.createGame(player.getNickname());
        return gameSession.getGame();
    }

    @PostMapping("/connect")
    public Game connect(@RequestBody ConnectRequest request) throws InvalidParamException {
        return gameService.connectToGame(request.getPlayer(), request.getGameUUID());
    }

    @PostMapping("/gameplay")
    public GameDTO gameplay(@RequestBody GamePlay request) {
        int [][] board = gameService.gamePlay(request);
        GameDTO gameDTO = new GameDTO();
        gameDTO.setBoard(this.gameSession.getBoard());
        gameDTO.setGame(this.gameSession.getGame());
        gameDTO.setWinner(this.gameSession.getWinner());
        simpMessagingTemplate.convertAndSend("/topic/game-progress" +
                gameDTO.getGame().getGameUUID(),
                gameDTO);
        return gameDTO;
    }

    @GetMapping("/show")
    public int[][] show()  {
        return this.gameSession.getBoard();
    }

/* ------------------------------------------------------------------ */

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
