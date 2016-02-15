package com.github.nolink.calc;

import java.util.HashMap;

public class Operators extends HashMap<String, Priority> {

	public Operators add(String op, int priority, boolean leftAssociative) {
		put(op, new Priority(priority, leftAssociative));
		return this;
	}

}
