import java.awt.*;

public class Life {
    private int lives;

    public Life(int numLives) {
        lives = numLives;
    }


    public void loseLife() {
        lives -= 1;
    }
    public int getLives() {
        return lives;
    }


    public void paint(Graphics2D g) {
        g.setColor(Color.yellow);
        for(int i = 0; i < lives; i++) {
            g.fillArc(1310, 30 + (i*80), 35, 35, 45, 270);
        }
    }
}
