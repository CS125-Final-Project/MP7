package gamelogic;

import java.io.File;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Scanner;

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
    /**
     * This will retrieve a text file and present a string.
     * @param templateFilename the name of the file include extension.
     * @return The text file as a string delineated by new lines.
     */
    
    public static String getFile(String templateFilename) {
        String templateText;
        try {
            String templatePath = Map.class.getClassLoader()
                    .getResource(templateFilename).getFile();
    
            templatePath = new URI(templatePath).getPath();
            File templateFile = new File(templatePath);
            Scanner templateScanner = new Scanner(templateFile, "UTF-8");
            templateText = templateScanner.useDelimiter("\\A").next();
            templateScanner.close();
        } catch (Exception e) {
            throw new InvalidParameterException("Bad file path" + e);
        }
        return templateText;
    }
    
    /** Prints the map to the console. */
    public static String processToGui(final String templateFilename) {
        String templateText;
        try {
            String templatePath = Map.class.getClassLoader()
                    .getResource(templateFilename).getFile();
    
            templatePath = new URI(templatePath).getPath();
            File templateFile = new File(templatePath);
            Scanner templateScanner = new Scanner(templateFile, "UTF-8");
            templateText = templateScanner.useDelimiter("\\A").next();
            templateScanner.close();
        } catch (Exception e) {
            throw new InvalidParameterException("Bad file path" + e);
        }
        String[] temp = templateText.split("\n");
        StringBuffer guiAccumulator = new StringBuffer();
        for (int y = 0; y < temp.length; y++) {
            for (int x = 0; x < 10; x += 1) {
                guiAccumulator.append(" ");
            }
            for (int x = 1; x < temp[y].length(); x++) {

                guiAccumulator.append("");
                guiAccumulator.append(temp[y].charAt(x));
                guiAccumulator.append("");
                
            }
            guiAccumulator.deleteCharAt(guiAccumulator.length() - 1);
            guiAccumulator.append("\n");
        }
        System.out.println(guiAccumulator.toString());
        return guiAccumulator.toString();
    }

}
