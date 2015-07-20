import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class Display {
	JFrame mainFrame;
	JPanel controlPanel;
	JButton buttonStart, buttonStop;

	public Display() {
		prepareGUI();
	}

	public void prepareGUI() {
		mainFrame = new JFrame("Game of life");
		mainFrame.setSize(500, 300);
//		mainFrame.setLayout(new GridLayout(2, 2));
//		mainFrame.setLayout(new GridLayout(1, 2));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonStart = new JButton("Start");
		buttonStart.setSize(10, 10);
		buttonStop = new JButton("Stop");
		
		controlPanel = new JPanel();
//		controlPanel.setBackground(Color.BLUE);
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(buttonStart);
		controlPanel.add(buttonStop);
		
		Grid grid = new Grid();

//		mainFrame.add(grid, BorderLayout.WEST);
		mainFrame.add(grid);
		mainFrame.add(controlPanel, BorderLayout.EAST);
//		mainFrame.add(buttonStart);
//		mainFrame.add(buttonStop);
	}

	private void show() {
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		Display d = new Display();
		d.show();
	}
}
