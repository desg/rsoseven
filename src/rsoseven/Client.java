package rsoseven;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tldr.plugins.screenshot.Grabber;

public class Client {
	public static void main(String[] args) throws MalformedURLException,
			IOException, InstantiationException, IllegalAccessException,
			ClassNotFoundException, URISyntaxException, InterruptedException,
			AWTException {
		JFrame frame = new JFrame("Runescape 2007 Client");
		JLabel label = new JLabel();
		// JFrame loadingframe = new JFrame("Loading, wait you asshole");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setIconImage(new ImageIcon(new URL(
				"http://www.runescape.com/img/global/mobile.png")).getImage());
		ImageIcon icon = new ImageIcon(new URL(
				"http://www.runescape.com/img/game/splash.gif"));

		icon.setImageObserver(null);
		label.setIcon(icon);
		label.setVisible(true);

		frame.add(label);
		frame.setVisible(true);
		frame.pack();

		// System.out.println(System.getProperty("java.io.tmpdir"));

		RsOSeven a = new RsOSeven();
		frame.add(a.getApplet());
		frame.setSize(new Dimension(770, 530));
		frame.setMaximumSize(new Dimension(770, 530));
		frame.setMaximizedBounds(new Rectangle(frame.getSize()));
		frame.setMinimumSize(new Dimension(770, 530));
		frame.setResizable(false);

		label.setVisible(false);

		Thread.sleep(7000);
		// System.out.println(a.getApplet().getX()+", "+a.getApplet().getY()+", "+a.getApplet().getWidth()+", "+a.getApplet().getHeight());
		new Grabber(frame.getX()
				+ (frame.getWidth() - frame.getContentPane().getWidth()) - (frame.getHeight() - frame.getContentPane().getHeight()),
						frame.getY() + (frame.getHeight() - frame.getContentPane().getHeight()),		
						frame.getContentPane().getWidth(),
						frame.getContentPane().getHeight());
		// System.out.println(frame.getContentPane().getBounds());

	}
}