public class Board{

	// Note:Fix winner.

	private Piece[][] pieces; //rows, columns. Fire is in the first 3 rows, water in the last 3
	// public static Board board = new Board(false);
	private boolean has_selected = false; //keeps track of whether the player has selected a piece yet. Only select changes this value.
	private Piece selected_piece; //the selected piece. Set by select.
	private int selectedx; //the x coordinate of the piece
	private int selectedy; //the y coordinate of the piece
	private Piece captured; //the captured piece
	private int capturedx; //its x coordinate
	private int capturedy; //its y coordinate
	private boolean has_moved; // has the selected piece moved?
	private boolean turntaker = true; // true if fire turn, false if water turn. Initializes at true, coz fire starts. 
	private static Board board;

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        board = new Board(false);
        // board.pieces = new Piece[N][N];

        while(board.winner() != null) {
 			board.drawBoard(8);
 			// System.out.println("turn:" + board.turntaker);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y)) {
                	// System.out.print(board.canSelect((int) x, (int) y));
                	board.select( (int) x, (int) y); 
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
                	// System.out.println("got here");

                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	// System.out.println(board.canEndTurn());
            	if (board.canEndTurn()) {
            			board.endTurn();
            			// System.out.println("turnEnd:" + board.turntaker);
            	}
            }           
            StdDrawPlus.show(100);
         }
        System.out.println(board.winner());
        return;
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire() == true) {
                		if (pieces[i][j].isShield() == true) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		}
                		else if (pieces[i][j].isBomb() == true) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		}
                		else {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                			else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	}
                	else {
                		if (pieces[i][j].isShield() == true) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}
                			else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		}
                		else if (pieces[i][j].isBomb() == true) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                			else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		}	
                		else {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}
                		}
                	}
                }
            }
        }
    }

	public Board(boolean shouldBeEmpty) { 
		pieces = new Piece[8][8];
		if (shouldBeEmpty == false){
			for (int i = 0; i < 8; i ++) { //column index
				for (int j = 0; j < 8; j++) { //row index

					if (i % 2 == 0) {
						if (j == 0) {
							pieces[i][j] = new Piece(true, this, i, j, "pawn");
						}
						if (j == 2) {
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
						}
						if (j == 6) {
							pieces[i][j] = new Piece(false, this, i, j, "shield");
						}			
					}
					if (i % 2 == 1) {
						if (j == 1) {
							pieces[i][j] = new Piece(true, this, i, j, "shield");
						}
						if (j == 5) {
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
						}
						if (j == 7) {
							pieces[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}
					
				}
			}
		}
	}

		// else {
		// 	for (int i = 0; i < 8; i++) {
  //           	for (int j = 0; j < 8; j++) {
  //               	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
  //               	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
  //               	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
  //               	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
  //               }
  //           }
		// }

 public Piece pieceAt(int x, int y) {
	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
		return null;
	}
	else {
		return pieces[x][y];
	}	
 }

public boolean canSelect(int x, int y) { //this checks if EVERYTHING works ok. 
	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {// is the index fine
		return false;
	}
	if (pieces[x][y] == null) {
		if ((has_selected == true) && (this.has_moved == false) && (this.validmove(selectedx, selectedy, x, y) == true) && (pieces[x][y] == null)) { //normal movement check
			return true;
		}
		if ((has_selected == true) && (Math.abs(selectedy - y) == 2) && (this.capturable(selectedx, selectedy, x, y) == true)) { //capturable (multicapture is just a special case of capturing)
			return true;
		}
		else {
			return false;
		}
	}

	else { //selected spot has a piece
		if ((this.has_selected == false) && (this.turntaker == pieces[x][y].isFire())) { //initial selection of a piece
			return true;
		}
		if ((this.has_selected == true) && (this.has_moved == false) && (this.turntaker == pieces[x][y].isFire())) { //re-selection.
			return true;
		} 
		else {
			return false;
		}
	}
}

private boolean capturable(int xi, int yi, int xf, int yf) { //checks if there is a piece to capture
	if ((pieces[((xi + xf) / 2)][((yi + yf) / 2)] != null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)].isFire() != turntaker)) { //there's a piece of opposite type between me and where I will move.
		if (pieceAt(xi,yi).isKing()) {
			return true;
		}
		else {
			if (pieces[xi][yi].isFire() == true && (yf - yi) > 0) { //fire moves up
				return true;
			}
			else if (pieces[xi][yi].isFire() == false && (yf - yi) < 0) { //water moves down
				return true;
			}
			else {
				return false;
			}
		}
	}
	else {
		return false;
	}
}

// private boolean move_after_capture(Piece taker, int x, int y) { //checks if a taker can move to a selected place after a capture
// 	if (taker.isKing() == true) {
// 		if ((Math.abs(x - taker.captured.x) == 1) && (Math.abs(y - taker.captured.y) == 1)) {
// 			return true;
// 		}
// 		else {
// 			return false;
// 		}
// 	}
// 	else {
// 		if (taker.isFire() == true) {
// 			if (y == taker.captured.y + 1) {
// 				if ((taker.captured.x > taker.x) && (x == taker.captured.x + 1)) {
// 					return true;
// 				}
// 				if ((taker.captured.x < taker.x) && (x == taker.captured.x - 1)) {
// 					return true;
// 				}
// 				else {
// 					return false;
// 				}
// 			}
// 			else {
// 				return false;
// 			}
// 		}
// 		else { //water taker
// 			if (y == taker.captured.y - 1) {
// 				if ((taker.captured.x > taker.x) && (x == taker.captured.x + 1)) {
// 					return true;
// 				}
// 				if ((taker.captured.x < taker.x) && (x == taker.captured.x - 1)) {
// 					return true;
// 				}
// 				else {
// 					return false;
// 				}
// 			}
// 			else {
// 				return false;
// 			}
// 		}
// 	}
// }

private boolean validmove(int xi, int yi, int xf, int yf) { //does not take into account if the initial and final places are within index, boardwise.
	if (this.pieceAt(xi,yi) != null) { //there is a piece at this spot
		Piece piece = this.pieceAt(xi,yi);
		if (piece.isKing() == true) { // allow the piece to move back
			if (((xf - 1 == xi) || (xf + 1 == xi)) && ((yf == yi + 1) || (yf == yi - 1))) {
				return true;
			}
			else {
				return false;
			}
		}
		if (piece.isFire() == true) { //and the piece is fire	
			if (((xf - 1 == xi) || (xf + 1 == xi)) && yf == yi + 1) {
				return true;
			} 
			else {
				return false;
			}
		}
		else { //piece is water
			if (((xf - 1 == xi) || (xf + 1 == xi)) && yf == yi - 1) {
				return true;
			}
			else {
				return false;
			}
		} 
	}
	else {
		return false;
	}
}


	public void select(int x, int y) {

		if (this.pieceAt(x,y) != null) {
			this.has_selected = true;
			this.selected_piece = this.pieceAt(x,y);
			this.selectedx = x;
			this.selectedy = y;
		}
		else { // no piece at the square
			if (this.has_selected == false) {
				;
			}
			else { //I've selected something previously, and canSelect has checked that I can select this empty square. So I move.
			// System.out.println("hi");
			// System.out.println(board.selected_piece.b == null);
				if (Math.abs(y - this.selectedy) == 2) {
					capturedx = ((this.selectedx + x) / 2);
					capturedy = ((this.selectedy + y)/2);
					captured = pieces[this.capturedx][this.capturedy];
					selectedx = x;
					selectedy = y;
				}
				this.selected_piece.move(x, y);
				this.has_moved = true;
			}
		}
	}


public void place(Piece p, int x, int y) {
	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
		;
	}
	if (this.already_exists(x, y)) {
		this.remove(x,y);
	}

	pieces[x][y] = p;
	String element;
	String type;
	if (p.isFire() == true) {
		element = "fire";
	}
	else {
		element = "water";
	}
	if (p.isBomb() == true) {
		type = "bomb";
	}
	else if (p.isShield() == true) {
		type = "shield";
	}
	else {
		type = "pawn";
	}
	// StdDrawPlus.picture(x + .5, y + .5, "img/" + type + "-" + element + ".png", 1, 1);
}

private boolean already_exists(int x, int y){
	// System.out.println(pieces);
	if (pieces[x][y] == null) {
		return true;
	}
	return false;
}

public Piece remove(int x, int y) {
	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
		System.out.println("Index out of bounds.");
		return null;
	}
	if (pieces[x][y] == null) {
		System.out.print("No piece at position (" + x + "," + y + ").");
		return null;
	}
	Piece temp = pieces[x][y];
	// if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
 //    else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
 //    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	pieces[x][y] = null;
	return temp;
}

public boolean canEndTurn() {
	if ((this.has_selected == true) && (this.has_moved == true)) {
		return true;
	}
	else {
		return false;
	}
}

public void endTurn() {
	this.selected_piece.doneCapturing(); //changes has_moved and captured to null and false respectively on the piece that has moved;
	this.has_selected = false;
	this.selected_piece = null; //all resetting game state info
	this.captured = null; //no need to worry bout null pointer exceptions for capturedx and capturedy, because canSelect checks has_selected FIRST
	this.has_moved = false;
	if (this.turntaker == true) { //changes the turn...I hope?
		this.turntaker = false;
	} 
	else if (this.turntaker == false) {
		this.turntaker = true;
	}
}

private Piece temp;
public String winner() {
	for (int i = 0; i < 8; i ++) {
		for (int j = 0; j < 8; j++) {
			if (pieceAt(i,j) != null) {
				if (this.temp == null) {
					this.temp = pieceAt(i,j); 
				}
				else { //temp is not null
					if (this.temp.side() != pieceAt(i,j).side()) {
						return null; //game is still in progress
					}
					else {
						; // the temp piece and new piece are on the same side. In this case, continue iteration. 
					}
				}
			}
		}
	}
	if (temp == null) { //the board had pieces, but now there are none
		return "No one";
	}
	else if (temp.isFire() == true) {
		return "Fire";
	}
	else {
		return "Water";
	}
 }

}