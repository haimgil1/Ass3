import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * A Ball class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {

    }

    // Run the game -- start the animation loop.
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        GUI gui = new GUI("Game", 800, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}