package dataStructures;

import java.util.ArrayList;
import java.util.List;

import maze.Cell;

public class MoveForwardOperator extends Operator{
	
	public MoveForwardOperator(){
		
	}
	
	public State moveForward(PokemonState state){		
		int timeToHatch = 0;
		if(state.getTimeToHatch() > 0)
		{
			timeToHatch = state.getTimeToHatch()-1;
		}
		Cell newLocation = move(state.getDirection(),state.getLocation());
		List<Cell> pokemons = collectPokemon(state.getUncollectedPokemons(), newLocation);
		
		State result = new PokemonState(newLocation,timeToHatch,pokemons,state.getDirection());
		return result;
	}

	static List<Cell> collectPokemon(List<Cell>pokemons, Cell location){
		List<Cell> result = new ArrayList<Cell>();	
		for (int i = 0; i < pokemons.size(); i++) {
			if (!pokemons.get(i).equal(location)) {
				result.add(pokemons.get(i));
			}
		}
		return result;
	}
	
	static Cell move(int direction, Cell currentLocation){
		Cell result = new Cell(0,0);
		switch(direction)
		{
		case 0:
			result.setX(currentLocation.getX());
			result.setY(currentLocation.getY() - 1);
			break;
		case 2:
			result.setX(currentLocation.getX());
			result.setY(currentLocation.getY() + 1);
			break;
		case 3:
			result.setX(currentLocation.getX() - 1);
			result.setY(currentLocation.getY());
			break;
		case 1:
			result.setX(currentLocation.getX() + 1);
			result.setY(currentLocation.getY());
			break;
			
		}
		return result;
	}
}
 