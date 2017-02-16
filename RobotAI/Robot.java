package RobotAI;
import MazeGenerator.*;
import java.util.EmptyStackException;
import java.util.Map;
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
		// Deadend- backtrack if length of nodeSet = 2 and check non-Current node to pathTraveled
		if (nodeSet.size() == 1) { //2) {
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
						return oldNode;
					}
//				}
//			}
		}
		// Neutral Node: follow the path that isn't currently in pathTraveled
		if (nodeSet.size() == 2) {
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
					return oldNode;
				}
			}
		}
		// Intersection
		// Set North, South, East, and West nodes
		SwarmNode northNode = nodeSet.get("North");
		SwarmNode eastNode = nodeSet.get("East");
		SwarmNode southNode = nodeSet.get("South");
		SwarmNode westNode = nodeSet.get("West");
		SwarmNode newNode = northNode;	// To Do: remove instantiation (proceeding checks will set newNode)
		// Add checks here
		// Update new node occupied and previous node occupied
		this.currentSwarmNode.setIsOccupied(false);
		this.currentSwarmNode = newNode;
		this.currentSwarmNode.setIsOccupied(true);
		// To Do: check for end node and set atEnd if it is the end node
		return oldNode;
	}
	public SwarmNode update(Map<String, SwarmNode> nodeSet) {
//		SwarmNode previousCurrentNode = this.currentSwarmNode;
		// Choose a direction (all checks and whatnot) DFS & random
		SwarmNode oldNode = chooseDirection(nodeSet);
		// Check if new node is in stack already (means we are backtracking)
		// Update new node occupied and previous node occupied
//		this.currentSwarmNode.setIsOccupied(false);
//		this.currentSwarmNode = newNode;
//		this.currentSwarmNode.setIsOccupied(true);
		return oldNode;
	}
}