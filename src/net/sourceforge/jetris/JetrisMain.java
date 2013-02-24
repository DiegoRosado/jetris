package net.sourceforge.jetris;
import javax.swing.SwingUtilities;

import net.sourceforge.jetris.gui.JetrisMainFrame;
import net.sourceforge.jetris.gui.splashscreen.SplashScreen;

public class JetrisMain {
	
    public static void main(String[] args) {
        SplashScreen sp = new SplashScreen();
        sp.dispose();
        JetrisMainFrame jetrisMainFrame = new JetrisMainFrame();
        SwingUtilities.invokeLater(jetrisMainFrame);
    }

}
