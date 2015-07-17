public class Cell {
	private int posX, posY;
	private boolean alive;

	public Cell(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public Cell(Cell other) {
		this.posX = other.posX;
		this.posY = other.posY;
		this.alive = other.alive;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
