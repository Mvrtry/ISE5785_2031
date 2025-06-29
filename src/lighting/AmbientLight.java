package lighting;

import primitives.Color;

/**
 * Class AmbientLight represents ambient lighting in a 3D scene.
 * Ambient light provides uniform illumination from all directions.
 *
 * @author Maor Atari
 */
public class AmbientLight {

    /**
     * The intensity of the ambient light (IA)
     * Private and final - cannot be modified after construction
     */
    private final Color intensity;

    /**
     * Static constant representing no ambient light (black/zero intensity)
     * Public access for easy reference
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK);

    /**
     * Constructor to initialize AmbientLight with given intensity
     *
     * @param intensity the intensity of the ambient light (IA) as Color
     */
    public AmbientLight(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Getter for the ambient light intensity
     *
     * @return the intensity of the ambient light as Color
     */
    public Color getIntensity() {
        return intensity;
    }
}