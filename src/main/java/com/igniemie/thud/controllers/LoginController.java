package com.igniemie.thud.controllers;

import com.igniemie.thud.exception.NicknameValidationException;
import com.igniemie.thud.service.ILoginService;
import com.igniemie.thud.service.impl.LoginService;
import com.igniemie.thud.session.PlayerSession;
import com.igniemie.thud.validators.NicknameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Autowired
    ILoginService loginService;

    @Resource
    PlayerSession playerSession;

    @GetMapping(value = "/login")
    public String loginForm(Model model) {
        model.addAttribute("logged", this.playerSession.isLogged());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String nickname) {
        try {
            NicknameValidator.validateNickname(nickname);
        } catch (NicknameValidationException e) {
            return "redirect:/login";
        }

        this.loginService.login(nickname);

        if(this.playerSession.isLogged()) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.playerSession.setPlayer(null);
        return "redirect:/main";
    }

}
