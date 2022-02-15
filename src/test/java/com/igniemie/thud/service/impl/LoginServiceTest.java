package com.igniemie.thud.service.impl;

import com.igniemie.thud.config.AppConfiguration;
import com.igniemie.thud.service.ILoginService;
import com.igniemie.thud.session.PlayerSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class})
public class LoginServiceTest {

    @Autowired
    ILoginService loginService;

    @Resource
    PlayerSession playerSession;

    @Test
    public void correctLoginTest(){
        String login = "kajtek12";

        this.loginService.login(login);

        Assert.assertTrue(this.playerSession.isLogged());
    }

    @Test
    public void incorrectLoginTest(){
        String login = "k";

        this.loginService.login(login);

        Assert.assertFalse(this.playerSession.isLogged());
    }

}
