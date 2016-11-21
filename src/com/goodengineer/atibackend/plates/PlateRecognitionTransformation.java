package com.goodengineer.atibackend.plates;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.Transformation;

public class PlateRecognitionTransformation implements Transformation {

	@Override
	public void transform(Band band) {
		new SCWTransformation(2, 5, 4, 10, 0.7).transform(band);
	}

}
