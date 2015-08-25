import java.awt.Point;
import java.util.*;

public class Solver {

	private Board goal;
	private Board currentBoard;
	private PriorityQueue<Node> queue;
	private HashSet<Board> boardSeen;
	private Node node;
	private boolean simple=false;

	public Solver(Board root, Board goalBoard) {
		goal = goalBoard;
		currentBoard = root;
		boardSeen = new HashSet<Board>();
		boardSeen.add(root);
		queue = new PriorityQueue<Node>(10, new NodeComparator());
		queue.add(new Node(root, 0));
	}

	public static void main(String[] args) {
		String inputfile = args[0];
		String outputfile = args[1];
		Load begin = new Load(inputfile, 0, 0);
		Load end = new Load(outputfile, begin.getBoard().getLength(), begin.getBoard().getWidth());
		Solver s = new Solver(begin.getBoard(), end.getBoard());
		s.solve();
	}

	private class Node {
		private int dis;
		private Board myBoard;

		public Node(Board b, int distance) {
			myBoard = b;
			dis = distance;
		}
	}

	private class NodeComparator implements Comparator<Node> {
		public int compare(Node obj1, Node obj2) {
			if (obj1.dis > obj2.dis) {
				return 1;
			} else if (obj1.dis < obj2.dis) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	private int distance(Board board) {
		if (simple==true){
			return 0;
		}
		int temp = 0;
		for (Block b : goal.getBlocks()) {
			for (Block i : board.getBlocks()) {
				if (b.getLength() == i.getLength() && b.getWidth() == i.getWidth()) {
					temp += Math.abs((i.getStart().x - b.getStart().x));
					temp += Math.abs((i.getStart().y - b.getStart().y));
				}
			}
		}
		return temp;
	}

	public void solve() {
		if (goal.getBlocks().size() > 20) {
			simple = true;
		}
		while (!isSolved()) {
			getMoves();
			if (queue.isEmpty()) {
				System.exit(1);
			}
			node = queue.remove();
			currentBoard = node.myBoard;
		}
		System.out.print(currentBoard.printMoves());
	}

	public boolean isSolved() {
		for (Block bk : goal.getBlocks()) {
			if (!currentBoard.getBlocks().contains(bk)) {
				return false;
			}
		}
		return true;
	}

	private void getMoves() {
		HashSet<Point> a = new HashSet<Point>(currentBoard.getEmpty());
		for (Point temp : a) {
			if (temp.x + 1 < currentBoard.getWidth()) {
				this.move(new Point(temp.x + 1, temp.y), "left");
			}
			if (temp.x - 1 >= 0) {
				this.move(new Point(temp.x - 1, temp.y), "right");
			}
			if (temp.y + 1 < currentBoard.getLength()) {
				this.move(new Point(temp.x, temp.y + 1), "up");
			}
			if (temp.y - 1 >= 0) {
				this.move(new Point(temp.x, temp.y - 1), "down");
			}
		}
	}

	private void move(Point p, String direction) {
		HashMap<Point, Block> currTray = currentBoard.getTray();
		if (currTray.containsKey(p)) {
			Point start = currTray.get(p).getStart();
			Point moveto = null;
			if (direction.equals("right")) {
				moveto = new Point(start.x + 1, start.y);
			} else if (direction.equals("left")) {
				moveto = new Point(start.x - 1, start.y);
			} else if (direction.equals("up")) {
				moveto = new Point(start.x, start.y - 1);
			} else {
				moveto = new Point(start.x, start.y + 1);
			}
			Board possibleBoard = currentBoard.makeMove(currTray.get(p), moveto);
			if (possibleBoard != null && !boardSeen.contains(possibleBoard)) {
				Node n = new Node(possibleBoard, distance(possibleBoard));
				queue.add(n);
				boardSeen.add(possibleBoard);
			}

		}

	}

}
