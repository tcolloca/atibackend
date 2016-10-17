package com.goodengineer.atibackend.transformation.key_points;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.MultiplyImageTransformation;
import com.goodengineer.atibackend.transformation.SquareTransformation;
import com.goodengineer.atibackend.transformation.Transformation;
import com.goodengineer.atibackend.transformation.filter.FilterAndZeroCrossTransformation;
import com.goodengineer.atibackend.transformation.filter.FilterTransformation;
import com.goodengineer.atibackend.transformation.filter.MultiFilterTransformation;
import com.goodengineer.atibackend.transformation.filter.ZeroCrossTransformation;
import com.goodengineer.atibackend.transformation.filter.pixelRules.MaxPixelRule;
import com.goodengineer.atibackend.transformation.filter.pixelRules.NormPixelRule;
import com.goodengineer.atibackend.util.MaskFactory;
import com.goodengineer.atibackend.util.MaskFactory.Direction;
import com.goodengineer.atibackend.util.Point;

public class Harris {

	private static final double HARRIS_K = 0.04;

	public static List<Point> findKeyPoints(Band inputBand, int keyPointsAmount) {
		Band band = inputBand.clone();

		Transformation LoGTransformation = new FilterAndZeroCrossTransformation(0, MaskFactory.LoG(7, 1));
		LoGTransformation.transform(band);

		Transformation IxTransformation = new MultiFilterTransformation(new NormPixelRule(),
				MaskFactory.sobel(MaskFactory.Direction.E));
		Band Ix = band.clone();
		IxTransformation.transform(Ix);

		Transformation IyTransformation = new MultiFilterTransformation(new NormPixelRule(),
				MaskFactory.sobel(MaskFactory.Direction.S));
		Band Iy = band.clone();
		IyTransformation.transform(Iy);

		Band Ixy = Ix.clone();

		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				double ix = Ix.getRawPixel(x, y);
				double iy = Iy.getRawPixel(x, y);
				double newColor = ix * iy;
				Ixy.setRawPixel(x, y, newColor);
			}
		}

		Band Ix2 = Ix;
		Band Iy2 = Iy;

		Transformation squareTransformation = new SquareTransformation();
		squareTransformation.transform(Ix2);
		squareTransformation.transform(Iy2);

		Transformation gaussTransformation = new FilterTransformation(MaskFactory.gauss(7, 2));
		gaussTransformation.transform(Ix2);
		gaussTransformation.transform(Iy2);
		gaussTransformation.transform(Ixy);

		Band maxima = band.clone();
//		PriorityQueue<PixelNode> heap = new PriorityQueue<>(10, Collections.<PixelNode>reverseOrder());
		
		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				double ix2 = Ix2.getRawPixel(x, y);
				double iy2 = Iy2.getRawPixel(x, y);
				double ixy = Ixy.getRawPixel(x, y);
				double newColor = (ix2 * iy2 - ixy * ixy) - HARRIS_K * (ix2 + iy2) * (ix2 + iy2);
//				double newColor = (ix2 * iy2 - ixy * ixy) / (ix2 + iy2 + HARRIS_K);
				maxima.setRawPixel(x, y, newColor);
			}
		}

		List<Point> keyPoints = new ArrayList<>();
		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				if (maxima.getRawPixel(x, y) > maxima.getMax() * 0.5) {
					keyPoints.add(new Point(x, y));
				}
			}
		}

//		for (int i = 0; i < keyPointsAmount; i++) {
//			PixelNode pixelNode = heap.poll();
//			keyPoints.add(new Point(pixelNode.x, pixelNode.y));
//		}
		return keyPoints;
	}
	
	private static class PixelNode implements Comparable<PixelNode> {

		public final int x;
		public final int y;
		private final double value;
		
		public PixelNode(int x, int y, double value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
		
		@Override
		public int compareTo(PixelNode o) {
			if (value - o.value < 0) return -1;
			else if (value - o.value > 0) return 1;
			return 0;
		}
		
	}
}
