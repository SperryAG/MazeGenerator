package MazeGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import RobotAI.*;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SimulatorUI {
	/* Variables */
	// Frame
	private JFrame frmMansiMazeSimulator;
	// Screen Size
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	// Window Size
	private final int    FRAMEWIDTH         = 1024;
	private final int    FRAMEHEIGHT        = 768;
	// JPanel - Main
	JPanel pnlLogo;
	JPanel pnlControls;
	JPanel pnlStatistics;
	JPanel pnlGrid;
	// JPanel - pnlLogo
	JLabel lblProgramTitle;
	JLabel lblProgramSubTitle;
	// JPanel - pnlControls
	JLabel lblRobotCountTitle;
	JLabel lblCurrentRobotCount;
	JSlider sdrRobotCount;
	JLabel lblSpeedTitle;
	JLabel lblCurrentSpeed;
	JSlider sdrSpeed;
	JButton btnPlayPause;
	JButton btnFinish;
	JPanel pnlTimer;
	JLabel lblTimerValue;
	// JPanel - pnlStatistics
	JPanel pnlStatisticsBackround;
	JScrollPane fileListScrollPane;
	JList lstMazeList;
	DefaultListModel lstMazeListModel;
	JButton btnCompleteAll;
	JPanel pnlMazeDataBackground;
	JLabel lblTitle;
	JLabel lblTitleValue;
	JLabel lblCreated;
	JLabel lblCreatedValue;
	JLabel lblGridSize;
	JLabel lblGridSizeValue;
	JLabel lblActiveNodeCount;
	JLabel lblActiveNodeCountValue;
	JLabel lblBranchingFactor;
	JLabel lblBranchingFactorValue;
	JLabel lblComplexity;
	JLabel lblComplexityValue;
	JLabel lblIntersectionCount;
	JLabel lblIntersectionCountValue;
	JLabel lblDeadendCount;
	JLabel lblDeadendCountValue;
	JLabel lblLoopCount;
	JLabel lblLoopCountValue;
	private JPanel pnlGraph;
	ChartPanel cpnlGraph;
	JFreeChart jfcGraph;
	DefaultCategoryDataset catDataset;
	// JPanel - pnlGrid
	ArrayList<JPanel> nodePanelList = new ArrayList<JPanel>();
	// Simulation
	public int hours, minutes, seconds, tenMillis;
	boolean simulationStarted, simulationComplete, simulationFinish;
	boolean recordComplete;
	public int pausedSimulationSpeed;
	Maze maze = new Maze();
	Swarm swarm;

	/* Launch the application */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulatorUI window = new SimulatorUI();
					window.frmMansiMazeSimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Create the application. */
	public SimulatorUI() throws InterruptedException {
		// Initialization
		initialize();
	}

	/* Initialize the contents of the frame. */
	private void initialize() {
		// Frame
		frmMansiMazeSimulator = new JFrame();
		frmMansiMazeSimulator.setTitle("MANS-i Maze Simulator");
		frmMansiMazeSimulator.getContentPane().setBackground(Color.decode("#E5E4D7"));
		frmMansiMazeSimulator.setBounds(screen.width/2-FRAMEWIDTH/2, screen.height/2-FRAMEHEIGHT/2,FRAMEWIDTH,FRAMEHEIGHT);
		frmMansiMazeSimulator.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmMansiMazeSimulator.setResizable(false);
		frmMansiMazeSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMansiMazeSimulator.getContentPane().setLayout(null);
		
		// JPanel - Main
		pnlLogo = new JPanel();
		pnlLogo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.GRAY));
		pnlLogo.setBackground(Color.decode("#92CD00"));
		pnlLogo.setBounds(10, 11, 358, 76);
		frmMansiMazeSimulator.getContentPane().add(pnlLogo);
		pnlLogo.setLayout(null);
		
		pnlControls = new JPanel();
		pnlControls.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.GRAY));
		pnlControls.setBackground(Color.decode("#92CD00"));
		pnlControls.setBounds(378, 11, 630, 76);
		frmMansiMazeSimulator.getContentPane().add(pnlControls);
		pnlControls.setLayout(null);
		
		pnlStatistics = new JPanel();
		pnlStatistics.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.GRAY));
		pnlStatistics.setBackground(Color.decode("#92CD00"));
		pnlStatistics.setBounds(10, 98, 358, 630);
		frmMansiMazeSimulator.getContentPane().add(pnlStatistics);
		pnlStatistics.setLayout(null);
		
		pnlGrid = new JPanel();
		pnlGrid.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(128, 128, 128), Color.LIGHT_GRAY, new Color(128, 128, 128), Color.LIGHT_GRAY));
		pnlGrid.setBackground(Color.decode("#4f4f4f"));
		pnlGrid.setBounds(378, 98, 630, 630);
		frmMansiMazeSimulator.getContentPane().add(pnlGrid);
		pnlGrid.setLayout(null);
		
		// JPanel - pnlLogo
		lblProgramTitle = new JLabel("MANS-i");
		lblProgramTitle.setFont(new Font("Modern No. 20", Font.ITALIC, 48));
		lblProgramTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramTitle.setBounds(10, 11, 338, 39);
		pnlLogo.add(lblProgramTitle);
		
		lblProgramSubTitle = new JLabel("Swarm Robotics Maze Simulator");
		lblProgramSubTitle.setVerticalAlignment(SwingConstants.TOP);
		lblProgramSubTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProgramSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramSubTitle.setBounds(10, 51, 338, 25);
		pnlLogo.add(lblProgramSubTitle);
		
		// JPanel - pnlControls
		lblRobotCountTitle = new JLabel("Robot Count:");
		lblRobotCountTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRobotCountTitle.setBounds(10, 11, 125, 17);
		pnlControls.add(lblRobotCountTitle);
		
		lblCurrentRobotCount = new JLabel("0");
		lblCurrentRobotCount.setLabelFor(sdrRobotCount);
		lblCurrentRobotCount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentRobotCount.setBounds(98, 11, 93, 17);
		pnlControls.add(lblCurrentRobotCount);
		
		sdrRobotCount = new JSlider();
		sdrRobotCount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(sdrRobotCount.getValue() == 0)
					lblCurrentRobotCount.setText("Random");
				else
					lblCurrentRobotCount.setText(Integer.toString(sdrRobotCount.getValue()));
			}
		});
		sdrRobotCount.setMaximum(50);
		sdrRobotCount.setPaintTicks(true);
		sdrRobotCount.setMinorTickSpacing(1);
		sdrRobotCount.setMajorTickSpacing(25);
		sdrRobotCount.setSnapToTicks(true);
		sdrRobotCount.setBounds(10, 33, 181, 32);
		sdrRobotCount.setValue(1);
		sdrRobotCount.setEnabled(false);
		pnlControls.add(sdrRobotCount);
		
		lblSpeedTitle = new JLabel("Simulation Speed:");
		lblSpeedTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpeedTitle.setBounds(201, 11, 114, 17);
		pnlControls.add(lblSpeedTitle);
		
		lblCurrentSpeed = new JLabel("0");
		lblCurrentSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentSpeed.setLabelFor(sdrSpeed);
		lblCurrentSpeed.setBounds(250, 11, 121, 17);
		pnlControls.add(lblCurrentSpeed);
		
	    sdrSpeed = new JSlider();
		sdrSpeed.setPaintTicks(true);
		sdrSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(sdrSpeed.getValue() == 0)
				{
					lblCurrentSpeed.setText("Paused");
					if(simulationStarted)
						btnPlayPause.setText("Resume");
				}
				else
				{
					lblCurrentSpeed.setText(Integer.toString(sdrSpeed.getValue()*50)+"%");
					if(simulationStarted)
						btnPlayPause.setText("Pause");
				}
			}
		});
		sdrSpeed.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sdrSpeed.setMajorTickSpacing(1);
		sdrSpeed.setValue(0);
		sdrSpeed.setSnapToTicks(true);
		sdrSpeed.setMaximum(5);
		sdrSpeed.setBounds(201, 33, 170, 32);
		sdrSpeed.setEnabled(false);
		pnlControls.add(sdrSpeed);
		
		btnPlayPause = new JButton("Start");
		btnPlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!simulationStarted)
				{
					if(sdrRobotCount.getValue() == 0)
					{
						Random rand = new Random();
						sdrRobotCount.setValue(rand.nextInt(sdrRobotCount.getMaximum()) + 1);
					}
					btnPlayPause.setText("Pause");
					if(sdrSpeed.getValue() == 0)
					{
						pausedSimulationSpeed = sdrSpeed.getMajorTickSpacing();
						btnPlayPause.setText("Resume");
					}
					sdrRobotCount.setEnabled(false);
					lstMazeList.setEnabled(false);
					hours = 0; minutes = 0; seconds = 0; tenMillis = 0;
					lblTimerValue.setText("00:00:00:0");
					simulationStarted = true;
					runSimulation();
				}
				else
				{
					if(btnPlayPause.getText().equals("Pause"))
					{
						pausedSimulationSpeed = sdrSpeed.getValue();
						sdrSpeed.setValue(0);
						btnPlayPause.setText("Resume");
					}
					else
					{
						sdrSpeed.setValue(pausedSimulationSpeed);
						btnPlayPause.setText("Pause");
					}
				}
			}
		});
		btnPlayPause.setEnabled(false);
		btnPlayPause.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPlayPause.setBounds(381, 11, 100, 23);
		pnlControls.add(btnPlayPause);
		
		btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sdrSpeed.setValue(2);
				simulationFinish = true;
			}
		});
		btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnFinish.setBounds(381, 42, 100, 23);
		btnFinish.setEnabled(false);
		pnlControls.add(btnFinish);
		
		pnlTimer = new JPanel();
		pnlTimer.setBounds(491, 11, 129, 54);
		pnlControls.add(pnlTimer);
		pnlTimer.setLayout(null);
		
		lblTimerValue = new JLabel(
			(hours>9?Integer.toString(hours):"0"+Integer.toString(hours)) + ":" +
			(minutes>9?Integer.toString(minutes):"0"+Integer.toString(minutes)) + ":" +
			(seconds>9?Integer.toString(seconds):"0"+Integer.toString(seconds)) + ":" +
			Integer.toString(tenMillis)
		);
		lblTimerValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTimerValue.setBounds(10, 11, 109, 32);
		lblTimerValue.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTimer.add(lblTimerValue);
		
		// JPanel - pnlStatistics
		pnlStatisticsBackround = new JPanel();
		pnlStatisticsBackround.setBackground(Color.decode("#E5E4D7"));
		pnlStatisticsBackround.setBounds(10, 11, 338, 608);
		pnlStatistics.add(pnlStatisticsBackround);
		pnlStatisticsBackround.setLayout(null);
		
		fileListScrollPane = new JScrollPane();
		fileListScrollPane.setBounds(10, 11, 318, 131);
		pnlStatisticsBackround.add(fileListScrollPane);
		
		lstMazeList = new JList();
		lstMazeList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!lstMazeList.isSelectionEmpty() && !lstMazeList.getValueIsAdjusting())
				{
					maze.fromFile(lstMazeList.getSelectedValue().toString());
					sdrRobotCount.setEnabled(true);
					sdrSpeed.setEnabled(true);
					btnPlayPause.setEnabled(true);
					btnFinish.setEnabled(true);
					lblTitleValue.setText(maze.getTitle());
					lblCreatedValue.setText(maze.getCreated());
					lblGridSizeValue.setText(maze.getGridSize() +"x"+maze.getGridSize());
					lblActiveNodeCountValue.setText(Integer.toString(maze.getActiveNodeCount()));
					lblBranchingFactorValue.setText(Double.toString(maze.getBranchFactor()));
					lblComplexityValue.setText(Integer.toString(maze.getComplexity()));
					lblIntersectionCountValue.setText(Integer.toString(maze.getintersectionCount()));
					lblDeadendCountValue.setText(Integer.toString(maze.getDeadendCount()));
					lblLoopCountValue.setText(Integer.toString(maze.getLoopCount()));
					swarm = new Swarm(sdrRobotCount.getValue(), maze.getNodeArray());
					generateMazeGrid();
					refreshRvTChart();
				}
				else if (lstMazeList.isSelectionEmpty() && !lstMazeList.getValueIsAdjusting())
				{
					sdrRobotCount.setEnabled(false);
					sdrSpeed.setEnabled(false);
					btnPlayPause.setEnabled(false);
					btnFinish.setEnabled(false);
					lblTitleValue.setText("");
					lblCreatedValue.setText("");
					lblGridSizeValue.setText("");
					lblActiveNodeCountValue.setText("");
					lblBranchingFactorValue.setText("");
					lblComplexityValue.setText("");
					lblIntersectionCountValue.setText("");
					lblDeadendCountValue.setText("");
					lblLoopCountValue.setText("");
					pnlGraph.removeAll();
					for(JPanel jp : nodePanelList)
						pnlGrid.remove(jp);
					nodePanelList.clear();
					pnlGrid.repaint();
				}
				hours = 0; minutes = 0; seconds = 0; tenMillis = 0;
				lblTimerValue.setText("00:00:00:0");
			}
		});
		lstMazeListModel = new DefaultListModel();
	    File o = new File(".");
	    
	    File[] yourFileList = o.listFiles(new FilenameFilter() {
	    	@Override
	    	public boolean accept(File dir, String name) {
	    		return name.endsWith(".txt");
	    	}
	    });
	    for(File f : yourFileList) {
	    	lstMazeListModel.addElement(f.getName());
	    }
	    lstMazeList.setModel(lstMazeListModel);
		fileListScrollPane.setViewportView(lstMazeList);
		lstMazeList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lstMazeList.setVisibleRowCount(6);
		lstMazeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnCompleteAll = new JButton("Complete All Maze Statistics");
		btnCompleteAll.setBounds(10, 153, 318, 23);
		pnlStatisticsBackround.add(btnCompleteAll);
		
		pnlMazeDataBackground = new JPanel();
		pnlMazeDataBackground.setBounds(10, 187, 318, 187);
		pnlStatisticsBackround.add(pnlMazeDataBackground);
		pnlMazeDataBackground.setLayout(null);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setBounds(10, 5, 134, 15);
		pnlMazeDataBackground.add(lblTitle);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblTitleValue = new JLabel();
		lblTitleValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTitleValue.setBounds(149, 6, 159, 14);
		pnlMazeDataBackground.add(lblTitleValue);
		
		lblCreated = new JLabel("Created:");
		lblCreated.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCreated.setBounds(10, 25, 134, 15);
		pnlMazeDataBackground.add(lblCreated);
		
		lblCreatedValue = new JLabel();
		lblCreatedValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCreatedValue.setBounds(149, 26, 159, 14);
		pnlMazeDataBackground.add(lblCreatedValue);
		
		lblGridSize = new JLabel("Grid Size:");
		lblGridSize.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGridSize.setBounds(10, 45, 134, 15);
		pnlMazeDataBackground.add(lblGridSize);
		
		lblGridSizeValue = new JLabel();
		lblGridSizeValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGridSizeValue.setBounds(149, 46, 159, 14);
		pnlMazeDataBackground.add(lblGridSizeValue);
		
		lblActiveNodeCount = new JLabel("Active Node Count:");
		lblActiveNodeCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblActiveNodeCount.setBounds(10, 65, 134, 15);
		pnlMazeDataBackground.add(lblActiveNodeCount);
		
		lblActiveNodeCountValue = new JLabel();
		lblActiveNodeCountValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblActiveNodeCountValue.setBounds(149, 66, 159, 14);
		pnlMazeDataBackground.add(lblActiveNodeCountValue);
		
		lblBranchingFactor = new JLabel("Branching Factor:");
		lblBranchingFactor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBranchingFactor.setBounds(10, 85, 134, 15);
		pnlMazeDataBackground.add(lblBranchingFactor);
		
		lblBranchingFactorValue = new JLabel();
		lblBranchingFactorValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBranchingFactorValue.setBounds(149, 86, 159, 14);
		pnlMazeDataBackground.add(lblBranchingFactorValue);
		
		lblComplexity = new JLabel("Complexity:");
		lblComplexity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblComplexity.setBounds(10, 105, 134, 15);
		pnlMazeDataBackground.add(lblComplexity);
		
		lblComplexityValue = new JLabel();
		lblComplexityValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblComplexityValue.setBounds(149, 106, 159, 14);
		pnlMazeDataBackground.add(lblComplexityValue);
		
		lblIntersectionCount = new JLabel("Intersection Count:");
		lblIntersectionCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIntersectionCount.setBounds(10, 125, 134, 15);
		pnlMazeDataBackground.add(lblIntersectionCount);
		
		lblIntersectionCountValue = new JLabel();
		lblIntersectionCountValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIntersectionCountValue.setBounds(149, 126, 159, 14);
		pnlMazeDataBackground.add(lblIntersectionCountValue);
		
		lblDeadendCount = new JLabel("Deadend Count:");
		lblDeadendCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeadendCount.setBounds(10, 145, 134, 15);
		pnlMazeDataBackground.add(lblDeadendCount);
		
		lblDeadendCountValue = new JLabel();
		lblDeadendCountValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDeadendCountValue.setBounds(149, 146, 159, 14);
		pnlMazeDataBackground.add(lblDeadendCountValue);
		
		lblLoopCount = new JLabel("Loop Count:");
		lblLoopCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoopCount.setBounds(10, 165, 134, 15);
		pnlMazeDataBackground.add(lblLoopCount);
		
		lblLoopCountValue = new JLabel();
		lblLoopCountValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLoopCountValue.setBounds(149, 166, 159, 14);
		pnlMazeDataBackground.add(lblLoopCountValue);
		
		// JPanel - pnlGrid
		
	}
	
	private void runSimulation()
	{
		// Dedicated Simulation Thread (Prevents GUI Thread Issues)
		SwingWorker<Void,String> worker = new SwingWorker<Void,String>() 
		{
			@Override
			protected Void doInBackground() throws Exception 
			{
				do
				{	
					simulationComplete = swarm.update();
//					simulationComplete = (boolean) swarmUpdate.get(0);	// index 0 will always be the boolean
//					swarm.getRobotSet() = (ArrayList<Robot>) swarmUpdate.get(1);	// index 1 will always be the set of updated robot movements
					generateMazeGrid();
					// Apply a simulation speed until an instant simulation finish is requested (btnFinish)
					if(!simulationFinish)
					{
						// If the simulation speed is zero, pause the thread
						if(sdrSpeed.getValue() == 0)
						{
							// Pause the thread until the simulation speed is not longer zero
							while(sdrSpeed.getValue() == 0)
							{
								try {TimeUnit.MILLISECONDS.sleep(1);} 
								catch (InterruptedException e) 
								{
									e.printStackTrace();
								}
							}
						}
						// If the simulation speed is not zero, pause the thread according to simulation speed
						else
						{
							try {TimeUnit.MILLISECONDS.sleep((sdrSpeed.getMaximum()-(sdrSpeed.getValue()-1))*25);} 
							catch (InterruptedException e) 
							{
								e.printStackTrace();
							}
						}
					}
					// Update the clock
					updateClock();
					// Publish the new clock from this thread
					publish(
						(hours>9?Integer.toString(hours):"0"+Integer.toString(hours)) + ":" +
						(minutes>9?Integer.toString(minutes):"0"+Integer.toString(minutes)) + ":" +
						(seconds>9?Integer.toString(seconds):"0"+Integer.toString(seconds)) + ":" +
						Integer.toString(tenMillis)
					);
					// Run a simulation step one time per second and return the simulation status.
					if(tenMillis % 10 == 0) // Clock runs in .1ms = 1step/sec
						simulationComplete = !stepSimulation();
				
				}while(!simulationComplete);
				
				// Once the simulation has completed, record the results into the maze file.
				recordSimulation();
				return null;
			}
			
			// When the separate simulation thread completes, reset the simulator (except timer).
			@Override
			protected void done() {
				btnPlayPause.setText("Start");
				btnPlayPause.setEnabled(false);
				btnFinish.setEnabled(false);
				sdrSpeed.setEnabled(false);
				simulationStarted = false;
				simulationComplete = false;
				simulationFinish = false;
				lstMazeList.setEnabled(true);
				super.done();
			}
			
			// When the thread publishes the timer, update the GUI
			@Override
			protected void process(java.util.List<String> chunks) {
				lblTimerValue.setText(chunks.get(chunks.size()-1));
				super.process(chunks);
			}
		};
		
		// Execute the thread simulator thread defines above
		worker.execute();
	}
	
	private void updateClock()
	{
		tenMillis++;
		if(tenMillis == 10)
		{
			tenMillis = 0;
			seconds++;
		}
		if(seconds == 60)
		{
			seconds = 0;
			minutes++;
		}
		if(minutes == 60)
		{
			minutes = 0;
			hours++;
		}
		if(hours == 99)
			hours = 0;
	}
	
	private boolean stepSimulation()
	{
		System.out.println(
			(hours>9?Integer.toString(hours):"0"+Integer.toString(hours)) + ":" +
			(minutes>9?Integer.toString(minutes):"0"+Integer.toString(minutes)) + ":" +
			(seconds>9?Integer.toString(seconds):"0"+Integer.toString(seconds)) + ":" +
			Integer.toString(tenMillis)
		);
		if(minutes == 1)
			simulationComplete = true;
		return !simulationComplete;
	}
	
	private void recordSimulation()
	{
		System.out.println("Simulation Results!!");
	}
	
	private void refreshRvTChart()
	{
		// Clear the previous graph
		if(pnlGraph != null)
			pnlGraph.removeAll();
		
			// Load the selected maze
			maze = new Maze();
			maze.fromFile(lstMazeList.getSelectedValue().toString());
			
			// Create the graph dataset
			catDataset = new DefaultCategoryDataset(); 
			ArrayList<ArrayList<Integer>> resultAverages = new ArrayList<ArrayList<Integer>>();
			int index;
			for(Result r : maze.getResultArray())
			{
				index = r.getRobotCount();
				while(resultAverages.size() <= index)
					resultAverages.add(resultAverages.size(),new ArrayList<Integer>());
				resultAverages.get(index).add(r.getCompletionTime());
			}
			int total;
			for(int i = 0; i < resultAverages.size(); i++)
			{
				if(resultAverages.get(i).size() != 0)
				{
					total = 0;
					for(int n : resultAverages.get(i))
					{
						total += n;
					}
					catDataset.addValue(total/resultAverages.get(i).size(), "x", Integer.toString(i));
				}
			}
			System.out.println(catDataset.toString());
			
			// Create the graph using the dataset
			jfcGraph = ChartFactory.createBarChart(
		            "",   // chart title
		            "Robot Count",                       // domain axis label
		            "Completion Time",                   // range axis label
		            catDataset,                          // data
		            PlotOrientation.VERTICAL,            // the plot orientation
		            false,                               // include legend
		            true,
		            false
		        );
			jfcGraph.setBackgroundPaint(Color.LIGHT_GRAY);
			
			// Further Customization
			final CategoryPlot plot = jfcGraph.getCategoryPlot();
			plot.setNoDataMessage("NO DATA!");
			plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(1.0f);
			
			Font axisLabelFont = new Font("Dialog", Font.PLAIN, 12); 
			plot.getDomainAxis().setLabelFont(axisLabelFont);
			plot.getRangeAxis().setLabelFont(axisLabelFont);
			
			Font axisTickFont = new Font("Dialog", Font.PLAIN, 8);
			plot.getDomainAxis().setTickLabelFont(axisTickFont);
			plot.getRangeAxis().setTickLabelFont(axisTickFont);
			
			plot.getDomainAxis().setTickMarkOutsideLength(2.0f);
			plot.getRangeAxis().setTickMarkOutsideLength(2.0f);
			
			// Display the graph
			pnlGraph = new JPanel();
			pnlGraph.setBounds(10, 385, 318, 212);
			pnlGraph.setBackground(Color.LIGHT_GRAY);
			pnlStatisticsBackround.add(pnlGraph);
			
			cpnlGraph = new ChartPanel(jfcGraph);
			cpnlGraph.setPreferredSize(pnlGraph.getSize());
			pnlGraph.add(cpnlGraph);
	}
	
	private void generateMazeGrid()
	{
		// Clear the grid and panel array
		for(JPanel jp : nodePanelList)
			pnlGrid.remove(jp);
		nodePanelList.clear();
		
		// Maze panels
		int gridWidth = pnlGrid.getWidth();
		int gridBottomCornerY = 630;
		int gridSquareSize = gridWidth / maze.getGridSize();
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
					//if(nodes[x][y].getIsCoreNode())
					//	p.setBackground(Color.GRAY);
					//if(nodes[x][y].getIsOptimalPath())
					//	p.setBackground(Color.decode("#90A8D4"));
					//if(nodes[x][y].getIsIntersection() && !nodes[x][y].getIsEndIntersection())
					//	p.setBackground(Color.decode("#D4BC90"));
					//if(nodes[x][y].getIsDeadend())
					//	p.setBackground(Color.decode("#B290D4"));
					//if(nodes[x][y].getIsEndIntersection())
					//	p.setBackground(Color.decode("#D4D490"));
					if(nodes[x][y].getIsStartNode()) {
						p.setBackground(Color.decode("#B2D490"));						
					}
					if(nodes[x][y].getIsEndNode())
						p.setBackground(Color.decode("#D49090"));
					p.setBounds(gridSquareSize*(x-1)+(pnlGrid.getWidth() % maze.getGridSize())/2, gridBottomCornerY-gridSquareSize*(y)-(pnlGrid.getWidth() % maze.getGridSize())/2, gridSquareSize, gridSquareSize);
					ArrayList<Robot> robots = swarm.getRobotSet();
					for (Robot r : robots) {
						SwarmNode currentCoord = r.getCurrentSwarmNode();
						if (currentCoord.getXCoord() == x && currentCoord.getYCoord() == y) {
//							Icon icon = new ImageIcon("http://www.iconsdb.com/black-icons/circle-icon.html");
							JLabel label = new JLabel("" + r.getIdent());//, icon, JLabel.CENTER);
//							label.setText("" + r.getIdent());
//							label.setIcon(icon);
							p.add(label);
						}
					}
					
					nodePanelList.add(p);
				}
				else
				{
					p.setBorder(emptyBorder);
					p.setBackground(Color.decode("#cccccc"));
					p.setBounds(gridSquareSize*(x-1)+(pnlGrid.getWidth() % maze.getGridSize())/2, gridBottomCornerY-gridSquareSize*(y)-(pnlGrid.getWidth() % maze.getGridSize())/2, gridSquareSize, gridSquareSize);
					nodePanelList.add(p);
				}
			}
		}
		for(JPanel j : nodePanelList)
		{
			pnlGrid.add(j);
		}
		pnlGrid.repaint();
	}
}
