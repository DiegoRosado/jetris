package net.sourceforge.jetris.gui.panel.info;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jetris.figure.Figure;
import net.sourceforge.jetris.gui.JetrisMainFrame;

public class InfoPanel extends JPanel {

	// Constants

	// Class attributes

	// Attributes
	private JetrisMainFrame jetrisMainFrame;
	private final NextFigurePanel nextFigurePanel;
	private final GameInfoPanel gameInfoPanel;
	private final MovesInfoPanel movesInfoPanel;

	// Constructors
	public InfoPanel(JetrisMainFrame jetrisMainFrame) {
		this.jetrisMainFrame = jetrisMainFrame;
		this.nextFigurePanel = new NextFigurePanel();
		this.gameInfoPanel = new GameInfoPanel();
		this.movesInfoPanel = new MovesInfoPanel();
		
		setLayout(createLayout());
		add(nextFigurePanel, "dock north");
		add(gameInfoPanel, "dock center");
		add(movesInfoPanel, "dock south");
	}

	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout(
				"", // Layout contraints
				"[]", // Columns contraints
				"[][][]"); // Row contraints
		return layout;
	}
	
	// Methods
	public void nextFigureEvent(Figure nextFigure) {
		nextFigurePanel.setNextFigure(nextFigure);
	}
	
	public void addLinesCount(int linesCount) {
		gameInfoPanel.addLinesCount(linesCount);
	}
	
	public int getLevel() {
		return gameInfoPanel.getLevel();
	}
}

