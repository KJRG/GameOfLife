import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class Display implements ActionListener {
	JFrame mainFrame;
	JPanel controlPanel;
	JButton buttonStart,
			buttonStop,
			buttonNextRound;
	
	private Board board;

	public Display() {
		// TODO Move different figures to enum
		
		List<Cell> cells = new ArrayList<Cell>();
		cells.add(new Cell(0, 0));
		cells.add(new Cell(0, 1));
		cells.add(new Cell(1, 0));
		board = new Board(3, cells);
		
		prepareGUI();
	}

	public void prepareGUI() {
		mainFrame = new JFrame("Game of life");
		mainFrame.setSize(500, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonStart = new JButton("Start");
		buttonStop = new JButton("Stop");
		buttonNextRound = new JButton("Next round");
		buttonStart.setPreferredSize(new Dimension(100, 26));
		buttonStop.setPreferredSize(new Dimension(100, 26));
		buttonNextRound.setPreferredSize(new Dimension(100, 26));
		buttonNextRound.addActionListener(this);

		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.add(buttonStart);
		controlPanel.add(buttonStop);
		controlPanel.add(buttonNextRound);

		Grid grid = new Grid();

		mainFrame.add(grid, BorderLayout.CENTER);
		mainFrame.add(controlPanel, BorderLayout.EAST);
	}

	private void show() {
		mainFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == this.buttonNextRound) {
			board.nextRound();
			
		}
	}

	public static void main(String[] args) {
		Display d = new Display();
		d.show();
	}
}
