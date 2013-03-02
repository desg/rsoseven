package rsoseven;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jnativehook.NativeHookException;

import rsoseven.ui.MainFrame;
import tldr.plugins.screenshot.Grabber;

public class Client {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, AWTException, IOException, URISyntaxException, NativeHookException {
		new MainFrame();
	}
	
}