package com.company;

public class DistanceCalculator {

    public double calculateDistance(double firstCaveX1, double firstCaveY1, double secondCaveX2, double secondCaveY2) {

        double ac = Math.abs(secondCaveY2 - firstCaveY1);
        double cb = Math.abs(secondCaveX2 - firstCaveX1);

        return Math.hypot(ac, cb);
    }
}
