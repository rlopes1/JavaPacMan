import java.awt.*;
import java.util.Vector;

public class Maze {
    private Color levelColor;

    /*
    Below is the level data for level 0., 0 signifies a pip (point), 1 signifies a wall 2 signifies a
    powerup, 4 signifies the top left corner, 8 signifies the top right corner, 16 signifies the bottom left
    corner, 32 signifies the bottom right corner, 64 the player start pos, and 128 the ghost start pos. We
    can add up these values to draw our maze (ex. 18 in the array implies there is a powerup in the bottom left corner).
    */
    private int[] level0Data = new int[] {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 2, 8, 0, 0, 0, 0, 0, 0, 2, 1,
            1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1,
            1, 2, 0, 0, 0, 0, 0, 0, 0, 4, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    };
    private int[] level1Data = new int[10];
    private int[] level2Data = new int[10];

    private int[] levelData = level0Data;

    private int TILE_SIZE;
    private int numTilesWidth;

    private int[] playerPos = new int[2];
    private int[] ghostPos = new int[2];

    //Constructor for the maze class
    public Maze(int tileSize, int level, Color color) {
        TILE_SIZE = tileSize;
        setLevel(level);
        setNumTilesWidth(levelData);
        levelColor = color;
    }


    private void setLevel(int level) {
        if(level == 0) {
            levelData = level0Data;
        } else if(level == 1) {
            levelData = level1Data;
        } else if(level == 2) {
            levelData = level2Data;
        } else {
            levelData = level0Data;
        }
     }
    private void setNumTilesWidth(int[] levelData) {
        for(int i = 0; i < levelData.length; i++) {
            if(levelData[i] == 1 && levelData[i+1] != 1) {
                numTilesWidth = i;
                break;
            }
        }
    }

    public void drawMaze(Graphics2D g2d) {
        int counter = 0;
        int row = 0;
        for(int col = 0; col < numTilesWidth; col++ ) {

            if((levelData[counter] & 1) == 1) {
                g2d.setColor(levelColor);
                g2d.drawRect(col*TILE_SIZE, row*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            } else if((levelData[counter] & 2) == 2) {
                g2d.setColor(Color.white);
                g2d.fillOval((col*TILE_SIZE) + (TILE_SIZE/2), (row*TILE_SIZE) + (TILE_SIZE/2), 15, 15);
            }
            else if((levelData[counter] & 4) == 4){
                //ghostPos[0] = (col * TILE_SIZE) + (TILE_SIZE / 2);
                //ghostPos[1] = (row * TILE_SIZE) + (TILE_SIZE / 2);
            }
            else if((levelData[counter] & 8) == 8){
                //playerPos[0] = (col * TILE_SIZE) + (TILE_SIZE / 2);
                //playerPos[1] = (row * TILE_SIZE) + (TILE_SIZE / 2);
            }
            else if((levelData[counter] & 1) == 0) {
                g2d.setColor(Color.white);
                g2d.fillOval((col * TILE_SIZE) + (TILE_SIZE / 2), (row * TILE_SIZE) + (TILE_SIZE / 2), 5, 5);
            } else {
                g2d.setColor(Color.black);
                g2d.fillRect(col*TILE_SIZE, row*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            if(col == numTilesWidth-1) {
                col = -1;
                row++;
            }
            if(counter == levelData.length-1) {
                break;
            }
            counter++;
        }

    }

    public int[] getLevelData() {
        return levelData;
    }
    public int getIndex(int x, int y) {
        return (numTilesWidth * (y/TILE_SIZE)) + (x/TILE_SIZE);
    }

    public int[] getPlayerStart() {
        int counter = 0;
        int row = 0;
        for(int col = 0; col < numTilesWidth; col++ ) {
            if((levelData[counter] & 8) == 8) {
                playerPos[0] = (col * TILE_SIZE) + (TILE_SIZE / 2);
                playerPos[1] = (row * TILE_SIZE) + (TILE_SIZE / 2);
                break;
            }
            if(col == numTilesWidth-1) {
                col = -1;
                row++;
            }
            counter++;
        }
        return playerPos;
    }
    public int[] getGhostStart() {
        int counter = 0;
        int row = 0;
        for(int col = 0; col < numTilesWidth; col++ ) {
            if((levelData[counter] & 4) == 4) {
                ghostPos[0] = (col * TILE_SIZE) + (TILE_SIZE / 2);
                ghostPos[1] = (row * TILE_SIZE) + (TILE_SIZE / 2);
                break;
            }
            if(col == numTilesWidth-1) {
                col = -1;
                row++;
            }
            counter++;
        }
        return ghostPos;
    }
    public void eraseIndex(int index) {
        levelData[index] = 16;
    }
    public Rectangle getBounds(int index) {
        System.out.println("Rect:" + new Rectangle((index % numTilesWidth) * TILE_SIZE,  (index / numTilesWidth) * TILE_SIZE, TILE_SIZE, TILE_SIZE));
        return new Rectangle((index % numTilesWidth) * TILE_SIZE,  (index / numTilesWidth) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}
