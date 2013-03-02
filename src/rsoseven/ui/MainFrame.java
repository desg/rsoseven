package rsoseven.ui;

import java.applet.Applet;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import rsoseven.RsOSeven;
import tldr.plugins.screenshot.Grabber;

public class MainFrame {

	private JFrame frame;
	private JLabel label;

	public MainFrame() throws AWTException, IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, URISyntaxException, NativeHookException {
		frame = new JFrame("Runescape 2007 Client");
		
		
		
		label = new JLabel();
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
		
		//add keylistener:
		GlobalScreen.registerNativeHook();
		GlobalScreen.getInstance().addNativeKeyListener(new KeyShortcutReader(this));
		
		/*
		new Grabber(
				frame.getX() + (frame.getWidth() - frame.getContentPane().getWidth())/ 2,
				frame.getY() + (frame.getHeight() - frame.getContentPane().getHeight())
						- (frame.getWidth() - frame.getContentPane().getWidth()) / 2,
				frame.getContentPane().getWidth(),
				frame.getContentPane().getHeight());
		*/
	}
	public void takeScreen() throws AWTException, IOException{
		new Grabber (
		frame.getX() + (frame.getWidth() - frame.getContentPane().getWidth())/ 2,
		frame.getY() + (frame.getHeight() - frame.getContentPane().getHeight())
				- (frame.getWidth() - frame.getContentPane().getWidth()) / 2,
		frame.getContentPane().getWidth(),
		frame.getContentPane().getHeight());
	}
	
	
}
