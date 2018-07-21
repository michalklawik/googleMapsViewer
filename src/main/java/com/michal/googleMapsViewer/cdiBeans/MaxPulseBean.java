package com.michal.googleMapsViewer.cdiBeans;

public class MaxPulseBean implements MaxPulse {
    @Override
    public double calculateMaxPulseForMen(int age) {
        return 202 - (0.55 * age);
    }

    @Override
    public double calculateMaxPulseForWomen(int age) {
        return 216 - (1.09 * age);
    }
}
