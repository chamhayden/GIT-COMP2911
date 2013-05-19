import java.util.LinkedList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * A* Search Class.
 * 
 * Allows for searching of a given Graph with
 *  an A* search - by using a comparator in order
 *  to give weighting to particular state spaces over
 *  one another.
 *  
 * Please note this A* Search is based on the 
 *  DualNode class
 * 
 * @author	Hayden Charles Smith, z3418003
 * 			Last modified: 15th May 2013
 */
public class Asearch {

	/**
	 * Construct an Asearch Object
	 * @param graph Graph containing DualPoint's that need to 
	 *  be searched
	 */
	public Asearch(DirectedGraph<DualPoint> graph)
	{
		this.graph = graph;
		heuristic = new MinimalEdgeHeuristic(graph);
	}
	
	/**
	 * This method finds a path from the initial DualPoint passed
	 *  in to a final DualPoint, while passing through every other
	 *  DualPoint.
	 * @param initialPoint DualPoint in which to start from
	 * @param comp Comparator which orders items added to PriorityQueue
	 * @return LinkedList of DualPoint's that make up the path 
	 */
	public LinkedList<DualPoint> findMinimalSpanningPath(DualPoint initialPoint, Comparator<SearchNode<DualPoint>> comp)
	{
		// Establish path to take
		LinkedList<DualPoint> result = new LinkedList<DualPoint>();
		PriorityQueue<SearchNode<DualPoint>> priorityQueue = new PriorityQueue<SearchNode<DualPoint>>(INITIAL_QUEUE_CAPACITY, comp);
		
		if (graph.getNumNodes() > 0)
		{
			int c = 0;
			SearchNode<DualPoint> current = new AsearchNode<DualPoint>(initialPoint, 0);
			LinkedList<SearchNode<DualPoint>> closedList = new LinkedList<SearchNode<DualPoint>>();
			
			priorityQueue.add(current);
			
			boolean visitedAll = false;
			
			ArrayList<DualPoint> neighbours = null;
			int explored = 0;
			while (!priorityQueue.isEmpty() && !visitedAll)
			{
				//System.out.println("Closed List: " + closedList);
				current = priorityQueue.poll();
				explored++;
				//System.out.println("SNode: " + current);
				
				if (!closedList.contains(current))
				{
					current.addVisited(current);
					neighbours = graph.getNeighbours(current.getNodeObj());
				    for(DualPoint currentNeighbour : neighbours)
				    {
				    	if (!current.hasVisitedObj(currentNeighbour))
				    	{
					    	int distanceDifference = current.getNodeObj().getExternalDistanceTo(currentNeighbour);
					    		
					    	int travelled = (current.getExternalDistanceTravelled() + distanceDifference);
					    	SearchNode<DualPoint> nodeToAdd = new AsearchNode<DualPoint>(currentNeighbour, travelled);
					    		
				    		for(SearchNode<DualPoint> alreadyVisited : current.getNodesVisited())
				    		{
				    			nodeToAdd.addVisited(alreadyVisited);
				    		}
				    		nodeToAdd.setEstimatedDistanceRemaining(heuristic.getEstimate(nodeToAdd));
				    		priorityQueue.add(nodeToAdd);
				    		c++;
				    	}
				    }
				    closedList.add(current);
				}
			    if (current.getNumNodesVisited() >= this.graph.getNumNodes())
				{
					visitedAll = true;
				}
			}
			if(current.getNumNodesVisited() == this.graph.getNumNodes()) {
				result = current.getNodeObjsVisited();
			}			 
			System.out.println("Iterations: " + c);
			System.out.println("Nodes Explored: " + explored);
		}
		return result;		
	}
		
	private DirectedGraph<DualPoint> graph;
	private static final int INITIAL_QUEUE_CAPACITY = 100;
	private Heuristic<SearchNode<DualPoint>> heuristic;

}
