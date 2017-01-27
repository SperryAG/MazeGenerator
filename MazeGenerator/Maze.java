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
	/* Variables */
	private String title;
	private String created;
	private int gridSize;
	private int activeNodeCount;
	private int coreActiveNodeCount; // Complexity Formula (N)
	private int coreOptimalPathCount; // Complexity Formula (O)
	private int intersectionCount;
	private int coreIntersectionCount; // Complexity Formula (I)
	private int deadendCount;
	private int coreDeadendCount; // Complexity Formula (D)
	private int loopCount;
	private int coreLoopCount; // Complexity Formula (L)
	private int longestTailCount;
	private double branchFactor;
	private double complexity;
	
	private Node[] nodeArray;
	private ArrayList<Result> resultArray;
	
	/* Constructors */
	public Maze() {
		this.title = "";
		this.created = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss").format(new Date());
		this.gridSize = -1;
		this.activeNodeCount = -1;
		this.coreActiveNodeCount = -1; 
		this.coreOptimalPathCount = -1;
		this.intersectionCount = -1;
		this.coreIntersectionCount = -1;
		this.deadendCount = -1;
		this.coreDeadendCount = -1;
		this.loopCount = -1;
		this.coreLoopCount = -1;
		this.longestTailCount = -1;
		this.branchFactor = -1;
		this.complexity = -1;
		this.nodeArray = null;
		this.resultArray = new ArrayList<Result>();;
	}
	
	public Maze(String title, int gridSize) {
		this.title = title;
		this.created = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss").format(new Date());
		this.gridSize = gridSize;
		this.activeNodeCount = -1;
		this.coreActiveNodeCount = -1; 
		this.coreOptimalPathCount = -1;
		this.intersectionCount = -1;
		this.coreIntersectionCount = -1;
		this.deadendCount = -1;
		this.coreDeadendCount = -1;
		this.loopCount = -1;
		this.coreLoopCount = -1;
		this.longestTailCount = -1;
		this.branchFactor = -1;
		this.complexity = -1;
		this.nodeArray = null;
		this.resultArray = new ArrayList<Result>();;
	}
	
	public Maze(String title, int gridSize, int activeNodeCount) {
		this.title = title;
		this.created = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss").format(new Date());
		this.gridSize = gridSize;
		this.activeNodeCount = activeNodeCount;
		this.coreActiveNodeCount = -1; 
		this.coreOptimalPathCount = -1;
		this.intersectionCount = -1;
		this.coreIntersectionCount = -1;
		this.deadendCount = -1;
		this.coreDeadendCount = -1;
		this.loopCount = -1;
		this.coreLoopCount = -1;
		this.longestTailCount = -1;
		this.branchFactor = -1;
		this.complexity = -1;
		this.nodeArray = new Node[activeNodeCount];
		this.resultArray = new ArrayList<Result>();
	}
	
	/* General Methods */
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
	public void setCoreActiveNodeCount(int coreActiveNodeCount) {
		this.coreActiveNodeCount = coreActiveNodeCount;
	}
	public int getCoreActiveNodeCount() {
		return coreActiveNodeCount;
	}
	public void setCoreOptimalPathCount(int coreOptimalPathCount) {
		this.coreOptimalPathCount = coreOptimalPathCount;
	}
	public int getCoreOptimalPathCount() {
		return coreOptimalPathCount;
	}
	public void setintersectionCount(int intersectionCount) {
		this.intersectionCount = intersectionCount;
	}
	public int getintersectionCount() {
		return intersectionCount;
	}
	public void setCoreIntersectionCount(int coreIntersectionCount) {
		this.coreIntersectionCount = coreIntersectionCount;
	}
	public int getCoreIntersectionCount() {
		return coreIntersectionCount;
	}
	public void setDeadendCount(int deadendCount) {
		this.deadendCount = deadendCount;
	}
	public int getDeadendCount() {
		return deadendCount;
	}
	public void setCoreDeadendCount(int coreDeadendCount) {
		this.coreDeadendCount = coreDeadendCount;
	}
	public int getCoreDeadendCount() {
		return coreDeadendCount;
	}
	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}
	public int getLoopCount() {
		return loopCount;
	}
	public void setCoreLoopCount(int coreLoopCount) {
		this.coreLoopCount = coreLoopCount;
	}
	public int getCoreLoopCount() {
		return coreLoopCount;
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
	// Return the endNode in nodeArray
	private Node getEndNode()
	{
		for(Node n : nodeArray)
			if(n.getIsEndNode())
				return n;
		return null;
	}
	// Return a node in nodeArray based on coordinates
	private Node getCoordNode(int x, int y)
	{
		for(Node n : nodeArray)
			if(n.getXCoord() == x && n.getYCoord() == y)
				return n;
		return null;
	}
	// Get the next node based on the current node and desired direction
	private Node getNextNode(Node node, char direction)
	{
		switch (Character.toUpperCase(direction))
		{
			case 'N':
				return getCoordNode(node.getXCoord(),node.getYCoord() + 1);
			case 'E':
				return getCoordNode(node.getXCoord() + 1,node.getYCoord());
			case 'S':
				return getCoordNode(node.getXCoord(),node.getYCoord() - 1);
			case 'W':
				return getCoordNode(node.getXCoord() - 1,node.getYCoord());
			default:
				return null;
		}
	}
	// Get the number of outgoing paths based on an incoming direction.
	private int getOutPathCount(Node node, char inFrom)
	{
		int count = 0;
		if(!node.getNorthWall() && Character.toUpperCase(inFrom) != 'N')
			count++;
		if(!node.getEastWall() && Character.toUpperCase(inFrom) != 'E')
			count++;
		if(!node.getSouthWall() && Character.toUpperCase(inFrom) != 'S')
			count++;
		if(!node.getWestWall() && Character.toUpperCase(inFrom) != 'W')
			count++;
		return count;
	}
	// If a node has a branching factor of 2/4, return the out direction based on the
	// in direction.
	private char getOutPathDirection(Node node, char inFrom)
	{
		if(!node.getNorthWall() && Character.toUpperCase(inFrom) != 'N')
			return 'N';
		else if(!node.getEastWall() && Character.toUpperCase(inFrom) != 'E')
			return 'E';
		else if(!node.getSouthWall() && Character.toUpperCase(inFrom) != 'S')
			return 'S';
		else if(!node.getWestWall() && Character.toUpperCase(inFrom) != 'W')
			return 'W';
		else
			return 'X';
	}
	// Get the opposite direction of a given direction
	private char getOppositeDirection(char direction)
	{
		switch(Character.toUpperCase(direction))
		{
			case 'N':
				return 'S';
			case 'E':
				return 'W';
			case 'S':
				return 'N';
			case 'W':
				return 'E';
			default:
				return 'X'; // Undefined
		}
	}
	
	/* Maze Methods */
	public void generateMaze()
	{
		Random rand = new Random();
		boolean[][] maze = new boolean[gridSize][gridSize];
		
		/* Randomize gridSize if needed */
		if(gridSize == -1)
			gridSize = rand.nextInt(48) + 2; // random Integer from 2-50
		
		/* Randomize activeNodeCount if needed */
		if(activeNodeCount == -1)
			activeNodeCount = rand.nextInt(gridSize*gridSize) + 1;
		
		/* Generate random maze nodes and store in nodeArray */
		populateNodeArray();
		
		/* Generate random start Node */
		int randNode = rand.nextInt(nodeArray.length);
		nodeArray[randNode].setStartNode(true);
		
		/* Generate random end Node different from start Node */
		do {randNode = rand.nextInt(nodeArray.length);}
		while(nodeArray[randNode].getIsStartNode());
		nodeArray[randNode].setEndNode(true);
		
		/* Find the full optimal path(s) and set isOptimalPath = true for involved nodes */
		setOptimalPathNodes();
		
		/* Find and Set the isEndIntersection Node */
		setEndIntersectionNode();
		
		/* Set isCoreNode = true for every core node and calculate coreActiveNodeCount */
		coreActiveNodeCount = setCoreNodes();
		
		/* Find and Set isCoreOptimalPath = true for coreOptimalPath nodes and set coreOptimalPathCount */
		coreOptimalPathCount = setCoreOptimalPath();
		
		/* Find and Set isIntersection / isCoreIntersection and IntersectionCount / coreIntersectionCount.
		   coreIntersections includes start and I_end nodes */
		intersectionCount = setIntersectionNodes();
		coreIntersectionCount = setCoreIntersectionNodes();
		
		/* Find and Set isDeadend / isCoreDeadend for last node of a deadend path and set
		   deadendCount / coreDeadendCount */
		deadendCount = setDeadendNodes();
		coreDeadendCount = setCoreDeadendNodes();
		
		/* Loops - Not sure how to do this - Can just adjust this value manually in the maze file for now. */
		
		/* Find and Set isLongestTail and longestTailCount */
		longestTailCount = setLongestTailNodes();
		
		/* Calculate and Set branchFactor */
		branchFactor = calcBranchFactor();
		
		/* Calculate and Set complexity */
		complexity = calcComplexity();
	}
	
	/* Generation Methods */
	// Given gridSize and activeNodeCount, generate a random maze
	private void populateNodeArray()
	{
		//Randomize if needed 
		double nSquare = Math.sqrt(activeNodeCount);
  		int n = (int) Math.ceil(nSquare);
  		//initialization time.
		//Generate random nodes 
		int count = 0;
		ArrayList<Node> tempArray = new ArrayList<Node>();
		Set<Pair> coords = new HashSet<Pair>();
		for(int times = 0; times < this.activeNodeCount; times++)
		{
			if(times == 0)
			{
			   Node toAdd = new Node();
			   toAdd.setXCoord(this.gridSize/2);
			   toAdd.setYCoord(this.gridSize/2);
			   tempArray.add(toAdd);
			   coords.add(new Pair(this.gridSize/2, this.gridSize/2));
			}
			else
			{
			   Node toAdd = new Node();
			   Random rand = new Random();
			   int  randomPick = rand.nextInt(tempArray.size());
			   Node randomNode = tempArray.get(randomPick);
			   while(randomNode.isFull()){
				   this.nodeArray[count] = randomNode;
				   count++;
//				   tempArray.remove(randomPick);
				   randomPick = rand.nextInt(tempArray.size());
				   randomNode = tempArray.get(randomPick);
			   }
			   ArrayList<Integer> dirArray = new ArrayList<Integer>();
			   dirArray = randomNode.getWalls(coords, this.gridSize);
			   int randomDirection = rand.nextInt(dirArray.size());
			   randomDirection = dirArray.get(randomDirection);
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
			   coords.add(new Pair(toAdd.getXCoord(), toAdd.getYCoord()));
			   tempArray.add(toAdd);
		   }
       }//end for for loop
		Node[] nodeArr = new Node[tempArray.size()];
		nodeArray = tempArray.toArray(nodeArr);
		/*for(Node node : nodeArr){
			this.nodeArray[count] = node;
			count++;
		}*/
		for(Node node: nodeArray){
			node.updateWalls(coords);
		}
		//String[] both = (String[])ArrayUtils.addAll(first, second);
	}
	// Find and Set the nodes involved in the optimal path(s)
	private void setOptimalPathNodes()
	{
		Map<Integer, Set<Pair>> BFS = new HashMap<Integer, Set<Pair>>();
		Set<Pair> availableCoords = new HashSet<Pair>();
		for (Node n: nodeArray) {
			if (n.getIsStartNode()) {
				BFS.put(0, n.getCoords());
				availableCoords = n.isPath();
				break;
			}
		}
		int count = 1;
		boolean isEnd = false;
		while(!isEnd) {
			Set<Pair> temp = new HashSet<Pair>();
//			System.out.println("availableCoords: " + availableCoords);
			for (Pair p : availableCoords) {
				for (Node n : this.nodeArray) {
					if (n.getXCoord() == p.getXCoord() && n.getYCoord() == p.getYCoord()) { // n.getXYCoords() == p) {
						if (n.getIsEndNode()) {
							isEnd = true;
							return count;
						}
						Set<Pair> toAdd = new HashSet<Pair>();
						if (BFS.containsKey(count)) {
							for (Pair i : BFS.get(count)) {
								if (!toAdd.contains(i)) {
									toAdd.add(i);
								}
							}
//							toAdd = BFS.get(count);
						}
						if (!toAdd.contains(n.getCoords())) {
							toAdd.addAll(n.getCoords());	
						}
						BFS.put(count, toAdd);
						temp.addAll(n.isPath());
					}
				}
			}
			count++;
			availableCoords = temp;
		}
		return count;
	}
	// Find and Set the end intersection node
	private void setEndIntersectionNode()
	{
		// Get the endNode from nodeArray
		Node endNode = getEndNode();
		
		// Get the coordinates of the intersections directly connected to the end node
		Node tempNode;
		char direction;
		ArrayList<Integer[]> coordArray = new ArrayList<Integer[]>();
		// Get the North path direct end intersection if it exists
		if(!endNode.getNorthWall()) 
		{
			direction = 'N';
			tempNode = getNextNode(endNode,direction);
			while(getOutPathCount(tempNode,getOppositeDirection(direction)) == 1)
			{
				direction = getOutPathDirection(tempNode,direction);
				tempNode = getNextNode(tempNode,direction);
			}
		}
		
			// Follow end node back to first intersection(s) and store them temporarily
			// Follow optimal path from start.
				// Choose the first intersection stepped on out of the following:
					// Any of the direct end intersections found in step one.
					// An optimal path split.
	}
	// Set core nodes and coreActiveNodeCount
	private int setCoreNodes()
	{
		return 0;
	}
	// Set core optimal path nodes and coreOptimalPathCount
	private int setCoreOptimalPath()
	{
		int count = 0;
		for(Node n : nodeArray)
			if(n.getIsOptimalPath() && n.getIsCoreNode())
			{
				n.setIsCoreOptimalPath(true);
				count++;
			}
		return count;
	}
	// Set intersection nodes and return intersection count
	public int setIntersectionNodes()
	{
		if(this.nodeArray == null || this.nodeArray.length == 0)
			return (int)0;
		else
		{
			int temp = 0;
			int intersection = 0; 
			for (Node i  : nodeArray)
			{
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
	// Set core intersection nodes and coreIntersectionCount
	public int setCoreIntersectionNodes() 
	{
		int count = 0;
		for(Node n : nodeArray)
			if(n.getIsIntersection() && n.getIsCoreNode())
			{
				n.setIsCoreIntersection(true);
				count++;
			}
		return count;
	}
	// Set deadend nodes and deadendCount
	public int setDeadendNodes()
	{
		if(this.nodeArray == null || this.nodeArray.length == 0)
			return (int)0;
		else
		{
			int temp = 0;
			int deadend = 0; 
			for (Node i  : nodeArray)
			{
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
	// Set core deadend nodes and coreDeadendCount
	public int setCoreDeadendNodes() 
	{
		int count = 0;
		for(Node n : nodeArray)
			if(n.getIsDeadend() && n.getIsCoreNode())
			{
				n.setIsCoreDeadend(true);
				count++;
			}
		return count;
	}
	// Set longest tail nodes and longestTailCount
	public int setLongestTailNodes()
	{
//		Map<Integer, Set<Pair>> BFS = new HashMap<Integer, Set<Pair>>();
//		Set<Pair> availableCoords = new HashSet<Pair>();
//		for (Node n: nodeArray) {
//			if (n.getEndIntersection()) {
//				BFS.put(0, n.getCoords());
//				availableCoords = n.isPath();
//				break;
//			}
//		}
		int count = 1, currentCount = 1;
//		boolean isEnd = false;
//		while(!isEnd) {
//			Set<Pair> temp = new HashSet<Pair>();
//			System.out.println("availableCoords: " + availableCoords);
//			for (Pair p : availableCoords) {
//				for (Node n : this.nodeArray) {
//					if (n.getXCoord() == p.getXCoord() && n.getYCoord() == p.getYCoord()) { // n.getXYCoords() == p) {
//						if (n.getIsEndNode()) {
//							isEnd = true;
//							count = currentCount;
//						}
//						Set<Pair> toAdd = new HashSet<Pair>();
//						if (BFS.containsKey(count)) {
//							for (Pair i : BFS.get(count)) {
//								if (!toAdd.contains(i)) {
//									toAdd.add(i);
//								}
//							}
////							toAdd = BFS.get(count);
//						}
//						if (!toAdd.contains(n.getCoords())) {
//							toAdd.addAll(n.getCoords());	
//						}
//						BFS.put(count, toAdd);
//						temp.addAll(n.isPath());
//					}
//				}
//			}
//			currentCount++;
//			availableCoords = temp;
//		}
		return count;
	}
	// Calculate the branch factor for the maze
	public double calcBranchFactor() 
	{
		if(this.nodeArray == null || this.nodeArray.length == 0)
			return (double)0;
		else
		{
			int temp = 0;
			int walls = 0; 
			int intersection = 0 ; 
			for (Node i : nodeArray)
			{
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
			branchFactor = (walls/intersection) * 4;
			return branchFactor; // <- Not yet implemented 
		}
	}
	// Calculate the complexity for the maze
	public double calcComplexity() 
	{
		return 2 * activeNodeCount + /*branchSum*/ - coreDeadendCount - coreLoopCount + longestTailCount;		
	}

	/* Output Methods */
	// Output to String
	public String toString() 
	{
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
	public void toFile() 
	{
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