package com.example.LegoThemesFileReader.Lego;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LegoTheme {

    private int id;
    private String name;
    private int parentId;

}
