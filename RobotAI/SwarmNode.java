package RobotAI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
	public SwarmNode(SwarmNode s) {
		this.deadend = s.isDeadend();
		this.occupied = s.isOccupied();
		this.setXCoord(s.getXCoord());
		this.setYCoord(s.getYCoord());
		this.setStartNode(s.getIsStartNode());
		this.setEndNode(s.getIsEndNode());
		this.setIsIntersection(s.getIsIntersection());
		this.setNorthWall(s.getNorthWall());
		this.setEastWall(s.getEastWall());
		this.setSouthWall(s.getSouthWall());
		this.setWestWall(s.getWestWall());
	}
	public String lastTraveledtoString(SwarmNode lastTraveled){
		String toReturn = "error";
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
	
	public ArrayList<String> leastTraveled(Set<String> directionsTraveled){
		ArrayList<String> toReturn = new ArrayList<String>();
		
		int min = Integer.MAX_VALUE;
		for(String direction : directionsTraveled){
			if(getRobotTraveled(direction) <= min){
				min = getRobotTraveled(direction);
			}
		}
				
		for(String direction : directionsTraveled){
			if(getRobotTraveled(direction)<= min){
				toReturn.add(direction);
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
	public boolean equals(SwarmNode obj) {
		return this.getXYCoords().equals(obj.getXYCoords()); // this.getXCoord() == obj.getXCoord() && this.getYCoord() == obj.getYCoord();
	}
	public int hashCode() {
	    int hash = this.getXCoord()+this.getYCoord();
	    return hash;
	}
	
	public ArrayList<String> emptyWalls(SwarmNode swarm){
		ArrayList<String> toReturn = new ArrayList<String>();
		if(!this.getNorthWall()){
			toReturn.add("North");
		}
		if(!this.getEastWall()){
			toReturn.add("East");
		}
		if(!this.getSouthWall()){
			toReturn.add("South");
		}
		if(!this.getWestWall()){
			toReturn.add("West");
		}
		return toReturn;
	}
	
	// Output Methods
	public String toString() {
		String output = "";
		
		output += "<SwarmNode>" + '\n';
		output += '\t' + "<Coordinates>(" + Integer.toString(this.getXCoord()) + "," + Integer.toString(this.getYCoord()) + ")</Coordinates>" + '\n';
		output += '\t' + "<StartNode>" + Boolean.toString(this.getIsStartNode()) + "</StartNode>" + '\n';
		output += '\t' + "<EndNode>" + Boolean.toString(this.getIsEndNode()) + "</EndNode>" + '\n';
		output += '\t' + "<DeadendNode>" + Boolean.toString(this.deadend) + "</DeadendNode>" + '\n';
		output += '\t' + "<Occupied>" + Boolean.toString(this.occupied) + "</Occupied>" + '\n';
		output += '\t' + "<RobotsTraveledNorth>" + Integer.toString(this.getRobotsTraveledNorth()) + "</RobotsTraveledNorth>" + '\n';
		output += '\t' + "<RobotsTraveledEast>" + Integer.toString(this.getRobotsTraveledEast()) + "</RobotsTraveledEast>" + '\n';
		output += '\t' + "<RobotsTraveledSouth>" + Integer.toString(this.getRobotsTraveledSouth()) + "</RobotsTraveledSouth>" + '\n';
		output += '\t' + "<RobotsTraveledWest>" + Integer.toString(this.getRobotsTraveledWest()) + "</RobotsTraveledWest>" + '\n';
		output += "</SwarmNode>";
		
		return output;
	}
}
