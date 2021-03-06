import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;

public abstract class AStar {
	
	public AStar(Heuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	public ProblemState findSolution() {
		ProblemState start = getStartState();
		return findAnswerFrom(start);
	}
	
	private ProblemState findAnswerFrom(ProblemState start) {
		ProblemState solution = null;
		int statesRemoved = 0;
		Comparator<ProblemState> comparator = getComparator();
		int initialCapacity = getInitialCapacity();
		PriorityQueue<ProblemState> queue = new PriorityQueue<ProblemState>(initialCapacity, comparator);
		ArrayList<ProblemState> visited = new ArrayList<ProblemState>();
		queue.add(start);
		boolean reachedGoal = false;
		int loop = 0;
		while(!queue.isEmpty() && reachedGoal != true) {
			
			//remove this
			if(queue.size() > 1) {
			//System.out.println("Printing the queue");
			for(ProblemState state : queue) {
				//System.out.println(state.getNodeObj().getXStart() + ", " + ((CourierDeliveryProblemState) state).getNodeObj().getYStart() + " with distance of " + state.getDistanceToState() + " heuristic of " + heuristic.getEstimateToGoal(state) + " total is " + (heuristic.getEstimateToGoal(state) + state.getDistanceToState()));
			}
			}
			
			
			ProblemState current = queue.poll();
			statesRemoved++;
			
			//remove this
			if(queue.size() > 1) {
			//System.out.println("taking " + ((CourierDeliveryProblemState) current).getNodeObj().getXStart() + ", " + ((CourierDeliveryProblemState) current).getNodeObj().getYStart() + " off the queue, total distance travelled is " + ((CourierDeliveryProblemState) current).getDistanceToState() + " estimate is " + heuristic.getEstimateToGoal(current));
			//System.out.println("jobs remaining are ");
				for(Node job : current.getNodesRemaining()) {
				//	System.out.println(job.getXStart() + ", " + job.getYStart());
				}
			} else {
				//System.out.println("taking initial state off the queue");
			}
			
			
			if(!prunedState(current, visited)) {
				
				visited.add(current);
				
				//remove this
				//System.out.println("visited now contains");
				for(ProblemState what : visited) {
					if(what.getNodeObj() != null) {
					//System.out.println(((CourierDeliveryProblemState) what).getJob().getXStart() + ", " + ((CourierDeliveryProblemState) what).getJob().getYStart());
					}
				} 
				//System.out.println("done");
				
				
				if(isAnswer(current)) {
					System.out.println("**** IS THE ANSWER - YAY :)");
					reachedGoal = true;
					current.setTotalStatesRemoved(statesRemoved);
					solution = current;
				} else {
					LinkedList<ProblemState> children = getChildren(current);
					for(ProblemState state : children) {
						queue.add(state);
					}
				}
			}
			else
			{
				loop++;
			}
		}
		System.out.println("LOOPS: " + loop);
		return solution;
	}
	
	public abstract ProblemState getStartState();
	
	public abstract Comparator<ProblemState> getComparator();
	
	public abstract int getInitialCapacity();
	
	public abstract boolean isAnswer(ProblemState state);
		
	public abstract LinkedList<ProblemState> getChildren(ProblemState state);
	
	public abstract boolean prunedState(ProblemState state, ArrayList<ProblemState> visited);
	
	protected Heuristic heuristic;
}
