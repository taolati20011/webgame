package com.example.webgame.repository;

import com.example.webgame.entity.GameType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameTypeRepository extends JpaRepository<GameType, Long> {
}
