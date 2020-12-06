package com.merkle.kata.apirest.entity;

import com.merkle.kata.apirest.enums.Command;
import com.merkle.kata.apirest.enums.Direction;

public class Rover {
	private Coordinate position;
	private final PlanetGrid planet;
	private Direction direction;
	
	public Rover(PlanetGrid planet, Coordinate position, Direction direction) {
		this.planet = planet;
		this.position = position;
		this.direction = direction;
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
    public void commands(String commands) throws Exception {
    	for (char command : commands.toUpperCase().toCharArray()) {
            command(command);
        }
    }
    
    public void command(char command) throws Exception {
    	System.out.println("Char: " + command);
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
        	 throw new Exception("Command \"" + command + "\" does not exist.");
         }
    }
    
    public void turnLeft() {
    	System.out.println("turnLeft: " + direction.toString() + " > " + direction.turnLeft().toString() );
    	direction = direction.turnLeft();
    }
    
    public void turnRight() {
    	System.out.println("turnRight: " + direction.toString() + " > " + direction.turnRight().toString() );
    	direction = direction.turnRight();    	
    }
    
    public void moveForward() throws Exception {
    	setPosition(planet.getCoordinateForward(getPosition(), direction));
    }
    
    public void moveBackward() throws Exception {
    	setPosition(planet.getCoordinateBackward(getPosition(), direction.turnBack()));
    }
            
    
	
}
