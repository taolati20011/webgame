package com.example.webgame.service;

import com.example.webgame.entity.Game;
import com.example.webgame.response.GameDetailResponse;

public interface GameService {
    public GameDetailResponse getGameDetails(Integer id);

    public void addGameDetails(Game game);
}
