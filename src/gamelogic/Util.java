package gamelogic;

public class Util {
    /**
     * Finds the index of the max value of an integer array.
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
    
    // Codes for directions
    public static int NORTH = 0;
    public static int EAST = 1;
    public static int SOUTH = 2;
    public static int WEST = 3;
    
    public static int newX(int oldX, int dir) {
        if (dir == EAST) {
            return oldX + 1;
        } else if (dir == WEST) {
            return oldX - 1;
        }
        return oldX;
    }
    
    public static int newY(int oldY, int dir) {
        if (dir == SOUTH) {
            return oldY + 1;
        } else if (dir == NORTH) {
            return oldY - 1;
        }
        return oldY;
    }
}
