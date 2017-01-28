package MazeGenerator;

public class Pair {
	/* Variables */
	final int x;
	final int y;
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
	public String toString() {
		String output = "(" + x + ", " + y + ")";
		return output;
	}
}
