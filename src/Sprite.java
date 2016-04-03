import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * A Rectangle class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public interface Sprite {
    // draw the sprite to the screen
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    void timePassed();
}