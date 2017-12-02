package gamelogic;


/**
 * This is the class which contains the basic properties for all immovable
 * objects.
 * @author Elayda
 *
 */
public abstract class GameObject {
    /**
     * This is the character an object will show when it is printed.
     */
private String ascii = " ";
/**
 * This boolean controls whether a mob can enter this tile.
 */
private boolean passable = false;

/**
 * This method prints an obstacle's ascii character.
 */
public void printAscii() {
    System.out.print(this.ascii);
}
}
