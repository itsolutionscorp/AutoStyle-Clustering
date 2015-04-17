public class Board {

	public Piece[][] boardarray;
    private Piece current;
    private int side;
    private String status;
    private int xi;
    private int yi;

	public static void main (String arg[]) {
		Board board = new Board(false);
		board.drawBoard(8);
		board.drawPiecesDefault(8);
		
        /*side = 0; /* 0 - Fire, 1 - Water */
        /*status = null;
        current = null;
        while (status == null) {
            if (StdDrawPlus.mousePressed() == true) {
                int x = (int)Math.floor(StdDrawPlus.mouseX());
                int y = (int)Math.floor(StdDrawPlus.mouseY());
                this.xi = x;
                this.yi = y;
                if (board.canSelect(x, y) == true) {
                    board.select(x,y);
                }
                if (StdDrawPlus.mousePressed() == true) {
                    int xnew = (int)Math.floor(StdDrawPlus.mouseX());
                    int ynew = (int)Math.floor(StdDrawPlus.mouseY());
                    if (board.canSelect(xnew, ynew) == true) {
                        board.select(xnew,ynew);
                        current.move(xnew, ynew);
                    }
                }
	       }
            if (StdDrawPlus.isSpacePressed() == true) {
                if (this.canEndTurn() == true) {
                    this.endTurn();
                    status = this.winner();
                }
            }
        } */

    }
		public Board(boolean shouldBeEmpty) {

			if (shouldBeEmpty == true) {
				this.boardarray = new Piece[8][8]; 
			}
			if (shouldBeEmpty == false) {
				this.boardarray = new Piece[8][8];
				int j = 0;
				while (j < 8) {
					this.boardarray[j][0] = new Piece(true, this, j, 0, "pawn-fire");
					this.boardarray[j][2] = new Piece(true, this, j, 2, "bomb-fire");
					this.boardarray[j][6] = new Piece(false, this, j, 6, "shield-water");
					j = j + 2;
				}
				int i = 1;
				while (i <8) {
					this.boardarray[i][1] = new Piece(true, this, i, 1, "shield-fire");
					this.boardarray[i][5] = new Piece(false, this, i, 5, "bomb-water");
					this.boardarray[i][7] = new Piece(false, this, i, 7, "pawn-water");
					i = i + 2;
				}
			}
		}

		public void drawBoard(int N) {
			StdDrawPlus.setXscale(0,N);
			StdDrawPlus.setYscale(0,N);
        	for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	}
        	}
		}

		private void drawPiecesDefault(int N) {
			StdDrawPlus.setXscale(0,N);
			StdDrawPlus.setYscale(0,N);
			int j = 0;
			while (j < N) {
                    StdDrawPlus.picture(j + .5, .5, "img/pawn-fire.png", 1, 1);
                    StdDrawPlus.picture(j + .5, 2.5, "img/bomb-fire.png", 1, 1);
                    StdDrawPlus.picture(j + .5, 6.5, "img/shield-water.png", 1, 1);
                    j = j+2;
            }
            int i = 1;
            while (i < N) {
            		StdDrawPlus.picture(i + .5, 1.5, "img/shield-fire.png", 1, 1);
            		StdDrawPlus.picture(i + .5, 5.5, "img/bomb-water.png", 1, 1);
                    StdDrawPlus.picture(i + .5, 7.5, "img/pawn-water.png", 1, 1);
                    i = i+2;
            }

        }

        public Piece pieceAt(int x, int y) {
        	if (x < 0 || x >= this.boardarray.length) {
        		return null;
        	}
        	if (y < 0 || y >= this.boardarray[0].length) {
        		return null;
        	}
        	return this.boardarray[x][y];
        } 

        public void place(Piece p, int x, int y) {
        	boolean xbounds = (x >= 0) && (x < this.boardarray.length);
        	boolean ybounds = (y >= 0) && (y < this.boardarray[0].length);
        	if ((xbounds == true) && (ybounds == true)) {
        		this.boardarray[x][y] = p;
        	}
        }

        private boolean validMove(int xi, int yi, int xf, int yf) {

        return true;    
        }

        public boolean canSelect(int x, int y) {
            Piece pc = this.boardarray[x][y];
            if (pc == null) {
                if ((current != null) && (xi == current.x) && (yi == current.y) && (this.validMove(xi, yi, x, y) == true)) {
                return true;
                }
                else if ((current.isKing() == true) && (current.hasCaptured() == true) && (this.validMove(current.x, current.y, x, y) == true)) {
                    return true;
                }
                else {
                    return false;
                }
            }
            
            if (pc.side() == side) {
                if (current == null) {
                    return true;
                }
                else if ((xi == current.x) && (yi == current.y))  {
                        return true;
                    }
                else {
                    return false;
                }

            }
            else {
                return false;
            }
        }

       	public void select(int x, int y) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            if (this.boardarray[x][y] != null) {
                current = pieceAt(x,y);
                xi = x;
                yi = y;
            }
            else {
                current.move(x,y);
            }

       	} 

        public Piece remove(int x, int y) {
            if (x < 0 || x >= this.boardarray.length) {
                System.out.println("Selection out of bounds");
                return null;
                            }
            if (y < 0 || y >= this.boardarray[0].length) {
                System.out.println("Selection out of bounds");
                return null;   
            }
            Piece removed = this.boardarray[x][y];
            if (this.boardarray[x][y] == null) {
                 System.out.println("No piece at" + x + "," + y + "to remove");
                return null;
               
            }
            else {
                this.boardarray[x][y] = null;
                if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            return removed;
            }
        }
        public boolean canEndTurn() {
            /* returns whether or not the current player can end their turn */
            if ((current.xi != current.x) && (current.yi != current.y)) {
                    return true;
                }
            else {
                return false;
            }
        }

        public void endTurn() {
            /*called at the end of each turn. handles switching of players, etc. */
            side = side ^ 1;
            current.doneCapturing();
            current = null;
        }

        public String winner() {
            int isFireTrue = 0;
            int isFireFalse = 0;
            for (int k=0; k<boardarray.length; ++k) {
                for (int l=0; l<boardarray[0].length; ++l) {
                    if (boardarray[k][l] != null) {
                        if (boardarray[k][l].isFire() == true) {
                            isFireTrue += 1;
                        }
                        else  {
                            isFireFalse += 1;
                        }
                    }

                }
            }
            if ((isFireFalse != 0) && (isFireTrue != 0)) {
                return null;
            }
            else if ((isFireFalse != 0) && (isFireTrue == 0)) {
                return "Water";
            }
            else if ((isFireFalse == 0) && (isFireTrue != 0)) {
                return "Fire";
            }
            else {
                return "No One";
            }

           
            
        }

}