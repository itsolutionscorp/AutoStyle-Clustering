/**
 *   @author Allen Zeng 
  */

public class Board {
	private int[] selected = {-1, -1};
	private Piece[][] position;

	private int firePiecesLeft;
	private int waterPiecesLeft;

	// Let 0 = Fire. 1 = Water.
	private int playerturn = 0;

	// Let moved = 0 mean a move has not been made
	// Let moved = 1 mean a non-capture move has been made
	// Let moved = 2 mean a capturing move has been made
	private int moved = 0;

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			position = new Piece[8][8];
			firePiecesLeft = 12;
			waterPiecesLeft = 12;

			// Fire Pawns
			position[0][0] = new Piece(true, this, 0, 0, "pawn");
			position[2][0] = new Piece(true, this, 2, 0, "pawn");
			position[4][0] = new Piece(true, this, 4, 0, "pawn");
			position[6][0] = new Piece(true, this, 6, 0, "pawn");

			// Water Pawns
			position[1][7] = new Piece(false, this, 1, 7, "pawn");
			position[3][7] = new Piece(false, this, 3, 7, "pawn");
			position[5][7] = new Piece(false, this, 5, 7, "pawn");
			position[7][7] = new Piece(false, this, 7, 7, "pawn");

			// Fire Shields
			position[1][1] = new Piece(true, this, 1, 1, "shield");
			position[3][1] = new Piece(true, this, 3, 1, "shield");
			position[5][1] = new Piece(true, this, 5, 1, "shield");
			position[7][1] = new Piece(true, this, 7, 1, "shield");

			// Water Shields
			position[0][6] = new Piece(false, this, 0, 6, "shield");
			position[2][6] = new Piece(false, this, 2, 6, "shield");
			position[4][6] = new Piece(false, this, 4, 6, "shield");
			position[6][6] = new Piece(false, this, 6, 6, "shield");

			// Fire Bombs
			position[0][2] = new Piece(true, this, 0, 2, "bomb");
			position[2][2] = new Piece(true, this, 2, 2, "bomb");
			position[4][2] = new Piece(true, this, 4, 2, "bomb");
			position[6][2] = new Piece(true, this, 6, 2, "bomb");

			// Water Bombs
			position[1][5] = new Piece(false, this, 1, 5, "bomb");
			position[3][5] = new Piece(false, this, 3, 5, "bomb");
			position[5][5] = new Piece(false, this, 5, 5, "bomb");
			position[7][5] = new Piece(false, this, 7, 5, "bomb");
		}
		else {
			position = new Piece[8][8];
		}
	}

	private static boolean inBounds(int x, int y) {
		// X and Y must both be either odd or even
		if ((x % 2) != (y % 2))
			return false;
		// Out of bounds checking
		if ((x > 7) || (x < 0) || (y > 7) || (x < 0))
			return false;
		// Else
		return true;
	}

	public Piece pieceAt(int x, int y) {
		if (!inBounds(x, y))
			return null;

		// Grabs piece at (x,y) if it exists
		if (position[x][y] != null)
			return position[x][y];
		//else
		return null;
	}

	public boolean canSelect(int x, int y) {
		// Finished moving
		if (moved == 1)
			return false;

		// Out of Bounds
		if (!inBounds(x, y))
			return false;

		// Cannot select a red space.
		if ((x + y) % 2 == 1)
			return false;

		// Can select a place to move to that is adjacent
		if (position[x][y] == null)
			return validMove(x, y);

		// Player on Player's turn has not selected a piece yet
		if (playerturn == position[x][y].side() && selected[0] == -1
												&& selected[1] == -1) {
			return true;
		}

		// Player has selected a piece, but did not move it to a valid place yet.
		if (moved == 0			&& selected[0] != -1  &&  playerturn == position[x][y].side()
			&& validMove(x, y)  && selected[1] != -1) {
			return true;
		}

		// Reselection: Player has selected a piece, then selects another friendly piece.
		if (moved == 0	&& selected[0] != -1  &&  playerturn == position[selected[0]][selected[1]].side()
						&& selected[1] != -1  &&  playerturn == position[x][y].side()) {
			return true;
		}

		else
			return false;
	}

	private boolean validMove(int x, int y) {
		// Out of Bounds
		if (!inBounds(x, y))
			return false;

		// Blocked by another piece
		if (position[x][y] != null)
			return false;

		// Move too far
		if ( (Math.abs(selected[0]-x) > 2) || (Math.abs(selected[1]-y) > 2) )
			return false;

		// Move into invalid space. IE (1,-1) is okay. (2, 1) is not.
		if ( Math.abs( Math.abs(selected[0]-x) - Math.abs(selected[1]-y) ) != 0)
			return false;
		
		// If move to two spaces away
		if ( (Math.abs(selected[0]-x) == 2) && (Math.abs(selected[1]-y) == 2) ) {
			// Position of intermediate piece
			int a = (selected[0]+x)/2;
			int b = (selected[1]+y)/2;
			Piece intermediate = position[a][b];
			
			// Cannot jump over empty space.
			if (intermediate == null)
				return false;

			// Cannot jump over friendly piece
			if (intermediate.side() == playerturn)
				return false;
		}

		// Purely Vertical and Horizontal movement disallowed
		if ( (selected[0]-x) == 0 || (selected[1]-y) == 0 )
			return false;

		// Non-king pieces may only move one vertical direction
		if (!position[selected[0]][selected[1]].isKing()) {
			// On fire's turn, if move-to vertical coordinate is below current, false
			if ((playerturn == 0) && (y - selected[1] < 0)) {
				return false;
			}
			// On water's turn, if move-to vertical coordinate is above current, false
			if ((playerturn == 1) && (y - selected[1] > 0)) {
				return false;
			}
		}

		// After capturing, can only do another capture or end turn.
		// Equivalent to selecting 1 move away after capturing.
		if (moved == 2 && ((Math.abs(selected[0]-x) < 2) || (Math.abs(selected[1]-y) < 2)))
			return false;

		// Else
		return true;
	}

	public void select(int x, int y) {
		// No new selection can be made. End of turn.
		if (moved == 1) {
			return;
		}

		// New selection
		if (selected[0] == -1 && selected[1] == -1 && moved == 0) {
			selected[0] = x;
			selected[1] = y;
			return;
		}

		// Move selection
		if (selected[0] != -1 && selected[1] != -1) {
			// Reselection: Player has selected a piece, then selects another friendly piece.
			if (moved == 0	&&  position[x][y] != null
							&&  playerturn == position[x][y].side()) {
				selected[0] = x;
				selected[1] = y;
				return;
			}

			// Bomb Capture Move
			if (position[selected[0]][selected[1]].isBomb() &&
				Math.abs(selected[0] - x) == 2 &&
				Math.abs(selected[1] - y) == 2) {

				position[selected[0]][selected[1]].move(x, y);
				moved = 1;

				selected[0] = -1; selected[1] = -1;

				return;
			}

			// Move selection.

			// Cannot do <position[x][y] = remove(selected[0], selected[1])>,
			// as remove() decrements xPiecesLeft counters.
			position[selected[0]][selected[1]].move(x, y);
			position[x][y] = position[selected[0]][selected[1]];
			position[selected[0]][selected[1]] = null;

			selected[0] = x; selected[1] = y;

			moved = 1;
			// Reset moved to false if Piece has captured another
			if (position[x][y].hasCaptured())
				moved = 2;

			// Edge case: King, cannot reselect unless multi-capturing
			if (moved != 2 && position[x][y].isKing()) {
				endTurn();
				return;
			}

			return;
		}
	}

	public void place(Piece p, int x, int y) {
		position[x][y] = p;
		if (p.side() == 0)
			firePiecesLeft += 1;
		else
			waterPiecesLeft += 1;
	}

	public Piece remove(int x, int y) {
		if (!inBounds(x, y)) {
			System.out.println("Cannot remove.");
			System.out.println("Position["+ x +"]["+ y +"] is out of bounds");
			return null;
		}

		// Grabs piece at (x,y) if it exists
		if (position[x][y] != null) {
			Piece temp = position[x][y];
			position[x][y] = null;

			if (temp.side() == 0)
				firePiecesLeft -= 1;
			else
				waterPiecesLeft -= 1;

			return temp;
		}

		// Else
		System.out.println("Cannot remove.");
		System.out.println("No piece found at position["+ x +"]["+ y +"]");
		return null;
	}

	public boolean canEndTurn() {
		if (moved == 1 || moved == 2)	// Depends on rule style.
			return true;				// Here, one may choose to end turn even
		return false;					// if another capture is available.
	}

	public void endTurn() {
		if (canEndTurn()) {
			playerturn = (playerturn+1) % 2;

			// Prevents null error after bomb exploding and removal
			if (selected[0] != -1 && selected[1] != -1)
				position[selected[0]][selected[1]].doneCapturing();
			
			moved = 0;
			selected[0] = -1; selected[1] = -1;
		}
	}

	public String winner() {
		if (firePiecesLeft > 0 && waterPiecesLeft > 0)
			return null;
		if (firePiecesLeft > 0 && waterPiecesLeft == 0)
			return "Fire";
		else if (firePiecesLeft == 0 && waterPiecesLeft > 0)
			return "Water";
		else
			return "No one";
	}

	// Method adopted from StdDrawDemo by @Josh Hug
    private static void drawBoard(int N, Board b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);

                	// If current square is selected, paint it white.
                	if (b.selected[0] == i && b.selected[1] == j)
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else                 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board b = new Board(false);
        
        while(true) {
            drawBoard(N, b);

            for (int j = 0; j < N; j++) {
            	for(int i = 0; i < N; i++) {
            		Piece pointer = b.position[i][j];

            		if (pointer != null) {
            			String type = "";
            			String side = "-";
            			String crowned = "";

            			if (pointer.isBomb())
            				type += "bomb";
            			else if (pointer.isShield())
            				type += "shield";
            			else
            				type += "pawn";

            			if (pointer.side() == 0)
            				side += "fire";
            			else
            				side += "water";

            			if (pointer.isKing())
            				crowned += "-crowned";

            			StdDrawPlus.picture(i + .5, j + .5, "img/" + type + side + crowned + ".png", 1, 1);
            		}
            	}
            }         StdDrawPlus.show(75);


            if (StdDrawPlus.mousePressed()) {
            	// Mouse X corresponds to internal y-axis
            	// Mouse Y corresponds to internal x-axis
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y))
                	b.select((int) x, (int) y);
            }

            if (StdDrawPlus.isSpacePressed()) {
            	b.endTurn();

            	String a = b.winner();
            	if (a != null)
            		return;
            }
        }
	}
}