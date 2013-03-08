package rsoseven.ui;

import java.applet.Applet;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import rsoseven.ui.listners.KeyShortcutReader;
import rsoseven.ui.listners.windoListner;


public class MainFrame {

	private JFrame frame;
	private JLabel label;
	private RsAppletPanel appletPanel;
	private int topDecorSize;
	private int sideDecorSize;	
	private static int RS_CLIENT_X=765;
	private static int RS_CLIENT_Y=503;
	private Dimension mainFrameSize; 
	public MainFrame() throws AWTException, IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, URISyntaxException, NativeHookException {
		
		//set title
		frame = new JFrame("Runescape 2007 Client");
		//main window listener
		frame.addWindowListener(new windoListner());
		//don't close app when pressing close
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		//frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		label = new JLabel();
		Image i = new ImageIcon(new URL("http://tldr.me/1fwc8bt.png")).getImage();
		frame.setIconImage(i);
		ImageIcon icon = new ImageIcon(new URL(
				"http://www.runescape.com/img/game/splash.gif"));

		icon.setImageObserver(null);
		label.setIcon(icon);
		label.setVisible(true);
		frame.add(label);
		frame.setResizable(false);//FIXME: set back
		frame.setVisible(true);
		frame.pack();
		
		//get size of decorations
		sideDecorSize = (frame.getWidth()-label.getWidth())/2;
		topDecorSize = (frame.getHeight()- label.getHeight())-sideDecorSize;
		mainFrameSize = new Dimension(sideDecorSize*2+RS_CLIENT_X,topDecorSize+sideDecorSize+RS_CLIENT_Y+100);
		
		
		
		//add keylistener:
		GlobalScreen.registerNativeHook();
		GlobalScreen.getInstance().addNativeKeyListener(new KeyShortcutReader(this));
		
		
		
		//Start adding client
		appletPanel = new RsAppletPanel();
		label.setVisible(false);
		frame.add(appletPanel.getApplet());
		
		JPanel a = (JPanel) frame.getGlassPane();
		a.setVisible(true);
		a.setLayout(new BorderLayout());
		
		BotPanel b = new BotPanel();
		b.setLayout(new BoxLayout(b,BoxLayout.Y_AXIS));
		b.setSize(new Dimension(160,100));
		b.setBackground(Color.black);
		b.setMaximumSize(new Dimension(160,100));
		b.setMinimumSize(new Dimension(160,100));
		a.add(b,BorderLayout.SOUTH);
		b.add(Box.createRigidArea(new Dimension(1,100)),BorderLayout.SOUTH);
		JPanel c = new JPanel();
		
		c.setSize(new Dimension(RS_CLIENT_X,100));
		c.setBackground(Color.blue);
		c.setMaximumSize(new Dimension(RS_CLIENT_X,100));
		c.setMinimumSize(new Dimension(RS_CLIENT_X,100));
		b.add(c);
		frame.setSize(mainFrameSize);
		frame.setMaximumSize(mainFrameSize);
		frame.setMinimumSize(mainFrameSize);
		frame.setMaximizedBounds(new Rectangle(frame.getSize()));
		
		/*JPanel c = new JPanel();
		c.setSize(new Dimension(RS_CLIENT_X,100));
		c.setBackground(Color.blue);
		c.setMaximumSize(new Dimension(RS_CLIENT_X,100));
		c.setMinimumSize(new Dimension(RS_CLIENT_X,100));
		b.add(c);
		*/
		//a.add(Box.createVerticalGlue());

	}

	public JFrame getFrame(){
		return frame;
		
	}
}
