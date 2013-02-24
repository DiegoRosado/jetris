package net.sourceforge.jetris.gui.panel.statistics;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class StatisticsPanel extends JPanel {

	
	// Attributes
	private StatisticsFiguresPanel figuresPanel;
	private StatisticsLinesPanel linesPanel;
	
	
	
	// Constructors
	public StatisticsPanel(int maxFigures) {
		this.figuresPanel = new StatisticsFiguresPanel(maxFigures);
		this.linesPanel = new StatisticsLinesPanel();
		this.setLayout(createLayout());
		
		add(new JLabel("STATISTICS: "), "wrap");
		add(figuresPanel, "wrap");
		add(linesPanel, "wrap");
	}
	
	
	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout(
				"", // Layout contraints
				"[]", // Columns contraints
				"[][][]"); // Row contraints
		return layout;
	}

	
	// Methods
    public void addFigureCount(int figureId) {
    	figuresPanel.addFigureCount(figureId);
    }
    
    public void addLinesCount(int lines) {
    	linesPanel.addLinesCount(lines);
    }

}
