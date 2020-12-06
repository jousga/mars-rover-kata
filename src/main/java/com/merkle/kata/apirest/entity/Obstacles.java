package com.merkle.kata.apirest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Obstacles {
	private List<Coordinate> obstacles;

	public Obstacles() {
		setObstacles(new ArrayList<>());
	}

	public Obstacles(List<Coordinate> obstacles) {
		setObstacles(obstacles);
	}

	public void setObstacles(List<Coordinate> obstacles) {
		this.obstacles = obstacles;
	}
	
	public List<Coordinate> getObstacles() {
		return obstacles;
	}
	
	public void removeInvalid(Coordinate limit) {
		this.obstacles = this.obstacles.stream()
				 .filter(o ->  
						 (o.getX() >= 0 && o.getX() <= limit.getX()) &&
						 (o.getY() >= 0 && o.getY() <= limit.getY()) )
				 .collect(Collectors.toList());
	}

	public boolean isCollision(Coordinate coordinate) {
		return obstacles.stream()
						.anyMatch(o -> o.equals(coordinate));
	}

	
}
