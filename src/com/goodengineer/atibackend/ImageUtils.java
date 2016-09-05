package com.goodengineer.atibackend;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.model.BlackAndWhiteImage;

public class ImageUtils {

	public static BlackAndWhiteImage crop(BlackAndWhiteImage image, int startX, int startY, int width, int height) {
		Band band = new Band(new double[width][height]);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				band.setPixel(x, y, image.getPixel(startX + x, startY + y));
			}
		}
		return new BlackAndWhiteImage(band);
	}

	public static float[] createHistogram(Band band) {
		float[] histogram = new float[256];
		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				histogram[band.getPixel(x, y)]++;
			}
		}
		int totalPixels = band.getWidth() * band.getHeight();
		for (int i = 0; i < histogram.length; i++) {
			histogram[i] = histogram[i] / totalPixels;
		}
		return histogram;
	}
}
