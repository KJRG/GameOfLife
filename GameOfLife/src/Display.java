import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display implements ActionListener, MouseListener {
	private JFrame mainFrame;
	private JPanel buttonsPanel;
	private JButton buttonStart,
			buttonStop,
			buttonNextRound,
			buttonClearBoard;
	private GridPanel grid;

	private Board board;
	private boolean animate;
	private Thread animatorThread;

	public Display(Board board) {
		this.board = board;
		animate = false;
		prepareGUI();
	}

	public void prepareGUI() {
		mainFrame = new JFrame("Game of life");
		mainFrame.setSize(800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonStart = new JButton("Start");
		buttonStop = new JButton("Stop");
		buttonNextRound = new JButton("Next round");
		buttonClearBoard = new JButton("Clear board");

		buttonStart.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				buttonStart.getMinimumSize().height));
		buttonStop.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				buttonStop.getMinimumSize().height));
		buttonNextRound.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				buttonNextRound.getMinimumSize().height));
		buttonClearBoard.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				buttonClearBoard.getMinimumSize().height));

		buttonStart.addActionListener(this);
		buttonStop.addActionListener(this);
		buttonNextRound.addActionListener(this);
		buttonClearBoard.addActionListener(this);

		grid = new GridPanel(board.getNumOfRows(), board.getNumOfColumns());
		grid.setBoard(this.board.getCurrentRound());
		grid.addMouseListener(this);

		buttonsPanel = new JPanel();
		buttonsPanel
				.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
		buttonsPanel.add(buttonStart);
		buttonsPanel.add(Box.createVerticalStrut(2));
		buttonsPanel.add(buttonStop);
		buttonsPanel.add(Box.createVerticalStrut(2));
		buttonsPanel.add(buttonNextRound);
		buttonsPanel.add(Box.createVerticalStrut(2));
		buttonsPanel.add(buttonClearBoard);

		mainFrame.add(grid, BorderLayout.CENTER);
		mainFrame.add(buttonsPanel, BorderLayout.EAST);
	}

	public void show() {
		mainFrame.setVisible(true);
	}

	private Runnable animator = new Runnable() {

		@Override
		public void run() {

			while (animate) {
				board.nextRound();
				grid.setBoard(board.getCurrentRound());
				grid.repaint();

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == this.buttonStart && !animate) {
			animate = true;
			animatorThread = new Thread(animator);
			animatorThread.start();
		}

		if (ae.getSource() == this.buttonStop && animate) {
			animate = false;
			animatorThread = null;
		}

		if (ae.getSource() == this.buttonNextRound && !animate) {
			board.nextRound();
			grid.setBoard(board.getCurrentRound());
			grid.repaint();
		}
		
		if(ae.getSource() == this.buttonClearBoard && !animate) {
			board.clear();
			grid.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(animate || e.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		
		int row = e.getY() / 10;
		int column = e.getX() / 10;
		
		if(row >= board.getNumOfRows() || column >= board.getNumOfColumns()) {
			return;
		}
		
		toggleCell(new Position(column, row));
	}
	
	private void toggleCell(Position position) {
		Cell cell = this.board.getCurrentRound().get(position);
		cell.setAlive(!cell.isAlive());
		this.board.updateCell(cell);
		
		grid.repaint();
	}
}
