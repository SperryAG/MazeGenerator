package MazeGenerator;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JSlider;
import java.awt.event.InputMethodListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MazeGeneratorUI {
	
	private JFrame frmMansiMazegenerator;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	/* Settings */
	private final int    FRAMEWIDTH         = 800;
	private final int    FRAMEHEIGHT        = 600;
	
	private JFormattedTextField inpMazeTitle;
	private JTextField inpMazeTitle2;
	private JTextField inpEndNodeX;
	private JTextField inpEndNodeY;
	private JTextField inpStartNodeX;
	private JTextField inpStartNodeY;
	private JTextField txtBranchFactor;
	private JTextField txtComplexity;
	
	private String[] fileList = {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt",
			                     "file6.txt", "file7.txt", "file8.txt", "file9.txt", "file10.txt"};
	
	ArrayList<JPanel> nodePanelList = new ArrayList<JPanel>();
	
	/*Launch the application. */
	public static void main(String[] args) {
		Maze maze = new Maze();
		String filename = "TestMaze_9x9_46_0.7142857142857143_75.0_2017-01-29-02-17-19.txt";
    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeGeneratorUI window = new MazeGeneratorUI(maze);
					window.frmMansiMazegenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// Create a test maze variable
		maze.setTitle("TestMaze");
		maze.setGridSize(9);
		maze.setActiveNodeCount(46);
		Node[] tempNodeArray = new Node[46];
			tempNodeArray[0] = new Node(1,1,false,false,false,false,false,true,true);
			tempNodeArray[1] = new Node(2,1,false,false,false,true,false,true,false);
			tempNodeArray[2] = new Node(3,1,false,false,false,false,true,true,false);
			tempNodeArray[3] = new Node(7,1,false,false,false,false,false,true,true);
			tempNodeArray[4] = new Node(8,1,false,false,false,true,false,true,false);
			tempNodeArray[5] = new Node(9,1,false,false,true,true,true,true,false);
			
			tempNodeArray[6] = new Node(1,2,false,false,false,false,true,false,true);
			tempNodeArray[7] = new Node(3,2,false,false,false,false,true,false,true);
			tempNodeArray[8] = new Node(5,2,false,false,false,false,false,true,true);
			tempNodeArray[9] = new Node(6,2,false,false,false,true,false,true,false);
			tempNodeArray[10] = new Node(7,2,false,true,false,false,true,false,false);
			
			tempNodeArray[11] = new Node(1,3,false,false,false,false,true,false,true);
			tempNodeArray[12] = new Node(3,3,false,false,false,false,true,false,true);
			tempNodeArray[13] = new Node(5,3,false,false,false,false,true,false,true);
			tempNodeArray[14] = new Node(7,3,false,false,false,false,true,false,true);
			tempNodeArray[15] = new Node(9,3,false,false,false,false,true,true,true);
			
			tempNodeArray[16] = new Node(1,4,false,false,false,false,true,false,true);
			tempNodeArray[17] = new Node(3,4,false,false,false,false,true,false,true);
			tempNodeArray[18] = new Node(5,4,false,false,false,false,true,false,true);
			tempNodeArray[19] = new Node(7,4,false,false,false,false,true,false,true);;
			tempNodeArray[20] = new Node(9,4,false,false,false,false,true,false,true);
			
			tempNodeArray[21] = new Node(1,5,false,false,false,false,true,false,true);
			tempNodeArray[22] = new Node(3,5,false,false,false,true,false,false,true);
			tempNodeArray[23] = new Node(4,5,false,false,false,true,false,true,false);
			tempNodeArray[24] = new Node(5,5,false,false,false,false,false,false,false);
			tempNodeArray[25] = new Node(6,5,false,false,false,true,false,true,false);
			tempNodeArray[26] = new Node(7,5,false,false,false,true,false,false,false);
			tempNodeArray[27] = new Node(8,5,false,false,false,true,false,true,false);
			tempNodeArray[28] = new Node(9,5,false,false,false,true,true,false,false);
			
			tempNodeArray[29] = new Node(1,6,false,false,false,false,true,false,true);
			tempNodeArray[30] = new Node(5,6,false,false,false,false,true,false,true);
			
			tempNodeArray[31] = new Node(1,7,false,false,false,false,false,false,true);
			tempNodeArray[32] = new Node(2,7,false,false,false,true,false,true,false);
			tempNodeArray[33] = new Node(3,7,false,false,false,true,false,true,false);
			tempNodeArray[34] = new Node(4,7,false,false,false,false,false,true,false);
			tempNodeArray[35] = new Node(5,7,false,false,false,true,false,false,false);
			tempNodeArray[36] = new Node(6,7,false,false,false,true,false,true,false);
			tempNodeArray[37] = new Node(7,7,false,false,false,true,false,true,false);
			tempNodeArray[38] = new Node(8,7,false,false,false,false,true,true,false);
			
			tempNodeArray[39] = new Node(1,8,false,false,false,false,true,false,true);
			tempNodeArray[40] = new Node(4,8,false,false,false,false,true,false,true);
			tempNodeArray[41] = new Node(8,8,false,false,false,false,true,false,true);
			
			tempNodeArray[42] = new Node(1,9,true,false,false,true,true,false,true);
			tempNodeArray[43] = new Node(4,9,false,false,false,true,true,false,true);
			tempNodeArray[44] = new Node(8,9,false,false,false,true,false,false,true);
			tempNodeArray[45] = new Node(9,9,false,false,false,true,true,true,false);
			
			tempNodeArray[3].setIsLongestTailNode(true);
			tempNodeArray[4].setIsLongestTailNode(true);
			tempNodeArray[5].setIsLongestTailNode(true);
			
			tempNodeArray[0].setIsCoreNode(true);
			tempNodeArray[1].setIsCoreNode(true);
			tempNodeArray[2].setIsCoreNode(true);
			for(int i = 6; i < 46; i++)
				tempNodeArray[i].setIsCoreNode(true);
			
			tempNodeArray[15].setIsDeadend(true);
			tempNodeArray[43].setIsDeadend(true);
			tempNodeArray[45].setIsDeadend(true);
			
			tempNodeArray[10].setIsIntersection(true);
			tempNodeArray[24].setIsIntersection(true);
			tempNodeArray[26].setIsIntersection(true);
			tempNodeArray[31].setIsIntersection(true);
			tempNodeArray[34].setIsIntersection(true);
			tempNodeArray[35].setIsIntersection(true);
			tempNodeArray[42].setIsIntersection(true);
			
			tempNodeArray[3].setIsOptimalPath(true);
			tempNodeArray[4].setIsOptimalPath(true);
			tempNodeArray[5].setIsOptimalPath(true);
			tempNodeArray[8].setIsOptimalPath(true);
			tempNodeArray[9].setIsOptimalPath(true);
			tempNodeArray[10].setIsOptimalPath(true);
			tempNodeArray[13].setIsOptimalPath(true);
			tempNodeArray[14].setIsOptimalPath(true);
			tempNodeArray[18].setIsOptimalPath(true);
			tempNodeArray[19].setIsOptimalPath(true);
			tempNodeArray[24].setIsOptimalPath(true);
			tempNodeArray[25].setIsOptimalPath(true);
			tempNodeArray[26].setIsOptimalPath(true);
			tempNodeArray[30].setIsOptimalPath(true);
			tempNodeArray[31].setIsOptimalPath(true);
			tempNodeArray[32].setIsOptimalPath(true);
			tempNodeArray[33].setIsOptimalPath(true);
			tempNodeArray[34].setIsOptimalPath(true);
			tempNodeArray[35].setIsOptimalPath(true);
			tempNodeArray[39].setIsOptimalPath(true);
			tempNodeArray[42].setIsOptimalPath(true);
		maze.setNodeArray(tempNodeArray);
		maze.setCoreActiveNodeCount(43);
		maze.setCoreOptimalPathNodeCount(14);
		maze.setintersectionCount(7);
		maze.setCoreIntersectionCount(7);
		maze.setDeadendCount(3);
		maze.setCoreDeadendCount(3);
		maze.setLoopCount(2);
		maze.setCoreLoopCount(2);
		maze.setLongestTailCount(3);
		maze.setBranchFactor((double)20/28);
		maze.setComplexity(75);
	}

	/* Create the application. */
	public MazeGeneratorUI(Maze maze) {
		initialize(maze, nodePanelList);
	}

	/* Initialize the contents of the frame. */
	private void initialize(Maze maze, ArrayList<JPanel> nodePanelList) {
		
		frmMansiMazegenerator = new JFrame();
		frmMansiMazegenerator.setTitle("MANS-i MazeGenerator");
		frmMansiMazegenerator.getContentPane().setBackground(Color.decode("#E5E4D7"));
		frmMansiMazegenerator.setBounds(screen.width/2-FRAMEWIDTH/2, screen.height/2-FRAMEHEIGHT/2,FRAMEWIDTH,FRAMEHEIGHT);
		frmMansiMazegenerator.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmMansiMazegenerator.setResizable(false);
		frmMansiMazegenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMansiMazegenerator.getContentPane().setLayout(null);
		
		JPanel pnlleftTopPanel = new JPanel();
		pnlleftTopPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.GRAY));
		pnlleftTopPanel.setBackground(Color.decode("#92CD00"));
		pnlleftTopPanel.setBounds(10, 11, 217, 236);
		frmMansiMazegenerator.getContentPane().add(pnlleftTopPanel);
		
		JButton btnGenerateMaze = new JButton("Generate Maze");
		btnGenerateMaze.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGenerateMaze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		btnGenerateMaze.setBounds(10, 202, 197, 23);
		pnlleftTopPanel.setLayout(null);
		pnlleftTopPanel.add(btnGenerateMaze);
		
		JLabel lblGridSize = new JLabel("Grid Size:");
		lblGridSize.setBounds(10, 56, 235, 15);
		pnlleftTopPanel.add(lblGridSize);
		lblGridSize.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		MaskFormatter formatter = null;
		  try {
		    formatter = new MaskFormatter("AAAAAAAAAAAA");
		  } catch (ParseException e) {
		    e.printStackTrace();
		  }
		inpMazeTitle = new JFormattedTextField(formatter);
		inpMazeTitle.setBackground(Color.WHITE);
		inpMazeTitle.setBounds(10, 26, 197, 20);
		pnlleftTopPanel.add(inpMazeTitle);
		inpMazeTitle.setColumns(20);
		
		JLabel lblMazeTitle = new JLabel("Maze Title:");
		lblMazeTitle.setBounds(10, 11, 235, 15);
		pnlleftTopPanel.add(lblMazeTitle);
		lblMazeTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JSlider sldGridSize = new JSlider();
		sldGridSize.addChangeListener(new ChangeListener() {
			JSlider sldActiveNodeCount = null;
			public void stateChanged(ChangeEvent arg0) {
				int newValue = sldGridSize.getValue();
				if(sldActiveNodeCount != null)
					pnlleftTopPanel.remove(sldActiveNodeCount);
				sldActiveNodeCount = new JSlider();
				sldActiveNodeCount.setBounds(10, 143, 197, 48);
				pnlleftTopPanel.add(sldActiveNodeCount);
				sldActiveNodeCount.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				sldActiveNodeCount.setFont(new Font("Tahoma", Font.PLAIN, 10));
				sldActiveNodeCount.setMinorTickSpacing(1);
				sldActiveNodeCount.setPaintLabels(true);
				sldActiveNodeCount.setSnapToTicks(true);
				sldActiveNodeCount.setPaintTicks(true);
				sldActiveNodeCount.setMaximum(newValue*newValue);
				sldActiveNodeCount.setMajorTickSpacing((int)(((newValue*newValue)/5 < 5)?1:(newValue*newValue)/5));
			}
		});
		sldGridSize.setBounds(10, 70, 197, 48);
		pnlleftTopPanel.add(sldGridSize);
		sldGridSize.setSnapToTicks(true);
		sldGridSize.setPaintTicks(true);
		sldGridSize.setPaintLabels(true);
		sldGridSize.setMinorTickSpacing(1);
		sldGridSize.setMaximum(50);
		sldGridSize.setMajorTickSpacing(10);
		sldGridSize.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sldGridSize.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblActiveNodeCount = new JLabel("Active Node Count:");
		lblActiveNodeCount.setBounds(10, 129, 235, 15);
		pnlleftTopPanel.add(lblActiveNodeCount);
		lblActiveNodeCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel pnlLeftBottomPanel = new JPanel();
		pnlLeftBottomPanel.setBackground(Color.decode("#92CD00"));
		pnlLeftBottomPanel.setBounds(10, 258, 217, 302);
		frmMansiMazegenerator.getContentPane().add(pnlLeftBottomPanel);
		pnlLeftBottomPanel.setLayout(null);
		
		JScrollPane fileListScrollPane = new JScrollPane();
		fileListScrollPane.setBounds(10, 11, 197, 88);
		pnlLeftBottomPanel.add(fileListScrollPane);
		
		JList list = new JList();
		DefaultListModel model1 = new DefaultListModel();
	    File o = new File(".");
	    
	    File[] yourFileList = o.listFiles(new FilenameFilter() {
	    	@Override
	    	public boolean accept(File dir, String name) {
	    		return name.endsWith(".txt");
	    	}
	    });
	    for(File f : yourFileList) {
	        model1.addElement(f.getName());
	    }
	    list.setModel(model1);
		fileListScrollPane.setViewportView(list);
		list.setFont(new Font("Tahoma", Font.PLAIN, 12));
		list.setVisibleRowCount(6);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel pnlDetailsPanel = new JPanel();
		pnlDetailsPanel.setBounds(10, 110, 197, 147);
		pnlLeftBottomPanel.add(pnlDetailsPanel);
		pnlDetailsPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		SpringLayout sl_pnlDetailsPanel = new SpringLayout();
		pnlDetailsPanel.setLayout(sl_pnlDetailsPanel);
		
		inpMazeTitle2 = new JTextField();
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, inpMazeTitle2, 80, SpringLayout.WEST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpMazeTitle2, -10, SpringLayout.EAST, pnlDetailsPanel);
		inpMazeTitle2.setHorizontalAlignment(SwingConstants.TRAILING);
		inpMazeTitle2.setColumns(20);
		inpMazeTitle2.setText(maze.getTitle());
		pnlDetailsPanel.add(inpMazeTitle2);
		
		inpEndNodeX = new JTextField();
		inpEndNodeX.setHorizontalAlignment(SwingConstants.TRAILING);
		inpEndNodeX.setColumns(3);
		inpEndNodeX.setText(Integer.toString(maze.getEndNode().getXCoord()));
		pnlDetailsPanel.add(inpEndNodeX);
		
		inpEndNodeY = new JTextField();
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, inpEndNodeX, 0, SpringLayout.NORTH, inpEndNodeY);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpEndNodeX, -8, SpringLayout.WEST, inpEndNodeY);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpEndNodeY, 0, SpringLayout.EAST, inpMazeTitle2);
		inpEndNodeY.setHorizontalAlignment(SwingConstants.TRAILING);
		inpEndNodeY.setColumns(3);
		inpEndNodeY.setText(Integer.toString(maze.getEndNode().getYCoord()));
		pnlDetailsPanel.add(inpEndNodeY);
		
		inpStartNodeX = new JTextField();
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, inpStartNodeX, 8, SpringLayout.SOUTH, inpMazeTitle2);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, inpStartNodeX, -6, SpringLayout.NORTH, inpEndNodeX);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpStartNodeX, 0, SpringLayout.EAST, inpEndNodeX);
		inpStartNodeX.setHorizontalAlignment(SwingConstants.TRAILING);
		inpStartNodeX.setColumns(3);
		inpStartNodeX.setText(Integer.toString(maze.getStartNode().getXCoord()));
		pnlDetailsPanel.add(inpStartNodeX);
		
		inpStartNodeY = new JTextField();
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, inpStartNodeY, 8, SpringLayout.SOUTH, inpMazeTitle2);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, inpStartNodeY, -6, SpringLayout.NORTH, inpEndNodeY);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpStartNodeY, 0, SpringLayout.EAST, inpMazeTitle2);
		inpStartNodeY.setHorizontalAlignment(SwingConstants.TRAILING);
		inpStartNodeY.setColumns(3);
		inpStartNodeY.setText(Integer.toString(maze.getStartNode().getYCoord()));
		pnlDetailsPanel.add(inpStartNodeY);
		
		txtBranchFactor = new JTextField();
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, inpEndNodeY, -6, SpringLayout.NORTH, txtBranchFactor);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, txtBranchFactor, -10, SpringLayout.EAST, pnlDetailsPanel);
		txtBranchFactor.setEditable(false);
		txtBranchFactor.setHorizontalAlignment(SwingConstants.TRAILING);
		txtBranchFactor.setColumns(20);
		txtBranchFactor.setText(Double.toString(
				(double)Math.round(maze.getBranchFactor()*100) / 100.0
						));
		pnlDetailsPanel.add(txtBranchFactor);
		
		txtComplexity = new JTextField();
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, txtComplexity, 82, SpringLayout.WEST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, txtComplexity, -10, SpringLayout.EAST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, txtBranchFactor, -6, SpringLayout.NORTH, txtComplexity);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, txtComplexity, -10, SpringLayout.SOUTH, pnlDetailsPanel);
		txtComplexity.setEditable(false);
		txtComplexity.setHorizontalAlignment(SwingConstants.TRAILING);
		txtComplexity.setColumns(20);
		txtComplexity.setText(Double.toString(maze.getComplexity()));
		pnlDetailsPanel.add(txtComplexity);
		
		JLabel lblStartNode = new JLabel("Start Node");
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, lblStartNode, 3, SpringLayout.NORTH, inpStartNodeX);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, lblStartNode, 96, SpringLayout.WEST, pnlDetailsPanel);
		lblStartNode.setHorizontalAlignment(SwingConstants.LEFT);
		pnlDetailsPanel.add(lblStartNode);
		
		JLabel lblMazeTitle2 = new JLabel("Maze Title");
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, lblStartNode, 0, SpringLayout.WEST, lblMazeTitle2);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, inpMazeTitle2, -3, SpringLayout.NORTH, lblMazeTitle2);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, lblMazeTitle2, -119, SpringLayout.SOUTH, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, lblMazeTitle2, 10, SpringLayout.NORTH, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, lblMazeTitle2, 10, SpringLayout.WEST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, lblMazeTitle2, -97, SpringLayout.EAST, pnlDetailsPanel);
		lblMazeTitle2.setHorizontalAlignment(SwingConstants.LEFT);
		pnlDetailsPanel.add(lblMazeTitle2);
		
		JLabel lblEndNode = new JLabel("End Node");
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, lblEndNode, 10, SpringLayout.WEST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, lblEndNode, -19, SpringLayout.WEST, inpEndNodeX);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, lblEndNode, 3, SpringLayout.NORTH, inpEndNodeX);
		pnlDetailsPanel.add(lblEndNode);
		
		JLabel lblBranchFactor = new JLabel("Branch Factor");
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, lblBranchFactor, 10, SpringLayout.WEST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, lblBranchFactor, -116, SpringLayout.EAST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, txtBranchFactor, 5, SpringLayout.EAST, lblBranchFactor);
		pnlDetailsPanel.add(lblBranchFactor);
		
		JButton btnUpdateMaze = new JButton("Update Maze");
		btnUpdateMaze.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateMaze.setBounds(10, 268, 197, 23);
		pnlLeftBottomPanel.add(btnUpdateMaze);
		
		JPanel pnlRightPanel = new JPanel();
		pnlRightPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(128, 128, 128), Color.LIGHT_GRAY, new Color(128, 128, 128), Color.LIGHT_GRAY));
		pnlRightPanel.setBackground(Color.decode("#4f4f4f"));
		pnlRightPanel.setBounds(235, 11, 549, 549);
		frmMansiMazegenerator.getContentPane().add(pnlRightPanel);
		pnlRightPanel.setLayout(null);
		
		JLabel lblComplexity = new JLabel("Complexity");
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, lblBranchFactor, -9, SpringLayout.NORTH, lblComplexity);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, lblComplexity, 0, SpringLayout.NORTH, txtComplexity);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, lblComplexity, 9, SpringLayout.WEST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, lblComplexity, -10, SpringLayout.SOUTH, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, lblComplexity, -6, SpringLayout.WEST, txtComplexity);
		pnlDetailsPanel.add(lblComplexity);
		
		// Maze panels
		int gridWidth = pnlRightPanel.getWidth();
		int gridBottomCornerY = 549;
		int gridSquareSize = gridWidth / maze.getGridSize();
		Color nC, eC, sC, wC;
		boolean[][] exists = new boolean[maze.getGridSize()+1][maze.getGridSize()+1];
		Node[][] nodes = new Node[maze.getGridSize()+1][maze.getGridSize()+1];
		for(Node node : maze.getNodeArray())
		{
			exists[node.getXCoord()][node.getYCoord()] = true;
			nodes[node.getXCoord()][node.getYCoord()] = node;
		}
		for(int y = 1; y < maze.getGridSize() + 1; y++)
		{
			for(int x = 1; x < maze.getGridSize() + 1; x++)
			{
				JPanel p = new JPanel();
				if(exists[x][y])
				{
					if(nodes[x][y].getNorthWall()){nC = Color.BLACK;}else{nC = Color.decode("#dbdbdb");}
					if(nodes[x][y].getEastWall()){eC = Color.BLACK;}else{eC = Color.decode("#dbdbdb");}
					if(nodes[x][y].getSouthWall()){sC = Color.BLACK;}else{sC = Color.decode("#dbdbdb");}
					if(nodes[x][y].getWestWall()){wC = Color.BLACK;}else{wC = Color.decode("#dbdbdb");}
					p.setBorder(new CompoundBorder(
									new CompoundBorder(
										BorderFactory.createMatteBorder(1, 0, 0, 0, nC),
										BorderFactory.createMatteBorder(0, 0, 0, 1, eC)
									),
									new CompoundBorder(
										BorderFactory.createMatteBorder(0, 0, 1, 0, sC),
										BorderFactory.createMatteBorder(0, 1, 0, 0, wC)
									)
								));
					if(nodes[x][y].getIsStartNode())
						p.setBackground(Color.decode("#2a8c3d"));
					else if(nodes[x][y].getIsEndNode())
						p.setBackground(Color.decode("#8c2a2a"));
					else if(nodes[x][y].getIsEndIntersection())
						p.setBackground(Color.decode("#8c852a"));
					else if(nodes[x][y].getIsIntersection() && !nodes[x][y].getIsEndIntersection())
						p.setBackground(Color.decode("#2a888c"));
					else if(nodes[x][y].getIsDeadend())
						p.setBackground(Color.decode("#8c562a"));
					else
						p.setBackground(Color.WHITE);
					p.setBounds(gridSquareSize*(x-1), gridBottomCornerY-gridSquareSize*(y), gridSquareSize, gridSquareSize);
					
					nodePanelList.add(p);
				}
				else
				{
					p.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#dbdbdb")));
					p.setBackground(Color.decode("#cccccc"));
					p.setBounds(gridSquareSize*(x-1), gridBottomCornerY-gridSquareSize*(y), gridSquareSize, gridSquareSize);
					nodePanelList.add(p);
				}
			}
		}
		for(JPanel j : nodePanelList)
		{
			pnlRightPanel.add(j);
		}
	}
}
