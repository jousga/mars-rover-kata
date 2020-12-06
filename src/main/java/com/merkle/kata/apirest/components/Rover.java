package com.merkle.kata.apirest.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merkle.kata.apirest.entity.Coordinate;
import com.merkle.kata.apirest.enums.Command;
import com.merkle.kata.apirest.enums.Direction;

@Component
public class Rover {
    private static final Logger LOGGER = LoggerFactory.getLogger(Rover.class);
    
	private Coordinate position;
	@Autowired
	private PlanetGrid planet;
	private Direction direction;
	

	public Rover() {
		setDefaultValues();
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) throws Exception {
		if(!planet.isCoordinateValid(position)) throw new Exception("Position not valid.");
		this.position = position;
	}
	
	public Direction getDirection() {
		return direction;
	}

	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
    public void commands(String commands) throws Exception, IllegalArgumentException {
    	for (char command : commands.toUpperCase().toCharArray()) {
            command(command);
        }
    }
    
    public void command(char command) throws Exception, IllegalArgumentException {
    	 if (Command.LEFT.getSymbol() == command) {
    		 turnLeft();
    	 }
         else if (Command.RIGHT.getSymbol() == command) {
        	 turnRight();
         }
         else if (Command.FORWARD.getSymbol() == command) {
        	 moveForward();
         }
         else if (Command.BACKWARD.getSymbol() == command) {
        	 moveBackward();
         }
         else {
        	 String msg = "Command \"" + command + "\" does not exist."; 
        	 
        	 LOGGER.error(msg);
        	 throw new IllegalArgumentException(msg);
         }
    }
    
    public void turnLeft() {
    	direction = direction.turnLeft();
    }
    
    public void turnRight() {
    	LOGGER.info("Turn Right: " + direction.toString() + " > " + direction.turnRight().toString() );
    	direction = direction.turnRight();    	
    }
    
    public void moveForward() throws Exception {
    	setPosition(planet.getCoordinateForward(getPosition(), direction));
    }
    
    public void moveBackward() throws Exception {
    	setPosition(planet.getCoordinateBackward(getPosition(), direction.turnBack()));
    }
    
    public void setDefaultValues() {
    	position = new Coordinate(1, 1);
    	direction = Direction.EAST;
    }
            
    
	
}
