package gamelogic;

import java.io.File;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;


public class Map {
    private int mapHeight;
    private int mapWidth;
    public int[][] heatMap;
    public GameObject[][] gameMap;
    ArrayList<GameObject> listObj = new ArrayList<GameObject>();
    private Player player;
    
    public Player getPlayer() {
        return player;
    }

    /**
     * Checks if a move is valid for this map.
     * 
     * @param move the move to check
     * @return true iff it is a valid move
     */
    public boolean checkMove(int move) {
        if (move < Util.NORTH || move > Util.WEST) {
            return false;
        }
        int newX = Util.newX(player.getX(), move);
        int newY = Util.newY(player.getY(), move);
        if (newX < 0 || newY < 0 || newX >= mapWidth || newY >= mapHeight
                || gameMap[newX][newY] instanceof Wall) {
            return false;
        }
        return true;
    }
    
    /** Code for an inaccessible location on the heat map. */
    public static final int INACCESSIBLE = 999;
    
    /**
     * Generates a "heat map", where the value is the distance from the player.
     * 
     * @param playerX the x coordinate of the player
     * @param playerY the y coordinate of the player
     */
    public void genHeatMap(final int playerX, final int playerY) {
        for (int x = 0; x < getMapWidth(); x++) {
            for (int y = 0; y < mapHeight; y++) {
                heatMap[x][y] = INACCESSIBLE;
            }
        }
        generateHeatMap(playerX, playerY, 0);
    }
    
    private void generateHeatMap(final int x, final int y, final int distance) {
        if (gameMap[x][y] instanceof Wall) {
            return;
        } 
            
        if (x + 1 < getMapWidth() && heatMap[x + 1][y] > distance + 1) {
            this.generateHeatMap(x + 1, y, distance + 1);
        }
        if (x - 1 >= 0 && heatMap[x - 1][y] > distance + 1) {
            this.generateHeatMap(x - 1, y, distance + 1);
        }
        if (y + 1 < getMapWidth() && heatMap[x][y + 1] > distance + 1) {
            this.generateHeatMap(x, y + 1, distance + 1);
        }
        if (y - 1 >= 0 && heatMap[x][y - 1] > distance + 1) {
            this.generateHeatMap(x, y - 1, distance + 1);
        }
    }
    
    /**
     * Generates a map based off of a template.
     * 
     * @param templateFilename the filename of the level data (a text file)
     */
    public Map(final String templateFilename) {
        String templateText;
        try {
            String templatePath = Map.class.getClassLoader()
                    .getResource(templateFilename).getFile();
            
            templatePath = new URI(templatePath).getPath();
            File templateFile = new File(templatePath);
            Scanner templateScanner = new Scanner(templateFile, "UTF-8");
            templateText = templateScanner.useDelimiter("\\A").next();
            templateScanner.close();
        } catch (Exception e) {
            throw new InvalidParameterException("Bad file path" + e);
        }
        
        String[] rowChars = templateText.split("\n");
        
        mapWidth = rowChars[0].length();
        mapHeight = rowChars.length;
        
        for (int y = 0; y < this.gameMap.length; y += 1) {
            for (int x = 0; x < this.gameMap[x].length; x += 1) {

                char tempChar = rowChars[y].charAt(x);

                if (tempChar == ' ') {
                    gameMap[x][y] = null;

                } else if (tempChar == Wall.ASCII) {
                    gameMap[x][y] = new Wall();

                } else if (tempChar == Slime.ASCII) {
                    gameMap[x][y] = new Slime(this);
                    listObj.add(gameMap[x][y]);
                } else if (tempChar == Player.ASCII) {
                    if (player != null) {
                        throw new IllegalArgumentException("Level data has two or more players!");
                    }
                    player = new Player(x, y);
                    gameMap[x][y] = player;
                    listObj.add(gameMap[x][y]);
                }
            }
        }
        if (player == null) {
            throw new IllegalArgumentException("Level data has no player!");
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }


}
