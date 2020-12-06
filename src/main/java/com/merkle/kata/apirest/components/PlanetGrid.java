package com.merkle.kata.apirest.components;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.merkle.kata.apirest.entity.Coordinate;
import com.merkle.kata.apirest.entity.Obstacles;
import com.merkle.kata.apirest.enums.Direction;

@Component
public class PlanetGrid {
	private Coordinate limits;
	private Obstacles obstacles;
	
	public PlanetGrid() {
		setDefaultValues();
	}

	public Coordinate getLimits() {
		return limits;
	}

	public void setLimits(Coordinate limits) {
		this.limits = limits;
	}

	public void setObstacles(List<Coordinate> obstacles) {
		this.obstacles.setObstacles(obstacles);
		this.obstacles.removeInvalid(limits);
	}
	
	public List<Coordinate> getObstacles() {
		return obstacles.getObstacles();
	}
	
	public boolean isCollision(Coordinate coordinate) {
		return obstacles.isCollision(coordinate);
	}

	public boolean isCoordinateValid(Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();		
		
		if(x < 0 || x > limits.getX() || 
		   y < 0 || y > limits.getY()  ||
		   isCollision(coordinate)) 
			return false;
		
		return true;
	}
	
	public Coordinate getCoordinateForward(Coordinate coordinate, Direction direction) throws Exception {
		return moveCoordinate(coordinate, direction);
	}
	
	public Coordinate getCoordinateBackward(Coordinate coordinate, Direction direction) throws Exception {
		return moveCoordinate(coordinate, direction);
	}
	
	private Coordinate moveCoordinate(Coordinate coordinate, Direction direction) throws Exception {
		int x = coordinate.getX();
        int y = coordinate.getY();
        
        switch (direction) {
            case NORTH:
            	y = (y + 1) % (limits.getY() + 1);
                break;
            case EAST:
            	x = (x + 1) % (limits.getX() + 1);
                break;
            case SOUTH:
            	y = (y > 0)? y -1 : limits.getY();
                break;
            case WEST:
            	x = (x > 0)? x -1 : limits.getX();
                break;
        }
        
        Coordinate c = new Coordinate(x, y);
        
        if(isCollision(c)) {
        	throw new Exception("Displacement stopped, possible collision detected.");
        }
        
		return c;
	}
	

    public void setDefaultValues() {
    	limits = new Coordinate(10, 10);
    	obstacles = new Obstacles(Arrays.asList(
									new Coordinate(2, 4)
								));
    }
	
}
