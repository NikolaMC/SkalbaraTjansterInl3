package com.example.LegoThemesFileReader.Lego;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class LegoController {

    @Autowired
    LegoService legoService;

    @GetMapping("all")
    public Collection<LegoTheme> getAllThemes() {

        System.out.println("Getting all themes");
        return legoService.getAllThemes();

    }

    @GetMapping("{name}")
    public Collection<LegoTheme> getSpecificTheme(@PathVariable("name") String name) {

        System.out.println("Getting specific theme: " + name);
        if (name.isBlank()) {
            return null;
        }
        return legoService.getSpecificTheme(name);

    }

}
