package tldr.plugins.screenshot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Grabber {
	
	private BufferedImage capture;
	
	public Grabber(int xp,int yp,int xs,int ys) throws AWTException, IOException{
		this.capture = new Robot().createScreenCapture(new Rectangle(xp, yp, xs, ys));
		File outputfile = new File("saved.png");
	    ImageIO.write(capture, "png", outputfile);
	    
	}
	
}
