package gamelogic;

/**
 * This is the class which contains the basic properties for all immovable
 * objects.
 * 
 * @author Elayda
 *
 */
public abstract class GameObject {
    /**
     * This is the character an object will show when it is printed.
     */
    protected char ascii;
    protected int xpos;
    protected int ypos;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ascii;
        result = prime * result + xpos;
        result = prime * result + ypos;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GameObject other = (GameObject) obj;
        if (ascii != other.ascii) {
            return false;
        }
        if (xpos != other.xpos) {
            return false;
        }
        if (ypos != other.ypos) {
            return false;
        }
        return true;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    /**
     * This is the maximum stack of items in the inventory or on the floor.
     */
    static final int MAX_STACK = 128;

    /** This method prints an obstacle's ascii character. */
    public void printAscii() {
        System.out.print(this.ascii + " ");
    }

    public char getAscii() {
        return this.ascii;
    }
}
