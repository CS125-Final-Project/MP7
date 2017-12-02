/***/

package gamelogic;

/**
 * This is a javadoc comment.
 * 
 * @author Elayda
 *
 */
public class Player extends GameObject {

    /**
     * Whether the player in question has died.
     */
    private boolean isAlive;
    
    /**
     * Current item in use.
     */
    private GameObject item;
    
    /**
     * this is how many items you can stack in a place.
     */
    private GameObject[] inventory = new GameObject[MAX_STACK];
    
    public Player() {
        isAlive = true;
        item = null;
    }
}
