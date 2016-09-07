package com.goodengineer.atibackend.model;

import com.goodengineer.atibackend.transformation.RectBorderTransformation;
import com.goodengineer.atibackend.transformation.Transformation;

import java.util.Arrays;
import java.util.List;

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
		if (transformation instanceof RectBorderTransformation) {
			RectBorderTransformation t = (RectBorderTransformation) transformation;
			int color = t.getColor();
			new RectBorderTransformation(t, (0xFF0000 & color) >> 16).transform(red);
			new RectBorderTransformation(t, (0xFF00 & color) >> 8).transform(green);
			new RectBorderTransformation(t, 0xFF & color).transform(blue);
			return;
		}
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

	public int getRed(int x, int y) {
		return red.getPixel(x, y);
	}

	public int getGreen(int x, int y) {
		return green.getPixel(x, y);
	}

	public int getBlue(int x, int y) {
		return blue.getPixel(x, y);
	}

	public List<Band> getBands() {
		return Arrays.asList(red, green, blue);
	}
}
