public class Board{
	private boolean playerFireIsTrue = true;
	private boolean pieceSelected = false;
	private boolean pieceMoved = false;
	private int selectedX;
	private int selectedY;
	private Piece[][] actualpiece = new Piece[8][8];
	private int numberFire = 12;
	private int numberWater = 12;
	private boolean kill = false;
	public Board(boolean shouldbeempty) {
		if (shouldbeempty) {
			numberFire = 0;
			numberWater = 0;
		}
		else{
			pieceInitialize(this);
		}
	}

	public Piece pieceAt(int x, int y) {
		return actualpiece[x][y];
	}

	private boolean validMove(int xsel, int ysel, int x, int y) {
		//can I move from xsel to x, etc.
		if (pieceAt(x, y) != null) {
			return false;
		}
		//regular moving.
		else if ((x - xsel == 1) || (xsel - x == 1)) {
			if ((y - ysel == 1) && (pieceAt(xsel,ysel).isFire())) {
				return true;
			} else if ((ysel - y == 1) && !(pieceAt(xsel, ysel).isFire())) {
				return true;
			} if (pieceAt(xsel, ysel).isKing()) {
				if ((ysel - y == 1) || (y - ysel == 1)) {
					return true;
				} else return false;
			} else return false;
		} else if ((pieceAt(  (xsel + ((x-xsel)/2))  , (ysel + ((y-ysel)/2))  ) != null)) {
			if (pieceAt((xsel + (x-xsel)/2)  , (ysel + (y-ysel)/2)).isFire() != pieceAt(xsel, ysel).isFire()) {
				if (x - xsel == 2 || xsel - x == 2) {
					if ((y - ysel == 2) && (pieceAt(xsel,ysel).isFire())) {
						return true;
					} else if ((ysel - y== 2) && !(pieceAt(xsel, ysel).isFire())) {
						return true;
					} if ((y - ysel == -2) || (ysel - y == -2)) {
						if (pieceAt(xsel, ysel).isKing()) {
							return true;
						} else return false;
					} else return false;
				} else return false;
			} else return false;
		} else return false;
	}

	public boolean canSelect(int x, int y) {
		if (pieceMoved == false) {
			if (pieceSelected == false) {
				if ((pieceAt(x, y) != null) && (pieceAt(x, y).isFire() == playerFireIsTrue)) {
					return true;
				}
			} else if (pieceSelected == true) {
				if ((pieceAt(x, y) != null) && (pieceAt(x, y).isFire() == playerFireIsTrue)) {
					return true;
				} else if (pieceAt(x, y) == null) {
					return validMove(selectedX, selectedY, x, y);
				}
			}
		} else if (pieceMoved == true) {
			if (((x - selectedX == 2) || (selectedX - x == 2)) && ((y - selectedY == 2) || (selectedY - y == 2))) {
				return validMove(selectedX, selectedY, x, y);
			}
		} return false;
	}

	public void select(int x, int y) {
		if ((pieceSelected == false) || (pieceAt(x, y) != null)){
			kill = false;
			selectedX = x;
			selectedY = y;
			pieceSelected = true;
		} else if (pieceSelected == true) {
			kill = false;
			place(remove(selectedX, selectedY), x, y);
			pieceAt(x, y).move(x, y);

			if (x - selectedX == 2 || x - selectedX == -2) {
				kill = true;
				remove((x + (selectedX - x) / 2) , (y + (selectedY - y) / 2));
				bombExplosion(x, y);
				kill = false;
			}
			selectedX = x;
			selectedY = y;
			pieceSelected = true;
			pieceMoved = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x > 8 || y > 8 || x < 0 || y < 0) {}
		else{
			actualpiece[x][y] = p;
		}
	}

	private void bombExplosion(int x, int y) {
		if (pieceAt(x, y).isBomb()) {
			kill = true;
			remove(x, y);
			if ((x == 7) || (y==7)) {}
			else {
				if (pieceAt(x+1, y+1) != null) {
					if (pieceAt(x+1, y+1).isShield()) {}
						else {
							remove(x+1, y+1);
						}
					}
				}
			if ((x == 7) || (y==0)) {}
			else {
				if (pieceAt(x+1, y-1) != null) {
					if (pieceAt(x+1, y-1).isShield()) {}
						else {
							remove(x+1, y-1);
						}
					}
				}
			if ((x == 0) || (y==7)) {}
			else {
				if (pieceAt(x-1, y+1) != null) {
					if (pieceAt(x-1, y+1).isShield()) {}
						else {
							remove(x-1, y+1);
						}
					}
				}
			if ((x == 0) || (y == 0)) {}
			else {
				if (pieceAt(x-1, y-1) != null) {
					if (pieceAt(x-1, y-1).isShield()) {}
						else {
							remove(x-1, y-1);
						}
					}
				}
			kill = false;
		}
	}

	public Piece remove(int x, int y) {
		String whatGonnaPrint;
		if (actualpiece[x][y] == null) {
			return null;
		} else {
			if (kill == true) {
			if (actualpiece[x][y].isFire()) {
				whatGonnaPrint = "Fire";
				numberFire -= 1;
			} else {
				whatGonnaPrint = "Water";
				numberWater -= 1;
			}
			if (actualpiece[x][y].isBomb()) {
				whatGonnaPrint += " Bomb";
			} else if (actualpiece[x][y].isShield()) {
				whatGonnaPrint += " Shield";
			} else {
				whatGonnaPrint += " Pawn";
			}
			if (actualpiece[x][y].isKing()) {
				whatGonnaPrint += " king removed.";
			} else {
				whatGonnaPrint += " removed.";
			}
			System.out.println(whatGonnaPrint);
		}
			Piece pieceholder = actualpiece[x][y];
			actualpiece[x][y] = null;
			return pieceholder;
			}
		}
	

	public boolean canEndTurn() {
		if (pieceMoved) {
			return true;
		} else return false;
	} 

	public void endTurn() {
		pieceMoved = false;
		pieceSelected = false;
		playerFireIsTrue = !playerFireIsTrue;

	}

	public String winner() {
		if (numberFire <= 1 && numberWater > 0) {
			return "Water";
		} else if (numberFire > 0 && numberWater <= 0) {
			return "Fire";
		} else if (numberFire <= 0 && numberWater <= 0) {
			return "No One";
		} return null;
	}

	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	//drew grid, now drawing pieces
            	if (actualpiece[i][j] != null) {
            		Piece tempPiece = pieceAt(i, j);

            		String imagefile = "img/";
			            	if (tempPiece.isBomb()){
			            		imagefile += "bomb-";
			            	} else if (tempPiece.isShield()) {
			            		imagefile += "shield-";
			            	} else {
			            		imagefile += "pawn-";
			            	}
			            	if (tempPiece.isFire()) {
			            		imagefile += "fire";
			            	} else {
			            		imagefile += "water";
			            	}
			            	if (tempPiece.isKing()) {
			            		imagefile += "-crowned.png";
			            	} else{
			            		imagefile += ".png";
			            	}
			            	StdDrawPlus.picture(i+.5, j+.5, imagefile, 1, 1);
			        }
            	}
			}
		}

	private void pieceInitializeHelper(int yValue, String eachType, boolean sided, Board bard) {
		int xValue = 0;
		if (yValue % 2 == 0) {
			while (xValue < 8) {
				if (xValue % 2 == 0) {
        			place(new Piece(sided, bard, xValue, yValue, eachType), xValue, yValue);
        		}
        		xValue += 1;
    		}
    	} else if (yValue % 2 == 1) {
    		while (xValue < 8) {
    			if (xValue % 2 == 1) {
        			place(new Piece(sided, bard, xValue, yValue, eachType), xValue, yValue);
        		}
        		xValue += 1;
    		}
    	}
    }

    private void pieceInitialize(Board bard) {
        pieceInitializeHelper(0, "pawn", true, bard);
        pieceInitializeHelper(1, "shield", true, bard);
        pieceInitializeHelper(2, "bomb", true, bard);
        pieceInitializeHelper(5, "bomb", false, bard);
        pieceInitializeHelper(6, "shield", false, bard);
        pieceInitializeHelper(7, "pawn", false, bard);
    }

    public static void main(String arg[]) {
    	Board boahrd = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while (true) {
        	boahrd.drawBoard();
        	if (boahrd.winner() != null) {
        		break;
        	}
        	if (StdDrawPlus.mousePressed()) { 
        		double x = StdDrawPlus.mouseX();
        		double y = StdDrawPlus.mouseY();
        		if (boahrd.canSelect((int) x, (int) y)) {
        			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        			StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
        			boahrd.select((int) x, (int) y);
        		}
        	}
        	if (StdDrawPlus.isSpacePressed()) {
        		if (boahrd.canEndTurn()) {
        			boahrd.endTurn();
        		}
        	}
        	StdDrawPlus.show(100);

        }
        System.out.println(boahrd.winner());


	}
	}	