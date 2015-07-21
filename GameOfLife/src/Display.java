import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Display implements ActionListener {
	private JFrame mainFrame;
	private JPanel buttonsPanel;
	private JButton buttonStart,
			buttonStop,
			buttonNextRound;
	private GridPanel grid;

	private Board board;
	private boolean animate;
	private Thread animatorThread;

	public Display(List<Cell> cells) {
		animate = false;
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

		grid = new GridPanel();
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
	}
}
