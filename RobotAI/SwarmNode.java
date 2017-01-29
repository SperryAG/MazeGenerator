package RobotAI;
import MazeGenerator.*;

public class SwarmNode extends Node {
	private int robotsTraveledNorth = 0, robotsTraveledEast = 0, robotsTraveledSouth = 0, robotsTraveledWest = 0;
	private boolean occupied;
	private boolean deadend;
	private int xCoord;
	private int yCoord;
	private boolean isStartNode;
	private boolean isEndNode;
	private boolean isIntersection;
	private boolean isWall_North, isWall_East, isWall_South, isWall_West;
	
	public SwarmNode(Node n) {
		this.deadend = false;
		this.occupied = false;
		this.xCoord = n.getXCoord();
		this.yCoord = n.getYCoord();
		this.isStartNode = n.getIsStartNode();
		this.isEndNode = n.getIsEndNode();
		this.isIntersection = n.getIsIntersection();
		this.isWall_North = n.getNorthWall();
		this.isWall_East = n.getEastWall();
		this.isWall_South = n.getSouthWall();
		this.isWall_West = n.getWestWall();		
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
}
