/**
 * Represents an Edge in the graph as going between two nodes
 * @author Timothy Wiley
 */
public class DSGEdge {
	private final DSGNode from;
	private final DSGNode to;
	private final int weight;

	/**
	 * Construct an edge given two nodes
	 * @param from Node the edge is from
	 * @param to Node the edge is to
	 * @param weight Weight of the edge
	 */
	public DSGEdge(DSGNode from, DSGNode to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public DSGNode getFrom() {
		return from;
	}

	public DSGNode getTo() {
		return to;
	}
	
	public int getWeight() {
		return weight;
	}

	@Override
	/**
	 * Two edges are equal if their from.equal() and to.equal() return true
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		DSGEdge other = (DSGEdge) obj;
		return from.equals(other.from) && to.equals(other.to);
	}
	
	@Override
	public String toString() {
		return from.getValue() + " -> " + to.getValue();
	}
}