package MazeGenerator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

class Maze {
	// Variables
	private String title;
	private String created;
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
		this.created = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss").format(new Date());
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
		this.created = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss").format(new Date());
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
		this.created = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss").format(new Date());
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
	// Get & Set Methods
	public void setTitle(String title) {
		this.title = title;
	}
	
	// ...add all the other get and set methods here.
	
	// Calculation Methods
	public double calcBranchFactor() {
		if(this.nodeArray == null || this.nodeArray.length == 0)
			return (double)0;
		else
			return 0; // <- Not yet implemented 
	}
	
	// Randomization Method
	public void randomizeMaze() {
		
	}
	
	// Output Methods
	// Output to String
	public String toString() {
		String output = "";
		
		// Title
		if(title == "")
			output += ("<Title>" + "Not Assigned" + "</Title>" + '\n');
		else
			output += ("<Title>" + title + "</Title>" + '\n');
		// Created
		if(created == null)
			output += ("<Created>" + "Not Assigned" + "</Created>" + '\n');
		else
			output += ("<Created>" + created + "</Created>" + '\n');
		// GridSize
		if(gridSize == -1)
			output += ("<GridSize>" + "Not Assigned" + "</GridSize>" + '\n');
		else
			output += ("<GridSize>" + Integer.toString(gridSize) + "</GridSize>" + '\n');
		// ActiveNodeCount
		if(activeNodeCount == -1)
			output += ("<ActiveNodeCount>" + "Not Assigned" + "</ActiveNodeCount>" + '\n');
		else
			output += ("<ActiveNodeCount>" + Integer.toString(activeNodeCount) + "</ActiveNodeCount>" + '\n');
		// BranchFactor
		if(branchFactor == -1)
			output += ("<BranchFactor>" + "Not Calculated" + "</BranchFactor>" + '\n');
		else
			output += ("<BranchFactor>" + Double.toString(branchFactor) + "</BranchFactor>" + '\n');
		// Complexity
		if(complexity == -1)
			output += ("<Complexity>" + "Not Calculated" + "</Complexity>" + '\n');
		else
			output += ("<Complexity>" + Double.toString(complexity) + "</Complexity>" + '\n');
		// IntersectionCount
		if(intersectionCount == -1)
			output += ("<IntersectionCount>" + "Not Calculated" + "</IntersectionCount>" + '\n');
		else
			output += ("<IntersectionCount>" + Integer.toString(intersectionCount) + "</IntersectionCount>" + '\n');
		// DeadendCount
		if(deadendCount == -1)
			output += ("<DeadendCount>" + "Not Calculated" + "</DeadendCount>" + '\n');
		else
			output += ("<DeadendCount>" + Integer.toString(deadendCount) + "</DeadendCount>" + '\n');
		// LoopCount
		if(deadendCount == -1)
			output += ("<LoopCount>" + "Not Calculated" + "</LoopCount>" + '\n');
		else
			output += ("<LoopCount>" + Integer.toString(loopCount) + "</LoopCount>" + '\n');
		// Nodes
		output += "<Nodes>" + '\n';
		for(Node n : nodeArray)
			output += ('\t' + n.toString() + '\n');
		output += "</Nodes>" + '\n';
		// Nodes
		output += "<Results>" + '\n';
		//for(Result r : resultArray)
		//	output += ('\t' + r.toString() + '\n');
		output += "</Results>" + '\n';
		
		return output;
	}
	
	// Output to file
	public void toFile() {
		try {
			List<String> output = Arrays.asList(this.toString());
			Path file = Paths.get(title + "_" + Integer.toString(gridSize) + "x" + Integer.toString(gridSize) + "_" +
		                          Integer.toString(activeNodeCount) + "_" + Double.toString(branchFactor) + "_" + 
					              Double.toString(complexity) + "_" + created + ".txt");
			Files.write(file, output, Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
