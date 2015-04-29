public class Board{

	/** Location of pieces. */
    private Piece[][] pieces;
    private boolean fireTurn = true;
    private int xSelectedPiece = -1;
    private int ySelectedPiece = -1;
    private boolean moved = false;

    public static void main(String[] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board theBoard = new Board(false);

        while(!theBoard.gameOverTest()) {
            theBoard.drawBoard(N);
			if (StdDrawPlus.mousePressed()) {
            	double x = StdDrawPlus.mouseX();
            	double y = StdDrawPlus.mouseY();
            	int xCoord = (int) x;
            	int yCoord = (int) y;
            	if (theBoard.canSelect(xCoord, yCoord)){
            		theBoard.select(xCoord,yCoord);
            	}
        	}  
        	if (StdDrawPlus.isSpacePressed()){
        		if (theBoard.canEndTurn()){
        			theBoard.endTurn();
        		}
        	}  
            StdDrawPlus.show(50);
        }

	}

	private void selectPiece(int x, int y){
		xSelectedPiece = x;
		ySelectedPiece = y;
	}

    /**Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html*/
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (i==xSelectedPiece && j==ySelectedPiece){
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }

                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, imageName(i,j), 1, 1);
                }
            }
        }
    }

    private String type(int x, int y){
    	if (pieces[x][y].isShield()){
    		return "shield";
    	} else if (pieces[x][y].isBomb()){
    		return "bomb";
    	} else {
    		return "pawn";
    	}
    }

    private String imageName(int x, int y){
    	if (pieces[x][y].isFire()) {
    		if (pieces[x][y].isKing()){
    			return "img/" + type(x,y) + "-fire-crowned.png";
    		}
			return "img/" + type(x,y) + "-fire.png"; 
		} else {
			if (pieces[x][y].isKing()){
				return "img/" + type(x,y) + "-water-crowned.png";
			}
			return "img/" + type(x,y) + "-water.png";
		}
    }

    private void drawInitialBoard(int N){
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        drawRow(0,0,"img/pawn-fire.png");
        drawRow(1,1,"img/shield-fire.png");
        drawRow(0,2,"img/bomb-fire.png");
        drawRow(1,5,"img/bomb-water.png");
        drawRow(0,6,"img/shield-water.png");
        drawRow(1,7,"img/pawn-water.png");
    }

    private void drawRow(int i, int j, String img){
    	for (int k = i; k < 8; k += 2){
    		StdDrawPlus.picture(k + .5, j + .5, img, 1, 1);
    	}
    }

    private void makeNull(){
    	for (int i = 0; i < 8; i++){
    		for (int j = 0; j < 8; j++){
    			pieces[i][j] = null;
    		}
    	}
    }
    
    private void initializeBoard(Board b){
		pieces[0][0] = new Piece(true, b, 0, 0, "pawn");
		pieces[2][0] = new Piece(true, b, 2, 0, "pawn");
		pieces[4][0] = new Piece(true, b, 4, 0, "pawn");
		pieces[6][0] = new Piece(true, b, 6, 0, "pawn");
		pieces[1][1] = new Piece(true, b, 1, 1, "shield");
		pieces[3][1] = new Piece(true, b, 3, 1, "shield");
		pieces[5][1] = new Piece(true, b, 5, 1, "shield");
		pieces[7][1] = new Piece(true, b, 7, 1, "shield");
		pieces[0][2] = new Piece(true, b, 0, 2, "bomb");
		pieces[2][2] = new Piece(true, b, 2, 2, "bomb");
		pieces[4][2] = new Piece(true, b, 4, 2, "bomb");
		pieces[6][2] = new Piece(true, b, 6, 2, "bomb");
		pieces[1][5] = new Piece(false, b, 1, 5, "bomb");
		pieces[3][5] = new Piece(false, b, 3, 5, "bomb");
		pieces[5][5] = new Piece(false, b, 5, 5, "bomb");
		pieces[7][5] = new Piece(false, b, 7, 5, "bomb");
		pieces[0][6] = new Piece(false, b, 0, 6, "shield");
		pieces[2][6] = new Piece(false, b, 2, 6, "shield");
		pieces[4][6] = new Piece(false, b, 4, 6, "shield");
		pieces[6][6] = new Piece(false, b, 8, 6, "shield");
		pieces[1][7] = new Piece(false, b, 1, 7, "pawn");
		pieces[3][7] = new Piece(false, b, 3, 7, "pawn");
		pieces[5][7] = new Piece(false, b, 5, 7, "pawn");
		pieces[7][7] = new Piece(false, b, 7, 7, "pawn");
    }

	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty){
			pieces = new Piece[8][8];
			makeNull();
		} else{
			pieces = new Piece[8][8];
			makeNull();
			initializeBoard(this);
		}
	}

	private boolean withinBoardAndGray(int x, int y){
		if (x < 0 || x > 7 || y < 0 || y > 7 || (x%2 != y%2)){
			return false;
		} else {
			return true;
		}
	}

	public Piece pieceAt(int x, int y){
		if (!withinBoardAndGray(x, y)){
			return null;
		}
		else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y){
		if (withinBoardAndGray(x,y)){
			if (pieces[x][y] != null && !moved){
				if ((pieces[x][y].isFire() && fireTurn) || (!pieces[x][y].isFire() && !fireTurn)){
					return true;
				}
			} if (xSelectedPiece > -1 && ySelectedPiece > -1) {
				if (!moved && basicMoves(xSelectedPiece, ySelectedPiece, x, y)){
					return true;
				} if ((validJump(xSelectedPiece, ySelectedPiece, x, y) && !moved) || (validJump(xSelectedPiece, ySelectedPiece, x, y) && moved && pieces[xSelectedPiece][ySelectedPiece].hasCaptured())){
					return true;
				}
			}
		}
		return false;
	}

	private boolean validJump(int xi, int yi, int xf, int yf){
		if (withinBoardAndGray(xf,yf) && pieces[xf][yf] == null){
			if (xi + 2 == xf || xi - 2 == xf){
				//for fire
				if (yi + 2 == yf && pieces[xi][yi].isFire()){
					if (xi + 2 == xf && !pieces[xi+1][yi+1].isFire()){
						return true;
					} else if (xi - 2 == xf && !pieces[xi-1][yi+1].isFire()){
						return true;
					} 
				}
				//for rain
				else if (yi - 2 == yf && !pieces[xi][yi].isFire()){
					if (xi + 2 == xf && pieces[xi+1][yi-1].isFire()){
						return true;
					} else if (xi - 2 == xf && pieces[xi-1][yi-1].isFire()){
						return true;
					}
				}
				//kings
				else if (pieces[xi][yi].isKing()){
					if (yi + 2 == yf && xi + 2 == xf && pieces[xi][yi].isFire() != pieces[xi+1][yi+1].isFire()){
						return true;
					} if (yi + 2 == yf && xi - 2 == xf && pieces[xi][yi].isFire() != pieces[xi-1][yi+1].isFire()){
						return true;
					} if (yi - 2 == yf && xi - 2 == xf && pieces[xi][yi].isFire() != pieces[xi-1][yi-1].isFire()){
						return true;
					} if (yi - 2 == yf && xi + 2 == xf && pieces[xi][yi].isFire() != pieces[xi+1][yi-1].isFire()){
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean basicMoves(int xi, int yi, int xf, int yf){
		if (withinBoardAndGray(xf,yf) && pieces[xf][yf] == null){
			//basic moves
			if (xi + 1 == xf || xi - 1 == xf){
				//basic moves for fire
				if (yi + 1 == yf && (pieces[xi][yi].isFire() || pieces[xi][yi].isKing())){
					return true;
				}
				//basic moves for rain
				else if (yi - 1 == yf && (!pieces[xi][yi].isFire() || pieces[xi][yi].isKing())){
					return true;
				} 
			}
		}
		return false;
	}

	public void select(int x, int y){
		if (xSelectedPiece > -1 && ySelectedPiece > -1){
			if (pieces[x][y] == null){
				pieces[xSelectedPiece][ySelectedPiece].move(x, y);
				pieces[x][y] = remove(xSelectedPiece,ySelectedPiece);
				moved = true;
				if (pieces[x][y].hasCaptured()){
					int dyingx = (xSelectedPiece + (x - xSelectedPiece)/2);
					int dyingy = (ySelectedPiece + (y - ySelectedPiece)/2);
					remove(dyingx,dyingy);
					if (pieces[x][y].isBomb()){
						for (int i = x-1; i<x+2; i++){
							for (int j = y-1; j<y+2; j++){
								if (withinBoardAndGray(i,j) && pieces[i][j] != null && !pieces[i][j].isShield()){
									remove(i,j);
									xSelectedPiece = -1;
									ySelectedPiece = -1;
								}
							}
						}
					}
				}
			}
		}
		if (pieces[x][y] != null){
			selectPiece(x,y);
		}
	}

	public void place(Piece p, int x, int y){
		if (p != null){
			if (withinBoardAndGray(x,y)){
				pieces[x][y] = p;
			}
		}
	}

	public Piece remove(int x, int y){
		if (!withinBoardAndGray(x, y)){
			System.out.println("Sorry! The input(x,y) is out of range! :'(");
			return null;
		} else if (pieces[x][y] == null){
			System.out.println("Whoops! There wasn't a piece there...");
			return null;
		} else {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
	}

	public boolean canEndTurn(){
		if (moved){
			return true;
		} else {
			return false;
		}
		//a piece must have moved or performed a capture
		//plus the other junk required
	}

	public void endTurn(){
		//called at the end of each turnss
		fireTurn = !fireTurn;
		moved = false;
		if (xSelectedPiece > -1){
			pieces[xSelectedPiece][ySelectedPiece].doneCapturing();
		}
		xSelectedPiece = -1;
		ySelectedPiece = -1;
		//probably does other stuff	
	}

	private boolean gameOverTest(){
		int pieceCount = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieces[i][j] != null){
					pieceCount++;
				}
			}
		}
		if (pieceCount > 0){
			return false;
		} else {
			return true;
		}
	}

	public String winner(){
		int fireCount = 0;
		int rainCount = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieces[i][j] != null){
					if (pieces[i][j].isFire()){
						fireCount++;
					} else {
						rainCount++;
					}
				}
			}
		}
		if (fireCount == 0 && rainCount == 0){
			return "No One";
		} else if (fireCount > rainCount){
			return "Fire";
		} else if (rainCount > fireCount){
			return "Water";
		} else {
			return null;
		}
	}

	private boolean containsPiece(int x, int y){
		if (pieces[x][y] != null){
			return true;
		} else {
			return false;
		}
	}
}