package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * Simple implementation of ray tracer
 * Provides basic ray tracing functionality using ambient lighting only
 *
 * @author Maor Atari
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * Constructor to initialize simple ray tracer with a scene
     * Calls the parent constructor to set up the scene
     *
     * @param scene the 3D scene to be rendered
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray through the scene and calculates its color intensity
     * Implements basic ray tracing with ambient lighting only
     *
     * @param ray the ray to trace through the scene
     * @return the color intensity of the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        // Find intersections between ray and scene geometries
        List<Point> intersections = scene.geometries.findIntersections(ray);

        // If no intersections found, return background color
        if (intersections == null) {
            return scene.background;
        }

        // Find the closest intersection point to ray origin
        Point closestPoint = ray.findClosestPoint(intersections);

        // Calculate and return the color at the intersection point
        return calcColor(closestPoint);
    }

    /**
     * Calculates the color at a given point in the scene
     * Currently returns only ambient light intensity (no use of point parameter yet)
     *
     * @param point the point at which to calculate color (currently unused)
     * @return the color at the given point (currently only ambient light)
     */
    private Color calcColor(Point point) {
        // At this stage, return only ambient light intensity
        return scene.ambientLight.getIntensity();
    }
}