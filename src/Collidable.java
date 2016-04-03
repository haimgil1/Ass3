/**
 * A Collidable interface.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
