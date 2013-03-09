package rsoseven.lib.type;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MessageList extends ArrayList<Message> {
	
	private int max;

	public MessageList(int i) {
		max=i;
	}
	
	@Override
	public boolean add(Message e) {
		
		if (this.size() >max){
			this.remove(0);
			this.trimToSize();
		} else {
			
		}
		return super.add(e);
		
	}
	
	public void write(Graphics2D g2,int initialX,int initialY){
		int X = initialX;
		int Y = initialY;
		for (Message i:this){
			i.DrawMessage(g2, X, Y);
			Y+=9;
		}
	}
	
}