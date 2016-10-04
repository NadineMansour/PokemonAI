package maze;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import dataStructures.PokemonsProblem;
import dataStructures.Problem;

public class Testing {
	
	static RecursiveBacktrackerMaze mazeGrid;
	static Problem pokemonProblem;
	
	public static void main(String[] args){
		
		maze();
		pokemonProblem();
		
	}
	
	static void maze(){
		//Generate the maze;
				mazeGrid = new RecursiveBacktrackerMaze(5, 7,0,0);
				mazeGrid.generateMaze();
				PrintStream out = new PrintStream(System.out);
				mazeGrid.printMaze(out);
				out.flush();
	}
	
	static void pokemonProblem(){
		pokemonProblem = new PokemonsProblem(mazeGrid.startingPoint, mazeGrid.endingPoint, mazeGrid.timeToHatch, new ArrayList<Cell>(Arrays.asList(mazeGrid.pokemonsCells)), 3);
	}
}