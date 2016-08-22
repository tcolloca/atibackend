package com.goodengineer.atibackend;

public class ImageUtils {
	
	public static ImageSource crop(ImageSource image, int startX, int startY, int width, int height, ImageFactory imageFactory) {
		ImageSource croppedImage = imageFactory.createEmpty(width, height);
		for (int x = startX; x < width; x++) {
			for (int y = startY; y < height; y++) {
				croppedImage.setPixel(x, y, image.getPixel(x, y));
			}
		}
		return croppedImage;
	}
	
	public static void copy(ImageSource from, ImageSource to) {
		for (int x = 0; x < from.getWidth(); x++) {
			for (int y = 0; y < from.getHeight(); y++) {
				to.setPixel(x, y, from.getPixel(x, y));
			}
		}
	}
}
