import java.awt.*;

public class Life {
    private static int lives = 3;
    Game game;
    public Life(Game game, int numLives) {
        lives = numLives;
        this.game = game;
    }


    //static methods for interaction with lives
    public static void loseLife() {
        lives -= 1;
    }
    public static int getLives() {
        return lives;
    }


    //draws pacman life icons on sidebar, updated in driver's paint method
    public void paint(Graphics2D g) {
        g.setColor(Color.yellow);
        for(int i = 0; i < lives; i++) {
            g.fillArc(game.getWindowWidth() - (7*game.getTileSize()/4), 30 + (i*80), 35, 35, 45, 270);
        }
    }
}
