package net.sourceforge.jetris.gui.panel.statistics;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jetris.figure.FigureFactory;

public class StatisticsFiguresPanel extends JPanel {
	
	// Attributes
	private StatisticsInternalFigurePanel[] figurePanelArray;
	
	
	// Constructors
	                              // MAX_FIGURE_ID 
	public StatisticsFiguresPanel(int maxFigures) {
		this.setLayout(createLayout());
		figurePanelArray = new StatisticsInternalFigurePanel[maxFigures+1];
		for (int figureId=0; figureId<=maxFigures; figureId++) {
			figurePanelArray[figureId] = new StatisticsInternalFigurePanel(FigureFactory.getFigure(figureId));
			add(figurePanelArray[figureId], "wrap");
		}
	}
	
	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout(
				"", // Layout contraints
				"[]", // Columns contraints
				"[]10[]0[]0[]0[]0[]0[]"); // Row contraints
		return layout;
	}
	
	// Methods
	public void addFigureCount(int figureId) {
		figurePanelArray[figureId].addFigureCount();
	}
	
}
