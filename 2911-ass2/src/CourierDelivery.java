import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;
import java.lang.StringBuffer;
import java.util.LinkedList;
import java.text.DecimalFormat;

/**
 * Courier Delivery Class.
 * 
 * Given a list of Jobs with a "from" point and a "to" point
 *  from standard input, this class creates a graph, adds these
 *  points to the graph.
 * This class then performs an A* search on the graph to find 
 *  the minimal spanning path from one DualNode to
 *  another that passes through every DualNode
 * 
 * TODO: Set limitations
 * 
 * @author	Hayden Charles Smith, z3418003
 * 			Last modified: 15th May 2013
 */

public class CourierDelivery
{
	/**
	 * Main method for program
	 * @param args Arguments from standard input
	 */
	public static void main(String[] args)
	{

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        
        double miliStart = System.currentTimeMillis();
		try
	    {
			Scanner userInput 					= new Scanner(new FileReader(args[0]));			
			DualPoint initialDualPoint 			= new JobPoint(0, 0, 0, 0);
			AdjacencyListGraph<DualPoint> graph = new AdjacencyListGraph<DualPoint>();
			
			while (userInput.hasNextLine())
			{
				String input[] = userInput.nextLine().split(" ");
				int fromX = Integer.parseInt(input[COODINATE_FROM_X]);
				int fromY = Integer.parseInt(input[COODINATE_FROM_Y]);
				int toX =	Integer.parseInt(input[COODINATE_TO_X]);
				int toY = 	Integer.parseInt(input[COODINATE_TO_Y]);
				graph.addNode(new JobPoint(fromX, fromY, toX, toY));
			}
			userInput.close();
			
			graph.addNode(initialDualPoint);			
			graph.connectAllNodes();
			asearch = new Asearch(graph);
			
			Comparator<SearchNode<DualPoint>> comparator = new Comparator<SearchNode<DualPoint>>()
		    { 
				public int compare(SearchNode<DualPoint> e1, SearchNode<DualPoint> e2)
				{
			    	if (e1.getHeuristicEstimate() > e2.getHeuristicEstimate()) return 1;
			    	if (e1.getHeuristicEstimate() < e2.getHeuristicEstimate()) return -1;
			    	else return 0;
				}
		    };
			speak(getPathPrintout(asearch.findMinimalSpanningPath(initialDualPoint, comparator))); 
	    }
	    catch (FileNotFoundException e)
	    {
	    	speak("FileNotFoundException: " + e.getMessage());
	    }
		catch (ArrayIndexOutOfBoundsException e)
		{
			speak("Please add a single parameter that is an input file");
		}
		
		speak("\nTime: " + (System.currentTimeMillis() - miliStart) / 1000 + "s\n");
		
        double one = (new Double(runtime.totalMemory() - runtime.freeMemory()).doubleValue());
        double two = ((new Double(runtime.maxMemory()).doubleValue()));
        double mix = ((one / two) * 100);
        speak("Memory Usage: " + mix + "% of " + runtime.maxMemory());
    
		
	}

	
	/**
	 * Given a list of DualPoints, print out the number of DualPoints in this path,
	 *  the total distance covered by the DualPoints (internal and external),
	 *  and provide a summary of the movements (or carries) made between or within
	 *  these DualPoints
	 * @param path LinkedList of DualPoint's that constitute the path
	 * @return Single string that contains all necessary text
	 */
	private static String getPathPrintout(LinkedList<DualPoint> path)
	{
		StringBuffer printResult = new StringBuffer();
		StringBuffer nodePrintouts = new StringBuffer();
		int totalDistance = 0;
		int i = 0;
		for (; i < (path.size() - 1); i++)
		{
			DualPoint nodeLeft = path.get(i);
			DualPoint nodeRight = path.get(i + 1);
			if (nodeLeft.getExternalDistanceTo(nodeRight) > 0)
			{
				nodePrintouts.append("Move from " + nodeLeft.getToX() + " " + nodeLeft.getToY() + " to " + nodeRight.getFromX() + " " + nodeRight.getFromY() + "\n");
			}
			nodePrintouts.append("Carry from " + nodeRight.getFromX() + " " + nodeRight.getFromY() + " to " + nodeRight.getToX() + " " + nodeRight.getToY() + "\n");
			totalDistance += nodeLeft.getInternalDistance();
			totalDistance += nodeLeft.getExternalDistanceTo(nodeRight);
		}
		//totalDistance += path.get(i).getInternalDistance();
		
		printResult.append(path.size() + " nodes explored\n");
		printResult.append("cost = " + totalDistance + "\n");
		printResult.append(nodePrintouts.toString());
		return printResult.toString();
	}
	
	/* May, 2013: For Assignment 2, do not print out move actions where the start and end points are the same; thus the output could consist of two (or more) carry actions in sequence. */

	/**
	 * Print a string to standard output
	 * @param printout String to print
	 */
	private static void speak(String printout)
	{
		if (!printout.equals(""))
		{
			System.out.print(printout);
		}
	}
	
	private static Asearch asearch;
	private static final int COODINATE_FROM_X = 1;
	private static final int COODINATE_FROM_Y = 2;
	private static final int COODINATE_TO_X = 4;
	private static final int COODINATE_TO_Y = 5;
	
}


