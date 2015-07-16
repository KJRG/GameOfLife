import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BoardTest {

	@Test
	public void cellDiesWhenItHasNoNeighbours() {
		List<Cell> aliveCells = Arrays.asList((new Cell(0, 0)));
		Board board = new Board(3, aliveCells);
		boolean alive = false;

		board.nextRound();

		for (Cell c : board.getCells()) {
			if (c.getPosX() == 0 && c.getPosY() == 0) {
				if (c.isAlive()) {
					alive = true;
				}

				break;
			}
		}

		assertFalse(alive);
	}

	@Test
	public void cellDiesWhenItHasOneNeighbour() {
		List<Cell> aliveCells = Arrays.asList((new Cell(0, 0)), new Cell(1, 0));
		Board board = new Board(3, aliveCells);
		boolean alive = false;

		board.nextRound();

		for (Cell c : board.getCells()) {
			if (c.getPosX() == 0 && c.getPosY() == 0) {
				if (c.isAlive()) {
					alive = true;
				}

				break;
			}
		}

		assertFalse(alive);
	}

	@Test
	public void cellStaysAliveWhenItHas2Neighbours() {
		List<Cell> aliveCells = Arrays.asList((new Cell(0, 1)), new Cell(1, 0),
				new Cell(1, 1));
		Board board = new Board(3, aliveCells);
		boolean alive = false;

		board.nextRound();

		for (Cell c : board.getCells()) {
			if (c.getPosX() == 0 && c.getPosY() == 0) {
				if (c.isAlive()) {
					alive = true;
				}

				break;
			}
		}

		assertTrue(alive);
	}

	@Test
	public void cellStaysDeadWhenItHas2Neighbours() {
		List<Cell> aliveCells = Arrays.asList((new Cell(0, 1)), new Cell(1, 0),
				new Cell(1, 1), new Cell(2, 1));
		Board board = new Board(3, aliveCells);
		boolean alive = false;
		
		board.nextRound();
		
		for (Cell c : board.getCells()) {
			if (c.getPosX() == 0 && c.getPosY() == 2) {
				if (c.isAlive()) {
					alive = true;
				}
				
				break;
			}
		}
		
		assertFalse(alive);
	}

	@Test
	public void cellStaysAliveWhenItHas3Neighbours() {
		fail("Not yet implemented");
	}

	@Test
	public void cellBecomesAliveWhenItHas3Neighbours() {
		fail("Not yet implemented");
	}

	@Test
	public void cellDiesWhenItHasMoreThan3Neighbours() {
		fail("Not yet implemented");
	}

}
