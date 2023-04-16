package com.example.webgame.repository;

import com.example.webgame.entity.ImageGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ImageGameRepository extends JpaRepository<ImageGame, Integer> {
    @Query(value = "select i from ImageGame i where i.imageName = ?1")
    public ImageGame findImageByImageName(String fileName);

    @Query(value = "select i from ImageGame i where i.gameId = ?1 and i.isMainImage = 1")
    public ImageGame findImageGameByGameId(Integer fileName);

    @Query(value = """
            select * from image_game i
            where i.game_id = ?1 and i.is_main_image = 0
            """, nativeQuery = true)
    public ArrayList<ImageGame> findImagesByGameId(Integer fileName);
}
