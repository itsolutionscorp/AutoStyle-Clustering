public class Board {
	private boolean shouldBeEmpty;
	private static Piece[][] pieces;
	private Piece selectedPiece;
	private boolean hasMoved = false;
	private int player = 0;
  private int pieceX;
  private int pieceY;

  //the main method of Board; starts the GUI-supported version of the game
	public static void main(String[] args) {
		Board b = new Board(false);
    int N = 8;
    StdDrawPlus.setXscale(0, N);
    StdDrawPlus.setYscale(0, N);
    while(true) {
      b.drawBoard(N);
      if (StdDrawPlus.mousePressed()) {
        double x = StdDrawPlus.mouseX();
        double y = StdDrawPlus.mouseY();
        if (b.canSelect((int) x, (int) y)) {
          b.select((int) x, (int) y);
        } 
      }   
      if (b.canEndTurn()) {
        if (StdDrawPlus.isSpacePressed()) {
          b.endTurn();
        } 
      }    
      StdDrawPlus.show(100);
    }
  }

  //drawBoard helper method: used to highlight the selected piece's background
  private void drawSelect(int x, int y) {
    Piece current = pieceAt(x, y);
    StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
    if (current != null) {
      StdDrawPlus.picture((int) x + .5, (int) y + .5, this.getImage(current), 1, 1);
    }
  }

  //draws the board (wheneve called) based on the position of each Piece object within the pieces array
	private void drawBoard(int N) {
  	for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        if (pieces[i][j] != null) {
          if (selectedPiece != null && pieceAt(i, j) == selectedPiece) {
            drawSelect(i, j);
          }
          Piece current = pieces[i][j];
          StdDrawPlus.picture(i + .5, j + .5, this.getImage(current), 1, 1);
        }
      }
    }
  }

  //to reduce the amount of code required for drawing a piece by extracting the appropriate file name
  private String getImage(Piece p) {
    String path = "img/";
  	if (p.isBomb()) {
  		path += "bomb";
    } else if (p.isShield()) {
    	path += "shield";
    } else {
    	path += "pawn";
  	}

    if (p.isFire()) {
  		path += "-fire";
    } else {
  		path += "-water";
  	}

    if (p.isKing()) {
  		path += "-crowned.png";
  	} else {
    	path += ".png";
    }
  	return path;
  }

  //Board constructor; populates the pieces array with all Piece objects at their initial positions
	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		this.pieces = new Piece[8][8];
		if (shouldBeEmpty == false) {
			for (int y = 0; y < 8; y++) {
				if (y % 2 == 0) {
					for (int x = 0; x < 8; x+=2) {
						if (y == 0) {
							pieces[x][y] = new Piece(true, this, x, y, "pawn");
						} else if (y == 2) {
							pieces[x][y] = new Piece(true, this, x, y, "bomb");
						} else if (y == 6) {
							pieces[x][y] = new Piece(false, this, x, y, "shield");
						}
					}
				} else {
					for (int x = 1; x < 8; x+=2) {
						if (y == 1) {
							pieces[x][y] = new Piece(true, this, x, y, "shield");
						} else if (y == 5) {
							pieces[x][y] = new Piece(false, this, x, y, "bomb");
						} else if (y == 7) {
							pieces[x][y] = new Piece(false, this, x, y, "pawn");
						}
					}
				}
			}
		}
	}

  //returns the Piece object at (x, y), else returns null if no piece present
	public Piece pieceAt(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

  //returns whether or not piece or empty space at (x, y) can be selected at a given moment
	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
      if (player == pieceAt(x, y).side() && (selectedPiece == null || (selectedPiece != null && !hasMoved))) {
          return true;
      } else {
        return false;
      }
    } else {
      if (validMove(pieceX, pieceY, x, y)) {
        if (selectedPiece != null && (!hasMoved || selectedPiece.hasCaptured() && canCapture(pieceX, pieceY, x, y))) {
          return true;
        } else {
          return false;
        }
      }
      return false;
    }
  }

  //helper method for validMove/canSelect: returns whether or not a piece can legally perform a capture moving from some (xi, yi) to some (xf, yf)
  private boolean canCapture(int xcurr, int ycurr, int xdest, int ydest) {
    Piece current = pieceAt(xcurr, ycurr);
    if (pieceAt(xdest, ydest) != null) {
      return false;
    }
    if (Math.abs(xdest - xcurr) == 2 && Math.abs(ydest - ycurr) == 2) {
      int xcap = 0;
      int ycap = 0;
      if (xdest > xcurr) {
        xcap += 1;
      } else {
        xcap -= 1;
      }
      if (ydest > ycurr) {
        ycap += 1;
      } else {
        ycap -= 1;
      }
      if (pieceAt(xcurr + xcap, ycurr + ycap) == null || current.isFire() == pieceAt(xcurr + xcap, ycurr + ycap).isFire()) {
        return false;
      }
      return true;
    }
    return false;
  }

  //helper method for canSelect: returns whether or not a piece can move from some (xi, yi) to some (xf, yf); invokes canCapture
  private boolean validMove(int xi, int yi, int xf, int yf) {
    Piece current = pieceAt(xi, yi);
    if (xf > 7 || xf < 0 || yf > 7 || yf < 0) {
      return false;
    } else if (xi > 7 || xi < 0 || yi > 7 || yi < 0) {
      return false;
    } else if (current == null) {
      return false;
    } else if (Math.abs(xf - xi) != Math.abs(yf - yi)) {
      return false;
    }

    if ((yf - yi == 1 && Math.abs(xf - xi) == 1)) {
      if (current.isFire()) {
        return true;
      } else {
        if (current.isKing()) {
          return true;
        } else {
          return false;
        }
      }
    } else if (yf - yi == -1 && Math.abs(xf - xi) == 1) {
      if (current.isFire()) {
        if (current.isKing()) {
          return true;
        } else {
          return false;
        }
      } else {
        return true;
      }
    } else if (canCapture(xi, yi, xf, yf)) {
      return true;
    } else {
      return false;
    }
  }

  //executes the action of selecting a piece/empty space at a given (x, y) position
	public void select(int x, int y) {
    pieceX = x;
    pieceY = y;
    if (pieceAt(x, y) != null) {
      selectedPiece = pieceAt(x, y);
    } else {
      selectedPiece.move(x, y);
      hasMoved = true;
    }
	}

  //places a given Piece p at position (x, y)
	public void place(Piece p, int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return;
		}
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (pieceAt(i, j) == p) {
          p = remove(i, j);
        }
      }
    }
		pieces[x][y] = p;
	}

  //removes and returns a piece from (x, y), else null if no piece exists at this position
	public Piece remove(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			System.out.println("Outside board range.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("No piece at location.");
			return null;
		}
		Piece removed = pieceAt(x, y);
		pieces[x][y] = null;
		return removed;
	}

  //returns whether or not the current player is allowed to end their turn
	public boolean canEndTurn() {
    if (selectedPiece != null) {
      return (selectedPiece.hasCaptured() || hasMoved);
    } 
    return false;
	}

  //ends the current player's turn and switches to the other player
	public void endTurn() {
		hasMoved = false;
		player = 1 - player;
    selectedPiece.doneCapturing();
    selectedPiece = null;
	}

  //returns which side has won the game: Fire, Water, or neither one
	public String winner() {
	  int fireCount = 0;
    int waterCount = 0;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (pieceAt(i, j) != null) {
          if (pieceAt(i, j).isFire()) {
            fireCount += 1;
          } else {
            waterCount += 1;
          }
        }
      }
    }
    if (fireCount > 0 || waterCount > 0) {
      if (fireCount == 0) {
        return "Water";
      } else if (waterCount == 0) {
        return "Fire";
      } else {
        return null;
      }
    }
    return "No one";
  }
}
