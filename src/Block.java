import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * A Block class implements Collidable.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private java.awt.Color color;

    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    // Return the "collision shape" of the object.
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    // javac -cp biuoop-1.4.jar:. BlockChecks.java -Xlint:unchecked for details.
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Rectangle recHit = this.getCollisionRectangle();

        //create the lines of the rectangle.
        Line upperLine = recHit.getUpperLine();
        Line leftLine = recHit.getLeftLine();
        Line bottomLine = recHit.getBottomLine();
        Line rightLine = recHit.getRightLine();

        //check if collisionPoint is on the bottom/upper side or on right/left side of the rectangle
        if (upperLine.ifPointOnLine(collisionPoint) || bottomLine.ifPointOnLine(collisionPoint)) {
            currentVelocity = new Velocity(dx, -dy);
        }
        if (leftLine.ifPointOnLine(collisionPoint) || rightLine.ifPointOnLine(collisionPoint)) {
            currentVelocity = new Velocity(-dx, dy);
        }
        return currentVelocity;
    }

    public void drawOn(DrawSurface surface) {
        int width = (int)rect.getWidth();
        int height = (int)rect.getHeight();
        int x = (int)rect.getUpperLeft().getX();
        int y = (int)rect.getUpperLeft().getY();
        surface.setColor(this.color); // Setting the color.
        surface.drawRectangle(x, y, width, height);
    }

    /**
     *
     */
    public void timePassed() {

    }
}
