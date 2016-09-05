package com.goodengineer.atibackend.transformation2.filter;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.util.MaskFactory;
//
//public class MedianFilterTransformation extends FilterTransformation {
//
//    public MedianFilterTransformation(int size) {
//        super(size);
//    }
//
//    @Override
//    public void transform(Band band) {
//        double[][] matrix = new double[size][size];
//        for (int x = 0; x < band.getWidth(); x++) {
//            for (int y = 0; y < band.getHeight(); y++) {
//                applyMask(band, x, y, matrix);
//                band.setRawPixel(x, y, MaskFactory.squareMedianFilter(matrix));
//            }
//        }
//    }
//}
