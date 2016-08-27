package com.goodengineer.atibackend.transformation2.filter;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.util.FilterApplier;

public class GaussFilterTransformation extends FilterTransformation {

    private final double sigma;

    public GaussFilterTransformation(int size, double sigma) {
        super(size);
        this.sigma = sigma;
    }

    @Override
    public void transform(Band band) {
        double[][] matrix = new double[size][size];
        for (int x = 0; x < band.getWidth(); x++) {
            for (int y = 0; y < band.getHeight(); y++) {
                fillMatrix(band, x, y, matrix);
                band.setRawPixel(x, y, FilterApplier.squareGaussianFilter(matrix, sigma));
            }
        }
    }
}
