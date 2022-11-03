package com.example.webgame.repository;

import com.example.webgame.entity.Score;
import com.example.webgame.response.ScoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    @Modifying
    @Query(value = "SELECT TOP 10 * FROM Score s ORDER BY s.score DESC", nativeQuery = true)
    public List<Score> getTopScore();
}
