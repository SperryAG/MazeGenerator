package RobotAI;
import MazeGenerator.*;
import java.util.*;

public class Swarm {
	private int robotCount;
	//Robot[] RobotSet;
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
		//RobotSet = new Robot[robotCount];
		for (int i = 0; i < this.robotCount; i++) {
			//RobotSet[i] = new Robot(findStart());
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
//		System.out.println(this.map.values());
		for (SwarmNode value : this.map.values()) {
			if (value.getIsStartNode()) {
				startNode = value;
				return value;
			}
		}
		return startNode;
	}
//	public void clear() {
//		this.RobotSet = null;
//		this.map = null;
//		this.endRobot = null;
//	}
//	public void copy(Swarm newSwarm) {
//		System.out.println("in copy");
//		this.robotCount = newSwarm.getRobotCount();
//		this.RobotSet.addAll(newSwarm.getRobotSet());
//		this.map.putAll(newSwarm.getMap());
//		this.endRobot = newSwarm.getEndRobot();
//	}
//	public boolean equals(Swarm newSwarm) {
//		return false; 
//	}
	public boolean update() {
		if(this.oneAtEnd == false){
			for (Robot currentRobot : this.RobotSet) {	// for every robot running the maze
				SwarmNode currentNode = currentRobot.getCurrentSwarmNode();
				Map<String, SwarmNode> neighbors = new HashMap<String, SwarmNode>();	// Includes current node and neighbor nodes
				//neighbors.put("Current", map.get(currentNode.getXYCoords()));
				System.out.println("Current node: " + currentNode.getXYCoords());
				for (Map.Entry<String, Pair> entry : currentNode.paths().entrySet()) {
					System.out.println("entry(possible path): " + entry.getValue() + " map: " + map.get(entry.getValue()));
					if (!currentRobot.getPathTraveled().contains(map.get(entry.getValue())) && !(map.get(entry.getValue()).isDeadend()) && (entry.getValue().equals(currentNode.getXYCoords()) == false )) {	// Only add nodes to neighbors that aren't currently occupied
						System.out.println("Node: " + entry.getValue() + " not occupied and not deadend");
						neighbors.put(entry.getKey(), map.get(entry.getValue()));
					}
				}
				System.out.println("Current Robot Ident: " + currentRobot.getIdent() + " XY: " + currentNode.getXYCoords().toString() + " neighbors:  " + neighbors);
				map.remove(currentNode.getXYCoords());
				map.put(currentNode.getXYCoords(), currentRobot.update(neighbors));	// Update robot's previous currentNode
				System.out.println("New current: " + currentRobot.getCurrentSwarmNode());
				map.remove(currentRobot.getCurrentSwarmNode().getXYCoords());
				map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());	// Update robot's new currentNode
				System.out.println("Map replaced? " + map.get(currentRobot.getCurrentSwarmNode().getXYCoords()));
				if (currentRobot.getAtEnd()) {	// Robot has found the end node
					System.out.println("One robot has found the end.");
					this.oneAtEnd = true;
					stackToEnd = currentRobot.getPathTraveled();
					SwarmNode temp = map.get(currentRobot.getCurrentSwarmNode().getXYCoords());
					temp.setIsOccupied(false);
					map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), temp);
					System.out.println("endNode: " + map.get(currentRobot.getCurrentSwarmNode().getXYCoords()));
					
					
					endRobot = currentRobot;
					RobotSet.remove(endRobot);
					if(RobotSet.isEmpty()){
						return true;
					} 
					else{
						return false;
					}
	//				List<Object> toReturn = new ArrayList<Object>();
	//				toReturn.add(true);
	//				toReturn.add(map)
				}
			}
		}	
		else{
			System.out.println("Moving remaining to end");
			if(RobotSet.isEmpty()){
				System.out.println("returning true");
				return true;
			}
			moveRobotToEnd(stackToEnd);
		}
		
//		List<Object> toReturn = new ArrayList<Object>();
//		toReturn.add(false);
//		toReturn.add(RobotSet);	// Add updated Robot moves 
//		toReturn.add(map);
		return false;
	}

	private void moveRobotToEnd(Stack<SwarmNode> stackToEnd) {
		// This will move all robots one step and if they are at end pop them from robotSet.
		System.out.println("moveRobotToEnd entered");
		ArrayList<Robot> robotsToRemove = new ArrayList<Robot>(); //TODO maybe I can't delete as I'm going through them....
			for(Robot currentRobot : this.RobotSet){
				SwarmNode currentNode = currentRobot.getCurrentSwarmNode();
				System.out.println("Robot ID: " + currentRobot.getIdent() +  " currentNode: " + currentNode.getXYCoords() + " " + map.get(currentNode.getXYCoords()));
				
				//currentRobot is at the end. Time to remove. 
				if(currentRobot.getCurrentSwarmNode().getIsEndNode()){
					System.out.println("remove robot");
					robotsToRemove.add(currentRobot);
					System.out.println("robotSetSize: " + RobotSet.size());
					if(RobotSet.isEmpty()){
						return;
					}
				}
				
				//backtrack until you hit the currentSwarmNode
				else if(stackToEnd.contains(currentRobot.getCurrentSwarmNode())==false){ //means that the currentRobot is not on the path to end
					System.out.println("backtracking to stack");
					map.remove(currentNode.getXYCoords());
					map.put(currentNode.getXYCoords(), currentRobot.moveToEnd());//moveToEnd will return a swarmNode similar to update.
					map.remove(currentRobot.getCurrentSwarmNode().getXYCoords());
					map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());	// Update robot's new currentNode
				}
				
				//once you already are traversing the stack to end.
				else if(stackToEnd.contains(currentRobot.getCurrentSwarmNode())){
					System.out.println("traveling to end");
					Map<String, SwarmNode> neighbors = new HashMap<String, SwarmNode>();
					for (Map.Entry<String, Pair> entry : currentNode.paths().entrySet()) {
						//System.out.println("entry(possible path): " + entry.getValue() + " map: " + map.get(entry.getValue()));
						if (!(map.get(entry.getValue()).isDeadend()) && (entry.getValue().equals(currentNode.getXYCoords()) == false )) {	// Only add nodes to neighbors that aren't currently occupied
							//System.out.println("Node: " + entry.getValue() + " not occupied and not deadend");
							neighbors.put(entry.getKey(), map.get(entry.getValue()));
						}
					}
					
					ArrayList<SwarmNode> neighbor = new ArrayList<SwarmNode>();
					for(SwarmNode swarmNode : neighbors.values()){
						neighbor.add(swarmNode);
					}
					System.out.println("exited neighbor");
					
					if(map.containsKey(currentNode.getXYCoords())){
						map.remove(currentNode.getXYCoords());
					}
					System.out.println("before puts line 180 Swarm.java " + currentNode.getXYCoords());
					
					map.put(currentNode.getXYCoords(), currentRobot.continueOnEnd(stackToEnd, neighbor));//update previous currentNode
					System.out.println("New current: " + currentRobot.getCurrentSwarmNode());
					map.remove(currentRobot.getCurrentSwarmNode().getXYCoords());
					map.put(currentRobot.getCurrentSwarmNode().getXYCoords(), currentRobot.getCurrentSwarmNode());	// Update robot's new currentNode
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
