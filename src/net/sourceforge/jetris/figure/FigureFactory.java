package net.sourceforge.jetris.figure;
import java.util.Random;

import net.sourceforge.jetris.config.CommonConfig;


public class FigureFactory {
    
	// Constants
    private static final Random RANDOM = CommonConfig.getRandomGenerator();
    
    // Class Attributes
    // TODO change this int[] counts for Map or something
    private static int[] counts = createCountsArray();

	// Attributes

    // Constructors
    private FigureFactory() {
    	// Factory class
    }
    
    // Methods
    public static Figure getRandomFigure() {
    	Figure figure = getFigure(RANDOM.nextInt(Figure.MAX_FIGURE_ID+1));
    	figure.rotateRandomly();
    	return figure;
    }
    
    public static Figure getFigure(int figureId) {
    	Figure figure = null;
    	switch (figureId) {
    		case Figure.FIGURE_ID_I:
    			figure = new FigureI();
    			break;
    		case Figure.FIGURE_ID_T:
    			figure = new FigureT();
    			break;
    		case Figure.FIGURE_ID_O:
    			figure = new FigureO();
    			break;
    		case Figure.FIGURE_ID_L:
    			figure = new FigureL();
    			break;
    		case Figure.FIGURE_ID_J:
    			figure = new FigureJ();
    			break;
    		case Figure.FIGURE_ID_S:
    			figure = new FigureS();
    			break;
    		case Figure.FIGURE_ID_Z:
    			figure = new FigureZ();
    			break;
    		default:
    			figure = null;
    			break;
    	}
    	return figure;
    }
    
    public static String getNameFromId(int figureId) {
    	String figureName = null;
    	switch (figureId) {
		case Figure.FIGURE_ID_I:
			figureName = "FigureI";
			break;
		case Figure.FIGURE_ID_T:
			figureName = "FigureT";
			break;
		case Figure.FIGURE_ID_O:
			figureName = "FigureO";
			break;
		case Figure.FIGURE_ID_L:
			figureName = "FigureL";
			break;
		case Figure.FIGURE_ID_J:
			figureName = "FigureJ";
			break;
		case Figure.FIGURE_ID_S:
			figureName = "FigureS";
			break;
		case Figure.FIGURE_ID_Z:
			figureName = "FigureZ";
			break;
		default:
			figureName = null;
			break;
    	}
    	return figureName;
    }
    
    public static int[] getCounts() {
        return counts;
    }
     
    public static void resetCounts() {
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
    }
    
    private static int[] createCountsArray() {
    	int[] counts = new int[Figure.MAX_FIGURE_ID + 1];
    	for (int i=0; i<counts.length; i++) {
    		counts[i] = 0;
    	}
    	return counts;
    }
    

}
