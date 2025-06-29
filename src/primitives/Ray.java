package primitives;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Class Ray is the basic class representing a ray (half-infinite line) of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Ray {
    /**
     * The starting point of the ray
     */
    private final Point p0;
    /**
     * The direction vector of the ray (always normalized)
     */
    private final Vector dir;

    /**
     * Constructor to initialize Ray based on starting point and direction vector
     *
     * @param p0  the starting point
     * @param dir the direction vector (will be normalized)
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Gets the starting point of the ray
     *
     * @return the starting point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Gets the direction vector of the ray
     *
     * @return the direction vector
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Calculates a point on the ray at a given distance from the starting point
     *
     * @param t the distance from the starting point (can be negative)
     * @return the point on the ray at distance t from p0
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            return p0;
        }
        return p0.add(dir.scale(t));
    }

    /**
     * Finds the closest point to the ray's starting point from a list of points
     *
     * @param points list of points to search through
     * @return the closest point to the ray's starting point, or null if the list is null or empty
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null || points.isEmpty()) {
            return null;
        }

        Point closestPoint = points.get(0);
        double minDistance = p0.distanceSquared(closestPoint);

        for (Point point : points) {
            double distance = p0.distanceSquared(point);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = point;
            }
        }

        return closestPoint;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.p0.equals(other.p0)
                && this.dir.equals(other.dir);
    }

    @Override
    public String toString() {
        return "Ray{" + "p0=" + p0 + ", dir=" + dir + '}';
    }
}