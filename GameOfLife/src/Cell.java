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

	public void setX(int x) {
		position.setX(x);
	}

	public int getY() {
		return position.getY();
	}

	public void setY(int y) {
		position.setY(y);
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
