package net.sourceforge.jetris.timer;

import net.sourceforge.jetris.figure.Move;
import net.sourceforge.jetris.gui.JetrisMainFrame;

public class JetrisTimerThread extends Thread {

	// Constants
	private static final int AUTO_MOVE_DOWN_MAX_CYCLES = 20;
	private static final int CYCLE_SLEEP_TIME = 50;

	// Class attributes

	// Attributes
	private JetrisMainFrame mainFrame;
	private int cyclesCount;

	// Constructors
	public JetrisTimerThread(JetrisMainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.cyclesCount = 0;
	}
	
	// Methods
	@Override
	public void run() {
		
		while (!mainFrame.isGameOver()) {
			try {
				Thread.sleep(CYCLE_SLEEP_TIME);
				if (!mainFrame.isPause()) {
					cyclesCount++;

					// move down if needed
					int gravity = mainFrame.getLevel();
					if (cyclesCount>=AUTO_MOVE_DOWN_MAX_CYCLES / gravity) {
						cyclesCount = 0;
						mainFrame.moveEvent(Move.DOWN);
					}
				}
			} catch (InterruptedException e) {
				System.out.println("Interrupted exception");
			}
		}
	}
	
	public void restartAutoMoveDownCountdown() {
		cyclesCount = 0;
	}
	
	public void endAutoMoveDownCycle() {
		cyclesCount = AUTO_MOVE_DOWN_MAX_CYCLES;
	}

}

