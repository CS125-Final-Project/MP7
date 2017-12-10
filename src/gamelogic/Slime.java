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
    public Slime(final Map exist) {
        ascii = ASCII;
        this.attackRange = 0;
        this.world = exist;
        // TODO Auto-generated constructor stub
    }

}
