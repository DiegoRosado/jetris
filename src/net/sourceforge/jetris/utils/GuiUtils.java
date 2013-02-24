package net.sourceforge.jetris.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class GuiUtils {

	// Constants

	// Constructors
	private GuiUtils() {
		// Utility Class
	}

	// Methods
	public static Point getInitialFrameLocation(Component frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2 - frame.getWidth() / 2;
        int height = screenSize.height / 2 - frame.getHeight() / 2;
        return new Point(width, height);
	}

}

