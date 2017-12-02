/***/
package gamelogic;

/**
 * @author Elayda
 *
 */
public class Player extends GameObject {
    /**
     * has the player in question died?
     */
private boolean isAlive;
/**
 * this is how many items you can stack in a place.
 */
private GameObject[] inventory = new GameObject[MAX_STACK];
}
