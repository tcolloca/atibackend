package com.goodengineer.atibackend.transformation2.filter;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation2.Transformation;

abstract class FilterTransformation implements Transformation {

    final int size;

    FilterTransformation(int size) {
        if (size % 2 == 0) {
            throw new IllegalArgumentException("Filter must be odd sized.");
        }
        this.size = size;
    }

    void fillMatrix(Band band, int x, int y, double[][] matrix) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = getMatrixValue(band, x, y, i, j);
            }
        }
    }

    private double getMatrixValue(Band band, int x, int y, int i, int j) {
        int relativeX = x - (size - 1) / 2 + i;
        int relativeY = x - (size - 1) / 2 + i;
        if (relativeX < 0 || relativeX >= band.getWidth() || relativeY < 0 || relativeY >= band.getHeight()) {
            return band.getValidMin();
        }
        return band.getPixel(relativeX, relativeY);
    }
}
