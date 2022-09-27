import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
    public Maze level = new Maze(60, 0, Color.blue);
    public Score score = new Score();

    private int width = 1580;
    private int height = 1020;

    public Pac pacman = new Pac(this, level.getPlayerStart()[0], level.getPlayerStart()[1]);
    public Ghost red = new Ghost(level.getGhostStart()[0], level.getGhostStart()[1]);

    public Game() {
        //Keylistener for inputs from the user
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                pacman.keyReleased(e);
            }
            @Override
            public void keyPressed(KeyEvent e) {
                pacman.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void move() {
        pacman.move();
        //red.moveToPlayer(pacman.getPos());
    }


    public void reset() {
        pacman.setX(level.getPlayerStart()[0]);
        pacman.setY(level.getPlayerStart()[1]);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over...", "Game Over",JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }


    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Pacman");
        Game game = new Game();
        frame.add(game);
        frame.setSize(game.getWidth(), game.getHeight()-260);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Game Loop, executes until program is exited
        while(true) {
            game.move();
            game.repaint();
            Thread.sleep(5);
        }
    }

    @Override
    public void paint(Graphics g) {
        //Calling paint in the parent class
        super.paint(g);
        //Importing Graphics and creating a Graphics2D object of it
        Graphics2D g2d = (Graphics2D) g;
        //Drawing the black background in the canvas
        setBackground(Color.black);
        //Turning antialiasing on for smoother shape edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //painting our objects
        pacman.paint(g2d);
        level.drawMaze(g2d);
        red.paint(g2d);
        pacman.getLives().paint(g2d);
        score.drawScore(g2d);
    }

    public boolean wallCollision() {
        Rectangle pacBox = pacman.getMoveBound();
        System.out.println("Pac: " + pacBox.toString());
        for (int i = 0; i < level.getLevelData().length; i++) {
            Rectangle wallBox = level.getBounds(i);
            System.out.println("wall" + wallBox.toString());
            if (level.getLevelData()[i] == 1) {
                return pacBox.intersects(wallBox);
            }
        }
        return false;
    }
    public void pipPickup() {
        int pacIndex = level.getIndex(pacman.getX(), pacman.getY());
        if(level.getLevelData()[pacIndex] == 1) {
            level.eraseIndex(pacIndex);
            score.pipEaten();
        }
    }
    public boolean ghostCollision() {
        Rectangle ghostBox = red.getBounds();
        if(ghostBox.intersects(pacman.getBounds())) {
            return true;
        }
        return false;
    }
}
