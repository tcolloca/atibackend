package com.goodengineer.atibackend.transformation;

import com.goodengineer.atibackend.model.Band;

public class ThresholdingTransformation implements Transformation {

	private int thresholdColor;

	public ThresholdingTransformation(int thresholdColor) {
		this.thresholdColor = thresholdColor;
	}

	@Override
	public void transform(Band band) {
		double rawThresholdColor = band.map(thresholdColor);

		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				double currentColor = band.getRawPixel(x, y);
				double newColor = currentColor >= rawThresholdColor ? band.getValidMax() : band.getValidMin();
				band.setRawPixel(x, y, newColor);
			}
		}
	}

}
