public class PartTester
{
	public static void main(String argv[]) 
	{
		Wheel wheel = new Wheel();
		Steering steering = new Steering();
		Chassis chassis = new Chassis();
		System.out.println("Wheel weight: " + wheel.getWeight());
		System.out.println("Steering weight: " + steering.getWeight());
		System.out.println("Chassis weight: " + chassis.getWeight());
	}
}
