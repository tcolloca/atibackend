package com.goodengineer.atibackend.plates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.LineHoughTransformation;
import com.goodengineer.atibackend.transformation.filter.FilterTransformation;
import com.goodengineer.atibackend.transformation.filter.MultiFilterTransformation;
import com.goodengineer.atibackend.transformation.filter.pixelRules.MaxPixelRule;
import com.goodengineer.atibackend.transformation.filter.pixelRules.NormPixelRule;
import com.goodengineer.atibackend.transformation.key_points.HarrisTransformation;
import com.goodengineer.atibackend.transformation.key_points.SusanTransformation;
import com.goodengineer.atibackend.transformation.threshold.OtsuThresholdingTransformation;
import com.goodengineer.atibackend.util.MaskFactory;
import com.goodengineer.atibackend.util.MaskFactory.Direction;


class Component {
	
	private static final int MIN_COMPONENT_SIZE = 20;
	
	private final Band band;
	private final List<int[]> pixels = new ArrayList<>();
	private int minCol = Integer.MAX_VALUE;
	private int minRow = Integer.MAX_VALUE;
	private int maxCol;
	private int maxRow;
	private int eulerNumber = -1;
	
	public Component(Band band) {
		super();
		this.band = band;
	}

	void addPixel(int x, int y) {
		pixels.add(new int[]{x, y});
		if (x < minCol) {
			minCol = x;
		}
		if (y < minRow) {
			minRow = y;
		}
		if (x >= maxCol) {
			maxCol = x;
		}
		if (y >= maxRow) {
			maxRow = y;
		}
	}
	
	List<int[]> getPixels() {
		return pixels;
	}

	double getAspectRatio() {
		return  (maxCol - minCol + 1)/ (double)(maxRow - minRow + 1);
	}
	
	int size() {
		return pixels.size();
	}

	public int eulerNumber() {
		if (eulerNumber < 0) {
			Band subBand = band.subRegion(minCol - 1, minRow - 1, maxCol + 2, maxRow + 2);
			List<Component> subComponents = ComponentFinder.findComponents(subBand, 255);
			List<Component> components = new ArrayList<>();
			for (Component subComponent : subComponents) {
				if (subComponent.size() > MIN_COMPONENT_SIZE) {
					components.add(subComponent);
				}
			}
			eulerNumber = components.size();
		}
		return eulerNumber - 1;
	}
	
	double getRotation(double[][] newPixels) {
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		Band subBand = band.subRegion(minCol - 1, minRow - 1, maxCol + 2, maxRow + 2);
		int width = subBand.getWidth();
		int height = subBand.getHeight();
		
		MultiFilterTransformation.PixelRule rule = new NormPixelRule();
		List<double[][]> masks = Arrays.asList(MaskFactory.sobel(Direction.E), MaskFactory.sobel(Direction.S));
	    new MultiFilterTransformation(rule, masks).transform(subBand);
	    new OtsuThresholdingTransformation().transform(subBand);
//		int diag = (int) Math.sqrt(width * width + height * height);
		
//		System.out.println(height * 0.6);
//		LineHoughTransformation transform = new LineHoughTransformation(180, diag, 2.0, (int) (height * 0.55));
//		transform.transform(subBand);
		
		List<Component> subComponents = ComponentFinder.findComponents(subBand, 255);
		Component perimeter = subComponents.get(0);
		for (Component subC : subComponents) {
			if (subC.size() > perimeter.size()) {
				perimeter = subC;
			}
		}

		double[][] newSubPixels = new double[subBand.getWidth()][subBand.getHeight()];
		for (int[] pixel : perimeter.pixels) {
			newSubPixels[pixel[0]][pixel[1]] = 255;
		}
		subBand.setPixels(newSubPixels);
		SusanTransformation transform = new SusanTransformation(5, 0.75);
		transform.transform(subBand);
//		
		for (int x = minCol; x <= maxCol; x++) {
			for (int y = minRow; y <= maxRow; y++) {
				newPixels[x][y] = subBand.getPixel(x - minCol + 1, y - minRow + 1);
//				if (innerPixels[x - minCol + 1][y - minRow + 1]) {
//					newPixels[x][y] = 127;
//				}
//				if(band.getPixel(x, y) == 255 || innerPixels[x - minCol + 1][y - minRow + 1]) {
//					int auxx = x - minCol;
//					int auxy = y - minRow;
//					sum1 = auxx*auxy;
//					sum2 = auxx*auxx;
//					sum3 = auxy*auxy;
//				}
			}
		}
		return Math.atan(2*sum1 / (double)(sum3 - sum2)) * 180 / Math.PI / 2;
	}
}
