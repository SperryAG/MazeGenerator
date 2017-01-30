package RobotAI;
import MazeGenerator.*;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

public class Robot {
	private SwarmNode currentSwarmNode;
	private Stack<SwarmNode> pathTraveled;
	private int steps;
	
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
	// remove a SwarmNode from the path (when backtracking)
	public boolean popPathTraveled() {
		try {
			pathTraveled.pop();
		}
		catch (EmptyStackException e) {
			return false;
		}
		return true;
	}
	private SwarmNode chooseDirection(Map<String, SwarmNode> nodeSet) {
		// Set North, South, East, and West nodes
		SwarmNode northNode = nodeSet.get("North");
		SwarmNode eastNode = nodeSet.get("East");
		SwarmNode southNode = nodeSet.get("South");
		SwarmNode westNode = nodeSet.get("West");
		SwarmNode newNode = northNode;	// To Do: remove instantiation (proceeding checks will set newNode)
		// Add checks here
		return newNode;
	}
	public SwarmNode update(Map<String, SwarmNode> nodeSet) {
		SwarmNode previousCurrentNode = this.currentSwarmNode;
		// Choose a direction (all checks and whatnot) DFS & random
		SwarmNode newNode = chooseDirection(nodeSet);
		// Update new node occupied and previous node occupied
		this.currentSwarmNode.setIsOccupied(false);
		this.currentSwarmNode = newNode;
		this.currentSwarmNode.setIsOccupied(true);
		return previousCurrentNode;
	}
}