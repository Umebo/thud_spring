package com.igniemie.thud.service.impl;

import com.igniemie.thud.database.IGameDAO;
import com.igniemie.thud.exception.InvalidParamException;
import com.igniemie.thud.model.Game;
import com.igniemie.thud.database.dto.GamePlay;
import com.igniemie.thud.model.GameStatus;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.model.PlayerType;
import com.igniemie.thud.service.IGameService;
import com.igniemie.thud.session.GameSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameService implements IGameService {

    @Autowired
    IGameDAO gameDAO;

    @Autowired
    GameSession gameSession;

    @Override
    public Game createGame(String player){
        Game game = new Game();
        game.setGameUUID(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(GameStatus.NEW);
        this.gameSession.setGame(game);
        this.gameDAO.addGame(game);
        return game;
    }

    @Override
    public Game getGameById(String gameId) {
        return this.gameDAO.getGameById(gameId).get();
    }

    @Override
    public List<Game> getGames() {
        return this.gameDAO.getGames();
    }

    @Override
    public Game getGameByPlayer(String player) {
        System.out.print(this.gameDAO.getGameById(player).get());
        return this.gameDAO.getGameById(player).get();
    }

    @Override
    public Game connectToGame(Player player2, String gameId) throws InvalidParamException {
        Game game = this.gameDAO.getGameById(gameId).get();

        if (game.getPlayer2() != null) {
            throw new InvalidParamException("This game is already occupied.");
        }

        game.setPlayer2(player2.getNickname());
        game.setStatus(GameStatus.IN_PROGRESS);
        this.gameDAO.updateGame(game);
        this.gameSession.setGame(game);
        return game;
    }

    @Override
    public int[][] gamePlay(GamePlay gamePlay) {

        int[][] board = this.gameSession.getBoard();
        board[gamePlay.getDimX()][gamePlay.getDimY()] = gamePlay.getType().getValue();
        this.gameSession.setBoard(board);

        if (checkWinner(board,PlayerType.X)) {
            this.gameSession.setWinner(PlayerType.X);
            this.gameSession.getGame().setStatus(GameStatus.FINISHED);
            this.gameDAO.updateGame(this.gameSession.getGame());
        } else if (checkWinner(board,PlayerType.O)) {
            this.gameSession.setWinner(PlayerType.O);
            this.gameSession.getGame().setStatus(GameStatus.FINISHED);
            this.gameDAO.updateGame(this.gameSession.getGame());
        }

        return board;
    }

    private Boolean checkWinner(int[][] board, PlayerType playerType) {
        int[] boardArray = new int[9];
        int counterIndex = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boardArray[counterIndex] = board[i][j];
                counterIndex++;
            }
        }

        int[][] winCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int i = 0; i < winCombinations.length; i++) {
            int counter = 0;
            for (int j = 0; j < winCombinations[i].length; j++) {
                if (boardArray[winCombinations[i][j]] == playerType.getValue()) {
                    counter++;
                    if (counter == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
