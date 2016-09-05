package com.goodengineer.atibackend.model;

import com.goodengineer.atibackend.transformation.Transformation;

public interface Image {
	int getWidth();

	int getHeight();

	void transform(Transformation transformation);
}
