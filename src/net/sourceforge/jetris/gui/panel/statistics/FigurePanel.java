package net.sourceforge.jetris.gui.panel.statistics;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jetris.config.CommonConfig;
import net.sourceforge.jetris.figure.Figure;
import net.sourceforge.jetris.figure.FigureFactory;
import net.sourceforge.jetris.geometry.Point;
import net.sourceforge.jetris.gui.utils.GridPanel;

public class FigurePanel extends JPanel {

	// Contants
	private static final Color BACKGROUND_COLOR = CommonConfig.getInstance()
			.getStatisticsPanelBackgroundColor();
	private static final Border DEFAULT_EMPTY_BORDER = BorderFactory
			.createEmptyBorder();

	// Attributes
	private Figure figure;
	private GridPanel gridPanel;

	// Constructors
	public FigurePanel() {
		this.gridPanel = createEmptyGridPanel();
		this.setLayout(createLayout());
		add(gridPanel);
	}

	public FigurePanel(Figure figure) {
		this.figure = figure;
		this.gridPanel = createGridPanel(figure);
		this.setLayout(createLayout());
		add(gridPanel);
	}

	private GridPanel createGridPanel(Figure figure) {
		GridPanel figureGridPanel = createEmptyGridPanel();
		figureGridPanel.showFigureUpMedium(figure);
		return figureGridPanel;
	}

	private GridPanel createEmptyGridPanel() {
		Point dimension = new Point(4, 4);
		GridPanel emptyCanvas = new GridPanel(dimension, BACKGROUND_COLOR,
				DEFAULT_EMPTY_BORDER);
		return emptyCanvas;
	}

	// private Point getOffsetForFigure(int figureId) {
	// Point point = null;
	// switch (figureId) {
	// case Figure.FIGURE_ID_I:
	// point = new Point(2,0);
	// break;
	// case Figure.FIGURE_ID_T:
	// point = new Point(1,1);
	// break;
	// case Figure.FIGURE_ID_O:
	// point = new Point(1,1);
	// break;
	// case Figure.FIGURE_ID_J:
	// point = new Point(1,1);
	// break;
	// case Figure.FIGURE_ID_L:
	// point = new Point(1,1);
	// break;
	// case Figure.FIGURE_ID_S:
	// point = new Point(1,1);
	// break;
	// case Figure.FIGURE_ID_Z:
	// point = new Point(1,1);
	// break;
	// default:
	// point = new Point(0,0);
	// break;
	// }
	// return point;
	// }

	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout("", // Layout constraints
				"0[]0[]0[]0[]0", // Columns contraints
				"0[]0[]0[]0[]0"); // Rows contraints
		return layout;
	}

}
