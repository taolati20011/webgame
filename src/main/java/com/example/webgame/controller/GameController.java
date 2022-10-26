package com.example.webgame.controller;

import com.example.webgame.entity.Game;
import com.example.webgame.response.GameDetailResponse;
import com.example.webgame.service.implement.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private GameServiceImpl gameService;

    @GetMapping()
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/get-all")
    public Collection<GameDetailResponse> games() {
        return gameService.findAll();
    }

    @GetMapping("/get-detail")
    public ResponseEntity<?> getGameDetail(@RequestParam (value = "game-id") Integer id) {
        return ResponseEntity.ok(gameService.getGameDetails(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addGameDetail(@RequestBody Game gameDetail) {
        gameService.addGameDetails(gameDetail);
        return ResponseEntity.ok("Add game successful");
    }
}
