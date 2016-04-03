import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * A MultipleBouncingBallsAnimation class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Converting string array to integer array.
     *
     * @param stringsArr is string array.
     * @return integer array converting from srtings.
     */
    public static int[] stringsToInts(String[] stringsArr) {
        int[] convertingArray = new int[stringsArr.length];
        // Converting string array to integer array.
        for (int i = 0; i < stringsArr.length; i++) {
            convertingArray[i] = Integer.parseInt(stringsArr[i]);
        }
        return convertingArray;
    }

    /**
     * @param sizes   is an integer array represent the size of the balls.
     * @param cornerX is the start x coordinate of the frame that surrond the balls.
     * @param cornerY is the start y coordinate of the frame that surrond the balls.
     * @param limitX  is the limit x coordinate of the frame that surrond the balls.
     * @param limitY  is the limit y coordinate of the frame that surrond the balls.
     * @return a ball array.
     */
    public static Ball[] createBalls(int[] sizes,
                                     int cornerX, int cornerY,
                                     int limitX, int limitY) {
        Ball[] ballsArray = new Ball[sizes.length];
        Random rand = new Random();

        // Creating tha balls.
        for (int i = 0; i < ballsArray.length; i++) {
            int ballSize = sizes[i];
            // Randomize position in accordance to the size of the screen.
            int xPosition = rand.nextInt(limitX - cornerX - 2 * ballSize) + cornerX + ballSize;
            int yPosition = rand.nextInt(limitY - cornerY - 2 * ballSize) + cornerY + ballSize;
            Point randP = new Point(xPosition, yPosition);
            double speed = 50 / ballSize; // The ball is slower as bigger as the ball.
            if (ballSize > 50) { // Balls that are bigger than 50 are in the same speed.
                speed = 1;
            }

            // Setting the ball according to random position and macthing speed.
            Ball ball = new Ball(randP, ballSize, Color.black);
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), speed);
            ball.setVelocity(v);
            ballsArray[i] = ball;
        }
        return ballsArray;

    }

    /**
     * Drawing the balls animation.
     *
     * @param size     is the length of the args
     * @param ballsArr is a ball array.
     */
    public static void drawingBallsAnimation(int size, Ball[] ballsArr) {
        // Create a window with the title "Drawing Balls Animation"
        // which is 620 pixels wide and 620 pixels high.
        GUI gui = new GUI("Drawing Balls Animation", 620, 620);
        biuoop.Sleeper sleeper = new biuoop.Sleeper(); // Create a sleeper generator.
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int j = 0; j < size; ++j) {
                Ball ball = ballsArr[j];
                ball.drawOn(d);
                ball.moveOneStep();
            }
            gui.show(d);
            sleeper.sleepFor(50); // Wait for 50 milliseconds.
        }
    }

    /**
     * MultipleBouncingBallsAnimation main method.
     * Receives the balls as the first commandline argument,
     * and represent them on the screen.
     *
     * @param args the first item in args is an integer representing the
     *             number of balls. The other items are ignored.
     *             <p/>
     *             Example usage:
     *             java -cp biuoop-1.4.jar:. MultipleBouncingBallsAnimation 12 2 3 4 2 9
     */
    public static void main(String[] args) {
        // Checking if there is input.
        if (args.length > 0) {
            //converting strin input to int.
            int[] ballSizes = stringsToInts(args);
            Ball[] ballsArr = createBalls(ballSizes, 0, 0, 620, 620);
            drawingBallsAnimation(args.length, ballsArr);
        }
    }
}





