package dataStructures;

import maze.Cell;

public class PokemonsProblem extends Problem {
	
	Cell destination;
	
	public PokemonsProblem(Cell destination) {
		this.destination = destination;
	}

	@Override
	boolean passTheGoalTest(State state) {
		PokemonState pokemonState = (PokemonState)state;
		if (pokemonState.timeToHatch == 0 && pokemonState.uncollectedPokemons.size() == 0 && pokemonState.location.equal(this.destination)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	int pathCost(Node node) {
		return node.costFromRoot+1;
	}

}
