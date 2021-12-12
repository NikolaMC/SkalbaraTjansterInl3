package com.example.LegoThemesFileReader.Lego;

import com.example.LegoThemesFileReader.GetThemesFromFile;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@NoArgsConstructor
public class LegoRepository {

    private final List<LegoTheme> legoThemes = GetThemesFromFile.getThemeList();

    public Collection<LegoTheme> getAllThemes() {
        return new ArrayList<>(legoThemes);
    }

    public Collection<LegoTheme> getSpecificTheme(String name) {
        return legoThemes.stream().filter(n -> n.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

}
