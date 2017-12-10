package gamelogic;

public class Util {
    /**
     * Finds the index of the maximum value of an integer array.
     * @param data the array of integer data
     * @return the index of the max value
     */
    public static int findIndexOfMax(final int[] data) {
        int maxIdx = 0;
        for (int i = 1; i < data.length; i++) {
            if (data[i] > data[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    /**
     * Finds the index of the minimum value of an integer array.
     * @param data the array of integer data
     * @return the index of the minimum value
     */
    public static int findIndexOfMin(final int[] data) {
        int minIdx = 0;
        for (int i = 1; i < data.length; i++) {
            if (data[i] < data[minIdx]) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    // Codes for directions
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    
    /**
     * Returns the new x-coordinate after moving in the specified direction.
     * 
     * @param oldX the initial x-coordinate
     * @param dir the direction of movement (NORTH, EAST, SOUTH, or WEST)
     * @return the new x-coordinate
     */
    public static int newX(int oldX, int dir) {
        if (dir == EAST) {
            return oldX + 1;
        } else if (dir == WEST) {
            return oldX - 1;
        }
        return oldX;
    }
    
    /**
     * Returns the new y-coordinate after moving in the specified direction.
     * 
     * @param oldY the initial y-coordinate
     * @param dir the direction of movement (NORTH, EAST, SOUTH, or WEST)
     * @return the new y-coordinate
     */
    public static int newY(int oldY, int dir) {
        if (dir == SOUTH) {
            return oldY + 1;
        } else if (dir == NORTH) {
            return oldY - 1;
        }
        return oldY;
    }
}
