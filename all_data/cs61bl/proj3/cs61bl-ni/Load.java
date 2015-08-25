import java.awt.Point;
import java.io.*;

public class Load {

	private Board board;

	public Load(String inputfile, int numberOfRows, int numberOfColumns) {
		int numrows = 0;
		int numcols = 0;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(inputfile)));
			if (numberOfRows == 0 && numberOfColumns == 0) {
				String inputdimension = in.readLine();
				String[] dimension = inputdimension.split(" ");
				if (dimension.length != 2) {
					throw new Exception();
				}
				numrows = Integer.parseInt(dimension[0]);
				numcols = Integer.parseInt(dimension[1]);

			} else {
				numrows = numberOfRows;
				numcols = numberOfColumns;
			}
			board = new Board(numrows, numcols);
			while (true) {
				String nextline = in.readLine();
				if (nextline == null) {
					break;
				} else {
					String[] point = nextline.split(" ");
					if (point.length != 4) {
						throw new Exception();
					}
					Point p1 = new Point(Integer.parseInt(point[1]), Integer.parseInt(point[0]));
					Point p2 = new Point(Integer.parseInt(point[3]), Integer.parseInt(point[2]));
					Block a = new Block(p1, p2);
					board.addBlock(a);
				}
			}
		} catch (Exception e) {
			System.err.println("Invalid init and/or goal file.");
			System.exit(1);
		}
	}

	public Board getBoard() {
		return board;

	}

}
