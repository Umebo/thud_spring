package com.igniemie.thud.service.impl;

import com.igniemie.thud.config.AppConfiguration;
import com.igniemie.thud.database.IGameDAO;
import com.igniemie.thud.exception.InvalidParamException;
import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.GameStatus;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.service.IGameService;
import com.igniemie.thud.session.GameSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class})
public class GameServiceTest {

    @Autowired
    IGameService gameService;

    @Resource
    GameSession gameSession;

    @MockBean
    IGameDAO gameDAO;

    @Test
    public void connectToGameTest () throws InvalidParamException {
        Mockito.when(this.gameDAO.getGameById(Mockito.anyString())).thenReturn(generateNewGame());
        String UUID = "bb5c71f3-4263-4678-936a-5c4e4c053ffc";
        Player player = new Player();
        player.setNickname("tytus");

        this.gameService.connectToGame(player, UUID);

        Assert.assertTrue(this.gameSession.getGame().getPlayer2().equals(player.getNickname()));
        Assert.assertTrue(this.gameSession.getGame().getStatus() == GameStatus.IN_PROGRESS);
    }

    //TODO GamePlay test
    /*
    @Test
    public void GamePlayTest() {
        Mockito.when(this.gameDAO.getGameById(Mockito.anyString())).thenReturn(generateGameInProgress());

    }
*/

    public Optional<Game> generateNewGame() {
        Game game = new Game();
        game.setGameId(1);
        game.setGameUUID("bde7c065-74c3-41fb-a26b-10a6430d0db0");
        game.setPlayer1("romek");
        game.setStatus(GameStatus.NEW);

        return Optional.of(game);
    }

    public Optional<Game> generateGameInProgress() {
        Game game = new Game();
        game.setGameId(1);
        game.setGameUUID("bde7c065-74c3-41fb-a26b-10a6430d0db0");
        game.setPlayer1("romek");
        game.setPlayer2("tytus");
        game.setStatus(GameStatus.IN_PROGRESS);

        return Optional.of(game);
    }

    public Optional<Player> generatePlayer() {
        Player player = new Player();
        player.setNickname("atomek");

        return Optional.of(player);
    }
}
