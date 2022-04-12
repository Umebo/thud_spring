package com.igniemie.thud.controllers;

import com.igniemie.thud.database.dto.ConnectRequest;
import com.igniemie.thud.database.dto.GameDTO;
import com.igniemie.thud.exception.InvalidParamException;
import com.igniemie.thud.model.Board;
import com.igniemie.thud.model.Game;
import com.igniemie.thud.database.dto.GamePlayDTO;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.service.IGameService;
import com.igniemie.thud.service.impl.BoardService;
import com.igniemie.thud.session.GameSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    IGameService gameService;

    @Autowired
    BoardService boardService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    GameSession gameSession;

    public GameController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/start")
    public Game start(@RequestBody Player player) {
        gameService.createGame(player);
        return gameSession.getGame();
    }

    @PostMapping("/connect")
    public Game connect(@RequestBody ConnectRequest request) throws InvalidParamException {
        return gameService.connectToGame(request.getPlayer(), request.getGameUUID());
    }

    //TODO remove manipulating DTO from controller

    @PostMapping("/gameplay")
    public GameDTO gameplay(@RequestBody GamePlayDTO request) {
        this.gameService.gamePlay(request);
        GameDTO gameDTO = new GameDTO();
        gameDTO.setBoard(this.boardService.initialiseBoard());
        gameDTO.setGame(this.gameSession.getGame());
        gameDTO.setWinner(this.gameSession.getWinner());
        simpMessagingTemplate.convertAndSend("/topic/game-progress" +
                gameDTO.getGame().getGameUUID(),
                gameDTO);
        return gameDTO;
    }

    /* additional */

    @GetMapping("/show")
    public int[][] show()  {
        return this.gameSession.getBoard().getTiles();
    }
}
