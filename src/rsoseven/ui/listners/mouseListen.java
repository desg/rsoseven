package rsoseven.ui.listners;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseListener;

import rsoseven.ui.MainFrame;
import tldr.plugins.screenshot.GetName;

public class mouseListen implements NativeMouseListener{
	private MainFrame root;
	
	public mouseListen(MainFrame mainFrame) {
		this.root = mainFrame;
	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		//System.out.println("mouse pressed");
		//System.out.println(arg0.getButton());
		// TODO Auto-generated method stub
		if(root.getFrame().isActive() && root.getFrame().isShowing() && root.getFrame().isFocused() && (arg0.getButton()==MouseEvent.BUTTON2) ){
			//System.out.println("pressed");
			GetName a = new GetName(root, arg0.getPoint());
			a.start();
		}
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
