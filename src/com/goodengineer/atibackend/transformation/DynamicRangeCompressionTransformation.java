package com.goodengineer.atibackend.transformation;

import com.goodengineer.atibackend.ImageSource;

/**
 * I really doubt this transformation is well implemented.
 * Ask Juliana if the result looks ok.
 */
public class DynamicRangeCompressionTransformation implements ImageTransformation {

	@Override
	public void transform(ImageSource imageSource) {
		int max = imageSource.getPixel(0, 0);
		for (int x = 0; x < imageSource.getWidth(); x++) {
			for (int y = 0; y < imageSource.getHeight(); y++) {
				max = Math.max(max, imageSource.getPixel(x, y));
			}
		}
		double c = 255.0/Math.log10(1 + max);
		for (int x = 0; x < imageSource.getWidth(); x++) {
			for (int y = 0; y < imageSource.getHeight(); y++) {
				int newColor = (int) (c*Math.log10(1 + imageSource.getPixel(x, y)));
				imageSource.setPixel(x, y, newColor);
			}
		}
	}
}
