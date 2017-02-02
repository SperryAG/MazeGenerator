package MazeGenerator;

public class MazeGenerator {
	public static void main(String[] args) {
		
		Maze maze = new Maze();
	
		maze.setGridSize(20);
		maze.setActiveNodeCount(400);
		maze.generateMaze();
		System.out.println(maze.toString());
		maze.toFile();
	}
}