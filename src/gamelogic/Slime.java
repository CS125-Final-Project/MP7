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
    public void move() {
        int moveDir = this.findPlayer();
        this.x = Util.newX(this.x, moveDir);
        this.x = Util.newY(this.y, moveDir);
    }
    /**
     * .
     * 
     */
    public Slime(int xPos, int yPos, final Map exist) {
        ascii = ASCII;
        this.attackRange = 1;
        this.world = exist;
        this.x = xPos;
        this.y = yPos;
        // TODO Auto-generated constructor stub
    }

}
