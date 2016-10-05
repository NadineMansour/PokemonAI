package dataStructures;

import java.util.ArrayList;
import java.util.List;

import maze.Cell;

public class PokemonsProblem extends Problem {
	
	Cell destination;
	
	public PokemonsProblem(Cell initialState, Cell destination, int timeToHatch, List<Cell> pokemons, int direction) {
		this.destination = destination;
		this.initialState = new PokemonState(initialState,timeToHatch,pokemons,direction);
		this.operators = new ArrayList<Operator>();
		this.operators.add(new MoveForwardOperator());
		this.operators.add(new RotateOperator());
	}

	@Override
	public
	boolean passTheGoalTest(State state) {
		PokemonState pokemonState = (PokemonState)state;
		if (pokemonState.timeToHatch == 0 && pokemonState.uncollectedPokemons.size() == 0 && pokemonState.location.equal(this.destination)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public
	int pathCost(Node node) {
		return node.costFromRoot+1;
	}

}
