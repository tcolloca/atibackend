package com.goodengineer.atibackend.plates;

import java.util.ArrayList;
import java.util.List;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.Transformation;
import com.goodengineer.atibackend.transformation.threshold.OtsuThresholdingTransformation;

public class PlateRecognitionTransformation implements Transformation {

	private static final double MIN_ASPECT_RATIO = 2;
	private static final double MAX_ASPECT_RATIO = 5;
	private static final double MIN_EULER = 3;
	private static final double MAX_EULER = 12;
	private static final double MAX_COMPONENT_SIZE_RATIO = 0.0100; 
	private static final double MIN_COMPONENT_SIZE_RATIO = 0.0008;
	
	public PlateRecognitionTransformation() {
		super();
	}

	@Override
	public void transform(Band band) {
		Band original = band.clone();
		
		new OtsuThresholdingTransformation().transform(band); // TODO: Improve Otsu por Seuvola
		
		List<Component> rawComponents = ComponentFinder.findComponents(band, 0);
		
		int size = band.getHeight() * band.getWidth();
		int minComponentSize = (int) (MIN_COMPONENT_SIZE_RATIO * size);
		int maxComponentSize = (int) (MAX_COMPONENT_SIZE_RATIO * size);
		List<Component> components = new ArrayList<>();
		for (Component component : rawComponents) {	
			if (minComponentSize <= component.size() 
					&& component.size() <= maxComponentSize
					&& MIN_ASPECT_RATIO <= component.getAspectRatio()
					&& component.getAspectRatio() <= MAX_ASPECT_RATIO
					&& MIN_EULER <= component.eulerNumber()
					&& component.eulerNumber() <= MAX_EULER) {
				components.add(component);
			}
		}
		
		double[][] newPixels = new double[band.getWidth()][band.getHeight()];
		for (Component component : components) {
			component.getRotation(newPixels);
//			for (int[] pixel : component.getPixels()) {
//				newPixels[pixel[0]][pixel[1]] = 255;
//			}
		}
		band.setPixels(newPixels);
	}
}
