package com.example.webgame.repository;

import com.example.webgame.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query(value = "select 1 from Game where gameName = ?1")
    Optional<Integer> findGameByGameName(String gameName);

    @Query("""
            select g from Game g
            where g.gameName like %?1%
            """)
    List<Game> findByFilter(String words);
}
