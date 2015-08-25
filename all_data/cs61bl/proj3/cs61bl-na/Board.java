import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Board {

	private HashSet<Piece> pieces;
	private HashSet<PointPlus> emptySpots;
	private Tray currentTray;
	private HashSet<Piece> goalPieces;

	public Board(ArrayList<String> start, ArrayList<String> goal) throws IllegalStateException {

		pieces = new HashSet<Piece>();
		emptySpots = new HashSet<PointPlus>();
		currentTray = new Tray();
		goalPieces = new HashSet<Piece>();

		try {

			String breakdown[] = start.remove(0).split("\\s+");
			int xaxis = (Integer.parseInt(breakdown[0]));
			int yaxis = (Integer.parseInt(breakdown[1]));
			if (breakdown.length != 2 || xaxis < 1 || yaxis < 1 || xaxis > 256
					|| yaxis > 256) {
				throw new IllegalStateException();
			}

			for (int x = 0; x < xaxis; x++) {
				for (int y = 0; y < yaxis; y++) {
					PointPlus falseHolder = new PointPlus(x, y);
					emptySpots.add(falseHolder);
				}
			}

			for (String pieceCoords : goal) {
				String[] elements = pieceCoords.split("\\s+");
				int first = Integer.parseInt(elements[0]);
				int second = Integer.parseInt(elements[1]);
				int third = Integer.parseInt(elements[2]);
				int fourth = Integer.parseInt(elements[3]);
				if (elements.length != 4 || first < 0 || first >=  xaxis ||
						third < 0 || third >= xaxis ||
						second < 0 || second >= yaxis ||
						fourth < 0 || fourth >= yaxis || first > third || second > fourth){
					throw new IllegalStateException();
				}
				goalPieces().add(new Piece(first, second, third, fourth));
			}
			
			for (String pieceCoords : start) {
				String coordinates[] = pieceCoords.split("\\s+");
				int first = Integer.parseInt(coordinates[0]);
				int second = Integer.parseInt(coordinates[1]);
				int third = Integer.parseInt(coordinates[2]);
				int fourth = Integer.parseInt(coordinates[3]);
				if (coordinates.length != 4 || first >= xaxis || first < 0
						|| third >= xaxis || third < 0 || second >= yaxis
						|| second < 0 || fourth >= yaxis || fourth < 0 || first > third || second > fourth) {
					throw new IllegalStateException();
				}
				Piece newPiece = new Piece(first, second, third, fourth);
				pieces.add(newPiece);
				for (int i = newPiece.getTopLeft().X(); i <= newPiece.getBottomRight().X(); i++) {
					for (int j = newPiece.getTopLeft().Y(); j <= newPiece.getBottomRight().Y(); j++) {
						 if (!emptySpots.remove(new PointPlus(i, j))) {
							 throw new IllegalStateException();
						 }
					}
				}
			}
		} catch (NumberFormatException e) {
			throw new IllegalStateException();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalStateException();
		}
	}

	public void redo(Tray toDo) {
		if (toDo.move() != null) {
			pieces.remove(toDo.oldPieces());
			emptySpots.removeAll(toDo.oldEmpty());
			pieces.add(toDo.newPieces());
			emptySpots.addAll(toDo.newEmpty());
			currentTray = toDo;
		}
	}

	public void undo(Tray toDo) {
		if (currentTray.move() != null) {
			pieces.add(toDo.oldPieces());
			emptySpots.addAll(toDo.oldEmpty());
			pieces.remove(toDo.newPieces());
			emptySpots.removeAll(toDo.newEmpty());
			currentTray = toDo.parent();
		}
	}

	public HashSet<Tray> makeChildren() {
		for (Piece piece : pieces) {
			moveHelper(piece, piece.getTopLeft(), piece.getBottomLeft(), 1, true);
			moveHelper(piece, piece.getTopRight(), piece.getBottomRight(), -1, true);
			moveHelper(piece, piece.getTopLeft(), piece.getTopRight(), 1, false);
			moveHelper(piece, piece.getBottomLeft(), piece.getBottomRight(), -1, false);
		}
		return currentTray.children();
	}

	public void moveHelper(Piece oldPiece, PointPlus start, PointPlus end,
			int side, boolean upDown) {
		LinkedList<PointPlus> toDelete = new LinkedList<PointPlus>();
		LinkedList<PointPlus> toAdd = new LinkedList<PointPlus>();
		Piece newPiece = oldPiece;

		if (upDown) {
			for (int i = start.X(); i <= end.X(); i++) {
				PointPlus toCheck = new PointPlus(i, start.Y() - side);
				if (emptySpots.contains(toCheck) == false)
					return;
				PointPlus newEmpty = new PointPlus(toCheck.X(), toCheck.Y()
						+ (side * oldPiece.getYDim()));
				toDelete.add(toCheck);
				toAdd.add(newEmpty);
			}
			newPiece = new Piece(oldPiece.getTopLeft().X(), oldPiece
					.getTopLeft().Y() - side, oldPiece.getBottomRight().X(),
					oldPiece.getBottomRight().Y() - side);
		} else {
			for (int i = start.Y(); i <= end.Y(); i++) {
				PointPlus toCheck = new PointPlus(start.X() - side, i);
				if (emptySpots.contains(toCheck) == false)
					return;
				PointPlus newEmpty = new PointPlus(toCheck.X()
						+ (side * oldPiece.getXDim()), toCheck.Y());
				toDelete.add(toCheck);
				toAdd.add(newEmpty);
			}
			newPiece = new Piece(oldPiece.getTopLeft().X() - side, oldPiece
					.getTopLeft().Y(), oldPiece.getBottomRight().X() - side,
					oldPiece.getBottomRight().Y());
		}

		String move = oldPiece.getTopLeft().X() + " "
				+ oldPiece.getTopLeft().Y() + " " + newPiece.getTopLeft().X()
				+ " " + newPiece.getTopLeft().Y();

		Tray newTray = new Tray(newPiece, toAdd, oldPiece, toDelete, currentTray, move);
		currentTray.children().add(newTray);
	}

	public String stringCode() {
		StringBuilder toReturn = new StringBuilder("");
	    for (Piece piece : pieces) {
	        toReturn.append(piece.toString());
	    }
	    String result = toReturn.toString();
	    result.intern();
	    return result;
	}

	public boolean isSolution() {
		return pieces.containsAll(goalPieces);
	}

	public Tray currentTray() {
		return currentTray;
	}

	public HashSet<Piece> pieces() {
		return pieces;
	}

	public HashSet<PointPlus> empty() {
		return emptySpots;
	}

	public HashSet<Piece> goalPieces() {
		return goalPieces;
	}

	@Override
	public int hashCode() {
		return pieces.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else {
			try {
				Board other = (Board) object;
				return pieces.equals(other.pieces())
						&& emptySpots.equals(other.empty());
			} catch (ClassCastException e) {
				return false;
			}
		}

	}

}
