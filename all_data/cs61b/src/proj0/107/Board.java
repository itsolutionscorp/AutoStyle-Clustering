import java.awt.Color;

public class Board {
	
	private static final Color GRAY = new Color(177, 177, 177);	
	private static final Color RED = new Color(196, 69, 49);
	
	// all coordinates are width (x) by height (y)
	// pieces initially all nulls
	private Piece[][] pieces = new Piece[8][8];
	private boolean hasMovedAtLeastOnce; // this turn
	private boolean hasPerformedCaptureAtLeastOnce; // this turn
	private String currentTurn = "fire"; // initially false
	private Piece selected;
	private String motd = "Welcome to Checkers61b!";
	
	
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			return;
		}
		// fire pawns
		int y = 0;
		for (int x = 0; x < 8; x += 2) {
			pieces[x][y] = new Piece(true, this, x, y, "pawn");
		}
		// fire shields
		y = 1;
		for (int x = 1; x < 8; x += 2) {
			pieces[x][y] = new Piece(true, this, x, y, "shield");
		}
		// fire bombs
		y = 2;
		for (int x = 0; x < 8; x += 2) {
			pieces[x][y] = new Piece(true, this, x, y, "bomb");
		}
		// water bombs
		y = 5;
		for (int x = 1; x < 8; x += 2) {
			pieces[x][y] = new Piece(false, this, x, y, "bomb");
		}
		// water shields
		y = 6;
		for (int x = 0; x < 8; x += 2) {
			pieces[x][y] = new Piece(false, this, x, y, "shield");
		}		
		// water pawns
		y = 7;
		for (int x = 1; x < 8; x += 2) {
			pieces[x][y] = new Piece(false, this, x, y, "pawn");
		}
	}
	
	public Piece pieceAt(int x, int y) {
		return x < 0 || y < 0 || x > 7 || y > 7 ? null : pieces[x][y];
	}
    
    // are you for real
    // you are not doing encapsulation correctly
    // if you don't have p.getX() and p.getY()
    private int[] locationOf(Piece p) {
		for (int x = 0; x < 8; x += 1) {
			for (int y = 0; y < 8; y += 1) {
				if (pieceAt(x, y) == p) {
					return new int[] {x, y};
				}
			}
		}
		return new int[] {-1, -1};
    }
	
	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
			// tryna select a piece
			Piece target = pieceAt(x, y);
			if (selected == null) {
				// no pieces selected yet
				if ((target.isFire() && currentTurn != "fire")
				|| (!target.isFire() && currentTurn != "water")) {
						// wrong turn
						motd = "This piece is not yours!";
						return false;
				}
				return true;
			} else {
				// piece already selected
				if (target == selected) {
					// can select already-selected piece
					return true;
				}
				if (target.side() - selected.side() == 0) {
//					System.out.println("Changing selected piece on own side");
					return true;
				}
//				if (!validMove(selected.x, selected.y, x, y)) {
//					motd = "You can't move there!";
//					System.out.println("Not valid move");
//					return false;
//				}
//				if (hasPerformedCaptureAtLeastOnce) {
//					System.out.println("Already performed capture, so can perform again");
//					// if already performed capture, can perform another
//					return true;
//				}				
//				// if haven't moved, can perform first capture
//				if (hasMovedAtLeastOnce) {
//					motd = "You have already moved!";
//					return false;
//				}
				motd = "An enemy piece occupies the destination square.";
				return false;
			}
			
		} else {
			// tryna select an empty space
			if (selected == null) {
				motd = "Select a piece first.";
				return false;
			} else {
				int[] pos = locationOf(selected);
				if (!validMove(pos[0], pos[1], x, y)) {
					return false;					
				}
//				System.out.println(selected);
				if (hasMovedAtLeastOnce && !selected.hasCaptured()) {
					motd = "You have already moved this turn!";
					return false;
				}
				return true;
			}
		}
		
	}
	
	public void select(int x, int y) {
		Piece targetPiece = pieceAt(x, y);
		if (selected != null) {
			// a piece is already selected
			if (targetPiece != null && selected.side() - targetPiece.side() == 0) {
				// changing to piece of same team
				selected = targetPiece;
				return;
			}
			selected.move(x, y);
			hasMovedAtLeastOnce = true;
			if (selected.hasCaptured()) {
				hasPerformedCaptureAtLeastOnce = true;
			}
			if (pieceAt(x, y) != selected) {
				// piece must have exploded
				selected = null;
				motd = "Boom!";
			} else if (selected.hasCaptured()) {
				motd = "Capture successful!";
			} else {
				motd = "Move successful!";
			}
		} else {
			selected = targetPiece;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi < 0 || yi < 0 || xi > 7 || yi > 7) {
			return false;
		}
		if (xf < 0 || yf < 0 || xf > 7 || yf > 7) {
			return false;
		}
		// make sure move is valid according to whether or not is king
		if (!selected.isKing()) {
			if (selected.isFire() && yf < yi || !selected.isFire() && yf > yi) {
				motd = "Only crowned pieces can move backwards.";
//				System.out.println("Cannot move there because not king");
				return false;
			}
		}		
		if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
			// attempting move to adjacent
			if (selected.hasCaptured()) {
				motd = "A piece that has already captured cannot move unless capturing again.";
//				System.out.println("Selected piece has already captured and cannot move to adjacent");
				return false;
			}
			if (pieceAt(xf, yf) == null) {
//				System.out.println("Move to adjacent empty square approved");
				return true;
			}
			motd = "The destination square is not empty!";
//			System.out.println("Destination is not empty");
			return false;
		}
		if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
			// attempting move with radius 2
//			System.out.println("Attempting move with radius 2");
			if (pieceAt(xi + ((xf - xi) / 2), yi + ((yf - yi) / 2)) == null) {
				motd = "There is nothing to capture!";
//				System.out.println("Cannot capture move because nothing is being captured");
				return false;
			}
			if (pieceAt(xf, yf) != null) {
				motd = "Cannot capture move because the destination square is not empty!";
//				System.out.println("Destination for capture move is not empty");
				return false;
			}
//			System.out.println("Capture move approved");
			return true;
		}
		motd = "You cannot move there!";
//		System.out.println("Move not approved");
		return false;
	}
	
	public void place(Piece p, int x, int y) {
		if (p == null | x < 0 || y < 0 || x > 7 || y > 7) {
			return;
		}
		outer: // what's this for? see line 86 ;)
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] == p) {
					remove(i, j);
					break outer; // I know, this is absolutely incredible
				}
			}
		}
//		Piece removed = pieces[x][y];
		pieces[x][y] = p;
	}
	
	public Piece remove(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7) {
			System.err.println("Tried to remove piece from out-of-bounds coordinates");
			return null;
		}
		Piece removed = pieces[x][y];
		pieces[x][y] = null;
		return removed;
	}
	
	public boolean canEndTurn() {
		if (hasMovedAtLeastOnce || hasPerformedCaptureAtLeastOnce) {
			motd = "Turn ended.";
			return true;
		}
		motd = "Cannot end turn.";
		return false;
	}
	
	public void endTurn() {
		currentTurn = currentTurn.equals("fire") ? "water" : "fire"; 
		hasMovedAtLeastOnce = hasPerformedCaptureAtLeastOnce = false;
		selected = null;
		resetPiecesHasCaptured(this);
//		System.out.println("Turn now belongs to " + currentTurn);
	}
	
	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            Piece p = pieceAt(i, j);
	            if (p != null) {
	            	if (p.isFire()) {
	            		firePieces += 1;
	            	} else {
	            		waterPieces += 1;
	            	}
	            }
	        }
	    }
	    if (firePieces == 0 && waterPieces == 0) {
	    	return "No one";
	    }
	    if (firePieces == 0) {
	    	return "Water";
	    }
	    if (waterPieces == 0) {
	    	return "Fire";
	    }
	    return null;
	}

	private void dumpState() {
		System.out.println("Board [selected="
				+ selected + ", hasMovedAtLeastOnce=" + hasMovedAtLeastOnce
				+ ", hasPerformedCaptureAtLeastOnce="
				+ hasPerformedCaptureAtLeastOnce + ", currentTurn="
				+ currentTurn + "]");
	}

	private static void drawBoard(int N, Board b) {
		StdDrawPlus.clear();
        StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
        String turnString = b.currentTurn.substring(0,1).toUpperCase() + b.currentTurn.substring(1);
		StdDrawPlus.text(4, -0.25, "It is currently " + turnString + "'s turn."); 
		StdDrawPlus.text(4, 8.155, b.motd); 
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	// draw actual board
	            if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(GRAY);
	            else                  StdDrawPlus.setPenColor(RED);
	            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            // draw piece (if present)
	            Piece p = b.pieceAt(i, j);
	            if (p != null) {
	            	if (p == b.selected) {
	            		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	            	}
	            	StdDrawPlus.picture(i + 0.5, j + 0.5, getFilename(p), 1, 1);
	            }
	        }
	    }
	}
	
	
	private static String getFilename(Piece p) {
		return "img/" + getType(p) + "-" +
		           (p.isFire() ? "fire" : "water") + (p.isKing() ? "-crowned" : "")
		           + ".png"; 
	}
	
	private static String getType(Piece p) {
//		return p.isBomb() ? "bomb" : (p.isShield() ? "shield" : "pawn");
		return quinternary(p.isBomb(), p.isShield(), "bomb", "shield", "pawn");
	}
	
	// Because things aren't already abstract enough
	private static <T> T quinternary(boolean predicate1, boolean predicate2,
			                        T consequent, T alternativeConsequent,
			                        T alternativeAlternative) {
		return predicate1 ? consequent :
			   (predicate2 ? alternativeConsequent : alternativeAlternative);
	}
	
	private static void handleInput(Board b) {
        if (StdDrawPlus.mousePressed()) {
            double xRaw = StdDrawPlus.mouseX();
            double yRaw = StdDrawPlus.mouseY();
            int x = (int) xRaw;
            int y = (int) yRaw;
//            System.out.println(x + " | " + y + " clicked");
//            System.out.println("Selection is " + (b.canSelect(x, y) ? "valid" : "not valid"));
            if (b.canSelect(x, y)) {
            	b.select(x, y);
            }
        }
        if (StdDrawPlus.isSpacePressed()) {
        	if (b.canEndTurn()) {
        		b.endTurn();
        	}
        }
        if (StdDrawPlus.isNPressed()) {
        	b.dumpState();
        }
	}
	
	private static void resetPiecesHasCaptured(Board b) {
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            Piece p = b.pieceAt(i, j);
	            if (p != null) {
	            	p.doneCapturing();
	            }
	        }
	    }		
	}
	
	private static boolean isGameOver(Board b) {
		String winner = b.winner();
		if (winner != null) {
			b.motd = winner + " is the winner!";
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// given setup code
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        // game setup
        Board b = new Board(false);
        
        // main event loop
        for (;;) {
            drawBoard(N, b);
            handleInput(b);
            if (isGameOver(b)) {
//            	System.out.println("Game over");
                drawBoard(N, b); // final update
                StdDrawPlus.show(80);
            	break;
            }
            StdDrawPlus.show(80);
        }
	}
	
}
