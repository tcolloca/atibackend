package com.goodengineer.atibackend.transformation;

import com.goodengineer.atibackend.ImageSource;

public class NegativeTransformation implements ImageTransformation {

	@Override
	public void transform(ImageSource imageSource) {
		for (int x = 0; x < imageSource.getWidth(); x++) {
			for (int y = 0; y < imageSource.getHeight(); y++) {
				imageSource.setPixel(x, y, 255 - imageSource.getPixel(x, y));
			}
		}
	}
}
