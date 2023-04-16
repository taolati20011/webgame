package com.example.webgame.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer imageId;

    public Integer gameId;

    public boolean isMainImage;

    public String imageName;
}
