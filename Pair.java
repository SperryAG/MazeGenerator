package MazeGenerator;

public class Pair {
	/* Variables */
	private int x;
	private int y;
	/* Constructors */
	Pair(int x, int y) {this.x=x;this.y=y;}
	/* Methods */
	public boolean isEquals(int x, int y) {
		return this.x == x && this.y == y;
	}
	public int getXCoord() {
		return this.x;
	}
	public int getYCoord() {
		return this.y;
	}
	public String getCoords() {
		return "(" + x + ", " + y + ")";
	}
	public void update(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		String output = "(" + x + ", " + y + ")";
		return output;
	}
}