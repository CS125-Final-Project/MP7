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
        loadTitle();
    }

    public static MyKeyListener input = new MyKeyListener();
    
    /**
     * This helper function can be called in order to open the title screen.
     */
    public static void loadTitle() {
        clearScreens();
        
        title = new JFrame();

        JTextArea ascii = new JTextArea(Util.getFile("Title_Screen.txt"));
        title.add(ascii);
        title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setLayout(new FlowLayout());
        title.setPreferredSize(new Dimension(XDIM, 720));
        title.setMinimumSize(new Dimension(XDIM, 720));
        ascii.setFont(new Font("Monospaced", Font.PLAIN, 10));
        ascii.setLineWrap(true);
        ascii.setEditable(false);
        ascii.setPreferredSize(new Dimension(XDIM, 720));
        ascii.setMinimumSize(new Dimension(XDIM, 720));
        title.addKeyListener(input);

        ascii.addKeyListener(input);
        title.setVisible(true);
    }

    /**
     * Helper function to reset all screens.
     */
    private static void clearScreens() {
        if (title != null) {
            title.setVisible(false);
            title = null;
        }
        if (gameScreen != null) {
            gameScreen.setVisible(false);
            gameScreen = null;
        }
        if (deathScreen != null) {
            deathScreen.setVisible(false);
            deathScreen = null;
        }
        if (winScreen != null) {
            winScreen.setVisible(false);
            winScreen = null;
        }
        
    }
    
    /**
     * This is a helper function which will start the game from the title screen
     * using the Key Listener enter function. This also displays the initial ASCII
     * game screen.
     */
    public static void startGame() {
        started = true;
        clearScreens();
        
        map = new Map("Map_Data2.txt");
        player = map.getPlayer();

        gameScreen = new JFrame();
        gameScreen.addKeyListener(input);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen.setLayout(new FlowLayout());
        gameScreen.setPreferredSize(new Dimension(XDIM, 720));
        gameScreen.setMinimumSize(new Dimension(XDIM, 720));

        asciiScreen = new JTextArea(map.processToGui());
        gameScreen.add(asciiScreen);
        asciiScreen.setEditable(false);
        asciiScreen.addKeyListener(input);
        asciiScreen.setFont(new Font("Courier", Font.PLAIN, GAME_ASCII_SIZE));

        JTextArea controlGuide = new JTextArea(Util.getFile("Controls.txt"));
        gameScreen.add(controlGuide);
        gameScreen.setVisible(true);
    }

    /**
     * This is a function which will play a death screen based upon the enemy which
     * performed the kill.
     * 
     * @param slayer
     *            The mob which killed the player.
     */
    public static void deadGame(final String slayer) {
        started = false;
        clearScreens();
        
        deathScreen = new JFrame();
        deathScreen.addKeyListener(input);
        deathScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deathScreen.setLayout(new FlowLayout());
        deathScreen.setPreferredSize(new Dimension(XDIM, 720));
        deathScreen.setMinimumSize(new Dimension(XDIM, 720));

        JTextArea asciiScreen = new JTextArea(Util.processToGui(slayer + ".txt"));
        asciiScreen.setEditable(false);
        asciiScreen.addKeyListener(input);
        asciiScreen.setFont(new Font("MonoSpaced", Font.BOLD, 6));
        asciiScreen.setPreferredSize(new Dimension(XDIM, 720));
        asciiScreen.setMinimumSize(new Dimension(XDIM, 720));
        deathScreen.add(asciiScreen);
        deathScreen.setVisible(true);
    }

    
    /**
     * This helper function can be called in order to move to the win screen upon
     * victory.
     */
    public static void winGame() {
        started = false;
        clearScreens();
        winScreen = new JFrame();
        winScreen.addKeyListener(input);
        winScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winScreen.setLayout(new FlowLayout());
        winScreen.setPreferredSize(new Dimension(XDIM, 720));
        winScreen.setMinimumSize(new Dimension(XDIM, 720));

        JTextArea asciiScreen = new JTextArea(Util.processToGui("Win_Screen.txt"));
        winScreen.add(asciiScreen);
        asciiScreen.setEditable(false);
        asciiScreen.addKeyListener(input);
        asciiScreen.setFont(new Font("Monospaced", Font.BOLD, 6));

        winScreen.setVisible(true);
    }

    public static boolean isHasWon() {
        return hasWon;
    }

    public static void setHasWon(boolean hasWon) {
        Controller.hasWon = hasWon;
    }

    @SuppressWarnings(value = { "unused" })
    private static Player player;
    private static JFrame title;
    private static JFrame gameScreen;
    private static JFrame deathScreen;
    private static JFrame winScreen;
    private static JTextArea asciiScreen;
    private static boolean started = false;
    private static Map map;
    private static boolean hasWon = false;
    public static final int XDIM = 1366;
    public static final int YDIM = 766;
    public static final int GAME_ASCII_SIZE = 20;

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
    public static boolean movePlayer(int direction) {
        if (player.isAlive() && playerTurn && map.checkMove(direction)) {
            // does some stuff
            playerTurn = false;
            map.movePlayer(direction);
            map.printToConsole();
            /*
            asciiScreen.setEditable(true);
            //asciiScreen.addKeyListener(input);
            asciiScreen.setFont(new Font("Courier", Font.PLAIN, GAME_ASCII_SIZE));
            asciiScreen.setText(map.processToGui());
            asciiScreen.setEditable(false);
            gameScreen.setVisible(true);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            */
            String slayer = moveEnemies();
            if (slayer == null && !hasWon && player.isAlive()) {
                asciiScreen.setEditable(true);
                //asciiScreen.addKeyListener(input);
                asciiScreen.setFont(new Font("Courier", Font.PLAIN, GAME_ASCII_SIZE));
                asciiScreen.setText(map.processToGui());
                asciiScreen.setEditable(false);
                gameScreen.setVisible(true);
                map.printToConsole();
            } else {
                started = false;
                // deadGame(slayer);
            }
            playerTurn = true;
        }
        return true;
        // don't do anything if the move was invalid
    }

    /*    *//**
             * Kills the player if the end their turn right next to a mob.
             *//*
                * private static void enemyAttack() { for (int i = 0; i <
                * map.getEnemies().size(); i += 1) { Mob temp = ((Mob)
                * map.getEnemies().get(i)); temp.attack(temp.getXpos(), (temp).getYpos(),
                * temp.attackRange); } }
                */

    private static String moveEnemies() {
        map.genHeatMap();
        for (int i = 0; i < map.getEnemies().size(); i += 1) {
            Mob temp = ((Mob) map.getEnemies().get(i));
            String slayer = temp.move();
            if (slayer != null) {
                return slayer;
            }
        }
        return null;
        // map.printToConsole();
    }

    public static boolean isPlayerTurn() {
        // TODO Auto-generated method stub
        return playerTurn;
    }

    /*
     * private static int getMove() { return -1; }
     */
}
