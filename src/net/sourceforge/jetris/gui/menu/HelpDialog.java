/* HelpDialog created on 28.09.2006 */
package net.sourceforge.jetris.gui.menu;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import net.sourceforge.jetris.config.CommonConfig;
import res.ResClass;

public class HelpDialog {

	// Constants
    private static final String NAME = "JETRIS HELP";

	// Attributes
    private final JFrame owner;
    private JScrollPane scroll;

    // Constructors
    public HelpDialog(JFrame owner) {
        
        this.owner = owner;
        
        try {
            JEditorPane doc = new JEditorPane(ResClass.class.getResource("help.html"));
            doc.setEditable(false);
            this.scroll = new JScrollPane(doc);
            scroll.setPreferredSize(new Dimension(400,450));
        }
        catch(java.io.IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void show() {
        JOptionPane.showMessageDialog(owner,scroll, NAME, JOptionPane.PLAIN_MESSAGE, 
        		new ImageIcon(CommonConfig.getInstance().getJetrisImage()));
    }
}
