package com.goodengineer.atibackend;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.model.BlackAndWhiteImage;

public class ImageUtils {

	public static BlackAndWhiteImage crop(BlackAndWhiteImage image, int startX, int startY, int width, int height,
			ImageFactory imageFactory) {
		Band band = new Band(new double[width][height]);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				band.setPixel(x, y, image.getPixel(startX + x, startY + y));
			}
		}
		return new BlackAndWhiteImage(band);
	}

	public static void copy(ImageSource from, ImageSource to) {
		for (int x = 0; x < from.getWidth(); x++) {
			for (int y = 0; y < from.getHeight(); y++) {
				to.setPixel(x, y, from.getPixel(x, y));
			}
		}
	}

	/**
	 * 
	 * @param imageSource
	 *            needs to have pixels between 0 and 255 (at least for this
	 *            method)
	 */
	public static void normalize(ImageSource imageSource) {
		int min = imageSource.getPixel(0, 0);
		int max = min;
		for (int x = 0; x < imageSource.getWidth(); x++) {
			for (int y = 0; y < imageSource.getHeight(); y++) {
				min = Math.min(min, imageSource.getPixel(x, y));
				max = Math.max(max, imageSource.getPixel(x, y));
			}
		}
		// only normalize if it's necessary
		if (min >= 0 && max <= 255)
			return;
		for (int x = 0; x < imageSource.getWidth(); x++) {
			for (int y = 0; y < imageSource.getHeight(); y++) {
				int color = (int) ((255.0 * (imageSource.getPixel(x, y) - min)) / (max - min));
				imageSource.setPixel(x, y, color);
			}
		}
	}

	/**
	 * 
	 * @param imageSource
	 *            needs to have pixels between 0 and 255 (at least for this
	 *            method)
	 */
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
