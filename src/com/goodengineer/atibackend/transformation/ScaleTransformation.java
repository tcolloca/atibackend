package com.goodengineer.atibackend.transformation;

import com.goodengineer.atibackend.ImageSource;

public class ScaleTransformation implements ImageTransformation {

	private int scalar;
	
	public ScaleTransformation(int scalar) {
		this.scalar = scalar;
	}
	
	@Override
	public void transform(ImageSource imageSource) {
		for (int x = 0; x < imageSource.getWidth(); x++) {
			for (int y = 0; y < imageSource.getHeight(); y++) {
				imageSource.setPixel(x, y, imageSource.getPixel(x, y) * scalar);
			}
		}
	}

	
}
