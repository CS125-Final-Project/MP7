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
    
    /**
     * this function reads a text file and generates a level based on a text file.
     * @param template The text file name REMEMBER TO ADD ".txt" TO THE END OF THE FILE NAME.
     */
    public void templateReader(final String template) {
        String templateText;
        try {
            String templatePath = Map.class.getClassLoader()
                    .getResource(template).getFile();
            
            templatePath = new URI(templatePath).getPath();
            File templateFile = new File(templatePath);
            Scanner templateScanner = new Scanner(templateFile, "UTF-8");
            templateText = templateScanner.useDelimiter("\\A").next();
            templateScanner.close();
        } catch (Exception e) {
            throw new InvalidParameterException("Bad file path" + e);
        }
        
        
        char slimeAscii = Slime.ascii;
        char playerAscii = Player.ascii;
        char wallAscii = Wall.ascii;
        
        String[] rowChars = templateText.split("\n");
        for (int i = 0; i < this.gameMap.length; i += 1) {
            for (int j = 0; j < this.gameMap[i].length; j += 1) {

                char tempChar = rowChars[i].charAt(j);

                if (tempChar == ' ') {
                    gameMap[i][j] = null;

                } else if (tempChar == wallAscii) {
                    gameMap[i][j] = new Wall();

                } else if (tempChar == slimeAscii) {
                    gameMap[i][j] = new Slime();
                    listObj.add(gameMap[i][j]);
                } else if (tempChar == playerAscii) {
                    player = new Player(i,j);
                    gameMap[i][j] = player;
                    listObj.add(gameMap[i][j]);
                }
            }
        }
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
     * @param width This is the width of this map.
     * @param height This is the height of this map.
     * @param mapTemplate this is a notepad document filled with ascii characters for 
     */
    
    public Map(final int width, final int height, final String mapTemplate) {
        this.gameMap = new GameObject[width][height];
        this.heatMap = new int[width][height];
        this.templateReader(mapTemplate);
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }


}
