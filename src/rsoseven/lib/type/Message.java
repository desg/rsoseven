package rsoseven.lib.type;

import java.awt.Color;
import java.awt.Graphics2D;

public class Message {
	public static Color INFO  = Color.blue;
	public static Color ALERT = Color.red;
	public static Color CHAT  = Color.black;
	public static Color LEGAL = Color.gray;
	
	private String message;
	private Color color;
	
	public Message(String message,Color color){
		this.message=message;
		this.color = color;
	}
	
	public void DrawMessage(Graphics2D g2,int X,int Y){
		g2.setColor(this.color);
		g2.drawString(this.message, X, Y);
	}
	
	@Override
	public String toString(){
		return this.message;
	}

	public Color getColor() {
		return color;
	}
	
}
