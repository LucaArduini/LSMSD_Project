package it.unipi.lsmd.BeatBuddy.controllers.api;

import it.unipi.lsmd.BeatBuddy.repository.User_Repo_Neo4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class Neo4jBasicOperations_RESTCtrl {

    @Autowired
    User_Repo_Neo4j user_RepoNeo4j;

    @PostMapping("/api/addFollow")
    public @ResponseBody String addFollow(
            @RequestParam("user1") String user1,
            @RequestParam("user2") String user2) {

        String result = user_RepoNeo4j.addFollow(user1, user2);
        if(result.equals("CREATED"))
            return "{\"outcome_code\": 0}";
        else if(result.equals("EXISTING"))
            return "{\"outcome_code\": 1}";
        else
            return "{\"outcome_code\": 2}";
    }

    @PostMapping("/api/removeFollow")
    public @ResponseBody String removeFollow(
            @RequestParam("user1") String user1,
            @RequestParam("user2") String user2) {

        if(user_RepoNeo4j.removeFollow(user1, user2))
            return "{\"outcome_code\": 0}";
        else
            return "{\"outcome_code\": 1}";
    }

    @PostMapping("/api/addLikesAbum")
    public @ResponseBody String addLikesAbum(
            @RequestParam("username") String username,
            @RequestParam("coverURL") String coverURL) {

        String result = user_RepoNeo4j.addLikes_A(username, coverURL);
        if(result.equals("CREATED"))
            return "{\"outcome_code\": 0}";
        else if(result.equals("EXISTING"))
            return "{\"outcome_code\": 1}";
        else
            return "{\"outcome_code\": 2}";
    }    
    
    @PostMapping("/api/addLikesSong")
    public @ResponseBody String addLikesSong(
            @RequestParam("username") String username,
            @RequestParam("title") String title,
            @RequestParam("coverURL") String coverURL) {

        String result = user_RepoNeo4j.addLikes_S(username, title, coverURL);
        if(result.equals("CREATED"))
            return "{\"outcome_code\": 0}";
        else if(result.equals("EXISTING"))
            return "{\"outcome_code\": 1}";
        else
            return "{\"outcome_code\": 2}";
    }

    @PostMapping("/api/albumDetails/removeLikesAlbum")
    public @ResponseBody String removeLikesAlbum(
            @RequestParam("username") String username,
            @RequestParam("coverURL") String coverURL) {

        if(user_RepoNeo4j.removeLikes_A(username, coverURL))
            return "{\"outcome_code\": 0}";
        else
            return "{\"outcome_code\": 1}";
    }

    @PostMapping("/api/albumDetails/removeLikesSong")
    public @ResponseBody String removeLikesSong(
            @RequestParam("username") String username,
            @RequestParam("title") String title,
            @RequestParam("coverURL") String coverURL) {

        if(user_RepoNeo4j.removeLikes_S(username, title, coverURL))
            return "{\"outcome_code\": 0}";
        else
            return "{\"outcome_code\": 1}";
    }
}
