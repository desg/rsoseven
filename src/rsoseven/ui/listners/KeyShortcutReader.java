package rsoseven.ui.listners;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

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
	private boolean ignore;
	private boolean legacy;
	private int lastkey=0;

	public KeyShortcutReader(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame=mainFrame;
		this.ignore=false;
		this.legacy=true;
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {

		//only capture if game is active else dispose (don't capture keys when in background)
		if (mainFrame.getFrame().isActive()){

			//handle keys when ctrl is pressed
			if ((arg0.getModifiers() & NativeInputEvent.CTRL_MASK) !=0){
				if (arg0.getKeyCode()==NativeKeyEvent.VK_E ){
					legacy =!legacy;
					mainFrame.message(legacy?"legacy EOC Fkeys turned on":"Legacy EOC Fkeys turned off",Message.ALERT);
				}
				//only capture screenshot if the client is active
				//so when pressing printscreen outside client gets ignored by client

				else if (arg0.getKeyCode()==NativeKeyEvent.VK_C ){
					if ( mainFrame.getBot().isVisible()){
						//because it points to eachother-
						mainFrame.hideBot();

					} else {
						mainFrame.showBot();
					}
				}

				else if (arg0.getKeyCode()==NativeKeyEvent.VK_T ){
					mainFrame.getFrame().setAlwaysOnTop(!mainFrame.getFrame().isAlwaysOnTop());
					mainFrame.message("To toggle AlwaysOnTop press CTRL+T. PS: Your mom is always on top.",Message.INFO);

				}
				else if (arg0.getKeyCode()==NativeKeyEvent.VK_M ){
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
				else if (arg0.getKeyCode()==NativeKeyEvent.VK_Q){

					mainFrame.message("",Message.ALERT);
					mainFrame.message("",Message.ALERT);
					mainFrame.message("################### CLIENT HELP ###################",Message.ALERT);
					mainFrame.message("CTRL+C: Toggle Console CTRL+T: Toggle Always On Top",Message.ALERT);
					mainFrame.message("CTRL+X: Close Game     CTRL+L: Toggle 1337 Mode",Message.ALERT);			
					mainFrame.message("CTRL+Q: View Help      CTRL+E: Enable EOC legacy keys",Message.ALERT);
					mainFrame.message("CTRL+M: View Map       PRTSCR: Print screen & upload",Message.ALERT);
					mainFrame.message("################### CLIENT HELP ###################",Message.ALERT);
				}

				else if (arg0.getKeyCode()==NativeKeyEvent.VK_X ){
					JFrame root = mainFrame.getFrame();
					if (0==JOptionPane.showConfirmDialog(root, "Are you sure you want to close the client?")){
						GlobalScreen.unregisterNativeHook();
						root.dispose();
						System.exit(0);
					} else {
						//JOptionPane.showMessageDialog(root, "");
					}	
				}

				else if (arg0.getKeyCode()==NativeKeyEvent.VK_L ){ 
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
							e.printStackTrace();
						}
						catch (UnsupportedAudioFileException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (LineUnavailableException e) {
							e.printStackTrace();
						}
					}	
				}
			}
			else if ((arg0.getKeyCode()==NativeKeyEvent.VK_PRINTSCREEN  ) ) {
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
							frame.getContentPane().getHeight(),mainFrame).start();

				} catch (AWTException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (legacy && !ignore){
				if (legacy &&!ignore && arg0.getKeyCode()==NativeKeyEvent.VK_F1){
					ignore=true;
					mainFrame.getRobot().keyRelease(KeyEvent.VK_1);
					mainFrame.getRobot().waitForIdle();
					lastkey=NativeKeyEvent.VK_ESCAPE;
					mainFrame.getRobot().keyPress(KeyEvent.VK_ESCAPE);
					mainFrame.getRobot().keyRelease(KeyEvent.VK_ESCAPE);
					mainFrame.getRobot().waitForIdle();

				}

				else if (legacy &&!ignore && arg0.getKeyCode()==NativeKeyEvent.VK_F3  ){

					ignore=true;
					mainFrame.getRobot().keyRelease(KeyEvent.VK_3);
					mainFrame.getRobot().waitForIdle();
					lastkey=NativeKeyEvent.VK_F5;
					mainFrame.getRobot().keyPress(KeyEvent.VK_F5);
					mainFrame.getRobot().keyRelease(KeyEvent.VK_F5);
					mainFrame.getRobot().waitForIdle();
				}
				else if (legacy &&!ignore && arg0.getKeyCode()==NativeKeyEvent.VK_F4  ){
					ignore=true;

					mainFrame.getRobot().keyRelease(KeyEvent.VK_4);
					mainFrame.getRobot().waitForIdle();
					lastkey=NativeKeyEvent.VK_F6;
					mainFrame.getRobot().keyPress(KeyEvent.VK_F6);
					mainFrame.getRobot().keyRelease(KeyEvent.VK_F6);
					mainFrame.getRobot().waitForIdle();
				}
				else if (legacy &&!ignore && arg0.getKeyCode()==NativeKeyEvent.VK_F5  ){
					ignore=true;
					mainFrame.getRobot().keyRelease(KeyEvent.VK_5);
					mainFrame.getRobot().waitForIdle();
					lastkey=NativeKeyEvent.VK_F1;
					mainFrame.getRobot().keyPress(KeyEvent.VK_F1);
					mainFrame.getRobot().keyRelease(KeyEvent.VK_F1);
					mainFrame.getRobot().waitForIdle();
				}
				else if (legacy &&!ignore && arg0.getKeyCode()==NativeKeyEvent.VK_ESCAPE  ){
					ignore=true;
					mainFrame.getRobot().keyRelease(KeyEvent.VK_ESCAPE);
					mainFrame.getRobot().waitForIdle();
					lastkey=NativeKeyEvent.VK_F10;
					mainFrame.getRobot().keyPress(KeyEvent.VK_F10);
					mainFrame.getRobot().keyRelease(KeyEvent.VK_F10);
					mainFrame.getRobot().waitForIdle();
				}
			}
		}
	}

	/*if (roulette  ){
		Random rnd = new Random();
		if (rnd.nextInt(6)==0){
			mainFrame.message("def active");
			System.out.println("def active");
			ignore=true;
			lastkey=NativeKeyEvent.VK_F1;
			mainFrame.getRobot().keyPress(KeyEvent.VK_F1);
			mainFrame.getRobot().keyRelease(KeyEvent.VK_F1);
			//mainFrame.getBot()
			Point loc = mainFrame.getFrame().getLocation();
			loc.setLocation(loc.x+mainFrame.getSideDecorSize(), loc.y+mainFrame.getTopDecorSize());
			Point temp = MouseInfo.getPointerInfo().getLocation();
			mainFrame.getRobot().mouseMove(loc.x+605, loc.y+325);
			mainFrame.getRobot().delay(50);
			mainFrame.getRobot().waitForIdle();
			mainFrame.getRobot().mousePress(MouseEvent.BUTTON1_MASK);
			mainFrame.getRobot().mouseRelease(MouseEvent.BUTTON1_MASK);
			mainFrame.getRobot().delay(50);
			mainFrame.getRobot().waitForIdle();
			mainFrame.getRobot().mouseMove(temp.x, temp.y);
			ignore=true;
			lastkey=NativeKeyEvent.VK_ESCAPE;
			mainFrame.getRobot().keyPress(KeyEvent.VK_ESCAPE);
			mainFrame.getRobot().keyRelease(KeyEvent.VK_ESCAPE);

		}
		else {
			mainFrame.message("Lucky this time");
		}
	}*/
	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

		if (lastkey==arg0.getKeyCode()){
			ignore=false;
		} else {

		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub		
	}

}
