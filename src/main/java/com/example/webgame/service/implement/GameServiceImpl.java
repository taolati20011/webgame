package com.example.webgame.service.implement;

import com.example.webgame.dto.AddGameDTO;
import com.example.webgame.entity.Game;
import com.example.webgame.exception.NotFoundException;
import com.example.webgame.repository.GameRepository;
import com.example.webgame.repository.GameTypeRepository;
import com.example.webgame.response.GameDetailResponse;
import com.example.webgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameTypeRepository gameTypeRepository;

    @Override
    public GameDetailResponse getGameDetails(Integer id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Game " + id + " not found"));

        GameDetailResponse gameDetailResponse = new GameDetailResponse();
        gameDetailResponse.gameId = game.gameId;
        gameDetailResponse.gameDescription = game.gameDescription;
        gameDetailResponse.gameName = game.gameName;
        String gameType = gameTypeRepository.findById(game.getType().getTypeId()).get().getTypeName();
        gameDetailResponse.gameType = gameType;
        gameDetailResponse.releaseDate = game.releaseDate;
        gameDetailResponse.releaseLocation = game.releaseLocation;

        return gameDetailResponse;
    }

    @Override
    public void addGameDetails(AddGameDTO addGameDTO) {
        Game game = new Game();
        game.setGameDescription(addGameDTO.gameDescription);
        game.setGameName(addGameDTO.gameName);
        game.setGameDescription(addGameDTO.gameDescription);
        game.setType(gameTypeRepository.findById(addGameDTO.typeId).get());
        gameRepository.save(game);
    }

    @Override
    public List<GameDetailResponse> findAll() {
        return gameRepository.findAll().stream().map(data -> {
            GameDetailResponse gameDetailResponse = new GameDetailResponse();
            gameDetailResponse.gameId = data.gameId;
            gameDetailResponse.gameDescription = data.gameDescription;
            gameDetailResponse.gameName = data.gameName;
            String gameType = gameTypeRepository.findById(data.getType().getTypeId()).get().getTypeName();
            gameDetailResponse.gameType = gameType;
            gameDetailResponse.releaseDate = data.releaseDate;
            gameDetailResponse.releaseLocation = data.releaseLocation;
            return gameDetailResponse;
        }).collect(Collectors.toList());
    }
}
