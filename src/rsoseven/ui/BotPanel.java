package rsoseven.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BotPanel extends JPanel {

	private BufferedImage bimg;
	private MainFrame frame;

	public BotPanel(MainFrame frame) throws IOException {
		bimg = ImageIO.read(getClass().getClassLoader().getResource(
				"res/chat.png"));
		this.frame = frame;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.drawImage(bimg, 0, 0, null);
		Font font = new Font("Courier", Font.BOLD, 8);
		g2.setFont(font);
		g2.setColor(Color.black);
		frame.getMessageList().write(g2, 10, 15);
	}
}
