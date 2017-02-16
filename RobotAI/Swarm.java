package RobotAI;
import MazeGenerator.*;
import java.util.*;

public class Swarm {
	private int robotCount;
	Robot[] RobotSet;
	Map<Pair, SwarmNode> map;
	
	public Swarm (int robotCount, ArrayList<Node> nodeArray) {
		this.robotCount = robotCount;
		this.map = convert(nodeArray);
		for (int i = 0; i < this.robotCount; i++) {
			RobotSet[i] = new Robot(findStart());
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
	
	private void update() {
		for (Robot currentRobot : this.RobotSet) {
			SwarmNode currentNode = currentRobot.getCurrentSwarmNode();
			Map<String, SwarmNode> neighbors = new HashMap<String, SwarmNode>();	// Includes current node and neighbor nodes
//			neighbors.put("Current", map.get(currentNode.getXYCoords()));
			for (Map.Entry<String, Pair> entry : currentNode.paths().entrySet()) {
				if (!map.get(entry.getValue()).isOccupied()) {	// Only add nodes to neighbors that aren't currently occupied
					neighbors.put(entry.getKey(), map.get(entry.getValue()));
				}
			}
			map.put(currentNode.getXYCoords(), currentRobot.update(neighbors));	// Update robot's previous currentNode
			map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());	// Update robot's new currentNode
			if (currentRobot.getAtEnd()) {	// Robot has found the end node
				// Signal all other robots
			}
		}
	}
}
