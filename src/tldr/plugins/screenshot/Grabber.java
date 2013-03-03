package tldr.plugins.screenshot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

public class Grabber {
	
	private BufferedImage capture;
	
	public Grabber(int xp,int yp,int xs,int ys) throws AWTException, IOException{
		this.capture = new Robot().createScreenCapture(new Rectangle(xp, yp, xs, ys));
		String homeDir = System.getProperty("user.home");
		String s = File.separator;
		
		File imagedic = new File(homeDir+s+"EthoClient");
		
		if (!imagedic.exists()){
			imagedic.mkdir();
		}
		String ImagePath = imagedic.getPath()+s+String.valueOf(System.currentTimeMillis())+".png";
		File outputfile = new File(ImagePath);
		ImageIO.write(capture, "png", outputfile);
		/*System.getProperty("user.home");
		File outputfile = new File("saved.png");
	    ImageIO.write(capture, "png", outputfile);
	    */
	    
	    
	}
	
}
