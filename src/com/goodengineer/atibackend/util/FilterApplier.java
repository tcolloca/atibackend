package com.goodengineer.atibackend.util;

import java.util.Set;
import java.util.TreeSet;

public class FilterApplier {

    public static double squareAverageFilter(double[][] matrix) {
        int size = matrix.length;
        double count = 0;
        for (double[] row : matrix) {
            for (double cell : row) {
                count += cell;
            }
        }
        return count / size*size;
    }

    public static double squareGaussianFilter(double[][] matrix, double sigma) {
        int size = matrix.length;
        double count = 0;
        double gaussCount = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double exp  = Math.exp((Math.pow(x, 2) + Math.pow(y, 2)) / - Math.pow(sigma, 2));
                double gauss = 1 / (2 * Math.PI * Math.pow(sigma, 2)) * exp;
                gaussCount += gauss;
                count += matrix[x][y] / gauss;
            }
        }
        return count / gaussCount;
    }

    public static double squareMedianFilter(double[][] matrix) {
        int size = matrix.length;
        Set<Double> medianSet = new TreeSet<>();
        for (double[] row : matrix) {
            for (double cell : row) {
                medianSet.add(cell);
            }
        }
        int i = 0;
        for (double num : medianSet) {
            if (i == (size - 1) / 2) {
                return num;
            }
            i++;
        }
        throw new IllegalStateException();
    }
}
