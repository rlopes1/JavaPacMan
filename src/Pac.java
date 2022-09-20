import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Pac extends JPanel {
    private int x = 60;
    private int y = 60;
    private int xVel = 0;
    private int yVel = 0;

    private Game game;
    private Ghost red;


    public Pac(Game game) {
        this.game = game;
    }


    //Need to tweak input to only be 1-directional
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xVel = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xVel = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            yVel = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            yVel = 1;
        }

    }
    public void keyReleased(KeyEvent e) {
        xVel = 0;
        yVel = 0;
    }

    //Move method
    public void move() {
        if (Life.getLives() == 0) {
            game.gameOver();
        } else if(collideGhost()) {
            game.reset(0);
            Life.loseLife();
        } else {
            x += xVel;
            y += yVel;
        }
    }

    //Position methods
    public Vector getPos() {
        return new Vector(x, y);
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    //Collision methods
    //NEED TO ADD WALL COLLISION
    public boolean collideGhost() {
        return game.red.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }

    //Paint of the pacman object
    //NEED TO ADD DIRECTIONAL PAINTING
    public void paint(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillArc(x, y, 50, 50, 45, 270);
    }
}
