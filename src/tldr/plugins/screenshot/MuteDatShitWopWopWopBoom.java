package tldr.plugins.screenshot;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import rsoseven.ui.MainFrame;

public class MuteDatShitWopWopWopBoom extends Thread{

	private MainFrame root;
	private Robot robo;

	public MuteDatShitWopWopWopBoom(MainFrame root){
		this.root=root;
		this.robo = root.getRobot();
	}
	@Override
	public	void run(){
		while (true){

			try {
				sleep(500);
				root.getRobot().getPixelColor(1, 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (root.getFrame().isActive() && root.getFrame().isShowing() && root.getFrame().isFocused()){
				//get pixel in location to check if its unmuted
				Color rgb = robo.getPixelColor(root.getScreencorner().x+744,root.getScreencorner().y+ 491);
				
				//if music is unmuted mute it
				if (rgb.equals(new Color(65,63,63))){
					
					//get current mouse loc save it for later
					Point oldmouse = MouseInfo.getPointerInfo().getLocation();
					BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

					// Create a new blank cursor.
					Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
					    cursorImg, new Point(0, 0), "blank cursor");

					// Set the blank cursor to the JFrame.
					root.getFrame().getContentPane().setCursor(blankCursor);
					//this hides the mouse cursor
					
					//move & click to disable sound
					robo.mouseMove(root.getScreencorner().x+744, root.getScreencorner().y+491);
					//sleep 5 seconds for SLOW CPUs
					robo.delay(5);
					//wait for robot
					robo.waitForIdle();
					robo.mousePress(MouseEvent.BUTTON1_MASK);
					robo.mouseRelease(MouseEvent.BUTTON1_MASK);
					
					//set mouse back @ old spot
					robo.mouseMove(oldmouse.x, oldmouse.y);

					//set cursor back to old cursor
					root.getFrame().getContentPane().setCursor(null);
					//System.out.println("sound muted");
					
				}
			}

		}
	}
}
