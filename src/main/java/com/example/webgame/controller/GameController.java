package com.example.webgame.controller;

import com.example.webgame.dto.AddGameDTO;
import com.example.webgame.dto.UserDTO;
import com.example.webgame.entity.Game;
import com.example.webgame.response.GameDetailResponse;
import com.example.webgame.service.implement.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "https://connect-db-test.vercel.app")
@RestController
@Transactional
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private GameServiceImpl gameService;

    @GetMapping()
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/count-game")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Integer countNumberOfGame(@RequestParam(defaultValue = "") String words) {
        return gameService.countNumberOfGame(words);
    }

    @GetMapping("/get-all")
    public Collection<GameDetailResponse> games() {
        return gameService.findAll();
    }

    @GetMapping("/get-all-by-filter")
    public ResponseEntity<List<GameDetailResponse>> games(@RequestParam (defaultValue = "") String words,
                                                          @RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam Integer pageSize) {
        return ResponseEntity.ok(gameService.findGamesByFilter(words, pageNo, pageSize));
    }

    @GetMapping("/get-detail")
    public ResponseEntity<?> getGameDetail(@RequestParam (value = "game-id") Integer id) {
        return ResponseEntity.ok(gameService.getGameDetails(id));
    }

    @GetMapping("/get-name")
    public ResponseEntity<?> getGameName(@RequestParam (value = "game-id") Integer id) {
        return ResponseEntity.ok(gameService.getGameDetails(id).gameName);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addGameDetail(@RequestBody AddGameDTO gameDetail) {
        return ResponseEntity.ok(gameService.addGameDetails(gameDetail));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> editGame(
            @PathVariable("id") Integer id,
            @RequestBody AddGameDTO addGameDTO
    ) {
        gameService.editGameDetails(id, addGameDTO);
        return ResponseEntity.ok("Update Successful");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteGame(
            @PathVariable("id") Integer id
    ) {
        gameService.deleteGameById(id);
        return ResponseEntity.ok("Delete Successful");
    }
}
