package RobotAI;
import MazeGenerator.*;
import java.util.*;

public class Swarm {
	private int robotCount;
	ArrayList<Robot> RobotSet = new ArrayList<Robot>();
	Map<Pair, SwarmNode> map = new HashMap<Pair, SwarmNode>();
	Robot endRobot = null;
	boolean oneAtEnd = false;
	Stack<SwarmNode> stackToEnd = null;
	
	public Swarm () {
		this.robotCount = -1;
	}
	public Swarm (int robotCount, Node[] nodeArray) {
		this.robotCount = robotCount;
		this.map = convert(nodeArray);
		for (int i = 0; i < this.robotCount; i++) {
			RobotSet.add(new Robot(findStart(), i + 1));
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
		boolean backTrackOccupied = false;
		if(this.oneAtEnd == false){
			for (Robot currentRobot : this.RobotSet) {	// for every robot running the maze
				SwarmNode currentNode = currentRobot.getCurrentSwarmNode();
				Map<String, SwarmNode> neighbors = new HashMap<String, SwarmNode>();	// Includes current node and neighbor nodes
				for (Map.Entry<String, Pair> entry : currentNode.paths().entrySet()) {
					if (!currentRobot.getPathTraveled().contains(map.get(entry.getValue())) && !(map.get(entry.getValue()).isDeadend()) && (entry.getValue().equals(currentNode.getXYCoords()) == false )) {	// Only add nodes to neighbors that aren't currently occupied
						neighbors.put(entry.getKey(), map.get(entry.getValue()));
					}
					if(currentRobot.getPathTraveled().contains(map.get(entry.getValue()))){
						backTrackOccupied = map.get(entry.getValue()).isOccupied();
					}
				}
				map.remove(currentNode.getXYCoords());
				map.put(currentNode.getXYCoords(), currentRobot.update(neighbors, backTrackOccupied));	// Update robot's previous currentNode
				map.remove(currentRobot.getCurrentSwarmNode().getXYCoords());
				map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());	// Update robot's new currentNode
				if (currentRobot.getAtEnd()) {	// Robot has found the end node
					this.oneAtEnd = true;
					stackToEnd = currentRobot.getPathTraveled();
					SwarmNode temp = map.get(currentRobot.getCurrentSwarmNode().getXYCoords());
					temp.setIsOccupied(false);
					map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), temp);
					
					endRobot = currentRobot;
					RobotSet.remove(endRobot);
					if(RobotSet.isEmpty()){
						return true;
					} 
					else{
						return false;
					}
				}
			}
		}	
		else{
			if(RobotSet.isEmpty()){
				return true;
			}
			moveRobotToEnd(stackToEnd);
			return false;
		}
		return false;
	}

	private void moveRobotToEnd(Stack<SwarmNode> stackToEnd) {
		// This will move all robots one step and if they are at end pop them from robotSet.
		ArrayList<Robot> robotsToRemove = new ArrayList<Robot>(); //TODO maybe I can't delete as I'm going through them....
			for(Robot currentRobot : this.RobotSet){
				SwarmNode currentNode = currentRobot.getCurrentSwarmNode();

				//currentRobot is at the end. Time to remove. 
				if(currentRobot.getCurrentSwarmNode().getIsEndNode()){
					robotsToRemove.add(currentRobot);
					if(RobotSet.isEmpty()){
						return;
					}
				}
				
				else{
					Map<String, SwarmNode> neighbors = new HashMap<String, SwarmNode>();
					for(Map.Entry<String, Pair> entry : currentNode.paths().entrySet()){
						if ((entry.getValue().equals(currentNode.getXYCoords()) == false )) {	// Only add nodes to neighbors that aren't currently occupied
							neighbors.put(entry.getKey(), map.get(entry.getValue()));
						}
					}
						
					HashMap<Pair, SwarmNode> neighbor = new HashMap<Pair, SwarmNode>();
					for(SwarmNode swarmNode : neighbors.values()){
						neighbor.put(swarmNode.getXYCoords(), swarmNode);
					}
					
					if(map.containsKey(currentNode.getXYCoords())){
						map.remove(currentNode.getXYCoords());
					}
					
					map.put(currentNode.getXYCoords(), currentRobot.continueOnEnd(stackToEnd, neighbor));//update previous currentNode
					map.remove(currentRobot.getCurrentSwarmNode().getXYCoords());
					map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());	
					
				}
				
			}	
			for(Robot robot : robotsToRemove ){
				this.RobotSet.remove(robot);
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
			output += "</Swarm>";
			
			return output;
		}
}
