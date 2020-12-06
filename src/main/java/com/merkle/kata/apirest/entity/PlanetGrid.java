package com.merkle.kata.apirest.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.merkle.kata.apirest.enums.Direction;

public class PlanetGrid {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetGrid.class);
	private Coordinate limits;
	private Obstacles obstacles;

	public PlanetGrid(Coordinate limit, Obstacles obstacles) {
		setLimits(limit);
		setObstacles(obstacles);
	}

	public Coordinate getLimits() {
		return limits;
	}

	public void setLimits(Coordinate limits) {
		this.limits = limits;
	}

	public void setObstacles(Obstacles obstacles) {
		obstacles.removeInvalid(limits);
		this.obstacles = obstacles;	
	}
	
	public boolean isCollision(Coordinate coordinate) {
		return obstacles.isCollision(coordinate);
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
        	LOGGER.info("Displacement stopped, possible collision detected. (" + c +")");
        	throw new Exception("Displacement stopped, possible collision detected.");
        }
        
		return c;
	}
	
}
