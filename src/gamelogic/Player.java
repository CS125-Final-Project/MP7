/***/

package gamelogic;

/**
 * This is a javadoc comment.
 * 
 * @author Elayda
 *
 */
public class Player extends GameObject {
    
    char ascii = 'P';
    /**
     * has the player in question died.?
     */
    private boolean isAlive;

    /**
     * this is how many items you can stack in a place.
     */
    private GameObject[] inventory = new GameObject[MAX_STACK];
    Player() {
        isAlive = true;
        ascii = 'P';
    }
}
