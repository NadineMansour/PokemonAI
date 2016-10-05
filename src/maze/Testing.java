package maze;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dataStructures.Node;
import dataStructures.Operator;
import dataStructures.PokemonsProblem;
import dataStructures.Problem;

public class Testing {
	
	public static final int DFS = 0;
	public static final int BFS = 1;
	
	static RecursiveBacktrackerMaze mazeGrid;
	static Problem pokemonProblem;
	
	public static void main(String[] args){
		
		maze();
		//search();
		
	}
	
	static void maze(){
		//Generate the maze;
				mazeGrid = new RecursiveBacktrackerMaze(5, 7,0,0);
				mazeGrid.generateMaze();
				PrintStream out = new PrintStream(System.out);
				mazeGrid.printMaze(out);
				out.flush();
	}
	
	static void search(RecursiveBacktrackerMaze maze, String strategy){
		pokemonProblem = new PokemonsProblem(mazeGrid.startingPoint, mazeGrid.endingPoint, mazeGrid.timeToHatch, new ArrayList<Cell>(Arrays.asList(mazeGrid.pokemonsCells)), 3);
		
	}
	
	static Node generalSearch(Problem problem, int startegy){
		Queue<Node> nodes = new LinkedList<Node>();
		nodes.add(initialNode(problem));
		while (!nodes.isEmpty()) {
			Node toBeExpanded = nodes.remove();
			if(problem.passTheGoalTest(toBeExpanded.getState())){
				return toBeExpanded;
			}
			QuingFunction(nodes, expand(toBeExpanded, problem.getOperators()), startegy);
		}
		return null;
	}
	
	static Node initialNode(Problem problem){
		Node result = new Node(problem.getInitialState(),null,null,0,0);
		return result;
	}
	
	static ArrayList<Node> expand(Node node, ArrayList<Operator> operators){
		ArrayList<Node> result = new ArrayList<Node>();
		
		return result;
	}
	
	static void QuingFunction(Queue<Node> nodes, ArrayList<Node> tobeAdded, int strategy){
		switch (strategy) {
		case 1:
			DFQueing(nodes,tobeAdded);
			break;
		case 2:
			BFQueing(nodes,tobeAdded);
			break;
		case 3:
			IDQueing(nodes,tobeAdded);
			break;
		case 4:
			UCQueing(nodes,tobeAdded);
			break;
		case 5:
			GRQueing(nodes,tobeAdded);
			break;
		case 6:
			ASQueing(nodes,tobeAdded);
			break;
		default:
			break;
		}
	}
	
	static void DFQueing(Queue<Node> nodes, List<Node> tobeAdded){
		
	}
	
	static void BFQueing(Queue<Node> nodes, List<Node> tobeAdded){
		
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