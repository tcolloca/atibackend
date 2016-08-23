package com.goodengineer.atibackend.transformation;

import com.goodengineer.atibackend.ImageSource;

public class ThresholdingTransformation implements ImageTransformation {

	private int thresholdColor;

	public ThresholdingTransformation(int thresholdColor) {
		this.thresholdColor = thresholdColor;
	}

	@Override
	public void transform(ImageSource imageSource) {
		for (int x = 0; x < imageSource.getWidth(); x++) {
			for (int y = 0; y < imageSource.getHeight(); y++) {
				int currentColor = imageSource.getPixel(x, y);
				int newColor = currentColor >= thresholdColor ? 255 : 0;
				imageSource.setPixel(x, y, newColor);
			}
		}
	}

}
