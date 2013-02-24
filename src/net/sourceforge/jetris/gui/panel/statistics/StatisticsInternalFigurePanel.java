package net.sourceforge.jetris.gui.panel.statistics;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jetris.figure.Figure;

public class StatisticsInternalFigurePanel extends JPanel {

	
	// Attributes
	private final FigurePanel figurePanel;
	private final JLabel xLabel;
	private final CountLabel countLabel;
	
	// Constructors
	public StatisticsInternalFigurePanel(Figure figure) {
		this.figurePanel = new FigurePanel(figure);
		this.xLabel = createXLabel();
		this.countLabel = new CountLabel();
		this.setLayout(createLayout());
		
		add(figurePanel);
		add(xLabel);
		add(countLabel);
	}
	
	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout(
				"", // Layout contraints
				"[]", // Columns contraints
				"0[]0[]0[]0"); // Rows contraints
		return layout;
	}
	
	// Methods
	public void addFigureCount() {
		countLabel.incrementCount();
	}
	
	private JLabel createXLabel() {
		JLabel timesLabel = new JLabel("x");
		return timesLabel;
	}
	
}
