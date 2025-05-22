package edu.depaul.coffeeapp.tools;

import org.springframework.stereotype.Component;

/**
 * calculation utilized for finding closest shops to user (haversine, consider euclidean?)
 */
@Component
public class DistanceCalc {
    private static final double EARTH_RADIUS_MILES = 3963.1;

    public double calculateDistance(double lat1, double lat2, double lon1, double lon2) {
        return calculateDistanceHelper(lat1, lat2, lon1, lon2, EARTH_RADIUS_MILES);
    }

    /**
     * haversine formula
     * TODO: double check calculation for a
     * @param lat1
     * @param lat2
     * @param lon1
     * @param lon2
     * @param radius
     * @return
     */
    private double calculateDistanceHelper(double lat1, double lat2, double lon1, double lon2, double radius) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) *
                Math.cos(Math.toRadians(lat2)) * Math.cos(dLon * Math.PI / 180);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * c;
    }
}
