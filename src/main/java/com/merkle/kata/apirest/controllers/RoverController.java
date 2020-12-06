package com.merkle.kata.apirest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merkle.kata.apirest.components.Rover;
import com.merkle.kata.apirest.entity.Coordinate;
import com.merkle.kata.apirest.enums.Direction;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = {"/api/rest/rover/v1",
						 "/api/rest/rover"})
public class RoverController {
	@Autowired
	private Rover rover;
	
	@GetMapping("")
	public ResponseEntity<?> getRover(){		
		return ResponseEntity.ok(rover);
	}
	
	@PutMapping("/commands/{commands}")
	public ResponseEntity<?> applyCommands(@PathVariable String commands){	
		
		try {
			rover.commands(commands);			
		} catch (IllegalArgumentException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("error", e.getMessage());
			response.put("x", rover.getPosition().getX());
			response.put("y", rover.getPosition().getY());
			response.put("direction", rover.getDirection());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("error", e.getMessage());
			response.put("x", rover.getPosition().getX());
			response.put("y", rover.getPosition().getY());
			response.put("direction", rover.getDirection());
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}

		return ResponseEntity.ok(rover);		
	}

	@GetMapping("/position")
	public ResponseEntity<?> getPosition(){		
		return ResponseEntity.ok(rover.getPosition());
	}

	@PutMapping("/position")
	public ResponseEntity<?> setPosition(@RequestBody Coordinate position){	
		try {
			rover.setPosition(position);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("error", e.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.ok(rover.getPosition());
	}
	

	@GetMapping("/direction")
	public ResponseEntity<?> getDirection(){		
		return ResponseEntity.ok(rover.getDirection());
	}

	@PutMapping("/direction/{direction}")
	public ResponseEntity<?> setDirection(@PathVariable String direction){
		
		try {
			rover.setDirection(Direction.valueOf(direction.toUpperCase()));
		}catch(IllegalArgumentException e) {

			Map<String, Object> response = new HashMap<>();
			response.put("error", "Direction \"" + direction + "\"not found.");
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.ok(rover.getDirection());
	}

}
