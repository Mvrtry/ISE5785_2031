package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Interface representing geometries that can be intersected by a ray
 *
 * @author Maor Atari
 */
public interface Intersectable {
    /**
     * Finds intersection points between a ray and the geometry
     *
     * @param ray the ray to check intersection with
     * @return list of intersection points, or null if there are no intersections
     */
    List<Point> findIntersections(Ray ray);
}