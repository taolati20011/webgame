package com.example.webgame.service.implement;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.webgame.entity.Game;
import com.example.webgame.entity.ImageGame;
import com.example.webgame.exception.NotFoundException;
import com.example.webgame.repository.GameRepository;
import com.example.webgame.repository.ImageGameRepository;
import com.example.webgame.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private ImageGameRepository imageGameRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public String uploadFile(MultipartFile file, Integer gameId, boolean isMainImage) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        ImageGame imageGame = new ImageGame();
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException("Game not found"));
        ImageGame imageGame1 = imageGameRepository.findImageGameByGameId(gameId);
        if (imageGame1 != null) {
            if (isMainImage == imageGame1.isMainImage()) {
                imageGame.setImageId(imageGame1.getImageId());
            }
        }
        imageGame.setGameId(gameId);
        imageGame.setImageName(fileName);
        imageGame.setMainImage(isMainImage);
        imageGameRepository.save(imageGame);
        fileObj.delete();
        return "File uploaded : " + fileName;
    }

    @Override
    public String uploadMultiFile(List<MultipartFile> files, Integer gameId, boolean isMainImage) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException("Game not found"));
        ArrayList<ImageGame> listImage = imageGameRepository.findImagesByGameId(gameId);
        System.out.println(listImage.size());
        int i = 0, j = 0;
        for (MultipartFile file: files) {
            j++;
            File fileObj = convertMultiPartFileToFile(file);
            String fileName = file.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
            ImageGame imageGame = new ImageGame();
            if (i < listImage.size()) {
                if (listImage.get(i) != null) {
                    if (isMainImage == listImage.get(i).isMainImage()) {
                        imageGame.setImageId(listImage.get(i).getImageId());
                        i++;
                    }
                }
            }
            imageGame.setGameId(gameRepository.findById(gameId).get().getGameId());
            imageGame.setImageName(fileName);
            imageGame.setMainImage(isMainImage);
            imageGameRepository.save(imageGame);
            fileObj.delete();
            if (j == 3) break;
        }
        return "File uploaded done";
    }

    @Override
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteFile(String fileName) {
        ImageGame imageGame = imageGameRepository.findImageByImageName(fileName);
        if (imageGame == null) {
            return "File not found";
        }
        imageGameRepository.delete(imageGame);
        s3Client.deleteObject(bucketName, fileName);
        return "Images " + fileName + " removed ...";
    }

    @Override
    public File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
        }
        return convertedFile;
    }
}
