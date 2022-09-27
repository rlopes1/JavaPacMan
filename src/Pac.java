import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pac extends JPanel {
    private int x = 60;
    private int y = 60;
    private int xVel = 0;
    private int yVel = 0;
    private int pacSize = 40;
    private Life lives = new Life(3);

    private Game game;
    private Ghost red;
    private Maze level = new Maze(60, 0, Color.blue);


    public Pac(Game game, int startX, int startY){
        this.game = game;
        x = startX;
        y = startY;
    }
    public Pac(Game game) {
        this.game = game;
    }


    //Need to tweak input to only be 1-directional
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xVel = 1;
            yVel = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xVel = -1;
            yVel = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            yVel = -1;
            xVel = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            yVel = 1;
            xVel = 0;
        }

    }
    public void keyReleased(KeyEvent e) {
        xVel = 0;
        yVel = 0;
    }

    public Life getLives() {
        return lives;
    }
    public Rectangle getMoveBound() {
        return new Rectangle(x + xVel, y + yVel, pacSize, pacSize);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, pacSize, pacSize);
    }
    //Move method
    public void move() {
        boolean wall = game.wallCollision();
        if (lives.getLives() == 0) {
            game.gameOver();
        } else if(game.ghostCollision()) {

            game.reset();
            lives.loseLife();
        } else if(wall) {
            xVel = 0;
            yVel = 0;
        } else {
            x += xVel;
            y += yVel;
        }
        game.pipPickup();
    }

    //Position methods
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setXVel(int xVel) {
        this.xVel = xVel;
    }
    public void setYVel(int yVel) {
        this.yVel = yVel;
    }

    //Paint of the pacman object
    //NEED TO ADD DIRECTIONAL PAINTING
    public void paint(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillArc(x, y, pacSize, pacSize, 45, 270);
    }
}
