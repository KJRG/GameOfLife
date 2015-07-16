import java.util.List;
import java.util.ArrayList;

public class Board {
	private int size;
	private List<Cell> currentRound;

	public List<Cell> getCells() {
		return currentRound;
	}

	public Board(int size, List<Cell> aliveCells) {
		this.size = size;
		this.currentRound = new ArrayList<Cell>();

		int posOnList = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Cell c = new Cell(i, j);
				c.setAlive(false);

				if (posOnList < aliveCells.size() && i == aliveCells.get(posOnList).getPosX()
						&& j == aliveCells.get(posOnList).getPosY()) {
					
					c.setAlive(true);
					posOnList++;
				}
				
				currentRound.add(c);
			}
		}
	}

	private int getNumAliveNeighbours(Cell c) {
		int numAliveNeighbours = 0;

		List<Cell> neighbours = new ArrayList<Cell>();

		return numAliveNeighbours;
	}

	public void nextRound() {
		List<Cell> nextRound = new ArrayList<Cell>(currentRound);
		int numAliveNeighbours = 0;

		for (int i = 0; i < nextRound.size(); i++) {
			numAliveNeighbours = getNumAliveNeighbours(currentRound.get(i));

			if (numAliveNeighbours < 2 || numAliveNeighbours > 3
					|| (numAliveNeighbours == 2
							&& !currentRound.get(i).isAlive())) {

				nextRound.get(i).setAlive(false);
				continue;
			}

			nextRound.get(i).setAlive(true);
		}

		currentRound = nextRound;
	}

	public List<Cell> getCurrentRound() {
		return currentRound;
	}
}
