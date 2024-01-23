package it.unipi.lsmd.BeatBuddy.controllers;

import it.unipi.lsmd.BeatBuddy.utilities.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfilePage_Ctrl {

    @RequestMapping("/profilePage")
    public String profilePage(HttpSession session,
                              Model model){
        model.addAttribute("logged", (Utility.isLogged(session)) ? true : false);

        if(!Utility.isLogged(session))
            return "redirect:/login";
        else
            return "profilePage";
    }
}
