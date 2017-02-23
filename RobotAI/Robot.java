package RobotAI;
import MazeGenerator.*;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class Robot {
	private SwarmNode currentSwarmNode;
	private Stack<SwarmNode> pathTraveled;
	private int steps, ident;
	private boolean atEnd;
	
	public Robot(SwarmNode startSwarmNode, int ident) {
		this.currentSwarmNode = startSwarmNode;
		this.pathTraveled = new Stack<SwarmNode>();
		this.pathTraveled.push(startSwarmNode);
		this.steps = 0;
		this.ident = ident;
		this.atEnd = false;
	}
	// returns current SwarmNode
	public SwarmNode getCurrentSwarmNode() {
		return this.currentSwarmNode;
	}
	// returns current path
	public Stack<SwarmNode> getPathTraveled() {
		return this.pathTraveled;
	}
	// returns step count
	public int getSteps() {
		return this.steps;
	}
	// returns ident
	public int getIdent() {
		return this.ident;
	}
	// returns if the robot has found the end node or not
	public boolean getAtEnd() {
		return this.atEnd;
	}
	// set current SwarmNode to new SwarmNode
	public void setCurrentSwarmNode(SwarmNode newSwarmNode) {
		this.currentSwarmNode = newSwarmNode;
	}
	// increment step every cycle
	public void incrementSteps() {
		this.steps++;
	}
	// add a SwarmNode to the path (when NOT backtracking)
	public boolean pushPathTraveled(SwarmNode nextSwarmNode) {
		if (pathTraveled.contains(nextSwarmNode)) {
			return false;
		}
		else {
			pathTraveled.push(nextSwarmNode);
			return true;
		}
	}
//	// remove a SwarmNode from the path (when backtracking)
//	public SwarmNode popPathTraveled() {
//		try {
//			SwarmNode toRemove = pathTraveled.peek();
//			pathTraveled.pop();
//			return toRemove;
//			// Set popped node to deadend
//		}
//		catch (EmptyStackException e) {
//			return false;
//		}
//		return true;
//	}
	private SwarmNode chooseDirection(Map<String, SwarmNode> nodeSet) {
		//There's no viable place to go. 
		if(nodeSet.size() == 0){
			SwarmNode oldNode = pathTraveled.peek();
			this.currentSwarmNode = oldNode;
			this.steps++;
			return oldNode;
		}
				
		// Deadend- backtrack if length of nodeSet = 1 and check non-Current node to pathTraveled
		else if (nodeSet.size() == 1) { //2) {
			System.out.println("in deadend");
//			for (Map.Entry<String, SwarmNode> entry : nodeSet.entrySet()) {
//				if (entry.getKey() != "Current") {
					// nodeSet.entrySet().iterator().next().getValue() => gives the SwarmNode of the only neighbor in nodeSet
//					if (pathTraveled.contains(nodeSet.entrySet().iterator().next().getValue())) {	// You have reached a deadend, pop the node, set it to deadend, and backtrack
					if (pathTraveled.peek() == nodeSet.entrySet().iterator().next().getValue()) {
						SwarmNode oldNode = pathTraveled.peek();
						pathTraveled.pop();
						oldNode.setIsOccupied(false);	// Exit old node
						oldNode.setIsDeadend(true);	// Set old node to deadend
						this.currentSwarmNode = pathTraveled.peek();	// Set new node
						this.currentSwarmNode.setIsOccupied(true);	// Set new node to occupied
						this.steps++;
						return oldNode;
					}
					else {
						SwarmNode oldNode = getCurrentSwarmNode();
						oldNode.setIsOccupied(false);	// To Do: to do or not to do?
						this.pathTraveled.push(nodeSet.entrySet().iterator().next().getValue());
						this.currentSwarmNode = pathTraveled.peek();
						if (this.currentSwarmNode.getIsEndNode()) {
							this.atEnd = true;
						}
						this.currentSwarmNode.setIsOccupied(true);
						this.steps++;
						return oldNode;
					}
//				}
//			}
		}
		// Neutral Node: follow the path that isn't currently in pathTraveled
		else if (nodeSet.size() == 2) {
			System.out.println("in neutral node");
			for (Map.Entry<String, SwarmNode> entry : nodeSet.entrySet()) {	// for the two entries in nodeSet
				System.out.println("in for loop: " + entry.getValue().getXYCoords());
				System.out.println("PathTraveled: " + pathTraveled.toString());
				System.out.println("Entry value: " + entry.getValue().getXYCoords());
				Pair neighCoords = entry.getValue().getXYCoords(); //pair form of the neighbor Swarm nodes
				System.out.println("pathTraveled.Contains: " + pathTraveled.contains(entry));
//				if (!pathTraveled.contains(entry.getValue().getXYCoords())) {	// if the coordinates of the SwarmNode isn't in pathTraveled
				if (!pathTraveled.contains(entry)) {
					System.out.println("path not contained");
					SwarmNode oldNode = getCurrentSwarmNode();
					oldNode.setIsOccupied(false);	// Exit old node
					pathTraveled.push(entry.getValue());	// Add new node to pathTraveled
					this.currentSwarmNode = pathTraveled.peek();	// Set new node
					if (this.currentSwarmNode.getIsEndNode()) {
						this.atEnd = true;
					}
					this.currentSwarmNode.setIsOccupied(true);	// Set new node to occupied
					this.steps++;
					return oldNode;
				}
			}
		}
		// Intersection
		// Set North, South, East, and West nodes
		//For when there's 3 or 4 viable paths to go. 
		else{
			
			System.out.println("3 or 4 paths to go");
			//System.out.println("nodeSet: " + nodeSet);
			System.out.println("pathTraveled: " + pathTraveled.toString());
			Random random  = new Random();
			List<String> directions = this.currentSwarmNode.leastTraveled(this.pathTraveled.peek()); //substitute nodeSet.keySet() to be the tied directions....
			SwarmNode oldNode = this.pathTraveled.peek();
			this.steps++;
			if (directions.size() == 1){
				oldNode.setRobotTraveled(directions.get(0));
				oldNode.setIsOccupied(false);
				this.currentSwarmNode = nodeSet.get(directions.get(0));
				this.pathTraveled.push(this.currentSwarmNode);
				this.currentSwarmNode.setIsOccupied(true);
				return oldNode;
			}
			
			else{
				boolean newPath = false;
				while(!newPath){
					System.out.println("while loop trap");
					String randomDirection = directions.get(random.nextInt(directions.size()));
					System.out.println(randomDirection);
					SwarmNode newDirection = nodeSet.get(randomDirection);
					System.out.println("newDirection: " + newDirection.getXYCoords());
//					if(this.pathTraveled.contains(newDirection) == false){
					if (!pathTraveled.contains(newDirection)){
						newPath = true;
						oldNode.setRobotTraveled(randomDirection);
						this.currentSwarmNode = newDirection;
					}
				}
				oldNode.setIsOccupied(false);
				pathTraveled.push(this.currentSwarmNode);
				// Add checks here
				// Update new node occupied and previous node occupied			
				this.currentSwarmNode.setIsOccupied(true);
				// To Do: check for end node and set atEnd if it is the end node
				return oldNode;
			}
		}
		return currentSwarmNode;
	}
	public SwarmNode update(Map<String, SwarmNode> nodeSet) {
//		SwarmNode previousCurrentNode = this.currentSwarmNode;
		// Choose a direction (all checks and whatnot) DFS & random
		SwarmNode oldNode = chooseDirection(nodeSet);
		System.out.println("Exited choose direction");
		if(this.currentSwarmNode.getIsEndNode()){
			this.atEnd = true;
		}
		// Check if new node is in stack already (means we are backtracking)
		// Update new node occupied and previous node occupied
//		this.currentSwarmNode.setIsOccupied(false);
//		this.currentSwarmNode = newNode;
//		this.currentSwarmNode.setIsOccupied(true);
		return oldNode;
	}
	public SwarmNode moveToEnd() {
		// TODO Auto-generated method stub
		SwarmNode oldNode = pathTraveled.peek();
		pathTraveled.pop();
		oldNode.setIsOccupied(false);	// Exit old node
		
		this.currentSwarmNode = pathTraveled.peek();	// Set new node
		this.currentSwarmNode.setIsOccupied(true);	// Set new node to occupied
		this.steps++;
		return oldNode;
	}
	public SwarmNode continueOnEnd(Stack<SwarmNode> stackToEnd, ArrayList<SwarmNode> neighbors) {
		// TODO Auto-generated method stub
		SwarmNode oldNode = pathTraveled.peek();
		oldNode.setIsOccupied(false);
		for(SwarmNode node : neighbors){
			if(stackToEnd.contains(node) && this.pathTraveled.contains(node)== false){
				this.currentSwarmNode = node;
				this.currentSwarmNode.setIsOccupied(true);
				this.steps++;
			}
		}
		
		return oldNode;
	}
}