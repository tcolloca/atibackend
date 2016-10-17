package com.goodengineer.atibackend.transformation.key_points;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.MultiplyImageTransformation;
import com.goodengineer.atibackend.transformation.SquareTransformation;
import com.goodengineer.atibackend.transformation.Transformation;
import com.goodengineer.atibackend.transformation.filter.MultiFilterTransformation;
import com.goodengineer.atibackend.transformation.filter.pixelRules.MaxPixelRule;
import com.goodengineer.atibackend.util.MaskFactory;
import com.goodengineer.atibackend.util.MaskFactory.Direction;
import com.goodengineer.atibackend.util.Point;

public class Harris {

	private static final double HARRIS_K = 0.04;

	public static List<Point> findKeyPoints(Band band, int keyPointsAmount) {
		Transformation IxTransformation = new MultiFilterTransformation(new MaxPixelRule(),
				MaskFactory.sobel(Direction.E));
		Band Ix = band.clone();
		IxTransformation.transform(Ix);

		Transformation IyTransformation = new MultiFilterTransformation(new MaxPixelRule(),
				MaskFactory.sobel(Direction.S));
		Band Iy = band.clone();
		IxTransformation.transform(Iy);

		Band Ixy = Ix.clone();
		Transformation multiplyTransformation = new MultiplyImageTransformation(Iy);
		multiplyTransformation.transform(Ixy);

		Band Ix2 = Ix;
		Band Iy2 = Iy;

		Transformation squareTransformation = new SquareTransformation();
		squareTransformation.transform(Ix2);
		squareTransformation.transform(Iy2);

		PriorityQueue<PixelNode> heap = new PriorityQueue<>(10, Collections.reverseOrder());
		
		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				double ix2 = Ix2.getRawPixel(x, y);
				double iy2 = Iy2.getRawPixel(x, y);
				double ixy = Ixy.getRawPixel(x, y);
				double newColor = (ix2 * iy2 - ixy * ixy) - HARRIS_K * (ix2 + iy2) * (ix2 + iy2);
				heap.add(new PixelNode(x, y, newColor));
			}
		}
		
		List<Point> keyPoints = new ArrayList<>();
		for (int i = 0; i < keyPointsAmount; i++) {
			PixelNode pixelNode = heap.poll();
			keyPoints.add(new Point(pixelNode.x, pixelNode.y));
		}
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
