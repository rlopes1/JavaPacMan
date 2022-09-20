import java.awt.*;

public class Maze {
    private int level = 0;
    private Color levelColor;

    /*
    Below is the level data for level 0. 0 signifies a wall, 1 signifies a pip (point), 2 signifies a
    powerup, 4 signifies the top left corner, 8 signifies the top right corner, 16 signifies the bottom left
    corner, 32 signifies the bottom right corner, 64 the player start pos, and 128 the ghost start pos. We
    can add up these values to draw our maze (ex. 18 in the array implies there is a powerup in the bottom left corner).
    */

    private int[] level0Data = new int[] {
            0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,6,1,1,1,1,1,1,1,1,1,10,0,
            0,0,0,0,1,0,0,0,0,1,0,0,0,
            0,17,1,1,1,1,2,1,1,1,1,33,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,
        };

    private int TILE_SIZE;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;

    //Constructor for the maze class
    public Maze(int tileSize, int level, int screenWidth, int screenHeight, Color color) {
        TILE_SIZE = tileSize;
        this.level = level;
        SCREEN_WIDTH = screenWidth;
        SCREEN_HEIGHT = screenHeight;
        levelColor = color;
    }

    public void drawMaze(Graphics2D g2d) {

        for(int i = 0; i < SCREEN_WIDTH; i += TILE_SIZE) {
            for(int j = 0; j < SCREEN_HEIGHT; i += TILE_SIZE) {
                int k = 0;
                //Draws pip if there is a 1 bit in levelData
                if((level0Data[k] & 1) != 0) {
                    g2d.setColor(Color.white);
                    g2d.fillOval(i, j, 5, 5);
                }
                //Draw superPip if there is a 2 bit in levelData
                if((level0Data[k] & 2) != 0) {
                    g2d.setColor(Color.white);
                    g2d.fillOval(i, j, 10, 10);
                }
                //Draws
                if(level0Data[k] == 0) {
                    g2d.setColor(levelColor);
                    //g2d.setStroke(new BasicStroke(2));
                    //g2d.drawRoundRect(i, j, TILE_SIZE, TILE_SIZE);
                }
            }
        }

    }




}
