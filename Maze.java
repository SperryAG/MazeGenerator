package MazeGenerator;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class Maze {
	/* Variables */
	private String title;
	private String created;
	private int gridSize;
	private int activeNodeCount;
	private int coreActiveNodeCount; // Complexity Formula (N)
	private int coreOptimalPathNodeCount; // Complexity Formula (O)
	private int intersectionCount;
	private int coreIntersectionCount; // Complexity Formula (I)
	private int deadendCount;
	private int coreDeadendCount; // Complexity Formula (D)
	private int loopCount;
	private int coreLoopCount; // Complexity Formula (L)
	private int longestTailCount;
	private double branchFactor;
	private int complexity;
	private Node[] nodeArray;
	private ArrayList<Result> resultArray;
	
	/* Constructors */
	public Maze() {
		this.title = "";
		this.created = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss").format(new Date());
		this.gridSize = -1;
		this.activeNodeCount = -1;
		this.coreActiveNodeCount = -1; 
		this.coreOptimalPathNodeCount = -1;
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
		this.coreOptimalPathNodeCount = -1;
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
		this.coreOptimalPathNodeCount = -1;
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
	public void setCoreOptimalPathNodeCount(int coreOptimalPathCount) {
		this.coreOptimalPathNodeCount = coreOptimalPathCount;
	}
	public int getCoreOptimalPathNodeCount() {
		return coreOptimalPathNodeCount;
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
	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}
	public double getComplexity() {
		return complexity;
	}
	// Return the startNode in nodeArray
		private Node getStartNode()
		{
			for(Node n : nodeArray)
				if(n.getIsStartNode())
					return n;
			return null;
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
	// Get a node's path count
	private int getPathCount(Node node)
	{
		int count = 0;
		if(!node.getNorthWall())
			count++;
		if(!node.getEastWall())
			count++;
		if(!node.getSouthWall())
			count++;
		if(!node.getWestWall())
			count++;
		return count;
	}
	// Traverse all nodes from input node and mark them visited until isEndIntersection node is reached.
	private void step(Node node, boolean[][] visited)
	{
		int xCoord = node.getXCoord();
		int yCoord = node.getYCoord();
		
		// If current Node has not been visited
		if(!visited[xCoord][yCoord])
		{
			// Mark visited as true
			visited[xCoord][yCoord] = true;
			// Step into available out paths.
			if(!node.getNorthWall() && !visited[xCoord][yCoord + 1])
			{
				// If current Node is iEnd, then we only want to find the deadends connected to it.
				if(node.getIsEndIntersection())
					stepDeadEnd(getNextNode(node,'N'),visited);
				// If current Node is not iEnd, just step.
				else
					step(getNextNode(node,'N'),visited);
			}
			if(!node.getEastWall() && !visited[xCoord + 1][yCoord])
			{
				if(node.getIsEndIntersection())
					stepDeadEnd(getNextNode(node,'E'),visited);
				else
					step(getNextNode(node,'E'),visited);
			}
			if(!node.getSouthWall() && !visited[xCoord][yCoord - 1])
			{
				if(node.getIsEndIntersection())
					stepDeadEnd(getNextNode(node,'S'),visited);
				else
					step(getNextNode(node,'S'),visited);
			}
			if(!node.getWestWall() && !visited[xCoord - 1][yCoord])
			{
				if(node.getIsEndIntersection())
					stepDeadEnd(getNextNode(node,'W'),visited);
				else
					step(getNextNode(node,'W'),visited);
			}
		}
	}
	// Traverse paths out of endIntersection that havnt beed visited and mark deadend paths visited.
	private void stepDeadEnd(Node node, boolean[][] visited)
	{
		Node temp;
		char direction = 'N';
		// Set the isEndIntersection to visited
		visited[node.getXCoord()][node.getYCoord()] = true;
		// If North path is a deadend, add the nodes as visited
		if(!node.getNorthWall())
		{
			temp = getNextNode(node,'N');
			while(!visited[temp.getXCoord()][temp.getYCoord()] && !temp.getIsIntersection() && !temp.getIsEndNode())
			{
				direction = getOutPathDirection(temp,direction);
				temp = getNextNode(temp,getOppositeDirection(direction));
			}
			if(temp.getIsDeadend() && !visited[temp.getXCoord()][temp.getYCoord()])
					step(temp,visited);
		}
		// If East path is a deadend, add the nodes as visited
		if(!node.getEastWall())
		{
			temp = getNextNode(node,'E');
			while(!visited[temp.getXCoord()][temp.getYCoord()] && !temp.getIsIntersection() && !temp.getIsEndNode())
			{
				direction = getOutPathDirection(temp,direction);
				temp = getNextNode(temp,getOppositeDirection(direction));
			}
			if(temp.getIsDeadend() && !visited[temp.getXCoord()][temp.getYCoord()])
				step(temp,visited);
		}
		// If South path is a deadend, add the nodes as visited
		if(!node.getSouthWall())
		{
			temp = getNextNode(node,'S');
			while(!visited[temp.getXCoord()][temp.getYCoord()] && !temp.getIsIntersection() && !temp.getIsEndNode())
			{
				direction = getOutPathDirection(temp,direction);
				temp = getNextNode(temp,getOppositeDirection(direction));
			}
			if(temp.getIsDeadend())
					step(temp,visited);
		}
		// If West path is a deadend, add the nodes as visited
		if(!node.getWestWall())
		{
			temp = getNextNode(node,'W');
			while(!visited[temp.getXCoord()][temp.getYCoord()] && !temp.getIsIntersection() && !temp.getIsEndNode())
			{
				direction = getOutPathDirection(temp,direction);
				temp = getNextNode(temp,getOppositeDirection(direction));
			}
			if(temp.getIsDeadend())
					step(temp,visited);
		}
	}
	// Return the number of outpaths that are optimal
	public int getOptimalPathOutCount(Node node)
	{
		int count = 0;
		Node temp;
		if(!node.getNorthWall())
		{
			temp = getNextNode(node,'N');
			if(temp.getIsOptimalPath())
				count++;
		}
		if(!node.getEastWall())
		{
			temp = getNextNode(node,'E');
			if(temp.getIsOptimalPath())
				count++;
		}
		if(!node.getSouthWall())
		{
			temp = getNextNode(node,'S');
			if(temp.getIsOptimalPath())
				count++;
		}
		if(!node.getWestWall())
		{
			temp = getNextNode(node,'W');
			if(temp.getIsOptimalPath())
				count++;
		}
		return count;
	}
	// Get the nextOptimalNode base on a specific input direction
	public Node getNextOptimalNode(Node node, char direction)
	{
		if(!node.getNorthWall() && getNextNode(node,'N').getIsOptimalPath() && direction != 'N')
			return getNextNode(node,'N');
		if(!node.getEastWall() && getNextNode(node,'E').getIsOptimalPath() && direction != 'E')
			return getNextNode(node,'E');
		if(!node.getSouthWall() && getNextNode(node,'S').getIsOptimalPath() && direction != 'S')
			return getNextNode(node,'S');
		if(!node.getWestWall() && getNextNode(node,'W').getIsOptimalPath() && direction != 'W')
			return getNextNode(node,'W');
		return null;

	}
	// Evaluate whether a node matches a node in an arraylist
	public boolean nodeFound(Node node, ArrayList<Node> nodeArray)
	{
		for(Node n : nodeArray)
			if(n.equals(node))
				return true;
		return false;
	}
	
	/* Maze Methods */
	public void generateMaze()
	{
		Random rand = new Random();
		
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
		
		/* Find the intersection nodes and set isIntersection = true for involved nodes. Return the intersection count. */
		intersectionCount = setIntersectionNodes();
		
		/* Find and Set isDeadend for last node of a deadend path. */
		deadendCount = setDeadendNodes();
		
		/* Find and Set the isEndIntersection Node */
		//setEndIntersectionNode();
		
		/* Set isCoreNode = true for every core node and calculate coreActiveNodeCount */
		coreActiveNodeCount = setCoreNodes();
		
		/* Set the core variable counts */
		coreOptimalPathNodeCount = calcCoreOptimalPathNodeCount();
		coreIntersectionCount = calcCoreIntersectionCount();
		coreDeadendCount = calcCoreDeadendCount();
		
		/* Loops - Not sure how to do this - Can just adjust this value manually in the maze file for now. */
		
		/* Find the longest tail and set isLongestTail = true for involved nodes. Return the longestTailCount */
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
	
	private Map<Integer, Set<Pair>> optimalPathCoords(){
		Map<Integer, Set<Pair>> BFS = new HashMap<Integer, Set<Pair>>();
		Set<Pair> availableCoords = new HashSet<Pair>();
		for (Node n: nodeArray) {
			if (n.getIsStartNode()) {
				BFS.put(0, n.getCoords());
				availableCoords = n.isPath();//isPath returns the set of x,y coordinates that it can travel to
				System.out.println("Available Coords from the start node: " + BFS);
				break;
			}
		}
		int count = 1;
		boolean isEnd = false;
		while(isEnd == false) {
			Set<Pair> updateAvailableCoords = new HashSet<Pair>();
//			System.out.println("availableCoords: " + availableCoords);
			for (Pair p : availableCoords) {
				for (Node n : this.nodeArray) {
					if (n.getXCoord() == p.getXCoord() && n.getYCoord() == p.getYCoord()) { // n.getXYCoords() == p) {
						if (n.getIsEndNode()) {//if the next coordinate is the end node. 
							isEnd = true;
							BFS.remove(count);
							BFS.put(count, n.getCoords());//leave the end node in its own array. 
							return BFS;
						}
						Set<Pair> toAdd = new HashSet<Pair>(); //toAdd is the next level of coordinates that can be reached. 
						if (BFS.containsKey(count)) {
							for (Pair i : BFS.get(count)) { //get all the available coordinates already that can be reached?
								if (toAdd.contains(i) == false) {
									toAdd.add(i);
								}
							}
						}
						if (toAdd.contains(n.getCoords()) == false) {
							toAdd.addAll(n.getCoords());	
						}
						BFS.put(count, toAdd);
						updateAvailableCoords.addAll(n.isPath());
					}
				}
			}//end of for loop of availableCoords. 
			count++;
			availableCoords = updateAvailableCoords;
		}
		return BFS;
	}
	
	//this will update and replace a Node
	private void updateNode(Node toUpdate){
		for(int i = 0; i < this.nodeArray.length; i++){
			Node temp = nodeArray[i];
			if(temp.getXYCoords() == toUpdate.getXYCoords()){
				nodeArray[i] = toUpdate;
			}
		}
	}
	
	private void isNeighbor(Node toCheck){
		
	}
	// Find and Set the nodes involved in the optimal path(s)
	private void setOptimalPathNodes()
	{
		Node setOptLocal = new Node();
		Map<Integer, Set<Pair>> optimalPathCoords = optimalPathCoords();
		System.out.println("optimalPathCoords: " + optimalPathCoords);
		Set<Pair> endCoords = optimalPathCoords.get(optimalPathCoords.size()-1);
		System.out.println("endCoordsSet: " + endCoords + " endCoords:");//print statement of end coords
		for(int i = optimalPathCoords.size()-1; i >= 0;i--){
			Set<Pair> coordinates = optimalPathCoords.get(i);
			System.out.println("coordinates: " + coordinates);
			for(Pair p: coordinates){
				System.out.println("p: " + coordinates);
				Node temp = this.getCoordNode(p.getXCoord(), p.getYCoord());
				//System.out.println(temp);
				if(temp.getIsEndNode()){
					temp.setIsOptimalPath(true);
					//System.out.println("endNode " + temp);
					setOptLocal = temp;
					updateNode(temp);
				}
				else{
					System.out.print("printWalls: ");
					temp.printWalls();
					System.out.print("\n");
					if(setOptLocal.getNorthWall() && temp.getSouthWall()){
						temp.setIsOptimalPath(true);
						setOptLocal = temp;
						updateNode(temp);
					}
					else if(setOptLocal.getEastWall() && temp.getWestWall()){
						temp.setIsOptimalPath(true);
						setOptLocal = temp;
						updateNode(temp);
					}
					else if(setOptLocal.getSouthWall() && temp.getNorthWall()){						
						temp.setIsOptimalPath(true);
						setOptLocal = temp;
						updateNode(temp);
					}
					else if(setOptLocal.getWestWall() && temp.getEastWall()){						
						temp.setIsOptimalPath(true);
						setOptLocal = temp;
						updateNode(temp);
					}
				}
			}
		}
		for(Node n: this.nodeArray){
			System.out.print(n.getXYCoords()+ " " + n.getIsOptimalPath() + " ");
		}
		System.out.println("\n" + optimalPathCoords);
	}
	// Find and Set the end intersection node
	private void setEndIntersectionNode()
	{
		// Get the endNode from nodeArray
		Node endNode = getEndNode();
		
		// Get the coordinates of the intersections directly connected to the end node
		Node tempNode;
		char direction;
		ArrayList<Node> directIntersections = new ArrayList<Node>();
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
			if(tempNode.getIsIntersection())
				directIntersections.add(tempNode);
		}
		// East Path
		if(!endNode.getEastWall()) 
		{
			direction = 'E';
			tempNode = getNextNode(endNode,direction);
			while(getOutPathCount(tempNode,getOppositeDirection(direction)) == 1)
			{
				direction = getOutPathDirection(tempNode,direction);
				tempNode = getNextNode(tempNode,direction);
			}
			if(tempNode.getIsIntersection())
				directIntersections.add(tempNode);
		}
		// South Path
		if(!endNode.getSouthWall()) 
		{
			direction = 'S';
			tempNode = getNextNode(endNode,direction);
			while(getOutPathCount(tempNode,getOppositeDirection(direction)) == 1)
			{
				direction = getOutPathDirection(tempNode,direction);
				tempNode = getNextNode(tempNode,direction);
			}
			if(tempNode.getIsIntersection())
				directIntersections.add(tempNode);
		}
		// West Path
		if(!endNode.getWestWall()) 
		{
			direction = 'W';
			tempNode = getNextNode(endNode,direction);
			while(getOutPathCount(tempNode,getOppositeDirection(direction)) == 1)
			{
				direction = getOutPathDirection(tempNode,direction);
				tempNode = getNextNode(tempNode,direction);
			}
			if(tempNode.getIsIntersection())
				directIntersections.add(tempNode);
		}
		
		// Follow the optimal path from start until there is an optimal path split or 
		// one of the above direct intersections is stepped on.
		tempNode = getStartNode();
		if(getOptimalPathOutCount(tempNode) > 1)
			tempNode.setEndIntersection(true);
		else
		{
			// Set the direction of the optimal path to correctly get the next optimal path
			if(!tempNode.getNorthWall() && getNextNode(tempNode,'N').getIsOptimalPath())
				direction = getOppositeDirection('N');
			else if(!tempNode.getEastWall() && getNextNode(tempNode,'E').getIsOptimalPath())
				direction = getOppositeDirection('E');
			else if(!tempNode.getSouthWall() && getNextNode(tempNode,'S').getIsOptimalPath())
				direction = getOppositeDirection('S');
			else
				direction = getOppositeDirection('W');
			tempNode = getNextOptimalNode(tempNode,'X');
			while(getOptimalPathOutCount(tempNode) == 2 && !nodeFound(tempNode,directIntersections))
			{
				tempNode = getNextOptimalNode(tempNode,direction);
			}
		}
		tempNode.setEndIntersection(true);
	}
	// Set core nodes and coreActiveNodeCount
	private int setCoreNodes()
	{
		int count = 0;
		Node current = getStartNode();
		boolean[][] visited = new boolean[51][51];
		
		step(current,visited);
		for(Node n : nodeArray)
		{
			if(visited[n.getXCoord()][n.getYCoord()])
			{
				n.setIsCoreNode(true);
				count++;
			}
		}
		return count;
	}
	// Calculate and return the coreOptimalPathNodeCount
	private int calcCoreOptimalPathNodeCount()
	{
		int count = 0;
		for(Node n : nodeArray)
			if(n.getIsOptimalPath() && n.getIsCoreNode())
				count++;
		return count;
	}
	// Set intersection nodes and return intersection count
	public int setIntersectionNodes()
	{
		if(this.nodeArray == null || this.nodeArray.length == 0)
			return 0;
		else
		{
			int count = 0;
			for(Node n : nodeArray)
				if((n.getIsStartNode() || getPathCount(n) >= 3) && !n.getIsEndNode())
				{
					n.setIsIntersection(true);
					count++;
				}
			return count;
		}
	}
	// Set the coreIntersectionCount
	public int calcCoreIntersectionCount() 
	{
		int count = 0;
		for(Node n : nodeArray)
			if(n.getIsIntersection() && n.getIsCoreNode())
				count++;
		return count;
	}
	// Set deadend nodes and deadendCount
	public int setDeadendNodes()
	{
		if(this.nodeArray == null || this.nodeArray.length == 0)
			return 0;
		else
		{
			int count = 0;
			for(Node n : nodeArray)
				if(!n.getIsStartNode() && !n.getIsEndNode() && getPathCount(n) == 1)
				{
					n.setIsDeadend(true);
					count++;
				}
			return count;
		}
	}
	// Set the coreDeadendCount
	public int calcCoreDeadendCount() 
	{
		int count = 0;
		for(Node n : nodeArray)
			if(n.getIsDeadend() && n.getIsCoreNode())
				count++;
		return count;
	}
	// Find the longest tail and return its count. 
	// !isCoreNode can be use determine which nodes come after the isEndIntersectionNode
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
			int total = 0;
			for(Node n : nodeArray)
				if(n.getIsIntersection())
					total += getPathCount(n);  
			return total / (intersectionCount*4);
		}
			
	}
	// Calculate the core branch summation for complexity
	private int calcBranchSum()
	{
		int total = 0;
		int count = 0;
		for(Node n : nodeArray)
		{
			if(n.getIsCoreNode() && n.getIsIntersection())
			{
				total += (getPathCount(n)-2);
				count++;
			}
		}
		return total / count;
	}
	// Calculate the complexity for the maze
	public int calcComplexity() 
	{
		int temp;
		temp = 2*coreActiveNodeCount;
		temp -= coreOptimalPathNodeCount;
		temp += calcBranchSum();
		temp -= coreDeadendCount;
		temp -= coreLoopCount;
		temp += longestTailCount;
		return temp;		
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
					output += (n.toString() + '\n');
				}
			}	
		}
		output += "</Nodes>" + '\n';
		
		// Results
		output += "<Results>" + '\n';
		if(resultArray != null && resultArray.size() > 0) {
			for(Result r : resultArray) {
				if(r != null) {
					output += (r.toString() + '\n');
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