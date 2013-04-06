package rsoseven.ui.listners;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import rsoseven.lib.type.Message;
import rsoseven.ui.MainFrame;
import tldr.plugins.screenshot.Grabber;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class KeyShortcutReader implements NativeKeyListener {

	private static MainFrame mainFrame;
	private static Clip clip;
	private static JFrame frame;
	private KeyShortcut shortcut;

	public KeyShortcutReader(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;

	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if ((shortcut = KeyShortcut.get(e)) != null)
			shortcut.evaluate(e);
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {

	}

	private enum KeyShortcut {
		TOGGLE_CHAT(NativeKeyEvent.VK_C) {
			@Override
			public void perform() {
				if (mainFrame.getBot().isVisible()) {
					// FIXME: when you mod a it changes the value of actual
					// screensize
					// because it points to eachother-
					mainFrame.hideBot();

				} else {
					mainFrame.showBot();
				}
			}
		},
		CLOSE_GAME(NativeKeyEvent.VK_X) {
			@Override
			public void perform() {
				JFrame root = mainFrame.getFrame();
				if (0 == JOptionPane.showConfirmDialog(root,
						"Are you sure you want to close the client?")) {
					GlobalScreen.unregisterNativeHook();
					root.dispose();
					System.exit(0);
				} else {
					// JOptionPane.showMessageDialog(root, "");
				}
			}
		},
		VIEW_HELP(NativeKeyEvent.VK_Q) {
			@Override
			public void perform() {
				mainFrame.message("", Message.ALERT);
				mainFrame.message("", Message.ALERT);
				mainFrame.message(
						"################### CLIENT HELP ###################",
						Message.ALERT);
				mainFrame.message(
						"CTRL+C: Toggle Chat    CTRL+T: Toggle Always On Top",
						Message.ALERT);
				mainFrame.message(
						"CTRL+X: Close Game     CTRL+L: Toggle 1337 Mode",
						Message.ALERT);
				mainFrame.message(
						"CTRL+Q: View Help      CTRL+N: Nuke North Korea",
						Message.ALERT);
				mainFrame.message(
						"CTRL+M: View Map       PRTSCR: Print screen & upload",
						Message.ALERT);
				mainFrame.message(
						"################### CLIENT HELP ###################",
						Message.ALERT);
			}
		},
		VIEW_MAP(NativeKeyEvent.VK_M) {
			@Override
			public void perform() {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop
						.getDesktop() : null;
				if (desktop != null
						&& desktop.isSupported(Desktop.Action.BROWSE)) {
					try {
						mainFrame
								.message(
										"Opening the map... To your mom's bedroom EAYHOO",
										Message.INFO);
						desktop.browse(new URL(
								"http://www.runescape.com/img/rsp777/gamewin/runescape-map-24-july-07.jpg")
								.toURI());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		},
		ALWAYS_ON_TOP(NativeKeyEvent.VK_T) {
			@Override
			public void perform() {
				mainFrame.getFrame().setAlwaysOnTop(
						!mainFrame.getFrame().isAlwaysOnTop());
				mainFrame
						.message(
								"To toggle AlwaysOnTop press CTRL+T. PS: Your mom is always on top kid.",
								Message.INFO);
			}
		},
		LEET_MODE(NativeKeyEvent.VK_L) {
			@Override
			public void perform() {
				if (clip != null) {
					if (clip.isRunning()) {
						clip.stop();
						mainFrame
								.message(
										"You gone already? Too bad for you, more Nyan for me",
										Message.INFO);
					} else {
						mainFrame.message("Never stop Nyanning", Message.INFO);
						clip.loop(Clip.LOOP_CONTINUOUSLY);
					}
				} else {
					URL url;
					try {
						mainFrame.message("1337 Mode Active Scrubs stand back",
								Message.INFO);
						url = getClass().getClassLoader().getResource(
								"res/nyan.mid");
						AudioInputStream audioIn = AudioSystem
								.getAudioInputStream(url);
						clip = AudioSystem.getClip();
						// Open audio clip and load samples from the audio input
						// stream.
						clip.open(audioIn);
						clip.loop(Clip.LOOP_CONTINUOUSLY);

					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// this.getClass().getClassLoader().getResource("res/nyan.mid");
					catch (UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		},
		NUKE_N_K(NativeKeyEvent.VK_N) {
			@Override
			public void perform() {
				mainFrame
						.message(
								"ICBM USS Missouri (SSN-780) set for 39.0333° N, 125.7500° E",
								Message.ALERT);
				mainFrame
						.message(
								"Error 0xDEFEC8ED: Gold Sequence missmatch, CORE DUMPED!",
								Message.ALERT);
			}
		},
		PRINT_SCREEN(NativeKeyEvent.VK_PRINTSCREEN) {
			@Override
			public void perform() {
				mainFrame
						.message("Screenshot Saved to HOME, uploading now... PLEASE WAIT");
				JFrame frame = mainFrame.getFrame();
				try {
					Thread grabthread = new Grabber(frame.getX()
							+ (frame.getWidth() - frame.getContentPane()
									.getWidth()) / 2, frame.getY()
							+ (frame.getHeight() - frame.getContentPane()
									.getHeight())
							- (frame.getWidth() - frame.getContentPane()
									.getWidth()) / 2, frame.getContentPane()
							.getWidth(), frame.getContentPane().getHeight(),
							mainFrame);
					grabthread.start();

				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		private int[] keycodes;

		KeyShortcut(int... keycodes) {
			this.keycodes = keycodes;
		}

		public static KeyShortcut get(NativeKeyEvent e) {
			for (KeyShortcut k : values())
				if (k.isReady(e))
					return k;
			return null;
		}

		private boolean containsKey(int needle) {
			for (int i : keycodes)
				if (i == needle)
					return true;
			return false;
		}

		private boolean isReady(NativeKeyEvent e) {
			return (this.equals(KeyShortcut.PRINT_SCREEN) || (e.getModifiers() & NativeInputEvent.CTRL_MASK) != 0)
					&& containsKey(e.getKeyCode())
					&& mainFrame.getFrame().isActive();
		}

		public void evaluate(NativeKeyEvent e) {
			if (isReady(e))
				perform();
		}

		public abstract void perform();
	}

}