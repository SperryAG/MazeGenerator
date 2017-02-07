package MazeGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import java.util.Stack;

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
	public void setLongestTailCount(int longestTailCount) {
		this.longestTailCount = longestTailCount;
	}
	public int getLongestTailCount() {
		return longestTailCount;
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
	public int getComplexity() {
		return complexity;
	}
	public void setNodeArray(Node[] nodeArray)
	{
		this.nodeArray = nodeArray;
	}
	public Node[] getNodeArray()
	{
		return this.nodeArray;
	}
	// Return the startNode in nodeArray
	Node getStartNode()
	{
		if(nodeArray == null || nodeArray.length == 0)
			return null;
		for(Node n : nodeArray)
			if(n.getIsStartNode())
				return n;
		return null;
	}
	// Return the endNode in nodeArray
	Node getEndNode()
	{
		if(nodeArray == null || nodeArray.length == 0)
			return null;
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
	// If a node has a branching factor of 2/4, return the out direction based on the in direction.
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
	// Traverse paths out of endIntersection that haven't been visited and mark deadend paths visited.
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
		if(gridSize == -1 || gridSize == 0)
			gridSize = rand.nextInt(48) + 2; // random Integer from 2-50
		
		/* Randomize activeNodeCount if needed */
		if(activeNodeCount == -1 || activeNodeCount == 0)
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
		
		/* Find the intersection nodes and set isIntersection = true for involved nodes. Return the intersection count. */
		intersectionCount = setIntersectionNodes();
		
		/* Find and Set isDeadend for last node of a deadend path. */
		deadendCount = setDeadendNodes();
		
		/* Find the full optimal path(s) and set isOptimalPath = true for involved nodes */
		setOptimalPathNodes();
		
		/* Find and Set the isEndIntersection Node */
		setEndIntersectionNode();
		System.out.println("after end intersection node");
		/* Set isCoreNode = true for every core node and calculate coreActiveNodeCount */
//		coreActiveNodeCount = setCoreNodes();	// Throws NullPointer Exception stepDeadEnd() line 397
		/* Set the core variable counts */
//		coreOptimalPathNodeCount = calcCoreOptimalPathNodeCount();	// Infinite Looping?
//		coreIntersectionCount = calcCoreIntersectionCount();	// Infinite Looping?
//		coreDeadendCount = calcCoreDeadendCount();	// Infinite Looping?
		/* Loops - Not sure how to do this - Can just adjust this value manually in the maze file for now. */
		
		/* Find the longest tail and set isLongestTail = true for involved nodes. Return the longestTailCount */
		System.out.println("before longest tail");
		longestTailCount = setLongestTailNodes();
		System.out.println("after longest tail");
		/* Calculate and Set branchFactor */
//		branchFactor = calcBranchFactor();
		
		/* Calculate and Set complexity */
//		complexity = calcComplexity();
		
		/*Testing out DFSSTEP */
		//System.out.println("DFS STEP: " + this.dfsStep());
	}
	
	/* Generation Methods */
	// Given gridSize and activeNodeCount, generate a random maze
	private void populateNodeArray()
	{
		// Andrew's Version
		System.out.println(gridSize);
		System.out.println(activeNodeCount);
		Node tempNode;
		ArrayList<Node> availableNodes = new ArrayList<Node>();
		boolean[][] spaceFilled = new boolean[gridSize+1][gridSize+1];
		
		Random rand = new Random();
		int randNodeIndex = 0, cntFinishedNodes = 0;
		ArrayList<Character> availableDirections = new ArrayList<Character>();
		
		nodeArray = new Node[activeNodeCount];
		
		// Generate the first node toward the center of the grid
		tempNode = new Node(gridSize/2,gridSize/2,false);
		System.out.println(tempNode.getXCoord() + " " + tempNode.getYCoord());
		spaceFilled[tempNode.getXCoord()][tempNode.getYCoord()] = true;
		availableNodes.add(tempNode);
		
		// Generate activeNodeCount more nodes.
		for(int i = 0; i < activeNodeCount - 1;)
		{
			// Choose a random node inside availableNodes to extend
			randNodeIndex = rand.nextInt(availableNodes.size());
			
			// Get the directions that can be extended
			tempNode = availableNodes.get(randNodeIndex);
			availableDirections.clear();
			if(tempNode.getNorthWall() && tempNode.getYCoord() < gridSize && !spaceFilled[tempNode.getXCoord()][tempNode.getYCoord() + 1])
				availableDirections.add('N');
			if(tempNode.getEastWall() && tempNode.getXCoord() < gridSize && !spaceFilled[tempNode.getXCoord() + 1][tempNode.getYCoord()])
				availableDirections.add('E');
			if(tempNode.getSouthWall() && tempNode.getYCoord() > 1 && !spaceFilled[tempNode.getXCoord()][tempNode.getYCoord() - 1])
				availableDirections.add('S');
			if(tempNode.getWestWall() && tempNode.getXCoord() > 1 && !spaceFilled[tempNode.getXCoord() - 1][tempNode.getYCoord()])
				availableDirections.add('W');
			
			// If no directions can be extended move the node to nodeArray and prevent the for loop from incrementing.
			if(availableDirections.isEmpty())
			{
				nodeArray[cntFinishedNodes] = tempNode;
				availableNodes.remove(randNodeIndex);
				cntFinishedNodes++;
			}
			// Else choose a random direction and create the new node
			else
			{
				switch(availableDirections.get(rand.nextInt(availableDirections.size())))
				{
					case('N'):
						tempNode.setNorthWall(false);
						tempNode = new Node(tempNode.getXCoord(),tempNode.getYCoord()+1,false);
						tempNode.setSouthWall(false);
						break;
					case('E'):
						tempNode.setEastWall(false);
						tempNode = new Node(tempNode.getXCoord()+1,tempNode.getYCoord(),false);
						tempNode.setWestWall(false);
						break;
					case('S'):
						tempNode.setSouthWall(false);
						tempNode = new Node(tempNode.getXCoord(),tempNode.getYCoord()-1,false);
						tempNode.setNorthWall(false);
						break;
					case('W'):
						tempNode.setWestWall(false);
						tempNode = new Node(tempNode.getXCoord()-1,tempNode.getYCoord(),false);
						tempNode.setEastWall(false);
						break;
				}
				availableNodes.add(tempNode);
				spaceFilled[tempNode.getXCoord()][tempNode.getYCoord()] = true;
				i++;
			}
		}
		// After activeNodeCount nodes have been completed copy remaining availableNodes into nodeArray
		for(Node node : availableNodes)
		{
			nodeArray[cntFinishedNodes] = node;
			cntFinishedNodes++;
		}
		
		/*
		// Steven's Version
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
				   //this.nodeArray[count] = randomNode;
				   //count++;
				   tempArray.remove(randomPick);
				   randomPick = rand.nextInt(tempArray.size());
				   randomNode = tempArray.get(randomPick);
			   }
			   ArrayList<Integer> dirArray = new ArrayList<Integer>();
			   dirArray = randomNode.getWalls(coords, this.gridSize);
			   while(dirArray.size() == 0){
				   randomPick = rand.nextInt(tempArray.size());
				   randomNode = tempArray.get(randomPick);
				   dirArray = randomNode.getWalls(coords, this.gridSize);
			   }
			   int ran = rand.nextInt(dirArray.size());
			   int randomDirection = dirArray.get(ran);
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
		}
		for(Node node: nodeArray){
			node.updateWalls(coords);
		}
		//String[] both = (String[])ArrayUtils.addAll(first, second);
		 */
	}
	
	private Map<Integer, Set<Pair>> optimalPathCoords(){//this should return the map of integer and set pairs of stuff. 
		boolean[][] localVisited = new boolean[gridSize+1][gridSize+1];
		Map<Integer, Set<Pair>> BFS = new HashMap<Integer, Set<Pair>>();
		Set<Pair> availableCoords = new HashSet<Pair>();
		Node currentNode = this.getStartNode(); //Let's get the starting node.
		availableCoords.add(currentNode.getXYCoords());
		localVisited[currentNode.getXCoord()][currentNode.getYCoord()] = true;
		BFS.put(0, availableCoords); //adding start node to position 0 of BFS
		availableCoords = currentNode.isPath(localVisited);//this is all the nodes reachable from the startPosition
		BFS.put(1,  availableCoords);
		int steps = 1;//how many steps away from the start node we are. We're currently on all nodes 1Step away from Start
		while(true){
			availableCoords = BFS.get(steps);
			steps++;//increment steps to be adding to the next level. We enter having already filled level 1 with all reachable
			//nodes from the start node.
			Set<Pair> tempSet = new HashSet<Pair>();
			for(Pair p: availableCoords){//iterate through all the possible paths reachable from the currentNode. 
				Node tempNode = this.getCoordNode(p.getXCoord(), p.getYCoord());
				localVisited[p.getXCoord()][p.getYCoord()] = true;
				tempSet.addAll(tempNode.isPath(localVisited));
				//If we do find the endNode, set the furthest step as a set pair with only end node cords. 
				if(tempNode.getXCoord() == this.getEndNode().getXCoord() && tempNode.getYCoord() == this.getEndNode().getYCoord()){
					Set<Pair> endNode = new HashSet<Pair>();
					endNode.add(new Pair(tempNode.getXCoord(), tempNode.getYCoord()));
					BFS.put(steps-1, endNode);
					System.out.println("We finished BFS: " + BFS + " endNode: " + this.getEndNode().getXYCoords());
					return BFS;
				}
			}
			//At this point, tempSet should contain all the values reachable from this level of nodes. 
			BFS.put(steps, tempSet);			
		}
	}
	
	//this will update and replace a Node
	private void updateNode(Node toUpdate){
		for(int i = 0; i < this.nodeArray.length; i++){
			if(nodeArray[i].getXYCoords() == toUpdate.getXYCoords()){
				nodeArray[i] = toUpdate;
			}
		}
	}
	
	// Find and Set the nodes involved in the optimal path(s)
	private void setOptimalPathNodes()
	{
		Node setOptLocal = this.getEndNode();
		Map<Integer, Set<Pair>> optimalPathCoords = optimalPathCoords();
		Set<Pair> endCoords = optimalPathCoords.get(optimalPathCoords.size()-1);
		for(int i = optimalPathCoords.size() -1; i >= 0; i--){//for loop backwards through all values in the map of optimalNodes
			Set<Pair> currentSet = optimalPathCoords.get(i);
			for(Pair pair : currentSet){//for all values in the current level of the map
				Node tempNode = this.getCoordNode(pair.getXCoord(), pair.getYCoord());
				//endNode set true
				if(tempNode.getXCoord() == this.getEndNode().getXCoord() && tempNode.getYCoord() == this.getEndNode().getYCoord()){
					tempNode.setIsOptimalPath(true);
					updateNode(tempNode);
				}
				//startNode set true
				else if(tempNode.getXCoord() == this.getStartNode().getXCoord() && tempNode.getYCoord() == this.getStartNode().getYCoord()){
					tempNode.setIsOptimalPath(true);
					updateNode(tempNode);
				}
				//only Set true if the level below has the neighbor that's set to true?
				else{
					Set<Pair> currentNeighbors = tempNode.isPath();
					for(Pair p : currentNeighbors){
						Node oneFurther = this.getCoordNode(p.getXCoord(), p.getYCoord());
						if(oneFurther.getIsOptimalPath()){
							tempNode.setIsOptimalPath(true);
							updateNode(tempNode);
						}
					}
				}
			}
		}
		//these are print statements to check OptimalNodes
		/*for(Node n: this.nodeArray){
			System.out.print(n.getXYCoords()+ " " + n.getIsOptimalPath() + " ");
		}
		System.out.println("\n optimalPathCoords: " + optimalPathCoords);*/
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
			{
				if((n.getIsStartNode() || getPathCount(n) >= 3) && !n.getIsEndNode())
				{
					n.setIsIntersection(true);
					count++;
				}
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
		private int setToLongestTail (Stack<Pair> traveled) {
			for (Pair p : traveled) {
				System.out.println("settolongesttail loop");
				getCoordNode(p.getXCoord(), p.getYCoord()).setIsLongestTailNode(true);
			}
			return traveled.size();
		}
		// recursively calls itself down a path as it intersects
		// if multiple of these paths reach the endIntersection node
			// then it returns the length of the path and the coordinates of the endIntersection of the longest one
		// if none of the paths lead to the end intersection, it returns -1 and the invalid coordinates of (-1, -1)
		private Map<Integer, Stack<Pair>> traversingLongestTailPaths(ArrayList<Pair> neighbors, Stack<Pair> traveled) {
			Map<Integer, Stack<Pair>> toReturn1 = new HashMap<Integer, Stack<Pair>>();
			toReturn1.put(-2, null);
			Map<Integer, Stack<Pair>> toReturn2 = new HashMap<Integer, Stack<Pair>>();
			toReturn2.put(-2, null);
			Map<Integer, Stack<Pair>> toReturn3 = new HashMap<Integer, Stack<Pair>>();
			toReturn3.put(-2, null);
			if (neighbors.size() == 1) {	// node is a deadend
				if (getCoordNode(neighbors.get(0).getXCoord(), neighbors.get(0).getYCoord()).getIsEndIntersection()) {	// if node is endIntersection
					toReturn1.clear();
					toReturn1.put(traveled.size(), traveled);
				}
				else {
					Stack<Pair> toAdd = new Stack<Pair>();
					toAdd.push(new Pair(-1, -1));
					toReturn1.clear();
					toReturn1.put(-1, toAdd);
				}
			}
			else if (neighbors.size() == 2) {	// node is a neutral node (not a deadend or intersection)
				for (int i = 0; i < neighbors.size(); i++) {
					if (neighbors.get(i) != traveled.peek()) {
						traveled.push(neighbors.get(i));
						neighbors = getCoordNode(traveled.peek().getXCoord(), traveled.peek().getYCoord()).getAllNeighbors(traveled);
						toReturn2.clear();
						toReturn2 = traversingLongestTailPaths(neighbors, traveled);
					}
				}
			}
			else if (neighbors.size() > 2) {	// node is an intersection
				Map<Integer, Stack<Pair>> tempReturn1 = new HashMap<Integer, Stack<Pair>>();
				tempReturn1.put(-2, null);
				Map<Integer, Stack<Pair>> tempReturn2 = new HashMap<Integer, Stack<Pair>>();
				tempReturn1.put(-2, null);
				Map<Integer, Stack<Pair>> tempReturn3 = new HashMap<Integer, Stack<Pair>>();
				tempReturn1.put(-2, null);
				for (int i = 0; i < neighbors.size(); i++) {
					System.out.println("traversingLongestTailPaths loop");
					if (neighbors.get(i) != traveled.peek()) {
						traveled.push(neighbors.get(i));
						neighbors = getCoordNode(traveled.peek().getXCoord(), traveled.peek().getYCoord()).getAllNeighbors(traveled);
						if (tempReturn1.containsKey(-2)) {
							tempReturn1.clear();
							tempReturn1 = traversingLongestTailPaths(neighbors, traveled);
						}
						else if (!tempReturn1.containsKey(-2) && tempReturn2.containsKey(-2)) {
							tempReturn2.clear();
							tempReturn2 = traversingLongestTailPaths(neighbors, traveled);
						}
						else if (!tempReturn1.containsKey(-2) && !tempReturn2.containsKey(-2) && tempReturn3.containsKey(-2)) {
							tempReturn3.clear();
							tempReturn3 = traversingLongestTailPaths(neighbors, traveled);
						}
					}
				}
				int tempMaxLength1 = tempReturn1.entrySet().iterator().next().getKey();
				int tempMaxLength2 = tempReturn2.entrySet().iterator().next().getKey();
				int tempMaxLength3 = tempReturn3.entrySet().iterator().next().getKey();
				toReturn3.clear();
				if (Math.max(Math.max(tempMaxLength1, tempMaxLength2), tempMaxLength3) == tempMaxLength1) {
					toReturn3 = tempReturn1;
				}
				else if (Math.max(Math.max(tempMaxLength1, tempMaxLength2), tempMaxLength3) == tempMaxLength2) {
					toReturn3 = tempReturn2;
				}
				else if (Math.max(Math.max(tempMaxLength1, tempMaxLength2), tempMaxLength3) == tempMaxLength3) {
					toReturn3 = tempReturn3;
				}
			}
			int maxLength1 = toReturn1.entrySet().iterator().next().getKey();
			int maxLength2 = toReturn2.entrySet().iterator().next().getKey();
			int maxLength3 = toReturn3.entrySet().iterator().next().getKey();
			if (Math.max(Math.max(maxLength1, maxLength2), maxLength3) == maxLength1) {
				return toReturn1;
			}
			if (Math.max(Math.max(maxLength1, maxLength2), maxLength3) == maxLength2) {
				return toReturn2;
			}
			else {
//			if (Math.max(Math.max(maxLength1, maxLength2), maxLength3) == maxLength3) {
				return toReturn3;
			}
		}
		
		// Find the longest tail and return its count. 
		// !isCoreNode can be use determine which nodes come after the isEndIntersectionNode
		public int setLongestTailNodes()
		{
			for (Node n: this.nodeArray) {
				System.out.println(n.toString());
			}
			setEndIntersectionNode();
//			Node endIntersection = new Node();
			Node endNode = new Node();
			Pair endPair = new Pair(0, 0);
			for (Node n : this.nodeArray) {
				if (n.getIsEndNode()) {
					endNode = n;
					endPair.update(n.getXCoord(), n.getYCoord());
					break;
				}
			}
			System.out.println("EndNode: " + endNode + " EndPair: " + endPair);
			Stack<Pair> traveled = new Stack<Pair>();
			traveled.push(endPair);
			ArrayList<Pair> neighbors = endNode.getAllNeighbors(traveled);
			System.out.println("Traveled: " + traveled + " neighbors: " + neighbors);
			if (neighbors.size() == 1) {	// only optimal path
				getCoordNode(neighbors.get(0).getXCoord(), neighbors.get(0).getYCoord()).setIsLongestTailNode(true);//sets neighbor of endNode to be true
				Stack<Pair> traveledOptimal = new Stack<Pair>();
				traveledOptimal.push(endPair);
//				traveledOptimal.push(neighbors.get(0));
				boolean isEndIntersection = false;
				while (!isEndIntersection) {	// while node is not endIntersection
					System.out.println("only optimal path loop: " + neighbors);
					if (getCoordNode(neighbors.get(0).getXCoord(), neighbors.get(0).getYCoord()).getIsEndIntersection()) {
						return 1;
					}
					ArrayList<Pair> temp = getCoordNode(neighbors.get(0).getXCoord(), neighbors.get(0).getYCoord()).getAllNeighbors(traveledOptimal);
					System.out.println("temp size: " + temp.size());
					System.out.println("temp: " + temp);
					for (Pair p : temp) {
						System.out.println("in optimal path only for loop: " + getCoordNode(p.getXCoord(), p.getYCoord()).getIsOptimalPath());
						if (getCoordNode(p.getXCoord(), p.getYCoord()).getIsEndIntersection()) {
							isEndIntersection = true;
							break;
						}
						if (getCoordNode(p.getXCoord(), p.getYCoord()).getIsOptimalPath()) {
							traveledOptimal.push(p);
							getCoordNode(p.getXCoord(), p.getYCoord()).setIsLongestTailNode(true);
							neighbors.clear();
							neighbors = getCoordNode(p.getXCoord(), p.getYCoord()).getAllNeighbors(traveledOptimal);
							System.out.print("neighbors after update: " + neighbors);
							break;
						}
					}
					if (neighbors.get(0) == temp.get(0)) {	// neighbors was not updated, end intersection was reached
						return 1;
					}
				}
			}
			Stack<Pair> traveled1 = traveled;
			Stack<Pair> traveled2 = traveled;
			Stack<Pair> traveled3 = traveled;
//			ArrayList<Pair> temp1 = new ArrayList<Pair>();
			boolean temp1inUse = false;
//			ArrayList<Pair> temp2 = new ArrayList<Pair>();
			boolean temp2inUse = false;
//			ArrayList<Pair> temp3 = new ArrayList<Pair>();
			boolean temp3inUse = false;
			for (int i = 0; i < neighbors.size(); i++) {	// if more than just optimal path is available
				System.out.println("more than optimal loop");
//				ArrayList<Pair> temp1 = new ArrayList<Pair>();
//				boolean temp1inUse = false;
//				ArrayList<Pair> temp2 = new ArrayList<Pair>();
//				boolean temp2inUse = false;
//				ArrayList<Pair> temp3 = new ArrayList<Pair>();
//				boolean temp3inUse = false;
				if (getCoordNode(neighbors.get(i).getXCoord(), neighbors.get(i).getYCoord()).getIsEndIntersection()) {
					return 1;	// traveled.size()
				}
				if (!getCoordNode(neighbors.get(i).getXCoord(), neighbors.get(i).getYCoord()).getIsOptimalPath()) {
					if (traveled1 == traveled) {
						traveled1.push(neighbors.get(i));
//						temp1 = getCoordNode(neighbors.get(i).getXCoord(), neighbors.get(i).getYCoord()).getAllNeighbors(traveled1);
						temp1inUse = true;
					}
					else if (traveled1 != traveled && traveled2 == traveled) {
						traveled2.push(neighbors.get(i));
//						temp2 = getCoordNode(neighbors.get(i).getXCoord(), neighbors.get(i).getYCoord()).getAllNeighbors(traveled2);
						temp2inUse = true;
					}
					else if (traveled1 != traveled && traveled2 != traveled && traveled3 == traveled) {
						traveled3.push(neighbors.get(i));
//						temp3 = getCoordNode(neighbors.get(i).getXCoord(), neighbors.get(i).getYCoord()).getAllNeighbors(traveled3);
						temp2inUse = true;
					}
				}
//				Map<Integer, Stack<Pair>> toReturn1, toReturn2, toReturn3;
//				while (temp1inUse) {	// follow first path until you reach endIntersection node
//					neighbors = getCoordNode(traveled1.peek().getXCoord(), traveled1.peek().getXCoord()).getNeighbors(traveled1);
//					toReturn1 = traversingLongestTailPaths(neighbors, traveled1); 
//					if (traveled1.peek().getCoords() == "(-1, -1)") {	// if no endIntersection path was found
//						temp1inUse = false;
//					}
//					else {	// To Do: the last pair returned should be the endIntersection node...?
//						neighbors = getCoordNode(traveled1.peek().getXCoord(), traveled1.peek().getXCoord()).getNeighbors(traveled1);
//					}
//				}
//				while (temp2inUse) {	// follow second path (if there is one) until you reach endIntersection node
//					neighbors = getCoordNode(traveled2.peek().getXCoord(), traveled2.peek().getXCoord()).getNeighbors(traveled2);
//					toReturn2 = traversingLongestTailPaths(neighbors, traveled2);	// should traverse any branching paths and set isLongestTail as needed
//					if (traveled2.peek().getCoords() == "(-1, -1)") {	// if no endIntersection path was found
//						temp2inUse = false;
//					}
//					else {	// To Do: the last pair returned should be the endIntersection node...?
//						neighbors = getCoordNode(traveled2.peek().getXCoord(), traveled2.peek().getXCoord()).getNeighbors(traveled2);
//					}
//				}
//				while (temp3inUse) {	// follow third path (if there is one) until you reach endIntersection node
//					neighbors = getCoordNode(traveled3.peek().getXCoord(), traveled3.peek().getXCoord()).getNeighbors(traveled3);
//					toReturn3 = traversingLongestTailPaths(neighbors, traveled3);
//					if (traveled3.peek().getCoords() == "(-1, -1)") {	// if no endIntersection path was found
//						temp3inUse = false;
//					}
//					else {	// To Do: the last pair returned should be the endIntersection node...?
//						neighbors = getCoordNode(traveled3.peek().getXCoord(), traveled3.peek().getXCoord()).getNeighbors(traveled3);
//					}
//				}
//				int maxLength1 = toReturn1.entrySet().iterator().next().getKey();
//				int maxLength2 = toReturn2.entrySet().iterator().next().getKey();
//				int maxLength3 = toReturn3.entrySet().iterator().next().getKey();
//				if (Math.max(Math.max(maxLength1, maxLength2), maxLength3) == maxLength1) {
//					setToLongestTail(toReturn1.get(maxLength1));
//				}
//				else if (Math.max(Math.max(maxLength1, maxLength2), maxLength3) == maxLength2) {
//					setToLongestTail(toReturn2.get(maxLength2));
//				}
//				else if (Math.max(Math.max(maxLength1, maxLength2), maxLength3) == maxLength3) {
//					setToLongestTail(toReturn3.get(maxLength3));
//				}
			}
			Map<Integer, Stack<Pair>> toReturn1 = new HashMap<Integer, Stack<Pair>>();
			toReturn1.put(-2, null);
			Map<Integer, Stack<Pair>> toReturn2 = new HashMap<Integer, Stack<Pair>>();
			toReturn2.put(-2, null);
			Map<Integer, Stack<Pair>> toReturn3 = new HashMap<Integer, Stack<Pair>>();
			toReturn3.put(-2, null);
			while (temp1inUse) {	// follow first path until you reach endIntersection node
				System.out.println("in temp1 loop: " + traveled1 + " " + traveled1.peek());
				if (getCoordNode(traveled1.peek().getXCoord(), traveled1.peek().getXCoord()).getAllNeighbors(traveled1) == null) {
					break;
				}
				neighbors = getCoordNode(traveled1.peek().getXCoord(), traveled1.peek().getXCoord()).getAllNeighbors(traveled1);
				toReturn1.clear();
				toReturn1 = traversingLongestTailPaths(neighbors, traveled1);
				System.out.println("toreturn1: " + toReturn1);
//				if (traveled1.peek().getCoords() == "(-1, -1)") {	// if no endIntersection path was found
				if (toReturn1.entrySet().iterator().next().getKey() == -1) {	// if no endIntersection path was found
					temp1inUse = false;
				}
//				else {	// To Do: the last pair returned should be the endIntersection node...?
//					neighbors = getCoordNode(traveled1.peek().getXCoord(), traveled1.peek().getXCoord()).getAllNeighbors(traveled1);
//				}
			}
			while (temp2inUse) {	// follow second path (if there is one) until you reach endIntersection node
				System.out.println("in temp2 loop");
				if (getCoordNode(traveled2.peek().getXCoord(), traveled2.peek().getXCoord()).getAllNeighbors(traveled2) == null) {
					break;
				}
				neighbors = getCoordNode(traveled2.peek().getXCoord(), traveled2.peek().getXCoord()).getAllNeighbors(traveled2);
				toReturn2.clear();
				toReturn2 = traversingLongestTailPaths(neighbors, traveled2);	// should traverse any branching paths and set isLongestTail as needed
//				if (traveled2.peek().getCoords() == "(-1, -1)") {	// if no endIntersection path was found
				if (toReturn2.entrySet().iterator().next().getKey() == -1) {	// if no endIntersection path was found
					temp2inUse = false;
				}
//				else {	// To Do: the last pair returned should be the endIntersection node...?
//					neighbors = getCoordNode(traveled2.peek().getXCoord(), traveled2.peek().getXCoord()).getAllNeighbors(traveled2);
//				}
			}
			while (temp3inUse) {	// follow third path (if there is one) until you reach endIntersection node
				System.out.println("in temp3 loop");
				if (getCoordNode(traveled3.peek().getXCoord(), traveled3.peek().getXCoord()).getAllNeighbors(traveled3) == null) {
					break;
				}
				neighbors = getCoordNode(traveled3.peek().getXCoord(), traveled3.peek().getXCoord()).getAllNeighbors(traveled3);
				toReturn3.clear();
				toReturn3 = traversingLongestTailPaths(neighbors, traveled3);
//				if (traveled3.peek().getCoords() == "(-1, -1)") {	// if no endIntersection path was found
				if (toReturn3.entrySet().iterator().next().getKey() == -1) {	// if no endIntersection path was found
					temp3inUse = false;
				}
//				else {	// To Do: the last pair returned should be the endIntersection node...?
//					neighbors = getCoordNode(traveled3.peek().getXCoord(), traveled3.peek().getXCoord()).getAllNeighbors(traveled3);
//				}
			}
			int maxLength1 = toReturn1.entrySet().iterator().next().getKey();
			int maxLength2 = toReturn2.entrySet().iterator().next().getKey();
			int maxLength3 = toReturn3.entrySet().iterator().next().getKey();
			int toReturnFinal = -2;
			if (Math.max(Math.max(maxLength1, maxLength2), maxLength3) == maxLength1) {
				toReturnFinal = setToLongestTail(toReturn1.get(maxLength1));
			}
			else if (Math.max(Math.max(maxLength1, maxLength2), maxLength3) == maxLength2) {
				toReturnFinal = setToLongestTail(toReturn2.get(maxLength2));
			}
			else if (Math.max(Math.max(maxLength1, maxLength2), maxLength3) == maxLength3) {
				toReturnFinal = setToLongestTail(toReturn3.get(maxLength3));
			}
			return toReturnFinal;
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

	public int dfsStep(){
		boolean[][] visited = new boolean[this.gridSize+1][this.gridSize+1];
		int steps = 0;
		Random rand = new Random();
		Stack<Pair> pathTraveled = new Stack<Pair>();
		Pair currentPair = this.getStartNode().getXYCoords();
		visited[currentPair.getXCoord()][currentPair.getYCoord()] = true;//always set to visited after touching
		pathTraveled.push(currentPair);//add new path to stack.
		boolean foundEnd = false; //initialize while loop to keep going until we hit endNode.
		while(foundEnd == false){
			currentPair = pathTraveled.peek();//check the current 
			Node currentNode = this.getCoordNode(currentPair.getXCoord(), currentPair.getYCoord());//current node from top of stack
			ArrayList<Pair> neighbors = currentNode.getNeighbors(visited);
			if(neighbors.isEmpty() == false){ //there's directions to go. 
				Pair newPair = neighbors.get(rand.nextInt(neighbors.size()));
				Node newNode = this.getCoordNode(newPair.getXCoord(), newPair.getYCoord());
				visited[newPair.getXCoord()][newPair.getYCoord()] = true;
				if(newNode.getIsEndNode()){
					foundEnd = true;
				}
				else{
					pathTraveled.push(newPair);
				}
			}
			else{//hit deadend or no other direction to go
				pathTraveled.pop();//pops the deadend.
				while(currentNode.getIsIntersection() == false && neighbors.isEmpty()){
					Pair tempPair = pathTraveled.peek();					
					currentNode = this.getCoordNode(tempPair.getXCoord(), tempPair.getYCoord());
					neighbors = currentNode.getNeighbors(visited);
					if(neighbors.isEmpty()){
						pathTraveled.pop();//no empty still. 
					}
					steps++;
				}
				Pair newPair = neighbors.get(rand.nextInt(neighbors.size()));
				Node newNode = this.getCoordNode(newPair.getXCoord(), newPair.getYCoord());
				visited[newPair.getXCoord()][newPair.getYCoord()] = true;
				if(newNode.getIsEndNode()){
					foundEnd = true;
				}
				else{
					pathTraveled.push(newPair);
				}
			}
			steps++;
		}
		return steps;
	}
	
	/* Output Methods */
	// Output to String
	public String toString() 
	{
		String output = "";
		
		// Title
		output += ("<Title>" + title + "</Title>" + '\n');
		// Created
		if(created == null)
			output += ("<Created>" + "</Created>" + '\n');
		else
			output += ("<Created>" + created + "</Created>" + '\n');
		// GridSize
		output += ("<GridSize>" + Integer.toString(gridSize) + "</GridSize>" + '\n');
		// ActiveNodeCount
		output += ("<ActiveNodeCount>" + Integer.toString(activeNodeCount) + "</ActiveNodeCount>" + '\n');
		// BranchFactor
		if(branchFactor == -1)
			output += ("<BranchFactor>" + "0.0" + "</BranchFactor>" + '\n');
		else
			output += ("<BranchFactor>" + Double.toString(branchFactor) + "</BranchFactor>" + '\n');
		// Complexity
		if(complexity == -1)
			output += ("<Complexity>" + "0" + "</Complexity>" + '\n');
		else
			output += ("<Complexity>" + Integer.toString(complexity) + "</Complexity>" + '\n');
		// IntersectionCount
		if(intersectionCount == -1)
			output += ("<IntersectionCount>" + "0" + "</IntersectionCount>" + '\n');
		else
			output += ("<IntersectionCount>" + Integer.toString(intersectionCount) + "</IntersectionCount>" + '\n');
		// DeadendCount
		if(deadendCount == -1)
			output += ("<DeadendCount>" + "0" + "</DeadendCount>" + '\n');
		else
			output += ("<DeadendCount>" + Integer.toString(deadendCount) + "</DeadendCount>" + '\n');
		// LoopCount
		if(deadendCount == -1)
			output += ("<LoopCount>" + "0" + "</LoopCount>" + '\n');
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
	public String toFile() 
	{
		Path file = null;
		try {
			List<String> output = Arrays.asList(this.toString());
			file = Paths.get(title + "_" + Integer.toString(gridSize) + "x" + Integer.toString(gridSize) + "_" +
		                          Integer.toString(activeNodeCount) + "_" + Double.toString(branchFactor) + "_" + 
					              Integer.toString(complexity) + "_" + created + ".txt");
			Files.write(file, output, Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return file.toString();
	}
	//File Reader
	public void fromFile(String Filename)
	{
		BufferedReader br = null;
		FileReader fr = null;
		String[] tokens ;
		Node temp = null ;
		temp = new Node();
		int count = 0;
		try 
		{
			fr = new FileReader(Filename);
			br = new BufferedReader(fr);
			String sCurrentLine; 
			int itemp = 0;
			boolean btemp;
			br = new BufferedReader(new FileReader(Filename));
			while ((sCurrentLine = br.readLine()) != null) 
			{ //Filereader
				String delims = "[<>,()]";
				tokens = sCurrentLine.split(delims);
				for (int i = 0; i < tokens.length; i++)
				{
			        switch (tokens[i]) 
			        {
		            	case "Title":
		            		i++;
		            		title = tokens[i];
			            	break;
		            	case "Created":
		            		i++; 
		            		created = tokens[i];
			            	break;
		            	case "GridSize":
		            		i++;
		            		gridSize = Integer.parseInt(tokens[i]);
		            		break;
		            	case "ActiveNodeCount":
		            		i++;
		            		activeNodeCount = Integer.parseInt(tokens[i]);
		            		break;
		            	case "BranchFactor":
		            		i++;
		            		if(tokens[i] == "Not")
		            		{
		            			branchFactor = 0.0;
		            			i++;
		            		}
		            		else
		            			branchFactor = Double.parseDouble(tokens[i]);
		            		break;
		            	case "Complexity":
		            		i++;
		            		if(tokens[i] == "Not")
		            		{
		            			complexity = 0;
		            			i++;
		            		}
		            		else
		            			complexity = Integer.parseInt(tokens[i]);
		            		break;
		            	case "IntersectionCount":
		            		i++;
		            		intersectionCount = Integer.parseInt(tokens[i]);
		            		break;
		            	case "DeadendCount":
		            		i++;
		            		deadendCount = Integer.parseInt(tokens[i]);
		            		break;		
		            	case "LoopCount":
		            		i++;
		            		if(tokens[i].equals("Not Calculated")){
		            			loopCount= -1;
		            		}else{
		            			itemp = Integer.parseInt(tokens[i]);
		            			loopCount= itemp;
		            		}
		            		break;		
		            	case "Nodes":
		            		i++;
							nodeArray = new Node[activeNodeCount];
		            		break;
		            	case "Node":
		            		temp = new Node();
		            		i++;
		            		break;	
		            	case "Coordinates":  
		                    i= i+2;
		                    itemp = Integer.parseInt(tokens[i]);
		                    temp.setXCoord(itemp);
		                    i++;
		                    itemp = Integer.parseInt(tokens[i]);
		                    temp.setYCoord(itemp);
		                    break;
		            	case "StartNode":  
				            i++;
                    		btemp = Boolean.parseBoolean(tokens[i]);
                    		temp.setStartNode(btemp);
                            break;		                            
				        case "EndNode":  
				        	i++;
	                    	btemp = Boolean.parseBoolean(tokens[i]);
	                    	temp.setEndNode(btemp);
	                        break;    
		                case "EndIntersectionNode":  
	                    	i++;
	                    	btemp = Boolean.parseBoolean(tokens[i]);
	                    	temp.setEndIntersection(btemp);
	                        break;
				        case "OptimalPathNode":  
			        	    i++;
			                btemp = Boolean.parseBoolean(tokens[i]);
			                temp.setIsOptimalPath(btemp);
			                break;    
				        case "IntersectionNode":  
			        	    i++;
			                btemp = Boolean.parseBoolean(tokens[i]);
			                temp.setIsIntersection(btemp);
			                break;     
				        case "DeadendNode":  
			        	    i++;
			                btemp = Boolean.parseBoolean(tokens[i]);
			                temp.setIsDeadend(btemp);
			                break;     
		                case "CoreNode":  
	                    	i++;
	                    	btemp = Boolean.parseBoolean(tokens[i]);
	                    	temp.setIsCoreNode(btemp);	
	                        break;		                            
		                case "LongestTailNode":  
	                    	i++;
	                    	btemp = Boolean.parseBoolean(tokens[i]);
	                    	temp.setIsLongestTailNode(btemp);
	                    	break;	 
		                case "WallNorth":  
	                    	i++;
	                    	btemp = Boolean.parseBoolean(tokens[i]);
	                    	temp.setNorthWall(btemp);
	                        break;	  
		                case "WallEast":  
	                    	i++;
	                    	btemp = Boolean.parseBoolean(tokens[i]);
	                    	temp.setEastWall(btemp);
	                        break;		                            
			            case "WallSouth":  
	                    	i++;
	                    	btemp = Boolean.parseBoolean(tokens[i]);
	                    	temp.setSouthWall(btemp);
	                    	break;	 
			          	case "WallWest":  
	                    	i++;
	                    	btemp = Boolean.parseBoolean(tokens[i]);
	                    	temp.setWestWall(btemp);
	                    	nodeArray[count] = temp;
	                    	count++;
	                        break;	      
	                    default:
	                    	break;
			        }
		               		
				}		
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
			}
			catch (IOException ex) {ex.printStackTrace();} 
		}
	}
}