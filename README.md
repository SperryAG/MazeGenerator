Swarm Robot Maze Solver: MazeGenerator
--------------------------------------
Inspiration-
	We are creating this Maze Generator as the first part of our project to create a virtual Swarm Robotic Maze Solver. By the end of our project, we will be able to determine the optimal number of robots needed to most efficiently solve a maze of a certain complexity. The robots traversing the maze will be able to use the information in each node of the maze to determine which paths it can take and whether it has solved the maze or not. Using our complexity formula, the Maze Generator will also be able to determine the complexity of the mazes based off the parameters it is given. This complexity will help us determine a mazes difficulty compared to other mazes and will help us determine if there is an optimal robot count for solving mazes of different complexities. Furthermore, the ease with which the Maze Generator can create mazes will allow us to test our robots on a wide variety of different mazes and thereby increase the accuracy of the data we collect.
Synopsis-
	The MazeGenerator will create a maze based off of a set of parameters given by the user.
	The maze will output to a text file with an XML tag structure, so that it can later be read with ease. 
Possible Parameters-
	title- title of the maze
	gridSize- the dimension of the square you want the maze to be
	activeNodeCount- the number of accessible nodes within the grid (cannot be more than the gridSize squared) 
	branchFactor- is the sum of the number of branches at each intersection node subtracted by two
	complexity- a measure of difficulty based off of our complexity formula which takes into account the branching factor and the size of the maze 
	intersectionCount- the number of intersections within the maze (nodes in the maze that result in multiple paths) 
	deadendCount- the number of deadends in the maze (nodes from where you can only return where you came from)
	loopCount- the number of loops you want to incorporate in your maze (following a path that can return from where it began without backtracking)
	startNode- the x and y coordinates of where in the maze you want the start node to be located
	endIntersection- the intersection before the end node
	endNode- the x and y coordinates of where in the maze you want the end node to be located
MazeGenerator.java- reads in the parameters and creates a Maze object, which it prints out and stores in a file.
Maze.java-
	Constructs a Maze object based on the parameters given. Using this information, it is able to calculate the branching factor, if it isn't specifically assigned one, as well as the complexity. Each maze is constructed using node objects.
Node.java-
	Each node within a maze stores information about it's location in the maze (x and y coordinates), whether it is a start or end node, and which directions lead to pathways or are deadends.
Result.java-
	After robots have run a simulation of a maze, data, such as, the completion time and the number of robots that ran the maze, is stored in the Results class.
Output-
	The Maze Generator outputs to a text file that follows an XML tag structure. This structure organizes the file and will also make it easier to identify information when the file is being read. This will be useful for our UI to read the information stored in the file and to output the maze and it's identifying features correctly.