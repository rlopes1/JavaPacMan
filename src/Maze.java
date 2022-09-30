import java.awt.*;

public class Maze {
    private Color levelColor;

    /*
     * 2D arrays containing data for default levels created by me.
     * 0 signifies a pip, 1 signifies a wall, 2 signifies a powerup pip.
     * The ghosts and pacman are drawn at a chosen index in the array in our drawMaze method.
     */
    private int[][] level0Data = new int[][] {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1 },
            { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    };
    private int[][] levelData = level0Data;
    private int[][] level1Data;
    private int[][] level2Data;

    private int tileSize;  //specifies how many pixels each tile will take up


    // Constructor for the maze class
    public Maze(int tileSize, int level, Color color) {
        this.tileSize = tileSize;
        setLevel(level);
        levelColor = color;
    }


    //level selector that can be called in driver
    private void setLevel(int level) {
        if (level == 0) {
            levelData = level0Data;
        } else if (level == 1) {
            levelData = level1Data;
        } else if (level == 2) {
            levelData = level2Data;
        } else {
            levelData = level0Data;
        }
    }
    public int[][] getLevel() {
        return levelData;
    }


    /*
     * drawMaze method imports a g2d object in the driver's paint method.
     * Double for loop that runs through each index of our levelData 2D array,
     * and draws the corresponding item at that index. Will update as the player interacts 
     * with level because paint is looped in the driver game loop.
     */
    public void drawMaze(Graphics2D g2d) {
        for (int row = 0; row < levelData.length; row++) {
            for (int col = 0; col < levelData[row].length; col++) {
                if ((levelData[row][col] & 1) == 1) {
                    g2d.setColor(levelColor);
                    g2d.drawRect(col * tileSize, row * tileSize, tileSize, tileSize);
                } else if ((levelData[row][col] & 2) == 2) {
                    g2d.setColor(Color.white);
                    g2d.fillOval((col * tileSize) + (tileSize / 2), (row * tileSize) + (tileSize / 2), 15, 15);
                } else if ((levelData[row][col] & 4) == 4) {
                } else if ((levelData[row][col] & 8) == 8) {
                } else if ((levelData[row][col] & 16) == 16) {
                } else if ((levelData[row][col] & 1) == 0) {
                    g2d.setColor(Color.white);
                    g2d.fillOval((col * tileSize) + (tileSize / 2), (row * tileSize) + (tileSize / 2), 5, 5);
                }

            }
        }

    }



    //return the value in array of specified index
    public int getValue(int row, int col) {
        return levelData[row][col];   
    }
    //getters for adjacent tiles of currTile array ([0] row, [1] row)
    public int getTileUp(int[] currTile) {
        return levelData[currTile[1]+1][currTile[0]];
    }
    public int getTileDown(int[] currTile) {
        return levelData[currTile[1]-1][currTile[0]];
    }
    public int getTileRight(int[] currTile) {
        return levelData[currTile[1]][currTile[0]+1];
    }
    public int getTileLeft(int[] currTile) {
        return levelData[currTile[1]][currTile[0]-1];
    }


    //sets specified index to 16, a value that will not be drawn (i.e. erased)
    public void eraseIndex(int row, int col) {
        levelData[row][col] = 16;
    }

    
    //tracks whether there are any pips left on level, returns true if there are none
    public boolean levelComplete() {
        for (int row = 0; row < levelData.length; row++) {
            for (int col = 0; col < levelData[row].length; col++) {
                if (levelData[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
