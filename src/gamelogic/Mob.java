package gamelogic;

/**
 * You thought we needed to make a shitton of different classes. CHECKMATE, we
 * dun made an ABSTRACTION;
 * 
 * @author Elayda
 *
 */
public abstract class Mob extends GameObject {
    protected Map world;
    /** Is it trying to kill you.? */
    protected boolean isHostile;

    protected int attackRange;

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
    public int findPlayer() {
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
    public boolean attack(final int x, final int y, final int distance) {
        boolean na;
        boolean ea;
        boolean sa;
        boolean wa;
        if (distance > attackRange) {
            return false;
        }
        if (world.gameMap[x][y] instanceof Player) {
            world.gameMap[x][y].setAlive(false);
            return true;
        }

        if (x + 1 < world.getMapWidth()) {
            na = this.attack(x + 1, y, distance + 1);
        }
        if (x - 1 >= 0) {
            ea = this.attack(x - 1, y, distance + 1);
        }
        if (y + 1 < world.getMapHeight()) {
            sa = this.attack(x, y + 1, distance + 1);
        }
        if (y - 1 >= 0) {
            wa = this.attack(x, y - 1, distance + 1);
        }

    }

}
