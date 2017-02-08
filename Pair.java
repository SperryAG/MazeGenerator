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
	public boolean equals(Object obj){
		Pair pair = (Pair) obj;
		return pair.x == this.x && pair.y == this.y;
	}
	public int hashCode() {
		  
	    int hash = x+y;
	    //System.out.println("hashcode called" + hash);
	    return hash;
	}
}
