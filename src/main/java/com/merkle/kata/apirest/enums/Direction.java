package com.merkle.kata.apirest.enums;

public enum Direction {
	NORTH,
    EAST,
    SOUTH,
    WEST;


	public Direction turnBack() {
		return Direction.values()[(ordinal() + Direction.values().length / 2) % Direction.values().length];
	}
	
	public Direction turnLeft() {
		return Direction.values()[(Direction.values().length + ordinal() - 1) % Direction.values().length];
	}

	public Direction turnRight() {
		return Direction.values()[(Direction.values().length + ordinal() + 1) % Direction.values().length];
	}
}

