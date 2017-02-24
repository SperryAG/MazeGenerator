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
	public Robot (Robot copy) {
		if (copy == null) {
			return;
		}
		this.currentSwarmNode = new SwarmNode(copy.getCurrentSwarmNode());
		this.pathTraveled = new Stack<SwarmNode>();
		for (SwarmNode s : copy.getPathTraveled()) {
			this.pathTraveled.push(s);
		}
		this.steps = copy.getSteps();
		this.ident = copy.getIdent();
		this.atEnd = copy.getAtEnd();
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
	private SwarmNode chooseDirection(Map<String, SwarmNode> nodeSet) {
		//There's no viable place to go. 
		if(nodeSet.size() == 0){
			SwarmNode oldNode = pathTraveled.peek();
			this.currentSwarmNode = oldNode;
			//this.steps++;
			return oldNode;
		}
				
		// Deadend- backtrack if length of nodeSet = 1 and check non-Current node to pathTraveled
		else if (nodeSet.size() == 1) { //2) {
			System.out.println("in deadend");
			if (pathTraveled.contains(nodeSet.entrySet().iterator().next().getValue())) {	// You have reached a deadend, pop the node, set it to deadend, and backtrack	
				SwarmNode oldNode = pathTraveled.peek();
				pathTraveled.pop();
				oldNode.setIsOccupied(false);	// Exit old node
				oldNode.isDeadend(true);	// Set old node to deadend
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
		}
		// Neutral Node: follow the path that isn't currently in pathTraveled
		else if (nodeSet.size() == 2) {
			System.out.println("in neutral node");
			for (Map.Entry<String, SwarmNode> entry : nodeSet.entrySet()) {	// for the two entries in nodeSet
				Pair neighCoords = entry.getValue().getXYCoords(); //pair form of the neighbor Swarm nodes
				if (!(pathTraveled.contains(entry.getValue()))) {	// if the coordinates of the SwarmNode isn't in pathTraveled
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
					if (!pathTraveled.contains(newDirection)){
						newPath = true;
						oldNode.setRobotTraveled(randomDirection);
						this.currentSwarmNode = newDirection;
					}
				}
				oldNode.setIsOccupied(false);
				pathTraveled.push(this.currentSwarmNode);			
				this.currentSwarmNode.setIsOccupied(true);
				return oldNode;
			}
		}
		return currentSwarmNode;
	}
	public SwarmNode update(Map<String, SwarmNode> nodeSet) {
		// Choose a direction (all checks and whatnot) DFS & random
		SwarmNode oldNode = chooseDirection(nodeSet);
		if(this.currentSwarmNode.getIsEndNode()){
			this.atEnd = true;
		}
		return oldNode;
	}
	public SwarmNode moveToEnd() {
		SwarmNode oldNode = pathTraveled.peek();
		pathTraveled.pop();
		oldNode.setIsOccupied(false);	// Exit old node
		
		this.currentSwarmNode = pathTraveled.peek();	// Set new node
		this.currentSwarmNode.setIsOccupied(true);	// Set new node to occupied
		this.steps++;
		return oldNode;
	}
	public SwarmNode continueOnEnd(Stack<SwarmNode> stackToEnd, ArrayList<SwarmNode> neighbors) {
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
	// Output Methods
		public String toString() {
			String output = "";
			
			output += "<Robot>" + '\n';
			output += '\t' + "<ident>" + Integer.toString(this.getIdent()) + "</ident>" + '\n';
			output += '\t' + this.getCurrentSwarmNode().toString() + '\n';
			output += '\t' + "<pathTraveled>" + this.getPathTraveled().toString() + "</pathTraveled>" + '\n';
			output += '\t' + "<steps>" + Integer.toString(this.getSteps()) + "</steps>" + '\n';
			output += '\t' + "<atEnd>" + Boolean.toString(this.getAtEnd()) + "</atEnd>" + '\n';
			output += "</Robot>";
			
			return output;
		}
}