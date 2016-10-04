package dataStructures;

import java.util.List;

public abstract class Problem {
	List<Operator> operators;
	State initialState;
	
	abstract boolean passTheGoalTest(State state);
	abstract int pathCost(Node node);
}
