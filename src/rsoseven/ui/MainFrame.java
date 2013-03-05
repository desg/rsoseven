package rsoseven.ui;

import java.applet.Applet;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import rsoseven.ui.listners.KeyShortcutReader;
import rsoseven.ui.listners.windoListner;


public class MainFrame {

	private JFrame frame;
	private JLabel label;
	private RsOSeven a;
	private JPanel glass;
	private MessageLabel picLabel;
	private Applet applet;
	public MainFrame() throws AWTException, IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, URISyntaxException, NativeHookException {
		
		frame = new JFrame("Runescape 2007 Client");
		frame.addWindowListener(new windoListner());
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		label = new JLabel();
		Image i = new ImageIcon(new URL("http://tldr.me/1fwc8bt.png")).getImage();
		frame.setIconImage(i);
		ImageIcon icon = new ImageIcon(new URL(
				"http://www.runescape.com/img/game/splash.gif"));

		icon.setImageObserver(null);
		label.setIcon(icon);
		label.setVisible(true);

		frame.add(label);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
		glass = (JPanel)frame.getGlassPane();
		glass.setVisible(true);
		
		//oh god, i don't even know, this is the best way to add text to the client...
		ImageIcon userMessageBackground = new ImageIcon(new URL("http://tldr.me/1kv3wzh.png"));
		picLabel = new MessageLabel(userMessageBackground);
		picLabel.setOpaque(true);
		picLabel.setBackground(null);
		glass.setLayout(null);
		glass.add(picLabel);
		picLabel.setBounds(new Rectangle(new Point(5,480),picLabel.getPreferredSize()));
		picLabel.setVisible(false);
		
		//add keylistener:
		GlobalScreen.registerNativeHook();
		GlobalScreen.getInstance().addNativeKeyListener(new KeyShortcutReader(this));
		
		//Start adding client
		a = new RsOSeven();
		applet = (Applet) a.getApplet();
		
		frame.add(a.getApplet());
		
		frame.setSize(new Dimension(770, 530));
		frame.setMaximumSize(new Dimension(770, 530));
		frame.setMaximizedBounds(new Rectangle(frame.getSize()));
		frame.setMinimumSize(new Dimension(770, 530));

		label.setVisible(false);
		
		a.getApplet().repaint();		
	}

	public JFrame getFrame(){
		return frame;
		
	}
	
	public void notifyUser(String message){
		picLabel.setMessage(message);
		frame.repaint();
		applet.repaint();
		glass.repaint();
		picLabel.setVisible(true);
	}
	
	public void clearMessage(){
		frame.repaint();
		applet.repaint();
		glass.repaint();
		picLabel.setVisible(false);
	}
	public RsOSeven getApplet(){
		return a;
		
	}
}
