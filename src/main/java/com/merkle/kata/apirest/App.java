package com.merkle.kata.apirest;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.merkle.kata.apirest.entity.Coordinate;
import com.merkle.kata.apirest.entity.Obstacles;
import com.merkle.kata.apirest.entity.PlanetGrid;
import com.merkle.kata.apirest.entity.Rover;
import com.merkle.kata.apirest.enums.Direction;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	
		
	}

}
