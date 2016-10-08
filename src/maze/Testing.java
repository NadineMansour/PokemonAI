package maze;

import java.io.PrintStream; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	
	static RecursiveBacktrackerMaze mazeGrid;
	static Problem pokemonProblem;
	
	public static void main(String[] args){
		
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
		Node node = generalSearch(pokemonProblem, BFS);
		System.out.println(((PokemonState)node.getState()).toString());
	}
	
	static Node generalSearch(Problem problem, int startegy){
		Queue<Node> nodes = new LinkedList<Node>();
		nodes.add(initialNode(problem));
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
				System.out.println("Depth: "+node.getDepth());
				System.out.println(((PokemonState)node.getState()).toString());
			}
			System.out.println("***********************");
			i++;
		}
		return null;
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
					result.add(new Node(newState,node,operator,node.getDepth()+1,node.getCostFromRoot()+1));
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
		
	}
	
	static void BFQueing(Queue<Node> nodes, List<Node> tobeAdded){
		for (int i = 0; i < tobeAdded.size(); i++) {
			nodes.add(tobeAdded.get(i));
		}
	}
	
	static void IDQueing(Queue<Node> nodes, List<Node> tobeAdded){
		
	}
	
	static void UCQueing(Queue<Node> nodes, List<Node> tobeAdded){
		
	}
	
	static void GRQueing(Queue<Node> nodes, List<Node> tobeAdded){
		
	}
	
	static void ASQueing(Queue<Node> nodes, List<Node> tobeAdded){
		
	}
}