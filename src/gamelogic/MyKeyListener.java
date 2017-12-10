package gamelogic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener extends KeyAdapter implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {

            Controller.startGame();
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (key == KeyEvent.VK_W) {
            Controller.movePlayer(Util.NORTH);
        }
        if (key == KeyEvent.VK_A) {
            Controller.movePlayer(Util.WEST);
        }
        if (key == KeyEvent.VK_S) {
            Controller.movePlayer(Util.SOUTH);
        }
        if (key == KeyEvent.VK_D) {
            Controller.movePlayer(Util.EAST);
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            Controller.startGame();
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (key == KeyEvent.VK_W) {
            Controller.movePlayer(Util.NORTH);
        }
        if (key == KeyEvent.VK_A) {
            Controller.movePlayer(Util.WEST);
        }
        if (key == KeyEvent.VK_S) {
            Controller.movePlayer(Util.SOUTH);
        }
        if (key == KeyEvent.VK_D) {
            Controller.movePlayer(Util.EAST);
        }
    }

}
