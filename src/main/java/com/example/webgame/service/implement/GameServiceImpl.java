package com.example.webgame.service.implement;

import com.example.webgame.dto.AddGameDTO;
import com.example.webgame.entity.Game;
import com.example.webgame.exception.NotFoundException;
import com.example.webgame.repository.GameRepository;
import com.example.webgame.repository.GameTypeRepository;
import com.example.webgame.response.GameDetailResponse;
import com.example.webgame.service.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
    public String addGameDetails(AddGameDTO addGameDTO) {
        if (addGameDTO.gameName.length() == 0) {
            return "Please fill game name!";
        }
        if (addGameDTO.gameDescription.length() == 0
                || addGameDTO.gameDescription == null) {
            return "Please fill game description!";
        }
        if (addGameDTO.typeId == null) {
            return "Please select game type";
        }
        Game game = new Game();
        Optional<Integer> res = gameRepository.findGameByGameName(addGameDTO.gameName);
        if (res.isPresent()) {
            return "Game " + addGameDTO.gameName + " already exist!";
        }
        game.setGameName(addGameDTO.gameName);
        game.setGameDescription(addGameDTO.gameDescription);
        game.setType(gameTypeRepository.findById(addGameDTO.typeId).get());
        game.setReleaseDate(addGameDTO.getReleaseDate());
        game.setReleaseLocation(addGameDTO.getReleaseLocation());
        gameRepository.save(game);
        return "Add game successful";
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

    @Override
    public List<GameDetailResponse> findGamesByFilter(String words, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<GameDetailResponse> games = gameRepository.findByFilter(words, paging)
                .stream()
                .map(data -> {
                    GameDetailResponse gameDetailResponse = new GameDetailResponse();
                    String gameType = gameTypeRepository.findById(data.getType().getTypeId()).get().getTypeName();
                    gameDetailResponse.gameType = gameType;
                    gameDetailResponse.gameName = data.gameName;
                    gameDetailResponse.gameDescription = data.gameDescription;
                    gameDetailResponse.gameId = data.gameId;
                    gameDetailResponse.releaseDate = data.releaseDate;
                    gameDetailResponse.releaseLocation = data.releaseLocation;
                    return gameDetailResponse;
                }).collect(Collectors.toList());
        if (!games.isEmpty()) {
            return games;
        }
        throw new NotFoundException("Game not found with : " + words);
    }

    @Override
    public Integer countNumberOfGame(String words) {
        return gameRepository.countNumberOfGame(words);
    }

    @Override
    public boolean deleteGameById(Integer gameId) {
        if (gameRepository.countId(gameId) > 0) {
            gameRepository.deleteGameById(gameId);
            return true;
        }
        throw new NotFoundException("User does not exist");
    }

    public String editGameDetails(Integer id, AddGameDTO addGameDTO) {
        if (addGameDTO.gameName.length() == 0) {
            return "Please fill game name!";
        }
        if (addGameDTO.gameDescription.length() == 0
                || addGameDTO.gameDescription == null) {
            return "Please fill game description!";
        }
        if (addGameDTO.typeId == null) {
            return "Please select game type";
        }
        Game game = new Game();
        Optional<Integer> res = gameRepository.findGameByGameName(addGameDTO.gameName);
        if (res.isPresent() && res.get() != id) {
            return "Game " + addGameDTO.gameName + " already exist!";
        }
        game.setGameId(id);
        game.setGameName(addGameDTO.gameName);
        game.setGameDescription(addGameDTO.gameDescription);
        game.setType(gameTypeRepository.findById(addGameDTO.typeId).get());
        game.setReleaseDate(addGameDTO.getReleaseDate());
        game.setReleaseLocation(addGameDTO.getReleaseLocation());
        gameRepository.save(game);
        return "Add game successful";
    }
}
