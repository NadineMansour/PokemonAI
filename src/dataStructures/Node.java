package dataStructures;

public class Node {
	State state;
	Node parentNode;
	Operator operatorApplied;
	int depth;
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	int costFromRoot;
	
	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public Operator getOperatorApplied() {
		return operatorApplied;
	}

	public void setOperatorApplied(Operator operatorApplied) {
		this.operatorApplied = operatorApplied;
	}

	public int getCostFromRoot() {
		return costFromRoot;
	}

	public void setCostFromRoot(int costFromRoot) {
		this.costFromRoot = costFromRoot;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Node(){
		
	}
	
	public Node(State state, Node parent, Operator operator, int d, int cost){
		this.state = state;
		this.parentNode = parent;
		this.operatorApplied = operator;
		this.depth = d;
		this.costFromRoot = cost;
	}
	
	public State getState(){
		return this.state;
	}
}
