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
	private int steps;
	private boolean atEnd;
	
	public Robot(SwarmNode startSwarmNode) {
		this.currentSwarmNode = startSwarmNode;
		this.pathTraveled = new Stack<SwarmNode>();
		this.pathTraveled.push(startSwarmNode);
		this.steps = 0;
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
//			for (Map.Entry<String, SwarmNode> entry : nodeSet.entrySet()) {
//				if (entry.getKey() != "Current") {
					// nodeSet.entrySet().iterator().next().getValue() => gives the SwarmNode of the only neighbor in nodeSet
					if (pathTraveled.contains(nodeSet.entrySet().iterator().next().getValue())) {	// You have reached a deadend, pop the node, set it to deadend, and backtrack
						SwarmNode oldNode = pathTraveled.peek();
						pathTraveled.pop();
						oldNode.setIsOccupied(false);	// Exit old node
						oldNode.setIsDeadend(true);	// Set old node to deadend
						this.currentSwarmNode = pathTraveled.peek();	// Set new node
						this.currentSwarmNode.setIsOccupied(true);	// Set new node to occupied
						this.steps++;
						return oldNode;
					}
//				}
//			}
		}
		// Neutral Node: follow the path that isn't currently in pathTraveled
		else if (nodeSet.size() == 2) {
			for (Map.Entry<String, SwarmNode> entry : nodeSet.entrySet()) {	// for the two entries in nodeSet
				if (!pathTraveled.contains(entry.getValue().getXYCoords())) {	// if the coordinates of the SwarmNode isn't in pathTraveled
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
					String randomDirection = directions.get(random.nextInt(directions.size()));
					SwarmNode newDirection = nodeSet.get(randomDirection);
					if(this.pathTraveled.contains(newDirection) == false){
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
		oldNode.setIsDeadend(true);	// Set old node to deadend
		this.currentSwarmNode = pathTraveled.peek();	// Set new node
		this.currentSwarmNode.setIsOccupied(true);	// Set new node to occupied
		this.steps++;
		return oldNode;
	}
}