import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;

/**
 * Implements Breadth First Search Algorithm
 * @author Hayden Smith
 *
 */
public class BFS {

	public BFS(DirectedStringGraph graph)
	{
		this.graph = graph;
		allNodes = graph.getNodeObjects();
	}
	
	public String findPath(String nodeStart, String nodeFinish, Comparator<DSGEdge> comp)
	{
		/*Debug*/System.out.println("==================================================================STEVEN <3 ===============================================================");
		
		LinkedList<DSGNode> nodesVisited = new LinkedList<DSGNode>();
		LinkedList<DSGNode> nodesToVisit = new LinkedList<DSGNode>();
		LinkedList<DSGNode> parent = new LinkedList<DSGNode>();
		LinkedList<DSGNode> parentVisited = new LinkedList<DSGNode>();
		if (allNodes.size() > 0)
		{
			DSGNode rootNode = allNodes.get(0);
			nodesToVisit.add(rootNode);
			parent.add(rootNode);
			
			DSGNode current = rootNode;
			DSGNode parentTemp = current;
			
			/*Debug*/printNodes("AllNodes", allNodes);
			/*Debug*/printEdges2("Edges (root node)", rootNode);
						
			while(!nodesToVisit.isEmpty() && !nodesVisited.contains(graph.findNode(nodeFinish)))
			{

				/*Debug*/System.out.println("\n");												
				/*Debug*/System.out.println("=== Part 1 === START");
				/*Debug*/printNodes("nodesVisited", nodesVisited);
				/*Debug*/printNodes("nodesToVisit", nodesToVisit);
				/*Debug*/printNodes("parent", parent);
				/*Debug*/printNodes("parentVisited", parentVisited);

				current = nodesToVisit.remove();
				nodesVisited.add(current);
				
				parentTemp = parent.remove();
				parentVisited.add(parentTemp);
				
				/*Debug*/System.out.println("\n=== Part 2 === ADDED TO PARENT VISITED / NODE VISITED");
				/*Debug*/printNodes("nodesVisited", nodesVisited);
				/*Debug*/printNodes("nodesToVisit", nodesToVisit);
				/*Debug*/printNodes("parent", parent);
				/*Debug*/printNodes("parentVisited", parentVisited);
				
				LinkedList<DSGEdge> toAdd = current.getEdges();
																					
				/*Debug*/System.out.println("\n=== Part 3 === PRINTING CURRENT EDGES");
				/*Debug*/printEdges1("Edges...", toAdd);
				
				Collections.sort(toAdd, comp);
				
				/*Debug*/System.out.println("\n=== Part 4 === SORTED");
				/*Debug*/printEdges1("Edges...", toAdd);
																					
				for (DSGEdge e : toAdd) 
				{
					if (!nodesVisited.contains(e.getTo()) && !nodesToVisit.contains(e.getTo()))
					{
						nodesToVisit.addLast(e.getTo());
						parent.addLast(current);
					}
				}
				/*Debug*/System.out.println("\n=== Part 5 === ALL THE OTHER STUFF");
				/*Debug*/printNodes("nodesVisited-", nodesVisited);
				/*Debug*/printNodes("parentVisited", parentVisited);
				/*Debug*/printNodes("nodesToVisit", nodesToVisit);
				/*Debug*/printNodes("parent", parent);
				/*Debug*/System.out.println("\n\n");
			}
			
			/*Debug*/System.out.println("Found it...");
			/*Debug*///printNodes("nodesVisited", nodesVisited);
			
			if (nodesVisited.contains(graph.findNode(nodeFinish)))
			{				
				LinkedList<DSGNode> path = new LinkedList<DSGNode>();
				
				current	= graph.findNode(nodeFinish);
				path.addFirst(current);
				int index = nodesVisited.indexOf(current);
				DSGNode currentParent = parentVisited.get(index);
				while (current != currentParent)
				{
					current = currentParent;
					index = nodesVisited.indexOf(current);
					currentParent = parentVisited.get(index);
					path.addFirst(current);
				}
				
				/*Debug*/printNodes("FINAL: ", path);
			}
			
			return null;
		}
		else
		{
			return "[X]";
		}
	}
	
	private void printNodes(String preface, LinkedList<DSGNode> list)
	{
		System.out.print(preface + ": .. ");
		for (DSGNode item : list)
		{
			if (item != null) 
			{
				System.out.print(item.getValue());
				
			}
			else {
				System.out.print("NULL");
			}
			System.out.print("-");
		}
		System.out.println();
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

}
