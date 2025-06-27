package renderer;

import primitives.*;

import java.util.MissingResourceException;

/**
 * Camera class represents a camera in 3D space
 * Uses the Builder design pattern for construction
 *
 * @author Maor Atari
 */
public class Camera implements Cloneable {
    /**
     * The location of the camera in 3D space
     */
    private Point location;
    /**
     * The forward direction vector (towards the view plane)
     */
    private Vector vTo;
    /**
     * The up direction vector
     */
    private Vector vUp;
    /**
     * The right direction vector
     */
    private Vector vRight;
    /**
     * The width of the view plane
     */
    private double width;
    /**
     * The height of the view plane
     */
    private double height;
    /**
     * The distance from the camera to the view plane
     */
    private double distance;

    /**
     * Private constructor for Camera
     */
    private Camera() {
        this.location = null;
        this.vTo = null;
        this.vUp = null;
        this.vRight = null;
        this.width = 0.0;
        this.height = 0.0;
        this.distance = 0.0;
    }

    /**
     * Get a new Builder instance
     *
     * @return new Builder object
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Construct a ray through a pixel
     *
     * @param nX number of pixels in X (width) - columns
     * @param nY number of pixels in Y (height) - rows
     * @param j  pixel column index (X direction)
     * @param i  pixel row index (Y direction)
     * @return ray through the pixel center
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC = location.add(vTo.scale(distance));

        double rY = height / nY;
        double rX = width / nX;

        double yI = -(i - (nY - 1) / 2.0) * rY;
        double xJ = (j - (nX - 1) / 2.0) * rX;

        Point pIJ = pC;

        if (!Util.isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }

        if (!Util.isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(yI));
        }

        Vector vIJ = pIJ.subtract(location);

        return new Ray(location, vIJ);
    }

    @Override
    protected Camera clone() throws CloneNotSupportedException {
        return (Camera) super.clone();
    }

    /**
     * Builder class for Camera
     */
    public static class Builder {
        /**
         * The camera instance being built
         */
        private final Camera camera;

        /**
         * Constructor for Builder
         */
        public Builder() {
            this.camera = new Camera();
        }

        /**
         * Set camera location
         *
         * @param location the camera location
         * @return this builder
         */
        public Builder setLocation(Point location) {
            if (location == null) {
                throw new IllegalArgumentException("Location cannot be null");
            }
            camera.location = location;
            return this;
        }

        /**
         * Set camera direction with forward and up vectors
         *
         * @param vTo forward direction vector
         * @param vUp up direction vector
         * @return this builder
         * @throws IllegalArgumentException if vectors are not orthogonal
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (vTo == null || vUp == null) {
                throw new IllegalArgumentException("Direction vectors cannot be null");
            }

            if (!Util.isZero(vTo.dotProduct(vUp))) {
                throw new IllegalArgumentException("Direction vectors must be orthogonal");
            }

            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            return this;
        }

        /**
         * Set camera direction by target point and up vector
         *
         * @param target target point to look at
         * @param vUp    approximate up vector
         * @return this builder
         */
        public Builder setDirection(Point target, Vector vUp) {
            if (target == null || vUp == null) {
                throw new IllegalArgumentException("Target and up vector cannot be null");
            }

            if (camera.location == null) {
                throw new IllegalArgumentException("Location must be set before direction");
            }

            Vector vTo = target.subtract(camera.location).normalize();
            Vector vRight = vTo.crossProduct(vUp).normalize();
            Vector vUpActual = vRight.crossProduct(vTo).normalize();

            camera.vTo = vTo;
            camera.vUp = vUpActual;
            camera.vRight = vRight;
            return this;
        }

        /**
         * Set camera direction by target point (assumes up is Y axis)
         *
         * @param target target point to look at
         * @return this builder
         * @throws IllegalArgumentException if camera points exactly up
         */
        public Builder setDirection(Point target) {
            if (target == null) {
                throw new IllegalArgumentException("Target cannot be null");
            }

            if (camera.location == null) {
                throw new IllegalArgumentException("Location must be set before direction");
            }

            Vector vTo = target.subtract(camera.location).normalize();

            // Default up vector is Y axis
            Vector vUpApprox = new Vector(0, 1, 0);

            // If camera points along Y axis, the cross product will be zero
            // This will throw an exception in setDirection(Point, Vector)
            return setDirection(target, vUpApprox);
        }

        /**
         * Set view plane size
         *
         * @param width  view plane width
         * @param height view plane height
         * @return this builder
         */
        public Builder setVpSize(double width, double height) {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Width and height must be positive");
            }
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * Set view plane distance
         *
         * @param distance distance from camera to view plane
         * @return this builder
         */
        public Builder setVpDistance(double distance) {
            if (distance <= 0) {
                throw new IllegalArgumentException("Distance must be positive");
            }
            camera.distance = distance;
            return this;
        }

        /**
         * Set image resolution (placeholder for now)
         *
         * @param nX horizontal resolution
         * @param nY vertical resolution
         * @return this builder
         */
        public Builder setResolution(int nX, int nY) {
            if (nX <= 0 || nY <= 0) {
                throw new IllegalArgumentException("Resolution must be positive");
            }
            return this;
        }

        /**
         * Build the camera
         *
         * @return new Camera instance
         * @throws MissingResourceException if required data is missing
         */
        public Camera build() {
            final String MISSING_DATA = "Missing rendering data";
            final String CAMERA_CLASS = "Camera";

            if (camera.location == null) {
                throw new MissingResourceException(MISSING_DATA, CAMERA_CLASS, "location");
            }

            if (camera.vTo == null) {
                throw new MissingResourceException(MISSING_DATA, CAMERA_CLASS, "direction vector vTo");
            }

            if (camera.vUp == null) {
                throw new MissingResourceException(MISSING_DATA, CAMERA_CLASS, "direction vector vUp");
            }

            if (Util.isZero(camera.width)) {
                throw new MissingResourceException(MISSING_DATA, CAMERA_CLASS, "view plane width");
            }

            if (Util.isZero(camera.height)) {
                throw new MissingResourceException(MISSING_DATA, CAMERA_CLASS, "view plane height");
            }

            if (Util.isZero(camera.distance)) {
                throw new MissingResourceException(MISSING_DATA, CAMERA_CLASS, "view plane distance");
            }

            if (camera.vRight == null) {
                camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            }

            try {
                return camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Clone not supported", e);
            }
        }
    }


}