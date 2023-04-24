package com.example.webgame.service.implement;

import com.example.webgame.repository.GameTypeRepository;
import com.example.webgame.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private GameTypeRepository typeRepository;

    @Override
    public List<String> getAllTypeName() {
        return typeRepository.findAll().stream().map(data -> {
            String listTypeName = data.getTypeName();
            return listTypeName;
        }).collect(Collectors.toList());
    }

    @Override
    public String getTypeName(Integer type_id) {
        return typeRepository.findById(Long.valueOf(type_id)).get().getTypeName();
    }
}
