package MazeGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;

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
	
	public Double calcBranchFactor()
}
