import java.util.ArrayList;

/**
 * This class implements an adjacency list graph. Graph nodes
 *  and edges are stored as an adjacency list.
 *  
 * @author	Hayden Charles Smith, z3418003<br />
 * 			Last modified: 19th May 2013
 */
public class AdjacencyListGraph<E> implements DirectedGraph<E>
{

	/**
	 * Constructs an AdjacencyListGraph, initializing the
	 *  2D ArrayList that consisting of generic objects E
	 */
	public AdjacencyListGraph()
	{
		nodes = new ArrayList<ArrayList<E>>();	
	}
	
	/**
	 * Adds a node to the adjacency list graph
	 * @param e Object to add
	 */
	public void addNode(E e)
	{
		ArrayList<E> adjList = new ArrayList<E>();
		adjList.add(e);
		nodes.add(adjList);
	}
	
	/**
	 * Removes a node (and it's edges) from the adjacency
	 *  list graph
	 * @param e Object to remove
	 */
	public void removeNode(E e)
	{
		boolean finished = false;
		for (ArrayList<E> nodeList : nodes)
		{
			if (getNode(nodeList).equals(e))
			{
				if (!finished)
				{
					nodes.remove(nodeList);
				}
				finished = true;
			}
		}
		finished = false;
		for (ArrayList<E> nodeList : nodes)
		{
			for (E item : nodeList)
			{
				if (item.equals(e) && !finished)
				{
					nodeList.remove(item);
					finished = true;
				}
			}
		}
	}

	/**
	 * Adds an edge between two nodes
	 * @param from Directed edge FROM this point
	 * @param to Directed edge TO this point
	 */
	public void addEdge(E from, E to)
	{
		for (ArrayList<E> nodeList : nodes)
		{
			if (getNode(nodeList).equals(from))
			{
				nodeList.add(to);
			}
		}		
	}

	/**
	 * Determines if two vertices are connected
	 * @param from Node to test connection FROM
	 * @param to Node to test connection TO
	 * @return Whether it is connected
	 */
	public boolean isConnected(E from, E to)
	{
		for (ArrayList<E> nodeList : nodes)
		{
			if (getNode(nodeList).equals(from))
			{
				return nodeList.contains(to);
			}
		}
		return false;
	}

	/**
	 * Removes an edge from a graph
	 * @param from Origin node of edge that is removed
	 * @param to Destination node of edge that is removed
	 */
	public void removeEdge(E from, E to)
	{
		for (ArrayList<E> nodeList : nodes)
		{
			if (getNode(nodeList).equals(from))
			{
				nodeList.remove(to);
			}
		}
	}

	/**
	 * Determines whether a node is in a graph
	 * @param e Object to look for
	 * @return Whether a node is in a graph
	 */
	public boolean isInGraph(E e)
	{
		for (ArrayList<E> nodeList : nodes)
		{
			if (getNode(nodeList).equals(e))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets all nodes connected by surrounding edges
	 * @param e Object that's neighbours are looking for
	 * @return ArrayList of neighbouring objects/nodes
	 */
	public ArrayList<E> getNeighbours(E e)
	{
		ArrayList<E> neighbours = new ArrayList<E>();
		for (ArrayList<E> nodeList : nodes)
		{
			if (getNode(nodeList).equals(e))
			{
				for (int i = 1; i < nodeList.size(); i++)
				{
					neighbours.add(nodeList.get(i));
				}
			}
		}
		return neighbours;
	}

	/**
	 * Returns number of nodes in the graph
	 * @return number of nodes in the graph
	 */
	public int getNumNodes()
	{
		return nodes.size();
	}
	
	/**
	 * Returns number of edges in the graph
	 * @return number of edges in the graph
	 */
	public int getNumEdges()
	{
		int totalEdges = 0;
		for (ArrayList<E> nodeList : nodes)
		{
			totalEdges += nodeList.size();
			totalEdges -= 1; // Remove initial item (is the node itself)
		}
		return totalEdges;
	}
	
	/**
	 * Creates edges between every node in the graph
	 */
	public void connectAllNodes()
	{
		for (ArrayList<E> nodeListConnectFrom : nodes)
		{
			for (ArrayList<E> nodeListConnectTo : nodes)
			{
				E node1 = getNode(nodeListConnectFrom);
				E node2 = getNode(nodeListConnectTo);
				if (!node1.equals(node2))
				{
					if (!isConnected(node1, node2))
					{
						this.addEdge(node1, node2);
					}
				}
			}
		}
	}
	
	/**
	 * Return an array list of all nodes in the graph
	 * @return ArrayList of all nodes in the graph
	 */
	public ArrayList<E> getNodes()
	{
		ArrayList<E> allNodes = new ArrayList<E>();
		for (ArrayList<E> nodeList : this.nodes)
		{
			allNodes.add(getNode(nodeList));
		}
		return allNodes;
	}
	
	/**
	 * Given a list of edges (including the node) for
	 *  a particular node, returns the node object
	 * @param nodeItems ArrayList of edges (includes node at position 0)
	 * @return Node within that list
	 */
	private E getNode(ArrayList<E> nodeItems)
	{
		return nodeItems.get(NODE_INDEX);
	}
	
	private ArrayList<ArrayList<E>> nodes;
	private static final int NODE_INDEX = 0;
}
