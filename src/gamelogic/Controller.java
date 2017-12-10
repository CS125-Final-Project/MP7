package gamelogic;

public class Controller {

    /**
     * Starts the game.
     * @param args unused
     */
    public static void main(final String[] args) {
        map = new Map("Map_Data.txt");
        player = map.getPlayer();
        input = new MyKeyListener();
        /*
        while (player.isAlive()) {
            // get player's next move
            int nextMove = -1;
            while (!map.checkMove(nextMove)) {
                nextMove = getMove();
            } 
        }
        */
    }
    
    private static Map map;
    private static Player player;
    private static MyKeyListener input;
    
    public static void movePlayer(int direction) {
        // does some stuff
        if (map.checkMove(direction)) {
            moveEnemies();
        }
    }
    
    private static void moveEnemies() {
        
    }
    
    /*
    private static int getMove() {
        return -1;
    }
    */
}
