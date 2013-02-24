package net.sourceforge.jetris.utils;

import java.awt.Image;
import java.io.BufferedInputStream;

import javax.imageio.ImageIO;

import res.ResClass;

public class ImageUtils {

	// Constants

	// Class attributes

	// Attributes

	// Constructors
	private ImageUtils() {
		// No contructors. Utils Class
	}

	// Methods
    public static Image loadImage(String imageName) {
        try {
            Image im = ImageIO.read(new BufferedInputStream(
                    ResClass.class.getResourceAsStream(imageName)));
            return im;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

}

