package com.igniemie.thud.controllers;

import com.igniemie.thud.database.dto.ConnectRequest;
import com.igniemie.thud.database.dto.GameDTO;
import com.igniemie.thud.exception.InvalidParamException;
import com.igniemie.thud.model.Game;
import com.igniemie.thud.database.dto.GamePlay;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.service.IGameService;
import com.igniemie.thud.session.GameSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class GameController {

    @Autowired
    IGameService gameService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    GameSession gameSession;

    public GameController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/game/start")
    public Game start(@RequestBody Player player) {
        gameService.createGame(player.getNickname());
        return gameSession.getGame();
    }

    @PostMapping("/game/connect")
    public Game connect(@RequestBody ConnectRequest request) throws InvalidParamException {
        return gameService.connectToGame(request.getPlayer(), request.getGameUUID());
    }

    @PostMapping("/game/gameplay")
    public GameDTO gameplay(@RequestBody GamePlay request) {
        this.gameService.gamePlay(request);
        GameDTO gameDTO = new GameDTO();
        gameDTO.setBoard(this.gameSession.getBoard());
        gameDTO.setGame(this.gameSession.getGame());
        gameDTO.setWinner(this.gameSession.getWinner());
        simpMessagingTemplate.convertAndSend("/topic/game-progress" +
                gameDTO.getGame().getGameUUID(),
                gameDTO);
        return gameDTO;
    }

    /* additional */

    @GetMapping("/game/show")
    public int[][] show()  {
        return this.gameSession.getBoard();
    }

}
