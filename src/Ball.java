import biuoop.DrawSurface;

/**
 * A Ball class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEn;

    /**
     * Constructs a ball given the point of the center.
     *
     * @param center is the center of the ball.
     * @param r      is the radius.
     * @param color  is the color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;

    }

    /**
     * Constructs a ball given the coordinate of the center.
     *
     * @param x     is the x  coordinate.
     * @param y     is the y  coordinate.
     * @param r     is the radius.
     * @param color is the color.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }


    /**
     * @return the x center coordinate.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the y center coordinate.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return the size of the radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the size of the radius.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEn;
    }
    public Line getTrajectory() {
        Line trajectory = new Line(this.getX(), this.getY(),(this.getX() + this.velocity.getDx()*2),
                (this.getY() + this.velocity.getDy()*2));
        return trajectory;
    }
    /**
     * @return the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @param v is the velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEn = ge;
    }

    /**
     * @param x is the x coordinate velocity.
     * @param y is the y coordinate velocity.
     */
    public void setVelocity(double x, double y) {
        this.velocity = new Velocity(x, y);
    }

    /**
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moving the ball one step.
     */
    public void moveOneStep() {
         //Getting the x and y coordinates of the center.
        double ballX = this.center.getX();
        double ballY = this.center.getY();
        CollisionInfo collisionInfo = this.gameEn.getClosestCollision(this.getTrajectory());
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
           // this.setVelocity(this.velocity);
        } else {

            Velocity newV = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), this.velocity);
            this.setVelocity(newV);
            Point almostHit = new Point(ballX-1,ballY-1);
            this.center = this.velocity.applyToPoint(almostHit);

        }
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface is the surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color); // Setting the color.
        // Casting from double to int.
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        surface.fillCircle(x, y, this.getSize());
       surface.drawLine((int)this.getTrajectory().start().getX(),(int)this.getTrajectory().start().getY(),
               (int)this.getTrajectory().end().getX(),(int)this.getTrajectory().end().getY());
    }

    /**
     *
     */
    public void timePassed() {
        this.moveOneStep();
    }
}
