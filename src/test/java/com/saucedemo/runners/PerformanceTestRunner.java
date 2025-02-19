package com.saucedemo.runners;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTestRunner {
    private static List<Double> responseTimes = new ArrayList<>();
    private static long startTime = 0;

    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public static void stopTimer() {
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0; // Convert to seconds
        responseTimes.add(responseTime);
    }

    public static double getAverageResponseTime() {
        if (responseTimes.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Double time : responseTimes) {
            sum += time;
        }
        return sum / responseTimes.size();
    }

    public static void resetTimer() {
        responseTimes.clear();
        startTime = 0;
    }

    // Optional: Get the maximum response time
    public static double getMaxResponseTime() {
        if (responseTimes.isEmpty()) {
            return 0.0;
        }
        return responseTimes.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);
    }
} 