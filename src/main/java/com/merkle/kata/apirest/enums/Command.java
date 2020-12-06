package com.merkle.kata.apirest.enums;

public enum Command {
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F"),
    BACKWARD("B");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public final char getSymbol() { 
    	return this.value.charAt(0); 
    }
}
