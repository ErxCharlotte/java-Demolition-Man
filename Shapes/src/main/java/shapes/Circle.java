package shapes;

import processing.core.PImage;

/**
 * Represents a simple Circle object.
 */
public class Circle extends Shape {

    /**
     * The number of seconds between jumps.
     */
    public static final double SECONDS_BETWEEN_MOVES = 0.2;

    /**
     * Keeps track of the number of frames since the last jump.
     */
    private int timer;

    /**
     * Creates a new Circle object at (300, 250)
     */
    public Circle() {
        super(300, 250);
        this.timer = 0;
    }

    /**
     * Updates the circle every frame.
     */
    public void tick(App app) {
        // Increments the timer
        this.timer++;
        //System.out.println(app.frameCount);

        // If more frames have passed than the number of seconds x the framerate
        // the circle jumps 30 pixels to the left
        if (this.timer > SECONDS_BETWEEN_MOVES * App.FPS) {
            System.out.println(app.frameCount);
            this.x -= 30;
            // The timer is reset to 0
            this.timer = 0;
        }
    }
}
