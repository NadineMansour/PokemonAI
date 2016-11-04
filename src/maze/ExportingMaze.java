package maze;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;

public class ExportingMaze {
	
	public static void main(String[] args) throws IOException{
		RecursiveBacktrackerMaze mazeGrid;
		mazeGrid = new RecursiveBacktrackerMaze(3, 3,0,0);
		mazeGrid.generateMaze();
		mazeGrid.exportMaze();
		System.out.println("Exported");
	}

}
