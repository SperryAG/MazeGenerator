package MazeGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.lang.*;
import java.util.Random;

class Maze {
	// Variables
	private String title;
	private Timestamp created;
	private int gridSize;
	private int activeNodeCount;
	private double branchFactor;
	private double complexity;
	private int intersectionCount;
	private int deadendCount;
	private int loopCount;
	private Node[] nodeArray;
	private ArrayList<Result> resultArray;
	private boolean[][] north;     // is there a wall to north of cell i, j
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;
	
	// Constructors
	public Maze() {
		this.title = "";
		this.created = new Timestamp(System.currentTimeMillis());
		this.gridSize = -1;
		this.activeNodeCount = -1;
		this.branchFactor = -1;
		this.complexity = -1;
		this.intersectionCount = -1;
		this.deadendCount = -1;
		this.loopCount = -1;
		this.nodeArray = null;
		this.resultArray = null;
	}
	
	public Maze(String title, int gridSize) {
		this.title = title;
		this.created = new Timestamp(System.currentTimeMillis());
		this.gridSize = gridSize;
		this.activeNodeCount = -1;
		this.branchFactor = -1;
		this.complexity = -1;
		this.intersectionCount = -1;
		this.deadendCount = -1;
		this.loopCount = -1;
		this.nodeArray = null;
		this.resultArray = null;
	}
	
	public Maze(String title, int gridSize, int activeNodeCount) {
		this.title = title;
		this.created = new Timestamp(System.currentTimeMillis());
		this.gridSize = gridSize;
		this.activeNodeCount = activeNodeCount;
		this.branchFactor = -1;
		this.complexity = -1;
		this.intersectionCount = -1;
		this.deadendCount = -1;
		this.loopCount = -1;
		this.nodeArray = new Node[activeNodeCount];
		this.resultArray = null;
	}
	
	// Methods
	public String toString() {
		String output = "";
		
		// Title
		if(title == "")
			output += ("<Title>" + "Not Assigned" + "</Title>" + '\n');
		else
			output += ("<Title>" + this.title + "</Title>" + '\n');
		// Created
		if(!created)
			output += ("<Created>" + "Not Assigned" + "</Created>" + '\n');
		else
			output += ("<Created>" + this.created.toString() + "</Created>" + '\n');
		output += ("<GridSize>" + Integer.toString(this.gridSize) + "</GridSize>" + '\n');
		output += ("<ActiveNodeCount>" + Integer.toString(this.activeNodeCount) + "</ActiveNodeCount>" + '\n');
		output += ("<BranchFactor>" + Integer.toString(this.gridSize) + "</GridSize>" + '\n');
		return output;
		
	}
	
	public void randomizeMaze(){
		double nSquare = Math.sqrt(activeNodeCount);
		int n = (int) Math.ceil(nSquare);
		//initialization time. 
		visited = new boolean[n+2][n+2];
		
        for (int x = 0; x < n+2; x++) {
            visited[x][0] = true;
            visited[x][n+1] = true;
        }
        for (int y = 0; y < n+2; y++) {
            visited[0][y] = true;
            visited[n+1][y] = true;
        }


        // initialize all walls as present
        north = new boolean[n+2][n+2];
        east  = new boolean[n+2][n+2];
        south = new boolean[n+2][n+2];
        west  = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            for (int y = 0; y < n+2; y++) {
                north[x][y] = true;
                east[x][y]  = true;
                south[x][y] = true;
                west[x][y]  = true;
            }
        }
		//Goal is to randomize a maze. 
        
	}
	
	private void generate(int x, int y) {
        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {
                double r = Random.uniform(4);
                if (r == 0 && !visited[x][y+1]) {
                    north[x][y] = false;
                    south[x][y+1] = false;
                    generate(x, y + 1);
                    break;
                }
                else if (r == 1 && !visited[x+1][y]) {
                    east[x][y] = false;
                    west[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                else if (r == 2 && !visited[x][y-1]) {
                    south[x][y] = false;
                    north[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                else if (r == 3 && !visited[x-1][y]) {
                    west[x][y] = false;
                    east[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }
	
	public Double calcBranchFactor(){
		return branchFactor;
		
	}
}
