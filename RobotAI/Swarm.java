package RobotAI;
import MazeGenerator.*;
import java.util.*;

public class Swarm {
	private int robotCount;
	//Robot[] RobotSet;
	ArrayList<Robot> RobotSet = new ArrayList<Robot>();
	Map<Pair, SwarmNode> map;
	Robot endRobot = null;
	
	public Swarm (int robotCount, ArrayList<Node> nodeArray) {
		this.robotCount = robotCount;
		this.map = convert(nodeArray);
		//RobotSet = new Robot[robotCount];
		for (int i = 0; i < this.robotCount; i++) {
			//RobotSet[i] = new Robot(findStart());
			RobotSet.add(new Robot(findStart()));
		}
	}
	
	private Map<Pair, SwarmNode> convert(ArrayList<Node> nodeArray) {
		for(Node n : nodeArray){
			SwarmNode toAdd = new SwarmNode(n);
			map.put(n.getXYCoords(), toAdd);
		}
		return map;
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
	
	private boolean update() {
		for (Robot currentRobot : this.RobotSet) {
			SwarmNode currentNode = currentRobot.getCurrentSwarmNode();
			Map<String, SwarmNode> neighbors = new HashMap<String, SwarmNode>();	// Includes current node and neighbor nodes
//			neighbors.put("Current", map.get(currentNode.getXYCoords()));
			for (Map.Entry<String, Pair> entry : currentNode.paths().entrySet()) {
				if (!map.get(entry.getValue()).isOccupied() && !(!map.get(entry.getValue()).isDeadend())) {	// Only add nodes to neighbors that aren't currently occupied
					neighbors.put(entry.getKey(), map.get(entry.getValue()));
				}
			}
			map.put(currentNode.getXYCoords(), currentRobot.update(neighbors));	// Update robot's previous currentNode
			map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());	// Update robot's new currentNode
			if (currentRobot.getAtEnd()) {	// Robot has found the end node
				//TODO function to getall other robots to get here
				Stack<SwarmNode> stackToEnd = currentRobot.getPathTraveled();
				endRobot = currentRobot;
				RobotSet.remove(endRobot);
				if(!RobotSet.isEmpty()){
					moveRobotToEnd(RobotSet, stackToEnd);
				}
				//stackToEnd 
				//set currentRobot to end Robot
				// remove end robot from RobotSet
				//robot to solution function.
				return true;// Signal all other robots
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
				if(stackToEnd.contains(currentRobot.getCurrentSwarmNode())==false){ //means that the currentRobot is not on the path to end
					currentRobot.moveToEnd();//moveToEnd will return a swarmNode similar to update. 
					
					
				}
			}
		}
		
	}
}
