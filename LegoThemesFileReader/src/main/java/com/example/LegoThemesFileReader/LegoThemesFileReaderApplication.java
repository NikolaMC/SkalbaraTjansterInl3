package com.example.LegoThemesFileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LegoThemesFileReaderApplication {

	public static void main(String[] args) {
		GetThemesFromFile.readFile();
		SpringApplication.run(LegoThemesFileReaderApplication.class, args);
	}

}
