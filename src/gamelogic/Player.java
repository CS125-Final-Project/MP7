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
    char ascii = 'P';
    
    /** Whether the player in question is alive. */
    private boolean isAlive;

    /** Items carried by the player. */
    private GameObject[] inventory = new GameObject[MAX_STACK];
    
    /** Constructor for player. */
    public Player() {
        isAlive = true;
        ascii = 'P';
    }
}
