package tldr.plugins.collector;

import java.awt.AWTException;
import java.awt.Robot;

public class collectorruntime extends Thread {
	private Robot scrRobot;
	public collectorruntime() throws AWTException{
		scrRobot = new Robot();
		
	}
}
