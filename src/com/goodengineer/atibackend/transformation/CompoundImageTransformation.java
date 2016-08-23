package com.goodengineer.atibackend.transformation;

import java.util.LinkedList;

import com.goodengineer.atibackend.ImageSource;

public class CompoundImageTransformation implements ImageTransformation {

	private LinkedList<ImageTransformation> transformations;

	public CompoundImageTransformation() {
		transformations = new LinkedList<>();
	}

	@Override
	public void transform(ImageSource imageSource) {
		for (ImageTransformation imageTransformation : transformations) {
			imageTransformation.transform(imageSource);
			imageSource.normalize();
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
