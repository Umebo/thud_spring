package com.igniemie.thud.controllers;

import com.igniemie.thud.session.GameSession;
import com.igniemie.thud.session.PlayerSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class MainController {

    @Resource
    PlayerSession playerSession;

    @Resource
    GameSession gameSession;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(){
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("logged", this.playerSession.isLogged());
        model.addAttribute("player", this.playerSession.getPlayer());
        return "main";
    }

}
