package maze;

import java.io.PrintStream;

public class Testing {
	public static void main(String[] args){
		RecursiveBacktrackerMaze mazeGrid = new RecursiveBacktrackerMaze(5, 7,0,0);
		mazeGrid.generateMaze();
		PrintStream out = new PrintStream(System.out);
		mazeGrid.printMaze(out);
		out.flush();
	}
}
