package dataStructures;

public class Node {
	State state;
	Node parentNode;
	Operator operatorApplied;
	int depth;
	int costFromRoot;
	
	public Node(){
		
	}
	
	public Node(State state, Node parent, Operator operator, int d, int cost){
		this.state = state;
		this.parentNode = parent;
		this.operatorApplied = operator;
		this.depth = d;
		this.costFromRoot = cost;
	}
}
