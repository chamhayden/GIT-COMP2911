public class Chassis implements Part
{
	public Chassis()
	{
		wheel = new Wheel();
		steering = new Steering();
	}
	
	public int getWeight() 
	{
		return steering.getWeight() + 4 * wheel.getWeight();
	}
	
	private Wheel wheel;
	private Steering steering;
}
