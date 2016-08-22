package com.goodengineer.atibackend;

import java.util.LinkedList;

public class CompoundImageTransformation implements ImageTransformation {

	private LinkedList<ImageTransformation> transformations;

	public CompoundImageTransformation() {
		transformations = new LinkedList<>();
	}

	@Override
	public void transform(ImageSource imageSource) {
		for (ImageTransformation imageTransformation : transformations) {
			imageTransformation.transform(imageSource);
		}
	}

	public void addTransformation(ImageTransformation imageTransformation) {
		transformations.add(imageTransformation);
	}

	/**
	 * This method will help implement the undo feature
	 */
	public void removeLastTransformation() {
		if (!transformations.isEmpty())
			transformations.removeLast();
	}
	
	public void clearTransformations() {
		transformations.clear();
	}
}
