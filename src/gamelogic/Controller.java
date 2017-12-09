package gamelogic;

public class Controller {

    public static void main(final String[] args) {
        Map map = new Map(1,1,"P");
        Player player = map.getPlayer();
        while (player.isAlive()) {
            // get player's next move
            int nextMove = -1;
            while (!map.checkMove(nextMove)) {
                nextMove = getMove();
            }
            
        }
    }
    
    // Codes for directions
    public static int NORTH = 0;
    public static int EAST = 1;
    public static int SOUTH = 2;
    public static int WEST = 3;
    
    private static int getMove() {
        return -1;
    }
}
