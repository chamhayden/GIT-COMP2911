public class AsearchNode {

	public AsearchNode(String name, int previousPathDistance, int straightLineDistance, AsearchNode parent)
	{
		this.name = name;
		this.parent = parent;
		this.previousPathDistance = previousPathDistance;
		this.totalDistances = previousPathDistance + straightLineDistance;
	}
	
	public boolean equals(Object name)
	{
		if (name == this.name)
		{
			return true;
		}
		return false;
	}
	
	public String getValue()
	{
		return this.name;
	}
	
	public AsearchNode getParent()
	{
		return this.parent;
	}
	
	public int getPreviousPathDistance()
	{
		return this.previousPathDistance;
	}
	
	public int getTotalDistances()
	{
		return this.totalDistances;
	}
	
	public String toString()
	{
		return "(OBJ: N "+this.name+" | P "+this.parent+" | PPD "+this.previousPathDistance+" | TD "+this.totalDistances+")";
	}
	
	private AsearchNode parent;
	private String name;
	private int previousPathDistance;
	private int totalDistances;
	
}