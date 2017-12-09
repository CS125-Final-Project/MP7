package gamelogic;

/**
 * This is an enemy which does a thing.
 * 
 * @author Elayda
 *
 */
public class Mob extends GameObject {
    private Map world;
    /** Is it trying to kill you.? */
    private boolean isHostile;

    private int attackRange;

    // Codes for directions
    public static int NORTH = 0;
    public static int EAST = 1;
    public static int SOUTH = 2;
    public static int WEST = 3;

    /**
     * @return This will return an integer based on which step will lead to the
     *         player the fastest. 0 is a North, 1 is a East, 2 is a South, 3 is a
     *         West.
     */
    public int findPlayer(Player player) {
        // currently ignores walls
        int[] dirs = new int[4];
        /*
         * dirs[NORTH] = this.y - player.getY(); dirs[EAST] = player.getX() - this.x;
         * dirs[SOUTH] = player.getY() - this.x; dirs[WEST] = this.x - player.getX();
         * 
         * 
         * return Util.findIndexOfMax(dirs);
         */
        dirs[NORTH] = world.heatMap[x][y - 1];
        dirs[EAST] = world.heatMap[x + 1][y];
        dirs[SOUTH] = world.heatMap[x][y + 1];
        dirs[WEST] = world.heatMap[x - 1][y];

        int ind = 0;
        for (int i = 0; i < dirs.length; i += 1) {
            if (dirs[ind] > dirs[i]) {
                ind = i;
            }
        }
        return ind;
    }

    /** Attacks the player. */
    public boolean attack() {
        return false;
    }

}
