package com.goodengineer.atibackend.model;

import com.goodengineer.atibackend.transformation2.Transformation;

public class ColorImage implements Image {

	private Band red;
	private Band green;
	private Band blue;
	
	public ColorImage(Band red, Band green, Band blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	@Override
	public void transform(Transformation transformation) {
		transformation.transform(red);
		transformation.transform(green);
		transformation.transform(blue);
	}

	@Override
	public int getWidth() {
		return blue.getWidth();
	}

	@Override
	public int getHeight() {
		return blue.getHeight();
	}
}
