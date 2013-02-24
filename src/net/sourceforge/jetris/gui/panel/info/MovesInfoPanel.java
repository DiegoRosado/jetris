package net.sourceforge.jetris.gui.panel.info;

import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MovesInfoPanel extends JPanel {

	// Constants

	// Class attributes

	// Attributes
	private JLabel leftInfoMoveLabel;
	private JLabel rightInfoMoveLabel;
	private JLabel rotateInfoMoveLabel;
	private JLabel downInfoMoveLabel;
	private JLabel dropInfoMoveLabel;
	private JButton restartButton;
	private JButton pauseButton;

	// Constructors
	public MovesInfoPanel() {
        this.leftInfoMoveLabel = new JLabel("A or \u2190 - Left");
        this.rightInfoMoveLabel = new JLabel("D or \u2192 - Right");
        this.rotateInfoMoveLabel = new JLabel("W or \u2191 - Rotate");
        this.downInfoMoveLabel = new JLabel("S or \u2193 - Down");
        this.dropInfoMoveLabel = new JLabel("Space - Drop");
        this.restartButton = new JButton("Restart");
        restartButton.setFocusable(false);
        this.pauseButton = new JButton("Pause");
        pauseButton.setFocusable(false);
        
        setLayout(createLayout());
        add(leftInfoMoveLabel, "wrap");
        add(rightInfoMoveLabel, "wrap");	
        add(rotateInfoMoveLabel, "wrap");	
        add(downInfoMoveLabel, "wrap");	
        add(dropInfoMoveLabel, "wrap");	
        add(restartButton, "grow, wrap");	
        add(pauseButton, "grow");	
    }

	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout(
				"", // Layout contraints
				"[]", // Columns contraints
				"[][][][][][][]"); // Row contraints
		return layout;
	}

	// Methods


}

