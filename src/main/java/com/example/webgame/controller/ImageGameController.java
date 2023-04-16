package com.example.webgame.controller;

import com.example.webgame.service.implement.StorageServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "https://connect-db-test.vercel.app")
@RestController
@Transactional
@RequestMapping("/api/image")
public class ImageGameController {
    @Autowired
    private StorageServiceImpl service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file,
                                             @RequestParam(value = "gameId") Integer gameId,
                                             @RequestParam(value = "isMainImage") boolean isMainImage) {
        return new ResponseEntity<>(service.uploadFile(file, gameId, isMainImage), HttpStatus.OK);
    }

    @PostMapping("/uploadMultiple")
    public ResponseEntity<String> uploadMultipleFile(@RequestParam(value = "file") List<MultipartFile> files,
                                             @RequestParam(value = "gameId") Integer gameId,
                                             @RequestParam(value = "isMainImage") boolean isMainImage) {
        return new ResponseEntity<>(service.uploadMultiFile(files, gameId, isMainImage), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestParam String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
}
