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
	public Pair getXYCoords() {
		return new Pair(this.x, this.y);
	}
	public String toString() {
		String output = "(" + x + ", " + y + ")";
		return output;
	}
	public boolean equals(Object obj){
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair pair = (Pair) obj;
		return pair.x == this.x && pair.y == this.y;
	}
	public int hashCode() {
		  
	    int hash = x+y;
	    //System.out.println("hashcode called" + hash);
	    return hash;
	}
}
