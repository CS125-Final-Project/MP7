package gamelogic;

/**
 * This is an enemy which does a thing.
 * 
 * @author Elayda
 *
 */
public class Mob extends GameObject {

    /**
     * Is it trying to kill you.?
     */
    private boolean isHostile;
    private int attackRange;
    
    
    public int findPlayer() {
        return 0;
    }
    
}
