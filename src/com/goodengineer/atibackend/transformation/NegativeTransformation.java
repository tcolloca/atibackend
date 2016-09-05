package com.goodengineer.atibackend.transformation;

import com.goodengineer.atibackend.model.Band;

public class NegativeTransformation implements Transformation {

	@Override
	public void transform(Band band) {

		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				band.setRawPixel(x, y, band.getValidMax() + band.getValidMin() - band.getRawPixel(x, y));
			}
		}
	}
}
