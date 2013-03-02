package rsoseven.test.lib.type;
import static org.junit.Assert.*;

import java.util.Dictionary;
import java.util.Hashtable;


import org.junit.Test;

import rsoseven.lib.type.RSHeaders;


public class RSHeaders_Test {

	@Test
	public void CheckValues() {
		RSHeaders a = RSHeaders.RS_2007_CONF;
		Dictionary<String, String> b = new Hashtable<String,String>();
		b.put("User-Agent", "Java/1.7.0-internal" );
		b.put("Host", "oldschool.runescape.com");
		b.put("Accept", "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
		b.put("Connection", "keep-alive");
		assertEquals(a.getHeaders(),b);
		
		
		b.remove("Host");
		b.put("Host", "oldschool.runescape.com");
		assertNotSame(a, b);
		
		assertNotSame(RSHeaders.RS_2007_CONF,RSHeaders.RS_2007_JAR);
		assertNotSame(RSHeaders.RS_2007_CONF.getHeaders(),RSHeaders.RS_2007_JAR.getHeaders());
		
		
		assertNotSame(RSHeaders.RS_EOC_CONF,RSHeaders.RS_EOC_JAR);
		assertNotSame(RSHeaders.RS_EOC_CONF.getHeaders(),RSHeaders.RS_EOC_JAR.getHeaders());
	}

}
