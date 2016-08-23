package com.goodengineer.atibackend.transformation;

import com.goodengineer.atibackend.ImageSource;

public class RectBorderTransformation implements ImageTransformation {

	private int left;
	private int top;
	private int right;
	private int bottom;
	private int color;
	
	public RectBorderTransformation(int left, int top, int right, int bottom, int color) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.color = color;
	}



	/**
	 * Draws an empty rectangle in the specified position
	 */
	@Override
	public void transform(ImageSource imageSource) {
		for (int x = left; x <= right; x++) {
			imageSource.setPixel(x, top, color);
			imageSource.setPixel(x, bottom, color);
		}
		for (int y = top; y <= bottom; y++) {
			imageSource.setPixel(left, y, color);
			imageSource.setPixel(right, y, color);
		}
	}
}
