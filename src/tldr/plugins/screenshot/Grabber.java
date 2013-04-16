package tldr.plugins.screenshot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public Grabber(int xp, int yp, int xs, int ys, MainFrame root) throws AWTException, IOException {
		this.root=root;
		BufferedImage old  = new Robot().createScreenCapture(new Rectangle(xp, yp, xs, ys));
	        int w = old.getWidth();
	        int h = old.getHeight();
	        BufferedImage img = new BufferedImage(
	            w, h, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = img.createGraphics();
	        g2d.drawImage(old, 0, 0, null);
	        BufferedImage timestamp = ImageIO.read(getClass().getClassLoader().getResource("res/timestamp.png"));
	        BufferedImage mmIcon = ImageIO.read(getClass().getClassLoader().getResource("res/mmicon.png"));

	        //410 480
	        //716 0
	        g2d.drawImage(timestamp,410,480,null);
	        g2d.drawImage(mmIcon,716,0,null);
	        
	        g2d.setPaint(Color.WHITE);
	        g2d.setFont(new Font("Courier", Font.BOLD, 10));
	        Date now = new Date();
	        String s =  new SimpleDateFormat("EEE, d MMM yyyy").format(now);
	        String t= new SimpleDateFormat("HH:mm:ss Z").format(now);;
	        
	        FontMetrics fm = g2d.getFontMetrics();
	        
	        g2d.drawString(s, 413, 488);
	        g2d.drawString(t, 413 , 500);
	        g2d.dispose();
	        capture=img;
	}
	
	@Override
	public void run(){
		String homeDir = System.getProperty("user.home");
		String s = File.separator;
		
		
		File imagedic = new File(homeDir+s+"EthoClient");
		
		if (!imagedic.exists()){
			imagedic.mkdir();
		}
		
		String ImagePath = imagedic.getPath()+s+String.valueOf(System.currentTimeMillis())+".png";
		File outputfile = new File(ImagePath);
		try {
			ImageIO.write(capture, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //FileInputStream fileInputStream = new FileInputStream(new File(ImagePath));
        //URL uploadURL= new URL("http://tldr.me/index.php?act=uploader");
        	    
	    
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

        HttpPost httppost = new HttpPost("http://tldr.me/index.php?act=uploader&display=link");
        httppost.setHeader("User-Agent", "fireFOEX");
        
        File file = outputfile;

        MultipartEntity mpEntity = new MultipartEntity();
        ContentBody cbFile = new FileBody(file, "image/png");
        mpEntity.addPart("Filedata", cbFile);

        

        httppost.setEntity(mpEntity);
        //System.out.println("executing request " + httppost.getRequestLine());
        HttpResponse response;
		try {
			response = httpclient.execute(httppost);
	        HttpEntity resEntity = response.getEntity();

	        //System.out.println(response.getStatusLine());
	        if (resEntity != null) {
	          //System.out.println(EntityUtils.toString(resEntity));
	    	    StringSelection selection = new StringSelection(EntityUtils.toString(resEntity));
	    	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    	    clipboard.setContents(selection, selection);
				root.message("Image uploaded to http://tldr.me/ URL added to clipboard.");
				root.message("Press CTRL+V in a textfield outside this client to view");
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
