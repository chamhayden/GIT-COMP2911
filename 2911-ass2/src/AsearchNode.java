import java.util.LinkedList;

/**
 * A* Search Node.
 * 
 * Acts as a node that operates in an A* search. This stores
 * 	a DualPoint object, and acts as a state space by storing
 * 	all previous nodes that have been visited upon reaching the
 *  current AsearchNode.
 * 
 * @author	Hayden Charles Smith, z3418003
 * 			Last modified: 15th May 2013
 */
public class AsearchNode<E> implements SearchNode<E> {

	/**
	 * Creates an AsearchNode object
	 * @param jobNode
	 * @param externalDistance
	 */
	public AsearchNode(E jobNode, int externalDistance)
	{
		this.nodeObj = jobNode;
		this.externalDistanceTravelled = externalDistance;
		this.visited = new LinkedList<SearchNode<E>>();
	}
	
	/**
	 * Returns the DualPoint object contained within the AsearchNode
	 * @return The DualPoint object contained within the AsearchNode
	 */
	public E getNodeObj()
	{
		return this.nodeObj;
	}
	
	/**
	 * Adds an AsearchNode to the list of previously visited AsearchNode's
	 *  contained within this node
	 * @param newVisited AsearchNode to add to the visited list
	 */
	public void addVisited(SearchNode<E> newVisited)
	{
		visited.addLast(newVisited);
	}
	
	/**
	 * Get the number of AsearchNodes that have been visited
	 * @return number of AsearchNodes previously visited
	 */
	public int getNumNodesVisited()
	{
		return visited.size();
	}
	
	/**
	 * Given a DualPoint, determines whether the current state
	 *  space has visited the node before
	 * @param otherNode DualPoint object to check if has been 
	 *  visited
	 * @return Whether the DualPoint object has been visited by
	 *  the current state space
	 */
	public boolean hasVisitedObj(E otherNode)
	{
		for (SearchNode<E> node : visited)
		{
			if (node.getNodeObj().equals(otherNode))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns a list of AsearchNodes that have been visited
	 *  previously by the current node.
	 * @return LinkedList of AsearchNodes that have been visited
	 */
	public LinkedList<SearchNode<E>> getNodesVisited()
	{
		LinkedList<SearchNode<E>> path = new LinkedList<SearchNode<E>>();
		for (SearchNode<E> obj : visited)
		{
			path.add(obj);
		}
		return path;
	}
	
	/**
	 * Returns a list of AsearchNodes that have been visited
	 *  previously by the current node.
	 * @return LinkedList of AsearchNodes that have been visited
	 */
	public LinkedList<E> getNodeObjsVisited()
	{
		LinkedList<E> path = new LinkedList<E>();
		for (SearchNode<E> obj : visited)
		{
			path.add(obj.getNodeObj());
		}
		return path;
	}
		
	/**
	 * Get the cumulative external distance the current state
	 *  space has travelled in order to reach it's current
	 *  location
	 * @return Cumulative external distance travelled to current 
	 *  state
	 */
	public int getExternalDistanceTravelled()
	{
		return this.externalDistanceTravelled;
	}
	
	public int getEstimatedDistanceRemaining()
	{
		return this.estimatedDistanceRemaining;
	}
	
	public void setEstimatedDistanceRemaining(int val)
	{
		this.estimatedDistanceRemaining = val;
	}
	
	public int getHeuristicEstimate()
	{
		//return getExternalDistanceTravelled();
		return this.estimatedDistanceRemaining + this.externalDistanceTravelled;
	}
	
	public String toString()
	{
		return nodeObj.toString() + "=>" + getNodeObjsVisited();
	}
	
	/*public boolean isSameContents(LinkedList<SearchNode<E>> visited)
	{
		boolean pruned = false;
		if (visited.size() > 0)
		{
			for (SearchNode<E> discovered : visited)
			{
				if (this.getNodeObj() != null && discovered.getNodeObj() != null) {
					if (this.getNodeObj().equals(discovered.getNodeObj()))
					{
						
						//System.out.println("Same!");
						
						boolean same = true;
						for(E job : this.getNodeObjsVisited())
						{
							if(!(discovered.getNodeObjsVisited().contains(job)))
							{
								same = false;
								//System.out.print("discovered ! contain job ...");
							}
						}
						if (same)
						{
							/*System.out.println("Node: " + this);
							System.out.println("Discovered: " + discovered);
							System.out.println("");
						}
						
						for(E job : discovered.getNodeObjsVisited())
						{
							if(!(this.getNodeObjsVisited().contains(job)))
							{
								same = false;
								//System.out.print("this ! contain job ...");
							}
						}
						
						if(same == true) {
							//pruned = true;
						}
					}
				}
			}
		}
		return pruned;
	}*/
		
		
		
		/*
		
		boolean same = true;
		if (node.getNodeObj() == this.getNodeObj())
		{			
			for (E job : node.getNodeObjsVisited())
			{
				if (!this.getNodeObjsVisited().contains(job))
				{
					same = false;
				}
			}
			for (E job : this.getNodeObjsVisited())
			{
				if (!node.getNodeObjsVisited().contains(job))
				{
					same = false;
				}
			}
		}
		else
		{
			same = false;
		}
		
		if (same) { System.out.println("FUCK"); } else {  } 
		return same;
	}
*/
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj)
	{
		
		boolean result = true;
		SearchNode<E> other;
		if(obj == this){
			result =  true;
		} else if (obj == null){
			result = false;
		} else {
			//System.out.println("");
			other = (SearchNode<E>)obj;
			result = false;
			//System.out.println("1: " + this);
			//System.out.println("2: " + other);
			if (this.getNodeObj().equals(other.getNodeObj()))
			{
				//System.out.println("Nodes equal: {"+this.getNodeObj()+"}  {"+other.getNodeObj()+"}");
				LinkedList<E> list1 = this.getNodeObjsVisited();
				LinkedList<E> list2 = other.getNodeObjsVisited();
				boolean noConflicts = true;
				for (E item : list1)
				{
					if (!list2.contains(item))
					{
						//System.out.println("Triggered");
						noConflicts = false;
					}
				}
				for (E item : list2)
				{
					if (!list1.contains(item))
					{
						//System.out.println("Triggered 2");
						noConflicts = false;
					}
				}
				if (noConflicts)
				{
					result = true;
				}
				if (result) { System.out.println("Lists the same"); System.out.println(list1); System.out.println(list2); }
			}
			else
			{
				//System.out.println("NODES NOT EQUAL");
			}
			if (result) { System.out.println("FUCK YEAH"); }
		} 
		return result;
	}
	
	private LinkedList<SearchNode<E>> visited;
	private E nodeObj;
	private int externalDistanceTravelled;
	private int estimatedDistanceRemaining;
	
}