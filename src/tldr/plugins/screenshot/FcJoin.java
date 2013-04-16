package tldr.plugins.screenshot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import rsoseven.ui.MainFrame;

public class FcJoin extends Thread{
	
	private Robot robot;
	private MainFrame root;
	
	
	public FcJoin(MainFrame root){
		this.root = root;
		try {
			this.robot= new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		Color rgbcorner = robot.getPixelColor(root.getScreencorner().x,root.getScreencorner().y);
		Color refrgb = new Color(25,19,5);
		if (rgbcorner.equals(refrgb)){
			System.err.println(rgbcorner+" "+refrgb);
			robot.keyPress(KeyEvent.VK_F7);
			robot.keyRelease(KeyEvent.VK_F7);
			//600 450
			
			//get current mouse loc save it for later
			Point oldmouse = MouseInfo.getPointerInfo().getLocation();
			BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

			// Create a new blank cursor.
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			    cursorImg, new Point(0, 0), "blank cursor");

			// Set the blank cursor to the JFrame.
			//root.getFrame().getContentPane().setCursor(blankCursor);
			//this hides the mouse cursor
			
			//move & click to disable sound
			robot.mouseMove(root.getScreencorner().x+600, root.getScreencorner().y+450);
			//sleep 5 seconds for SLOW CPUs
			robot.delay(15);
			//wait for robot
			robot.waitForIdle();
			robot.mousePress(MouseEvent.BUTTON1_MASK);
			//robot.delay(500);
			//robot.waitForIdle();
			robot.mouseRelease(MouseEvent.BUTTON1_MASK);
			
			//set mouse back @ old spot
			robot.mouseMove(oldmouse.x, oldmouse.y);

			//set cursor back to old cursor
			root.getFrame().getContentPane().setCursor(null);
			root.getRoboTask().add("mm trip");
			//root.getRoboTask().add("/CC autojoined");
			//^no support for / symbol yet.
		}
	}
	
	
}
