package rsoseven.ui;

import java.applet.Applet;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.OverlayLayout;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import rsoseven.ui.listners.KeyShortcutReader;
import rsoseven.ui.listners.windoListner;
import tldr.plugins.screenshot.Grabber;

public class MainFrame {

	private JFrame frame;
	private JLabel label;
	private JLabel userMessage;
	private TrayIcon trayIcon;
	private SystemTray tray = SystemTray.getSystemTray();
	
	public MainFrame() throws AWTException, IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, URISyntaxException, NativeHookException {
		
		frame = new JFrame("Runescape 2007 Client");
		frame.addWindowListener(new windoListner());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		label = new JLabel();
		
		//add keylistener:
		GlobalScreen.registerNativeHook();
		GlobalScreen.getInstance().addNativeKeyListener(new KeyShortcutReader(this));
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image i = new ImageIcon(new URL("http://tldr.me/1mmv0v5.png")).getImage();
		frame.setIconImage(i);
		trayIcon = new TrayIcon(i);
		tray.add(trayIcon);
		
		
		ImageIcon icon = new ImageIcon(new URL(
				"http://www.runescape.com/img/game/splash.gif"));

		
		icon.setImageObserver(null);
		label.setIcon(icon);
		label.setVisible(true);

		frame.add(label);
		frame.setVisible(true);
		frame.pack();
		
		//OVERLAY to show user messages
		JPanel glass = (JPanel) frame.getGlassPane();
		glass.setVisible(true);
		glass.setLayout(new BorderLayout());
		
		userMessage = new JLabel("Welcome to the matrix");
		userMessage.setVisible(false);
		glass.add(userMessage,BorderLayout.SOUTH);
		
		//Start adding client
		RsOSeven a = new RsOSeven();
		frame.add(a.getApplet());
		frame.setSize(new Dimension(770, 530));
		frame.setMaximumSize(new Dimension(770, 530));
		frame.setMaximizedBounds(new Rectangle(frame.getSize()));
		frame.setMinimumSize(new Dimension(770, 530));
		frame.setResizable(false);
		
		//remove loading image
		label.setVisible(false);

		label.setBackground(new Color(0, 0, 0, 0));
		glass.setBackground(new Color(0, 0, 0, 0));
		userMessage.setBackground(new Color(0, 0, 0, 0));
		
		label.setOpaque(false);
		glass.setOpaque(false);
		userMessage.setOpaque(false);
	}

	public JFrame getFrame(){
		return frame;
		
	}
	
	public void notifyUser(String message){

				trayIcon.displayMessage("Screenshot taken",
				"Screenshot saved, Uploaded and copied to clipboard",
				TrayIcon.MessageType.WARNING);
	}
	
	public void clearMessage(){
		//userMessage.setVisible(false);
	}
}
