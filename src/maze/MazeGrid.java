package maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public abstract class MazeGrid {
	
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	
	//Random variables
	Cell startingPoint;
	Cell endingPoint;
	int timeToHatch;
	int numberOfPokemons;
	Cell[] pokemonsCells;
	
	private Random random = new Random();
	
	private int width;
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private int height;
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private boolean[] horizntakWallsExists;
	private boolean[] verticalWallsExists;
	
	public boolean[] getHorizntakWallsExists() {
		return horizntakWallsExists;
	}

	public void setHorizntakWallsExists(boolean[] horizntakWallsExists) {
		this.horizntakWallsExists = horizntakWallsExists;
	}

	public boolean[] getVerticalWallsExists() {
		return verticalWallsExists;
	}

	public void setVerticalWallsExists(boolean[] verticalWallsExists) {
		this.verticalWallsExists = verticalWallsExists;
	}


	
	public boolean hasWall(Cell cell, int direction){
		boolean result;
		switch (direction) {
		case UP:
			result = horizntakWallsExists[cell.getY()*width + cell.getX()];
			break;
		case DOWN:
			result = horizntakWallsExists[(cell.getY()+1)*width + cell.getX()];
			break;
		case LEFT:
			result = verticalWallsExists[((width+1)*cell.getY())+cell.getX()];
			break;
		case RIGHT:
			result = verticalWallsExists[((width+1)*cell.getY())+cell.getX()+1];
			break;
		default:
			result =false;
			break;
		}
		return result;
	}
	
	public MazeGrid(int w, int h){
		this.width = w;
		this.height = h;
		
		horizntakWallsExists = new boolean[width * (height + 1)];
		verticalWallsExists  = new boolean[(width + 1) * height];
		
		Arrays.fill(horizntakWallsExists, true);
		Arrays.fill(verticalWallsExists, true);
		
		initRandoms();
	}
	
	public final void generate() {
		Arrays.fill(horizntakWallsExists, true);
		Arrays.fill(verticalWallsExists, true);
		generateMaze();
	}
	
	abstract void generateMaze();
	
	static void validDirection(int direction) {
		switch (direction) {
			case UP:
			case RIGHT:
			case DOWN:
			case LEFT:
				break;
			default:
				throw new IllegalArgumentException("Bad direction: " + direction);
		}
	}
	
	boolean validLocation(int x, int y){
		if(x<0 || x>= this.width)
			return false;
		if(y<0 || y>= this.height)
			return false;
		return true;
	}
	
	boolean createPath(int x, int y, int direction){
		validDirection(direction);
		validLocation(x, y);
		
		int index = -1;
		boolean[] wallsArrayToBeUsed = null;
		
		switch (direction) {
		case UP:
			index = y*width + x;
			wallsArrayToBeUsed = horizntakWallsExists;
			break;
		case DOWN:
			index = (y + 1)*width + x;
			wallsArrayToBeUsed = horizntakWallsExists;
			break;
		case LEFT:
			index = y*(width + 1) + x;
			wallsArrayToBeUsed = verticalWallsExists;
			break;
		case RIGHT:
			index = y*(width + 1) + (x + 1);
			wallsArrayToBeUsed = verticalWallsExists;
			break;
		default:
			break;
		}
		
		boolean oldStatus = wallsArrayToBeUsed[index];
		wallsArrayToBeUsed[index] = false;
		return oldStatus;
	}
	
	public void printMaze(PrintStream out){
		for (int y=0; y<height; y++){
			
			int rowBase = y * width;
			for(int x=0; x<width; x++) {
				out.print('*'/*'.'*/);
				out.print(horizntakWallsExists[rowBase + x] ? '-' : ' ');
			}
			out.println('*'/*'.'*/);
			
			rowBase = y*(width + 1);
			
			for (int x = 0; x < width; x++) {
				out.print(verticalWallsExists[rowBase + x] ? '|' : ' ');
				if (containsPokemon(new Cell(x,y))) {
					out.print('#');
				} else {
					out.print(' ');
				}	
			}
			out.println(verticalWallsExists[rowBase + width] ? '|' : ' ');
		}
		int rowBase = height * width;
		for (int x = 0; x < width; x++) {
			out.print('*'/*'.'*/);
			out.print(horizntakWallsExists[rowBase + x] ? '-' : ' ');
		}
		out.println('*'/*'.'*/);
		out.println("Starting point: "+startingPoint.toString());
		out.println("Ending point: "+endingPoint.toString());
		out.println("Number of pokemons: "+numberOfPokemons);
		out.println("Time needed to hatch the egg: "+timeToHatch);
		out.println("Pokemons locations");
		for (int i = 0; i < pokemonsCells.length; i++) {
			out.println(pokemonsCells[i].toString());
		}
	}
	
	public void exportMaze() throws IOException{
		String filePath = "/Users/NadineMansour/Development/AI/maze.txt";
		File f = new File(filePath);
		f.createNewFile();
		FileWriter fileWriter =  new FileWriter(filePath);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println("Nadine");
		printWriter.println("Mansour");
		printWriter.close();
	}
	private void initRandoms(){
		int startX, startY, endX, endY;
		do{
			startX = random.nextInt(width); 
			startY = random.nextInt(height); 
		} while(!validLocation(startX, startY));
		do{
			endX = random.nextInt(width); 
			endY = random.nextInt(height); 
		} while(!validLocation(endX, endY));
		startingPoint = new Cell(startX,startY);
		endingPoint = new Cell(endX,endY);
		
		numberOfPokemons = random.nextInt((width*height)/2);
		timeToHatch = random.nextInt((width*height));
		
		pokemonsCells = new Cell[numberOfPokemons];
		int createdPokemons = 0;
		do{
			boolean add = false;
			Cell location = new Cell(random.nextInt(width),random.nextInt(height));
			if (!(location.equal(startingPoint) || location.equal(endingPoint))) {
				add = true;
				for (int i = 0; i < createdPokemons; i++) {
					if (pokemonsCells[i].equal(location)) {
						add = false;
						break;
					}
				}
			}
			if (add) {
				pokemonsCells[createdPokemons] = location;
				createdPokemons++;
			}
		}while(createdPokemons<numberOfPokemons);
	}
	
	boolean containsPokemon(Cell location){
		for (int i = 0; i < pokemonsCells.length; i++) {
			if (pokemonsCells[i].equal(location)) {
				return true;
			}
		}
		return false;
	}
	
	
}
