package MazeGenerator;

public class MazeGenerator {
	public static void main(String[] args) {
		Maze maze;
		//Oh test commit
		//Steven's test commit again
		maze = new Maze("FirstMaze",12,42);
		System.out.println(maze.toString());
		maze.toFile();
	}
}
