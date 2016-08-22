package com.goodengineer.atibackend;

public interface ImageSource {
	int getPixel(int x, int y);
	void setPixel(int x, int y, int color);
	int getWidth();
	int getHeight();
	void dispose();
	
	/**
	 * this method will be used to avoid making transformations on the original image
	 */
	ImageSource copy();
}
