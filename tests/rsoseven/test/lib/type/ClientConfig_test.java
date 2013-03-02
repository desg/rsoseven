package rsoseven.test.lib.type;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import rsoseven.lib.net.FileFletcher;
import rsoseven.lib.type.ClientConfig;
import rsoseven.lib.type.RSHeaders;

public class ClientConfig_test {

	@Test
	public void test() {
		try {
			FileFletcher a = new FileFletcher(new URL("http://oldschool.runescape.com/jav_config.ws"), RSHeaders.RS_2007_CONF);
			new ClientConfig(a.getData());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
