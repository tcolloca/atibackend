package com.goodengineer.atibackend;

public interface ImageSource {
	int getPixel(int x, int y);
	void setPixel(int x, int y, int color);
	int getWidth();
	int getHeight();
	void dispose();
	
	/**
	 * This could very well make a call to ImageUtils.normalize(this).
	 * But an image allowing invalid values in its pixels is implementation dependant,
	 * so every ImageSource implementation is in charge of deciding how it is going to be normalized.
	 */
	void normalize();
	
	/**
	 * this method will be used to avoid making transformations on the original image
	 */
	ImageSource copy();
}
