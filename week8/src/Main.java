import java.util.Comparator;

/**
 * Main class, generating the Directed Graph
 * @author Timothy Wiley
 */
public class Main {
	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		// Generate Graph
		DirectedStringGraph graph = generateGraph();
		Comparator<AsearchNode> comparator = new Comparator<AsearchNode>() {
		    public int compare(AsearchNode e1, AsearchNode e2) { 
		    	if (e1.getTotalDistances() > e2.getTotalDistances()) return 1;
		    	if (e1.getTotalDistances() < e2.getTotalDistances()) return -1;
		    	else return 0;
		    }
		};
		Asearch asearch = new Asearch(graph);
		String path = asearch.findPath("Arad", "Bucharest", comparator);
		System.out.println(path);
		//System.out.println(graph);
	}

	private static DirectedStringGraph generateGraph() {
		DirectedStringGraph graph = new DirectedStringGraph();
		// 2nd parameter is "Straight line distance" to Bucharest

		// Nodes
		graph.addNode("Arad", 366);
		graph.addNode("Bucharest", 0);
		graph.addNode("Cralova", 160);
		graph.addNode("Dobreta", 242);
		graph.addNode("Eforle", 161);
		graph.addNode("Fagaras", 178);
		graph.addNode("Glurglu", 77);
		graph.addNode("Hirsova", 151);
		graph.addNode("Iasl", 226);
		graph.addNode("Lugoj", 244);
		graph.addNode("Mehadia", 241);
		graph.addNode("Neamt", 234);
		graph.addNode("Oradea", 380);
		graph.addNode("Pitesti", 98);
		graph.addNode("Rimnicu Vilcea", 193);
		graph.addNode("Sibiu", 253);
		graph.addNode("Timisoara", 329);
		graph.addNode("Urziceni", 80);
		graph.addNode("Vaslui", 199);
		graph.addNode("Zerlnd", 374);
		
		// Links
		// TODO: Implement weighted edges. When implemented the following code will work
		
		addLink(graph, "Arad", "Zerlnd", 75);
		addLink(graph, "Arad", "Timisoara", 118);
		addLink(graph, "Arad", "Sibiu", 140);
		addLink(graph, "Bucharest", "Pitesti", 101);
		addLink(graph, "Bucharest", "Fagaras", 211);
		addLink(graph, "Bucharest", "Urziceni", 85);
		addLink(graph, "Bucharest", "Glurglu", 90);
		addLink(graph, "Cralova", "Pitesti", 138);
		addLink(graph, "Cralova", "Rimnicu Vilcea", 146);
		addLink(graph, "Cralova", "Dobreta", 120);
		addLink(graph, "Dobreta", "Mehadia", 75);
		addLink(graph, "Eforle", "Hirsova", 86);
		addLink(graph, "Fagaras", "Sibiu", 99);
		addLink(graph, "Hirsova", "Urziceni", 98);
		addLink(graph, "Iasl", "Neamt", 87);
		addLink(graph, "Iasl", "Vaslui", 92);
		addLink(graph, "Lugoj", "Mehadia", 70);
		addLink(graph, "Lugoj", "Timisoara", 111);
		addLink(graph, "Oradea", "Sibiu", 151);
		addLink(graph, "Oradea", "Zerlnd", 71);
		addLink(graph, "Pitesti", "Rimnicu Vilcea", 97);
		addLink(graph, "Rimnicu Vilcea", "Sibiu", 80);
		addLink(graph, "Urziceni", "Vaslui", 142);
		
		return graph;
	}
	
	
	private static void addLink(DirectedStringGraph graph, String n1, String n2, int weight) {
		graph.addEdge(n1, n2, weight);
		graph.addEdge(n2, n1, weight);
	}
	
}
