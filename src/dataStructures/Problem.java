package dataStructures;

import java.util.ArrayList;

public abstract class Problem {
	ArrayList<Operator> operators;
	State initialState;
	
	abstract public boolean passTheGoalTest(State state);
	abstract public int pathCost(Node node);
	
	public State getInitialState(){
		return this.initialState;
	}
	
	public ArrayList<Operator> getOperators(){
		return this.operators;
	}
}
