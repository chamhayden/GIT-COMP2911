import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Heuristic takes an object of type E, and
 *  provides an arbitrary/relative estimate
 *  of how close the particular object is to the 
 *  goal state.<br /><br />
 *  
 *  This implements Heuristic interface with
 *   generic type of "SearchNode<Vector>"<br /><br />
 *  
 *  <b>This heuristic is both monotonic and admissible.</b><br /><br />
 *  
 *  <h5>Limitations of Program</h5>
 *  It should be noted that for optimal paths to be completed
 *   in a reasonable amount of time (while also using a reasonable
 *   amount of memory that doesn't exceed ~2gb) that the number
 *   of "Jobs" entered should be typically no more than 17.
 *  
 *  <h5>Mathematical details of this heuristic</h5>
 *  Let "E" be the set of all edges between "n" unexplored points
 *   and the same "n" unexplored points.
 *  Let "M" be the smallest value in this set "E"
 *  Let "S" be a set containing all values of "M"
 *  The heuristic result is equivalent to the sum of 
 *   the smallest (n-1) values of S
 *   
 *  <h5>General summary of this heuristic</h5>
 *  This heuristic finds all of the edges from every unvisited
 *   node to every other unvisited node. For each node, the
 *   smallest edge (neighbour) is taken. Of all of these smallest neighbours,
 *   the smallest n-1 (where n is the number of unvisited nodes) is summed
 *   and returned.
 *  
 * @author	Hayden Charles Smith, z3418003<br />
 * 			Last modified: 19th May 2013
 */
public class CourierDeliveryHeuristic implements Heuristic<SearchNode<Vector>> {
	
	/**
	 * Constructs a MinimalEdgeHeuristic object
	 * @param graph DirectedGraph consisting of generic types
	 *  Vector
	 */
	public CourierDeliveryHeuristic(DirectedGraph<Vector> graph)
	{
		this.graph = graph;
	}
	
	/**
	 * Given an SearchNode of generic type Vector, this method
	 *  returns an arbitrary integer that denotes
	 *  the relative closeness of a particular object
	 *  to a goal state
	 * @param node SearchNode of generic type Vector 
	 * @return Arbitrary integer that denotes
	 *  how relatively close a particular SearchNode of generic
	 *   type E is to the goal state compared to other objects
	 */
	public int getEstimate(SearchNode<Vector> node)
	{
		Comparator<Integer> highToLow = new Comparator<Integer>()
		{
			public int compare(Integer a, Integer b)
			{
				return (b - a);
			}
		};
		int sum = 0;
		PriorityQueue<Integer> edgeWeights = new PriorityQueue<Integer>(INITIAL_QUEUE_CAPACITY, highToLow);
				
		for (Vector vector : this.graph.getNodes())
		{
			int minimumValue = Integer.MAX_VALUE;
			if (!node.hasVisitedObj(vector))
			{
				ArrayList<Vector> neighbours = new ArrayList<Vector>(graph.getNeighbours(vector));
				for (Vector neighbour : neighbours)
				{
					if (!node.hasVisitedObj(neighbour) && node.getNodeObj() != neighbour)
					{
						int distance = vector.getExternalDistanceTo(neighbour);
						if (minimumValue > distance)
						{
							minimumValue = distance;
						}
					}
				}
				if (minimumValue > 0)
				{
					edgeWeights.add(minimumValue);
				}
			}
		}
		
		/**
		 *  This strips the biggest edge. This is because
		 *  for n nodes we want to consider (n-1) edges
		 */		    
		edgeWeights.poll();
		
		for (Integer val : edgeWeights)
		{
			sum += val;
		}
		return sum;
		
	}
	
	private DirectedGraph<Vector> graph;
	private final int INITIAL_QUEUE_CAPACITY = 100;
}
