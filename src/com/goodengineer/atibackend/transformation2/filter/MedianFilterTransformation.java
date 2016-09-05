package com.goodengineer.atibackend.transformation2.filter;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.util.FilterApplier;

public class MedianFilterTransformation extends FilterTransformation {

    public MedianFilterTransformation(int size) {
        super(size);
    }

    @Override
    public void transform(Band band) {
        double[][] matrix = new double[size][size];
        for (int x = 0; x < band.getWidth(); x++) {
            for (int y = 0; y < band.getHeight(); y++) {
                fillMatrix(band, x, y, matrix);
                band.setRawPixel(x, y, FilterApplier.squareMedianFilter(matrix));
            }
        }
    }
}
