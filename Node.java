package MazeGenerator;

class Node {
	// Variables
	private int xCoord;
	private int yCoord;
	private boolean isStartNode;
	private boolean isEndIntersection;
	private boolean isEndNode;
	private boolean isWall_North, isWall_East, isWall_South, isWall_West;
	
	// Constructors
	public Node() {
		xCoord = -1;
		yCoord = -1;
		isStartNode = false;
		isEndIntersection = false;
		isEndNode = false;
		isWall_North = isWall_East = isWall_South = isWall_West = true;
	}
	
	public Node(int xCoord, int yCoord, boolean isStartNode, boolean isEndIntersection,
			    boolean isEndNode, boolean isWall_North, boolean isWall_East, boolean isWall_South,
			    boolean isWall_West) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.isStartNode = isStartNode;
		this.isEndIntersection = isEndIntersection;
		this.isEndNode = isEndNode;
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
	public void setEndIntersection(boolean x) {
		this.isEndIntersection = x;
	}
	public void setEndNode(boolean x) {
		this.isEndNode = x;
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
	public boolean getStartNode() {
		return isStartNode;
	}
	public boolean getEndIntersection() {
		return isEndIntersection;
	}
	public boolean getEndNode() {
		return isEndNode;
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
	
	public boolean getIsStartNode(){
		return isStartNode;
	}
	
	public boolean getIsEndNode(){
		return isEndNode;
	}
	
	// Output Methods
	public String toString() {
		String output = "";
		
		output += "<Node>" + '\n';
		output += '\t' + "<Coordinates>(" + Integer.toString(xCoord) + "," 
										  + Integer.toString(yCoord) + ")</Coordinates>" + '\n';
		output += '\t' + "<StartNode>" + Boolean.toString(isStartNode) + "</StartNode>" + '\n';
		output += '\t' + "<EndIntersection>" + Boolean.toString(isEndIntersection) + "</EndIntersection>" + '\n';
		output += '\t' + "<EndNode>" + Boolean.toString(isEndNode) + "</EndNode>" + '\n';
		output += '\t' + "<WallNorth>" + Boolean.toString(isWall_North) + "</WallNorth>" + '\n';
		output += '\t' + "<WallSouth>" + Boolean.toString(isWall_East) + "</WallEast>" + '\n';
		output += '\t' + "<WallEast>" + Boolean.toString(isWall_South) + "</WallSouth>" + '\n';
		output += '\t' + "<WallWest>" + Boolean.toString(isWall_West) + "</WallWest>" + '\n';
		output += "</Node>";
		
		return output;
	}
}