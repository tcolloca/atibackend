package com.goodengineer.atibackend.transformation.canny;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.Transformation;
import com.goodengineer.atibackend.transformation.threshold.HysteresisTransformation;

public class CannyTransformation implements Transformation {

	private NoMaxTransformation noMaxTransformation;
	private int l1;
	private int l2;
	
	public CannyTransformation(int l1, int l2) {
		this.noMaxTransformation = new NoMaxTransformation();
		this.l1 = l1;
		this.l2 = l2;
	}
	
	@Override
	public void transform(Band band) {
		noMaxTransformation.transform(band);
		new HysteresisTransformation(l1, l2).transform(band);
	}
}
