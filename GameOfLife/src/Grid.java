import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Grid extends JPanel {
	private static final int boardX = 3, boardY = 3;
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
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 300, 300);
		
		if(board.isEmpty()) {
			return;
		}
		
		for(int i = 0; i < boardX; i++) {
			for(int j = 0; j < boardY; j++) {
				
				if(board.get(new Position(i, j)).isAlive()) {
					g.setColor(Color.GREEN);
					g.fillRect((100 * i), (100 * j), 100, 100);					

					g.setColor(Color.BLACK);
					g.drawRect((100 * i), (100 * j), 100, 100);			
					
					continue;
				}
				
				g.setColor(Color.WHITE);
				g.fillRect((100 * i), (100 * j), 100, 100);			
				
				g.setColor(Color.BLACK);
				g.drawRect((100 * i), (100 * j), 100, 100);			
//				JOptionPane.showMessageDialog(null, "x: " + i + " y: " + j);
			}
		}

//		g.setColor(Color.BLUE);
//		g.fillRect(10, 10, 10, 10);
	}
}
