package com.goodengineer.atibackend.transformation.key_points;

import java.util.List;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation.Transformation;
import com.goodengineer.atibackend.util.Point;

public class HarrisTransformation implements Transformation {

	@Override
	public void transform(Band band) {
		List<Point> keyPoints = Harris.findKeyPoints(band, 25);
		for (Point point: keyPoints) {
			band.setPixel(point.x, point.y, 0);
		}
	}
}
