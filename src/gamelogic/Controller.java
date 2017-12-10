package gamelogic;

public class Controller {

    /**
     * Starts the game.
     * 
     * @param args
     *            unused
     */
    public static void main(final String[] args) {
        map = new Map("Map_Data.txt");
        player = map.getPlayer();
        input = new MyKeyListener();
        /*
         * while (player.isAlive()) { // get player's next move int nextMove = -1; while
         * (!map.checkMove(nextMove)) { nextMove = getMove(); } }
         */
    }

    private static Map map;
    private static Player player;
    private static MyKeyListener input;

    /**
     * Tries to move the player in the specified direction. If successful, the
     * player moves, followed by the enemies' turn.
     * 
     * @param direction
     *            the direction to attempt to move
     * @throws InterruptedException if Thread.Sleep is bad
     */
    public static void movePlayer(int direction) throws InterruptedException {
        if (map.checkMove(direction)) {
            // does some stuff
            map.printToConsole();
            Thread.sleep(1000);
            moveEnemies();
        }
        // don't do anything if the move was invalid
    }

    private static void moveEnemies() {
        for (int i = 0; i < map.getEnemies().size(); i += 1) {
            Slime temp = ((Slime) map.getEnemies().get(i));
            temp.attack(temp.x, temp.y, temp.attackRange);
        }
        for (int i = 0; i < map.getEnemies().size(); i += 1) {
            Slime temp = ((Slime) map.getEnemies().get(i));
            temp.move();
        }
        map.printToConsole();
    }

    /*
     * private static int getMove() { return -1; }
     */
}
