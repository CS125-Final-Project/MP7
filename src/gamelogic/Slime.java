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

    /** This function moves the slime along the closest path towards the player.*/
    public String move() {
        int moveDir = findPlayer();
        int newX = Util.newX(xpos, moveDir);
        int newY = Util.newY(ypos, moveDir);
        if (world.moveObject(xpos, ypos, newX, newY)) {
            this.xpos = newX;
            this.ypos = newY;
        } else {
            System.out.println("The slime says 'blurp'.");
        }
        if (attack(this.xpos, this.ypos, attackRange)) {
            System.out.println("Player dead.");
            return this.name;
        } else {
            return null;
        }
    }

    /**
     * Slime Constructor, accepts an x and y position and a Map reference which
     * determines what map it exists on.
     * 
     * @param x This is the slime's position on the game map of the Map reference.
     * @param y This is the Slime's position on the game map of the Map reference.
     * @param exist This is a map reference which refers to where a slime exists.
     */
    public Slime(int x, int y, final Map exist) {
        ascii = ASCII;
        name = "Slime";
        attackRange = 1;
        world = exist;
        xpos = x;
        ypos = y;
        // TODO Auto-generated constructor stub
    }

}
