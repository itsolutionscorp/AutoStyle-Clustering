public class Board{
	private Piece[][] pieces;
	private boolean firePlayer = true;
	private int initialPieceX;
	private int initialPieceY;
	private boolean selected = false;
	private boolean moved = false;
	private boolean flag = false;

	public Board(boolean shouldBeEmpty){
		int N = 8;
		pieces = new Piece[N][N];
		if (!shouldBeEmpty){
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					if (j == 0){
	    				if (i % 2 == 0){
	    					 pieces[i][j] = new Piece(true, this, i, j, "pawn");
	    				}
	    			}
	    			else if (j == 1){
	    				if (i % 2 != 0){
	    					pieces[i][j] = new Piece(true, this, i, j, "shield");
	    				}
	    			}
	    			else if (j == 2){
	    				if (i % 2 == 0){
	    					pieces[i][j] = new Piece(true, this, i, j, "bomb");
	    				}
	    			}
	    			else if (j == 5){
	    				if (i % 2 != 0){
	    					pieces[i][j] = new Piece(false, this, i, j, "bomb");
	    				}
	    			}
	    			else if (j == 6){
	    				if (i % 2 == 0){
	    					pieces[i][j] = new Piece(false, this, i, j, "shield");
	    				}
	    			}
	    			else if (j == 7){
	    				if (i % 2 != 0){
	    					pieces[i][j] = new Piece(false, this, i, j, "pawn");
	    				}
	    			}
				}
			}
		}
	}

	private boolean inBounds(int x, int y){
		if (x >= pieces.length || y >= pieces.length || x < 0 || y < 0){
			return false;
		}
		return true;
	}

	public Piece pieceAt(int x, int y){
		if (!inBounds(x, y)){
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		//if square with piece selected
		if (pieceAt(x, y) != null){
			//check if its appropriate player selecting his own piece
			if ((pieceAt(x, y).isFire() && firePlayer) || 
				(!pieceAt(x, y).isFire() && !firePlayer)){
				if (!selected && !flag){
					return true;
				}else if (!moved){
					return true;	
				}
			}
		}
		//check if empty square
		if (inBounds(x, y) && pieceAt(x, y) == null){
			if (selected && !moved){
				return validMove(initialPieceX, initialPieceY, x, y);
			}
			//multi-capture
			else if(selected && moved && pieceAt(initialPieceX, initialPieceY).hasCaptured()){
				return validMove(initialPieceX, initialPieceY, x, y);
			}
		}
		return false;
	}

	public void select(int x, int y){
		if (pieceAt(x, y) != null){
			initialPieceX = x;
			initialPieceY = y;
			selected = true;
		}
		else if (selected && pieceAt(x, y) == null){
			pieceAt(initialPieceX, initialPieceY).move(x, y);
			if (pieceAt(x, y) == null){
				selected = false;
				flag = true;
			}
			initialPieceX = x;
			initialPieceY = y;
			moved = true;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (pieceAt(xi, yi).isKing()){
			return checkFireDiagonals(xi, yi, xf, yf) || checkWaterDiagonals(xi, yi, xf, yf) || checkKingCapture(xi, yi, xf, yf);
		}else{
			if (firePlayer){
				return checkFireDiagonals(xi, yi, xf, yf) || checkFireCapture(xi, yi, xf, yf);
			}else{
				return checkWaterDiagonals(xi, yi, xf, yf) || checkWaterCapture(xi, yi, xf, yf);
			}
		}
	}

	private boolean checkKingCapture(int xi, int yi, int xf, int yf){
		if (firePlayer){
			if (checkFireCapture(xi, yi, xf, yf)){
				return true;
			}
			else if(pieceAt(xi-1, yi-1) != null && !pieceAt(xi-1, yi-1).isFire()){
				if (xi -2 == xf && yi -2 == yf){
					return true;
				}
			}
			else if(pieceAt(xi+1, yi-1) != null && !pieceAt(xi+1, yi-1).isFire()){
				if (xi +2 == xf && yi -2 == yf){
					return true;
				}
			}
		}
		if (!firePlayer){
			if (checkWaterCapture(xi, yi, xf, yf)){
				return true;
			}
			else if(pieceAt(xi+1, yi+1) != null && pieceAt(xi+1, yi+1).isFire()){
				if (xi +2 == xf && yi +2 == yf){
					return true;
				}
			}
			else if(pieceAt(xi-1, yi+1) != null && pieceAt(xi-1, yi+1).isFire()){
				if (xi -2 == xf && yi +2 == yf){
					return true;
				}
			} 
		}
		return false;
	}

	private boolean checkFireCapture(int xi, int yi, int xf, int yf){
		if (pieceAt(xi-1, yi+1) != null  && !pieceAt(xi-1, yi+1).isFire()){
			if (xi -2 == xf && yi +2 == yf){
				return true;
			}
		}
		if (pieceAt(xi+1, yi+1) != null && !pieceAt(xi+1, yi+1).isFire()){
			if (xi +2 == xf && yi +2 == yf){
				return true;
			}
		}
		return false;
	}

	private boolean checkWaterCapture(int xi, int yi, int xf, int yf){
		if (pieceAt(xi-1, yi-1) != null && pieceAt(xi-1, yi-1).isFire()){
			if (xi -2 == xf && yi -2 == yf){
				return true;
			}
		}
		if (pieceAt(xi+1, yi-1) != null && pieceAt(xi+1, yi-1).isFire()){
			if (xi +2 == xf && yi -2 == yf){
				return true;
			}
		}
		return false;
	}

	private boolean checkWaterDiagonals(int xi, int yi, int xf, int yf){
		if (!moved && ((xi - 1 == xf && yi - 1 == yf) || (xi + 1 == xf && yi - 1 == yf))){
			return true;
		}
		return false;
	}

	private boolean checkFireDiagonals(int xi, int yi, int xf, int yf){
		if (!moved && ((xi - 1 == xf && yi + 1 == yf) || (xi + 1 == xf && yi + 1 == yf))){
			return true;
		}
		return false;
	}

	public void place(Piece p, int x, int y){
		if (p != null && inBounds(x, y)){
			if (pieces[x][y] != null){
				remove(x, y);
			}
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		if (!inBounds(x, y)){
			System.out.println("You have selected an invalid location");
			return null;
		}
		else if (pieceAt(x, y) == null){
			System.out.println("There is no piece to remove!");
			return null;
		}
		else{
			Piece removed = pieces[x][y];
			pieces[x][y] = null;
			return removed;
		}
	}

	public boolean canEndTurn(){
		if(moved || pieceAt(initialPieceX, initialPieceY) == null || pieceAt(initialPieceX, initialPieceY).hasCaptured()){
			return true;
		}
		return false;
	}

	public void endTurn(){
		if (pieceAt(initialPieceX, initialPieceY) != null){
			pieceAt(initialPieceX, initialPieceY).doneCapturing();
		}
		selected = false;
		moved = false;
		firePlayer = !firePlayer;
	}

	public String winner(){
		int fireSum = 0;
		int waterSum = 0;
		for (int i = 0; i < pieces.length; i += 1){
			for (int j = 0; j < pieces.length; j ++){
				if(pieces[i][j] != null){
					if (pieces[i][j].isFire()){
						fireSum += 1;
					}else{
						waterSum += 1;
					}
				}
			}
		}

		if (fireSum == waterSum && fireSum == 0){
			return "No one";
		}
		else if (fireSum != 0 && waterSum == 0){
			return "Fire";
		}
		else if (waterSum != 0 && fireSum == 0){
			return "Water";
		}else{
			return null;
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i+= 1) {
            for (int j = 0; j < N; j+= 1) {
                if ((i + j) % 2 == 0){
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }

                if(selected && i == initialPieceX && j == initialPieceY){
                	highlightSquare();
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private void drawStartingPieces(){
    	for (int i = 0; i < pieces.length; i += 1){
    		for (int j = 0; j < pieces.length; j += 1){
    			if (pieces[i][j] != null){
    				if (pieces[i][j].isFire()){
    					if (pieces[i][j].isShield()){
    						if (pieces[i][j].isKing()){
    							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
    						}else{
    							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
    						}
    					}
    					else if (pieces[i][j].isBomb()){
    						if (pieces[i][j].isKing()){
    							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
    						}else{
    							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
    						}
    					}
    					else{
    						if (pieces[i][j].isKing()){
    							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
    						}else{
    							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
    						}
    					}
    				}else{
    					if (pieces[i][j].isShield()){
    						if (pieces[i][j].isKing()){
    							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
    						}else{
    							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
    						}
    					}
    					else if (pieces[i][j].isBomb()){
    						if (pieces[i][j].isKing()){
    							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
    						}else{
    							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
    						}
    					}
    					else{
    						if (pieces[i][j].isKing()){
    							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
    						}else{
    							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
    						}
    					}
    				}
    			}
    		}
    	}
    }

    private void highlightSquare(){
    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    }

	public static void main(String[] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        while(true) {
            b.drawBoard(N);
            b.drawStartingPieces();
            if (b.winner() != null){
            	System.out.println(b.winner());
            	break;
            }
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
              	int y = (int)StdDrawPlus.mouseY();
              	if (b.canSelect(x, y)){
              		b.select(x, y);
              	}  	
            }

            if(StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()){
            		b.endTurn();
            	}
            }
            StdDrawPlus.show(100); 
        }
        StdDrawPlus.show(100);
	}
}