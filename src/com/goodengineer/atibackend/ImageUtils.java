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
}
