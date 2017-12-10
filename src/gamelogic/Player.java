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
    final public static char ASCII = 'P';
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
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
    private GameObject[] inventory = new GameObject[MAX_STACK];
    
    /** Constructor for player. */
    public Player(final int startX, final int startY) {
        isAlive = true;
        ascii = ASCII;
        x = startX;
        y = startY;
    }

    
}
