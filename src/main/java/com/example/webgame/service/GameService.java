package com.example.webgame.service;

import com.example.webgame.dto.AddGameDTO;
import com.example.webgame.entity.Game;
import com.example.webgame.response.GameDetailResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface GameService {
    public GameDetailResponse getGameDetails(Integer id);

    public String addGameDetails(AddGameDTO game);

    public List<GameDetailResponse> findAll();

    public List<GameDetailResponse> findGamesByFilter(String words);
}
