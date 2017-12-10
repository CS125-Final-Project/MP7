package gamelogic;

public class Controller {

    public static void main(final String[] args) {
        Map map = new Map(10,10,"P");
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
