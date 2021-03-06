import java.util.LinkedList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * Allows for searching of a given DirectedGraph with
 *  an A* search - by using a comparator in order
 *  to give weighting to particular state spaces over
 *  one another.
 *  <br />
 * Please note this A* Search is based on the 
 *  Vector class
 * 
 * @author	Hayden Charles Smith, z3418003<br />
 * 			Last modified: 19th May 2013
 */
public class Asearch {

	/**
	 * Construct an Asearch Object
	 * @param graph Graph containing Vector's that need to 
	 *  be searched
	 */
	public Asearch(DirectedGraph<Vector> graph)
	{
		this.graph = graph;
		heuristic = new CourierDeliveryHeuristic(graph);
	}
	
	/**
	 * This method finds a path from the initial Vector passed
	 *  in to a final Vector, while passing through every other
	 *  Vector.
	 * @param initialPoint Vector in which to start from
	 * @param comp Comparator which orders items added to PriorityQueue.
	 *  This comparator is of generic type SearchNode<Vector>
	 * @return LinkedList of Vector's that make up the path 
	 */
	public LinkedList<Vector> findMinimalSpanningPath(Vector initialPoint, Comparator<SearchNode<Vector>> comp)
	{
		// Establish path to take
		LinkedList<Vector> result = new LinkedList<Vector>();
		PriorityQueue<SearchNode<Vector>> priorityQueue = new PriorityQueue<SearchNode<Vector>>(INITIAL_QUEUE_CAPACITY, comp);
		int explored = 0;
		if (graph.getNumNodes() > 0)
		{
			SearchNode<Vector> current = new AsearchNode<Vector>(initialPoint, 0);
			
			priorityQueue.add(current);
			
			boolean visitedAll = false;
			
			ArrayList<Vector> neighbours = null;
			
			while (!priorityQueue.isEmpty() && !visitedAll)
			{
				current = priorityQueue.poll();
				explored++;
				
				current.addVisited(current);
				neighbours = graph.getNeighbours(current.getNodeObj());
			    for(Vector currentNeighbour : neighbours)
			    {
			    	if (!current.hasVisitedObj(currentNeighbour))
			    	{
				    	int distanceDifference = current.getNodeObj().getExternalDistanceTo(currentNeighbour);
				    		
				    	int travelled = (current.getExternalDistanceTravelled() + distanceDifference);
				    	SearchNode<Vector> nodeToAdd = new AsearchNode<Vector>(currentNeighbour, travelled);
				    		
			    		for(SearchNode<Vector> alreadyVisited : current.getNodesVisited())
			    		{
			    			nodeToAdd.addVisited(alreadyVisited);
			    		}
			    		nodeToAdd.setEstimatedDistanceRemaining(heuristic.getEstimate(nodeToAdd));
			    		priorityQueue.add(nodeToAdd);
			    	}
			    }
				  
			    if (current.getNumNodesVisited() >= this.graph.getNumNodes())
				{
					visitedAll = true;
				}
			}
			if(current.getNumNodesVisited() == this.graph.getNumNodes()) {
				result = current.getNodeObjsVisited();
			}			 
		}
		this.nodesExplored = explored;
		return result;		
	}
	
	/**
	 * Return the number of nodes that have been explored in the search
	 * @return Number of nodes that have been explored in the search
	 */
	public int getNumNodesExplored()
	{
		return this.nodesExplored;
	}
		
	private int nodesExplored;
	private DirectedGraph<Vector> graph;
	private static final int INITIAL_QUEUE_CAPACITY = 100;
	private Heuristic<SearchNode<Vector>> heuristic;

}
