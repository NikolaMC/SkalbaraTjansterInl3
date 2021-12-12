package com.example.LegoThemesFileReader;

import com.example.LegoThemesFileReader.Lego.LegoTheme;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

@NoArgsConstructor
public class GetThemesFromFile {

    private static List<LegoTheme> themeList = new ArrayList<>();

    public static void readFile() {

        try {

            File file = new File("themes.csv");
            Scanner sc = new Scanner(file);
            String colNames = sc.nextLine();

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] splitLine = line.split(",");

                if (splitLine.length == 2) {
                    splitLine = ifIndexOutOfBounds(splitLine);
                }

                themeList.add(new LegoTheme(Integer.parseInt(splitLine[0]), splitLine[1], Integer.parseInt(splitLine[2])));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static String[] ifIndexOutOfBounds(String[] array) {

        String[] array1 = new String[array.length + 1];
        System.arraycopy(array, 0, array1, 0, array.length);
        array1[2] = "0";
        return array1;

    }

    public static List<LegoTheme> getThemeList() {
        return themeList;
    }

}
