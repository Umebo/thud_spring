package com.igniemie.thud.controllers;

import com.igniemie.thud.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "game")
public class GameController {

    @Autowired
    IGameService gameService;

}
