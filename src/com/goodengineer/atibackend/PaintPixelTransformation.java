package com.goodengineer.atibackend;

public class PaintPixelTransformation implements ImageTransformation {

	private int x;
	private int y;
	private int color;

	public PaintPixelTransformation(int x, int y, int color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public void transform(ImageSource imageSource) {
		imageSource.setPixel(x, y, color);
	}
}
