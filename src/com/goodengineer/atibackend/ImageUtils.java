package com.goodengineer.atibackend;

public class ImageUtils {
	
	public static ImageSource crop(ImageSource image, int startX, int startY, int width, int height, ImageFactory imageFactory) {
		ImageSource croppedImage = imageFactory.createEmpty(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				croppedImage.setPixel(x, y, image.getPixel(startX + x, startY + y));
			}
		}
		croppedImage.normalize();
		return croppedImage;
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
	 * @param imageSource needs to have pixels between 0 and 255 (at least for this method)
	 */
	public static void normalize(ImageSource imageSource) {
//		TODO: implement normalization
	}
}
