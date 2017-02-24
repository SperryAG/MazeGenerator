package RobotAI;
import MazeGenerator.*;
import java.util.*;

public class Swarm {
	private int robotCount;
	ArrayList<Robot> RobotSet = new ArrayList<Robot>();
	Map<Pair, SwarmNode> map = new HashMap<Pair, SwarmNode>();
	Robot endRobot = null;
	
	public Swarm () {
		this.robotCount = -1;
	}
	public Swarm (int robotCount, Node[] nodeArray) {
		this.robotCount = robotCount;
		this.map = convert(nodeArray);
		for (int i = 0; i < this.robotCount; i++) {
			RobotSet.add(new Robot(findStart(), i));
		}
	}
	public Swarm (Swarm copy) {
		this.robotCount = copy.getRobotCount();
		for (Robot r : copy.getRobotSet()) {
			this.RobotSet.add(new Robot(r));
		}
		this.map.putAll(copy.getMap());
		this.endRobot = new Robot(copy.getEndRobot());
	}
	private Map<Pair, SwarmNode> convert(Node[] nodeArray) {
		for(Node n : nodeArray){
			SwarmNode toAdd = new SwarmNode(n);
			map.put(n.getXYCoords(), toAdd);
		}
		return map;
	}
	public int getRobotCount() {
		return this.robotCount;
	}
	public ArrayList<Robot> getRobotSet() {
		return RobotSet;
	}
	Map<Pair, SwarmNode> getMap() {
		return this.map;
	}
	public Robot getEndRobot() {
		return this.endRobot; 
	}
	private SwarmNode findStart() {
		SwarmNode startNode = null;
		for (SwarmNode value : this.map.values()) {
			if (value.getIsStartNode()) {
				startNode = value;
				return value;
			}
		}
		return startNode;
	}
	public boolean update() {
		for (Robot currentRobot : this.RobotSet) {	// for every robot running the maze
			SwarmNode currentNode = currentRobot.getCurrentSwarmNode();
			Map<String, SwarmNode> neighbors = new HashMap<String, SwarmNode>();	// Includes current node and neighbor nodes
			for (Map.Entry<String, Pair> entry : currentNode.paths().entrySet()) {
				if (!(map.get(entry.getValue()).isOccupied()) && !(map.get(entry.getValue()).isDeadend())) {	// Only add nodes to neighbors that aren't currently occupied
					neighbors.put(entry.getKey(), map.get(entry.getValue()));
				}
			}
			map.remove(currentNode.getXYCoords());
			map.put(currentNode.getXYCoords(), currentRobot.update(neighbors));	// Update robot's previous currentNode
			map.remove(currentRobot.getCurrentSwarmNode().getXYCoords());
			map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());	// Update robot's new currentNode
			if (currentRobot.getAtEnd()) {	// Robot has found the end node
				Stack<SwarmNode> stackToEnd = currentRobot.getPathTraveled();
				endRobot = currentRobot;
				RobotSet.remove(endRobot);
				if(!RobotSet.isEmpty()){
					moveRobotToEnd(RobotSet, stackToEnd);
				}
				return true;
			}
		}
		return false;
	}

	private void moveRobotToEnd(ArrayList<Robot> robotSet, Stack<SwarmNode> stackToEnd) {
		// Create a robot traversal
		boolean allAtEnd = false;
		while(!allAtEnd){
			for(Robot currentRobot : robotSet){
				SwarmNode currentNode = currentRobot.getCurrentSwarmNode();
				
				//currentRobot is at the end. Time to remove. 
				if(currentRobot.getAtEnd()){
					robotSet.remove(currentRobot);
					if(robotSet.isEmpty()){
						allAtEnd = true;
					}
				}
				
				//backtrack until you hit the currentSwarmNode
				else if(stackToEnd.contains(currentRobot.getCurrentSwarmNode())==false){ //means that the currentRobot is not on the path to end
					map.put(currentNode.getXYCoords(), currentRobot.moveToEnd());//moveToEnd will return a swarmNode similar to update.
					map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());
				}
				
				//once you already are traversing the stack to end.
				else if(stackToEnd.contains(currentRobot.getCurrentSwarmNode())){
					Map<String, SwarmNode> neighbors = new HashMap<String, SwarmNode>();
					for (Map.Entry<String, Pair> entry : currentNode.paths().entrySet()) {
						if (!map.get(entry.getValue()).isOccupied() && !(!map.get(entry.getValue()).isDeadend())) {	// Only add nodes to neighbors that aren't currently occupied
							neighbors.put(entry.getKey(), map.get(entry.getValue()));
						}
					}
					ArrayList<SwarmNode> neighbor = new ArrayList<SwarmNode>();
					for(SwarmNode swarmNode : neighbors.values()){
						neighbor.add(swarmNode);
					}
					map.put(currentNode.getXYCoords(), currentRobot.continueOnEnd(stackToEnd, neighbor));
					map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());
				}
				
			}
		}
		
	}
	// Output Methods
		public String toString() {
			String output = "";
			
			output += "<Swarm>" + '\n';
			output += '\t' + "<robotCount>(" + Integer.toString(this.getRobotCount()) + ")</robotCount>" + '\n';
			output += '\t' + "<robotCount>(" + '\t';
			for (Robot r : this.RobotSet) {
				output += '\t' + r.toString() + '\n';
			}
			output += ")</robotCount>" + '\n';
			output += '\t' + "<Map>" + this.getMap().toString() + "</Map>" + '\n';
//			if (this.getEndRobot() == null) {
//				output += '\t' + "<endRobot>" + "null" + "</endRobot>" + '\n';
//			} 
//			else {	
//				output += '\t' + "<endRobot>" + this.getEndRobot().toString() + "</endRobot>" + '\n';
//			}
			output += "</Swarm>";
			
			return output;
		}
}
