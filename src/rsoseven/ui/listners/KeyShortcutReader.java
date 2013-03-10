package rsoseven.ui.listners;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import rsoseven.lib.type.Message;
import rsoseven.ui.MainFrame;
import tldr.plugins.screenshot.Grabber;



public class KeyShortcutReader implements NativeKeyListener {

	private MainFrame mainFrame;
	private Clip clip;
	public KeyShortcutReader(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame=mainFrame;
		
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		//only capture screenshot if the client is active
		//so when pressing printscreen outside client gets ignored by client
		if (arg0.getKeyCode()==NativeKeyEvent.VK_PRINTSCREEN && mainFrame.getFrame().isActive()){
			//i'll leave the following in because sometimes the keylistener won't work or spaz out
			//prob something to do how i dispose the listener, see windoListner close event for more detail
			//DEBUG:
			mainFrame.message("Screenshot Saved to HOME, uploading now... PLEASE WAIT");
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
		if (arg0.getKeyCode()==NativeKeyEvent.VK_C && mainFrame.getFrame().isActive() && ((arg0.getModifiers() & NativeInputEvent.CTRL_MASK) !=0) ){
			Dimension d = mainFrame.getFrame().getSize();
			Dimension a = mainFrame.getMainFrameSize();
			if ( mainFrame.getBot().isVisible()){
				//FIXME: when you mod a it changes the value of actual screensize
				//because it points to eachother
				a.setSize(a.getWidth(),a.getHeight()-100);
				mainFrame.getFrame().setMaximumSize(a);
				mainFrame.getFrame().setMinimumSize(a);
				mainFrame.getFrame().setSize(a);
				mainFrame.getFrame().setMaximizedBounds(new Rectangle(a));
				mainFrame.hideBot();
				
			} else {
				a.setSize(a.getWidth(),a.getHeight()+100);
				mainFrame.getFrame().setMaximumSize(a);
				mainFrame.getFrame().setMinimumSize(a);
				mainFrame.getFrame().setSize(a);
				mainFrame.getFrame().setMaximizedBounds(new Rectangle(a));
				mainFrame.showBot();
			}
		}
		
		if (arg0.getKeyCode()==NativeKeyEvent.VK_T && mainFrame.getFrame().isActive() && ((arg0.getModifiers() & NativeInputEvent.CTRL_MASK) !=0) ){
			mainFrame.getFrame().setAlwaysOnTop(!mainFrame.getFrame().isAlwaysOnTop());
			mainFrame.message("To toggle AlwaysOnTop press CTRL+T. PS: Your mom is always on top kid.",Message.INFO);

		}
		if (arg0.getKeyCode()==NativeKeyEvent.VK_M && mainFrame.getFrame().isActive() && ((arg0.getModifiers() & NativeInputEvent.CTRL_MASK) !=0) ){
		    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
		        try {
					mainFrame.message("Opening the map... To your mom's bedroom EAYHOO",Message.INFO);
		            desktop.browse(new URL("http://www.runescape.com/img/rsp777/gamewin/runescape-map-24-july-07.jpg").toURI());
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
		if (((((arg0.getModifiers() & NativeInputEvent.CTRL_MASK) !=0) && arg0.getKeyCode()==NativeKeyEvent.VK_Q) || arg0.getKeyCode() == NativeKeyEvent.VK_F1)  && mainFrame.getFrame().isActive()  ){

			mainFrame.message("",Message.ALERT);
			mainFrame.message("",Message.ALERT);
			mainFrame.message("################### CLIENT HELP ###################",Message.ALERT);
			mainFrame.message("CTRL+C: toggle Chat    CTRL+T: Toggle Always On Top",Message.ALERT);
			mainFrame.message("CTRL+X: Close Game     CTRL+L: Toggle 1337 Mode",Message.ALERT);			
			mainFrame.message("CTRL+Q: View Help      CTRL+N: Nuke North Korea",Message.ALERT);
			mainFrame.message("CTRL+M: View Map       PRTSCR: Print screen & upload",Message.ALERT);
			mainFrame.message("################### CLIENT HELP ###################",Message.ALERT);
		}
		
		if (arg0.getKeyCode()==NativeKeyEvent.VK_X && mainFrame.getFrame().isActive() && ((arg0.getModifiers() & NativeInputEvent.CTRL_MASK) !=0) ){
			JFrame root = mainFrame.getFrame();
			if (0==JOptionPane.showConfirmDialog(root, "Are you sure you want to close the client?")){
				GlobalScreen.unregisterNativeHook();
				root.dispose();
				System.exit(0);
			} else {
				//JOptionPane.showMessageDialog(root, "");
			}	
		}
		
		if (arg0.getKeyCode()==NativeKeyEvent.VK_L && mainFrame.getFrame().isActive() && ((arg0.getModifiers() & NativeInputEvent.CTRL_MASK) !=0) ){ 
			if (clip != null){
				if (clip.isRunning()){
					clip.stop();
					mainFrame.message("You gone already? Too bad for you, more Nyan for me",Message.INFO);
				} else {
					mainFrame.message("Never stop Nyanning",Message.INFO);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			} else {
	 			URL url;
				try {
					mainFrame.message("1337 Mode Active Scrubs stand back",Message.INFO);
					url = getClass().getClassLoader().getResource("res/nyan.mid");
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			         clip = AudioSystem.getClip();
			         // Open audio clip and load samples from the audio input stream.
			         clip.open(audioIn);
			         clip.loop(Clip.LOOP_CONTINUOUSLY);

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 			//this.getClass().getClassLoader().getResource("res/nyan.mid");
				catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
