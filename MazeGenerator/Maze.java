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
import java.util.Random;

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
		this.resultArray = new ArrayList<Result>();;
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
		this.resultArray = new ArrayList<Result>();;
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
		this.resultArray = new ArrayList<Result>();
	}
	
	// Methods
	// Get & Set Methods
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getCreated() {
		return created;
	}
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
	public int getGridSize() {
		return gridSize;
	}
	public void setActiveNodeCount(int activeNodeCount) {
		this.activeNodeCount = activeNodeCount;
	}
	public int getActiveNodeCount() {
		return activeNodeCount;
	}
	public void setBranchFactor(double branchFactor) {
		this.branchFactor = branchFactor;
	}
	public double getBranchFactor() {
		return branchFactor;
	}
	public void setComplexity(double complexity) {
		this.complexity = complexity;
	}
	public double getComplexity() {
		return complexity;
	}
	public void setIntersectionCount(int intersectionCount) {
		this.intersectionCount = intersectionCount;
	}
	public int getIntersectionCount() {
		return intersectionCount;
	}
	public void setDeadendCount(int deadendCount) {
		this.deadendCount = deadendCount;
	}
	public int getDeadendCount() {
		return deadendCount;
	}
	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}
	public int getLoopCount() {
		return loopCount;
	}
	
	// Calculation Methods
	public double calcBranchFactor() {
		if(this.nodeArray == null || this.nodeArray.length == 0){
			return (double)0;
			}
		else{
			int temp = 0;
			int walls = 0; 
			int intersection = 0 ; 
			for (Node i  : nodeArray){
					if(i.getEastWall() == true){
						temp ++; 
					}
					if(i.getNorthWall() == true){
						temp ++; 
					}
					if(i.getWestWall() == true){
						temp ++; 
					}
					if(i.getSouthWall() == true){
						temp ++; 
					}
					
					if(temp >= 3 || i.getIsStartNode()  == true){ //Intersection 
						temp = 4 - temp;
						walls =+ temp; 
						intersection++;
					}
				
				
			}
			branchFactor  =( walls/ intersection ) * 4 ;
			return branchFactor; // <- Not yet implemented 
		}
	}
	
	
	public int calcIntersection() {
		if(this.nodeArray == null || this.nodeArray.length == 0){
			return (int)0;
			}
		else{
			int temp = 0;
			int intersection = 0; 
			for (Node i  : nodeArray){
					if(i.getEastWall() == true){
						temp ++; 
					}
					if(i.getNorthWall() == true){
						temp ++; 
					}
					if(i.getWestWall() == true){
						temp ++; 
					}
					if(i.getSouthWall() == true){
						temp ++; 
					}
		
					if(temp >= 3 || i.getIsStartNode() == true){ //Intersection 
						intersection++;
					}
				
				
			}
			return intersection; // <- Not yet implemented 
		}
	}
	
	public int calcDeadend() {
		if(this.nodeArray == null || this.nodeArray.length == 0){
			return (int)0;
			}
		else{
			int temp = 0;
			int deadend = 0; 
			for (Node i  : nodeArray){
					if(i.getEastWall() == true){
						temp ++; 
					}
					if(i.getNorthWall() == true){
						temp ++; 
					}
					if(i.getWestWall() == true){
						temp ++; 
					}
					if(i.getSouthWall() == true){
						temp ++; 
					}
		
					if(temp < 2 && i.getIsStartNode() == false && i.getIsEndNode() == false ){ //Intersection 
						deadend++;
					}
				
				
			}
			return deadend; // <- Not yet implemented 
		}
	}
	
	public double calcComplexity() { //Incomplete
		
		complexity = 2 * activeNodeCount + branchFactor - deadendCount - loopCount  ;		
		return complexity ; 
		
	}
	
	// Randomization Method
	public void randomizeMaze(){//this will call generate

		//Goal is to randomize a maze.    
	}
	
	private void generate() {
		int count = 0;
		ArrayList<Node> tempArray = new ArrayList<Node>();
		for(int times = 0; times < this.activeNodeCount; times++){
			if(times == 0){
			   Node toAdd = new Node();
			   toAdd.setXCoord(this.gridSize/2);
			   toAdd.setYCoord(this.gridSize/2);
			   tempArray.add(toAdd);    		   
			}
			else{
			   Node toAdd = new Node();
			   Random rand = new Random();
			   int  randomPick = rand.nextInt(tempArray.size()-1);
			   Node randomNode = tempArray.get(randomPick);
			   while(randomNode.isFull()){
				   this.nodeArray[count] = randomNode;
				   count++;
				   tempArray.remove(randomPick);
				   randomPick = rand.nextInt(tempArray.size()-1);
				   randomNode = tempArray.get(randomPick);
			   }
			   int randomDirection = rand.nextInt(3);
			   if(randomDirection == 0){//this is north
				   toAdd.setXCoord(randomNode.getXCoord());
				   toAdd.setYCoord(randomNode.getYCoord() + 1);
				   toAdd.setSouthWall(false);
				   randomNode.setNorthWall(false);
				   tempArray.set(randomPick, randomNode);
			   }
			   else if(randomDirection == 1){ //this is east
				   toAdd.setXCoord(randomNode.getXCoord()+1);
				   toAdd.setYCoord(randomNode.getYCoord());
				   toAdd.setWestWall(false);
				   randomNode.setEastWall(false);
				   tempArray.set(randomPick, randomNode);
			   }
			   else if(randomDirection == 2){ //this is south
				   toAdd.setXCoord(randomNode.getXCoord());
				   toAdd.setYCoord(randomNode.getYCoord()-1);
				   toAdd.setNorthWall(false);
				   randomNode.setSouthWall(false);
				   tempArray.set(randomPick, randomNode);
			   }
			   else{//this is west
				   toAdd.setXCoord(randomNode.getXCoord()-1);
				   toAdd.setYCoord(randomNode.getYCoord());
				   toAdd.setEastWall(false);
				   randomNode.setWestWall(false);
				   tempArray.set(randomPick, randomNode);
			   }
			   tempArray.add(toAdd);
		   }
       }//end for for loop
		//String[] both = (String[])ArrayUtils.addAll(first, second);
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
		if(nodeArray != null && nodeArray.length > 0) {
			for(Node n : nodeArray) {
				if(n != null) {
					output += ('\t' + n.toString() + '\n');
				}
			}	
		}
		output += "</Nodes>" + '\n';
		
		// Results
		output += "<Results>" + '\n';
		if(resultArray != null && resultArray.size() > 0) {
			for(Result r : resultArray) {
				if(r != null) {
					output += ('\t' + r.toString() + '\n');
				}
			}	
		}
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