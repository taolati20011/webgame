package com.example.webgame.repository;

import com.example.webgame.dto.FileRequests;
import com.example.webgame.entity.Game;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query(value = "select gameId from Game where gameName = ?1")
    Optional<Integer> findGameByGameName(String gameName);

    @Query("""
            select g from Game g
            where g.gameName like %?1%
            """)
    List<Game> findByFilter(String words, Pageable pageable);

    @Query("""
            select count(g) from Game g
            where g.gameName like %?1%
            """)
    Integer countNumberOfGame(String words);

    @Modifying
    @Query(value = """
            delete from game 
            where game_id = ?1 ;
            """, nativeQuery = true)
    void deleteGameById(Integer gameId);

    @Query(value = """
            select count(g) from Game g
            where g.gameId = ?1
            """)
    Integer countId(Integer id);

    @Query(value = """
            select new com.example.webgame.dto.FileRequests(ig.imageName, g.gameId, g.gameName) from Game g
            join ImageGame ig on g.gameId = ig.gameId
            where g.type.typeId = ?1 and ig.isMainImage = true
            """)
    List<FileRequests> getMainImageGamesByType(Integer typeId);

    @Query(value = """
            select new com.example.webgame.dto.FileRequests(ig.imageName, g.gameId, g.gameName) from Game g
            join ImageGame ig on g.gameId = ig.gameId
            where g.gameId = ?1 and ig.isMainImage = false
            """)
    List<FileRequests> getCoverImageGamesByGameId(Integer gameId);

    @Query(value = """
            select new com.example.webgame.dto.FileRequests(ig.imageName, g.gameId, g.gameName) from Game g
            join ImageGame ig on g.gameId = ig.gameId
            where g.gameId = ?1 and ig.isMainImage = true
            """)
    FileRequests getMainImageGamesByGameId(Integer gameId);
}
