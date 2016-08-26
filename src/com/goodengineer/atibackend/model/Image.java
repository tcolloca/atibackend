package com.goodengineer.atibackend.model;

import com.goodengineer.atibackend.transformation2.Transformation;

public interface Image {
	int getWidth();

	int getHeight();

	void transform(Transformation transformation);
}
