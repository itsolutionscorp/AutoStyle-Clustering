import java.awt.Point;
import java.util.ArrayList;

public class Board {
	private Piece[][] myBoard = new Piece[8][8];
	private ArrayList<Piece> myPieces = new ArrayList<Piece>();
	private int player = 0;
	private boolean hasSelected = false;
	private Piece selectedPiece = null;
	private boolean hasMoved = false;
	private int waterpieces = 0;
	private int firepieces = 0;
	private boolean justmadeKing = false;
	private boolean pieceIsKing=false;

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
			waterpieces = 12;
			firepieces = 12;
			addPieces();
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (y > 7)) {
			return null;
		}
		return myBoard[x][y];

	}

	public void place(Piece p, int x, int y) {
		if (x > 7 || y > 7 || p == null) {
			return;
		}
		boolean found = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) == p) {
					found = true;
					Piece adder = remove(i, j);
					if (p.isFire()) {
						firepieces += 1;
					}
					if (p.isFire() == false) {
						waterpieces += 1;
					}
					myBoard[x][y] = adder;
					myPieces.add(adder);
				}
			}
		}
		if (found == false) {
			myBoard[x][y] = p;
			myPieces.add(p);
			if (p.isFire()) {
				firepieces += 1;
			}
			if (p.isFire() == false) {
				waterpieces += 1;
			}
		}
	}

	private void drawBoard() {
		for (int k = 0; k < 8; k++) {
			for (int j = 0; j < 8; j++) {
				if ((k + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(k + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (myBoard[k][j] == selectedPiece && selectedPiece != null) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(k + .5, j + .5, .5);
				}
				if (myBoard[k][j] != null) {
					Piece i = myBoard[k][j];
					if (i.isFire() == true && i.isShield()
							&& i.isKing() == false) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/shield-fire.png", 1, 1);
					}
					if (i.isFire() == true && i.isShield()
							&& i.isKing() == true) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/shield-fire-crowned.png", 1, 1);
					}
					if (i.isFire() == true && i.isBomb() && i.isKing() == false) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/bomb-fire.png", 1, 1);
					}
					if (i.isFire() == true && i.isBomb() && i.isKing() == true) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/bomb-fire-crowned.png", 1, 1);
					}
					if (i.isFire() == true && i.isShield() == false
							&& i.isBomb() == false && i.isKing() == false) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/pawn-fire.png", 1, 1);
					}
					if (i.isFire() == true && i.isShield() == false
							&& i.isBomb() == false && i.isKing() == true) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/pawn-fire-crowned.png", 1, 1);
					}
					if (i.isFire() == false && i.isShield()
							&& i.isKing() == false) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/shield-water.png", 1, 1);

					}
					if (i.isFire() == false && i.isShield()
							&& i.isKing() == true) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/shield-water-crowned.png", 1, 1);

					}
					if (i.isFire() == false && i.isBomb()
							&& i.isKing() == false) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/bomb-water.png", 1, 1);

					}
					if (i.isFire() == false && i.isBomb() && i.isKing() == true) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/bomb-water-crowned.png", 1, 1);

					}
					if (i.isFire() == false && i.isShield() == false
							&& i.isBomb() == false && i.isKing() == false) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/pawn-water.png", 1, 1);
					}
					if (i.isFire() == false && i.isShield() == false
							&& i.isBomb() == false && i.isKing() == true) {
						StdDrawPlus.picture(k + .5, j + .5,
								"img/pawn-water-crowned.png", 1, 1);
					}
				}
			}
		}
		StdDrawPlus.show(100);
	}

	private static final Point[] fire_shield_pos = { new Point(1, 1),
			new Point(3, 1), new Point(5, 1), new Point(7, 1) };
	private static final Point[] fire_pawn_pos = { new Point(0, 0),
			new Point(2, 0), new Point(4, 0), new Point(6, 0) };

	private static final Point[] fire_bomb_pos = { new Point(0, 2),
			new Point(2, 2), new Point(4, 2), new Point(6, 2) };

	private static final Point[] water_pawn_pos = { new Point(1, 7),
			new Point(3, 7), new Point(5, 7), new Point(7, 7) };
	private static final Point[] water_shield_pos = { new Point(0, 6),
			new Point(2, 6), new Point(4, 6), new Point(6, 6) };
	private static final Point[] water_bomb_pos = { new Point(1, 5),
			new Point(3, 5), new Point(5, 5), new Point(7, 5) };

	private Point getSquare() {
		double x = StdDrawPlus.mouseX();
		double y = StdDrawPlus.mouseY();
		return new Point((int) x, (int) y);
	}

	private void addPieces() {
		for (Point p : fire_shield_pos) {
			Piece s = new Piece(true, this, p.x, p.y, "shield");
			myPieces.add(s);
			myBoard[p.x][p.y] = s;
		}
		for (Point p : fire_pawn_pos) {
			Piece s = new Piece(true, this, p.x, p.y, "pawn");
			myPieces.add(s);
			myBoard[p.x][p.y] = s;
		}
		for (Point p : fire_bomb_pos) {
			Piece s = new Piece(true, this, p.x, p.y, "bomb");
			myPieces.add(s);
			myBoard[p.x][p.y] = s;
		}
		for (Point p : water_shield_pos) {
			Piece s = new Piece(false, this, p.x, p.y, "shield");
			myPieces.add(s);
			myBoard[p.x][p.y] = s;
		}
		for (Point p : water_pawn_pos) {
			Piece s = new Piece(false, this, p.x, p.y, "pawn");
			myPieces.add(s);
			myBoard[p.x][p.y] = s;
		}
		for (Point p : water_bomb_pos) {
			Piece s = new Piece(false, this, p.x, p.y, "bomb");
			myPieces.add(s);
			myBoard[p.x][p.y] = s;
		}
	}

	private boolean validKingCapture(Piece p, int xi, int yi, int xf, int yf) {
		if (xf > 7 || yf > 7) {
			return false;
		}
		if (myBoard[xf][yf] != null) {
			return false;
		}

		if (((xf == xi + 2) && (yf == yi + 2))) {
			Piece intermediate = myBoard[xi + 1][yi + 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		if (((xf == xi + 2) && (yf == yi - 2))) {
			Piece intermediate = myBoard[xi + 1][yi - 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		if (((xf == xi - 2) && (yf == yi + 2))) {
			Piece intermediate = myBoard[xi - 1][yi + 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		if (((xf == xi - 2) && (yf == yi - 2))) {
			Piece intermediate = myBoard[xi - 1][yi - 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}}
	return false;}
		

	
	private boolean validKingMove(Piece p, int xi, int yi, int xf, int yf) {
		if (xf > 7 || yf > 7) {
			return false;
		}
		if (myBoard[xf][yf] != null) {
			return false;
		}
		if (justmadeKing){
			if (((xf == xi + 2) && (yf == yi + 2))) {
				Piece intermediate = myBoard[xi + 1][yi + 1];
				if (intermediate == null) {
					return false;
				}
				if (intermediate.isFire() != p.isFire()) {
					return true;
				}
			}
			if (((xf == xi + 2) && (yf == yi - 2))) {
				Piece intermediate = myBoard[xi + 1][yi - 1];
				if (intermediate == null) {
					return false;
				}
				if (intermediate.isFire() != p.isFire()) {
					return true;
				}
			}
			if (((xf == xi - 2) && (yf == yi + 2))) {
				Piece intermediate = myBoard[xi - 1][yi + 1];
				if (intermediate == null) {
					return false;
				}
				if (intermediate.isFire() != p.isFire()) {
					return true;
				}
			}
			if (((xf == xi - 2) && (yf == yi - 2))) {
				Piece intermediate = myBoard[xi - 1][yi - 1];
				if (intermediate == null) {
					return false;
				}
				if (intermediate.isFire() != p.isFire()) {
					return true;
				}
			}
		}
		else{
			
			if (((xf == xi + 1) && (yf == yi + 1))
					|| ((xf == xi - 1) && (yf == yi + 1))) {
				return true;
			}
			if (((xf == xi + 1) && (yf == yi - 1))
					|| ((xf == xi - 1) && (yf == yi - 1))) {
				return true;
			}
			if (((xf == xi + 2) && (yf == yi + 2))) {
				Piece intermediate = myBoard[xi + 1][yi + 1];
				if (intermediate == null) {
					return false;
				}
				if (intermediate.isFire() != p.isFire()) {
					return true;
				}
			}
			if (((xf == xi + 2) && (yf == yi - 2))) {
				Piece intermediate = myBoard[xi + 1][yi - 1];
				if (intermediate == null) {
					return false;
				}
				if (intermediate.isFire() != p.isFire()) {
					return true;
				}
			}
			if (((xf == xi - 2) && (yf == yi + 2))) {
				Piece intermediate = myBoard[xi - 1][yi + 1];
				if (intermediate == null) {
					return false;
				}
				if (intermediate.isFire() != p.isFire()) {
					return true;
				}
			}
			if (((xf == xi - 2) && (yf == yi - 2))) {
				Piece intermediate = myBoard[xi - 1][yi - 1];
				if (intermediate == null) {
					return false;
				}
				if (intermediate.isFire() != p.isFire()) {
					return true;
				}}
			
		} 
		return false;}

	private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
		if (xf > 7 || yf > 7) {
			return false;
		}
		if (myBoard[xf][yf] != null) {
			return false;
		}

		if (p.isFire()
				&& (((xf == xi + 1) && (yf == yi + 1)) || ((xf == xi - 1) && (yf == yi + 1)))) {
			return true;
		}
		if (p.isFire() && (((xf == xi + 2) && (yf == yi + 2)))) {
			Piece intermediate = myBoard[xi + 1][yi + 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		if (p.isFire() && (((xf == xi - 2) && (yf == yi + 2)))) {
			Piece intermediate = myBoard[xi - 1][yi + 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		if (p.isFire() == false
				&& (((xf == xi + 1) && (yf == yi - 1)) || ((xf == xi - 1) && (yf == yi - 1)))) {
			return true;
		}
		if (p.isFire() == false && (((xf == xi + 2) && (yf == yi - 2)))) {
			Piece intermediate = myBoard[xi + 1][yi - 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		if (p.isFire() == false && (((xf == xi - 2) && (yf == yi - 2)))) {
			Piece intermediate = myBoard[xi - 1][yi - 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}

		return false;
	}

	private boolean validCaptureMove(Piece p, int xi, int yi, int xf, int yf) {
		if (myBoard[xf][yf] != null || p.isKing() == true) {
			return false;
		}
		if (p.isFire() == false && (((xf == xi + 2) && (yf == yi - 2)))) {
			Piece intermediate = myBoard[xi + 1][yi - 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		if (p.isFire() == false && (((xf == xi - 2) && (yf == yi - 2)))) {
			Piece intermediate = myBoard[xi - 1][yi - 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		if (p.isFire() && (((xf == xi + 2) && (yf == yi + 2)))) {
			Piece intermediate = myBoard[xi + 1][yi + 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		if (p.isFire() && (((xf == xi - 2) && (yf == yi + 2)))) {
			Piece intermediate = myBoard[xi - 1][yi + 1];
			if (intermediate == null) {
				return false;
			}
			if (intermediate.isFire() != p.isFire()) {
				return true;
			}
		}
		return false;
	}

	private Point getCoordinates(Piece p) {
		if (p == null) {
			return null;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) == p) {
					return new Point(i, j);
				}
			}
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		Piece piece = myBoard[x][y];
		if (piece == null) {
			if (selectedPiece != null) {
				Point coordinates = getCoordinates(selectedPiece);
				if (selectedPiece.hasCaptured() == false
						&& validMove(selectedPiece, coordinates.x,
								coordinates.y, x, y)
						&& selectedPiece.side() == player) {
					return true;
				}
				if (selectedPiece.isKing() == false
						&& selectedPiece.hasCaptured() == true
						&& validCaptureMove(selectedPiece, coordinates.x,
								coordinates.y, x, y)
						&& selectedPiece.side() == player) {
					return true;
				}
				if (selectedPiece.isKing() == true && selectedPiece.hasCaptured()==false
						&& validKingMove(selectedPiece, coordinates.x,
								coordinates.y, x, y)
						&& selectedPiece.side() == player) {
					return true;
				}
				if (selectedPiece.isKing() == true && selectedPiece.hasCaptured()==true
						&& validKingCapture(selectedPiece, coordinates.x,
								coordinates.y, x, y)
						&& selectedPiece.side() == player) {
					return true;

			}}}

		else if (piece != null) {
			if (hasSelected == false && piece != null) {
				if (piece.side() == player) {
					return true;
				}
			}
			if (hasSelected && hasMoved == false && piece.side() == player
					&& piece != null) {
				return true;
			}
		}
		return false;}
	

	public void select(int x, int y) {
		pieceIsKing=false;
		if (pieceAt(x, y) != null) {
			if (pieceAt(x,y)!=null){
			pieceIsKing= pieceAt(x,y).isKing();}
			hasSelected = true;
			selectedPiece = pieceAt(x, y);
		}

		else {
			place(selectedPiece, x, y);
			selectedPiece.move(x, y);
			selectedPiece = pieceAt(x, y);
			hasMoved = true;
			if (pieceIsKing==false && selectedPiece!= null && selectedPiece.isKing()==true){
				justmadeKing=true;
			}
		}

	}

	public Piece remove(int x, int y) {
		int correctIndex = -1;
		if (x > 7 || y > 7) {
			System.out.println("That index is out of bounds");
			return null;
		}
		Piece result = myBoard[x][y];
		if (result == null) {
			System.out.println("No piece at the given index");
			return null;
		}
		int index = 0;
		for (Piece p : myPieces) {
			if (p == result) {
				correctIndex = index;
			}
			index++;
		}
		if (result.isFire()) {
			firepieces = firepieces - 1;
		}
		if (result.isFire() == false) {
			waterpieces = waterpieces - 1;
		}
		if (correctIndex != -1) {
			myPieces.remove(correctIndex);
		}
		myBoard[x][y] = null;
		return result;
	}

	public boolean canEndTurn() {
		if (selectedPiece != null && selectedPiece.hasCaptured() == true) {
			System.out.println("captured");
			return true;
		}
		if (hasMoved == true) {
			return true;
		}
		return false;
	}

	public void endTurn() {

		player = 1 - player;
		hasMoved = false;
		hasSelected = false;
		if (justmadeKing){
			justmadeKing=false;
		}
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;

	}

	public String winner() {

		if (waterpieces == 0 && firepieces == 0) {

			return "No one";
		}
		if (waterpieces == 0) {
			return "Fire";
		}
		if (firepieces == 0) {
			return "Water";
		}
		return null;
	}

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		b.drawBoard();
		while (b.winner() == null) {
			if (StdDrawPlus.mousePressed()) {
				Point moveCoordinates = b.getSquare();
				if (b.canSelect(moveCoordinates.x, moveCoordinates.y)) {
					b.select(moveCoordinates.x, moveCoordinates.y);
				}
				b.drawBoard();
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}

			}

		}

		System.out.println(b.winner());
	}
}
