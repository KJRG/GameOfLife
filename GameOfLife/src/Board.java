import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Board {
	private int numOfRows, numOfColumns;
	private Map<Position, Cell> currentRoundBoard;

	public int getNumOfRows() {
		return numOfRows;
	}

	public int getNumOfColumns() {
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
				Cell cell = new Cell(i, j);
				currentRoundBoard.put(cell.getPosition(), cell);
			}
		}

		for (Cell cell : aliveCells) {
			Position posAlive = new Position(cell.getX(), cell.getY());
			Cell aliveCell = currentRoundBoard.get(posAlive);
			aliveCell.setAlive(true);
			currentRoundBoard.put(aliveCell.getPosition(), aliveCell);
		}
	}

	private List<Position> getNeighbours(Position position) {
		List<Position> neighbours = new ArrayList<Position>();

		int minLeft = position.getX() == 0 ? 0 : position.getX() - 1;
		int maxRight = position.getX() == (numOfRows - 1)
				? (numOfRows - 1)
				: position.getX() + 1;
		int minUp = position.getY() == 0 ? 0 : position.getY() - 1;
		int maxDown = position.getY() == (numOfColumns - 1)
				? (numOfColumns - 1)
				: position.getY() + 1;

		for (int i = minLeft; i <= maxRight; i++) {
			for (int j = minUp; j <= maxDown; j++) {
				if (i == position.getX() && j == position.getY()) {
					continue;
				}

				neighbours.add(new Position(i, j));
			}
		}

		return neighbours;
	}

	private int getNumAliveNeighbours(Position position) {
		int numAliveNeighbours = 0;

		for (Position neighbour : getNeighbours(position)) {
			if (currentRoundBoard.get(neighbour).isAlive()) {
				numAliveNeighbours++;
			}
		}

		return numAliveNeighbours;
	}

	public void nextRound() {
		int numAliveNeighbours = 0;
		Map<Position, Cell> nextRoundBoard = new HashMap<Position, Cell>();
		
		for (Position position : currentRoundBoard.keySet()) {
			Cell cell = new Cell(currentRoundBoard.get(position));
			numAliveNeighbours = getNumAliveNeighbours(position);

			if (numAliveNeighbours < 2 || numAliveNeighbours > 3
					|| (numAliveNeighbours == 2
							&& !cell.isAlive())) {

				cell.setAlive(false);
				nextRoundBoard.put(cell.getPosition(), cell);
				continue;
			}

			cell.setAlive(true);
			nextRoundBoard.put(cell.getPosition(), cell);
		}

		currentRoundBoard = nextRoundBoard;
	}
}
