package dataStructures;

public class Node implements Comparable<Node> {
	
	private State state;
	private Node parentNode;
	private Operator operatorApplied;
	private int depth;
	private int costFromRoot;
	private int comparableValue;
	
	public Node(){
		
	}

	public Node(State state, Node parent, Operator operator, int d, int cost){
		this.state = state;
		this.parentNode = parent;
		this.operatorApplied = operator;
		this.depth = d;
		this.costFromRoot = cost;
	}
	
	public Node(State state, Node parent, Operator operator, int d, int cost, int value){
		this.state = state;
		this.parentNode = parent;
		this.operatorApplied = operator;
		this.depth = d;
		this.costFromRoot = cost;
		this.comparableValue = value;
	}
	
	public int getComparableValue() {
		return comparableValue;
	}

	public void setComparableValue(int comparableValue) {
		this.comparableValue = comparableValue;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
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
	
	@Override
	public String toString() {
		return "Node [state=" + (PokemonState)state + ", operatorApplied=" + operatorApplied
				+ ", depth=" + depth + ", costFromRoot=" + costFromRoot + "CompareValue: "+comparableValue +"]";
	}

	public State getState(){
		return this.state;
	}

	@Override
	public int compareTo(Node o) {
		if(this.comparableValue<o.comparableValue)
			return -1;
		else if (this.comparableValue>o.comparableValue){
			return 1;
		} else{
			return 0;
		}
	}	
}
