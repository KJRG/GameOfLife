
public class Cell {
	private Position position;
	private boolean alive;

	public Cell(int x, int y) {
		this.position = new Position(x, y);
	}

	public Cell(Cell other) {
		this.position = new Position(other.getX(), other.getY());
		this.alive = other.alive;
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
