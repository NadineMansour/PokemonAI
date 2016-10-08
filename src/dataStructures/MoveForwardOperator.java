package dataStructures;

import java.util.ArrayList;
import java.util.List;

import maze.Cell;

public class MoveForwardOperator extends Operator{
	
	public MoveForwardOperator(){
		
	}
	
	public State moveForward(PokemonState state){		
		int timeToHatch = 0;
		if(state.timeToHatch > 0)
		{
			timeToHatch = state.timeToHatch-1;
		}
		Cell newLocation = move(state.direction,state.location);
		List<Cell> pokemons = collectPokemon(state.uncollectedPokemons, newLocation);
		
		State result = new PokemonState(newLocation,timeToHatch,pokemons,state.direction);
		return result;
	}

	static List<Cell> collectPokemon(List<Cell>pokemons, Cell location){
		List<Cell> result = new ArrayList<Cell>();	
		/*
			while(!pokemons.isEmpty())
			{				
				if(!pokemons.get(0).equal(location))
				{
					result.add(pokemons.remove(0));
				}
				else
					pokemons.remove(0);
			}			
		 */
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
 