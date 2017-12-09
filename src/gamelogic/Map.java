package gamelogic;

import java.util.ArrayList;

import com.sun.corba.se.impl.copyobject.ReferenceObjectCopierImpl;

public class Map {
    private int mapHeight;
    private int mapWidth;
    public int[][] heatMap;
    public GameObject[][] gameMap;
    ArrayList<GameObject> listObj = new ArrayList<GameObject>();

    public void templateReader(final String template) {
        char slimeAscii = Slime.ascii;
        char playerAscii = Player.ascii;
        char wallAscii = Wall.ascii;
        
        String[] rowChars = template.split("\n");
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
                    gameMap[i][j] = new Player(i,j);
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
     * @param playerY the y coordintae of the player
     */
    public void genHeatMap(final int playerX, final int playerY) {
        for (int x = 0; x < mapWidth; x++) {
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
            
        if (x + 1 < mapWidth && heatMap[x + 1][y] > distance + 1) {
            this.generateHeatMap(x + 1, y, distance + 1);
        }
        if (x - 1 > 0 && heatMap[x - 1][y] > distance + 1) {
            this.generateHeatMap(x - 1, y, distance + 1);
        }
        if (y + 1 < mapWidth && heatMap[x][y + 1] > distance + 1) {
            this.generateHeatMap(x, y + 1, distance + 1);
        }
        if (y - 1 > 0 && heatMap[x][y - 1] > distance + 1) {
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
}