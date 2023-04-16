package com.example.webgame.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface StorageService {
    public String uploadFile(MultipartFile file, Integer gameId, boolean isMainImage);

    public byte[] downloadFile(String fileName);

    public String deleteFile(String fileName);

    public File convertMultiPartFileToFile(MultipartFile file);

    public String uploadMultiFile(List<MultipartFile> files, Integer gameId, boolean isMainImage);
}
