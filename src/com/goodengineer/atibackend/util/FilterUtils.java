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
		for (int i = 0; i < mask.length; i++) {
			for (int j = 0; j < mask[0].length; j++) {
				count += getWithOffset(band, x, y, i, j, mask.length, mask[0].length) * mask[i][j];
			}
		}
		return count;
	}

	public static double getWithOffset(Band band, int x, int y, int i, int j, int width, int height) {
		int xOffset = -(width - 1) / 2 + i;
		int yOffset = -(height - 1) / 2 + j;
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
