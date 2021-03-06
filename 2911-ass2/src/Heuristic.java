/**
 * Heuristic takes an object of type E, and
 *  provides an arbitrary/relative estimate
 *  of how close the particular object is to the 
 *  goal state.
 *  
 * @author	Hayden Charles Smith, z3418003<br />
 * 			Last modified: 19th May 2013
 * 
 * @param <E> Object to determine heuristic of
 */
public interface Heuristic<E>
{
	/**
	 * Given an object of type E, this method
	 *  returns an arbitrary integer that denotes
	 *  the relative closeness of a particular object
	 *  to a goal state
	 * @param item Object of type E to determine heuristic of
	 * @return Arbitrary integer that denotes
	 *  how relatively close a particular object of type
	 *  E is to the goal state compared to other objects
	 */
	public int getEstimate(E item);
}