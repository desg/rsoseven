package rsoseven.lib.type;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientConfig {

	private String configText;

	private String codeBase;
	private String jarName;
	private String className;
	private Dictionary<String, String> params = new Hashtable<String, String>();

	private static Pattern codeBaseRex = Pattern.compile("^codebase=(.*)$",
			Pattern.MULTILINE);
	private static Pattern jarNameRex = Pattern.compile("^initial_jar=(.*)$",
			Pattern.MULTILINE);
	private static Pattern classNameRex = Pattern.compile(
			"^initial_class=(.*\\.class)$", Pattern.MULTILINE);
	private static Pattern paramsRex = Pattern.compile("^param=([0-9]+)=(.*)$",
			Pattern.MULTILINE);

	public ClientConfig(byte[] configbuffer) {
		this.configText = new String(configbuffer);
		// I'm going in dry
		// System.out.println(configText);
		// ^that is printed below, i use that text to match my shit on (i don't
		// wanne load per line cus its multiline data)
		Matcher m1 = codeBaseRex.matcher(configText);
		Matcher m2 = jarNameRex.matcher(configText);
		Matcher m3 = classNameRex.matcher(configText);

		setCodeBase(m1.find() ? m1.group(1) : "fail1_");
		setJarName(m2.find() ? m2.group(1) : "fail2_");
		setClassName(m3.find() ? m3.group(1) : "fail3_");

		Matcher matcher = paramsRex.matcher(configText);
<<<<<<< HEAD

		while (matcher.find()) {
			if (matcher.group(2).equals(
					"http://www.runescape.com/slr.ws?order=LPWM")) {
				params.put(matcher.group(1),
						"http://tldr.me/etho/rs2007/slr.php");
			} else {
				params.put(matcher.group(1), matcher.group(2));

=======
		
		
		
		while (matcher.find()){
			if (matcher.group(2).equals("http://www.runescape.com/slr.ws?order=LPWM")){
				params.put(matcher.group(1), "http://tldr.me/etho/worlds/listworlds.php");
			}else {
				params.put(matcher.group(1),matcher.group(2));
					
>>>>>>> EOC keys, World flair, AutoMute, cleanup & docs
			}
			// System.out.println(matcher.group(2));
		}
	}

	public String getCodeBase() {
		return codeBase;
	}

	public void setCodeBase(String codeBase) {
		this.codeBase = codeBase;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getJarName() {
		return jarName;
	}

	public void setJarName(String jarName) {
		this.jarName = jarName;
	}

	public Dictionary<String, String> getParams() {
		return params;
	}

	public void setParams(Dictionary<String, String> params) {
		this.params = params;
	}
}
