package dataStructures;

import java.util.ArrayList;
import java.util.List;

import maze.Cell;

public class MoveForwardOperator extends Operator{
	
	public MoveForwardOperator(){
		
	}
	
	public State moveForward(PokemonState state){
		State result = new PokemonState();
		
		return result;
	}

	List<Cell> collectPokemon(List<Cell>pokemons, Cell location){
		List<Cell> result = new ArrayList<Cell>();
		
		return result;
	}
	
	Cell move(int direction, Cell currentLocation){
		Cell result = new Cell(0,0);
		
		return result;
	}
}
