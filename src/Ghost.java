import java.awt.*;

public class Ghost {
    private int x;
    private int y;
    private int xVel = 1;
    private int yVel = 1;
    private int[] startPos = new int[2];
    private int ghostSize;
    Game game;


    //constructor for Ghost, including current game, starting pos, and draw size
    public Ghost(Game game, int startCol, int startRow, int ghostSize) {
        this.game = game;
        x = startCol * game.getTileSize() + (game.getTileSize()/4);
        y = startRow * game.getTileSize() + (game.getTileSize()/4);;
        startPos[0] = x;
        startPos[1] = y;
        this.ghostSize = ghostSize;
    }


    //Setters&Getters for Ghost's position
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    
    //reset position method
    public void resetPos() {
        x = startPos[0];
        y = startPos[1];
    }



    /*public int[] getPlayerGrid() {
        int[] playerGrid = new int[2];
        playerGrid[0] = (game.pacman.getX()+15) / game.getTileSize();
        playerGrid[1] = (game.pacman.getY()+15) / game.getTileSize();
        return playerGrid;
    }

    public void pathfind() {
        int playerXTile = getPlayerGrid()[0];
        int playerYTile = getPlayerGrid()[1];
        Maze layout = game.getCurrMaze();
        if ((playerXTile < layout.getCurrTile(x + 48, y)[0]) && (layout.getLeft(x - 22, y) != 1)) {
            x -= xVel;
            System.out.println("moving left");
        }  else if ((playerXTile > layout.getCurrTile(x + 48, y)[0]) && (layout.getRight(x + 48, y) != 1)) {
            x += xVel;
            System.out.println("moving right");
        } else if ((playerYTile > layout.getCurrTile(x, y - 48)[1]) && (layout.getDown(x, y) != 1)) {
            y += yVel;
            System.out.println("moving down");
        } else if ((playerYTile < layout.getCurrTile(x, y)[1]) && (layout.getUp(x, y) != 1)) {
            y -= yVel;
            System.out.println("moving up");
        } else {
            System.out.println("stucc");
        }
    }*/



    // Ghost collision bounds for hit detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, ghostSize, ghostSize);
    }


    // paint method for Ghost, updates in driver's paint method
    public void paint(Graphics2D g) {
        g.setColor(Color.red);
        g.fillArc(x, y, ghostSize-(ghostSize/4), ghostSize, -30, 235);
        g.setColor(Color.white);
        g.fillOval(x + 10, y + 15, 10, 10);
        g.fillOval(x + 30, y + 15, 10, 10);
        g.setColor(Color.blue);
        g.fillOval(x + 12, y + 17, 8, 8);
        g.fillOval(x + 32, y + 17, 8, 8);
    }

}
