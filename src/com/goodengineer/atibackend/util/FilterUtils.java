package com.goodengineer.atibackend.util;

import com.goodengineer.atibackend.model.Band;

public class FilterUtils {

	public static double leclerc(double x, double sigma) {
		double pow = -(x * x) / (sigma * sigma);
		return Math.pow(Math.E, pow);
	}

	public static double lorentzian(double x, double sigma) {
		double bottom = 1 + (x * x) / (sigma * sigma);
		return 1.0 / bottom;
	}

	public static double applyMask(Band band, double[][] mask, int x, int y) {
		double count = 0;
		int size = mask.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				count += getWithOffset(band, x, y, i, j, size) * mask[i][j];
			}
		}
		return count;
	}

	public static double getWithOffset(Band band, int x, int y, int i, int j, int size) {
		int xOffset = -(size - 1) / 2 + j;
		int yOffset = -(size - 1) / 2 + i;
		int relativeX = x + xOffset;
		int relativeY = y + yOffset;
		if (relativeX < 0) {
			relativeX = 0;
		} else if (relativeX >= band.getWidth()) {
			relativeX = band.getWidth() - 1;
		}
		if (relativeY < 0) {
			relativeY = 0;
		} else if (relativeY >= band.getHeight()) {
			relativeY = band.getHeight()  - 1;
		}
		return band.getPixel(relativeX, relativeY);
	}
}
