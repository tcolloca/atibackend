package com.goodengineer.atibackend.model;

import com.goodengineer.atibackend.transformation2.Transformation;

public class BlackAndWhiteImage implements Image {
	
	private Band grays;
	
	public BlackAndWhiteImage(Band grays) {
		this.grays = grays;
	}
	
	@Override
	public void transform(Transformation transformation) {
		transformation.transform(grays);
	}
	
	public int getPixel(int x, int y) {
		return grays.getPixel(x, y);
	}

	@Override
	public int getWidth() {
		return grays.getWidth();
	}

	@Override
	public int getHeight() {
		return grays.getHeight();
	}
	
	public Band getBand() {
		return grays;
	}
}
