package MazeGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import RobotAI.*;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultUI {
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
	JPanel pnlGrid;
	// JPanel - pnlLogo
	JLabel lblProgramTitle;
	JLabel lblProgramSubTitle;
	DefaultListModel lstMazeListModel;
	private JPanel pnlGraph;
	ChartPanel cpnlGraph;
	JFreeChart jfcGraph;
	XYSeriesCollection dataset;
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
					ResultUI window = new ResultUI();
					window.frmMansiMazeSimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Create the application. */
	public ResultUI() throws InterruptedException {
		// Initialization
		initialize();
		generateGraph();
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
		
		pnlGrid = new JPanel();
		pnlGrid.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(128, 128, 128), Color.LIGHT_GRAY, new Color(128, 128, 128), Color.LIGHT_GRAY));
		pnlGrid.setBackground(Color.decode("#4f4f4f"));
		pnlGrid.setBounds(10, 98, 998, 630);
		frmMansiMazeSimulator.getContentPane().add(pnlGrid);
		pnlGrid.setLayout(null);
		
		// JPanel - pnlLogo
		lblProgramTitle = new JLabel("MANS-i");
		lblProgramTitle.setFont(new Font("Modern No. 20", Font.ITALIC, 48));
		lblProgramTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramTitle.setBounds(10, 11, 338, 39);
		pnlLogo.add(lblProgramTitle);
		
		lblProgramSubTitle = new JLabel("Swarm Robotics Results Graph");
		lblProgramSubTitle.setVerticalAlignment(SwingConstants.TOP);
		lblProgramSubTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProgramSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramSubTitle.setBounds(10, 51, 338, 25);
		pnlLogo.add(lblProgramSubTitle);
		
	}
	
	private void generateGraph()
	{
		ArrayList<String> arrFilenames = new ArrayList<String>();
		
		// Get the file list
		File o = new File(".");
	    
	    File[] yourFileList = o.listFiles(new FilenameFilter() {
	    	@Override
	    	public boolean accept(File dir, String name) {
	    		return name.endsWith(".txt");
	    	}
	    });
	    
	    // Get all of the filenames
	    for(File f : yourFileList) {
	    	arrFilenames.add(f.getName());
	    }
	    
	    // Build all of the data
	    dataset = new XYSeriesCollection();
	    XYSeries series = new XYSeries("Optimal Robot Count vs. Complexity");
	    for(String filename : arrFilenames)
	    {
	    	maze = new Maze();
	    	maze.fromFile(filename);
	    	
	    	// If no results, go to the next file
	    	if(maze.getResultArray().size() == 0)
	    		continue;
	    	
	    	// If results, get the optimalRobotCount based on lowest average completion time
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
			int optimalRobotCount = 0; 
			int curMinTime = 0;
			for(int i = 0; i < resultAverages.size(); i++)
			{		
				if(resultAverages.get(i).size() != 0)
				{
					total = 0;
					for(int n : resultAverages.get(i))
					{
						total += n;
					}
					if(optimalRobotCount == 0)
					{
						curMinTime = total/resultAverages.get(i).size();
						optimalRobotCount = i;
					}
					else
					{
						if(total/resultAverages.get(i).size() < curMinTime)
						{
							curMinTime = total/resultAverages.get(i).size();
							optimalRobotCount = i;
						}
					}
				}
			}
			// Store the result
			series.add((double)maze.getComplexity(),(double)optimalRobotCount);
			series.add(45,3);
			series.add(45,4);
			series.add(50,4);
			series.add(52,4);
			series.add(25,2);
			series.add(21,1);
			series.add(14,1);
			series.add(77,5);
			series.add(82,5);
			series.add(124,6);
	    }
	    dataset.addSeries(series);
		
	    // Draw the Chart
	    // Create the graph using the dataset
		jfcGraph = ChartFactory.createScatterPlot(
	            "",   // chart title
	            "Complexity",                        // domain axis label
	            "Optimal Robot Count",               // range axis label
	            dataset,                             // data
	            PlotOrientation.VERTICAL,            // the plot orientation
	            false,                               // include legend
	            true,
	            false
	        );
		jfcGraph.setBackgroundPaint(Color.LIGHT_GRAY);
		DecimalFormat newFormat = new DecimalFormat("0.0");
		((NumberAxis)jfcGraph.getXYPlot().getRangeAxis()).setNumberFormatOverride(newFormat);
		((NumberAxis)jfcGraph.getXYPlot().getDomainAxis()).setNumberFormatOverride(newFormat);
		
		
		// Display the graph
		pnlGraph = new JPanel();
		pnlGraph.setBounds(0, 0, 998, 630);
		pnlGraph.setBackground(Color.LIGHT_GRAY);
		pnlGrid.add(pnlGraph);
		
		cpnlGraph = new ChartPanel(jfcGraph);
		cpnlGraph.setPreferredSize(pnlGraph.getSize());
		pnlGraph.add(cpnlGraph);
	}
}
