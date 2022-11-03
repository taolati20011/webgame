package com.example.webgame.service.implement;

import com.example.webgame.entity.Score;
import com.example.webgame.repository.ScoreRepository;
import com.example.webgame.response.ScoreResponse;
import com.example.webgame.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<ScoreResponse> getTopScore() {
        return scoreRepository.getTopScore().stream().map(
                data -> {
                    ScoreResponse scoreResponse = new ScoreResponse();
                    scoreResponse.score = Integer.toString(data.score);
                    scoreResponse.playerDisplayName = data.playerDisplayName;
                    scoreResponse.dateUpdated = data.scoreDate;
                    return scoreResponse;
                }
        ).collect(Collectors.toList());
    }
}
