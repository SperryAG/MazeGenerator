package MazeGenerator;

public class Pair {
	  final int x;
	  final int y;
	  Pair(int x, int y) {this.x=x;this.y=y;}
	  public String toString() {
		  String output = "(" + x + ", " + y + ")";
		  return output;
	  }
}
