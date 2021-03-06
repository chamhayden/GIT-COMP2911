
import java.util.LinkedList;

/**
 * Represents a node in the graph and stores all outgoing edges from that node
 * @author Timothy Wiley
 */
public class DSGNode {
	private final String value;
	private LinkedList<DSGEdge> edges;

	/**
	 * Construct a node given the String value of the node
	 * @param item String value
	 */
	public DSGNode(String item) {
		this.value = item;
		edges = new LinkedList<DSGEdge>();
	}
	
	public String getValue() {
		return value;
	}

	public LinkedList<DSGEdge> getEdges() {
		return edges;
	}

	@Override
	/**
	 * This object is equal to obj if
	 * 	- obj is a DSGNode and this.value == other.value
	 *  - obj is a String and this.value == (String) obj
	 */
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;

		if (obj.getClass() == String.class) {
			return value.equals(obj);
		} else if (getClass() == obj.getClass()) {
			DSGNode other = (DSGNode) obj;
			return value.equals(other.value);
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		string.append(value + ":\n");
		for(DSGEdge edge : edges) {
			string.append("\t-> " + edge.getTo().getValue() + " (Weight: "+edge.getWeight()+")\n");
		}
		return string.toString();
	}
}