/* HelpDialog created on 28.09.2006 */
package net.sourceforge.jetris.gui.menu;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.sourceforge.jetris.config.CommonConfig;
import res.ResClass;

public class AboutDialog {

	// Constants
    private static final String NAME = "About";

	// Attributes
    private final JFrame owner;
    private JEditorPane aboutPanel;

    // Constructors
    public AboutDialog(JFrame owner) {
        
        this.owner = owner;
        
        try {
            aboutPanel = new JEditorPane(ResClass.class.getResource("about.html"));
            aboutPanel.setEditable(false);
        }
        catch(java.io.IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void show() {
        JOptionPane.showMessageDialog(owner, aboutPanel, NAME, JOptionPane.PLAIN_MESSAGE,
        		new ImageIcon(CommonConfig.getInstance().getJetrisImage()));
    }
}
