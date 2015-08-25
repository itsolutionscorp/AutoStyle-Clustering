import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
	HashSet<IntArray2D> alreadyVisited;
	Node initialNode;
	IntArray2D goalBoard;
	int height;
	int width;

	public Graph(Board initial, IntArray2D goal) {
		initialNode = new Node(null, initial, null);
		goalBoard = goal;
		alreadyVisited = new HashSet<IntArray2D>();
		height = initial.height;
		width = initial.width;
	}

	public LinkedList<String> findSolutionDepth() throws Exception {
		Stack<Node> fringe = new Stack<Node>();
		fringe.add(initialNode);
		while (!fringe.isEmpty()) {
			Node currentNode = fringe.pop();
			alreadyVisited.add(currentNode.myItem.blocks);
			if (currentNode.myItem.goalFound(goalBoard)) {
				return getMovesToHere(currentNode);
			} else { // Make children, add them to stack
				Board currentBoard = currentNode.myItem;
				if (currentBoard.validMoves.isEmpty()) {
					currentBoard.betterGetMoves();
				}
				for (int[] move : currentBoard.validMoves) {
					int piece = move[0];
					int direction = move[1];
					IntArray2D moved = currentBoard.move(piece, direction);

					if (!alreadyVisited.contains(moved)) {
						Node child = new Node(currentNode, new Board(moved,
								height, width), makeMoveString(
								currentBoard.blocks.getCoord(piece, 0),
								currentBoard.blocks.getCoord(piece, 1),
								direction));
						fringe.add(child);
					}
				}
			}
		}
		return null;
	}

	public LinkedList<String> getMovesToHere(Node currentNode) {
		LinkedList<String> rtn = new LinkedList<String>();
		while (currentNode.myPrev != null) {
			rtn.addFirst(currentNode.myMove);
			currentNode = currentNode.myPrev;
		}
		rtn.add(currentNode.myMove);
		return rtn;

	}

	public static String makeMoveString(int h, int w, int direction) {
		int oldH = h;
		int oldW = w;
		switch (direction) {
		case 0: {
			h--;
			break;
		}
		case 1: {
			h++;
			break;
		}
		case 2: {
			w--;
			break;
		}
		case 3: {
			w++;
			break;
		}
		}
		return oldH + " " + oldW + " " + h + " " + w;
	}

	private class Node {
		Board myItem;
		Node myPrev;
		String myMove;

		public Node(Node parent, Board b, String move) {
			myItem = b;
			myPrev = parent;
			myMove = move;
		}

		public String toString() {
			return myItem.toString();
		}
	}
}
