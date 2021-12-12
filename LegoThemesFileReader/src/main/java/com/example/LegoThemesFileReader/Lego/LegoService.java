package com.example.LegoThemesFileReader.Lego;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class LegoService {

    @Autowired
    LegoRepository legoRepository;

    public Collection<LegoTheme> getAllThemes() {
        return legoRepository.getAllThemes();
    }

    public Collection<LegoTheme> getSpecificTheme(String name) {

        if (!name.isEmpty()) {
            return legoRepository.getSpecificTheme(name);
        }

        return null;

    }

}
