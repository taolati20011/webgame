package com.example.webgame.service;

import com.example.webgame.repository.ScoreRepository;
import com.example.webgame.response.ScoreResponse;

import java.util.List;

public interface ScoreService {
    public List<ScoreResponse> getTopScore();
}
