import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pac extends JPanel {
    private int x;
    private int y;
    private int xVel = 0;
    private int yVel = 0;

    private int[] startPos = new int[2];
    private int[] pacNode = new int[2];
    private int pacSize;
    private Game game;
    private int buffer; // used for collision calculations
    private double animFrame = 0;
    private boolean counter = false;

    // Constructor for Pac including current game, start pos, and size of drawing
    public Pac(Game game, int startCol, int startRow, int pacSize) {
        this.game = game;
        x = startCol * game.getTileSize() + (game.getTileSize() / 6);
        ;
        y = startRow * game.getTileSize() + (game.getTileSize() / 6);
        ;
        this.pacSize = pacSize;
        startPos[0] = x;
        startPos[1] = y;
        buffer = game.getTileSize() / 5;
    }

    public Pac(Game game) {
        this.game = game;
    }

    public void resetPos() {
        x = startPos[0];
        y = startPos[1];
    }

    // Move method
    public void move() {
        System.out.println(counter);
        // Game state checking before any movement is run
        if (Life.getLives() == 0) {
            game.gameOver();
        } else if (game.ghostCollision()) {
            game.reset();
            Life.loseLife();
        }
        if (game.getCurrMaze().levelComplete()) {
            game.youWin();
        }

        pacNode = game.getCurrTile(x, y);
        // System.out.println("row:" + pacNode[0] + ", col:" + pacNode[1]);
        if (yVel == 1) {
            pacNode = game.getCurrTile(x, y + 4 * buffer);

            if (game.getCurrMaze().getValue(pacNode[0], pacNode[1]) != 1) {
                y += yVel;
                animFrame += 0.07;
            } else {
                yVel = 0;
            }
        } else if (yVel == -1) {
            pacNode = game.getCurrTile(x, y - buffer);

            if (game.getCurrMaze().getValue(pacNode[0], pacNode[1]) != 1) {
                y += yVel;
                animFrame += 0.07;
            } else {
                yVel = 0;
            }
        } else if (xVel == 1) {
            pacNode = game.getCurrTile(x + buffer, y);

            if (game.getCurrMaze().getValue(pacNode[0], pacNode[1]) != 1) {
                x += xVel;
                animFrame += 0.07;
            } else {
                xVel = 0;
            }
        } else if (xVel == -1) {
            pacNode = game.getCurrTile(x - buffer, y);

            if (game.getCurrMaze().getValue(pacNode[0], pacNode[1]) == 1) {
                xVel = 0;
            } else {
                x += xVel;
                animFrame += 0.07;
            }
        }
    }

    // Setters and getters for pacmans position
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

    public int[] getPacNode() {
        return pacNode;
    }

    // keyPressed input method, can only move in one dir. at a time
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

    // keyReleased removes all speed once keys let go
    public void keyReleased(KeyEvent e) {
        xVel = 0;
        yVel = 0;
        animFrame = 0;
    }

    // Rectangle return for ghost hit detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, pacSize, pacSize);
    }

    // Paint of the pacman object
    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.yellow);

        if (xVel == 1) {
            drawAnimRight(g2d);
        } else if (xVel == -1) {
            drawAnimLeft(g2d);
        } else if (yVel == 1) {
            drawAnimDown(g2d);
        } else if (yVel == -1) {
            drawAnimUp(g2d);
        } else {
            drawAnimRight(g2d);
        }

        if ((int) animFrame == 3) {
            animFrame = 0;
        }
    }

    private void drawAnimRight(Graphics2D g2d) {
        switch ((int) animFrame) {
            case 0:
                g2d.fillArc(x, y, pacSize, pacSize, 45, 270); // facing right
                break;
            case 1:
                g2d.fillArc(x, y, pacSize, pacSize, 20, 320);
                break;
            case 2:
                g2d.fillArc(x, y, pacSize, pacSize, 20, 360);
                break;
            case 3:
                g2d.fillArc(x, y, pacSize, pacSize, 20, 320);
                break;

        }
    }

    private void drawAnimDown(Graphics2D g2d) {
        switch ((int) animFrame) {
            case 0:
                g2d.fillArc(x, y, pacSize, pacSize, 315, 270); // facing down
                break;
            case 1:
                g2d.fillArc(x, y, pacSize, pacSize, 290, 320);
                break;
            case 2:
                g2d.fillArc(x, y, pacSize, pacSize, 290, 360);
                break;
            case 3:
                g2d.fillArc(x, y, pacSize, pacSize, 290, 320);
                break;
        }
    }

    private void drawAnimLeft(Graphics2D g2d) {
        switch ((int) animFrame) {
            case 0:
                g2d.fillArc(x, y, pacSize, pacSize, 225, 270); // facing left
                break;
            case 1:
                g2d.fillArc(x, y, pacSize, pacSize, 200, 320);
                break;
            case 2:
                g2d.fillArc(x, y, pacSize, pacSize, 200, 360);
                break;
            case 3:
                g2d.fillArc(x, y, pacSize, pacSize, 200, 320);
                break;
        }
    }

    private void drawAnimUp(Graphics2D g2d) {
        switch ((int) animFrame) {
            case 0:
                g2d.fillArc(x, y, pacSize, pacSize, 135, 270); // facing up
                break;
            case 1:
                g2d.fillArc(x, y, pacSize, pacSize, 115, 320);
                break;
            case 2:
                g2d.fillArc(x, y, pacSize, pacSize, 115, 360);
                break;
            case 3:
                g2d.fillArc(x, y, pacSize, pacSize, 115, 320);
                break;
        }
    }
}