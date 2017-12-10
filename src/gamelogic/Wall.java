/***/

package gamelogic;

/**
 * This is the class used to denote that there is a wall.
 * 
 * @author Elayda
 */
public class Wall extends GameObject {

    public static final char ASCII = '#';
    
    /**
     * This controls whether the wall can be broken.
     */
    // private boolean canBreak;
    
    public Wall() {
        ascii = ASCII;
    }
}
