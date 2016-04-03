import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * A Ball class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class SpriteCollection {

    private List spriteCollection;

    public SpriteCollection() {
        this.spriteCollection = new ArrayList();
    }

    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }

    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteCollection.size(); i++) {
            Sprite s = (Sprite) this.spriteCollection.get(i);
            s.timePassed();
        }
    }

    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteCollection.size(); i++) {
            Sprite s = (Sprite) spriteCollection.get(i);
            s.drawOn(d);
        }
    }
}