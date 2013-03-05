package rsoseven.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GhostDrawPanel extends JPanel{
	private String message;
	
	public GhostDrawPanel(){
		message="uwotmate?";
		//this.add(new JLabel("HELLO"));
	}
	
	@Override
	public void paintComponent(Graphics g){
		//super.paint(g);
		g.drawString(message, 100, 100);
		//System.out.println("repainted!");
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
