package rsoseven.ui;

import java.awt.AWTException;
import java.io.IOException;

import javax.swing.JFrame;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;



public class KeyShortcutReader implements NativeKeyListener {

	MainFrame mainFrame;
	public KeyShortcutReader(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame=mainFrame;
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.getKeyCode()==NativeKeyEvent.VK_F12){
			System.out.println("F12 pressed");
			try {
				mainFrame.takeScreen();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub		
	}
	
}
