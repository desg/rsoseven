package rsoseven.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import rsoseven.lib.type.Message;
import rsoseven.lib.type.MessageList;
import rsoseven.ui.listners.DerpListen;
import rsoseven.ui.listners.KeyShortcutReader;
import rsoseven.ui.listners.mouseListen;
import rsoseven.ui.listners.windoListner;
import tldr.plugins.screenshot.MuteDatShitWopWopWopBoom;
import tldr.plugins.screenshot.RobotTask;


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
	private Robot robot;
	private Point screencorner = new Point(0,0);
	private RobotTask roboTask;
	
	public RobotTask getRoboTask() {
		return roboTask;
	}

	public MainFrame() throws AWTException, IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, URISyntaxException, NativeHookException {
		
		//robot class for handeling most of the click // type operations.
		robot= new Robot();
		//max allowed number of messages set here
		mList= new MessageList(7);
		
		//Add startup messages
		mList.add(new Message("This console is NOT part of Runescape!!!",Color.DARK_GRAY));
		mList.add(new Message("And is not official, endorsed, or moderated by JAGEX LTD.",Color.DARK_GRAY));
		mList.add(new Message("It's not allowed to use this client to break the JAGEX Rules of Conduct.",Color.DARK_GRAY));
		mList.add(new Message("Welcome to MM 2007 client V5, This client is not a cheating/botting tool!",Message.INFO));
		mList.add(new Message("REPORT RUNESCAPE TOS VIOLATIONS TO: Nick.Hermans.Be@Gmail.com",Message.ALERT));
		mList.add(new Message("To view HELP press CTRL+Q, To close this Box Press CTRL+C",Message.INFO));

		//Create core frame where the client is directly embedded on (can't embed into panel causes screen refresh bugs in the applet)
		frame = new JFrame("RS 2007 Client. CTRL+C to open Console");
		
		//Center frame
		frame.setLocationRelativeTo(null);
		
		//close operations
		frame.addWindowListener(new windoListner());
		//don't close app when pressing close
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		//create this to prevent nullpointer
		b = new BotPanel(this);
		label = new JLabel();
		a = (JPanel) frame.getGlassPane();

		//load images from jar
		Image i = new ImageIcon(getClass().getClassLoader().getResource("res/icon.png")).getImage();
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("res/loader.gif"));
		ImageIcon spacerIcon = new ImageIcon(getClass().getClassLoader().getResource("res/spacer.png"));
		
		//prepare loader
		frame.setIconImage(i);
		icon.setImageObserver(null);
		label.setIcon(icon);
		label.setVisible(true);
		frame.add(label);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
		//get size of decorations
		sideDecorSize = (frame.getWidth()-label.getWidth())/2;
		topDecorSize = (frame.getHeight()- label.getHeight())-sideDecorSize;
		mainFrameSize = new Dimension(sideDecorSize*2+RS_CLIENT_X,topDecorSize+sideDecorSize+RS_CLIENT_Y+100);
		frame.setLocationRelativeTo(null);
		
		//Start adding client
		appletPanel = new RsAppletPanel();
		
		//hide loading image
		label.setVisible(false);
		frame.setLocationRelativeTo(null);
		frame.add(appletPanel.getApplet());
		frame.setLocationRelativeTo(null);
		
		a.setVisible(true);
		a.setLayout(new BorderLayout());
		b.setLayout(new GridBagLayout());
		
		//leftspacer with spacerIcon is a image label that controlls the size of of the SOUTH frame
		//its a hack but it works
		GridBagConstraints leftSpacer = new GridBagConstraints();
		leftSpacer.anchor = GridBagConstraints.WEST;
		leftSpacer.gridheight = 10;
		b.setSize(new Dimension(765,100));
		b.setOpaque(false);
		b.setMaximumSize(new Dimension(765,100));
		b.setMinimumSize(new Dimension(765,100));

		//add 1px hack.
		a.add(b,BorderLayout.SOUTH);
		b.add(new JLabel(spacerIcon),leftSpacer);

		frame.setSize(mainFrameSize);
		frame.setMaximumSize(mainFrameSize);
		frame.setMinimumSize(mainFrameSize);
		frame.setMaximizedBounds(new Rectangle(frame.getSize()));
		
		//hide botpanel
		this.hideBot();
		
		//why the hell not?
		//frame.pack();
		//because it fucks shit up :)
		frame.setLocationRelativeTo(null);
		
		//screencorner, this is usefull for clicks & positioning in the client
		//this needs to be REFRESHED each time when called (because user can move the client)
		roboTask = new RobotTask(this);
		roboTask.start();
		
		screencorner = new Point(frame.getLocation().x+sideDecorSize,frame.getLocation().y+topDecorSize);
		MuteDatShitWopWopWopBoom mute = new MuteDatShitWopWopWopBoom(this);
		mute.start();
	
		
		
		GlobalScreen.registerNativeHook();
		GlobalScreen.getInstance().addNativeKeyListener(new KeyShortcutReader(this));
		GlobalScreen.getInstance().addNativeMouseListener(new mouseListen(this));
		
		
		}

	public Point getScreencorner() {
		screencorner.setLocation(frame.getLocation().x+sideDecorSize,frame.getLocation().y+topDecorSize);
		return this.screencorner;
	}

	public int getTopDecorSize() {
		return topDecorSize;
	}

	public int getSideDecorSize() {
		return sideDecorSize;
	}

	public JFrame getFrame(){
		return frame;
		
	}
	
	public MessageList getMessageList(){
		return mList;
	}

	public void message(String string,Color color) {
		mList.add(new Message(string,color));
		b.repaint();
	}
	public void message(String string) {
		message(string,Message.INFO);
		b.repaint();
	}
	
	public void redraws(){
		b.repaint();
	}

	public Dimension getMainFrameSize() {
		return mainFrameSize;
	}

	public void hideBot() {
		Dimension c = mainFrameSize;
		c.setSize(c.getWidth(),c.getHeight()-100);
		
		frame.setMinimumSize(c);
		frame.setMinimumSize(c);
		frame.setSize(c);
		frame.setMaximizedBounds(new Rectangle(c));
		
		a.setVisible(false);
	}
	
	public void showBot() {
		Dimension c = mainFrameSize;
		c.setSize(c.getWidth(),c.getHeight()+100);
				
		frame.setMaximumSize(c);
		frame.setMinimumSize(c);
		frame.setSize(c);
		frame.setMaximizedBounds(new Rectangle(c));

		a.setVisible(true);
		
	}
	public JPanel getBot(){
		return a;
	}
	public Robot getRobot(){
		return robot;
	}
}
