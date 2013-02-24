package net.sourceforge.jetris.gui.jetrisgrid;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import net.sourceforge.jetris.figure.Figure;
import net.sourceforge.jetris.figure.Move;
import net.sourceforge.jetris.figure.Position;
import net.sourceforge.jetris.geometry.Point;
import net.sourceforge.jetris.gui.utils.GridPanel;
import net.sourceforge.jetris.score.HiScore;

public class JetrisGrid extends GridPanel {
    
	// Constants
	private static final Color TETRIS_GRID_BACKGROUND_COLOR = Color.WHITE;
	private static final Border TETRIS_GRID_EMPTY_BORDER = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

    private static final String DAT_FILE = "JETRIS.DAT";
    private static final int CELL_H = 24;
  
    // Attributes
    private int lines;
    private int score;
    private int level;
    private HiScore[] hiScores;
    private Collection<JetrisGridEventsListener> listeners;
    
    // Constructors
    public JetrisGrid(final Point dimension) {
    	super(dimension, TETRIS_GRID_BACKGROUND_COLOR, TETRIS_GRID_EMPTY_BORDER);
    	setPreferredSize(new Dimension(10*CELL_H, 25*CELL_H));
    	
        lines = 0;
        score = 0;
        level = 1;
        
        try{
            hiScores = HiScore.load(DAT_FILE);
        } catch (Exception e) {
            hiScores = new HiScore[3];
            for (int i = 0; i < hiScores.length; i++) {
                hiScores[i] = new HiScore();
                hiScores[i].name = "<empty>";
            }
            File f = new File(DAT_FILE);
            try {
                HiScore.write(hiScores, f);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Could not load HiScore!", "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
        } 
        
        listeners = new ArrayList<JetrisGridEventsListener>();
    }
    
    // Methods
    public synchronized void moveEvent(Figure figure, Move move) {
    	if (isGameOver(figure, move)) {
    		moveFigure(figure, move);
    		notifyGameOver();
    	} else {
        	tryMoveFigure(figure, move);
    	}
    }
    
    private void tryMoveFigure(Figure figure, Move move) {
    	if (isNextMoveValid(figure, move)) {
    		moveFigure(figure, move);
    	} else {
    		checkEndFigureTurn(figure, move);
    	}
    }
    
    private void checkEndFigureTurn(Figure figure, Move move) {
    	if (isFigureTurnEnd(figure, move)) {
			checkDoneLines(figure);
			notifyNextFigure();
    	}
    }
    
    private boolean isFigureTurnEnd(Figure figure, Move move) {
		return move.isDownOrDrop() && !isNextMoveValid(figure, move);
    }
    
    private void checkDoneLines(Figure figure) {
    	int highestYInFigure = figure.getHighestY();
    	int doneLines = removeDoneLines(highestYInFigure);
    	if (doneLines>0) {
        	notifyDoneLines(doneLines);
    	}
    }

    
    private void moveFigure(final Figure figure, final Move move) {
    	if (isNextMoveValid(figure, move)) {
    		updateGUI(figure, move);
    		if (move.equals(Move.DROP)) {
    			moveFigure(figure, move);
    		}
    	}
    }
    
    private void updateGUI(Figure figure, Move move) {
        clearFigure(figure);
        figure.move(move);
        showFigure(figure);
    }
    
    private boolean isNextMoveValid(Figure figure, Move move) {
    	if (move.equals(Move.NEW_FIGURE)) {
    		return true;
    	}
    	Figure nextMoveFigure = figure.getMovedFigure(move);
    	return isValidPosition(nextMoveFigure, figure.getOffsetedPosition());
    }
    
    
    private int removeDoneLines(int yCoordinate) {
    	int doneLines = 0;
    	int minY = Math.max(yCoordinate-4, lowerLimit.getY());
    	assert minY>=0 : "minY is less than 0. yCoordinate is " + yCoordinate + 
    			         " -- yCoordinate - 4 is " + (yCoordinate - 4) + 
    			         " -- lowerLimit.getY() is " + lowerLimit.getY() +
    			         " -- minY is " + minY;
    	int currentY = yCoordinate;
    	while (currentY>=minY) {
        	if (isFullLine(currentY)) {
        		doneLines++;
            	removeLineAndDrop(currentY);
        	} else {
        		currentY--;
        	}
    	}
    	return doneLines;
    }
    
	private void removeLineAndDrop(int yCoordinate) {
		removeLine(yCoordinate);
		dropLinesAbove(yCoordinate);
	}
	
	private void removeLine(int yCoordinate) {
		int minX = lowerLimit.getX();
		int maxX = upperLimit.getX();
		for (int currentX = minX; currentX<=maxX; currentX++) {
			Point currentPoint = new Point(currentX, yCoordinate);
			updateGridPanel(currentPoint, backgroundColor, emptyBorder);
		}
	}
	
	private void dropLinesAbove(int yCoordinate) {
		for (int currentY=yCoordinate-1; currentY>lowerLimit.getY(); currentY--) {
			dropLine(currentY);
		}
		setEmptyLine(lowerLimit.getY());
	}

    
    
    private boolean isGameOver(Figure figure, Move move) {
    	return move.equals(Move.NEW_FIGURE) && !isValidPosition(figure.getOffsetedPosition());
    }
    
	private boolean isValidPosition(Figure figure, Position currentFigurePosition) {
		Position remainOffsetedPosition = removePointsFromCurrentFigure(figure, currentFigurePosition);
		return isValidPosition(remainOffsetedPosition);
	}
	
	private boolean isValidPosition(Position position) {
		boolean isValidPosition = true;
		if (position.isEmpty()) {
			isValidPosition = false;
		}  
		for (Point point : position.getPoints()) {
			if (!isValidPoint(point)) {
				isValidPosition = false;
				break;
			}
		}
		return isValidPosition;
	}

	private Position removePointsFromCurrentFigure(Figure figure, Position currentFigurePosition) {
		Position offsetedPosition = figure.getOffsetedPosition();
		Position remainOffsetedPosition = offsetedPosition.removePointsFrom(currentFigurePosition);
		return remainOffsetedPosition;
	}
    
    public int getLevel() {
    	return level;
    }
    
    public int getLines() {
    	return lines;
    }
    
    public int getScore() {
    	return score;
    }
    
    public void resetStats() {
        lines = 0;
        score = 0;
        level = 1;
    }
    
    public int updateHiScore() {
        for (int i = 0; i < hiScores.length; i++) {
            HiScore s = hiScores[i];
            if((s.score < score) || 
              ((s.score == score) && (s.lines >= lines))) {
                //Stack the HiScore
                switch (i) {
                case 0:
                    s = hiScores[1];
                    hiScores[1] = hiScores[0];
                    hiScores[2] = s;
                    s = new HiScore();
                    hiScores[0] = s;
                    break;
                case 1:
                    hiScores[2] = s;
                    s = new HiScore();
                    hiScores[1] = s;
                    break;
                };
                s.score = score;
                s.lines = lines;
                return i;
            } 
        }
        return -1;
    }
    
    public HiScore[] getHiScores()
    {
    	return hiScores;
    }

    void saveHiScore(String Name, int pos) {
        File f = new File(DAT_FILE);
        try {
            hiScores[pos].name = Name;
            HiScore.write(hiScores, f);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Could not save HiScore!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    
    // listeners methods
    public void subscribeEvents(JetrisGridEventsListener listener) {
    	listeners.add(listener);
    }
    
    public void unsubscribeEvents(JetrisGridEventsListener listener) {
    	listeners.remove(listener);
    }
    
    public void notifyDoneLines(int doneLines) {
    	for (JetrisGridEventsListener listener : listeners) {
    		listener.doneLinesEvent(doneLines);
    	}
    }
    
    public void notifyGameOver() {
    	for (JetrisGridEventsListener listener : listeners) {
    		listener.gameOverEvent();
    	}
    }
    
    public void notifyNextFigure() {
    	for (JetrisGridEventsListener listener : listeners) {
    		listener.nextFigureEvent();
    	}
    }
    
}
