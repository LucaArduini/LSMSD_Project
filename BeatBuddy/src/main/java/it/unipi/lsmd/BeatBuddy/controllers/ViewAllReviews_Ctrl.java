package it.unipi.lsmd.BeatBuddy.controllers;

import it.unipi.lsmd.BeatBuddy.repository.Album_Repo;
import it.unipi.lsmd.BeatBuddy.utilities.Utility;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewAllReviews_Ctrl {

    @Autowired
    Album_Repo album_Repo;

    @GetMapping("/viewAllReviews")
    public String viewAllReviews(HttpSession session,
                                 Model model,
                                 @RequestParam(name = "albumId") String albumId) {

        boolean albumFound = album_Repo.existsById(albumId);
        if(albumFound)
            model.addAttribute("albumId", albumId);
        else
            return "albumNotFound";

        model.addAttribute("logged", (Utility.isLogged(session)) ? true : false);

        //QUESTO NON DEVE ESSERE UN CONTROLLER, MA UN @RESTCONTROLLER
        return "test/viewAllReviews";
    }
}