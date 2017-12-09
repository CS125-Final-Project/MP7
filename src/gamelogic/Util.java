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
}
