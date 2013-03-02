package rsoseven;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Client {
	public static void main(String[] args) throws MalformedURLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
			RsOSeven a = new RsOSeven();
			JFrame frame = new JFrame("WE CLIENT NOW AHUEHEU!!!");
			frame.add(a.getApplet());
			frame.setSize(new Dimension(770,530));
			frame.setMaximumSize(new Dimension(770,530));
			frame.setMaximizedBounds(new Rectangle(frame.getSize()));
			frame.setMinimumSize(new Dimension(770,530));
			frame.setResizable(false);
			//frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setIconImage(new ImageIcon(new URL("http://www.runescape.com/img/global/mobile.png")).getImage());
	}
}