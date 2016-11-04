package maze;

import java.io.PrintStream; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
	public static final int IDS = 2;
	public static final int UCS = 3;
	public static final int GS  = 4;
	public static final int AS  = 5;

	public static final int MANHATTAN = 1;
	public static final int EUCLIDEAN = 2;
	public static final int H1 = 3;
	public static final int H2 = 4;
	
	static int counter;
	static int depth;
	
	static RecursiveBacktrackerMaze mazeGrid;
	static Problem pokemonProblem;
	static Hashtable<String, Integer> expandedStates;
	
	public static void main(String[] args){
		depth = 0;
		expandedStates = new Hashtable<String, Integer>();
		maze();
		PrintStream out = new PrintStream(System.out);
		mazeGrid.printMaze(out);
		out.flush();
		search(mazeGrid, true);	
	}
	
	static void maze(){
		//Generate the maze;
		mazeGrid = new RecursiveBacktrackerMaze(3, 3,0,0);
		mazeGrid.generateMaze();
	}
	
	static Node initialNode(Problem problem){
		Node result = new Node(problem.getInitialState(),null,null,0,0);
		return result;
	}
	
	static String strategyName(int startegy){
		switch (startegy) {
		case 0:
			return "BFS";
		case 1:
			return "DFS";
		case 2:
			return "IDS";
		case 3:
			return "UCS";
		case 4:
			return "GS";
		case 5:
			return "AS";
		default:
			return "ERROR";
		}
	}
	
	static String hName(int h){
		switch (h) {
		case 1:
			return "MANHATTAN";
		case 2:
			return "EUCLIDEAN";
		case 3:
			return "H1";
		case 4:
			return "H2";
		default:
			return "ERROR";
		}
	}
	
	static void search(RecursiveBacktrackerMaze maze, boolean draw){
		pokemonProblem = new PokemonsProblem(mazeGrid.startingPoint, mazeGrid.endingPoint, mazeGrid.timeToHatch, new ArrayList<Cell>(Arrays.asList(mazeGrid.pokemonsCells)), 3);
		ArrayList<Node> backTracking = new ArrayList<Node>();
		int[] startegies = {0,1,2,3,4,5};
		int[] heuristics = {1,2,3,4};
		
		for (int i = 0; i < startegies.length; i++) {
			counter =0;
			
			String startegyName  = strategyName(i);
			if (i == 4 || i == 5) {
				for (int j = 0; j < heuristics.length; j++) {
					counter =0;
					String HName = hName(heuristics[j]);
					System.out.println(startegyName);
					System.out.println(HName);
					Node nodeResult = generalSearch(pokemonProblem, startegies[i],heuristics[j]);
					if (nodeResult == null) {
						System.out.println("No solution");
					} else {
						System.out.println("Number of expanded nodes: "+counter);
						backTrack(backTracking,nodeResult);
						System.out.println("Cost: "+nodeResult.getCostFromRoot());
						for (int n = backTracking.size()-1 ;n>=0;n--) {
							if (n == 0) {
								//printMaze(new PrintStream(System.out), backTracking.get(n));
							}
							expandedStates.clear();
						}
					}
					System.out.println("$$$$$$$$$$$$$$$$$$$");
					
				}
			} else{
				System.out.println(startegyName);
				Node nodeResult = generalSearch(pokemonProblem, startegies[i],-1);
				if (nodeResult == null) {
					System.out.println("No solution");
				} else {
					System.out.println("Number of expanded nodes: "+counter);
					System.out.println("Cost: "+nodeResult.getCostFromRoot());
					backTrack(backTracking,nodeResult);
					for (int n = backTracking.size()-1 ;n>=0;n--) {
						if (n ==  0) {
							//printMaze(new PrintStream(System.out), backTracking.get(n));
						}
						expandedStates.clear();
					}
				}
				System.out.println("$$$$$$$$$$$$$$$$$$$");
			}
		}
	}
	
	static Node generalSearch(Problem problem, int startegy, int heuristicValue){
		
		LinkedList<Node> nodes = new LinkedList<Node>();

		
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
					expandedStates.clear();
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
				QuingFunction(nodes, smartExpand(toBeExpanded, problem.getOperators(),startegy,heuristicValue), startegy);
			}
		}
		else
		{
			while (!nodes.isEmpty()) {
				Node toBeExpanded = nodes.removeFirst();
				if(problem.passTheGoalTest(toBeExpanded.getState())){
					return toBeExpanded;
				}
				QuingFunction(nodes, smartExpand(toBeExpanded, problem.getOperators(), startegy, heuristicValue), startegy);
				if(!(startegy == BFS || startegy == DFS || startegy == IDS))
					Collections.sort(nodes);
				    
			}
			return null;
		}		
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
	
	static Node prepareNewNode(Node node, Operator operator){
		Node result =  new Node();
		
		return result;
	}
	
	static ArrayList<Node> smartExpand(Node node, ArrayList<Operator> operators, int strategy, int heuristicType){
		counter++;
		ArrayList<Node> result = new ArrayList<Node>();
		PokemonState currentState = ((PokemonState)(node.getState()));
		for (Operator operator : operators) {
			if (operator instanceof MoveForwardOperator) {
				if (!mazeGrid.hasWall(currentState.getLocation(),currentState.getDirection())) {
					State newState = ((MoveForwardOperator) operator).moveForward(currentState);
					Node newNode = new Node(newState,node,operator,node.getDepth()+1,node.getCostFromRoot()+2);
					int value=0;
					if (strategy == UCS) {
						value = newNode.getCostFromRoot();
					} else if(strategy == AS){
						value = newNode.getCostFromRoot()+getHeuristicValueFromNode(newNode,heuristicType);
					} else if(strategy == GS){
						value = getHeuristicValueFromNode(newNode,heuristicType);
					}
					newNode.setComparableValue(value);
					String index = ((PokemonState) newState).index();
					if (expandedStates.containsKey(index)) {
						int cost = expandedStates.get(index);
						if(cost>newNode.getCostFromRoot()){
							result.add(newNode);
							expandedStates.put(index, newNode.getCostFromRoot());
						}
					} else {
						result.add(newNode);
						expandedStates.put(index, newNode.getCostFromRoot());
					}
				}
			} else if(operator instanceof RotateOperator) {
					State newStateLeft = ((RotateOperator) operator).rotate(currentState, true, false);
					Node newNode = new Node(newStateLeft,node,operator,node.getDepth()+1,node.getCostFromRoot()+1);
					int value=0;
					if (strategy == UCS) {
						value = newNode.getCostFromRoot();
					} else if(strategy == AS){
						value = newNode.getCostFromRoot()+getHeuristicValueFromNode(newNode,heuristicType);
					} else if(strategy == GS){
						value = getHeuristicValueFromNode(newNode,heuristicType);
					}
					newNode.setComparableValue(value);
					String index = ((PokemonState) newStateLeft).index();
					if (expandedStates.containsKey(index)) {
						int cost = expandedStates.get(index);
						if(cost>newNode.getCostFromRoot()){
							result.add(newNode);
							expandedStates.put(index, newNode.getCostFromRoot());
						}
					} else {
						result.add(newNode);
						expandedStates.put(index, newNode.getCostFromRoot());
					}
					State newStateRight = ((RotateOperator) operator).rotate(currentState, false, true);
					newNode = new Node(newStateRight,node,operator,node.getDepth()+1,node.getCostFromRoot()+1);
					value=0;
					if (strategy == UCS) {
						value = newNode.getCostFromRoot();
					} else if(strategy == AS){
						value = newNode.getCostFromRoot()+getHeuristicValueFromNode(newNode,MANHATTAN);
					}
					newNode.setComparableValue(value);
					index = ((PokemonState) newStateRight).index();
					if (expandedStates.containsKey(index)) {
						int cost = expandedStates.get(index);
						if(cost>newNode.getCostFromRoot()){
							result.add(newNode);
							expandedStates.put(index, newNode.getCostFromRoot());
						}
					} else {
						result.add(newNode);
						expandedStates.put(index, newNode.getCostFromRoot());
					}
					
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
		for (Node node : tobeAdded) {
			nodes.add(node);
		}
	}
	
	static void GRQueing(Queue<Node> nodes, List<Node> tobeAdded){
		for (Node node : tobeAdded) {
			nodes.add(node);
		}
	}
	
	static void ASQueing(Queue<Node> nodes, List<Node> tobeAdded){
		for (Node node : tobeAdded) {
			nodes.add(node);
		}
	}
	
	static int getHeuristicValueFromNode(Node node, int type){
		int result = -100000;
		PokemonState pokemonState = (PokemonState)node.getState();
		switch (type) {
		case MANHATTAN:
			result = manhattanDistance(((PokemonsProblem)pokemonProblem).getDestination(), pokemonState.getLocation());
			break;
		case EUCLIDEAN:
			result = euclideanDistance(((PokemonsProblem)pokemonProblem).getDestination(), pokemonState.getLocation());
			break;
		case H1:
			result = h1(node);
			break;
		case H2:
			break;
		default:
			result = h2(node);
			break;
		}
		return result;
	}
	
	static int euclideanDistance(Cell cell1, Cell cell2){
		int result = (int)(Math.sqrt(Math.pow(cell2.getX() - cell1.getX(), 2) + Math.pow(cell2.getY() - cell1.getY(),2)));
		return result;
	}
	
	static int manhattanDistance (Cell cell1, Cell cell2){
		int result = Math.abs(cell2.getX() - cell1.getX()) + Math.abs(cell2.getY() - cell1.getY());
		return result;
	}
	
	static int h1(Node node){
		int result = getHeuristicValueFromNode(node,MANHATTAN);
		PokemonState state = ((PokemonState)node.getState());
		result+= state.getTimeToHatch();
		result+= state.numberOfUncollectedPokemons();
		return result;
	}
	
	static int h2(Node node){
		int result = getHeuristicValueFromNode(node,H1);
		//Add the distance to the closest pokemon
		PokemonState state = ((PokemonState)node.getState());
		ArrayList<Integer> distances = new ArrayList<Integer>();
		for (Cell location : state.getUncollectedPokemons()) {
			distances.add(manhattanDistance(state.getLocation(), location));
		}
		if (distances.isEmpty()) {
			result+= Collections.min(distances);
		}
		return result;
	}
	
	public static void printMaze(PrintStream out, Node node){
		int height = mazeGrid.getHeight();
		int width = mazeGrid.getWidth();
		boolean [] horizntakWallsExists = mazeGrid.getHorizntakWallsExists();
		boolean [] verticalWallsExists = mazeGrid.getVerticalWallsExists();
		PokemonState state = (PokemonState) node.getState();
		for (int y=0; y<height; y++){
			
			int rowBase = y * width;
			for(int x=0; x<width; x++) {
				out.print('*'/*'.'*/);
				out.print(horizntakWallsExists[rowBase + x] ? '-' : ' ');
			}
			out.println('*'/*'.'*/);
			
			rowBase = y*(width + 1);
			
			for (int x = 0; x < width; x++) {
				out.print(verticalWallsExists[rowBase + x] ? '|' : ' ');
				if (mazeGrid.containsPokemon(new Cell(x,y))) {
					out.print('#');
				} else if(state.getLocation().equal(new Cell(x,y))){
					out.print('P');
				}else {
					out.print(' ');
				}	
			}
			out.println(verticalWallsExists[rowBase + width] ? '|' : ' ');
		}
		int rowBase = height * width;
		for (int x = 0; x < width; x++) {
			out.print('*'/*'.'*/);
			out.print(horizntakWallsExists[rowBase + x] ? '-' : ' ');
		}
		out.println('*'/*'.'*/);
		out.println("Direction: "+state.getDirection());
		out.println("Number of pokemons: "+state.getUncollectedPokemons().size());
		out.println("Time needed to hatch the egg: "+state.getTimeToHatch());

	}
	
}