package dataStructures;

import java.util.List;

import maze.Cell;

public class PokemonState extends State{
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	private Cell location;
	private int timeToHatch;
	private List<Cell> uncollectedPokemons;
	private int direction;

	public PokemonState(){
		
	}
	
	public PokemonState(Cell location, int timeToHatch, List<Cell> pokemons, int direction){
		this.location = location;
		this.timeToHatch = timeToHatch;
		this.uncollectedPokemons = pokemons;
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public Cell getLocation() {
		return location;
	}

	public void setLocation(Cell location) {
		this.location = location;
	}

	public int getTimeToHatch() {
		return timeToHatch;
	}
	public void setTimeToHatch(int timeToHatch) {
		this.timeToHatch = timeToHatch;
	}
	
	public int numberOfUncollectedPokemons(){
		return this.uncollectedPokemons.size();
	}
	
	public List<Cell> getUncollectedPokemons() {
		return uncollectedPokemons;
	}

	public void setUncollectedPokemons(List<Cell> uncollectedPokemons) {
		this.uncollectedPokemons = uncollectedPokemons;
	}
	
	@Override
	public String toString() {
		return "PokemonState [location=" + location + ", timeToHatch="
				+ timeToHatch + ", uncollectedPokemons=" + uncollectedPokemons.size()
				+ ", direction=" + direction + "]";
	}
	
	public String index(){
		return location.getX()+" "+location.getY()+" "+direction+" "+timeToHatch+" "+uncollectedPokemons.size();
	}
	
	
}
