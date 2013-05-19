import java.util.LinkedList;

public interface SearchNode<E> {
	
	/**
	 * Returns the DualPoint object contained within the AsearchNode
	 * @return The DualPoint object contained within the AsearchNode
	 */
	public E getNodeObj();
	
	/**
	 * Adds an AsearchNode to the list of previously visited AsearchNode's
	 *  contained within this node
	 * @param newVisited AsearchNode to add to the visited list
	 */
	public void addVisited(SearchNode<E> newVisited);

	/**
	 * Get the number of AsearchNodes that have been visited
	 * @return number of AsearchNodes previously visited
	 */
	public int getNumNodesVisited();
	
	/**
	 * Given a DualPoint, determines whether the current state
	 *  space has visited the node before
	 * @param otherNode DualPoint object to check if has been 
	 *  visited
	 * @return Whether the DualPoint object has been visited by
	 *  the current state space
	 */
	public boolean hasVisitedObj(E otherNode);
	
	/**
	 * Returns a list of AsearchNodes that have been visited
	 *  previously by the current node.
	 * @return LinkedList of AsearchNodes that have been visited
	 */
	public LinkedList<SearchNode<E>> getNodesVisited();
	
	/**
	 * Returns a list of AsearchNodes that have been visited
	 *  previously by the current node.
	 * @return LinkedList of AsearchNodes that have been visited
	 */
	public LinkedList<E> getNodeObjsVisited();
		
	/**
	 * Get the cumulative external distance the current state
	 *  space has travelled in order to reach it's current
	 *  location
	 * @return Cumulative external distance travelled to current 
	 *  state
	 */
	public int getExternalDistanceTravelled();
	
	/**
	 * Get the cumulative external and internal distance the
	 *  current state space has travelled in order to
	 *  reach it's current location
	 * @return Cumulative external and internal distance
	 *  travelled to current state
	 */
	
	public int getEstimatedDistanceRemaining();
	
	public void setEstimatedDistanceRemaining(int val);
	
	public int getHeuristicEstimate();
	
	//public boolean isSameContents(LinkedList<SearchNode<E>> visited);
	
}