package MazeGenerator;

public class MazeGenerator {
	public static void main(String[] args) {
		Maze maze;
		
		maze = new Maze("FirstMaze",11,42);
		System.out.println(maze.toString());
		maze.toFile();
	}
}
