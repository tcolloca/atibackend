package com.goodengineer.atibackend.transformation.threshold;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.Transformation;

public class HysteresisTransformation implements Transformation {

	private int l1;
	private int l2;
	
	public HysteresisTransformation(int l1, int l2) {
		this.l1 = l1;
		this.l2 = l2;
	}
	
	@Override
	public void transform(Band band) {
		boolean[][] visited = new boolean[band.getWidth()][band.getHeight()];
		double[][] result = new double[band.getWidth()][band.getHeight()];
		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				if (visited[x][y]) continue;
				if (band.getPixelNormalized(x, y) < l1) {
					result[x][y] = 0;
					visited[x][y] = true;
				}
				else if (band.getPixelNormalized(x, y) > l2) {
					markBorders(band, result, x, y, visited);
				}
			}
		}
		
//		Get rid of isolated l1<p<l2 pixels
		for (int x = 0; x < band.getWidth(); x++) {
			for (int y = 0; y < band.getHeight(); y++) {
				if (!visited[x][y]) result[x][y] = 0;
			}
		}
		
		band.setPixels(result);
	}
	
	private void markBorders(Band band, double[][] result, int x, int y, boolean[][] visited) {
		if (x < 0 || y < 0 || x >= band.getWidth() || y >= band.getHeight()) return;
		if (visited[x][y]) return;
		int p = band.getPixelNormalized(x, y);
		if (!(l1 <= p && p <= l2)) return;
		visited[x][y] = true;
		result[x][y] = 255.0;
		markBorders(band, result, x + 1, y, visited);
		markBorders(band, result, x - 1, y, visited);
		markBorders(band, result, x, y + 1, visited);
		markBorders(band, result, x, y - 1, visited);
	}
}
