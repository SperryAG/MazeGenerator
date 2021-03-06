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

public class Maze {
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
	private int optimalRobotCount;
	
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
		this.resultArray = new ArrayList<Result>();
		this.optimalRobotCount = -1;
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
		this.resultArray = new ArrayList<Result>();
		this.optimalRobotCount = -1;
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
		this.optimalRobotCount = -1;
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
	public void setOptimalRobotCount(int optimalRobotCount) {
		this.optimalRobotCount = optimalRobotCount;
	}
	public int getOptimalRobotCount() {
		return optimalRobotCount;
	}
	public void setNodeArray(Node[] nodeArray)
	{
		this.nodeArray = nodeArray;
	}
	public Node[] getNodeArray()
	{
		return this.nodeArray;
	}
	public void setResultArray(ArrayList<Result> resultArray)
	{
		this.resultArray = resultArray;
	}
	public ArrayList<Result> getResultArray()
	{
		return this.resultArray;
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
		//System.out.print(count + " ");
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
				if(node.getIsEndIntersection() && getNextNode(node,'N').getIsOptimalPath())
					{/* Do Nothing */}
				// If current Node is not iEnd, just step.
				else
					step(getNextNode(node,'N'),visited);
			}
			if(!node.getEastWall() && !visited[xCoord + 1][yCoord])
			{
				if(node.getIsEndIntersection() && getNextNode(node,'E').getIsOptimalPath())
					{/* Do Nothing */}
				else
					step(getNextNode(node,'E'),visited);
			}
			if(!node.getSouthWall() && !visited[xCoord][yCoord - 1])
			{
				if(node.getIsEndIntersection() && getNextNode(node,'S').getIsOptimalPath())
					{/* Do Nothing */}
				else
					step(getNextNode(node,'S'),visited);
			}
			if(!node.getWestWall() && !visited[xCoord - 1][yCoord])
			{
				if(node.getIsEndIntersection() && getNextNode(node,'W').getIsOptimalPath())
					{/* Do Nothing */}
				else
					step(getNextNode(node,'W'),visited);
			}
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
		
		/* Set isCoreNode = true for every core node and calculate coreActiveNodeCount */
		coreActiveNodeCount = setCoreNodes();
		
		/* Set the core variable counts */
		coreOptimalPathNodeCount = calcCoreOptimalPathNodeCount();
		coreIntersectionCount = calcCoreIntersectionCount();
		coreDeadendCount = calcCoreDeadendCount();
		loopCount = 0;
		coreLoopCount = 0;
		
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
		// Andrew's Version
		Node tempNode;
		ArrayList<Node> availableNodes = new ArrayList<Node>();
		boolean[][] spaceFilled = new boolean[gridSize+1][gridSize+1];
		
		Random rand = new Random();
		int randNodeIndex = 0, cntFinishedNodes = 0;
		ArrayList<Character> availableDirections = new ArrayList<Character>();
		
		nodeArray = new Node[activeNodeCount];
		
		// Generate the first node toward the center of the grid
		tempNode = new Node(gridSize/2,gridSize/2,false);
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
					// System.out.println("We finished BFS: " + BFS + " endNode: " + this.getEndNode().getXYCoords());
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
	private char getOptimalOutPathDirection(Node node, char inFrom)
	{
		if(!node.getNorthWall() && getNextNode(node,'N').getIsOptimalPath() && Character.toUpperCase(inFrom) != 'N')
			return 'N';
		else if(!node.getEastWall() && getNextNode(node,'E').getIsOptimalPath() && Character.toUpperCase(inFrom) != 'E')
			return 'E';
		else if(!node.getSouthWall() && getNextNode(node,'S').getIsOptimalPath() && Character.toUpperCase(inFrom) != 'S')
			return 'S';
		else if(!node.getWestWall() && getNextNode(node,'W').getIsOptimalPath() && Character.toUpperCase(inFrom) != 'W')
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
	
	// Find and Set the end intersection node
	private void setEndIntersectionNode()
	{
	// Get the endNode as a starting point
		
		Node startNode = getStartNode();
		Node endNode = getEndNode();
		Node stepNode = endNode;
		char inDirection = 'X';
		char outDirection = 'X';
	// Follow the endNode path back to the first Intersection
		while(stepNode != startNode && !stepNode.getIsIntersection())
		{
			outDirection = getOptimalOutPathDirection(stepNode,inDirection);
			stepNode = getNextNode(stepNode,outDirection);
			inDirection = getOppositeDirection(outDirection);
		}
		stepNode.setEndIntersection(true);
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
	
	
	private void stepTail(ArrayList<Node> nodes, boolean[][] visited, Node current)
	{
		// Tag all tail nodes up to end node
		if(!current.getIsEndIntersection())
		{
			visited[current.getXCoord()][current.getYCoord()] = true;
			for(Node n : nodeArray)
				if(n.getXCoord() == current.getXCoord() && n.getYCoord() == current.getYCoord())
					n.setIsLongestTailNode(true);
		}
		if(current.getIsEndNode())
		{
			return;
		}
		for(Node n : nodes)
			if
			(
				(!current.getNorthWall() && getNextNode(current,'N') == n && !visited[n.getXCoord()][n.getYCoord()]) ||
				(!current.getEastWall() && getNextNode(current,'E') == n && !visited[n.getXCoord()][n.getYCoord()]) ||
				(!current.getSouthWall() && getNextNode(current,'S') == n && !visited[n.getXCoord()][n.getYCoord()]) ||
				(!current.getWestWall() && getNextNode(current,'W') == n && !visited[n.getXCoord()][n.getYCoord()])
			)
				stepTail(nodes,visited,n);
	}
	
	// Find the longest tail and return its count.
	private int setLongestTailNodes()
	{
		boolean[][] visited = new boolean[51][51];
		// Since our mazes do not generate loops
			// Every node from end-intersection to end node contributes to the longest path as a deadend
			// minus the nodes blocked by the end node.
			
		// Remove the core nodes and mark the start node
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node start = null;
		for(Node n : nodeArray)
		{
			if(!n.getIsCoreNode() || n.getIsEndIntersection())
				nodes.add(n);
			if(n.getIsEndIntersection())
				start = n;
		}
		
		// From end-Intersection, tag every node that isnt blocked by end-node
		stepTail(nodes,visited,start);
		
		// Remove all the nodes not marked
		nodes.clear();
		for(Node n : nodeArray)
			if(n.getIsLongestTailNode())
				nodes.add(n);
		
		// Calculate the longest tail
		int count = 0;
		for(Node n : nodes)
		{
			count += 2;
			if(n.getIsOptimalPath() && !n.getIsEndIntersection())
				count--;
			if(n.getIsDeadend())
				count--;
			if(n.getIsIntersection())
				count++;
		}
		
		return count;
	}
	
	// Calculate the branch factor for the maze
	public double calcBranchFactor() 
	{
		if(this.nodeArray == null || this.nodeArray.length == 0)
			return (double)0;
		else
		{
			double total = 0;
			for(Node n : nodeArray)
				if(n.getIsIntersection())
					total += getPathCount(n);  
			return total / ((double)(intersectionCount*4));
		}
			
	}
	// Get the path count of a node where only core paths are counted
	private int getCorePathCount(Node n)
	{
		int count = 0;
		if(!n.getNorthWall() && getNextNode(n,'N').getIsCoreNode())
			count++;
		if(!n.getEastWall() && getNextNode(n,'E').getIsCoreNode())
			count++;
		if(!n.getSouthWall() && getNextNode(n,'S').getIsCoreNode())
			count++;
		if(!n.getWestWall() && getNextNode(n,'W').getIsCoreNode())
			count++;
		return count;
	}
	// Calculate the core branch summation for complexity
	private int calcBranchSum()
	{
		int count = 0;
		for(Node n : nodeArray)
		{
			if(n.getIsCoreNode() && n.getIsIntersection())
			{
				count += (getCorePathCount(n)-2);
			}
		}
		count += 2;
		System.out.println("calcBranchSum: " + count);
		return count;
	}
	// Calculate the complexity for the maze
	public int calcComplexity() 
	{
		int temp = 0;
		temp = 2*coreActiveNodeCount;
		System.out.println("coreActiveNodeCount: " + coreActiveNodeCount);
		temp -= coreOptimalPathNodeCount;
		System.out.println("coreOptimalPathNodeCount: " + coreOptimalPathNodeCount);
		temp += calcBranchSum();
		temp -= coreDeadendCount;
		System.out.println("coreDeadendCount: " + coreDeadendCount);
		temp -= coreLoopCount;
		System.out.println("coreLoopCount: " + coreLoopCount);
		temp += longestTailCount;
		System.out.println("longestTailCount: " + longestTailCount);
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
		            	case "Results":
		            		i++;
							resultArray = new ArrayList<Result>();
		            		break;
		            	case "Result":
		            		i = i+2;
		                    int r, c;
		            		r = Integer.parseInt(tokens[i]);
		                    i++;
		                    c = Integer.parseInt(tokens[i]);
		                    resultArray.add(new Result(r,c));
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