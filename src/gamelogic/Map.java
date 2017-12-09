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
        for (int i = 0; i < rowChars.length; i += 1) {
            for (int j = 0; j < rowChars[i].length(); j += 1) {

                char tempChar = rowChars[i].charAt(j);

                if (tempChar == ' ') {
                    gameMap[i][j] = null;

                } else if (tempChar == wallAscii) {
                    gameMap[i][j] = new Wall();

                } else if (tempChar == slimeAscii) {
                    gameMap[i][j] = new Slime();
                    listObj.add(gameMap[i][j]);
                } else if (tempChar == playerAscii) {
                    gameMap[i][j] = new Player();
                    listObj.add(gameMap[i][j]);

                }
            }
        }
    }

    public Map(final int width, final int height, final String mapTemplate) {
        gameMap = new GameObject[width][height];
    }
}
