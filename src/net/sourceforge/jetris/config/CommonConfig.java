package net.sourceforge.jetris.config;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;

import net.sourceforge.jetris.geometry.Point;
import net.sourceforge.jetris.utils.ImageUtils;

public class CommonConfig {

	// Constants
    private static final String APPLICATION_NAME = "JETRIS";
    private static final String RELEASE_VERSION = "Refactorized 0.1 (Work in progress)";
    private static final String APPLICATION_NAME_PLUS_RELEASE_VERSION = APPLICATION_NAME + " " + RELEASE_VERSION;

    private static final Random RANDOM = new Random();
    private static final Color DEFAULT_TETRIS_GRID_BACKGROUND_COLOR = Color.WHITE;
    private static final Color DEFAULT_INFO_PANEL_BACKGROUND_COLOR = new Color(238,238,238);

    private static final Point DEFAULT_TETRIS_GRID_DIMENSION = new Point(20,10);
    
    private static final Image jetrisImage = ImageUtils.loadImage("jetris.png");
    private static final Image jetrisIcon = ImageUtils.loadImage("jetris16x16.png");
    
    private static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);

	// Inner SingletonHolder
	private static class SingletonHolder {
		private static final CommonConfig INSTANCE = new CommonConfig();
	}
	
	// Attributes
	private Color infoPanelBackgroundColor;
	private Color tetrisGridBackgroundColor;
	private Point tetrisGridDimension;
	private Font font;
	
	// Constructors
	private CommonConfig() {
		infoPanelBackgroundColor = DEFAULT_INFO_PANEL_BACKGROUND_COLOR;
		tetrisGridBackgroundColor = DEFAULT_TETRIS_GRID_BACKGROUND_COLOR;
		tetrisGridDimension = DEFAULT_TETRIS_GRID_DIMENSION;
		font = DEFAULT_FONT;
	}
	
	// Methods
	public static CommonConfig getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public static Random getRandomGenerator() {
		return RANDOM;
	}
	
	public Color getStatisticsPanelBackgroundColor() {
		return infoPanelBackgroundColor;
	}

	public Color getTetrisGridBackgroundColor() {
		return tetrisGridBackgroundColor;
	}

	public String getFullApplicationName() {
		return APPLICATION_NAME_PLUS_RELEASE_VERSION;
	}
	
	public Point getTetrisGridDimesion() {
		return tetrisGridDimension;
	}
	
	public Image getJetrisImage() {
		return jetrisImage;
	}
	
	public Image getJetrisIcon() {
		return jetrisIcon;
	}
	
	public Font getFont() {
		return font;
	}
	
}
