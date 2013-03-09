package rsoseven.ui;

import java.applet.Applet;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import rsoseven.lib.type.Message;
import rsoseven.lib.type.MessageList;
import rsoseven.ui.listners.KeyShortcutReader;
import rsoseven.ui.listners.windoListner;


public class MainFrame {

	private JFrame frame;
	private JLabel label;
	private RsAppletPanel appletPanel;
	private int topDecorSize;
	private int sideDecorSize;	
	public static int RS_CLIENT_X=765;
	public static int RS_CLIENT_Y=503;
	private Dimension mainFrameSize; 
	private MessageList mList;
	private BotPanel b;
	private JPanel a;
	
	public MainFrame() throws AWTException, IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, URISyntaxException, NativeHookException {
		
		//create messagelist
		mList= new MessageList(7);
		mList.add(new Message("This chat is _NOT_ part of runescape and is not official, endorsed, or moderated by JAGEX.",Color.DARK_GRAY));
		mList.add(new Message("You are not allowed to use this client to infringe with the JAGEX Rules of Conduct.",Color.DARK_GRAY));
		mList.add(new Message("Welcome to MM client V4, This client is not a cheating/botting tool, Please don't cheat!",Message.INFO));
		mList.add(new Message("REPORT RUNESCAPE TOS VIOLATIONS TO: Nick.Hermans.Be@Gmail.com",Message.ALERT));
		mList.add(new Message("To view HELP press CTRL+Q, To close this Box Press CTRL+C",Message.INFO));
		mList.add(new Message("You are now speaking in #MM as Ethoxyethaan",Message.ALERT));
		//mList.add(new Message("Welcome to mm client v3",Message.INFO));
		//mList.add(new Message("MM TRIP IN 2 DAYS 3 HOURS",Message.ALERT));
		//mList.add(new Message("You are now speaking in #MM as Ethoxyethaan",Message.INFO));
		//mList.add(new Message("<Runekas_3>: Lololololoooooool",Message.CHAT));
		//mList.add(new Message("<Ethoxyethaan>: Shadap kid.",Message.CHAT));
		//mList.add(new Message("<Runekas_3>: Nou",Message.CHAT));
		//mList.add(new Message("Screenshot saved as 1213231465.png",Message.INFO));
		
		//set title
		frame = new JFrame("Runescape 2007 Client");
		//main window listener
		frame.addWindowListener(new windoListner());
		//don't close app when pressing close
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		//frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		b = new BotPanel(this);

		
		label = new JLabel();
		//new ImageIcon(getClass().getResource("images/mark.gif"));
		Image i = new ImageIcon(getClass().getClassLoader().getResource("res/icon.png")).getImage();
		frame.setIconImage(i);
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("res/loader.gif"));
		ImageIcon spacerIcon = new ImageIcon(getClass().getClassLoader().getResource("res/spacer.png"));
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
		
		a = (JPanel) frame.getGlassPane();
		a.setVisible(true);
		a.setLayout(new BorderLayout());
		
		b = new BotPanel(this);
		//b.setLayout(new BoxLayout(b,BoxLayout.X_AXIS));
		b.setLayout(new GridBagLayout());
		
		//leftspacer with spacerIcon is a image label that controlls the size of of the SOUTH frame
		//its a hack but it works
		GridBagConstraints leftSpacer = new GridBagConstraints();
		leftSpacer.anchor = leftSpacer.WEST;
		leftSpacer.gridheight = 10;
		b.setSize(new Dimension(765,100));
		//b.setBackground(Color.black);
		b.setOpaque(false);
		b.setMaximumSize(new Dimension(765,100));
		b.setMinimumSize(new Dimension(765,100));
		//a.add(Box.createRigidArea(new Dimension(1,100)),BorderLayout.SOUTH);
		a.add(b,BorderLayout.SOUTH);
		b.add(new JLabel(spacerIcon),leftSpacer);
			/*
		JPanel c = new JPanel();
		c.setSize(new Dimension(RS_CLIENT_X,100));
		c.setBackground(Color.blue);
		c.setMaximumSize(new Dimension(RS_CLIENT_X,100));
		c.setMinimumSize(new Dimension(RS_CLIENT_X,100));
		b.add(c);*/
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
	
	public MessageList getMessageList(){
		return mList;
	}

	public void message(String string,Color color) {
		// TODO Auto-generated method stub
		mList.add(new Message(string,color));
		b.repaint();
	}
	public void message(String string) {
		// TODO Auto-generated method stub
		message(string,Message.INFO);
		b.repaint();
	}
	
	public void redraws(){
		b.repaint();
	}

	public Dimension getMainFrameSize() {
		// TODO Auto-generated method stub
		return mainFrameSize;
	}

	public void hideBot() {
		// TODO Auto-generated method stub
		a.setVisible(false);
	}
	
	public void showBot() {
		// TODO Auto-generated method stub
		a.setVisible(true);
	}
	
	
}
