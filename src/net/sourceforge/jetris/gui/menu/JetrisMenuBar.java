package net.sourceforge.jetris.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import net.sourceforge.jetris.gui.JetrisMainFrame;

public class JetrisMenuBar extends JMenuBar {

	// Constants

	// Class attributes

	// Attributes
	private JetrisMainFrame jetrisFrame;
	private JMenuItem restartMenuItem;
	private JMenuItem pauseMenuItem;
	private JMenuItem hiScoreMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem helpMenuItem;
	private JMenuItem aboutMenuItem;
	
	private HelpDialog helpDialog;
	private AboutDialog aboutDialog;
	
	// Constructors
	public JetrisMenuBar(JetrisMainFrame tetrisFrame) {
		this.jetrisFrame = tetrisFrame;
		createMenuBarComponents();
		createDialogs();
	}

	// Methods
	private void createMenuBarComponents() {
	    MenuHandler menuHandler = new MenuHandler();
	    
	    JMenu jetrisMenu = createJetrisMenu(menuHandler);
	    add(jetrisMenu);
   
	    JMenu helpMenu = createHelpMenu(menuHandler);
	    add(helpMenu);
	}
	
	private JMenu createJetrisMenu(MenuHandler menuHandler) {
	    JMenu jetrisMenu = new JMenu();
	    jetrisMenu.setText("Jetris");
	    jetrisMenu.setMnemonic('J');
	    
	    restartMenuItem = createRestartMenuItem(menuHandler); 
        jetrisMenu.add(restartMenuItem);
        
	    pauseMenuItem = createPauseMenuItem(menuHandler); 
        jetrisMenu.add(pauseMenuItem);

        jetrisMenu.addSeparator();

	    hiScoreMenuItem = createHiScoreMenuItem(menuHandler); 
        jetrisMenu.add(hiScoreMenuItem);

        jetrisMenu.addSeparator();

	    exitMenuItem = createExitMenuItem(menuHandler); 
        jetrisMenu.add(exitMenuItem);
        
        return jetrisMenu;
	}
	
	private JMenuItem createRestartMenuItem(MenuHandler menuHandler) {
		String menuItemText = "Restart";
		char mnemonic = 'R';
		int acceleratorKeyCode = 'R';
		return createMenuItem(menuHandler, menuItemText, mnemonic, acceleratorKeyCode);
	}

	private JMenuItem createPauseMenuItem(MenuHandler menuHandler) {
		String menuItemText = "Pause";
		char mnemonic = 'P';
		int acceleratorKeyCode = 'P';
		return createMenuItem(menuHandler, menuItemText, mnemonic, acceleratorKeyCode);
	}

	private JMenuItem createHiScoreMenuItem(MenuHandler menuHandler) {
		String menuItemText = "HiScore...";
		char mnemonic = 'H';
		int acceleratorKeyCode = 'H';
		return createMenuItem(menuHandler, menuItemText, mnemonic, acceleratorKeyCode);
	}

	private JMenuItem createExitMenuItem(MenuHandler menuHandler) {
		String menuItemText = "Exit";
		char mnemonic = 'X';
		int acceleratorKeyCode = KeyEvent.VK_ESCAPE;
		return createMenuItem(menuHandler, menuItemText, mnemonic, acceleratorKeyCode);
	}

	
	private JMenu createHelpMenu(MenuHandler menuHandler) {
	    JMenu menuHelp = new JMenu();
	    menuHelp.setText("Help");
	    menuHelp.setMnemonic('H');
	    
	    helpMenuItem = createHelpMenuItem(menuHandler); 
	    menuHelp.add(helpMenuItem);

	    aboutMenuItem = createAboutMenuItem(menuHandler); 
	    menuHelp.add(aboutMenuItem);
	    
	    return menuHelp;
	}
	
	private JMenuItem createHelpMenuItem(MenuHandler menuHandler) {
		String menuItemText = "Help";
		char mnemonic = 'H';
		int acceleratorKeyCode = KeyEvent.VK_F1;
		return createMenuItem(menuHandler, menuItemText, mnemonic, acceleratorKeyCode);
	}


	private JMenuItem createAboutMenuItem(MenuHandler menuHandler) {
		String menuItemText = "About";
		char mnemonic = 'A';
		return createMenuItem(menuHandler, menuItemText, mnemonic);
    }

	
	private JMenuItem createMenuItem(ActionListener menuHandler, String text, char mnemonic, int acceleratorKeyCode) {
		JMenuItem menuItem = createMenuItem(menuHandler, text, mnemonic);
        setKeyAcceleratorMenu(menuItem, acceleratorKeyCode);
        return menuItem;
	}

	private JMenuItem createMenuItem(ActionListener menuHandler, String text, char mnemonic) {
		JMenuItem restartMenuItem = new JMenuItem(text);
        restartMenuItem.addActionListener(menuHandler);
        restartMenuItem.setMnemonic(mnemonic);
        return restartMenuItem;
	}

    private void setKeyAcceleratorMenu(JMenuItem menuItem, int keyCode) {
    	int mask = 0;
        KeyStroke ks = KeyStroke.getKeyStroke(keyCode, mask);
        menuItem.setAccelerator(ks);
    }

    private void createDialogs() {
    	helpDialog = new HelpDialog(jetrisFrame);
    	aboutDialog = new AboutDialog(jetrisFrame);
    }
    
    public void doHelp() {
        helpDialog.show();
    }
    
    public void doAbout() {
    	aboutDialog.show();
    }
    
    // MenuHandler class
    //-------------------------------------
    private class MenuHandler implements ActionListener {

    	// Methods
        @Override
		public void actionPerformed(ActionEvent e) {
            try {
            	Object objectSource = e.getSource();
            	if (objectSource instanceof JMenuItem) {
                    JMenuItem sourceMenuItem = (JMenuItem) e.getSource();
                    if (sourceMenuItem == restartMenuItem) {
                    	jetrisFrame.restart();
                    } else if (sourceMenuItem == pauseMenuItem) {
                        jetrisFrame.pause();
                    } else if (sourceMenuItem == hiScoreMenuItem) {
                    	jetrisFrame.showHiScore();
                    } else if (sourceMenuItem == exitMenuItem) {
                    	System.exit(0);
                    } else if (sourceMenuItem == helpMenuItem) {
                    	doHelp();
                    }else if (sourceMenuItem == aboutMenuItem) {
                    	doAbout();
                    }
            	}
            } catch (Exception exception) {
            	System.out.println(exception.getStackTrace());
            }
        }
    }
    

}

