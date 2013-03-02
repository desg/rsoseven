package rsoseven;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Dictionary;
import java.util.Hashtable;

import rsoseven.lib.io.ClientWriter;
import rsoseven.lib.net.FileFletcher;
import rsoseven.lib.type.ClientConfig;
import rsoseven.lib.type.RSHeaders;

public class RsOSeven implements AppletStub{
	
	private String jarName;
	private URL codeBase;
	private String className;
	private Dictionary<String, String> params = new Hashtable<String, String>();
	private Applet applet;


	public RsOSeven() throws MalformedURLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		
		FileFletcher a = new FileFletcher(new URL("http://oldschool.runescape.com/jav_config.ws"), RSHeaders.RS_2007_CONF);
		//^this for oldscape
		
		//FileFletcher a = new FileFletcher(new URL("http://www.runescape.com/k=3/jav_config.ws"), RSHeaders.RS_EOC_CONF);
		//this for EOC
		
		//TODO: write rsc command
		//^ this for RSC
		
		ClientConfig b = new ClientConfig(a.getData());
		FileFletcher c = new FileFletcher(new URL(b.getCodeBase()+b.getJarName()), RSHeaders.RS_2007_CONF);
		ClientWriter d = new ClientWriter(c.getData(), b.getJarName().replace("/", ""));
		
		jarName = b.getJarName().replace("/", "");
		codeBase= new URL(b.getCodeBase());
		className=b.getClassName();
		params = b.getParams();
		
		applet = (Applet) new URLClassLoader(new URL[]{new File("./"+jarName).toURI().toURL()}).loadClass(className.replace(".class", "")).newInstance();
		applet.setStub(this);
		applet.init();
		applet.start();
		//applet.preferredSize(new Dimention(763,504));
		
		
	}

	@Override
	public void appletResize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppletContext getAppletContext() {
		return null;
		//TODO: I don't know what the fuck that does
	}

	@Override
	public URL getCodeBase() {
		return this.codeBase;
	}

	@Override
	public URL getDocumentBase() {
		return this.codeBase;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return params.get(name);
	}

	@Override
	public boolean isActive() {
		return true;
	}

	public Component getApplet() {
		// TODO Auto-generated method stub
		return applet;
	}
}
