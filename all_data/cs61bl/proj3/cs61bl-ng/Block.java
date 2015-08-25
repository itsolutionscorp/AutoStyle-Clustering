import java.awt.Point;

public class Block {

	private Point start;
	private Point finish;
	private int length;
	private int width;

	public Block(Point startPoint, Point finishPoint) {
		start = startPoint;
		finish = finishPoint;
		length = finish.y - start.y + 1;
		width = finish.x - start.x + 1;
	}

	public Point getStart() {
		return start;
	}

	public Point getFinish() {
		return finish;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public void move(Point one, Point two) {
		start = one;
		finish = two;
	}

	public int hashCode() {
		return start.x + start.y * 2 + finish.x * 3 + finish.y * 4;
	}

	public boolean equals(Object bk) {
		Block block = (Block) bk;
		return block.start.equals(start) && block.finish.equals(finish);
	}

}