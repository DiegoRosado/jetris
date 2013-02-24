package net.sourceforge.jetris.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import net.sourceforge.jetris.config.CommonConfig;
import net.sourceforge.jetris.figure.Figure;
import net.sourceforge.jetris.figure.FigureFactory;
import net.sourceforge.jetris.figure.Move;
import net.sourceforge.jetris.geometry.Point;
import net.sourceforge.jetris.gui.jetrisgrid.JetrisGrid;
import net.sourceforge.jetris.gui.jetrisgrid.JetrisGridEventsListener;
import net.sourceforge.jetris.gui.menu.JetrisMenuBar;
import net.sourceforge.jetris.gui.panel.info.InfoPanel;
import net.sourceforge.jetris.gui.panel.statistics.StatisticsPanel;
import net.sourceforge.jetris.timer.JetrisTimerThread;
import net.sourceforge.jetris.utils.GuiUtils;

public class JetrisMainFrame extends JFrame implements Runnable, JetrisGridEventsListener {
    
	// Constants

	// Class Attributes
    private static final CommonConfig config = CommonConfig.getInstance();

    // Attributes
    private boolean isGameOver;
    private boolean isPause;
    private Figure currentFigure;
    private Figure nextFigure;
    private JetrisTimerThread jetrisTimerThread;
    
    private StatisticsPanel statisticsPanel;
    private JetrisGrid jetrisGrid;
    private KeyListener keyHandler;
    private InfoPanel infoPanel;
    
    // Constructors    
    public JetrisMainFrame() {
        super(config.getFullApplicationName());
        
        this.isGameOver = false;
        this.isPause = false;
        this.nextFigure = FigureFactory.getRandomFigure();
    }
    
    // Methods
    // getters
    
    public boolean isPause() {
    	return isPause;
    }
    
    public boolean isGameOver() {
    	return isGameOver;
    }

    
    @Override
	public void run() {

    	init();
        
        setIconImage(config.getJetrisIcon());
        
        keyHandler = new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                Move move = Move.DOWN;
                if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                    move = Move.LEFT;
                } else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                    move = Move.RIGHT;
                } else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    move = Move.DOWN;
                } else if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    move = Move.ROTATION_RIGHT;
                } else if(code == KeyEvent.VK_SPACE) {
                    move = Move.DROP;
                } /*else if(code == KeyEvent.VK_R) { //Only for the applet needed
                    restart();
                } else if(code == KeyEvent.VK_P) {
                    pause();
                } */
                
                moveEvent(move);
            }
        };
        addKeyListener(keyHandler);
        
        jetrisGrid = new JetrisGrid(config.getTetrisGridDimesion());
        jetrisGrid.subscribeEvents(this);
        
        initMenu();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(statisticsPanel, BorderLayout.WEST);
        mainPanel.add(jetrisGrid, BorderLayout.CENTER);
        infoPanel = new InfoPanel(this);
        mainPanel.add(infoPanel, BorderLayout.EAST);
        mainPanel.add(new CreditsPanel(), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        pack();
        this.setResizable(false);

        addWindowFocusListener(new WindowFocusListener(){

            @Override
            public void windowGainedFocus(WindowEvent arg0) {
            	isPause = false;
            }

            @Override
            public void windowLostFocus(WindowEvent arg0) {
                isPause = true;
            }
        });
        
        
        setLocation(GuiUtils.getInitialFrameLocation(this));
        setVisible(true);

        jetrisTimerThread = new JetrisTimerThread(this);
        jetrisTimerThread.start();

        nextFigureEvent();
    }
    
    private void init() {
    	this.statisticsPanel = new StatisticsPanel(Figure.MAX_FIGURE_ID);
    }
    
    private void initMenu() {
        JMenuBar menuBar = new JetrisMenuBar(this);
        setJMenuBar(menuBar);
    }

    
    public int getLevel() {
    	return infoPanel.getLevel();
    }
    
    private void updateTetrisTimerIfNeeded(Move move) {
    	if (move.equals(Move.DOWN)) {
    		jetrisTimerThread.restartAutoMoveDownCountdown();
    	} else if (move.equals(Move.DROP)) {
    		jetrisTimerThread.endAutoMoveDownCycle();
    	}
    }
    
    public synchronized void moveEvent(Move move) {
    	updateTetrisTimerIfNeeded(move);
    	jetrisGrid.moveEvent(currentFigure, move);
    	if (isGameOver) {
    		showGameOver();
    	}
    }
    
    private void showGameOver() {
    	jetrisTimerThread.interrupt();
    	
    	Graphics graphics = jetrisGrid.getGraphics();
    	Font font = new Font(graphics.getFont().getFontName(), Font.BOLD, 24);
    	graphics.setFont(font);
    	graphics.drawString("GAME OVER", 47, 250);
    }

    
    private void nextFigureTurnEvent() {
		currentFigure = nextFigure;
		currentFigure.setOffset(new Point(4,0));
		nextFigure = FigureFactory.getRandomFigure();
		infoPanel.nextFigureEvent(nextFigure);
		moveEvent(Move.NEW_FIGURE);
		statisticsPanel.addFigureCount(currentFigure.getFigureId());
    }
    
    
    public synchronized void pause() {
        isPause = !isPause;
    }
    

    public void restart() {
        FigureFactory.resetCounts();
        isGameOver = false;
        isPause = false;
        nextFigure = FigureFactory.getRandomFigure();
        jetrisGrid.resetStats();
    }
    
    
    public void showHiScore() {
    }

    
	@Override
	public void doneLinesEvent(int doneLines) {
		statisticsPanel.addLinesCount(doneLines);
		infoPanel.addLinesCount(doneLines);
	}

	@Override
	public void gameOverEvent() {
		isGameOver = true;
		removeKeyListener(keyHandler);
	}
	
	@Override
	public void nextFigureEvent() {
		nextFigureTurnEvent();
	}

}
