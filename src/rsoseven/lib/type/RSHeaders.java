package rsoseven.lib.type;

import java.util.Dictionary;
import java.util.Hashtable;

public enum RSHeaders{
	RS_2007_CONF("Java/1.7.0-internal","oldschool.runescape.com","text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2","keep-alive"),
	RS_EOC_CONF("Java/1.7.0-internal","www.runescape.com","text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2","keep-alive"),
	RS_2007_JAR("pack200-gzip","Java/1.7.0-internal","oldschool.runescape.com","text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2","keep-alive"),
	RS_EOC_JAR("pack200-gzip","Java/1.7.0-internal","www.runescape.com","text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2","keep-alive");
	
	private final Dictionary<String, String> headers = new Hashtable<String, String>();
	
	RSHeaders(String userAgent,String host,String accept,String connection){
		headers.put("User-Agent", userAgent );
		headers.put("Host", host);
		headers.put("Accept", accept);
		headers.put("Connection", connection);
	}
	RSHeaders(String acceptencoding,String userAgent,String host,String accept,String connection){
		headers.put("accept-encoding", acceptencoding);
		headers.put("User-Agent", userAgent );
		headers.put("Host", host);
		headers.put("Accept", accept);
		headers.put("Connection", connection);
	}
	public final Dictionary<String, String> getHeaders() {
		return headers;
		
	}
}