package com.goodengineer.atibackend.plates;

import java.util.ArrayList;
import java.util.List;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.threshold.SauvolaThresholdingTransformation;
import com.goodengineer.atibackend.util.Point;

public class DigitsExtractor {
	private static final double MIN_ASPECT_RATIO = 1/3.0;
	private static final double MAX_ASPECT_RATIO = 2/3.0;
	private static final double MIN_EULER = 3;
	private static final double MAX_EULER = 12;
	private static final double MAX_COMPONENT_SIZE_RATIO = 0.0100; 
	private static final double MIN_COMPONENT_SIZE_RATIO = 0.0008;
	
	public static List<Band> extract(Band band) {
//		Band original = band.clone();
//		
//		System.out.println("Thresholding with Sauvola...");
//		new SauvolaThresholdingTransformation(0.5, 128, 20).transform(band);
//		
//		System.out.println("Finding components...");
//		List<Component> rawComponents = ComponentFinder.findComponents(band, 0);
//		System.out.println(rawComponents.size() + " components found.");
//		
//		int size = band.getHeight() * band.getWidth();
//		int minComponentSize = (int) (MIN_COMPONENT_SIZE_RATIO * size);
//		int maxComponentSize = (int) (MAX_COMPONENT_SIZE_RATIO * size);
//		List<Component> components = new ArrayList<>();
//		System.out.println("Selecting components...");
//		for (Component component : rawComponents) {	
//			if (minComponentSize <= component.size() 
//					&& component.size() <= maxComponentSize
//					&& MIN_ASPECT_RATIO <= component.getAspectRatio()
//					&& component.getAspectRatio() <= MAX_ASPECT_RATIO
//					&& MIN_EULER <= component.eulerNumber()
////					&& component.eulerNumber() <= MAX_EULER
//					) {
//				components.add(component);
//			}
//		}
//		System.out.println(components.size() + " components left.");
//		
//		System.out.println("Finding corners...");
//		double[][] newPixels = new double[band.getWidth()][band.getHeight()];
//		for (Component component : components) {
//			List<Point> corners = component.getCorners();
//			Band resizedBand = ImageResizer.resizeQuad(original, corners, 228, 75);
//		}
//		System.out.println("Finished!");
		return null;
	}
}
