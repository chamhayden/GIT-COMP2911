/**
 * Provides the interface for a "vector" that consists of 
 *  two points - a point "start" and a point "finish". This 
 *  interfaces allows access to these two points, 
 *  as well as providing access to the distance between these
 *  two points (length of vector), and the distance between
 *  "finish" of one point, to the "start" of a second point.
 * 
 * @author	Hayden Charles Smith, z3418003<br />
 * 			Last modified: 19th May 2013
 */
public interface Vector
{
	/**
	 * Return distance between "start" and "finish"
	 *  x and y coordinates of this Vector object
	 * @return Distance between "start" and "finish" 
	 *  coordinates
	 */
	public int getInternalDistance();
	
	/**
	 * Return distance between "finish" of this Vector, 
	 *  to the "start" of another Vector
	 * @param pointTo Vector containing "start" that requires
	 *  finding the distance to the "finish" point
	 * @return Distance between "finish" of this Vector,
	 *  and "start" of another Vector
	 */
	public int getExternalDistanceTo(Vector pointTo);
	
	/**
	 * Return x value of origin point
	 * @return x-coordinate of "start" point
	 */
	public int getXStart();
	
	/**
	 * Return y value of origin point
	 * @return y-coordinate of "start" point
	 */
	public int getYStart();
	
	/**
	 * Return x value of destination point
	 * @return x-coordinate of "finish" point
	 */
	public int getXFinish();
	
	/**
	 * Return y value of destination point
	 * @return y-coordinate of "finish" point
	 */
	public int getYFinish();	
	
}