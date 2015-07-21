import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Board {
	private int numOfRows, numOfColumns;
	private Map<Position, Cell> currentRoundBoard;

	public int getRows() {
		return numOfRows;
	}

	public int getColumns() {
		return numOfColumns;
	}

	public Map<Position, Cell> getCurrentRound() {
		return this.currentRoundBoard;
	}

	public Board(int rows, int columns, List<Cell> aliveCells) {
		this.numOfRows = rows;
		this.numOfColumns = columns;
		this.currentRoundBoard = new HashMap<Position, Cell>();

		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				currentRoundBoard.put(new Position(i, j), new Cell(i, j));
			}
		}

		for (Cell c : aliveCells) {
			Position posAlive = new Position(c.getPosX(), c.getPosY());
			Cell aliveCell = currentRoundBoard.get(posAlive);
			aliveCell.setAlive(true);
			currentRoundBoard.put(posAlive, aliveCell);
		}
	}

	private List<Position> getNeighbours(Position pos) {
		List<Position> neighbours = new ArrayList<Position>();

		int minLeft = pos.getX() == 0 ? 0 : pos.getX() - 1;
		int maxRight = pos.getX() == (numOfRows - 1) ? (numOfRows - 1) : pos.getX() + 1;
		int minUp = pos.getY() == 0 ? 0 : pos.getY() - 1;
		int maxDown = pos.getY() == (numOfColumns - 1)
				? (numOfColumns - 1)
				: pos.getY() + 1;

		for (int i = minLeft; i <= maxRight; i++) {
			for (int j = minUp; j <= maxDown; j++) {
				if (i == pos.getX() && j == pos.getY()) {
					continue;
				}

				neighbours.add(new Position(i, j));
			}
		}

		return neighbours;
	}

	private int getNumAliveNeighbours(Position pos) {
		int numAliveNeighbours = 0;

		for (Position p : getNeighbours(pos)) {
			if (currentRoundBoard.get(p).isAlive()) {
				numAliveNeighbours++;
			}
		}

		return numAliveNeighbours;
	}

	public void nextRound() {
		int numAliveNeighbours = 0;
		Map<Position, Cell> nextRoundBoard = new HashMap<Position, Cell>();

		for (Position pos : currentRoundBoard.keySet()) {
			Cell c = new Cell(currentRoundBoard.get(pos));
			numAliveNeighbours = getNumAliveNeighbours(pos);

			if (numAliveNeighbours < 2 || numAliveNeighbours > 3
					|| (numAliveNeighbours == 2
							&& !c.isAlive())) {

				c.setAlive(false);
				nextRoundBoard.put(new Position(pos), c);
				continue;
			}

			c.setAlive(true);
			nextRoundBoard.put(new Position(pos), c);
		}

		currentRoundBoard = nextRoundBoard;
	}
}
