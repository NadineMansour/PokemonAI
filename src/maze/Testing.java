package maze;

import java.io.PrintStream; 
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.management.InstanceAlreadyExistsException;

import dataStructures.MoveForwardOperator;
import dataStructures.Node;
import dataStructures.Operator;
import dataStructures.PokemonState;
import dataStructures.PokemonsProblem;
import dataStructures.Problem;
import dataStructures.RotateOperator;
import dataStructures.State;

public class Testing {
	
	public static final int DFS = 0;
	public static final int BFS = 1;
	public static final int IDS = 2;
	public static final int UCS = 3;
	
	
	static int depth;
	
	static RecursiveBacktrackerMaze mazeGrid;
	static Problem pokemonProblem;
	
	public static void main(String[] args){
		depth = 0;
		maze();
		search(mazeGrid, 1);
		PrintStream out = new PrintStream(System.out);
		mazeGrid.printMaze(out);
		out.flush();
	}
	
	static void maze(){
		//Generate the maze;
				mazeGrid = new RecursiveBacktrackerMaze(3, 2,0,0);
				mazeGrid.generateMaze();
	}
	
	static void search(RecursiveBacktrackerMaze maze, int strategy){
		pokemonProblem = new PokemonsProblem(mazeGrid.startingPoint, mazeGrid.endingPoint, mazeGrid.timeToHatch, new ArrayList<Cell>(Arrays.asList(mazeGrid.pokemonsCells)), 3);
		Node node = generalSearch(pokemonProblem, DFS);
		ArrayList<Node> backTracking = new ArrayList<Node>();
		backTrack(backTracking,node);
		System.out.println("Backtracking size "+backTracking.size());
		for (Node node2 : backTracking) {
			System.out.println(node2.getState());
			System.out.println("Cost: "+node2.getCostFromRoot());
			System.out.println("Depth: "+node2.getDepth());
			if (node2.getOperatorApplied()!=null) {
				System.out.println("Operator"+ node2.getOperatorApplied().getClass());
			} else {
				System.out.println("Operator NULL");
			}
			
			System.out.println("#################");
		}
		//System.out.println(((PokemonState)node.getState()).toString());
	}
	
	static void backTrack(ArrayList<Node> result, Node node){
		if (node.getParentNode() == null) {
			result.add(node);
			return;
		} else {
			result.add(node);
			backTrack(result,node.getParentNode());
		}
	}
	
	static Node generalSearch(Problem problem, int startegy){
		Queue<Node> nodes = new LinkedList<Node>();
		Node initialNode = initialNode(problem);
		nodes.add(initialNode);
		depth = 0;
		if(startegy == IDS)
		{
			while(true)
			{
				Node toBeExpanded;
				if(nodes.isEmpty())
				{
					nodes.add(initialNode);
					toBeExpanded = initialNode;
					depth++;
				}
				else
				{
					toBeExpanded = nodes.remove();
				}
				
				if(problem.passTheGoalTest(toBeExpanded.getState()))
				{
					return toBeExpanded;
				}
				System.out.println("TO BE EXPANDED:");
				System.out.println((PokemonState)toBeExpanded.getState());
				QuingFunction(nodes, expand(toBeExpanded, problem.getOperators()), startegy);
			}
		}
		else
		{
			int i = 0;
			while (!nodes.isEmpty()) {
				Node toBeExpanded = nodes.remove();
				if(problem.passTheGoalTest(toBeExpanded.getState())){
					return toBeExpanded;
				}
				System.out.println("TO BE EXPANDED:");
				System.out.println((PokemonState)toBeExpanded.getState());
				QuingFunction(nodes, expand(toBeExpanded, problem.getOperators()), startegy);
				for (Node node : nodes) {
					//System.out.println("Depth: "+node.getDepth());
					//System.out.println("Cost: "+node.getCostFromRoot());
					//System.out.println(((PokemonState)node.getState()).toString());
				}
				System.out.println("***********************");
				System.out.println("Final nodes size GS: "+nodes.size());
				i++;
			}
			return null;
		}		
	}
	
	static Node initialNode(Problem problem){
		Node result = new Node(problem.getInitialState(),null,null,0,0);
		return result;
	}
	
	static ArrayList<Node> expand(Node node, ArrayList<Operator> operators){
		ArrayList<Node> result = new ArrayList<Node>();
		//Question --> case specific 
		for (Operator operator : operators) {
			if (operator instanceof MoveForwardOperator) {
				PokemonState state = ((PokemonState)node.getState());
				if (!mazeGrid.hasWall(state.location,state.direction)) {
					State newState = ((MoveForwardOperator) operator).moveForward(state);
					result.add(new Node(newState,node,operator,node.getDepth()+1,node.getCostFromRoot()+2));
				}
			} else if(operator instanceof RotateOperator) {
				State newState = ((RotateOperator) operator).rotate((PokemonState)node.getState(), true, false);
				result.add(new Node(newState,node,operator,node.getDepth()+1,node.getCostFromRoot()+1));
				
				newState = ((RotateOperator) operator).rotate((PokemonState)node.getState(), false, true);
				result.add(new Node(newState,node,operator,node.getDepth()+1,node.getCostFromRoot()+1));
			}
		}
		return result;
	}
	
	
	static void QuingFunction(Queue<Node> nodes, ArrayList<Node> tobeAdded, int strategy){
		switch (strategy) {
		case 0:
			DFQueing(nodes,tobeAdded);
			break;
		case 1:
			BFQueing(nodes,tobeAdded);
			break;
		case 2:
			IDQueing(nodes,tobeAdded);
			break;
		case 3:
			UCQueing(nodes,tobeAdded);
			break;
		case 4:
			GRQueing(nodes,tobeAdded);
			break;
		case 5:
			ASQueing(nodes,tobeAdded);
			break;
		default:
			break;
		}
	}
	
	static void DFQueing(Queue<Node> nodes, List<Node> tobeAdded){
		Queue<Node> temp = new LinkedList<Node>();
		while(!nodes.isEmpty())
		{			
			temp.add(nodes.remove());			
		}
		
		for(int i = 0; i < tobeAdded.size();i++)
		{
			nodes.add(tobeAdded.get(i));
		}
		
		while(!temp.isEmpty())
		{
			nodes.add(temp.remove());		
		}
	}
	
	static void BFQueing(Queue<Node> nodes, List<Node> tobeAdded){
		for (int i = 0; i < tobeAdded.size(); i++) {
			nodes.add(tobeAdded.get(i));
		}
	}
	
	static void IDQueing(Queue<Node> nodes, List<Node> tobeAdded){
		Queue<Node> temp = new LinkedList<Node>();		
		while(!nodes.isEmpty())		
			temp.add(nodes.remove());
			
		while(!tobeAdded.isEmpty())
		{			
			Node currentNode = tobeAdded.remove(0);			
			if(currentNode.getDepth() <= depth)
			{
				nodes.add(currentNode);							
			}
		}
		
		while(!temp.isEmpty())
		{
			nodes.add(temp.remove());
		}
	}
	
	static void UCQueing(Queue<Node> nodes, List<Node> tobeAdded){
		System.out.println("The number of nodes to be added:- "+tobeAdded.size());
		Queue<Node> temporaryNodes = new LinkedList<Node>();
		for (Node node : tobeAdded) {
			System.out.println("__"+node.getCostFromRoot());
			if (nodes.size() == 0) {
				nodes.add(node);
				System.out.println("Node added in the begining");
			} else {
				while(!nodes.isEmpty()){
					Node firstNodeInTheQueue = nodes.peek();
					if (firstNodeInTheQueue.getCostFromRoot()<= node.getCostFromRoot()) {
						temporaryNodes.add(nodes.remove());
					} else {
						temporaryNodes.add(node);
						System.out.println("Node added in the middle");
						break;
					}
				}
				if (nodes.isEmpty()) {
					temporaryNodes.add(node);
					System.out.println("Node added in the end");
				} else {
					temporaryNodes.addAll(nodes);
					nodes.clear();
					System.out.println("Adding the rest of the nodes");
				}
			}
			nodes.addAll(temporaryNodes);
			temporaryNodes.clear();
		}
		System.out.println("Final nodes size: "+nodes.size());
	}
	
	static void GRQueing(Queue<Node> nodes, List<Node> tobeAdded){
		
	}
	
	static void ASQueing(Queue<Node> nodes, List<Node> tobeAdded){
		
	}
}