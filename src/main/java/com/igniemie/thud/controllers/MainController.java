package com.igniemie.thud.controllers;

import com.igniemie.thud.session.GameSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "")
public class MainController {

    @Resource
    GameSession gameSession;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(){
        return "redirect/main";
    }
}
