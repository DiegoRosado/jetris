package net.sourceforge.jetris.gui.panel.info;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jetris.figure.Figure;
import net.sourceforge.jetris.gui.panel.statistics.FigurePanel;

public class NextFigurePanel extends JPanel {

	// Constants

	// Class attributes

	// Attributes
	private final JLabel nextLabel;
	private FigurePanel figurePanel;

	// Constructors
	public NextFigurePanel() {
		this.nextLabel = new JLabel("NEXT FIGURE:");
		this.figurePanel = new FigurePanel();
		
		setLayout(createLayout());
		add(nextLabel, "wrap");
		add(figurePanel);
	}

	
	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout(
				"", // Layout contraints
				"[]", // Columns contraints
				"[][]"); // Row contraints
		return layout;
	}

	// Methods
	public void setNextFigure(Figure figure) {
		remove(figurePanel);
		figurePanel = new FigurePanel(figure);
		add(figurePanel);
	}
	

}

