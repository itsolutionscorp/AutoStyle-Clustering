public class Board {
	private boolean empty;
	private Piece[][] pieces = new Piece[8][8];
    private boolean fireTurn = true;
    private int fireCount = 0; //set public to test
    private int waterCount = 0; // set public to test
    private boolean hasMoved = false;
    private Piece selectedPiece; // set public to test
    private int selectedX;
    private int selectedY;
    private boolean bombMove = false; 

	public static void main(String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
        b.drawBoard(8);
		b.drawPieces();
        StdDrawPlus.show(25);
        b.hasMoved = false;

        while (b.fireCount > 0 && b.waterCount > 0) {
            if (StdDrawPlus.mousePressed()) {
                int inputX = (int) StdDrawPlus.mouseX();
                int inputY = (int) StdDrawPlus.mouseY();
                if (b.canSelect(inputX, inputY)) {
                        b.drawBoard(8);
                        b.select(inputX, inputY);
                        StdDrawPlus.filledSquare(inputX + .5, inputY + .5, .5);
                        b.drawPieces(); 
                        StdDrawPlus.show(25);
                    }
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
        }

        String win = b.winner();
        System.out.println(win);
	}
	
//Board constructor, mainly for testing.
	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
        if (!empty) {
            for (int x = 0; x < pieces.length; x++) {
                for (int y = 0; y < pieces[x].length; y++) {
                    if (x % 2 == 0 && y == 0) {
                        place(new Piece(true, this, x, y, "pawn"), x, y);
                    }
                    else if (x % 2 != 0 && y == 1) {
                        place(new Piece(true, this, x, y, "shield"), x, y);
                    }
                    else if (x % 2 == 0 && y == 2) {
                        place(new Piece(true, this, x, y, "bomb"), x, y);
                    }
                    else if (x % 2 != 0 && y == 5) {
                        place(new Piece(false, this, x, y, "bomb"), x, y);
                    }
                    else if (x % 2 == 0 && y == 6) {
                        place(new Piece(false, this, x, y, "shield"), x, y);
                    }
                    else if (x % 2 != 0 && y == 7) {
                        place(new Piece(false, this, x, y, "pawn"), x, y);
                    }
                }
            }
        }

	}

//Draws all pieces onto the board.
	private void drawPieces() {
        for (int x = 0; x < pieces.length; x++) {
            for (int y = 0; y < pieces[x].length; y++) {
            	Piece temp = pieceAt(x, y);
            	if (temp != null) {	
            		if (temp.isBomb()) {
            			if (temp.isFire()) {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else {
            				    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                            }
            			}
            			else {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else {
            				    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                            }
            			}
            		}
            		else if (temp.isShield()) {
            			if (temp.isFire()) {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
            				    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                            }
            			}
            			else {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
            				    StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                            }
            			}
            		}
            		else {
            			if (temp.isFire()) {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            else {
            				    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                            }
            			}
            			else {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                            else {
            				    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                            }
            			}
            		}

            	} 
            }
		}	
	}

// i is y coordinate, j is x coordinate. Draws board.
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {      
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    } 

// returns a piece if it is on a location.
    public Piece pieceAt(int x, int y) {
    	if (x > pieces.length - 1 || y > pieces[x].length - 1 || pieces[x][y] == null) {
    		return null;
    	}
    	else {
    		return pieces[x][y];
        }
    }

// checks if you can select a space.
    public boolean canSelect(int x, int y) {
        if (pieceAt(x, y) != null && ((pieceAt(x, y).isFire() && fireTurn) || (!pieceAt(x, y).isFire() && !fireTurn))) {
            if (bombMove) {
                return false;
            }
            else if (selectedPiece == null && !hasMoved) {
                return true;
            }
            else if (selectedPiece!= null && !hasMoved) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (selectedPiece != null && validMove(selectedX, selectedY, x, y)) {
                return true;
            }
            else if (selectedPiece != null && selectedPiece.hasCaptured() && validMove(selectedX, selectedY, x, y)) {
                return true;
            }
            else {
                return false;
            }
        }
    }

//selects a piece/space.
    public void select(int x, int y) {
        if (pieceAt(x, y) == null) {
            if (selectedPiece.isBomb()) {
                selectedPiece.move(x, y);
                bombMove = true;
                selectedPiece = null; 
                selectedX = 0;
                selectedY = 0;
            }
            else {
                selectedPiece.move(x, y);
                selectedX = x;
                selectedY = y;
            }
            hasMoved = true;
        }
        else {
            selectedPiece = pieceAt(x, y);
            selectedX = x;
            selectedY = y;
        }
    }

//places a piece onto a location.
    public void place(Piece p, int x, int y) {
    	if (p == null || x > pieces.length - 1|| y > pieces[x].length -1) {
    		return;
    	}
    	else {
    		pieces[x][y] = p;
            if (p.isFire()) {
                fireCount = fireCount + 1;
            }
            else {
                waterCount = waterCount + 1;
            }
    	}
    }

//removes a piece from a location and returns it. If something is removed, then a move is made, so hasMoved is set to true.
    public Piece remove(int x, int y) {
    	if (pieceAt(x, y) == null) {
    		System.out.println("There is no piece on this location.");
    		return null;
    	}
    	else if (x > pieces.length || y > pieces[x].length) {
    		System.out.println("This location is out of bounds.");
    		return null;
    	}
    	else {
    		Piece temp = pieceAt(x, y);
    		pieces[x][y] = null;
            if (temp.isFire()) {
                fireCount = fireCount - 1;
            }
            else {
                waterCount = waterCount - 1;
            }
    		return temp;
    	}
    }

//checks if player can end turn.
    public boolean canEndTurn() {
    	if (hasMoved) {
            return true;
        }
        else {
            return false;
        }
    }

//ends turn.
    public void endTurn() {
    	fireTurn = !fireTurn;
        if (selectedPiece != null) {
            selectedPiece.doneCapturing();
            selectedPiece = null;
        }
        hasMoved = false;
        bombMove = false;
    }

//returns winner of game.
    public String winner() {
    	if (fireCount == 0 && waterCount > 0) {
            return "Water";
        }
        else if (waterCount == 0 && fireCount > 0) {
            return "Fire";
        }
        else if (fireCount == 0 && waterCount == 0) {
            return "No one";
        }
        else {
            return null;
        }
    }

//checks if move is valid, used for canSelect.
    //WHEN BOMB EXPLODES NEXT TO SHIELD, NULLPOINTER EXCEPTION OCCURS.
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xf >= 0 && xf < 8 && yf >= 0 && yf < 8 && pieceAt(xf, yf) == null) {
            if (pieceAt(xi, yi).isKing()) {
                if ((xf == xi + 1 || xf == xi - 1) && (yf == yi + 1 || yf == yi - 1) && (!pieceAt(xi, yi).hasCaptured())) {
                    if (pieceAt(xf, yf) == null) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    if ((xf == xi + 2 || xf == xi - 2) && (yf == yi + 2 || yf == yi -2)) {
                        int xJump;
                        if (xf == xi + 2) {
                            xJump = (xi + xi + 2) / 2;                        
                        }
                        else {
                            xJump = (xi + xi -2) / 2;
                        }
                        int yJump;
                        if (yf == yi + 2) {
                            yJump = (yi + yi + 2) / 2;
                        }
                        else  {
                            yJump = (yi + yi - 2) / 2;
                        }
                        if (pieceAt(xJump, yJump) != null && pieceAt(xJump, yJump).isFire() != pieceAt(xi, yi).isFire()) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }                
            }
            else if (pieceAt(xi, yi).isFire()) {
                if ((xf == xi + 1 || xf == xi - 1) && (yf == yi + 1) && (!pieceAt(xi, yi).hasCaptured())) {
                    if (pieceAt(xf, yf) == null) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    if ((xf == xi + 2 || xf == xi - 2) && (yf == yi + 2)) {
                        int xJump;
                        if (xf == xi + 2) {
                            xJump = (xi + xi + 2) / 2;                        
                        }
                        else {
                            xJump = (xi + xi -2) / 2;
                        }
                        int yJump = (yi + yf) / 2;
                        if (pieceAt(xJump, yJump) != null && pieceAt(xJump, yJump).isFire() != pieces[xi][yi].isFire()) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            }
            else {
                if ((xf == xi + 1 || xf == xi - 1) && (yf == yi - 1) && (!pieceAt(xi, yi).hasCaptured())) {
                    if (pieceAt(xf, yf) == null) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    if ((xf == xi + 2 || xf == xi - 2) && (yf == yi - 2)) {
                        int xJump;
                        if (xf == xi + 2) {
                            xJump = (xi + xi + 2) / 2;                        
                        }
                        else {
                            xJump = (xi + xi -2) / 2;
                        }
                        int yJump = (yi + yf) / 2;
                        if (pieceAt(xJump, yJump) != null && pieceAt(xJump, yJump).isFire() != pieces[xi][yi].isFire()) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }                
            }
        }
        else {
            return false;
        }
    return false;
    }


}
