package gamelogic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener extends KeyAdapter implements KeyListener {

    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            
        }
        if (key == KeyEvent.VK_A) {
            
        }
        if (key == KeyEvent.VK_S) {
            
        }
        if (key == KeyEvent.VK_D) {
            
        }
    }

}
