package com.example.webgame.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilesResponse {
    public String title;

    public String image;

    public String typeGame;

    public Integer gameId;

    public String typeImage;
}
