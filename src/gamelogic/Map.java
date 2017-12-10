package gamelogic;

import java.io.File;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private int mapHeight;
    private int mapWidth;
    int[][] heatMap;
    GameObject[][] gameMap;
    private ArrayList<Mob> enemies = new ArrayList<Mob>();
    private Player player;
    
    public ArrayList<Mob> getEnemies() {
        return enemies;
    }
    
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
     * Attempts to move the object at oldX, oldY to newX, newY.
     * Fails if you try to move into a wall or are nothing.
     * @param oldX old x
     * @param oldY old y
     * @param newX new x
     * @param newY new y
     * @return true on success, false on failure.
     */
    public boolean moveObject(final int oldX, final int oldY, final int newX, final int newY) {
        if (gameMap[newX][newY] instanceof Wall || gameMap[oldX][oldY] == null) {
            return false;
        }
        gameMap[newX][newY] = gameMap[oldX][oldY];
        gameMap[oldX][oldY] = null;
        return true;
    }
    
    /**
     * This function will move the player and kill things where the player moves. 
     *      Can later be changed to attack stuff.
     * @param move This is the direction which will be passed in by the controller. 
     *      Obtained from the key listener.
     * @return This confirms that the move has occurred.
     */
    
    public boolean movePlayer(int move) {
        int newX = Util.newX(player.getX(), move);
        int newY = Util.newY(player.getY(), move);
        gameMap[player.getX()][player.getY()] = null;
        if (gameMap[newX][newY] instanceof Mob) {
            for (int i = 0; i < enemies.size(); i += 1) {
                if (enemies.get(i).x == gameMap[newX][newY].x
                        && enemies.get(i).y == gameMap[newX][newY].y) {
                    System.out.println("You killed a " + enemies.get(i).getName());
                    enemies.remove(i);
                }
            }
        } else {
            gameMap[newX][newY] = player;
            player.x = newX;
            player.y = newY;
        }
        return true;
    }

    /** Code for an inaccessible location on the heat map. */
    public static final int INACCESSIBLE = 999;
    
    /**
     * Generates a "heat map", where the value is the distance from the player.
     */
    public void genHeatMap() {
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                heatMap[x][y] = INACCESSIBLE;
            }
        }
        generateHeatMap(player.getX(), player.getY(), 0);
    }
    
    private void generateHeatMap(final int x, final int y, final int distance) {
        if (gameMap[x][y] instanceof Wall) {
            return;
        } 
        heatMap[x][y] = distance;
        if (x + 1 < mapWidth && heatMap[x + 1][y] > distance + 1) {
            generateHeatMap(x + 1, y, distance + 1);
        }
        if (x - 1 >= 0 && heatMap[x - 1][y] > distance + 1) {
            generateHeatMap(x - 1, y, distance + 1);
        }
        if (y + 1 < mapHeight && heatMap[x][y + 1] > distance + 1) {
            generateHeatMap(x, y + 1, distance + 1);
        }
        if (y - 1 >= 0 && heatMap[x][y - 1] > distance + 1) {
            generateHeatMap(x, y - 1, distance + 1);
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
        gameMap = new GameObject[mapWidth][mapHeight];
        heatMap = new int[mapWidth][mapHeight];
        for (int x = 0; x < gameMap.length; x += 1) {
            for (int y = 0; y < gameMap[x].length; y += 1) {

                char tempChar = rowChars[y].charAt(x);

                if (tempChar == ' ') {
                    gameMap[x][y] = null;

                } else if (tempChar == Wall.ASCII) {
                    gameMap[x][y] = new Wall();

                } else if (tempChar == Slime.ASCII) {
                    gameMap[x][y] = new Slime(x, y, this);
                    enemies.add((Slime)gameMap[x][y]);
                } else if (tempChar == Player.ASCII) {
                    if (player != null) {
                        throw new IllegalArgumentException("Level data has two or more players!");
                    }
                    player = new Player(x, y);
                    gameMap[x][y] = player;
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
    
    /** Prints the map to the console. */
    public void printToConsole() {
        System.out.println();
        for (int y = 0; y < mapHeight; y ++) {
            for (int x = 0; x < mapWidth; x++) {
                if (gameMap[x][y] == null) {
                    System.out.print("  ");
                } else {
                    gameMap[x][y].printAscii();
                }
            }
            System.out.println();
        }
    }

}
