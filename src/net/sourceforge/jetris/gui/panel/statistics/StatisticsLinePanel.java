package net.sourceforge.jetris.gui.panel.statistics;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class StatisticsLinePanel extends JPanel {
	
	// Attributes
	private JLabel descriptionLabel;
	private JLabel xLabel;
	private CountLabel countLabel;
	
	// Contructors
	public StatisticsLinePanel(String label) {
		this.descriptionLabel = new JLabel(label);
		this.xLabel = new JLabel("x");
		this.countLabel = new CountLabel();
		this.setLayout(createLayout());
		
		add(descriptionLabel);
		add(xLabel);
		add(countLabel);
	}
	

	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout(
				"", // Layout contraints
				"[][][]", // Columns contraints
				"[]"); // Row contraints
		return layout;
	}
	
	// Methods
	public void addLinesCount() {
		countLabel.incrementCount();
	}

}
