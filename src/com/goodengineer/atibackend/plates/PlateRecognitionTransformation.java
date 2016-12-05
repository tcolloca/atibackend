package com.goodengineer.atibackend.plates;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.model.ColorImage;
import com.goodengineer.atibackend.plates.util.BufferedImageColorImageTranslator;
import com.goodengineer.atibackend.plates.util.FileHelper;
import com.goodengineer.atibackend.transformation.Transformation;
import com.goodengineer.atibackend.transformation.threshold.OtsuThresholdingTransformation;
import com.goodengineer.atibackend.transformation.threshold.SauvolaThresholdingTransformation;
import com.goodengineer.atibackend.util.Point;

public class PlateRecognitionTransformation implements Transformation {

	private static int imageName = 0;
	
	private static final double MIN_ASPECT_RATIO = 2;
	private static final double MAX_ASPECT_RATIO = 5;
	private static final double MIN_EULER = 3;
	private static final double MAX_COMPONENT_SIZE_RATIO = 0.0100; 
	private static final double MIN_COMPONENT_SIZE_RATIO = 0.0008;
	private static final int PLATE_WIDTH = 228;
	private static final int PLATE_HEIGHT = 75;
	
	public PlateRecognitionTransformation() {
		super();
	}

	@Override
	public void transform(Band band) {
		Band original = band.clone();
		
		System.out.println("Thresholding with Sauvola...");
		new SauvolaThresholdingTransformation(0.5, 128, 20).transform(band);
		
		System.out.println("Finding components...");
		List<Component> rawComponents = ComponentFinder.findComponents(band, 0);
		System.out.println(rawComponents.size() + " components found.");
		
		int size = band.getHeight() * band.getWidth();
		int minComponentSize = (int) (MIN_COMPONENT_SIZE_RATIO * size);
		int maxComponentSize = (int) (MAX_COMPONENT_SIZE_RATIO * size);
		List<Component> components = new ArrayList<>();
		System.out.println("Selecting components...");
		for (Component component : rawComponents) {	
			if (minComponentSize <= component.size() 
					&& component.size() <= maxComponentSize
					&& MIN_ASPECT_RATIO <= component.getAspectRatio()
					&& component.getAspectRatio() <= MAX_ASPECT_RATIO
					&& MIN_EULER <= component.eulerNumber()
//					&& component.eulerNumber() <= MAX_EULER
					) {
				components.add(component);
			}
		}
		System.out.println(components.size() + " components left.");
		
		System.out.println("Finding corners...");
		for (Component component : components) {
			List<Point> corners = component.getCorners();
			if (corners.size() == 4) {
				Band resizedBand = ImageResizer.resizeQuad(original, corners, PLATE_WIDTH, PLATE_HEIGHT);
				List<Band> digits = DigitsExtractor.extract(resizedBand, 6);
				for (Band digit : digits) {
					new OtsuThresholdingTransformation().transform(digit);
					ColorImage image = new ColorImage(digit, digit, digit);
					BufferedImage buffImage = new BufferedImageColorImageTranslator().translateBackward(image);
					FileHelper.saveImage(buffImage, "_temp_" + imageName++ + ".png");
					
					// CLASSIFY
				}
			}
		}
		System.out.println("Finished!");
	}
}
