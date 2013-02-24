package net.sourceforge.jetris.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import net.sourceforge.jetris.config.CommonConfig;
import net.sourceforge.jetris.gui.utils.HTMLLink;

public class CreditsPanel extends JPanel {

	// Constants
	public static final CommonConfig config = CommonConfig.getInstance();

	// Class attributes

	// Attributes

	// Constructors
	public CreditsPanel() {
    	super(new BorderLayout());
    	add(getNikolayCreditPanel(), BorderLayout.NORTH);
    	add(getDiegoCreditPanel(), BorderLayout.CENTER);
	} 
    	
	// Methods
    private JPanel getNikolayCreditPanel() {
        JPanel nikolayCreditPanel = new JPanel(new BorderLayout());
        BoxLayout layout1 = new BoxLayout(nikolayCreditPanel,BoxLayout.X_AXIS);
        nikolayCreditPanel.setLayout(layout1);
        nikolayCreditPanel.setBorder(new EtchedBorder());
        nikolayCreditPanel.add(Box.createRigidArea(new Dimension(32,0)));
        
        JLabel copyrightLabel = new JLabel("Copyright (c) 2006 Nikolay G. Georgiev ");
        copyrightLabel.setFont(config.getFont());
        HTMLLink nikolayEmail = new HTMLLink("ngg@users.sourceforge.net", true);
        nikolayEmail.setFont(config.getFont());
        
        nikolayCreditPanel.add(copyrightLabel);
        nikolayCreditPanel.add(nikolayEmail);
        
        return nikolayCreditPanel;
	}

    private JPanel getDiegoCreditPanel() {
        JPanel diegoCreditPanel = new JPanel(new BorderLayout());
        BoxLayout layout1 = new BoxLayout(diegoCreditPanel,BoxLayout.X_AXIS);
        diegoCreditPanel.setLayout(layout1);
        diegoCreditPanel.setBorder(new EtchedBorder());
        diegoCreditPanel.add(Box.createRigidArea(new Dimension(32,0)));
        
        JLabel copyrightLabel = new JLabel("Copyright (c) 2013 Diego Rosado - ");
        copyrightLabel.setFont(config.getFont());
        HTMLLink nikolayEmail = new HTMLLink("diego_dot_rosado_dot_fuentes@gmail.com", true);
        nikolayEmail.setFont(config.getFont());
        
        diegoCreditPanel.add(copyrightLabel);
        diegoCreditPanel.add(nikolayEmail);
        
        return diegoCreditPanel;
    }
    
}

