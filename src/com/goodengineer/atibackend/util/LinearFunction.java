package com.goodengineer.atibackend.util;

public class LinearFunction implements Function {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public LinearFunction(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public int apply(int x) {
		double m = (y2 - y1) / ((double) x2 - x1);
		double b = y1 - m * x1;
		return (int) (m * x + b);
	}
}
