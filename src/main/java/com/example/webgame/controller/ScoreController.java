package com.example.webgame.controller;

import com.example.webgame.service.implement.ScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/score")
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @GetMapping("/top-score")
    public ResponseEntity<?> getTopScore() {
        return ResponseEntity.ok(scoreService.getTopScore());
    }
}
