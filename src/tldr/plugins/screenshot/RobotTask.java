package tldr.plugins.screenshot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import rsoseven.ui.MainFrame;

public class RobotTask extends Thread {
	private Robot robot;
	private ArrayList <CharSequence> characters;
	private boolean lock;
	private MainFrame root;

	public RobotTask(MainFrame root) {
		this.root = root;
		characters = new ArrayList<CharSequence>();
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock = false;

	}

	private void process(){
		this.lock=true;
		
		
		ArrayList<CharSequence> backup = new ArrayList<CharSequence>(); 
		for (CharSequence i:characters){
			backup.add(i);
		}
		characters = new ArrayList<CharSequence>();

		//lock thread, not standard way but is less of a pain in the ass for this class
		for (CharSequence i: backup){
			System.out.println("writing");
			//robot.keyRelease(KeyEvent.VK_CONTROL);
			//write everythign away.
			this.type(i);
		}


		//unlock thread
		this.lock=false;
	}


	private void type(CharSequence characters) {
		int length = characters.length();
		for (int i = 0; i < length; i++) {
			char character = characters.charAt(i);
			type(character);
		}
	}

	public void add(String reply){
		//reply = reply.replaceAll("[^a-bA-Z0-9\\\\ `-=~\\!@#$%\\^&\\*\\(\\)_\\+\\[\\]\\{\\}\\|\\;\\:\'\"\\,]", "");
		/*if (reply.length()>79){
			reply = reply.substring(0, 78)+"\n";

		} else{
			reply=reply+"\n";	
		}
		System.out.println(reply);
		 */
		if (reply.length()>79 && reply.contains(" ")){
			String [] splits = reply.split(" ");
			String temp = "";
			for (int i=0;i<splits.length;i++){
				if ((temp+" "+splits[i]+"\n").length() > 79){
					characters.add(temp+"\n");
					temp=splits[i];
				} else {
					temp=temp+" "+splits[i];
				} 
				
			}
			characters.add(temp+"\n");
		}
		else if (reply.length()>79){
			reply = reply.substring(0, 78)+"\n";
			characters.add(reply);
		} else {
			reply = reply+"\n";
			characters.add(reply);
		}


	}
	private void type(char character) {
		switch (character) {
		case 'a': doType(KeyEvent.VK_A); break;
		case 'b': doType(KeyEvent.VK_B); break;
		case 'c': doType(KeyEvent.VK_C); break;
		case 'd': doType(KeyEvent.VK_D); break;
		case 'e': doType(KeyEvent.VK_E); break;
		case 'f': doType(KeyEvent.VK_F); break;
		case 'g': doType(KeyEvent.VK_G); break;
		case 'h': doType(KeyEvent.VK_H); break;
		case 'i': doType(KeyEvent.VK_I); break;
		case 'j': doType(KeyEvent.VK_J); break;
		case 'k': doType(KeyEvent.VK_K); break;
		case 'l': doType(KeyEvent.VK_L); break;
		case 'm': doType(KeyEvent.VK_M); break;
		case 'n': doType(KeyEvent.VK_N); break;
		case 'o': doType(KeyEvent.VK_O); break;
		case 'p': doType(KeyEvent.VK_P); break;
		case 'q': doType(KeyEvent.VK_Q); break;
		case 'r': doType(KeyEvent.VK_R); break;
		case 's': doType(KeyEvent.VK_S); break;
		case 't': doType(KeyEvent.VK_T); break;
		case 'u': doType(KeyEvent.VK_U); break;
		case 'v': doType(KeyEvent.VK_V); break;
		case 'w': doType(KeyEvent.VK_W); break;
		case 'x': doType(KeyEvent.VK_X); break;
		case 'y': doType(KeyEvent.VK_Y); break;
		case 'z': doType(KeyEvent.VK_Z); break;
		case 'A': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_A); break;
		case 'B': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_B); break;
		case 'C': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_C); break;
		case 'D': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_D); break;
		case 'E': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_E); break;
		case 'F': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_F); break;
		case 'G': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_G); break;
		case 'H': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_H); break;
		case 'I': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_I); break;
		case 'J': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_J); break;
		case 'K': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_K); break;
		case 'L': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_L); break;
		case 'M': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_M); break;
		case 'N': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_N); break;
		case 'O': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_O); break;
		case 'P': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_P); break;
		case 'Q': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Q); break;
		case 'R': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_R); break;
		case 'S': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_S); break;
		case 'T': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_T); break;
		case 'U': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_U); break;
		case 'V': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_V); break;
		case 'W': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_W); break;
		case 'X': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_X); break;
		case 'Y': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Y); break;
		case 'Z': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Z); break;
		//case '`': doType(KeyEvent.VK_BACK_QUOTE); break;
		case '0': doType(KeyEvent.VK_NUMPAD0); break;
		case '1': doType(KeyEvent.VK_NUMPAD1); break;
		case '2': doType(KeyEvent.VK_NUMPAD2); break;
		case '3': doType(KeyEvent.VK_NUMPAD3); break;
		case '4': doType(KeyEvent.VK_NUMPAD4); break;
		case '5': doType(KeyEvent.VK_NUMPAD5); break;
		case '6': doType(KeyEvent.VK_NUMPAD6); break;
		case '7': doType(KeyEvent.VK_NUMPAD7); break;
		case '8': doType(KeyEvent.VK_NUMPAD8); break;
		case '9': doType(KeyEvent.VK_NUMPAD9); break;
		/*
		case '-': doType(KeyEvent.VK_MINUS); break;
		case '=': doType(KeyEvent.VK_EQUALS); break;

		case '~': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE); break;
		case '!': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_1); break;
		case '@': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_2); break;
		case '#': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_3); break;
		case '$': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_4); break;
		case '%': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_5); break;
		case '^': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_6); break;
		case '&': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_7); break;
		case '*': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_8); break;
		case '(': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_9); break;
		case ')': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_0); break;
		case '_': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_MINUS); break;
		case '+': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_PLUS); break;
		*/
		case '\t': doType(KeyEvent.VK_TAB); break;
		case '\n': doType(KeyEvent.VK_ENTER); break;
		/*
		case '[': doType(KeyEvent.VK_OPEN_BRACKET); break;
		case ']': doType(KeyEvent.VK_CLOSE_BRACKET); break;
		case '\\': doType(KeyEvent.VK_BACK_SLASH); break;
		case '{': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET); break;
		case '}': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET); break;
		case '|': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH); break;
		case ';': doType(KeyEvent.VK_SEMICOLON); break;
		*/
		case ':': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON); break;
		/*case '\'': doType(KeyEvent.VK_QUOTE); break;
		case '"': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_QUOTE); break;
		case ',': doType(KeyEvent.VK_COMMA); break;
		case '<': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_COMMA); break;
		case '.': doType(KeyEvent.VK_PERIOD); break;
		case '>': doType(KeyEvent.VK_SHIFT,KeyEvent.VK_PERIOD); break;
		case '/': doType(KeyEvent.VK_SLASH); break;
		case '?': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH); break;
		*/
		case ' ': doType(KeyEvent.VK_SPACE); break;
		

		//extra
		//case '.': doType(KeyEvent.VK); break;
		
		default:
			//if nothing gets found use questionmark to indicate unknown symbol
			doType(KeyEvent.VK_SHIFT,KeyEvent.VK_SPACE);
		}
	}

	private void doType(int... keyCodes) {
		doType(keyCodes, 0, keyCodes.length);
	}

	private void doType(int[] keyCodes, int offset, int length) {
		if (length == 0) {
			return;
		} else if (root.getFrame().isActive() && root.getFrame().isShowing() && root.getFrame().isFocused()){

		robot.keyPress(keyCodes[offset]);
		doType(keyCodes, offset + 1, length - 1);
		Random a = new Random();
		try {
			Thread.sleep(a.nextInt(60)+1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyRelease(keyCodes[offset]);
		try {
			Thread.sleep(a.nextInt(60)+1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
	}

	@Override
	public void run() {
		while(true){

			this.process();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
	}
}



