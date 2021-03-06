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
import javax.swing.text.NumberFormatter;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
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
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MazeGeneratorUI {
	
	private JFrame frmMansiMazegenerator;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	/* Settings */
	private final int    FRAMEWIDTH         = 800;
	private final int    FRAMEHEIGHT        = 600;
	
	private JTextField inpMazeTitle;
	private JFormattedTextField inpMazeTitle2;
	private JFormattedTextField inpEndNodeX;
	private JFormattedTextField inpEndNodeY;
	private JFormattedTextField inpStartNodeX;
	private JFormattedTextField inpStartNodeY;
	private JTextField txtBranchFactor;
	private JTextField txtComplexity;
	
	ArrayList<JPanel> nodePanelList = new ArrayList<JPanel>();
	
	/*Launch the application. */
	public static void main(String[] args) {
		Maze maze = new Maze();

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
		inpMazeTitle = new JTextField();
		inpMazeTitle.setText("");
		inpMazeTitle.setBackground(Color.WHITE);
		inpMazeTitle.setBounds(10, 26, 197, 20);
		pnlleftTopPanel.add(inpMazeTitle);
		
		JLabel lblMazeTitle = new JLabel("Maze Title:");
		lblMazeTitle.setBounds(10, 11, 235, 15);
		pnlleftTopPanel.add(lblMazeTitle);
		lblMazeTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JSlider sldActiveNodeCount = new JSlider();
		BoundedRangeModel model = sldActiveNodeCount.getModel();
		pnlleftTopPanel.add(sldActiveNodeCount);
		sldActiveNodeCount.setBounds(10, 143, 197, 48);
		sldActiveNodeCount.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sldActiveNodeCount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sldActiveNodeCount.setMinorTickSpacing(1);
		sldActiveNodeCount.setPaintLabels(true);
		sldActiveNodeCount.setSnapToTicks(true);
		sldActiveNodeCount.setPaintTicks(true);
		
		JSlider sldGridSize = new JSlider();
		sldGridSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int newValue = sldGridSize.getValue();
				model.setMaximum(newValue*newValue);
				sldActiveNodeCount.setLabelTable(null);
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
		
		JPanel pnlLeftBottomPanel = new JPanel();
		pnlLeftBottomPanel.setBackground(Color.decode("#92CD00"));
		pnlLeftBottomPanel.setBounds(10, 258, 217, 302);
		frmMansiMazegenerator.getContentPane().add(pnlLeftBottomPanel);
		pnlLeftBottomPanel.setLayout(null);
		
		JPanel pnlRightPanel = new JPanel();
		pnlRightPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(128, 128, 128), Color.LIGHT_GRAY, new Color(128, 128, 128), Color.LIGHT_GRAY));
		pnlRightPanel.setBackground(Color.decode("#4f4f4f"));
		pnlRightPanel.setBounds(235, 11, 549, 549);
		frmMansiMazegenerator.getContentPane().add(pnlRightPanel);
		pnlRightPanel.setLayout(null);
		
		JScrollPane fileListScrollPane = new JScrollPane();
		fileListScrollPane.setBounds(10, 11, 197, 88);
		pnlLeftBottomPanel.add(fileListScrollPane);
		
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
		
		JList list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!list.isSelectionEmpty())
				{
					maze.fromFile(list.getSelectedValue().toString());
					
					inpMazeTitle2.setText(maze.getTitle());
					if(maze.getStartNode() == null)
						inpStartNodeX.setText("");
					else
						inpStartNodeX.setText(Integer.toString(maze.getStartNode().getXCoord()));
					if(maze.getStartNode() == null)
						inpStartNodeY.setText("");
					else
						inpStartNodeY.setText(Integer.toString(maze.getStartNode().getYCoord()));
					if(maze.getEndNode() == null)
						inpEndNodeX.setText("");
					else
						inpEndNodeX.setText(Integer.toString(maze.getEndNode().getXCoord()));
					if(maze.getEndNode() == null)
						inpEndNodeY.setText("");
					else
						inpEndNodeY.setText(Integer.toString(maze.getEndNode().getYCoord()));
					if(maze.getBranchFactor() == 0)
						txtBranchFactor.setText("0.0");
					else
						txtBranchFactor.setText(Double.toString(
							(double)Math.round(maze.getBranchFactor()*100) / 100.0
									));
					if(maze.getComplexity() == 0)
						txtComplexity.setText("0");
					else
						txtComplexity.setText(Double.toString(maze.getComplexity()));
					
					for(JPanel jp : nodePanelList)
						pnlRightPanel.remove(jp);
					nodePanelList.clear();
					
					// Maze panels
					int gridWidth = pnlRightPanel.getWidth();
					int gridBottomCornerY = 549;
					int gridSquareSize = gridWidth / maze.getGridSize();
					Color nC = null, eC = null, sC = null, wC = null;
					boolean[][] exists = new boolean[maze.getGridSize()+1][maze.getGridSize()+1];
					Node[][] nodes = new Node[maze.getGridSize()+1][maze.getGridSize()+1];
					if(maze.getNodeArray() != null && maze.getNodeArray().length != 0)
					{
						for(Node node : maze.getNodeArray())
						{
							exists[node.getXCoord()][node.getYCoord()] = true;
							nodes[node.getXCoord()][node.getYCoord()] = node;
						}
					}
					for(int y = 1; y < maze.getGridSize() + 1; y++)
					{
						for(int x = 1; x < maze.getGridSize() + 1; x++)
						{
							JPanel p = new JPanel();
							EmptyBorder emptyBorder = new EmptyBorder(0, 0, 0, 0);
							Border insideBorder;
							if(exists[x][y])
							{
								insideBorder = BorderFactory.createMatteBorder
								(
									(nodes[x][y].getNorthWall()?1:0), 
									(nodes[x][y].getWestWall()?1:0), 
									(nodes[x][y].getSouthWall()?1:0), 
									(nodes[x][y].getEastWall()?1:0), 
									Color.BLACK
								);
								p.setBorder(new CompoundBorder(emptyBorder,insideBorder));		
								p.setBackground(Color.WHITE);
								if(nodes[x][y].getIsCoreNode())
									p.setBackground(Color.GRAY);
								if(nodes[x][y].getIsLongestTailNode())
									p.setBackground(Color.RED);
								if(nodes[x][y].getIsOptimalPath())
									p.setBackground(Color.decode("#90A8D4"));
								//if(nodes[x][y].getIsIntersection() && !nodes[x][y].getIsEndIntersection())
								//	p.setBackground(Color.decode("#D4BC90"));
								//if(nodes[x][y].getIsDeadend())
								//	p.setBackground(Color.decode("#B290D4"));
								if(nodes[x][y].getIsEndIntersection())
									p.setBackground(Color.decode("#D4D490"));
								if(nodes[x][y].getIsStartNode())
									p.setBackground(Color.decode("#B2D490"));
								if(nodes[x][y].getIsEndNode())
									p.setBackground(Color.decode("#D49090"));
								p.setBounds(gridSquareSize*(x-1)+(pnlRightPanel.getWidth() % maze.getGridSize())/2, gridBottomCornerY-gridSquareSize*(y)-(pnlRightPanel.getWidth() % maze.getGridSize())/2, gridSquareSize, gridSquareSize);
								
								nodePanelList.add(p);
							}
							else
							{
								p.setBorder(emptyBorder);
								p.setBackground(Color.decode("#cccccc"));
								p.setBounds(gridSquareSize*(x-1)+(pnlRightPanel.getWidth() % maze.getGridSize())/2, gridBottomCornerY-gridSquareSize*(y)-(pnlRightPanel.getWidth() % maze.getGridSize())/2, gridSquareSize, gridSquareSize);
								nodePanelList.add(p);
							}
						}
					}
					for(JPanel j : nodePanelList)
					{
						pnlRightPanel.add(j);
					}
					pnlRightPanel.repaint();
				}
			}
		});
		list.setModel(model1);
		fileListScrollPane.setViewportView(list);
		list.setFont(new Font("Tahoma", Font.PLAIN, 12));
		list.setVisibleRowCount(6);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnGenerateMaze = new JButton("Generate Maze");
		btnGenerateMaze.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGenerateMaze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String filename = "";
				Maze tempMaze = new Maze(inpMazeTitle.getText(),sldGridSize.getValue(),sldActiveNodeCount.getValue());
				tempMaze.generateMaze();
				filename = tempMaze.toFile();
				model1.addElement(filename);
				list.setSelectedValue(filename, true);
			}
		});
		btnGenerateMaze.setBounds(10, 202, 197, 23);
		pnlleftTopPanel.setLayout(null);
		pnlleftTopPanel.add(btnGenerateMaze);
		
		JLabel lblActiveNodeCount = new JLabel("Active Node Count:");
		lblActiveNodeCount.setBounds(10, 129, 235, 15);
		pnlleftTopPanel.add(lblActiveNodeCount);
		lblActiveNodeCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel pnlDetailsPanel = new JPanel();
		pnlDetailsPanel.setBounds(10, 110, 197, 147);
		pnlLeftBottomPanel.add(pnlDetailsPanel);
		pnlDetailsPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		SpringLayout sl_pnlDetailsPanel = new SpringLayout();
		pnlDetailsPanel.setLayout(sl_pnlDetailsPanel);
		
		inpMazeTitle2 = new JFormattedTextField(formatter);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, inpMazeTitle2, 80, SpringLayout.WEST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpMazeTitle2, -10, SpringLayout.EAST, pnlDetailsPanel);
		inpMazeTitle2.setHorizontalAlignment(SwingConstants.RIGHT);
		inpMazeTitle2.setCaretPosition(0);
		pnlDetailsPanel.add(inpMazeTitle2);
		
		NumberFormatter fmtDigit = new NumberFormatter();
		fmtDigit.setMinimum(1);
		fmtDigit.setMaximum(maze.getGridSize());
		
		inpEndNodeX = new JFormattedTextField(fmtDigit);
		inpEndNodeX.setHorizontalAlignment(SwingConstants.RIGHT);
		inpEndNodeX.setColumns(3);
		pnlDetailsPanel.add(inpEndNodeX);
		
		inpEndNodeY = new JFormattedTextField(fmtDigit);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, inpEndNodeX, 0, SpringLayout.NORTH, inpEndNodeY);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpEndNodeX, -8, SpringLayout.WEST, inpEndNodeY);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpEndNodeY, 0, SpringLayout.EAST, inpMazeTitle2);
		inpEndNodeY.setHorizontalAlignment(SwingConstants.RIGHT);
		inpEndNodeY.setColumns(3);
		pnlDetailsPanel.add(inpEndNodeY);
		
		inpStartNodeX = new JFormattedTextField(fmtDigit);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, inpStartNodeX, 8, SpringLayout.SOUTH, inpMazeTitle2);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, inpStartNodeX, -6, SpringLayout.NORTH, inpEndNodeX);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpStartNodeX, 0, SpringLayout.EAST, inpEndNodeX);
		inpStartNodeX.setHorizontalAlignment(SwingConstants.RIGHT);
		inpStartNodeX.setColumns(3);
		pnlDetailsPanel.add(inpStartNodeX);
		
		inpStartNodeY = new JFormattedTextField(fmtDigit);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, inpStartNodeY, 8, SpringLayout.SOUTH, inpMazeTitle2);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, inpStartNodeY, -6, SpringLayout.NORTH, inpEndNodeY);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, inpStartNodeY, 0, SpringLayout.EAST, inpMazeTitle2);
		inpStartNodeY.setHorizontalAlignment(SwingConstants.RIGHT);
		inpStartNodeY.setColumns(3);
		pnlDetailsPanel.add(inpStartNodeY);
		
		JButton btnUpdateMaze = new JButton("Update Maze");
		btnUpdateMaze.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateMaze.setBounds(10, 268, 197, 23);
		btnUpdateMaze.setEnabled(false);
		pnlLeftBottomPanel.add(btnUpdateMaze);
		
		KeyAdapter adapter = new KeyAdapter()
		{  
			public void keyReleased(java.awt.event.KeyEvent evt) 
			{  
				super.keyReleased(evt);
				if (inpMazeTitle2.getText().trim().length() == 0 ||
					inpStartNodeX.getText().trim().length() == 0 ||
			        inpStartNodeY.getText().trim().length() == 0 ||
			        inpEndNodeX.getText().trim().length() == 0 ||
			        inpEndNodeY.getText().trim().length() == 0) 
				{btnUpdateMaze.setEnabled(false);}
				else 
			    {btnUpdateMaze.setEnabled(true);}  
			}
		};
		inpMazeTitle2.addKeyListener(adapter);
		inpStartNodeX.addKeyListener(adapter);
		inpStartNodeY.addKeyListener(adapter);
		inpEndNodeX.addKeyListener(adapter);
		inpEndNodeY.addKeyListener(adapter);
		
		txtBranchFactor = new JTextField();
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, inpEndNodeY, -6, SpringLayout.NORTH, txtBranchFactor);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, txtBranchFactor, -10, SpringLayout.EAST, pnlDetailsPanel);
		txtBranchFactor.setEditable(false);
		txtBranchFactor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBranchFactor.setColumns(20);
		pnlDetailsPanel.add(txtBranchFactor);
		
		txtComplexity = new JTextField();
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, txtComplexity, 82, SpringLayout.WEST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, txtComplexity, -10, SpringLayout.EAST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, txtBranchFactor, -6, SpringLayout.NORTH, txtComplexity);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, txtComplexity, -10, SpringLayout.SOUTH, pnlDetailsPanel);
		txtComplexity.setEditable(false);
		txtComplexity.setHorizontalAlignment(SwingConstants.RIGHT);
		txtComplexity.setColumns(20);
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
		
		JLabel lblComplexity = new JLabel("Complexity");
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, lblBranchFactor, -9, SpringLayout.NORTH, lblComplexity);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.NORTH, lblComplexity, 0, SpringLayout.NORTH, txtComplexity);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.WEST, lblComplexity, 9, SpringLayout.WEST, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.SOUTH, lblComplexity, -10, SpringLayout.SOUTH, pnlDetailsPanel);
		sl_pnlDetailsPanel.putConstraint(SpringLayout.EAST, lblComplexity, -6, SpringLayout.WEST, txtComplexity);
		pnlDetailsPanel.add(lblComplexity);
	}
}
