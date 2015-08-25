import java.util.LinkedList;
import java.util.HashSet;

public class Tray {

	private Piece newPiece;
	private Piece oldPiece;
	private LinkedList<PointPlus> newEmpty;
	private LinkedList<PointPlus> oldEmpty;
	private HashSet<Tray> children;
	private Tray parent;
	private String move;
	private boolean processed;
	public int cost;

	public Tray() {
		newPiece = new Piece();
		newEmpty = new LinkedList<PointPlus>();
		oldPiece = new Piece();
		oldEmpty = new LinkedList<PointPlus>();
		children = new HashSet<Tray>();
		parent = this;
		move = null;
		processed = false;
	}

	public Tray(Piece pieceNew, LinkedList<PointPlus> emptyNew, Piece pieceOld,
			LinkedList<PointPlus> emptyOld, Tray myParent, String myMove) {
		newPiece = pieceNew;
		newEmpty = emptyNew;
		oldPiece = pieceOld;
		oldEmpty = emptyOld;
		children = new HashSet<Tray>();
		parent = myParent;
		move = myMove;
		processed = false;
	}

	@Override
	public int hashCode() {
		return (newPiece.hashCode() * oldPiece.hashCode() * 11) - (newEmpty.hashCode() * 51);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else {
			try {
				Tray other = (Tray) object;
				return newPiece.equals(other.newPiece)
						&& oldPiece.equals(other.oldPiece);
			} catch (ClassCastException e) {
				return false;
			}
		}
	}

	public String move() {
		return move;
	}

	public Tray parent() {
		return parent;
	}

	public HashSet<Tray> children() {
		return children;
	}

	public Piece newPieces() {
		return newPiece;
	}

	public LinkedList<PointPlus> newEmpty() {
		return newEmpty;
	}

	public Piece oldPieces() {
		return oldPiece;
	}

	public LinkedList<PointPlus> oldEmpty() {
		return oldEmpty;
	}

	public boolean processed() {
		return processed;
	}

	public void process() {
		processed = !processed;
	}

}
