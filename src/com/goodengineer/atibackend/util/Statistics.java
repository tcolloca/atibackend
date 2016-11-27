package com.goodengineer.atibackend.util;


public class Statistics {
	double[] data;
	int size;

	public Statistics(double[] data) {
		this.data = data;
		size = data.length;
	}

	public double getMean() {
		double sum = 0.0;
		for (double a : data)
			sum += a;
		return sum / size;
	}

	public double getVariance() {
		double mean = getMean();
		double temp = 0;
		for (double a : data)
			temp += (a - mean) * (a - mean);
		return temp / size;
	}

	public double getStdDev() {
		return Math.sqrt(getVariance());
	}
}
