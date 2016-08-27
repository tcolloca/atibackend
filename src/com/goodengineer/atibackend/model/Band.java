package com.goodengineer.atibackend.model;

import com.goodengineer.atibackend.util.LinearFunction;

public class Band {
	private double[][] pixels;

	private double min;
	private double max;
	private int minCount;
	private int maxCount;

	private LinearFunction rawToNormal;
	private LinearFunction normalToRaw;

	public Band(double[][] pixels) {
		this.pixels = pixels;
		findMinAndMax();
	}

	public int getWidth() {
		return pixels.length;
	}

	public int getHeight() {
		return pixels[0].length;
	}

	public double getRawPixel(int x, int y) {
		return pixels[x][y];
	}

	public void setRawPixel(int x, int y, double newColor) {
		double oldColor = pixels[x][y];
		if (newColor == oldColor) {
			return;
		}
		if (oldColor == max) {
			maxCount--;			
		}
		if (oldColor == min) {
			minCount--;			
		}
		pixels[x][y] = newColor;
		
		if (minCount == 0 || maxCount == 0) {
			findMinAndMax();
			return;
		}
		
		if (newColor > max) {
			max = newColor;
			maxCount = 1;
		}
		if (newColor == max) {
			maxCount++;
		}

		if (newColor < min) {
			min = newColor;
			minCount = 1;
		}
		if (newColor == min) {
			minCount++;
		}
	}

	public int getPixel(int x, int y) {
		if (hasInvalidValues()) {
			return (int) Math.round(rawToNormal.apply(getRawPixel(x, y)));
		}
		return (int) Math.round(getRawPixel(x, y));
	}

	public void setPixel(int x, int y, int color) {
		if (hasInvalidValues()) {
			setRawPixel(x, y, normalToRaw.apply((double) color));
			return;
		}
		setRawPixel(x, y, color);
	}

	public double map(int color) {
		if (hasInvalidValues()) {
			return normalToRaw.apply((double) color);
		}
		return (double) color;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public double getValidMin() {
		return hasInvalidValues() ? min : 0;
	}

	public double getValidMax() {
		return hasInvalidValues() ? max : 255;
	}

	private void findMinAndMax() {
		min = pixels[0][0];
		max = pixels[0][0];
		minCount = 0;
		maxCount = 0;
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				if (pixels[x][y] < min) {
					min = pixels[x][y];
					minCount = 1;
				}
				if (pixels[x][y] == min) {
					minCount++;
				}
				if (pixels[x][y] > max) {
					max = pixels[x][y];
					maxCount = 1;
				}
				if (pixels[x][y] == max) {
					maxCount++;
				}
			}
		}

		rawToNormal = new LinearFunction(min, 0, max, 255);
		normalToRaw = new LinearFunction(0, min, 255, max);
	}

	private boolean hasInvalidValues() {
		return min < 0 || max > 255;
	}
}