package rsoseven.ui.listners;

import java.awt.AWTException;
import java.io.IOException;

import javax.swing.JFrame;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import rsoseven.ui.MainFrame;
import tldr.plugins.screenshot.Grabber;



public class KeyShortcutReader implements NativeKeyListener {

	MainFrame mainFrame;
	public KeyShortcutReader(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame=mainFrame;
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode()==NativeKeyEvent.VK_PRINTSCREEN){
			System.out.println("printscreen!");
			JFrame frame = mainFrame.getFrame();

			try {
					new Grabber (
					frame.getX() + (frame.getWidth() - frame.getContentPane().getWidth())/ 2,
					frame.getY() + (frame.getHeight() - frame.getContentPane().getHeight())
							- (frame.getWidth() - frame.getContentPane().getWidth()) / 2,
					frame.getContentPane().getWidth(),
					frame.getContentPane().getHeight(),mainFrame).run();
					
					
					
					
			
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
