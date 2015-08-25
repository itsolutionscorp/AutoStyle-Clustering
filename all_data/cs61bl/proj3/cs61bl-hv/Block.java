import java.awt.Point;

public class Block {

	Point topLeft;
	Point bottomRight;
	int id;

	public Block(Point one, Point two, int id) {
		topLeft = one;
		bottomRight = two;
		this.id = id;
	}
	
	public Block(Block toCopy){
		topLeft = (Point) toCopy.topLeft.clone();
		bottomRight = (Point) toCopy.bottomRight.clone();
		id = toCopy.id;
	}
	
	public String toString() {
		return topLeft.getX() + " " + topLeft.getY() + " " + bottomRight.getX()
				+ " " + bottomRight.getY() + "\n";
	}

}
