package dataStructures;

import java.util.List;

import maze.Cell;

public class PokemonState extends State{
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	Cell location;
	int timeToHatch;
	List<Cell> uncollectedPokemons;
	int direction;
	
	public PokemonState(){
		
	}
	
}
