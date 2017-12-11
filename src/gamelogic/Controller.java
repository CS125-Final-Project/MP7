package gamelogic;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
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

        JTextArea ascii = new JTextArea(Util.getFile("Title_Screen.txt"));
        title.add(ascii);
        title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setLayout(new FlowLayout());
        title.setPreferredSize(new Dimension(1280, 720));
        title.setMinimumSize(new Dimension(1280, 720));
        ascii.setFont(new Font("Courier", Font.PLAIN, 10));
        ascii.setLineWrap(true);
        ascii.setEditable(false);
        ascii.setPreferredSize(new Dimension(1280, 720));
        ascii.setMinimumSize(new Dimension(1280, 720));
        title.addKeyListener(new MyKeyListener());

        ascii.addKeyListener(new MyKeyListener());
        title.setVisible(true);

        // map.printToConsole();
        /*
         * while (player.isAlive()) { // get player's next move int nextMove = -1; while
         * (!map.checkMove(nextMove)) { nextMove = getMove(); } }
         */
    }
    
    /**
     * This is a helper function which will start the game from the title screen
     * using the Key Listener enter function. This also displays the initial ASCII
     * game screen.
     */
    public static void startGame() {
        started = true;
        title.setVisible(false);
        map = new Map("Map_Data.txt");
        player = map.getPlayer();

        JFrame gameScreen = new JFrame();
        gameScreen.addKeyListener(new MyKeyListener());
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen.setLayout(new FlowLayout());
        gameScreen.setPreferredSize(new Dimension(1280, 720));
        gameScreen.setMinimumSize(new Dimension(1280, 720));
        
        JTextArea asciiScreen = new JTextArea(map.processToGui());
        gameScreen.add(asciiScreen);
        asciiScreen.setEditable(false);
        asciiScreen.addKeyListener(new MyKeyListener());
        asciiScreen.setFont(new Font("Courier", Font.PLAIN, 100));
        
        JTextArea controlGuide = new JTextArea(Util.getFile("Controls.txt"));
        gameScreen.add(controlGuide);
        gameScreen.setVisible(true);
    }

    private static JFrame title;
    private static boolean started = false;
    private static Map map;
    
    @SuppressWarnings(value = {"unused" })
    private static Player player;
    private static boolean playerTurn = true;

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
        if (playerTurn && map.checkMove(direction)) {
            // does some stuff
            playerTurn = false;
            map.movePlayer(direction);
            map.printToConsole();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            moveEnemies();
            playerTurn = true;
        }
        // don't do anything if the move was invalid
    }

    private static void moveEnemies() {
        map.genHeatMap();
        for (int i = 0; i < map.getEnemies().size(); i += 1) {
            Mob temp = ((Mob) map.getEnemies().get(i));
            temp.attack(temp.getXpos(), (temp).getYpos(), temp.attackRange);
        }
        for (int i = 0; i < map.getEnemies().size(); i += 1) {
            Mob temp = ((Mob) map.getEnemies().get(i));
            temp.move();
        }
        map.printToConsole();
    }

    public static boolean isPlayerTurn() {
        // TODO Auto-generated method stub
        return playerTurn;
    }

    /*
     * private static int getMove() { return -1; }
     */
}
