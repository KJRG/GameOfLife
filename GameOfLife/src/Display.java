import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class Display implements ActionListener {
	JFrame mainFrame;
	JPanel buttonsPanel;
	JButton buttonStart,
			buttonStop,
			buttonNextRound;
	Grid grid;
	
	private Board board;

	public Display() {
		// TODO Move different figures to enum
		
		List<Cell> cells = new ArrayList<Cell>();
		cells.add(new Cell(0, 0));
		cells.add(new Cell(0, 1));
		cells.add(new Cell(1, 0));
		board = new Board(3, 3, cells);
		
		prepareGUI();
	}

	public void prepareGUI() {
		mainFrame = new JFrame("Game of life");
		mainFrame.setSize(450, 350);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonStart = new JButton("Start");
		buttonStop = new JButton("Stop");
		buttonNextRound = new JButton("Next round");
		buttonStart.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonStart.getMinimumSize().height));
		buttonStop.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonStop.getMinimumSize().height));
		buttonNextRound.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonNextRound.getMinimumSize().height));
		buttonNextRound.addActionListener(this);

		grid = new Grid();

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
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
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == this.buttonNextRound) {
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
