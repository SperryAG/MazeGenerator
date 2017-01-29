package MazeGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Node {
	// Variables
	private int xCoord;
	private int yCoord;
	private boolean isStartNode;
	private boolean isEndNode;
	private boolean isEndIntersection;
	private boolean isOptimalPath;
	private boolean isIntersection;
	private boolean isDeadend;
	private boolean isCoreNode;
	private boolean isLongestTailNode;
	private boolean isWall_North, isWall_East, isWall_South, isWall_West;
	
	// Constructors
	public Node() {
		xCoord = -1;
		yCoord = -1;
		isStartNode = false;
		isEndNode = false;
		isEndIntersection = false;
		isOptimalPath = false;
		isIntersection = false;
		isDeadend = false;
		isCoreNode = false;
		isLongestTailNode = false;
		isWall_North = isWall_East = isWall_South = isWall_West = true;
	}
	public Node(int xCoord, int yCoord, boolean isStartNode){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.isStartNode = isStartNode;
		isEndNode = false;
		isEndIntersection = false;
		isOptimalPath = false;
		isIntersection = false;
		isDeadend = false;
		isCoreNode = false;
		isLongestTailNode = false;
		isWall_North = isWall_East = isWall_South = isWall_West = false;
	}
	
	public Node(int xCoord, int yCoord, boolean isStartNode, boolean isEndIntersection,
			    boolean isEndNode, boolean isWall_North, boolean isWall_East, boolean isWall_South,
			    boolean isWall_West) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.isStartNode = isStartNode;
		this.isEndNode = isEndNode;
		this.isEndIntersection = isEndIntersection;
		isOptimalPath = false;
		isIntersection = false;
		isDeadend = false;
		isCoreNode = false;
		isLongestTailNode = false;
		this.isWall_North = isWall_North;
		this.isWall_East = isWall_East;
		this.isWall_South = isWall_South;
		this.isWall_West = isWall_West;
	}
	
	// Methods
	// Helper Methods
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	public void setStartNode(boolean x) {
		this.isStartNode = x;
	}
	public void setEndNode(boolean x) {
		this.isEndNode = x;
	}
	public void setEndIntersection(boolean x) {
		this.isEndIntersection = x;
	}
	public void setIsOptimalPath(boolean x) {
		this.isOptimalPath = x;
	}
	public void setIsIntersection(boolean x) {
		this.isIntersection = x;
	}
	public void setIsDeadend(boolean x) {
		this.isDeadend = x;
	}
	public void setIsCoreNode(boolean x) {
		this.isCoreNode = x;
	}
	public void setIsLongestTailNode(boolean x) {
		this.isLongestTailNode = x;
	}
	public void setNorthWall(boolean x) {
		this.isWall_North = x;
	}
	public void setEastWall(boolean x) {
		this.isWall_East = x;
	}
	public void setSouthWall(boolean x) {
		this.isWall_South = x;
	}
	public void setWestWall(boolean x) {
		this.isWall_West = x;
	}
	public int getXCoord() {
		return xCoord;
	}
	public int getYCoord() {
		return yCoord;
	}
	public Pair getXYCoords() {
		return new Pair(this.getXCoord(), this.getYCoord());
	}
	// Returns (x,y) coordinates of this node
	public Set<Pair> getCoords() {
		Set<Pair> coords = new HashSet<Pair>();
		Pair newPair = new Pair(this.getXCoord(), this.getYCoord());
		coords.add(newPair);
		return coords;
	}
	public boolean getStartNode() {
		return isStartNode;
	}
	public boolean getEndNode() {
		return isEndNode;
	}
	public boolean getIsEndIntersection() {
		return isEndIntersection;
	}
	public boolean getIsOptimalPath() {
		return isOptimalPath;
	}
	public boolean getIsIntersection() {
		return isIntersection;
	}
	public boolean getIsDeadend() {
		return isDeadend;
	}
	public boolean getIsCoreNode() {
		return isCoreNode;
	}
	public boolean getIsLongestTailNode() {
		return isLongestTailNode;
	}
	public boolean getNorthWall() {
		return isWall_North;
	}
	public boolean getEastWall() {
		return isWall_East;
	}
	public boolean getSouthWall() {
		return isWall_South;
	}
	public boolean getWestWall() {
		return isWall_West;
	}
	
	public void printWalls(){
		if(this.getNorthWall()){System.out.print("North ");}
		if(this.getEastWall()){System.out.print("East ");}
		if(this.getSouthWall()){System.out.print("South ");}
		if(this.getWestWall()){System.out.print("west");}
	}
	
	public ArrayList<Integer> getWalls(Set<Pair> coords, int gridSize){
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		
		if(this.getNorthWall() && !coords.contains(new Pair(this.getXCoord(), this.getYCoord() + 1)) && this.getYCoord() + 1 <= gridSize){
			toReturn.add(0);
		}
		if(this.getEastWall() && !coords.contains(new Pair(this.getXCoord() + 1, this.getYCoord())) && this.getXCoord()+1 <= gridSize){
			toReturn.add(1);
		}
		if(this.getSouthWall() && !coords.contains(new Pair(this.getXCoord(), this.getYCoord() -1 )) && this.getYCoord()-1 > 0){
			toReturn.add(2);
		}
		if(this.getWestWall() && !coords.contains(new Pair(this.getXCoord() -1 , this.getYCoord())) && this.getXCoord() -1 > 0){
			toReturn.add(3);
		}
		
		return toReturn;
	}
	public void updateWalls(Set<Pair> coords) {
		Random rand = new Random();
		if(this.getNorthWall() == true && coords.contains(new Pair(this.getXCoord(), this.getYCoord()+1))){
			int percent = rand.nextInt(2);
			if(percent == 0){
				this.setNorthWall(false);
			}
		}
		if(this.getEastWall() == true && coords.contains(new Pair(this.getXCoord() +1, this.getYCoord()))){
			int percent = rand.nextInt(2);
			if(percent == 0){
				this.setEastWall(false);
			}
		}
		if(this.getSouthWall() == true && coords.contains(new Pair(this.getXCoord(), this.getYCoord() -1))){
			int percent = rand.nextInt(2);
			if(percent == 0){
				this.setSouthWall(false);
			}
		}
		if(this.getWestWall() == true && coords.contains(new Pair(this.getXCoord() -1, this.getYCoord()))){
			int percent = rand.nextInt(2);
			if(percent == 0){
				this.setWestWall(false);
			}
		}
	}
	// Returns which directions can be traveled from this node
	//Returns it as a set of Pair(x,y) coordinates
	public Set<Pair> isPath() {
		Set<Pair> toReturn = new HashSet<Pair>();
		if (!this.getNorthWall()) {
			toReturn.add(new Pair(this.getXCoord(), this.getYCoord() + 1));
		}
		if (!this.getEastWall()) {
			toReturn.add(new Pair(this.getXCoord() + 1, this.getYCoord()));
		}
		if (!this.getSouthWall()) {
			toReturn.add(new Pair(this.getXCoord(), this.getYCoord() - 1));
		}
		if (!this.getWestWall()) {
			toReturn.add(new Pair(this.getXCoord() - 1, this.getYCoord()));
		}
		return toReturn;
	}
	public boolean getIsStartNode(){
		return isStartNode;
	}
	
	public boolean getIsEndNode(){
		return isEndNode;
	}
	
	public boolean isFull(){
		return !(this.isWall_East || this.isWall_North || this.isWall_South || this.isWall_West);
	}
	
	// Output Methods
	public String toString() {
		String output = "";
		
		output += "<Node>" + '\n';
		output +=  "<Coordinates>(" + Integer.toString(xCoord) + "," + Integer.toString(yCoord) + ")</Coordinates>" + '\n';
		output += "<StartNode>" + Boolean.toString(isStartNode) + "</StartNode>" + '\n';
		output +=   "<EndNode>" + Boolean.toString(isEndNode) + "</EndNode>" + '\n';
		output +=  "<EndIntersectionNode>" + Boolean.toString(isEndIntersection) + "</EndIntersectionNode>" + '\n';
		output +=  "<OptimalPathNode>" + Boolean.toString(isOptimalPath) + "</OptimalPathNode>" + '\n';
		output +=  "<IntersectionNode>" + Boolean.toString(isIntersection) + "</IntersectionNode>" + '\n';
		output += "<DeadendNode>" + Boolean.toString(isDeadend) + "</DeadendNode>" + '\n';
		output +=  "<CoreNode>" + Boolean.toString(isCoreNode) + "</CoreNode>" + '\n';
		output +=  "<LongestTailNode>" + Boolean.toString(isLongestTailNode) + "</LongestTailNode>" + '\n';
		output +=  "<WallNorth>" + Boolean.toString(isWall_North) + "</WallNorth>" + '\n';
		output +=  "<WallEast>" + Boolean.toString(isWall_East) + "</WallEast>" + '\n';
		output +=  "<WallSouth>" + Boolean.toString(isWall_South) + "</WallSouth>" + '\n';
		output +=  "<WallWest>" + Boolean.toString(isWall_West) + "</WallWest>" + '\n';
		output += "</Node>";
		
		return output;
	}
}