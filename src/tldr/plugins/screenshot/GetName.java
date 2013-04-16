package tldr.plugins.screenshot;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import rsoseven.ui.MainFrame;

public class GetName extends Thread{
	
	private MainFrame root;
	private Robot robot;
	private Point click;
	private Point corner;
	private BufferedImage capture;
	
	public GetName(MainFrame root, Point point){
		this.root=root;
		this.click= point;
		this.robot = root.getRobot();
		this.corner = root.getScreencorner();
		
	}
	
	@Override
	public void run(){
		try {
			this.capture = new Robot().createScreenCapture(new Rectangle(click.x, click.y, 100, 400));
			
			
			//find left top corner
			//find right top corner
			//find bot left corner
			
			
			
			
			
			String homeDir = System.getProperty("user.home");
			String s = File.separator;
			
			
			File imagedic = new File(homeDir+s+"EthoClient");
			
			if (!imagedic.exists()){
				imagedic.mkdir();
			}
			
			String ImagePath = imagedic.getPath()+s+String.valueOf(System.currentTimeMillis())+".png";
			File outputfile = new File(ImagePath);
			try {
				ImageIO.write(capture, "png", outputfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
}
