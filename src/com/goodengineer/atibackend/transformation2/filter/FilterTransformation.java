package com.goodengineer.atibackend.transformation2.filter;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation2.Transformation;

abstract class FilterTransformation implements Transformation {

	final double[][] mask;
	final int size;

	FilterTransformation(double[][] mask) {
		if (mask.length % 2 == 0) {
			throw new IllegalArgumentException("Filter must be odd sized.");
		}
		this.mask = mask;
		this.size = mask.length;
	}

	@Override
	public void transform(Band band) {
		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				band.setRawPixel(x, y, applyMask(band, x, y));
			}
		}
	}

	private double applyMask(Band band, int x, int y) {
		double count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				count += getMatrixValue(band, x, y, i, j) * mask[i][j];
			}
		}
		return count;
	}

	private double getMatrixValue(Band band, int x, int y, int i, int j) {
		int xOffset = - (size - 1) / 2 + i;
		int yOffset = - (size - 1) / 2 + j;
		int relativeX = x + xOffset;
		int relativeY = y  + yOffset;
		if (relativeX < 0 || relativeX >= band.getWidth() || relativeY < 0 || relativeY >= band.getHeight()) {
			return band.getValidMin();
		}
		return band.getPixel(relativeX, relativeY);
	}
}
