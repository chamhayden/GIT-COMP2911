import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * Given a list of Jobs with a "start" point and a "finish" point
 *  from standard input, this class creates a graph, and adds these
 *  points to the graph.<br />
 * This class then performs an A* search on the graph to find 
 *  the minimal spanning path from one "CourierDeliveryJobVector" to
 *  another that passes through every "CourierDeliveryJobVector"<br /><br />
 * 
 * <b>For details on the <u>Heuristic</u> please see
 *  the CourierDeliveryHeuristic class</b><br /><br />
 *  
 *  Depending on the jobs entered, the program could reasonably
 *   evaluate up to 19 nodes, or as little as 15 etc, so it should
 *   be noted that 17 is a general limit, and not a fixed
 *   limit.
 * 
 * <h5>Sample Input</h5>
 * Job 3 3 to 5 3<br />
 * Job 1 1 to 9 2<br />
 * Job 3 5 to 2 7<br />
 * Job 5 5 to 5 7<br />
 * 
 * <h5>Sample Output (for above)</h5>
 * 5 nodes explored<br />
 * cost = 31<br />
 * Move from 0 0 to 1 1<br />
 * Carry from 1 1 to 9 2<br />
 * Move from 9 2 to 3 3<br />
 * Carry from 3 3 to 5 3<br />
 * Move from 5 3 to 5 5<br />
 * Carry from 5 5 to 5 7<br />
 * Move from 5 7 to 3 5<br />
 * Carry from 3 5 to 2 7<br />
 * @author	Hayden Charles Smith, z3418003<br />
 * 			Last modified: 19th May 2013
 */

public class CourierDelivery
{
	/**
	 * Main method for program
	 * @param args Arguments from standard input
	 */
	public static void main(String[] args)
	{
		try
	    {			
			graph				= new AdjacencyListGraph<Vector>();
			asearch				= new Asearch(graph);
			Vector origin	= new CourierDeliveryJobVector(0, 0, 0, 0);
			Scanner userInput 	= new Scanner(new FileReader(args[0]));
			
			Comparator<SearchNode<Vector>> comp = new Comparator<SearchNode<Vector>>()
			{
				public int compare(SearchNode<Vector> e1, SearchNode<Vector> e2)
				{
			    	if (e1.getHeuristicEstimate() > e2.getHeuristicEstimate()) return 1;
			    	if (e1.getHeuristicEstimate() < e2.getHeuristicEstimate()) return -1;
			    	else return 0;
				}
			};
			
			buildGraph(userInput, origin);
			LinkedList<Vector> minimalSpanningPathList = asearch.findMinimalSpanningPath(origin, comp); 
			printPathList(minimalSpanningPathList); 
	    }
	    catch (FileNotFoundException e)
	    {
	    	speak("FileNotFoundException: " + e.getMessage());
	    }
		catch (ArrayIndexOutOfBoundsException e)
		{
			speak("Please add a single parameter that is an input file");
		}
	}
	
	/**
	 * Given a Scanner input, this method extracts all of the 
	 *  relative point data for the reaction of CourierDeliveryJobVector's that
	 *  are then added to the graph
	 * @param userInput Scanner input
	 * @param initialVector Origin point to add to the graph (in 
	 *  case an origin is not given in the input)
	 */
	private static void buildGraph(Scanner userInput, Vector initialVector)
	{
		if (initialVector != null)
		{
			graph.addNode(initialVector);
		}
		
		while (userInput.hasNextLine())
		{
			// Split string into components
			String input[] = userInput.nextLine().split(" ");
			
			// Extract integer values from array of components
			int fromX = Integer.parseInt(input[COODINATE_FROM_X]);
			int fromY = Integer.parseInt(input[COODINATE_FROM_Y]);
			int toX =	Integer.parseInt(input[COODINATE_TO_X]);
			int toY = 	Integer.parseInt(input[COODINATE_TO_Y]);
			graph.addNode(new CourierDeliveryJobVector(fromX, fromY, toX, toY));
		}
		userInput.close();
		
		// Ensure every node has an edge to every other node
		graph.connectAllNodes();
	}
	
	/**
	 * Given a list of Vectors, print out the number of Vectors in this path,
	 *  the total distance covered by the Vector (internal and external),
	 *  and provide a summary of the movements (or carries) made between or within
	 *  these Vectors
	 * @param path LinkedList of Vector's that constitute the path
	 * @return Single string that contains all necessary text
	 */
	private static void printPathList(LinkedList<Vector> path)
	{
		String output = new String();
		int totalDistance = 0;
		for (int i = 0; i < (path.size() - 1); i++)
		{
			Vector nodeLeft = path.get(i);
			Vector nodeRight = path.get(i + 1);
			
			// Check if "Move"'s START and FINISH are not the same
			if (nodeLeft.getExternalDistanceTo(nodeRight) > 0)
			{
				output = output + "Move from " + nodeLeft.getXFinish() + " " + nodeLeft.getYFinish()
				 + " to " + nodeRight.getXStart() + " " + nodeRight.getYStart() + "\n";
			}
			output = output + "Carry from " + nodeRight.getXStart() + " " + nodeRight.getYStart()
			 + " to " + nodeRight.getXFinish() + " " + nodeRight.getYFinish() + "\n";
			
			// Add both the internal and external distance on
			totalDistance += nodeLeft.getInternalDistance();
			totalDistance += nodeLeft.getExternalDistanceTo(nodeRight);
		}
		totalDistance += path.get(path.size() - 1).getInternalDistance();
		
		output = "cost = " + totalDistance + "\n" + output;
		output = asearch.getNumNodesExplored() + " nodes explored\n" + output;
		speak(output);
	}

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
	
	private static AdjacencyListGraph<Vector> graph;
	private static Asearch asearch;
	private static final int COODINATE_FROM_X = 1;
	private static final int COODINATE_FROM_Y = 2;
	private static final int COODINATE_TO_X = 4;
	private static final int COODINATE_TO_Y = 5;
	
}