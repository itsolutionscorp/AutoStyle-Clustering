import java.awt.Point;
import java.util.ArrayList;

public class Piece {
	public Integer height;
	public Integer width;
	public Integer x;
	public Integer y;
	public ArrayList<Point> occupied;

	public Piece(Integer height, Integer width, Integer x, Integer y) {
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		occupied = new ArrayList<Point>();
		for (int r = y; r < y + height; r++) {
			for (int c = x; c < x + width; c++) {
				occupied.add(new Point(c, r));
			}
		}
	}

	// Convert the piece to input form
	public String convert() {
		String rtn = "";
		rtn += y.toString();
		rtn += " ";
		rtn += x.toString();
		rtn += " ";
		Integer tempY = y + height - 1;
		rtn += tempY.toString();
		rtn += " ";
		Integer tempX = x + width - 1;
		rtn += tempX.toString();
		return rtn;
	}

}
