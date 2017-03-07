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
		//There's no viable place to go. All Nodes nearby are occupied.  
		if(nodeSet.size() == 0){ //DeadEnd
			/*SwarmNode oldNode = pathTraveled.peek();
			this.currentSwarmNode = oldNode;
			//this.steps++;
			return oldNode;*/
			System.out.println("DeadEnd");
			SwarmNode oldNode = this.pathTraveled.peek();
			this.pathTraveled.pop();
			oldNode.setIsOccupied(false);
			oldNode.isDeadend(true);
			this.currentSwarmNode = pathTraveled.peek();
			this.currentSwarmNode.setIsOccupied(true);
			this.steps++;
			return oldNode;
		}
				
	else if (nodeSet.size() == 1) { // Neutral Node, it could be blocked. Also, could be start case. 
			System.out.println("in Neutral Node");
			SwarmNode toCheck = nodeSet.entrySet().iterator().next().getValue();
			
			//The only node to travel to, is occupied. So current Robot do nothing.
			if(toCheck.isOccupied()){
				System.out.println("Neutral Node Occupied");
				SwarmNode oldNode = pathTraveled.peek();
				this.currentSwarmNode = oldNode;
				return oldNode;
			}
			//Otherwise, we shall traverse the only path to go. 
			else{
				System.out.println("Neutral Node NOT Occupied");
				SwarmNode oldNode = pathTraveled.peek();
				oldNode.setIsOccupied(false);
				oldNode.setRobotTraveled(nodeSet.entrySet().iterator().next().getKey());
				this.pathTraveled.push(toCheck);
				this.currentSwarmNode = this.pathTraveled.peek();
				if (this.currentSwarmNode.getIsEndNode()) {
					this.atEnd = true;
				}
				this.currentSwarmNode.setIsOccupied(true);
				this.steps++;
				return oldNode;
			}			
		}
		
		// Intersection
		// Set North, South, East, and West nodes
		//For when there's 2 or 3 viable paths to go. 
		else{
			System.out.println("2 or 3 paths to go");
			//System.out.println("nodeSet: " + nodeSet);
//			System.out.println("pathTraveled: " + pathTraveled.toString());
			Random random  = new Random();
			//List<String> directions = this.currentSwarmNode.leastTraveled(this.pathTraveled.peek()); //substitute nodeSet.keySet() to be the tied directions....
			
			System.out.println("Trying to get directions");
			
			ArrayList<String> toRemove = new ArrayList<String>();
			for(Map.Entry<String, SwarmNode> entry : nodeSet.entrySet()){
				if(entry.getValue().isOccupied()){
					toRemove.add(entry.getKey());
				}
			}
			for(String remove : toRemove){
				nodeSet.remove(remove);
			}
			
			
			System.out.println("KeySet in 1 or 2 or 3: " + nodeSet.keySet());
			List<String> directions = this.currentSwarmNode.leastTraveled(nodeSet.keySet());
			System.out.println("directions have been set: " + directions);
			
			if (directions.size() == 0) {	// If all possible paths are occupied
				SwarmNode oldNode = pathTraveled.peek();
				this.currentSwarmNode = oldNode;
				return oldNode;
			}
			
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
				this.pathTraveled.push(node);
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