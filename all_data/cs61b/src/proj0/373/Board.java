public class Board {
	private Board gameBoard;
	private Piece[][] pieceSet = new Piece[8][8];
	private String[][] pieceTypes = new String[8][8];
	private boolean[][] pieces = new boolean[8][8];
	private Piece currentPiece;
	private Piece currentPieceSelected = null;
	private int xClick;
	private int yClick;
	private boolean fireplay = true;
	private boolean didMove = false;
	private Piece occupied;
	private boolean firstClick = false;
	private boolean didCapture;
	private Piece occupiedBomb;
	private Piece pieceToRemove;
	private int xClickCurrent;
	private int yClickCurrent;
	private int waterScore;
	private int fireScore;
	private boolean bombExploded = false;
	private boolean highlightCurrent = false;
	private boolean firePiecesLeft = false;
	private boolean waterPiecesLeft = false;


	public Board(boolean shouldEmpty)  {
		if (shouldEmpty) {
			pieces = new boolean [8][8];
			waterPiecesLeft = false;
			firePiecesLeft = false;
			didMove = false;
			didCapture = false;

		} else {
			initialSet();
			didMove = false;
			didCapture = false;
		}
	}

	private void drawBoard(int N) {
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(j + 0.5, i + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[j][i]) {
						StdDrawPlus.picture(j + 0.5, i + 0.5, pieceTypes[j][i],1,1);
				}
			}
		}
    }


    private boolean isSpacePressed() {
    	if (StdDrawPlus.isSpacePressed()) {
    		return true;
    		
    	} else {
    		return false;
    	}
    }

	public void select(int x, int y) {
		if (pieces[x][y] == false) {
			{
			if (x == xClickCurrent - 2 && y == yClickCurrent + 2) {
				remove(xClickCurrent - 1 , yClickCurrent + 1);
				didCapture = true;
				if (currentPieceSelected.isBomb()) {
					bombExplosion(x,y);
					bombExploded = true;
					remove(xClickCurrent,yClickCurrent);
				}
			} else if (x == xClickCurrent - 2 && y == yClickCurrent - 2) {
				remove(xClickCurrent - 1 , yClickCurrent - 1);
				didCapture = true;
				if (currentPieceSelected.isBomb()) {
					bombExplosion(x,y);
					bombExploded = true;
					remove(xClickCurrent,yClickCurrent);
				}
			} else if (x == xClickCurrent + 2 && y == yClickCurrent - 2) {
				remove(xClickCurrent + 1 , yClickCurrent - 1);
				didCapture = true;
				if (currentPieceSelected.isBomb()) {
					bombExplosion(x,y);
					bombExploded = true;
					remove(xClickCurrent,yClickCurrent);
				}
			} else if (x == xClickCurrent + 2 && y == yClickCurrent + 2) {
				remove(xClickCurrent + 1 , yClickCurrent + 1);
				didCapture = true;
				if (currentPieceSelected.isBomb()) {
					bombExplosion(x,y);
					bombExploded = true;
					remove(xClickCurrent,yClickCurrent);
				}
			}

			if (!bombExploded) {
			currentPieceSelected.move(x,y); 	
			xClickCurrent = x;
			yClickCurrent = y;
			}
		}
			bombExploded = false;
			didMove = true;
		} 

		if (pieces[x][y] == true && !didMove) {
			currentPieceSelected = pieceAt(x,y);
			xClickCurrent = x;
			yClickCurrent = y;
		}
	}

	public boolean canSelect(int x, int y) {
		if (didCapture) {
			if (captureLeftUp(x,y) || captureLeftD(x,y) || captureRightUp(x,y) || captureRightD(x,y)) {
				return true;
			} 

			currentPieceSelected.move(xClickCurrent,yClickCurrent);
			return false;
		}

		if (pieces[x][y] && didMove) {
			return false;
		}

		if (!pieces[x][y] && currentPieceSelected != null && !didCapture){
			if (canMove(x, y)) {
					return true;
			}	
		}
		
			if (pieces[x][y] && !didCapture && !didMove) { 
				currentPiece = pieceAt(x,y);
				if (currentPiece.isFire() && fireplay) {
					xClickCurrent = x;
					yClickCurrent = y;
					return true;
				} else if (!currentPiece.isFire() && !fireplay) {
					xClickCurrent = x;
					yClickCurrent = y;
					return true;
				}
				didMove = false;
			}
		return false;

	}

	public boolean canEndTurn() {
			if (didMove == true) {
				return true;
			}
		return false;
	}

	public void endTurn() {
	       	fireplay = !fireplay;
			didMove = false;
			currentPieceSelected = null;
			didCapture = false;
	}

	public Piece remove(int x, int y) {

		if (x < 0 || x > 7) {
			return null;
		} else if (y < 0 || y > 7) {
			return null;
		}

		if (pieceAt(x,y) == null) {
			return null;
		}

		pieceToRemove = pieceAt(x,y);
		pieces[x][y] = false;
		pieceSet[x][y] = null;
		return pieceToRemove;
	}

	private boolean simpleMoveUpR(int x, int y) {
		if (x == xClickCurrent + 1 && y == yClickCurrent + 1) {
			return true;
		} else {
			return false;
		}
	}	

	private boolean simpleMoveUpL(int x, int y) {
		if (x == xClickCurrent - 1 && y == yClickCurrent + 1) {
			return true;
		} else {
			return false;
		}
	}	

	private boolean simpleMoveDR(int x, int y) {
		if (x == xClickCurrent + 1 && y == yClickCurrent - 1) {
			return true;
		} else {
			return false;
		}
	}	

	private boolean simpleMoveDL(int x, int y) {
		if (x == xClickCurrent - 1 && y == yClickCurrent - 1) {
			return true;
		} else {
			return false;
		}
	}		

	private boolean captureLeftUp(int x, int y) {
		if (xClickCurrent > 1 && yClickCurrent < 6) {
			if (pieces[xClickCurrent-1][yClickCurrent+1]) {
				occupied = pieceAt(xClickCurrent-1,yClickCurrent+1);
				if (occupied.isFire() ^ currentPieceSelected.isFire()) {
					if (x == (xClickCurrent - 2) && y == (yClickCurrent + 2))  {
						if (currentPieceSelected.isFire()) {
							fireScore = fireScore + 1;
							} else {
								waterScore = waterScore + 1;
							}
						return true;
					}
				}
			}		
		}
		return false;
	}

	private boolean captureRightUp(int x, int y) {
		if (xClickCurrent < 6 && yClickCurrent < 6) {
			if (pieces[xClickCurrent+1][yClickCurrent+1]) {
				occupied = pieceAt(xClickCurrent+1,yClickCurrent+1);
				if (occupied.isFire() ^ currentPieceSelected.isFire()) {
					if (x == xClickCurrent + 2 && y == yClickCurrent + 2)  {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean captureLeftD(int x, int y) {
		if (xClickCurrent > 1 && yClickCurrent > 1) {
			if (pieces[xClickCurrent-1][yClickCurrent-1]) {
				occupied = pieceAt(xClickCurrent-1,yClickCurrent-1);
				if (occupied.isFire() ^ currentPieceSelected.isFire()) {
					if (x == xClickCurrent - 2 && y == yClickCurrent - 2)  {
						return true;
					}
				}
			}		
		}
		return false;
	}

	private boolean captureRightD(int x, int y) {
		if (xClickCurrent < 6 && yClickCurrent > 1) {
			if (pieces[xClickCurrent+1][yClickCurrent-1]) {
				occupied = pieceAt(xClickCurrent+1,yClickCurrent-1);
				if (occupied.isFire() ^ currentPieceSelected.isFire()) {
					if (x == (xClickCurrent + 2) && y == (yClickCurrent - 2))  {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void bombExplosion(int x, int y) {
		int[][] surCoord = new int[8][2];
		for (int i = -1; i < 2; i++) {
			surCoord[i+1][0] = x + i;
			surCoord[i+1][1] = y - 1;
			surCoord[i+6][0] = x + i;
			surCoord[i+6][1] = y + 1;
		}
		surCoord[3][0] = x - 1;
		surCoord[3][1] = y;
		surCoord[4][0] = x + 1;
		surCoord[4][1] = y;

		for (int i = 0; i < 8; i++) {
			if ((surCoord[i][0] < 8 && surCoord[i][0] > -1)  && (surCoord[i][1] < 8 && surCoord[i][1] > -1)) {
				if (pieces[surCoord[i][0]][surCoord[i][1]]) {
					occupiedBomb = pieceAt(surCoord[i][0],surCoord[i][1]);
					if ((currentPieceSelected.isFire() ^ occupiedBomb.isFire())) {
						if (!occupiedBomb.isShield())
							remove(surCoord[i][0],surCoord[i][1]);
						}
					}

				}
			
	}
}
	private boolean canMoveKing(int x, int y) {
		if (pieces[x][y] == false) {
			if (y != yClickCurrent && x != xClickCurrent && !didMove){
				if (yClickCurrent == 0 ) {
					if (simpleMoveUpR(x,y) || simpleMoveUpL(x,y)) {
						return true;
					}		
				} else if (yClickCurrent == 7) {
					if (simpleMoveDR(x,y) || simpleMoveDL(x,y)) {
						return true;
					}
				} else {
					if (simpleMoveDR(x,y) || simpleMoveDL(x,y) || simpleMoveUpR(x,y) || simpleMoveUpL(x,y)) {
						return true;
					}
				}
			}
		}

		if (kingCanCapture(x,y)) {
			return true;
		}
		return false;
	}

	private boolean kingCanCapture(int x, int y) {
				if (y != yClickCurrent && x != xClickCurrent && !didMove){
					if (captureLeftUp(x,y) || captureRightUp(x,y) || captureLeftUp(x,y) || captureLeftD(x,y)) {
						return true;
					}
				}
		return false;
	}

	private boolean canMove(int x, int y) {
		if (y != yClickCurrent && x != xClickCurrent && !didMove){
			if (pieceAt(xClickCurrent,yClickCurrent).isKing() && canMoveKing(x,y)) {
				return true;
			}
			if (currentPieceSelected.isFire()) {
				if (pieces[x][y] == false) {
					// Simple case 1: selected piece is at left edge
					if (x == 0) {
					 	if (simpleMoveUpR(x,y)) {
							return true;
						}
					// Simple case 2: selected piece is at right edge
					} else if (x == 7) {
						if (simpleMoveUpL(x, y)) {
								return true;
							}
					// Simple case 3: selected place is at middle
					} else  {
					if (x == xClickCurrent + 1 && y == yClickCurrent + 1)  {
						return true;

						} else if (x == xClickCurrent - 1 && y == yClickCurrent + 1)  {
							return true;
						}
					}

					// Can collect to the right?
					if (captureRightUp(x,y) || captureLeftUp(x,y)) {
						return true;
					}
				}
			}



			if (!currentPieceSelected.isFire()) {
				if (pieces[x][y] == false) {
					if (x == 0) {
						if (simpleMoveDR(x, y))  {
							return true;
						}
					} else if (x == 7) {
						if (simpleMoveDL(x, y)) {
							return true;
						}
					} else {
						if (simpleMoveDL(x, y) || simpleMoveDR(x, y)) {
						return true;
						}
					}

					if (captureRightD(x, y) || captureLeftD(x, y)){
						return true;
					}
				}	
			}
		}

		return false;
	}

	public void place(Piece p, int x, int y) {
		String img0;
		String img1;
		String img2;

		pieces[x][y] = true;
		pieceSet[x][y] = p;
		img0 = "img/";
		if (p.isFire()) {
			if (p.isKing()) {
				img2 = "-fire-crowned.png";
				} else {
				img2 = "-fire.png";
			}
		} else {
			if (p.isKing()){
				img2 = "-water-crowned.png";
			} else {
			img2 = "-water.png";
		}
		}


		if (p.isShield()) {
			img1 = "shield";
		} else if (p.isBomb()) {
			img1 = "bomb";
		} else {
			img1 = "pawn";
		}

		pieceTypes[x][y] = img0+img1+img2;
		}

	private void moveStuff(int x, int y) {
		if (x > -1 && x < 8 && y > -1 && y < 8) {//) {&& p != null) {
	        pieces[xClickCurrent][yClickCurrent] = false;
    		pieces[x][y] = true;
    		pieceSet[x][y] = pieceSet[xClickCurrent][yClickCurrent];
    		pieceTypes[x][y] = pieceTypes[xClickCurrent][yClickCurrent];
    		pieceSet[xClickCurrent][yClickCurrent] = null;
    		pieceTypes[xClickCurrent][yClickCurrent] = "";
    		didMove = true;
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || x > 7) {
			return null;
		}
		if (y < 0 || y > 7) {
			return null;
		}
		if (pieces[x][y]) {
			return pieceSet[x][y];
		} else {
		return null;
		}
	}
	
	private void initialSet() {
		int i = 0;
		while (i < 4) {
			pieceSet[i*2][0] = new Piece(true, this, i*2, 0, "pawn");
			pieceSet[i*2+1][1] = new Piece(true, this, i*2+1, 1, "shield");
			pieceSet[i*2][2] = new Piece(true, this, i*2, 2, "bomb");
			pieceSet[i*2+1][5] = new Piece(false, this, i*2+1, 5, "bomb");
			pieceSet[i*2][6] = new Piece(false, this, i*2, 6, "shield");
			pieceSet[i*2+1][7] = new Piece(false, this, i*2+1, 7, "pawn");
			pieceTypes[i*2][0] = "img/pawn-fire.png";
			pieceTypes[i*2+1][1] = "img/shield-fire.png";
			pieceTypes[i*2][2] = "img/bomb-fire.png";
			pieceTypes[i*2+1][5] = "img/bomb-water.png";
			pieceTypes[i*2][6] = "img/shield-water.png";
			pieceTypes[i*2+1][7] = "img/pawn-water.png";
			pieces[i*2][0] = true;
			pieces[i*2+1][1] = true;
			pieces[i*2][2] = true;
			pieces[i*2+1][5] = true;
			pieces[i*2][6] = true;
			pieces[i*2+1][7] = true;
			i++;	
		}
	}

	public String winner()  {
		for(int i =0; i < 8; i++) {
			for (int j = 0; j <8; j++) {
				if (pieces[i][j]) {
					if (pieceSet[i][j].isFire()) {
						firePiecesLeft = true;
					} else {
						waterPiecesLeft = true;
				}
			}
		}
	}

		if (firePiecesLeft && waterPiecesLeft) {
			waterPiecesLeft = false;
			firePiecesLeft = false;
			return null;
		}
		if (!firePiecesLeft && !waterPiecesLeft) {
			waterPiecesLeft = false;
			firePiecesLeft = false;
			return "No one";
			} else if (firePiecesLeft && !waterPiecesLeft) {
				waterPiecesLeft = false;
				firePiecesLeft = false;
				return "Fire";
			} else if (!firePiecesLeft && waterPiecesLeft) {
				waterPiecesLeft = false;
				firePiecesLeft = false;
				return "Water";
			}
		
		waterPiecesLeft = false;
		firePiecesLeft = false;
		return null;
	}

	private void highlightSquare(int x, int y) {
		StdDrawPlus.filledSquare(xClickCurrent + 0.5, yClickCurrent + 0.5, 0.5);
	}

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0,8);
		StdDrawPlus.setYscale(0,8);
		Board gameBoard = new Board(true);
		while (true) {
			gameBoard.drawBoard(8);
		    if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (x >= 0 && x <= 8 && y >= 0 && y <=8) {
                gameBoard.xClick = (int)x;
                gameBoard.yClick = (int)y;
                gameBoard.firstClick = true;
            	}
            }
   
	        if (gameBoard.firstClick && gameBoard.currentPieceSelected == null) {
	        	if(gameBoard.canSelect(gameBoard.xClick,gameBoard.yClick)) {
				gameBoard.select(gameBoard.xClick,gameBoard.yClick);
				gameBoard.xClickCurrent = gameBoard.xClick;
				gameBoard.yClickCurrent = gameBoard.yClick;

				gameBoard.currentPieceSelected = gameBoard.pieceAt(gameBoard.xClick,gameBoard.yClick);
	        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	        	gameBoard.highlightCurrent = true;
	        }  
	    }
	    if (gameBoard.highlightCurrent) {
	    	gameBoard.highlightSquare(gameBoard.xClickCurrent,gameBoard.yClickCurrent);
	    }
	    
		if (gameBoard.currentPieceSelected !=  null) {
			if (gameBoard.xClickCurrent != gameBoard.xClick && gameBoard.yClickCurrent != gameBoard.yClick){
				if (gameBoard.canSelect(gameBoard.xClick, gameBoard.yClick)) {
					gameBoard.select(gameBoard.xClick, gameBoard.yClick);
			}
		}
	}	
		
	if (gameBoard.isSpacePressed()) {
       	if (gameBoard.canEndTurn()) {
       		gameBoard.endTurn();
       	}       
    }
            StdDrawPlus.show(100);
		}
  	 }
}