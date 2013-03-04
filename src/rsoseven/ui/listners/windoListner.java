package rsoseven.ui.listners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jnativehook.GlobalScreen;

public class windoListner implements WindowListener {

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		//e.getWindow().dispose();
		JFrame root = (JFrame)e.getSource();
		if (0==JOptionPane.showConfirmDialog((JFrame)e.getSource(), "You sure you wanne close?")){
			GlobalScreen.unregisterNativeHook();
			root.dispose();
			System.exit(0);
		} else {
			JOptionPane.showMessageDialog(root, "BE MORE CAREFULL NEXT TIME DUMMY!!");
		}

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
