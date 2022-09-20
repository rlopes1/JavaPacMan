import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

    private Pac pacman = new Pac(this);
    private Wall wall = new Wall();
    public Ghost red = new Ghost();
    private Life lives = new Life();
    public Score score = new Score();

    private int width = 1580;
    private int height = 1020;


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


    public void reset(int level) {
        pacman.setX(50);
        pacman.setY(50);
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
        wall.createBorder();
        wall.paint(g2d);
        red.paint(g2d);
        lives.paint(g2d);
        score.drawScore(g2d);
    }
}
