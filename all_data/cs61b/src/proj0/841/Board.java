public class Board {

    private Piece[][] storageArray;
    private Piece g;
    private Piece y;
    private double mouseXloc;
    private double mouseYloc;
    private int mouseXInt;
    private int mouseYInt;
    private int initialx = 0;
    private int initialy = 0;
    private int movecount = 1;
    private boolean side = true; //Fire is true, Water is false
    private boolean hasmoved = false;
    private boolean hasSelected = false;
    private boolean capturestatus = false;
    private String piecename = "";
<<<<<<< HEAD
    //GIT CHANGE TRACK1
=======
>>>>>>> master

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Piece pieceAt(int x, int y) {
		if ((isOutofBounds(x, y)) || (this.storageArray[x][y] == null)) {
			return null;
		}
		if ((this.storageArray[x][y] != null) && (!isOutofBounds(x, y))) {
			return this.storageArray[x][y];
		}	
		return null;
	}

	public void place (Piece p, int x, int y) {
		if ((isOutofBounds(x,y)) || (p == null)) {
			return;
		}
		this.storageArray[x][y] = p;
	}

	private boolean isPlayerPiece(Piece piece) {
		if (piece == null) {
			return false;
		}
		if (this.side == piece.isFire()) {
			return true;
		}
		return false;
	}

	private int direction(Piece piece) {
		int forward = 0;
		if (piece.isFire()) {
			forward = forward + 1;
		}
		if (!piece.isFire()) {
			forward = forward - 1;
		}
		return forward;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece initialpiece = pieceAt(xi, yi); 
		if (initialpiece == null) {
			System.out.println("Cannot call select on a null initial position.");
			return false;
		}
		Piece finalpiece = pieceAt(xf, yf);
		int a = Math.abs(xf - xi);
		int b = Math.abs(yf - yi);
		int c = yf - yi;
		int d = direction(initialpiece);

		if ((a == 2) && (b == 2)) {
			int midx = (xf + xi) / 2;
			int midy = (yf + yi) / 2;
			Piece midpiece = pieceAt(midx, midy);
			if (midpiece == null) {
				return false;
			}
			if ((midpiece != null) && (!isPlayerPiece(midpiece)) && (!initialpiece.isKing())) {
				if (initialpiece.isFire() && (c > d)) {
					 return true;
				}
				if (!initialpiece.isFire() && (c < d)) {
					return true;
				}
				return false;
			}
			if ((midpiece != null) && (!isPlayerPiece(midpiece)) && (initialpiece.isKing())) { 
				return true;
			} 
			return false;
		}

		if ((a == 1) && (b == 1)) {
			if ((finalpiece == null) && (!initialpiece.isKing())) {
				if (c == d) {
					return true;
				}
				return false;
			}
			if ((finalpiece == null) && (initialpiece.isKing())) {
				return true;
			}
			return false;
		}
		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		Piece initialpiece = pieceAt(xi, yi); 
		if (initialpiece == null) {
			System.out.println("Cannot call validCapture on a null initial position.");
			return false;
		}
		Piece finalpiece = pieceAt(xf, yf);
		int a = Math.abs(xf - xi);
		int b = Math.abs(yf - yi);
		int c = yf - yi;
		int d = direction(initialpiece);

		if ((a == 2) && (b == 2)) {
			int midx = (xf + xi) / 2;
			int midy = (yf + yi) / 2;
			Piece midpiece = pieceAt(midx, midy);
			if (midpiece == null) {
				return false;
			}
			if ((midpiece != null) && (!isPlayerPiece(midpiece)) && (!initialpiece.isKing())) {
				if (initialpiece.isFire() && (c > d)) {
					 return true;
				}
				if (!initialpiece.isFire() && (c < d)) {
					return true;
				}
				return false;
			}
			if ((midpiece != null) && (!isPlayerPiece(midpiece)) && (initialpiece.isKing())) { 
				return true;
			} 
			return false;
		}
		return false;

	}

	public boolean canSelect(int x, int y) {
		Piece piece = this.storageArray[x][y];
		if (isPlayerPiece(piece)) {
			if (piece != null) {
				if (this.hasmoved == false) {
					this.initialx = x;
					this.initialy = y;
					return true;
				} 
			return false;
			}
		return false;
		}
		if (piece == null) {
			if ((this.hasmoved == false) && (validMove(this.initialx, this.initialy, x, y)) && (this.hasSelected)) {
				return true;
			}
			if ((this.hasSelected == true) && (this.capturestatus == true) && (validCapture(this.initialx, this.initialy, x, y))) {
				return true;
			}
		return false;
		}
	return false;
	}

	public void select(int x, int y) {
		Piece piece = pieceAt(x, y);
		if (piece != null) {
			if (piece.isShield()) {
				this.piecename = "Shield";
			}
			if (piece.isBomb()) {
				this.piecename = "Bomb";
			}
			if ((!piece.isBomb()) && (!piece.isShield())) {
				this.piecename = "Pawn";
			}
			this.g = piece;
			this.hasSelected = true;
			initialx = x;
			initialy = y;
			System.out.println(this.piecename + " piece selected at (" + x + ", " + y + ")");
		}
		if (piece == null) {
			String str = "";	
			if ((Math.abs(initialx - x) == 2) && (pieceAt(initialx, initialy) != null) && (pieceAt(initialx, initialy).isBomb())) {
				this.g.move(x, y);
				this.hasmoved = true;
				hasSelected = false;
				initialx = 999;
				initialy = 999;
				return;
			}
			this.g.move(x, y);
			initialx = x;
			initialy = y;
			this.hasmoved = true;
			//check if captured?
			this.hasPieceCaptured(g);
			if (this.hasPieceCaptured(g)) {
				str = "has";
			}
			if (!this.hasPieceCaptured(g)) {
				str = "has not";
			}
			System.out.println(this.piecename + " piece has moved to (" + x + ", " + y + ")");
			System.out.println("Piece " + str + " captured.");	
			}
		}	

	public Piece remove(int x, int y) { 
		if (isOutofBounds(x, y)) {
			System.out.println("Cannot remove, out of bounds.");
			return null;
		}
		if (this.storageArray[x][y] == null) {
			System.out.println("No piece is available to remove.");
			return null;
		} else {
			Piece piece = this.storageArray[x][y];
			this.storageArray[x][y] = null;
			//piece.doneCapturing();
			return piece;
		}
	}

	public boolean canEndTurn() { 
		if (this.hasmoved || this.capturestatus) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		String player = "";
		String nextplayer = "";
        if (this.side) {
			player = "Fire";
			nextplayer = "Water";
		}
		if (!this.side) {
			player = "Water";
			nextplayer = "Fire";
		}
		System.out.println("Player " + player + " has ended turn.");
		System.out.println("Current turn: " + this.movecount);
		System.out.println("//-------------------------------------------------------------------------------------------//");	
		this.hasSelected = false;
		this.capturestatus = false;
		this.hasmoved = false;
		this.movecount = this.movecount + 1;
		this.side = (!this.side);
		System.out.println("Player " + nextplayer + "'s turn has started.");
	}

	public String winner() {
		//
		int firecount = 0;
		int watercount = 0;
		int emptycount = 0;
		String winner = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = this.storageArray[i][j];
                if (piece == null) {
                	emptycount = emptycount + 1;
                }
                else if (piece.isFire()) {
                	firecount = firecount + 1;
                }
                else if (!piece.isFire()) {
                	watercount = watercount + 1;
                }  
            }
        }
        if (firecount == watercount) {
        	winner = "No one";
        }
        if ((firecount == 0) && (watercount > 0)) {
        	winner = "Water";
        }
        if ((watercount == 0) && (firecount > 0)) {
        	winner = "Fire";
        }
        if ((firecount > 0) && (watercount > 0)) {
        	return null;
        }
        //ACCOUNT FOR STALEMATE SITUATION;
        return winner;
	}

	private boolean hasPieceCaptured(Piece p) { 
		if (p.hasCaptured()) {
			this.capturestatus = true;
			return true;
		}
		return false;
	}

	//BOARD CONSTRUCTOR METHODS (DONE DO NOT TOUCH)

	public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == true) {
        	storageArray = new Piece[8][8];
        } else {
        storageArray = new Piece[8][8];
        	for (int i = 0; i < 8; i += 2) {
	    		this.storageArray[i][6] = new Piece(false, this, i, 6, "shield");
	    		this.storageArray[(i + 1)][7] = new Piece(false, this, i + 1, 7, "pawn");
	    		this.storageArray[(i + 1)][5] = new Piece(false, this, i + 1, 5, "bomb");
	      		this.storageArray[(i + 1)][1] = new Piece(true, this, i + 1, 1, "shield");
	      		this.storageArray[i][0] = new Piece(true, this, i, 0, "pawn");
	      		this.storageArray[i][2] = new Piece(true, this, i, 2, "bomb");
    		}
    	}
    }

    private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
    	StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {	
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece piece = this.storageArray[i][j];
                if (piece != null) {
                	String pieceName = nameConstructor(piece);
                    StdDrawPlus.picture(i + .5, j + .5, pieceName, 1, 1);

            	}
            	if ((this.hasSelected) && (i == initialx) && (j == initialy) && (piece != null)) {
            		String pieceName = nameConstructor(piece);
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            		StdDrawPlus.picture(i + .5, j + .5, pieceName, 1, 1);
            	}
            }
        }
    }

    private void keyListener() {
    	while (true) {   
    		drawBoard(8);		
            if (StdDrawPlus.mousePressed()) {
            	double mouseXloc = StdDrawPlus.mouseX();
                double mouseYloc = StdDrawPlus.mouseY();
                this.mouseXInt = (int) mouseXloc;
                this.mouseYInt = (int) mouseYloc;
                if (canSelect(this.mouseXInt, this.mouseYInt)) {
                	select(this.mouseXInt, this.mouseYInt);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (canEndTurn()) {
            		endTurn();
            	}
            }
            if (StdDrawPlus.isNPressed()) {
    			System.out.println("New game has been initialized.");
            	Board b = new Board(false);
            	b.keyListener();
    			b.drawBoard(8);
            }
            drawBoard(8);
            StdDrawPlus.show(100);         
    	}
    }

    private String nameConstructor(Piece piecer) {
    	String name = "";
    	if (piecer.isFire()) {
    		if (piecer.isShield()) {
    			name = name + "img/shield-fire";
    		}
    		if (piecer.isBomb()) {
    			name = name + "img/bomb-fire";
    		}
    		if ((!piecer.isShield()) && (!piecer.isBomb())) {
    			name = name + "img/pawn-fire";
    		}
    	} else {
    		if (piecer.isShield()) {
    			name = name + "img/shield-water";
    		}
    		if (piecer.isBomb()) {
    			name = name + "img/bomb-water";
    		}
    		if ((!piecer.isShield()) && (!piecer.isBomb())) {
    			name = name + "img/pawn-water";
    		}
    	}
    	if (piecer.isKing()) {
    		name = name + "-crowned";
    	}
    	name = name + ".png";
    	return name;
    }
	
	private boolean isOutofBounds(int x, int y) {
		if ((x < 0) || (y < 0) || (x > 7) || (y > 7)) {
			return true;
		} else {
			return false;
		}
	}
	
	//MAIN

	public static void main(String[] args) {
    	Board b = new Board(false);
    	b.keyListener();
    	b.drawBoard(8);
	}
}

