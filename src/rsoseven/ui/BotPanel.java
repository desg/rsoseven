package rsoseven.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class BotPanel extends JPanel {
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D)g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);


	    Font font = new Font("Monospaced", Font.BOLD, 16);
	    g2.setFont(font);
	    g2.setColor(Color.white);

	    g2.drawString("Must.... kill.... jagex.... antibot... squad.... ",3,14 );
	    
	  }
}
