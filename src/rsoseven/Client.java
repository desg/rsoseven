package rsoseven;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Client {
	public static void main(String[] args) throws MalformedURLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
			JFrame frame = new JFrame("Runescape 2007 Client");
			JLabel label = new JLabel();
			//JFrame loadingframe = new JFrame("Loading, wait you asshole");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.setIconImage(new ImageIcon(new URL("http://www.runescape.com/img/global/mobile.png")).getImage());
			ImageIcon icon = new ImageIcon(new URL("http://www.runescape.com/img/game/splash.gif"));
			
			icon.setImageObserver(null);
			label.setIcon(icon);
			label.setVisible(true);
			
			frame.add(label);
			frame.setVisible(true);
			frame.pack();			
			
			RsOSeven a = new RsOSeven();
			frame.add(a.getApplet());
			frame.setSize(new Dimension(770,530));
			frame.setMaximumSize(new Dimension(770,530));
			frame.setMaximizedBounds(new Rectangle(frame.getSize()));
			frame.setMinimumSize(new Dimension(770,530));
			frame.setResizable(false);
			
			label.setVisible(false);
						
			
			
	}
}