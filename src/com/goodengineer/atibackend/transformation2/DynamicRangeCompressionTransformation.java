package com.goodengineer.atibackend.transformation2;

import com.goodengineer.atibackend.model.Band;

/**
 * I really doubt this transformation is well implemented.
 * TODO: Ask Juliana if the result looks ok.
 */
public class DynamicRangeCompressionTransformation implements Transformation {

	@Override
	public void transform(Band band) {
		double max = band.getMax();
		double c = 255.0/Math.log10(1 + max);
		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				int newColor = (int) Math.round((c*Math.log10(1 + band.getRawPixel(x, y) - band.getValidMin())));
				band.setRawPixel(x, y, newColor);
			}
		}
	}
}