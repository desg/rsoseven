package rsoseven.ui;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Dictionary;
import java.util.Hashtable;

import rsoseven.lib.net.FileFletcher;
import rsoseven.lib.type.ClientConfig;
import rsoseven.lib.type.RSHeaders;

public class RsAppletPanel implements AppletStub {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jarName;
	private URL codeBase;
	private String className;
	private Dictionary<String, String> params = new Hashtable<String, String>();
	private Applet applet;
	private URL jarpath;
	private URLClassLoader classloader;
	private int hight = 765;
	private int width = 503;

	public RsAppletPanel() throws MalformedURLException, IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, URISyntaxException {

		FileFletcher a = new FileFletcher(new URL(
				"http://oldschool.runescape.com/jav_config.ws"),
				RSHeaders.RS_2007_CONF);

		ClientConfig b = new ClientConfig(a.getData());

		jarName = b.getJarName();
		codeBase = new URL(b.getCodeBase());
		className = b.getClassName().replace(".class", "");
		params = b.getParams();

		jarpath = new URL(codeBase.toString() + jarName);

		
		classloader = new URLClassLoader(new URL[] { jarpath });

		applet = (Applet) classloader.loadClass(className).newInstance();

		// TODO: needs better way to handle this
		// classloader.close();
		// TODO: when client closes end with .close()
		applet.setStub(this);
		applet.init();
		applet.start();
	}

	
	//appletstub part
	@Override
	public void appletResize(int width, int height) {
		applet.resize(width, height);
	}

	@Override
	public AppletContext getAppletContext() {
		return null;
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
		return params.get(name);
	}

	@Override
	public boolean isActive() {
		return true;
	}

	public Component getApplet() {
		return applet;
	}

	public void CloseApplet() throws IOException {
		//yolo;
		
	}
	
	
}
