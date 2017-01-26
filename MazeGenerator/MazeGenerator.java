package MazeGenerator;
import java.util.Scanner; 

public class MazeGenerator {
	public static void main(String[] args) {
		Maze maze;
		//Oh test commit
		//Steven's test commit again
		//A third steven's comment
		Scanner user_input = new Scanner(System.in);;
		
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
		maze.randomizeMaze();
	//	maze = new Maze("FirstMaze",12,42);
		System.out.println(maze.toString());
		maze.toFile();
	}
}
