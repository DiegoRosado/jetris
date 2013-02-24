package net.sourceforge.jetris.gui.splashscreen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

import net.sourceforge.jetris.utils.ImageUtils;


public class SplashScreen extends JWindow {
    
    private static final int W = 567;
    private static final int H = 191;
    
    public SplashScreen() {
        getContentPane().add(new JLabel(
                new ImageIcon(ImageUtils.loadImage("splash.png"))), 
                BorderLayout.CENTER);
        setSize(new Dimension(W,H));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2 - W/2, screenSize.height/2 - H/2);
        setVisible(true);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        } finally {
        	setVisible(false);
        }
    }
}
