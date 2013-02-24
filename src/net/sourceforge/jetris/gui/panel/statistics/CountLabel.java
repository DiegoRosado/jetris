package net.sourceforge.jetris.gui.panel.statistics;

import java.awt.Color;

import javax.swing.JLabel;

public class CountLabel extends JLabel {
	
	// Attributes
	private int count;
	
	// Constructors
	public CountLabel() {
		this.count = 0;
		this.setText(String.valueOf(count));
		this.setForeground(Color.BLUE);
	}
	
	// Methods
	public void incrementCount() {
		count++;
		this.setText(String.valueOf(count));
	}

	@Override
	public String toString() {
		return "CountLabel - " + count;
	}

}
