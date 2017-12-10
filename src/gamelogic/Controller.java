package gamelogic;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Controller {

    /**
     * Starts the game.
     * 
     * @param args
     *            unused
     */
    public static void main(final String[] args) {
        title = new JFrame();
        
        JTextArea ascii = new JTextArea(Util.getFile("Title_Screen.txt"),50,50);
        JPanel stuff = new JPanel();
        title.add(stuff);
        stuff.add(ascii);
        ascii.setFont(new Font("Courier", Font.PLAIN, 10));
        ascii.setLineWrap(true);
        ascii.setEditable(false);
        title.addKeyListener(new MyKeyListener());
        //title.add(ascii);  
        title.setVisible(true);

        
        // map.printToConsole();
        /*
         * while (player.isAlive()) { // get player's next move int nextMove = -1; while
         * (!map.checkMove(nextMove)) { nextMove = getMove(); } }
         */
    }

    public static void startGame() {
        if (started == true) {
            return;
        }
        started = true;
        title.setVisible(false);
        map = new Map("Map_Data.txt");
        player = map.getPlayer();

        JFrame listening = new JFrame();
        listening.addKeyListener(new MyKeyListener());
        listening.setVisible(true);
    }
    
    private static JFrame title;
    private static boolean started = false;
    private static Map map;
    private static Player player;
    private static MyKeyListener input;

    public static void setStarted(boolean startedNew) {
        started = startedNew;
    }

    public static boolean isStarted() {
        return started;
    }

    /**
     * Tries to move the player in the specified direction. If successful, the
     * player moves, followed by the enemies' turn.
     * 
     * @param direction
     *            the direction to attempt to move
     */
    public static void movePlayer(int direction) {
        if (map.checkMove(direction)) {
            // does some stuff
            map.movePlayer(direction);
            map.printToConsole();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            moveEnemies();
        }
        // don't do anything if the move was invalid
    }

    private static void moveEnemies() {
        map.genHeatMap();
        for (int i = 0; i < map.getEnemies().size(); i += 1) {
            Mob temp = ((Mob) map.getEnemies().get(i));
            temp.attack(temp.x, temp.y, temp.attackRange);
        }
        for (int i = 0; i < map.getEnemies().size(); i += 1) {
            Mob temp = ((Mob) map.getEnemies().get(i));
            temp.move();
        }
        map.printToConsole();
    }

    /*
     * private static int getMove() { return -1; }
     */
}
