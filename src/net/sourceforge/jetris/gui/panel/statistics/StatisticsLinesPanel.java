package net.sourceforge.jetris.gui.panel.statistics;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class StatisticsLinesPanel extends JPanel {
	
	// Constants
	private static final int MAX_DONE_LINE_TYPES = 4;
	private static final String[] LINE_TYPE_LABELS = new String[] { "Single", "Double", "Triple", "Tetris" };

	// Attributes
	private StatisticsLinePanel[] statisticsLinePanel;
	
	// Constructors
	public StatisticsLinesPanel() {
		this.setLayout(createLayout());
		this.statisticsLinePanel = new StatisticsLinePanel[MAX_DONE_LINE_TYPES];
		for (int currentLineType=0; currentLineType<MAX_DONE_LINE_TYPES; currentLineType++) {
			String currentLineTypeLabel = LINE_TYPE_LABELS[currentLineType];
			StatisticsLinePanel currentStatisticsLinePanel = new StatisticsLinePanel(currentLineTypeLabel);
			statisticsLinePanel[currentLineType] = currentStatisticsLinePanel;
			add(currentStatisticsLinePanel, "wrap");
		}
	}
	
	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout(
				"", // Layout contraints
				"[]", // Columns contraints
				"0[]0[]0[]0[]0"); // Row contraints
		return layout;
	}
	
	// Methods
	public void addLinesCount(int lines) {
		statisticsLinePanel[lines-1].addLinesCount();
	}
}
