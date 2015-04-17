
public class Board{
	
	private static int N = 8;
	private boolean hasMoved = false;
	private boolean currentPlayer = true;
	private int firePieces;
	private int waterPieces;
	private Piece savePiece = null;
	private int savex;
	private int savey;

	private Piece[][] pieces;

	public static void main(String[] args) {
		Board b = new Board(false);	
		while (true) {
			b.drawBoard(N);
			if (StdDrawPlus.mousePressed()) {
				double i = StdDrawPlus.mouseX();
        		double j = StdDrawPlus.mouseY();
        		if (b.canSelect((int) i, (int) j) == true) {
        			b.select((int) i, (int) j);
        		}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn() == true) {
					b.endTurn();
				}
			}
			StdDrawPlus.show(20);
		}
	}

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == true) {
			pieces = new Piece[N][N];
			drawBoard(N);
		}
		if (shouldBeEmpty == false) {
			pieces = new Piece[N][N];
			pieces[0][0] = new Piece(true,this,0,0,"pawn");
			pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
			pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
			pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
			pieces[1][1] = new Piece(true, this, 1, 1, "shield");
			pieces[3][1] = new Piece(true, this, 3, 1, "shield");
			pieces[5][1] = new Piece(true, this, 5, 1, "shield");
			pieces[7][1] = new Piece(true, this, 7, 1, "shield");
			pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
			pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
			pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
			pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
			pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
			pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
			pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
			pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
			pieces[0][6] = new Piece(false, this, 0, 6, "shield");
			pieces[2][6] = new Piece(false, this, 2, 6, "shield");
			pieces[4][6] = new Piece(false, this, 4, 6, "shield");
			pieces[6][6] = new Piece(false, this, 6, 6, "shield");
			pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
			pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
			pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
			pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

			drawBoard(N);
			
		}
	}

	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	StdDrawPlus.setXscale(0, N);
        		StdDrawPlus.setYscale(0, N);
				if (pieces[i][j] != null) {
					drawPiece(pieces[i][j], i, j);
				}
			}
		}
    }

	private void drawPiece(Piece p, int i, int j) {
		if (p.isKing() && currentPlayer == true) {
			makeKing(p, i, j, "fire");
		}
		if (p.isKing() && currentPlayer == false) {
			makeKing(p, i, j, "water");
		}
		if (p.isBomb() && p.isFire()) {
			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png" , 1, 1);
		}
		if (p.isShield() && p.isFire()) {
			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png" , 1, 1);
		}
		if (p.isFire() && p.isShield() == false && p.isBomb() == false) {
			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png" , 1, 1);
		}
		if (p.isBomb() && p.isFire() == false) {
			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png" , 1, 1);
		}
		if (p.isShield() && p.isFire() == false) {
			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png" , 1, 1);
		}
		if (p.isFire() == false && p.isShield() == false && pieces[i][j].isBomb() == false) {
			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png" , 1, 1);
		}
	}


	private void makeKing(Piece p, int i, int j, String type) {
		if (p.isBomb()) {
			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-"+ type + "-crowned.png" , 1, 1);
		}
		if (p.isShield()) {
			StdDrawPlus.picture(i + .5, j + .5, "img/shield-" + type + "-crowned.png" , 1, 1);
		}
		if (p.isBomb() == false && p.isShield() == false) {
			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-" + type + "-crowned.png" , 1, 1);
		}
	}



	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		else {
			if (pieces[x][y] != null) {
				return pieces[x][y];
			}
			else {
				return null;
			}
		}
	}


	public void place(Piece p, int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N && p != null) {
			pieces[x][y] = p;
			p = null;
		}
	}



	public boolean canSelect(int x, int y) {
		if (x < 0 || x > 7 || y < 0 ||  y > 7) {
			return false;
		}
		if (hasMoved == true && savePiece != null && savePiece.isKing() == true && savePiece.hasCaptured() == false) {
			return false;
		}

		if (currentPlayer == true) {
			if (pieceAt(x, y) != null && pieceAt(x, y).isFire() != true) {
				return false;
			}
		}
		if (currentPlayer == false) {
			if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == true) {
				return false;
			}
		}

		if (savePiece != null && savePiece.isKing() == false && currentPlayer == true) {
			if (pieceAt(x, y) != null && hasMoved == false && pieceAt(x, y).isFire() == savePiece.isFire()) {
				return true;
			}
			if (savey > y) {
				return false;
			}
		}

		if (savePiece != null && savePiece.isKing() == false && currentPlayer == false) {
			if (pieceAt(x, y) != null && hasMoved == false && pieceAt(x, y).isFire() == savePiece.isFire()) {
				return true;
			}

			if (savey < y) {
				return false;
			}
		}

		if (pieceAt(x, y) != null) {
			if (savePiece == null) {
				return true;
			}
			if (savePiece.hasCaptured() == false && hasMoved == false) {
				return true;
			}
			return false;
		}
        if (pieceAt(x, y) == null && savePiece != null) {
            if (Math.abs(savex - x) == 1 && Math.abs(savey - y) == 1 && hasMoved == false) {
               	return true;
           	}
            if (Math.abs(savex - x) == 2 && Math.abs(savey - y) == 2 && pieceAt((savex + x)/2,(savey + y)/2) != null 
            	&& pieceAt((savex + x)/2,(savey + y)/2).isFire() != savePiece.isFire()) {           
               		return true;
            }
      	}
      	return false;
    }

	public void select(int x, int y) {
		savex = x;
		savey = y;
		if (pieceAt(x, y) != null) {
			savePiece = pieces[x][y];
		}

		if (pieces[x][y] == null && savePiece != null) {
			savePiece.move(x, y);
			hasMoved = true;
		}
	}


	public Piece remove(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N) {
			if (pieces[x][y] != null) {
				Piece temp = pieces[x][y];
				pieces[x][y] = null;
				return temp;
			}
			else {
				System.out.println("No piece at the selected (x,y).");
				return null;
			}
		}
		else {
			System.out.println("Selected (x,y) is not a valid coordinate.");
			return null;

		}
	}

	public boolean canEndTurn() {
		if (hasMoved == true) {
			return true;
		}

		if (savePiece != null && savePiece.hasCaptured() == true) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean changePlayer() {
		if (savePiece.isFire() == true) {
			return false;
		}
		else {
			return true;
		}
	}

	public void endTurn() {
			currentPlayer = changePlayer();
			hasMoved = false;
			savePiece.doneCapturing();
			savePiece = null;
	}

	public String winner() {
		waterPieces = 0;
		firePieces = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isFire() == true) {
            			firePieces += 1;
            		}
            		if (pieces[i][j].isFire() == false) {
            			waterPieces += 1;
            		}
            	}
            }
        }
		if (firePieces == 0 && waterPieces != 0) {
			return "Water";
		}
		if (waterPieces == 0 && firePieces != 0) {
			return "Fire";
		}
		if (firePieces == 0 && waterPieces == 0) {
			return "No One";
		}
		return null;
	}
			

		

	







}