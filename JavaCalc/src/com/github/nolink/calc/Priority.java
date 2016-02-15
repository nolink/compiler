package com.github.nolink.calc;

public class Priority {

	private int priotity;
	private boolean leftAssociative;

	public Priority(int priority, boolean leftAssociative) {
		this.priotity = priority;
		this.leftAssociative = leftAssociative;
	}

	public int getPriotity() {
		return priotity;
	}

	public void setPriotity(int priotity) {
		this.priotity = priotity;
	}

	public boolean isLeftAssociative() {
		return leftAssociative;
	}

	public void setLeftAssociative(boolean leftAssociative) {
		this.leftAssociative = leftAssociative;
	}

}
