package rsoseven.lib.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import rsoseven.lib.type.RSHeaders;

public class FileFletcher {
	/* Class used to fletch data from runescape.com server
	 * 
	 * Fletch data while leaving the exact same web client signature as the Jagex Client
	 * 
	 */
	private URL fileURL;
	private RSHeaders header;
	private byte[] temp;
	
	public FileFletcher(URL fileURL, RSHeaders header) throws IOException{
		this.fileURL=fileURL;
		this.header=header;
		URLConnection c = fileURL.openConnection();
		c.setConnectTimeout(20000);
		Enumeration<String>e=header.getHeaders().keys();
		c.setUseCaches(true);
		
		if (header.getHeaders().get("accept-encoding")!=null){
			c.addRequestProperty("accept-encoding",header.getHeaders().get("accept-encoding"));
		}
		while (e.hasMoreElements()){
			String key = e.nextElement();
			if (key.equalsIgnoreCase("accept-encoding")){
				
			} 
			else {
			c.addRequestProperty(key, header.getHeaders().get(key));
			}
		}
		
		DataInputStream httpInputstream = new DataInputStream(c.getInputStream());
		
		
		this.temp = new byte[c.getContentLength()];
		
		httpInputstream.readFully(this.temp);
		httpInputstream.close();
		//close here
	}
	
	public byte [] getData(){
		return this.temp;
	}
	
}
