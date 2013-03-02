package rsoseven.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyShortcutReader implements KeyListener{


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		JFrame source = (JFrame)e.getSource();
		System.out.println("it works");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
