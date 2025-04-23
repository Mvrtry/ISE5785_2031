package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * Class Tube is the basic class representing a tube in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Tube extends RadialGeometry {
    /**
     * The axis ray of the tube
     */
    protected final Ray axisRay;

    /**
     * Constructor to initialize Tube with axis ray and radius
     *
     * @param axisRay the axis ray
     * @param radius  the radius value
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public Vector getNormal(Point point) {
        // Calculate vector from point on tube to axis origin
        Vector pointToAxisOrigin = point.subtract(axisRay.p0);

        // Project pointToAxisOrigin onto the axis direction
        double projectionLength = pointToAxisOrigin.dotProduct(axisRay.dir);

        // Calculate the closest point on the axis to the given point
        Point closestPoint = axisRay.p0.add(axisRay.dir.scale(projectionLength));

        // Return normalized vector from closest point to the given point
        return point.subtract(closestPoint).normalize();
    }
}