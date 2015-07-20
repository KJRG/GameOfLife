import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class Grid extends JPanel {
	private List<Cell> cells;
	Iterator<Cell> iter;
	private int boardSize;

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public Grid() {
		super();
		this.cells = new ArrayList<Cell>();
		this.boardSize = 0;
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 300, 300);
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
			}
		}
	}
}
