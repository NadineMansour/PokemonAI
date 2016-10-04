package maze;

import java.util.LinkedList;
import java.util.Random;

public class RecursiveBacktrackerMaze extends MazeGrid{
	
	private int initX, initY;
	private Random random = new Random();
	
	public RecursiveBacktrackerMaze(int width, int height){
		super(width, height);
		this.initX = random.nextInt(width);
		this.initY = random.nextInt(height);
	}
	
	public RecursiveBacktrackerMaze(int width, int height, int initX, int initY){
		super(width, height);
		validLocation(initX, initY);
		
		this.initX = initX;
		this.initY = initY;
	}
	
	@Override
	void generateMaze() {
		// TODO Auto-generated method stub
		int width = getWidth();
		int height = getHeight();
		
		boolean []cellsVisisted = new boolean[width*height];
		LinkedList<Cell> stack = new LinkedList<Cell>();
		
		Cell currentCell = new Cell(initX, initY);
		stack.addFirst(currentCell);
		
		int[] neighboursCells = new int[4];
		
		do{
			cellsVisisted[currentCell.getY()*width + currentCell.getX()] = true;
			
			int freeNeighbourCount = 0;
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case UP:
					if (currentCell.getY() > 0 && !cellsVisisted[(currentCell.getY() - 1)*width + currentCell.getX()]) {
						neighboursCells[freeNeighbourCount++] = i;
					}
					break;
				case DOWN:
					if (currentCell.getY() < height - 1 && !cellsVisisted[(currentCell.getY() + 1)*width + currentCell.getX()]) {
						neighboursCells[freeNeighbourCount++] = i;
					}
					break;
				case LEFT:
					if (currentCell.getX() > 0 && !cellsVisisted[currentCell.getY()*width + (currentCell.getX() - 1)]) {
						neighboursCells[freeNeighbourCount++] = i;
					}
					break;
				case RIGHT:
					if (currentCell.getX() < width - 1 && !cellsVisisted[currentCell.getY()*width + (currentCell.getX() + 1)]) {
						neighboursCells[freeNeighbourCount++] = i;
					}
					break;
				default:
					break;
				}
			}
			
			if (freeNeighbourCount > 0){
				stack.addFirst(currentCell);
				currentCell = new Cell(currentCell.getX(),currentCell.getY());
				switch (neighboursCells[random.nextInt(freeNeighbourCount)]) {
					case UP:
						createPath(currentCell.getX(), currentCell.getY(), UP);
						currentCell.setY(currentCell.getY()-1);
					break;
					case DOWN:
						createPath(currentCell.getX(), currentCell.getY(), DOWN);
						currentCell.setY(currentCell.getY()+1);
					break;
					case LEFT:
						createPath(currentCell.getX(), currentCell.getY(), LEFT);
						currentCell.setX(currentCell.getX()-1);
					break;
					case RIGHT:
						createPath(currentCell.getX(), currentCell.getY(), RIGHT);
						currentCell.setX(currentCell.getX()+1);
					break;
				}
			} else{
				currentCell = stack.removeFirst();
			}	
		}while(!stack.isEmpty());
	}
}
