
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Example Directed graph implementation for Strings for COMP2911 Lab Week 7
 * @author Timothy Wiley
 */
public class DirectedStringGraph {
	/**
	 * The directed graph is represented by a list of nodes
	 */
	protected LinkedList<DSGNode> nodes;
	static private final int IGNORE_WEIGHT_VALUE = 0;
	
	/**
	 * Construct an empty Directed String Graph
	 */
	public DirectedStringGraph() {
		nodes = new LinkedList<DSGNode>();
	}
	
	/**
	 * Add the given string to the graph, creating a new node.
	 * Strings compared for uniqueness by their equals() method
	 * @param s String to add to the graph
	 */
	public void addNode(String s) {
		if (!containsNode(s)) {
			DSGNode node = new DSGNode(s);
			nodes.add(node);
		}
	}
	
	/**
	 * Removes the given string from the graph, and all outgoing/incoming connections
	 * Strings compared for uniqueness by their equals() method
	 * @param s String to remove from the graph
	 */
	public void deleteNode(String s) {
		// Get the node
		DSGNode toDelete = findNode(s);
		
		if (toDelete != null) {
			// Remove node
			nodes.remove(toDelete);
			
			// Remove all incoming connections
			for (DSGNode from : nodes) {
				DSGEdge edge = new DSGEdge(from, toDelete, IGNORE_WEIGHT_VALUE);
				deleteEdge(edge);
			}
		}
	}

	/**
	 * Adds an directed edge from the node sFrom to the node sTo
	 * @param sFrom From node
	 * @param sTo To node
	 * @param weight Weight of the link
	 */
	public void addEdge(String sFrom, String sTo, int weight) {
		DSGNode from = findNode(sFrom);
		DSGNode to = findNode(sTo);
		
		if (from != null && to != null) {
			DSGEdge edge = new DSGEdge(from, to, weight);
			if (!from.getEdges().contains(edge)) {
				from.getEdges().add(edge);
			}
		}
	}
	
	/**
	 * Removes the given edge from the graph, if the edge exists
	 * @param sFrom From node
	 * @param sTo To node
	 */
	public void deleteEdge(String sFrom, String sTo) {
		DSGNode from = findNode(sFrom);
		DSGNode to = findNode(sTo);
		
		if (from != null && to != null) {
			DSGEdge edge = new DSGEdge(from, to, IGNORE_WEIGHT_VALUE);
			deleteEdge(edge);
		}
	}
	
	/**
	 * Removes the given edge from the graph, if the edge exists
	 * @param edge Edge to remove from the graph
	 */
	private void deleteEdge(DSGEdge edge) {
		edge.getFrom().getEdges().remove(edge);
	}
	
	/**
	 * True if the graph contains the given node.
	 * Strings compared for uniqueness by their equals() method 
	 * @param s String to find
	 * @return True if the node is contained in the graph, false otherwise
	 */
	public boolean containsNode(String s) {
		Iterator<DSGNode> nit = nodes.iterator();
		while (nit.hasNext()) {
			DSGNode next = nit.next();
			if (next.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Number of nodes in the graph
	 * @return Number of nodes in the graph
	 */
	public int nNodes() {
		return nodes.size();
	}
	
	/**
	 * Retrieve all of the nodes stored in this graph
	 * @return Array of nodes in the graph
	 */
	public String[] getNodes() {
		String[] sNodes = new String[nodes.size()];
		int i = 0;
		for (DSGNode node : nodes) {
			sNodes[i] = node.getValue();
			++i;
		}
		return sNodes;
	}
	
	/**
	 * Retrieve all nodes stored in graph in object form
	 * @return Object of each node in graph
	 */
	public LinkedList<DSGNode> getNodeObjects() {
		LinkedList<DSGNode> allNodes = new LinkedList<DSGNode>();
		for (DSGNode node : nodes) {
			allNodes.add(node);
		}
		return allNodes;
	}
	
	/**
	 * Determines if the directed edge exists
	 * @param sFrom From node
	 * @param sTo To node
	 */
	public boolean isConnected(String sFrom, String sTo) {
		DSGNode from = findNode(sFrom);
		DSGNode to = findNode(sTo);
		
		if (from != null && to != null) {
			DSGEdge edge = new DSGEdge(from, to, IGNORE_WEIGHT_VALUE);
			return from.getEdges().contains(edge);
		}
		return false;
	}
	
	/**
	 * Returns all of the nodes connected by an edge to the given node
	 * @param s Node to get connections of
	 * @return Array of nodes x such that there exists an edge s -> x
	 */
	public String[] getEdges(String s) {
		String[] edges = null;
		DSGNode node = findNode(s);
		
		if (node != null) {
			edges = new String[node.getEdges().size()];
			int i = 0;
			for(DSGEdge edge : node.getEdges()) {
				edges[i] = edge.getTo().getValue();
				++i;
			}
		}
		
		return edges;
	}
	
	/**
	 * Finds the node for the given string s
	 * @param s String to find
	 * @return Node corresponding to s
	 */
	public DSGNode findNode(String s) {
		DSGNode node = null;
		Iterator<DSGNode> nit = nodes.iterator();
		while (nit.hasNext() && node == null) {
			DSGNode next = nit.next();
			if (next.equals(s)) {
				node = next;
			}
		}
		return node;
	}
	
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		for (DSGNode node : nodes) {
			string.append(node.toString());
		}
		return string.toString();
	}
	
}
