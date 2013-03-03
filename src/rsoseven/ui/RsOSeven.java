package rsoseven.ui;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.net.URLStreamHandlerFactory;
import java.util.Dictionary;
import java.util.Hashtable;

import rsoseven.lib.io.ClientWriter;
import rsoseven.lib.net.FileFletcher;
import rsoseven.lib.type.ClientConfig;
import rsoseven.lib.type.RSHeaders;
import rtsoseven.test.lib.net.FileFletcher_test;

public class RsOSeven implements AppletStub {

	private String jarName;
	private URL codeBase;
	private String className;
	private Dictionary<String, String> params = new Hashtable<String, String>();
	private Applet applet;
	private URL jarpath;
	private URLClassLoader classloader;

	public RsOSeven() throws MalformedURLException, IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, URISyntaxException {

		FileFletcher a = new FileFletcher(new URL(
				"http://oldschool.runescape.com/jav_config.ws"),
				RSHeaders.RS_2007_CONF);
		// ^this for oldscape

		// FileFletcher a = new FileFletcher(new
		// URL("http://www.runescape.com/k=3/jav_config.ws"),
		// RSHeaders.RS_EOC_CONF);
		// ^this for EOC

		// TODO: write rsc command
		// ^this for RSC

		ClientConfig b = new ClientConfig(a.getData());

		jarName = b.getJarName();
		codeBase = new URL(b.getCodeBase());
		className = b.getClassName().replace(".class", "");
		params = b.getParams();

		jarpath = new URL(codeBase.toString() + jarName);

		
		classloader = new URLClassLoader(new URL[] { jarpath });
		
		
		/*JarURLConnection cl = (JarURLConnection) new URL("jar:"+codeBase.toString()+jarName+"!/").openConnection();
		cl.setRequestProperty("accept-encoding", "pack200-gzip");
		cl.setRequestProperty("User-Agent", "Java/1.7.0-internal");
		cl.setRequestProperty("Host", "www.runescape.com");
		cl.setRequestProperty("Accept",
				"text/html, image/gif, image/jpeg, *; q=.2, *//*; q=.2");
		cl.setRequestProperty("Connection", "keep-alive");
		 * thisLoader = AppletClassLoader.newInstance(new URL[] {
		 * clientConnection .getJarFileURL() });
		 * 
		 * clientApplet = (Applet) (thisLoader.loadClass(parseArg(
		 * search(jsInfoPage, codeRegex, 1)).split("\\.")[0]) .newInstance());
		 * 
		 * 
		 * Shit for using compressed client
		*/
		
		//classloader = new URLClassLoader(new URL[] {cl.getJarFileURL()});
		
		
		applet = (Applet) classloader.loadClass(className).newInstance();

		// TODO: needs better way to handle this
		// classloader.close();
		// TODO: when client closes end with .close()

		applet.setStub(this);
		applet.init();
		applet.start();
		applet.setFocusable(true);
		
	}

	@Override
	public void appletResize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public AppletContext getAppletContext() {
		return null;
		// TODO: I don't know what the fuck that does
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

	public void CloseApplet() throws IOException {
		classloader.close();
	}
}
