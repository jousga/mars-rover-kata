package com.merkle.kata.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merkle.kata.apirest.components.PlanetGrid;
import com.merkle.kata.apirest.entity.Coordinate;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = {"/api/rest/planet/v1",
						 "/api/rest/planet"})
public class PlanetController {

	@Autowired
	private PlanetGrid planet;
	
	@GetMapping("")
	public ResponseEntity<?> getPlanet(){		
		return ResponseEntity.ok(planet);
	}

	@GetMapping("/limits")
	public ResponseEntity<?> getLimits(){		
		return ResponseEntity.ok(planet.getLimits());
	}

	@PutMapping("/limits")
	public ResponseEntity<?> setLimits(@RequestBody Coordinate limits){	
		planet.setLimits(limits);
		return ResponseEntity.ok(planet.getLimits());
	}

	@GetMapping("/obstacles")
	public ResponseEntity<?> getObstacles(){		
		return ResponseEntity.ok(planet.getObstacles());
	}

	@PutMapping("/obstacles")
	public ResponseEntity<?> setObstacles(@RequestBody List<Coordinate> obstacles){	
		planet.setObstacles(obstacles);
		return ResponseEntity.ok(planet.getObstacles());
	}
	
}
