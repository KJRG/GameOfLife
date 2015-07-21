import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JPanel;

public class Grid extends JPanel {
	private static final int boardMaxX = 30, boardMaxY = 30, side = 10;
	private Map<Position, Cell> board;

	public Map<Position, Cell> getBoard() {
		return board;
	}

	public void setBoard(Map<Position, Cell> board) {
		this.board = board;
	}

	public Grid() {
		super();
		this.board = new HashMap<Position, Cell>();
	}

	public void paint(Graphics g) {
		for (int i = 0; i < boardMaxX; i++) {
			for (int j = 0; j < boardMaxY; j++) {
				g.setColor(Color.WHITE);
				g.fillRect((side * i), (side * j), side, side);

				g.setColor(Color.BLACK);
				g.drawRect((side * i), (side * j), side, side);
			}
		}

		if (board.isEmpty()) {
			return;
		}
		
		for(Position position : board.keySet()) {
			
			if (board.get(position).isAlive()) {
				g.setColor(Color.GREEN);
				g.fillRect((side * position.getX()), (side * position.getY()), side, side);
				
				g.setColor(Color.BLACK);
				g.drawRect((side * position.getX()), (side * position.getY()), side, side);
				
				continue;
			}
		}
	}
}
