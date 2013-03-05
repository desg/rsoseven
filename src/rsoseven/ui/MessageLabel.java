package rsoseven.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MessageLabel extends JLabel{
	private String message;
	
	public MessageLabel(ImageIcon userMessageBackground){
		super(userMessageBackground);
		message="uwotmate?";
		//this.add(new JLabel("HELLO"));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D)g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);


	    Font font = new Font("Monospaced", Font.BOLD, 16);
	    g2.setFont(font);
	    g2.setColor(Color.white);

	    g2.drawString(message,3,14 );
	    
	  }
	
	
	public void setMessage(String message){
		this.message= message;
		this.repaint();
	}

	public void setClear(){
		this.message="";
		this.repaint();
	}
}
