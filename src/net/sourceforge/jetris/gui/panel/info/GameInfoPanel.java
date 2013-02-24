package net.sourceforge.jetris.gui.panel.info;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jetris.gui.JetrisMainFrame;

public class GameInfoPanel extends JPanel {

	// Constants

	// Class attributes

	// Attributes
	private JLabel hiScoreLabel;
	private JLabel hiScoreField;
	private JLabel scoreLabel;
	private JLabel scoreField;
	private JLabel linesLabel;
	private JLabel linesField;
	private JLabel levelLabel;
	private JLabel levelField;
	private JLabel timeLabel;
	private JLabel timeField;
	private int hiScore;
	private int score;
	private int lines;
	private int level;
	private int time;

	// Constructors
	public GameInfoPanel() {
		// set values
		hiScore = 0;
		score = 0;
		lines = 0;
		level = 1;
		time = 0;

		// Create labels
		this.hiScoreLabel = new JLabel("HI-SCORE:");
		this.hiScoreField = new JLabel(Integer.toString(hiScore));
		this.scoreLabel = new JLabel("SCORE");
		this.scoreField = new JLabel(Integer.toString(score));
		this.linesLabel = new JLabel("LINES");
		this.linesField = new JLabel(Integer.toString(lines));
		this.levelLabel = new JLabel("LEVEL");
		this.levelField = new JLabel(Integer.toString(level));
		this.timeLabel = new JLabel("TIME");
		this.timeField = new JLabel(Integer.toString(time));
		
		// add components
		setLayout(createLayout());
		add(hiScoreLabel, "wrap");
		add(hiScoreField, "wrap");
		add(scoreLabel, "wrap");
		add(scoreField, "wrap");
		add(linesLabel, "wrap");
		add(linesField, "wrap");
		add(levelLabel, "wrap");
		add(levelField, "wrap");
		add(timeLabel, "wrap");
		add(timeField, "wrap");
		
	}
	
	private LayoutManager createLayout() {
		MigLayout layout = new MigLayout(
				"", // Layout contraints
				"[]", // Columns contraints
				"[][][][][][][][][][]"); // Row contraints
		return layout;
	}

	// Methods
	public void updateHiScore(int hiScore) {
		hiScoreLabel.setText(Integer.toString(hiScore));
	}

	public void updateScore(int score) {
		scoreLabel.setText(Integer.toString(score));
	}

	public void addLinesCount(int newLines) {
		checkNextLevel(newLines);
		lines = lines + newLines;
		linesField.setText(Integer.toString(lines));
	}
	
	private void checkNextLevel(int newLines) {
		int units = lines % 10;
		if (units + newLines > 10) {
			level++;
			levelField.setText(Integer.toString(level));
		}
	}
	
	public int getLevel() {
		return level;
	}

	public void updateLevel(int level) {
		levelLabel.setText(Integer.toString(level));
	}

	public void updateTime(int time) {
		timeLabel.setText(Integer.toString(time));
	}

}

