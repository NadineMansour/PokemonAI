package maze;

public class Cell {
	private int x,y;
	//x --> col  y --> row
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Cell(int x, int y){
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	
	public boolean equal(Cell c){
		return c.x==this.x && c.y==this.y;
	}
}
