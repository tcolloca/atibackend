package com.goodengineer.atibackend.util;

import java.util.Random;

public class DistributionRandom {

    private static Random random = new Random();

    private double nextGaussian(double mu, double sigma) {
        return random.nextGaussian()*sigma + mu;
    }

    private double nextExponential(double lambda) {
        return Math.log(1-random.nextDouble())/(-lambda);
    }

    private double nextRayleigh(double epsilon) {
        return Math.sqrt(epsilon * Math.log(1 / (1 - random.nextDouble())));
    }
}
