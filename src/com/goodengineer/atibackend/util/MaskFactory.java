package com.goodengineer.atibackend.util;

public class MaskFactory {

    public static double[][] average(int size) {
        double[][] mask = new double[size][size];
        double value = 1.0 / (size * size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mask[i][j] = value;
            }
        }
        return mask;
    }

    public static double[][] gauss(int size, double sigma) {
        double[][] mask = new double[size][size];
        double gaussCount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int xDist = (size - 1) / 2 - i;
                int yDist = (size - 1) / 2 - j;
                double exp  = Math.exp((Math.pow(xDist, 2) + Math.pow(yDist, 2)) / (-2 * Math.pow(sigma, 2)));
                double gauss = 1 / (2 * Math.PI * Math.pow(sigma, 2)) * exp;
                gaussCount += gauss;
                mask[i][j] = gauss;
            }
        }
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mask[i][j] = mask[i][j] / gaussCount;
            }
        }
        
        return mask;
    }

    public static double[][] hiPass(int size) {
        double[][] mask = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == (size - 1) / 2 && j == (size - 1) / 2) {
                    mask[i][j] = (size * size - 1);
                } else {
                    mask[i][j] = - 1;
                }
            }
        }
        return mask;
    }
}
