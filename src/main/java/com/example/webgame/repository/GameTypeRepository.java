package com.example.webgame.repository;

import com.example.webgame.entity.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameTypeRepository extends JpaRepository<GameType, Long> {
    @Query("""
            select t.typeName from GameType t 
            where t.typeId = ?1
            """)
    public String findTypeById(Integer id);

    @Query("""
            select t from GameType t 
            where t.typeId = ?1
            """)
    public GameType findByTypeId(Integer id);
}
