package com.example.webgame.service;

import java.util.List;

public interface TypeService {
    public List<String> getAllTypeName();

    public String getTypeName(Integer type_id);
}
