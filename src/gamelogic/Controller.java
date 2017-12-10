package gamelogic;

public class Controller {

    /**
     * Runs the game.
     * @param args unused
     */
    public static void main(final String[] args) {
        Map map = new Map("Map_Data.txt");
        Player player = map.getPlayer();
        while (player.isAlive()) {
            // get player's next move
            int nextMove = -1;
            while (!map.checkMove(nextMove)) {
                nextMove = getMove();
            }
            
        }
    }
    
    private static int getMove() {
        return -1;
    }
}
