/***/

package gamelogic;

/**
 * This is a javadoc comment.
 * 
 * @author Elayda
 *
 */
public class Player extends GameObject {
    
    /** Character to represent the player. */
    public static final char ASCII = 'P';
    
    public int getX() {
        return xpos;
    }
    
    public int getY() {
        return ypos;
    }
    
    /** Whether the player in question is alive. */
    private boolean isAlive;
    
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }
    
    /** Items carried by the player. */
    @SuppressWarnings("unused")
    private GameObject[] inventory = new GameObject[MAX_STACK];
    
    /**
     * This is a constructor for the player which takes a starting position.
     * @param startX Starting position on a map.
     * @param startY starting position on a map.
     */
    public Player(final int startX, final int startY) {
        isAlive = true;
        ascii = ASCII;
        xpos = startX;
        ypos = startY;
    }

    
}
