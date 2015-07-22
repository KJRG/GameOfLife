import java.util.ArrayList;
import java.util.List;

public class Controller {
	public static void main(String[] args) {
		List<Cell> cells = new ArrayList<Cell>();
		
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
		
		// R-pentomino
		cells.add(new Cell(4, 22));
		cells.add(new Cell(5, 21));
		cells.add(new Cell(5, 22));
		cells.add(new Cell(5, 23));
		cells.add(new Cell(6, 21));
		
		Board board = new Board(50, 51, cells);
		
		Display d = new Display(board);
		d.show();
	}
}
