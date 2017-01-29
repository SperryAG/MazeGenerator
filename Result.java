package MazeGenerator;

class Result {
	/* Variables */
	private int robotCount;
	private int completionTime; // In seconds
	
	/* Constructors */
	public Result() {
		robotCount = -1;
		completionTime = -1;
	}
	public Result(int robotCount, int completionTime) {
		this.robotCount = robotCount;
		this.completionTime = completionTime;
	}
	
	/* Methods */
	// Helper Methods
	public void setRobotCount(int robotCount) {
		this.robotCount = robotCount;
	}
	public int getRobotCount() {
		return robotCount;
	}
	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}
	public int getCompletionTime() {
		return completionTime;
	}
	
	
	// Output Methods
	public String toString() {
		String output = "";
		
		output += "<Result>(" + robotCount + "," + completionTime + ")</Result>";
		
		return output;
	}
}