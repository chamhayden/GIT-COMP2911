import java.util.ArrayList;

/**
 * Graph interface
 * @author hsmi665
 *
 */
public interface Graph<E> {
	
	/**
	 * Adds a node to the graph
	 * @param e
	 */
	public void addNode(E e);
	
	/**
	 * Removes a node from the graph
	 * @param e
	 */
	public void removeNode(E e);
	
	/**
	 * Adds an edge between two nodes
	 * @param from directed edge FROM this point
	 * @param to directed edge TO this point
	 */
	public void addEdge(E from, E to);
	
	/**
	 * Determines if two vertices are connected
	 * @param from point to test connection FROM
	 * @param to point to test connection TO
	 * @return whether it is connected
	 */
	public boolean isConnected(E from, E to);
	
	/**
	 * Removes an edge from a graph
	 * @param from Edge that is removed comes FROM here
	 * @param to Edge that is removed goes TO here
	 */
	public void removeEdge(E from, E to);
	
	/**
	 * Print a graph
	 */
	public void printGraph();
	
	/**
	 * Determines whether vertices is in a graph
	 * @param e
	 * @return whether a vertice is in a graph
	 */
	public boolean isInGraph(E e);
	
	/**
	 * 
	 * @return the nodes in a graph as an array
	 */ 
	public ArrayList<ArrayList<E>> getNodes();
	
	ArrayList<E> getNeighbours(E e);
	int getNumVertices();
	int getNumEdges();
}
