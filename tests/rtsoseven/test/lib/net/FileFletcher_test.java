package rtsoseven.test.lib.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import rsoseven.lib.net.FileFletcher;
import rsoseven.lib.type.RSHeaders;

public class FileFletcher_test {

	@Test
	public void test() {
	try {
		new FileFletcher(new URL("http://oldschool.runescape.com/jav_config.ws"), RSHeaders.RS_2007_CONF);
		//new FileFletcher(new URL("http://oldschool18.runescape.com/gamepack_6884502.jar"), RSHeaders.RS_2007_JAR);
		
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
