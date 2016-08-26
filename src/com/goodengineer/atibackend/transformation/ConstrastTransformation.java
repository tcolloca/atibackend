package com.goodengineer.atibackend.transformation;

import com.goodengineer.atibackend.ImageSource;

/**
 * I really doubt this transformation is well implemented.
 * Ask Juliana if the result looks ok.
 */
public class ConstrastTransformation implements ImageTransformation {

	private int r1;
	private int r2;
	
	public ConstrastTransformation(int r1, int r2) {
		this.r1 = r1;
		this.r2 = r2;
	}

	@Override
	public void transform(ImageSource imageSource) {
//		int s1 = r1/2;
//		int s2 = (r2 + 255)/2;
//		
//		final Function f1 = new LinearFunction(0, 0, r1, s1);
//		final Function f2 = new LinearFunction(r1, s1, r2, s2);
//		final Function f3 = new LinearFunction(r2, s2, 255, 255);
//		
//		Function function = new Function() {
//			@Override
//			public int apply(int x) {
//				if (x <= r1) return f1.apply(x);
//				else if(x <= r2) return f2.apply(x);
//				return f3.apply(x);
//			}
//		};
//		
//		for (int x = 0; x < imageSource.getWidth(); x++) {
//			for (int y = 0; y < imageSource.getHeight(); y++) {
//				imageSource.setPixel(x, y, function.apply(imageSource.getPixel(x, y)));
//			}
//		}
	}
}
