package com.goodengineer.atibackend.transformation2.noise;

import com.goodengineer.atibackend.model.Band;
import com.goodengineer.atibackend.transformation2.Transformation;
import com.goodengineer.atibackend.util.DistributionRandom;

public class GaussNoiseTransformation implements Transformation {

    private final double mu;
    private final double sigma;

    public GaussNoiseTransformation(double mu, double sigma) {
        this.mu = mu;
        this.sigma = sigma;
    }

    @Override
    public void transform(Band band) {
        for (int x = 0; x < band.getWidth(); x++) {
            for (int y = 0; y < band.getHeight(); y++) {
                band.setRawPixel(x, y, band.getRawPixel(x, y) + DistributionRandom.nextGaussian(mu, sigma));
            }
        }
    }
}
