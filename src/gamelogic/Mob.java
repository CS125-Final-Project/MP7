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
    
    protected String name;
    
    public String getName() {
        return name;
    }

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
        // world.genHeatMap();
        dirs[Util.NORTH] = world.heatMap[xpos][ypos - 1];
        dirs[Util.EAST] = world.heatMap[xpos + 1][ypos];
        dirs[Util.SOUTH] = world.heatMap[xpos][ypos + 1];
        dirs[Util.WEST] = world.heatMap[xpos - 1][ypos];

        return Util.findIndexOfMin(dirs);
    }

    /** Attacks the player. */
    protected boolean attackNorth(final int x, final int y, final int distance) {
        if (distance > attackRange) {
            return false;
        }
        if (world.getPlayer().getXpos() == x && world.getPlayer().getYpos() == y) {
            Controller.deadGame(this.name);
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
        if (world.getPlayer().getXpos() == x && world.getPlayer().getYpos() == y) {
            Controller.deadGame(this.name);
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
        if (world.getPlayer().getXpos() == x && world.getPlayer().getYpos() == y) {
            Controller.deadGame(this.name);
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
        if (world.getPlayer().getXpos() == x && world.getPlayer().getYpos() == y) {
            Controller.deadGame(this.name);
            return true;
        }
        if (world.gameMap[x][y] instanceof Wall) {
            return false;
        }
        return this.attackWest(x - 1, y, distance + 1);
    }

    /** Attacks the player. */
    public boolean attack(final int x, final int y, final int distance, final int direction) {

        if (world.getPlayer().getXpos() == x && world.getPlayer().getYpos() == y) {
            return true;
        }
        if (world.gameMap[x][y] instanceof Wall) {
            return false;
        }

        if (direction == Util.NORTH && x + 1 < world.getMapWidth()) {
            if (this.attackNorth(x + 1, y, distance + 1)) {
                return true;
            }
        }
        if (direction == Util.EAST && x - 1 >= 0) {
            if (this.attackEast(x - 1, y, distance + 1)) {
                return true;
            }
        }
        if (direction == Util.SOUTH && y + 1 < world.getMapHeight()) {
            if (this.attackSouth(x, y + 1, distance + 1)) {
                return true;
            }
        }
        if (direction == Util.WEST && y - 1 >= 0) {
            if (this.attackWest(x, y - 1, distance + 1)) {
                return true;
            }
        }
        return false;

    }
    
    /** Attacks the player. */
    public boolean attack(final int x, final int y, final int distance) {

        if (world.getPlayer().getXpos() == x && world.getPlayer().getYpos() == y) {
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
    /** Individual mobs should override this function with the requisite special moves.
     * @return Did we kill something? */
    
    public abstract String move();
}
