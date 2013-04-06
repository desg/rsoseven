package tldr.plugins.screenshot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import rsoseven.ui.MainFrame;

public class Grabber extends Thread {

	private BufferedImage capture;
	private MainFrame root;
	private Rectangle reqt;

	public Grabber(int xp, int yp, int xs, int ys, MainFrame root)
			throws AWTException, IOException {
		this.root = root;
		this.reqt = new Rectangle(xp, yp, xs, ys);
		// this.capture = root.robot.createScreenCapture(new Rectangle(xp, yp,
		// xs, ys));
	}

	@Override
	public void run() {
		this.capture = root.robot.createScreenCapture(reqt);
		String homeDir = System.getProperty("user.home");
		String s = File.separator;

		File imagedic = new File(homeDir + s + "EthoClient");

		if (!imagedic.exists()) {
			imagedic.mkdir();
		}

		String ImagePath = imagedic.getPath() + s
				+ String.valueOf(System.currentTimeMillis()) + ".png";
		File outputfile = new File(ImagePath);
		try {
			ImageIO.write(capture, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// FileInputStream fileInputStream = new FileInputStream(new
		// File(ImagePath));
		// URL uploadURL= new URL("http://tldr.me/index.php?act=uploader");

		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		HttpPost httppost = new HttpPost(
				"http://tldr.me/index.php?act=uploader&display=link");
		httppost.setHeader("User-Agent", "fireFOEX");

		File file = outputfile;

		MultipartEntity mpEntity = new MultipartEntity();
		ContentBody cbFile = new FileBody(file, "image/png");
		mpEntity.addPart("Filedata", cbFile);

		httppost.setEntity(mpEntity);
		// System.out.println("executing request " + httppost.getRequestLine());
		HttpResponse response;
		try {
			response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();

			// System.out.println(response.getStatusLine());
			if (resEntity != null) {
				// System.out.println(EntityUtils.toString(resEntity));
				StringSelection selection = new StringSelection(
						EntityUtils.toString(resEntity));
				Clipboard clipboard = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				clipboard.setContents(selection, selection);
				root.message("Image uploaded to tldr.me URL added to clipboard press CTRL+V on our forums or IRC to view");

			}
			if (resEntity != null) {
				resEntity.consumeContent();
			}

			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
