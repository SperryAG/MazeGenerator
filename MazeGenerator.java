package MazeGenerator;
import java.util.Scanner; 

public class MazeGenerator {
	public static void main(String[] args) {
		Maze maze = new Maze();
			
		Scanner user_input = new Scanner(System.in);
		
		String title;
		int gridsize;
		int activenode;
	
		System.out.print("Enter the title of maze: ");
		title = user_input.next();
		
		System.out.print("Enter the gridsize: ");
		gridsize = user_input.nextInt();
		
		System.out.print("Enter the active node count: ");
		activenode = user_input.nextInt();
		
		while( activenode > gridsize*gridsize){
			System.out.print("\nActive nodes greater than gridsize. Renter the active node count: ");
			activenode = user_input.nextInt();
		}
		
		maze = new Maze(title,gridsize,activenode);
		maze.generateMaze();
	//	maze = new Maze("FirstMaze",12,42);
		System.out.println(maze.toString());
		maze.toFile();
		
		/*maze.fromFile("TestMaze_9x9_46_0.7142857142857143_75_2017-01-29-02-17-19.txt");
		System.out.println(maze.calcComplexity());
		maze.toFile();*/
	}
}