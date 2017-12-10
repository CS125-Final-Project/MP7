/**
 * 
 */

package gamelogic;

/**
 * get mckfuckin slimed punk. this scrappy fellow will fuck your shit up. Get
 * meltified by its bone sizzling acid.
 * 
 * @author Elayda
 *
 */
public class Slime extends Mob {
    public static final char ASCII = 'S';
    
    /** Moves the slime. */
    public void move() {
        int moveDir = findPlayer();
        int newX = Util.newX(x, moveDir);
        int newY = Util.newY(y, moveDir);
        if (world.moveObject(x, y, newX, newY)) {
            x = newX;
            y = newY;
        } else {
            System.out.println("The slime says 'blurp'.");
        }
    }
    
    /**
     * ISSA NEW SLIME.
     * 
     */
    public Slime(int xPos, int yPos, final Map exist) {
        ascii = ASCII;
        name = "Slime";
        attackRange = 1;
        world = exist;
        x = xPos;
        y = yPos;
        // TODO Auto-generated constructor stub
    }
    

}
