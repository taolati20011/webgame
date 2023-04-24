package com.example.webgame.controller;

import com.example.webgame.service.implement.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://connect-db-test.vercel.app")
@RestController
@Transactional
@RequestMapping("/api/type")
public class TypeController {
    @Autowired
    private TypeServiceImpl typeService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTypeName() {
        return ResponseEntity.ok(typeService.getAllTypeName());
    }

    @GetMapping("/get-type/{type_id}")
    public ResponseEntity<?> getTypeName(@PathVariable Integer type_id) {
        return ResponseEntity.ok(typeService.getTypeName(type_id));
    }
}
