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
			timeToHatch = state.timeToHatch --;
		}
		Cell new_location = move(state.direction,state.location);
		List<Cell> pokemons = collectPokemon(state.uncollectedPokemons, new_location);
		
		State result = new PokemonState(new_location,timeToHatch,pokemons,state.direction);
		return result;
	}

	static List<Cell> collectPokemon(List<Cell>pokemons, Cell location){
		List<Cell> result = new ArrayList<Cell>();		
			while(!pokemons.isEmpty())
			{				
				if(!pokemons.get(0).equal(location))
				{
					result.add(pokemons.remove(0));
				}
				else
					pokemons.remove(0);
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
		case 1:
			result.setX(currentLocation.getX());
			result.setY(currentLocation.getY() + 1);
			break;
		case 2:
			result.setX(currentLocation.getX() - 1);
			result.setY(currentLocation.getY());
			break;
		case 3:
			result.setX(currentLocation.getX() + 1);
			result.setY(currentLocation.getY());
			break;
			
		}
		return result;
	}
	public static void main(String[] args)
	{
		List<Cell> p = new ArrayList<Cell>();
		p.add(new Cell(1,1));
		p.add(new Cell(0,0));
		p.add(new Cell(1,2));
		
		//List<Cell> result = collectPokemon(p, new Cell(0,0));		
		
		System.out.println(move(0,p.get(0)));						
	}
}
 