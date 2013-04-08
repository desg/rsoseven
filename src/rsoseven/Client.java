package rsoseven;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URISyntaxException;
import org.jnativehook.NativeHookException;

import rsoseven.ui.MainFrame;

public class Client {
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, AWTException,
			IOException, URISyntaxException, NativeHookException {
		new MainFrame();
		int a = 0;
	}
}