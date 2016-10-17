package com.goodengineer.atibackend.transformation.key_points;

import java.util.List;
import java.util.PriorityQueue;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.MultiplyImageTransformation;
import com.goodengineer.atibackend.transformation.PowerTransformation;
import com.goodengineer.atibackend.transformation.SquareTransformation;
import com.goodengineer.atibackend.transformation.Transformation;
import com.goodengineer.atibackend.transformation.filter.FilterTransformation;
import com.goodengineer.atibackend.transformation.filter.MultiFilterTransformation;
import com.goodengineer.atibackend.transformation.filter.pixelRules.MaxPixelRule;
import com.goodengineer.atibackend.transformation.filter.pixelRules.NormPixelRule;
import com.goodengineer.atibackend.util.MaskFactory;
import com.goodengineer.atibackend.util.Point;

public class HarrisTransformation implements Transformation {

	@Override
	public void transform(Band band) {
//		Transformation IxTransformation = new MultiFilterTransformation(new NormPixelRule(),
//				MaskFactory.sobel(MaskFactory.Direction.E));
//		Band Ix = band.clone();
//		IxTransformation.transform(Ix);
//
//		Transformation IyTransformation = new MultiFilterTransformation(new NormPixelRule(),
//				MaskFactory.sobel(MaskFactory.Direction.S));
//		Band Iy = band.clone();
//		IyTransformation.transform(Iy);
//
//		Band Ixy = Ix.clone();
//
//		for (int x = 0; x < band.getWidth(); x++) {
//			for (int y = 0; y < band.getHeight(); y++) {
//				double ix = Ix.getRawPixel(x, y);
//				double iy = Iy.getRawPixel(x, y);
//				double newColor = ix * iy;
//				Ixy.setRawPixel(x, y, newColor);
//			}
//		}
//
//		Band Ix2 = Ix;
//		Band Iy2 = Iy;
//
//		Transformation squareTransformation = new SquareTransformation();
//		squareTransformation.transform(Ix2);
//		squareTransformation.transform(Iy2);
//
//		Transformation gaussTransformation = new FilterTransformation(MaskFactory.gauss(3, 1));
//		gaussTransformation.transform(Ix2);
//		gaussTransformation.transform(Iy2);
//		gaussTransformation.transform(Ixy);

//		for (int x = 0; x < band.getWidth(); x++) {
//			for (int y = 0; y < band.getHeight(); y++) {
//				double ix2 = Ix2.getRawPixel(x, y);
//				double iy2 = Iy2.getRawPixel(x, y);
//				double ixy = Ixy.getRawPixel(x, y);
//				double newColor = (ix2 * iy2 - ixy * ixy) - 0.04 * (ix2 + iy2) * (ix2 + iy2);
//				band.setRawPixel(x, y, newColor);
//			}
//		}

		List<Point> keyPoints = Harris.findKeyPoints(band, 40);
//		Transformation darkenTransformation = new PowerTransformation(2);
//		darkenTransformation.transform(band);
		for (Point point: keyPoints) {
			band.setPixel(point.x, point.y, 127);
			band.setPixel(point.x, point.y - 1, 127);
			band.setPixel(point.x - 1, point.y, 127);
			band.setPixel(point.x + 1, point.y, 127);
			band.setPixel(point.x, point.y + 1, 127);
		}
	}
}
