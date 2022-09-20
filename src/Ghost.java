import java.awt.*;
import java.util.Vector;
public class Ghost {

    private int x = 300;
    private int y = 300;
    private int xVel = -1;
    private int yVel = -1;

    //private Vector playerPos; = pacman.getPos();

    //moveToPlayer will need to track where the player is and path to it
    public void moveToPlayer(Vector playerPos) {
        //normalizes a vector going towards the player and sets that to the value of the velocity
        //xVel = (int) playerPos.elementAt(0)/Math.abs((int) playerPos.elementAt(0));
        //yVel = (int) playerPos.elementAt(1)/Math.abs((int) playerPos.elementAt(1));
        x += xVel;
        y += yVel;
    }

    //Ghost collision bounds
    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 50);
    }

    //paint method for Ghost
    public void paint(Graphics2D g) {
        g.setColor(Color.red);
        g.fillArc(x, y, 50, 70, -30, 235);
        g.setColor(Color.white);
        g.fillOval(x + 10, y + 15, 10, 10);
        g.fillOval(x + 30, y + 15, 10, 10);
        g.setColor(Color.blue);
        g.fillOval(x + 12, y + 17, 8, 8);
        g.fillOval(x + 32, y + 17, 8, 8);
    }

}
