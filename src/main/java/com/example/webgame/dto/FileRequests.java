package com.example.webgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;

@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileRequests {
    public String fileName;

    public Integer gameId;

    public String gameName;
}
