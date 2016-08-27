package com.goodengineer.atibackend.util;

import java.util.Set;
import java.util.TreeSet;

public class FilterApplier {

    private static double squareAverageFilter(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix is not square.");
        }
        int size = matrix.length;
        if (size % 2 == 0) {
            throw new IllegalArgumentException("Matrix is even sized.");
        }
        double count = 0;
        for (double[] row : matrix) {
            for (double cell : row) {
                count += cell;
            }
        }
        return count / size*size;
    }

    private static double squareGaussianFilter(double[][] matrix, double sigma) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix is not square.");
        }
        int size = matrix.length;
        if (size % 2 == 0) {
            throw new IllegalArgumentException("Matrix is even sized.");
        }
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

    private static double squareMedianFilter(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix is not square.");
        }
        int size = matrix.length;
        if (size % 2 == 0) {
            throw new IllegalArgumentException("Matrix is even sized.");
        }
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
