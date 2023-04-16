package com.example.webgame.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://connect-db-test.vercel.app")
@RestController
@Transactional
@RequestMapping("/api/score")
public class ScoreController {
}
