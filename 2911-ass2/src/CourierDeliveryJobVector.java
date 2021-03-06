/**
 * Provides the class for a "vector" that consists of  
 *  two points - a "start" point and a "finish" These
 *  two pairs of coordinates can be accessed. The distance
 *  between the coordinates of the start and finish
 *  points can be accessed. The external distance between
 *  this CourierDevlieryJobVector's "finish" coordinates and another
 *  CourierDevlieryJobVector's "start" coordinates can also be accessed.
 * 
 * @author	Hayden Charles Smith, z3418003<br />
 * 			Last modified: 19th May 2013
 */
public class CourierDeliveryJobVector implements Vector
{

	/**
	 * Constructs a CourierDevlieryJobVector given x/y coordinates for both
	 *  the origin and destination point.
	 * @param fromX x-coordinate of "start" point
	 * @param fromY y-coordinate of "start" point
	 * @param toX x-coordinate of "finish" point
	 * @param toY y-coordinate of "finish" point
	 */
	public CourierDeliveryJobVector(int fromX, int fromY, int toX, int toY)
	{
		this.xStart = fromX;
		this.yStart = fromY;
		this.xFinish = toX;
		this.yFinish = toY;
		
		// Determine internal distance
		int changeInX = Math.abs(this.getXStart() - this.getXFinish());
		int changeInY = Math.abs(this.getYStart() - this.getYFinish());
		this.internalDistance = (changeInX + changeInY);
	}
	
	/**
	 * Return vertical/horizontal distance between "start" and
	 *  "finish" x and y coordinates.
	 *  Please note this means the integer difference in x-coordinates
	 *   and integer difference in y-coordinates is calculated independently
	 *   before being added together to provide the result. 
	 * @return Sum of vertical and horizontal distances between
	 *  "destination" and "origin" x/y coordinates
	 */
	public int getInternalDistance()
	{
		return this.internalDistance;
	}
	
	/**
	 * Return distance between "finish" of one CourierDeliveryJobPoint, 
	 *  to the "start" of another CourierDeliveryJobPoint.
	 * <b>Please note this means the
	 *  integer difference in x-coordinates and integer difference
	 *  in y-coordinates is calculated independently before being
	 *  added together to provide the result.</b> 
	 * @param vectorTo CourierDeliveryJobPoint containing "origin" that requires
	 *  finding the distance to from "this" destination
	 * @return Sum of vertical and horizontal distances between "destination"s
	 *  x/y coordinates of this CourierDeliveryJobPoint, and "origin"s x/y coordinates
	 *  of another CourierDeliveryJobPoint
	 */
	public int getExternalDistanceTo(Vector vectorTo)
	{
		int changeInX = Math.abs(vectorTo.getXStart() - this.getXFinish());
		int changeInY = Math.abs(vectorTo.getYStart() - this.getYFinish());
		return (changeInX + changeInY);
	}
	
	/**
	 * Return x value of start point
	 * @return x-coordinate of "start" point
	 */
	public int getXStart()
	{
		return this.xStart;
	}
	
	/**
	 * Return y value of start point
	 * @return y-coordinate of "start" point
	 */
	public int getYStart()
	{
		return this.yStart;
	}
	
	/**
	 * Return x value of finish point
	 * @return x-coordinate of "finish" point
	 */
	public int getXFinish()
	{
		return this.xFinish;
	}
	
	/**
	 * Return y value of finish point
	 * @return y-coordinate of "finish" point
	 */
	public int getYFinish()
	{
		return this.yFinish;
	}
	
	private int xStart;
	private int yStart;
	private int xFinish;
	private int yFinish;
	private int internalDistance;

}