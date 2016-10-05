package dataStructures;

import java.util.List;

import maze.Cell;

public class PokemonState extends State{
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public Cell location;
	public int timeToHatch;
	public List<Cell> uncollectedPokemons;
	public int direction;
	
	public PokemonState(){
		
	}
	
	public PokemonState(Cell location, int timeToHatch, List<Cell> pokemons, int direction){
		this.location = location;
		this.timeToHatch = timeToHatch;
		this.uncollectedPokemons = pokemons;
		this.direction = direction;
	}
}
