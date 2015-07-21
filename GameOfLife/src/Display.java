import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class Display implements ActionListener {
	private JFrame mainFrame;
	private JPanel buttonsPanel;
	private JButton buttonStart,
			buttonStop,
			buttonNextRound;
	private Grid grid;

	private Board board;
	private boolean loop;
	private Thread animatorThread;

	public Display() {
		// TODO Move different figures to enum

		List<Cell> cells = new ArrayList<Cell>();
		loop = false;

		// brick
		cells.add(new Cell(0, 0));
		cells.add(new Cell(0, 1));
		cells.add(new Cell(1, 0));

		// brick
		cells.add(new Cell(23, 23));
		cells.add(new Cell(23, 24));
		cells.add(new Cell(24, 23));

		// beehive
		cells.add(new Cell(2, 15));
		cells.add(new Cell(3, 14));
		cells.add(new Cell(3, 16));
		cells.add(new Cell(4, 14));
		cells.add(new Cell(4, 16));
		cells.add(new Cell(5, 15));

		// blinker
		cells.add(new Cell(4, 5));
		cells.add(new Cell(5, 5));
		cells.add(new Cell(6, 5));

		// beacon
		cells.add(new Cell(17, 1));
		cells.add(new Cell(17, 2));
		cells.add(new Cell(18, 1));
		cells.add(new Cell(18, 2));
		cells.add(new Cell(19, 3));
		cells.add(new Cell(19, 4));
		cells.add(new Cell(20, 3));
		cells.add(new Cell(20, 4));

		// glider
		cells.add(new Cell(10, 10));
		cells.add(new Cell(11, 11));
		cells.add(new Cell(12, 11));
		cells.add(new Cell(10, 12));
		cells.add(new Cell(11, 12));

		board = new Board(30, 30, cells);

		prepareGUI();
	}

	public void prepareGUI() {
		mainFrame = new JFrame("Game of life");
		mainFrame.setSize(450, 350);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonStart = new JButton("Start");
		buttonStop = new JButton("Stop");
		buttonNextRound = new JButton("Next round");

		buttonStart.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				buttonStart.getMinimumSize().height));
		buttonStop.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				buttonStop.getMinimumSize().height));
		buttonNextRound.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				buttonNextRound.getMinimumSize().height));

		buttonStart.addActionListener(this);
		buttonStop.addActionListener(this);
		buttonNextRound.addActionListener(this);

		grid = new Grid();
		grid.setBoard(this.board.getCurrentRound());

		buttonsPanel = new JPanel();
		buttonsPanel
				.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
		buttonsPanel.add(buttonStart);
		buttonsPanel.add(Box.createVerticalStrut(2));
		buttonsPanel.add(buttonStop);
		buttonsPanel.add(Box.createVerticalStrut(2));
		buttonsPanel.add(buttonNextRound);

		mainFrame.add(grid, BorderLayout.CENTER);
		mainFrame.add(buttonsPanel, BorderLayout.EAST);
	}

	private void show() {
		mainFrame.setVisible(true);
	}

	private Runnable animator = new Runnable() {

		@Override
		public void run() {

			while (loop) {
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

		if (ae.getSource() == this.buttonStart && !loop) {
			loop = true;
			animatorThread = new Thread(animator);
			animatorThread.start();
		}

		if (ae.getSource() == this.buttonStop && loop) {
			loop = false;
			animatorThread = null;
		}

		if (ae.getSource() == this.buttonNextRound && !loop) {
			board.nextRound();
			grid.setBoard(board.getCurrentRound());
			grid.repaint();
		}
	}

	public static void main(String[] args) {
		Display d = new Display();
		d.show();
	}
}
