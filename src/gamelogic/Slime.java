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
        world.gameMap[this.x][this.y] = null;
        this.x = Util.newX(this.x, moveDir);
        this.y = Util.newY(this.y, moveDir);
        world.gameMap[this.x][this.y] = this;
    }
    /**
     * .
     * 
     */
    public Slime(int xPos, int yPos, final Map exist) {
        ascii = ASCII;
        name = "Slime";
        this.attackRange = 1;
        this.world = exist;
        this.x = xPos;
        this.y = yPos;
        // TODO Auto-generated constructor stub
    }
    

}
