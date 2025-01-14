package it.unipi.lsmd.BeatBuddy.controllers;

import it.unipi.lsmd.BeatBuddy.utilities.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginPage_Ctrl {

    @RequestMapping("/login")
    public String loginPage(HttpSession session){
        if(!Utility.isLogged(session))
            return "login";
        else
            return "error/alreadyLogged";
    }
}
