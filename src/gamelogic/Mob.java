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
         * // Stupid pathfinding.
         * dirs[NORTH] = this.y - player.getY(); dirs[EAST] = player.getX() - this.x;
         * dirs[SOUTH] = player.getY() - this.x; dirs[WEST] = this.x - player.getX();
         * 
         * return Util.findIndexOfMax(dirs);
         */
        
        // Smart pathfinding.
        dirs[NORTH] = world.heatMap[x][y - 1];
        dirs[EAST] = world.heatMap[x + 1][y];
        dirs[SOUTH] = world.heatMap[x][y + 1];
        dirs[WEST] = world.heatMap[x - 1][y];

        return Util.findIndexOfMin(dirs);
    }

    /** Attacks the player. */
    protected boolean attackNorth(final int x, final int y, final int distance) {
        if (distance > attackRange) {
            return false;
        }
        if (world.gameMap[x][y] instanceof Player) {
            ((Player) world.gameMap[x][y]).setAlive(false);
            return true;
        }
        if (world.gameMap[x][y] instanceof Wall) {
            return false;
        }
        return this.attackNorth(x, y - 1, distance + 1);
    }

    /** Attacks the player. */
    protected boolean attackEast(final int x, final int y, final int distance) {
        if (distance > attackRange) {
            return false;
        }
        if (world.gameMap[x][y] instanceof Player) {
            ((Player) world.gameMap[x][y]).setAlive(false);
            return true;
        }
        if (world.gameMap[x][y] instanceof Wall) {
            return false;
        }
        return this.attackEast(x + 1, y, distance + 1);
    }

    /** Attacks the player. */
    protected boolean attackSouth(final int x, final int y, final int distance) {
        if (distance > attackRange) {
            return false;
        }
        if (world.gameMap[x][y] instanceof Player) {
            ((Player) world.gameMap[x][y]).setAlive(false);
            return true;
        }
        if (world.gameMap[x][y] instanceof Wall) {
            return false;
        }
        return this.attackSouth(x, y + 1, distance + 1);
    }

    /** Attacks the player. */
    protected boolean attackWest(final int x, final int y, final int distance) {
        if (distance > attackRange) {
            return false;
        }
        if (world.gameMap[x][y] instanceof Player) {
            ((Player) world.gameMap[x][y]).setAlive(false);
            return true;
        }
        if (world.gameMap[x][y] instanceof Wall) {
            return false;
        }
        return this.attackWest(x - 1, y, distance + 1);
    }

    /** Attacks the player. */
    public boolean attack(final int x, final int y, final int distance) {

        if (distance > attackRange) {
            return false;
        }
        if (world.gameMap[x][y] instanceof Player) {
            ((Player) world.gameMap[x][y]).setAlive(false);
            return true;
        }
        if (world.gameMap[x][y] instanceof Wall) {
            return false;
        }

        if (x + 1 < world.getMapWidth()) {
            if (this.attackNorth(x + 1, y, distance + 1)) {
                return true;
            }
        }
        if (x - 1 >= 0) {
            if (this.attackEast(x - 1, y, distance + 1)) {
                return true;
            }
        }
        if (y + 1 < world.getMapHeight()) {
            if (this.attackSouth(x, y + 1, distance + 1)) {
                return true;
            }
        }
        if (y - 1 >= 0) {
            if (this.attackWest(x, y - 1, distance + 1)) {
                return true;
            }
        }
        return false;

    }

}
