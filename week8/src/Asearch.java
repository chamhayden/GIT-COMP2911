import java.util.LinkedList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.lang.StringBuffer;

/**
 * Implements Breadth First Search Algorithm
 * @author Hayden Smith
 *
 */
public class Asearch {

	public Asearch(DirectedStringGraph graph)
	{
		this.graph = graph;
		allNodes = graph.getNodeObjects();
	}
	
	public String findPath(String nodeStart, String nodeFinish, Comparator<AsearchNode> comp)
	{
		nodesVisited = new LinkedList<AsearchNode>();
		nodesToVisit = new PriorityQueue<AsearchNode>(100, comp);
		
		if (allNodes.size() > 0)
		{
			// Find starting node and add it to nodeToVisit
			DSGNode rootNode = graph.findNode(nodeStart);
			nodesToVisit.add(new AsearchNode(rootNode.getValue(), 0, rootNode.getStraightLineDistance(), null));
			
			// Get first node from AsearchNode nodes
			AsearchNode current = nodesToVisit.peek();
			
			while(!nodesToVisit.isEmpty() && notVisited(nodeFinish))
			{
															/*Debug*/System.out.print(printNodes("\nnodesVisited", nodesVisited));
															/*Debug*/System.out.print(printQueue("nodesToVisit", nodesToVisit));
				current = nodesToVisit.poll();
				nodesVisited.add(current);		

				LinkedList<DSGEdge> toAdd = graph.findNode(current.getValue()).getEdges();									
								
				for (DSGEdge e : toAdd) 
				{
					String nodeName = e.getTo().getValue();
					int cumulativeDistance = e.getWeight() + current.getPreviousPathDistance();
					int straightLineDistance = graph.findNode(nodeName).getStraightLineDistance();
					AsearchNode newNode = new AsearchNode(nodeName, cumulativeDistance, straightLineDistance, current);
					
															/*Debug*/System.out.println("...[Trying to add " + e.getTo().getValue() + " (" + newNode.getTotalDistances() + ")]");
														
					if (notVisited(nodeName))
					{
						if (notToVisit(nodeName))
						{
							nodesToVisit.add(newNode);
															/*Debug*/System.out.print(printQueue("JUST ADDED TO VISIT", nodesToVisit));
						}
						else 
						{
							AsearchNode oldNode = findNodeToVisit(nodeName);//
							if (newNode.getTotalDistances() < oldNode.getTotalDistances())
							{
								nodesToVisit.remove(oldNode); // Remove old node
								nodesToVisit.add(newNode);
							}
						}
					}
				}
			}
			
			return printNodes("\n\nFinal Path", nodesVisited);
			
		}
		else
		{
			return "[X]";
		}
	}
	
	
	
	
	
	
	
	
	
	private AsearchNode findNodeToVisit(String name)
	{
		for (AsearchNode item : nodesToVisit)
		{
			if (item.getValue() == name)
			{
				return item;
			}
		}
		return null;
	}
	
	private boolean notVisited(String name)
	{
		for (AsearchNode item : nodesVisited)
		{
			if (item.getValue() == name)
			{
				return false;
			}
		}
		return true;
	}
	
	private boolean notToVisit(String name)
	{
		for (AsearchNode item : nodesToVisit)
		{
			if (item.getValue() == name)
			{
				return false;
			}
		}
		return true;
	}
	
	private String printNodes(String preface, LinkedList<AsearchNode> list)
	{
		StringBuffer output = new StringBuffer();
		output.append(preface + ": .. ");
		boolean firstLoop = true;
		for (AsearchNode item : list)
		{
			if (!firstLoop)
			{
				output.append("->");
			}
			firstLoop = false;
			
			if (item != null) 
			{
				output.append(item.getValue() + "(" + item.getTotalDistances() + ")");
				
			}
			else {
				output.append("NULL");
			}
		}
		output.append("\n");
		return output.toString();
	}
	
	private String printQueue(String preface, PriorityQueue<AsearchNode> list)
	{
		StringBuffer output = new StringBuffer();
		output.append(preface + ": .. ");
		for (AsearchNode item : list)
		{
			if (item != null) 
			{
				output.append(item.getValue() + "(" + item.getTotalDistances() + ")");
				
			}
			else {
				output.append("NULL");
			}
			output.append("-");
		}
		output.append("\n");
		return output.toString();
	}
	
	private void printEdges1(String preface, LinkedList<DSGEdge> edges)
	{
		System.out.print(preface + ": .. ");
		for (DSGEdge item : edges)
		{
			System.out.print(item.toString());
			System.out.print(" || ");
		}
		System.out.println();
	}
	
	private void printEdges2(String preface, DSGNode node)
	{
		System.out.print(preface + ": .. ");
		String[] edges = this.graph.getEdges(node.getValue());
		for (int c = 0; c < edges.length; c++)
		{
			System.out.print(edges[c]);
			System.out.print(" || ");
		}
		System.out.println();
	}
	
	private DirectedStringGraph graph;
	private static LinkedList<DSGNode> allNodes;
	LinkedList<AsearchNode> nodesVisited;
	PriorityQueue<AsearchNode> nodesToVisit;

}
