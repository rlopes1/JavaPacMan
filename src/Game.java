import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
    private int tileSize = 100;
    private static int width;
    private static int height;

    public Maze level = new Maze(tileSize, 0, Color.blue);
    public Score score = new Score(this);
    public Life lives = new Life(this, 3);
    public Pac pacman = new Pac(this, 1, 5, tileSize-30);
    public Ghost red = new Ghost(this, 5, 1, tileSize-30);


    //Constructor for our Game
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

        width = (tileSize * level.getLevel()[0].length) + tileSize*3;
        height = (tileSize * level.getLevel().length) + tileSize/3;
    }


    //Getters for all relevant game info
    public int getWindowWidth() {
        return width;
    }
    public int getWindowHeight() {
        return height;
    }
    public int getTileSize() {
        return tileSize;
    }
    public Maze getCurrMaze() {
        return level;
    }
    public Score getScore() {
        return score;
    }
    //return array with row[0] and column[1] of specified position
    //tileSize/3 used to approx. center since items are drawn from top left
    public int[] getCurrTile(int x, int y) {
        return new int[] {y/tileSize, x/tileSize};  
    }
    

    //Methods relating to game state
    public void reset() {
        pacman.resetPos();
        red.resetPos();
    }
    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over...", "Game Over",JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
    public void youWin() {
        JOptionPane.showMessageDialog(this, "You win!!!", "Complete",JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }


    //Move method that calls individual object move methods, and loops in game loop
    public void move() {
        pacman.move();
        //red.pathfind();
        if(level.getValue(pacman.getPacNode()[0], pacman.getPacNode()[1]) == 0) {
            level.eraseIndex(pacman.getY()/tileSize, pacman.getX()/tileSize);
            score.pipEaten();
        }
    }


    //Driver method, while loop is our game loop
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Pacman");
        Game game = new Game();
        frame.add(game);
        frame.setSize(width, height);
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
        Graphics2D g2d = (Graphics2D) g;         //Importing Graphics and creating a Graphics2D object of it
        setBackground(Color.black);        //Drawing the black background in the canvas
        super.paint(g); //Calling paint in the parent class
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        //Turning antialiasing on for smoother shape edges

        //painting our objects
        level.drawMaze(g2d);
        red.paint(g2d);
        this.lives.paint(g2d);
        score.drawScore(g2d);
        pacman.paint(g2d);
    }

    public boolean ghostCollision() {
        Rectangle ghostBox = red.getBounds();
        if(ghostBox.intersects(pacman.getBounds())) {
            return true;
        }
        return false;
    }
}
