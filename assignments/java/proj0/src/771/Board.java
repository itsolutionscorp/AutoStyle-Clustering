
public class Board {


	/* Array of arrays to represent a matrix that checks 
	/* whether a square on the board contains a piece or not */

	/* Array of arrays to represent pieces on the board */
	private Piece[][] pieces = new Piece[8][8];
    private boolean gameFinished = false;
    private int player = 0; // Fire side starts first
    private Piece selectedPiece = null; // No piece is selected when game first initialize. To be changed throughout the game.
    private int selectedX;
    private int selectedY;
    private boolean selectedPieceHasMoved = false;
    private int numberOfFire;
    private int numberOfWater;


	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			initialize();
		}
	}

	/* Adapted from StdDrawDemo */
	private void drawBoard() {
		for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.ORANGE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
                if (this.selectedPiece != null && i == this.selectedX && j == this.selectedY) {
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (pieces[i][j] != null) {
                	Piece p = pieces[i][j];

                	if (p.isFire()) {
                		if (p.isBomb()) {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
                		} else if (p.isShield()) {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		} else {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	} else { //p is water
                		if (p.isBomb()) {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		} else if (p.isShield()) {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		} else {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}
                		}
                	}
                }
            }
        }
	}

	/* Creates new instances of Piece and put them in intial starting position */
	private void initialize() {
		for (int i = 0 ; i < 8 ; i += 2) {
			this.pieces[i][0] = new Piece(true, this, i, 0, "pawn");	//initialize pawn-fire
			this.pieces[i+1][1] = new Piece(true, this, i + 1, 1, "shield"); //initialize shield-fire
			this.pieces[i][2] = new Piece(true, this, i, 2, "bomb"); //initialize bomb-fire

			this.pieces[i+1][7] = new Piece(false, this, i + 1, 7, "pawn"); //initialize pawn-water
			this.pieces[i][6] = new Piece(false, this, i, 6, "shield"); //initialize shield-water
			this.pieces[i+1][5] = new Piece(false, this, i + 1, 5, "bomb"); //initialize bomb-water
		}
        this.numberOfFire = 12;
        this.numberOfWater = 12;
	}


    /* Checks if position passed in is within the domain of this board.
     * Returns true if it isn't */
    private boolean outOfBound(int x, int y) {
        return ((x < 0) || (y < 0) || (x > 7) || (y > 7));
    }


    /* Gets the piece at position (x, y) on the board, 
     * or null if there is no piece. If (x, y) are out of bounds, returns null. */
    public Piece pieceAt(int x, int y) {
        if (outOfBound(x, y)) {
            return null;
        }
        return this.pieces[x][y]; 
    }

    public void place(Piece p, int x, int y) {
        // System.out.println("Calling place");
        // System.out.println(x);
        // System.out.println(y);
        if (outOfBound(x, y)) {
            return;
        } else {
            if (p.isFire()) {
                this.numberOfFire += 1;
            } else {
                this.numberOfWater += 1;
            }
            this.pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        // System.out.println("Calling remove");
        // System.out.println(x);
        // System.out.println(y);
        if (outOfBound(x, y)) {
            System.out.println("Cannot remove from out of bound position.");
            return null;
        } else if (this.pieces[x][y] == null) {
            System.out.println("No piece at this position.");
            return null;
        } else {
            Piece toBeRomoved = this.pieces[x][y];
            if (toBeRomoved.isFire()) {
                this.numberOfFire -= 1;
            } else {
                this.numberOfWater -= 1;
            }
            this.pieces[x][y] = null;
            return toBeRomoved;
        }
    }

    // Checks if P at xi, yi can move to or capture to xf, yf
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (outOfBound(xf, yf) || (pieceAt(xf, yf) != null)) {
            return false;
        }
        // At this point, it is certained that there is a piece at xi, yi
        Piece toBeMoved = pieceAt(xi, yi);
        int absDiffInX = Math.abs(xf - xi); // Horizontal movement. Doesn't matter left or right.
        int absDiffInY = Math.abs(yf - yi);
        int diffInY = yf - yi;              // Vertical movement. Direction recorded.

        if ((absDiffInX == 1) && (!toBeMoved.hasCaptured())) {
            if (toBeMoved.isKing()) {   // King can move in any diagonal direction.
                return absDiffInY == 1;
            } else if (toBeMoved.isFire()) { // Non-crowned Fire can only move up.
                return diffInY == 1;
            } else {                         // toBeMoved is non-crowned Water. can only move down.
                return diffInY == -1;
            }
        } else if (absDiffInX == 2) {
            if (absDiffInY != 2) {
                return false;
            }
            // Jumping over a square implies there is a potential capture.
            Piece toBeTakenOut = pieceAt((xi+xf)/2, (yi+yf)/2);
            if ((toBeTakenOut == null) || (toBeMoved.isFire() == toBeTakenOut.isFire())) {
                return false;           // Can't take over your own team member, nor can you jump over nothing.
            // At this point, a capture HAS to happen.
            } else if (toBeMoved.isKing()) {
                return true;
            } else if (toBeMoved.isFire()) {  // Non-crowned Fire can only capture pieces above itself.
                return diffInY == 2;
            } else {
                return diffInY == -2;    // Non-crowned Water. can only capture piece below itself.
            }
        } else {
            return false;
        }
    }

    public boolean canSelect(int x, int y) {
        // System.out.println("Calling canSelect");
        if (gameFinished || outOfBound(x, y)) {
            return false;
        }
        Piece toBeSelected = pieceAt(x, y);
        // Player hasn't selected a piece yet, and toBeSelected is to be selected.
        if (this.selectedPiece == null) { 
            return (toBeSelected != null) && (toBeSelected.side() == this.player);
        /* Player has already selected a piece and attempts to select an empty square.
         * Player attempts to move or capture. */
        } else if (toBeSelected == null) {  
            return (this.selectedPiece.hasCaptured() || !this.selectedPieceHasMoved) 
                    && validMove(this.selectedX, this.selectedY, x, y);
        /* At this point, both this.selectedPiece and toBeSelected are not null
         * Have to check if this is the right player's turn and if the selectedPiece 
         * hasn't moved yet. */
        } else {
            return (toBeSelected.side() == this.player) && (!this.selectedPieceHasMoved);
        }
    }

    public void select(int x, int y) {
        // Assume canSelect returns true
        // System.out.println("Calling select");
        Piece toBeSelected = pieceAt(x, y);
        this.selectedX = x;
        this.selectedY = y;
        if (toBeSelected != null) { // Confirms there is a piece at x, y.
            this.selectedPiece = toBeSelected;
        } else { // Player is trying to select an empty square.
            this.selectedPiece.move(x, y);
            this.selectedPieceHasMoved = true;
        }
    }

    /* Returns whether or not the the current player can end their turn. 
     * To be able to end a turn, a piece must have moved or performed a capture. */ 
    public boolean canEndTurn() {
        // System.out.println("Calling canEndTurn");
        System.out.println(this.player);
        System.out.println(this.selectedPiece);
        System.out.println(this.selectedX);
        System.out.println(this.selectedY);
        if (this.selectedPiece == null) {
            return false;   // In order to end a turn, player have to have selected a piece
        }
        return this.selectedPieceHasMoved || this.selectedPiece.hasCaptured();
    }

    public void endTurn() {
        // System.out.println("Calling endTurn");
        if (this.player == 0) {
            this.player = 1;
        } else {
            this.player = 0;
        }
        if (this.selectedPiece != null) {
            this.selectedPiece.doneCapturing();
            this.selectedPiece = null;
        }
        this.selectedPieceHasMoved = false;
    }

    public String winner() {
        if (this.numberOfFire > 0 && this.numberOfWater == 0) {
            return "Fire";
        }
        if (this.numberOfWater > 0 && this.numberOfFire == 0) {
            return "Water";
        }
        if (this.numberOfFire == 0 && this.numberOfWater == 0) {
            return "No one";
        }
        return null;
    }


	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

		Board b = new Board(false);
		while (!b.gameFinished) {
			b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double xPos = StdDrawPlus.mouseX();
                double yPos = StdDrawPlus.mouseY();
                int x = (int)xPos;
                int y = (int)yPos;
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                b.endTurn();
            }
            if (b.winner() != null) {
                b.gameFinished = true;
            }
            if (StdDrawPlus.isNPressed()) {
                b = new Board(false);
            }
            StdDrawPlus.show(100);
		}
	}
}
