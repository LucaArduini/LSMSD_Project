package it.unipi.lsmd.BeatBuddy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPage_Controller {

    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }
}