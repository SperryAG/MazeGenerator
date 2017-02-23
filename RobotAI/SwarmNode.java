package RobotAI;
import java.util.ArrayList;

import MazeGenerator.*;

public class SwarmNode extends Node {
	private int robotsTraveledNorth = 0, robotsTraveledEast = 0, robotsTraveledSouth = 0, robotsTraveledWest = 0;
	private boolean occupied;
	private boolean deadend;
	
	public SwarmNode(Node n) {
		this.deadend = false;
		this.occupied = false;
		this.setXCoord(n.getXCoord());
		this.setYCoord(n.getYCoord());
		this.setStartNode(n.getIsStartNode());
		this.setEndNode(n.getIsEndNode());
		this.setIsIntersection(n.getIsIntersection());
		this.setNorthWall(n.getNorthWall());
		this.setEastWall(n.getEastWall());
		this.setSouthWall(n.getSouthWall());
		this.setWestWall(n.getWestWall());		
	}
	
	public String lastTraveledtoString(SwarmNode lastTraveled){
		String toReturn = null;
		Pair coordLastTraveled = lastTraveled.getXYCoords();
		
		if(this.getYCoord()+1 == coordLastTraveled.getYCoord()){
			toReturn = "North";
		}
		
		else if(this.getXCoord()+1 == coordLastTraveled.getXCoord()){
			toReturn = "East";
		}
		
		else if(this.getYCoord()-1 == coordLastTraveled.getYCoord()){
			toReturn = "South";
		}
		
		else if(this.getXCoord()-1 == coordLastTraveled.getXCoord()){
			toReturn = "West";
		}
		
		return toReturn;
	}
	
	public ArrayList<String> leastTraveled(SwarmNode lastTraveled){
		ArrayList<String> toReturn = new ArrayList<String>();
		String previousCoordString = lastTraveled.lastTraveledtoString(lastTraveled);

		toReturn.add("North");
		toReturn.add("East");
		toReturn.add("South");
		toReturn.add("West");
		toReturn.remove(previousCoordString);
		
		int min = Integer.MAX_VALUE;
		for(String direction : toReturn){
			if(getRobotTraveled(direction) <= min){
				min = getRobotTraveled(direction);
			}
		}
		
		ArrayList<String> temp = toReturn;
		for(String direction : temp){
			if(getRobotTraveled(direction)> min){
				toReturn.remove(direction);
			}
		}
		
		return toReturn;
	}
	
	public int getRobotTraveled(String direction){
		if (direction.equals("North")){
			return this.getRobotsTraveledNorth();
		}
		else if (direction.equals("East")){
			return this.getRobotsTraveledEast();
		}
		else if (direction.equals("South")){
			return this.getRobotsTraveledSouth();
		}
		else{
			return this.getRobotsTraveledWest();
		}
	}
	
	public void setRobotTraveled(String direction){
		if (direction.equals("North")){
			setRobotsTraveledNorth();
		}
		else if (direction.equals("East")){
			setRobotsTraveledEast();
		}
		else if (direction.equals("South")){
			setRobotsTraveledSouth();
		}
		else{
			setRobotsTraveledWest();
		}
	}
	
	// set number of robots that have traveled NORTH through this node
	public void setRobotsTraveledNorth() {
		this.robotsTraveledNorth++;
	}
	// set number of robots that have traveled EAST through this node
	public void setRobotsTraveledEast() {
		this.robotsTraveledEast++;
	}
	// set number of robots that have traveled SOUTH through this node
	public void setRobotsTraveledSouth() {
		this.robotsTraveledSouth++;
	}
	// set number of robots that have traveled WEST through this node
	public void setRobotsTraveledWest () {
		this.robotsTraveledWest++;
	}
	// set if node is occupied or not
	public void setIsOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	// set if node is a deadend
	public void isDeadend(boolean deadend) {
		this.deadend = deadend;
	}
	// returns the number of robots that have traveled this path
	public int getRobotsTraveledNorth() {
		return robotsTraveledNorth;
	}
	// returns the number of robots that have traveled this path
	public int getRobotsTraveledEast() {
		return robotsTraveledEast;
	}
	// returns the number of robots that have traveled this path
	public int getRobotsTraveledSouth() {
		return robotsTraveledSouth;
	}
	// returns the number of robots that have traveled this path
	public int getRobotsTraveledWest() {
		return robotsTraveledWest;
	}
	// returns if the node is occupied or not 
	public boolean isOccupied() {
		return occupied;
	}
	// returns if the node is a deadend or not
	public boolean isDeadend() {
		return deadend;
	}
	public boolean equals(Object obj) {
		return this.getXYCoords() == obj. 
	}
}
