public class Board{
	private Piece[][] board;
	private boolean fireTurn;
	private Piece selection;
	private int selectionX;
	private int selectionY;
	private boolean hasMoved;

	public Board(boolean shouldBeEmpty){
		board = new Piece[8][8];
		fireTurn = true;
		selection = null;
		selectionX = -1;
		selectionY = -1;
		hasMoved = false;
		if (!shouldBeEmpty){
			board[1][7] = new Piece(false, this, 1, 7, "pawn");
			board[3][7] = new Piece(false, this, 3, 7, "pawn");
			board[5][7] = new Piece(false, this, 5, 7, "pawn");
			board[7][7] = new Piece(false, this, 7, 7, "pawn");
			board[0][6] = new Piece(false, this, 0, 6, "shield");
			board[2][6] = new Piece(false, this, 2, 6, "shield");
			board[4][6] = new Piece(false, this, 4, 6, "shield");
			board[6][6] = new Piece(false, this, 6, 6, "shield");
			board[1][5] = new Piece(false, this, 1, 5, "bomb");
			board[3][5] = new Piece(false, this, 3, 5, "bomb");
			board[5][5] = new Piece(false, this, 5, 5, "bomb");
			board[7][5] = new Piece(false, this, 7, 5, "bomb");
			board[0][0] = new Piece(true, this, 0, 0, "pawn");
			board[2][0] = new Piece(true, this, 2, 0, "pawn");
			board[4][0] = new Piece(true, this, 4, 0, "pawn");
			board[6][0] = new Piece(true, this, 6, 0, "pawn");
			board[7][1] = new Piece(true, this, 7, 1, "shield");
			board[5][1] = new Piece(true, this, 5, 1, "shield");
			board[3][1] = new Piece(true, this, 3, 1, "shield");
			board[1][1] = new Piece(true, this, 1, 1, "shield");
			board[0][2] = new Piece(true, this, 0, 2, "bomb");
			board[2][2] = new Piece(true, this, 2, 2, "bomb");
			board[4][2] = new Piece(true, this, 4, 2, "bomb");
			board[6][2] = new Piece(true, this, 6, 2, "bomb");
		}
	}

	public Piece remove(int x, int y){
		if ((x < board.length)&&(x >= 0)) {
			if((y < board[x].length)&&(y >= 0)) {
				if ((pieceAt(x, y)) != null){
					Piece p = pieceAt(x, y);
					board[x][y] = null;
					return p;
				}
				else{
					System.out.println("No piece at (" + x + ", " + y + ")");
					return null;
				}
			}
		}
		System.out.println("(" + x + ", " + y + ") is not on the board!");
		return null;
	}

	public Piece pieceAt(int x, int y){
		if ((x < board.length)&&(x >= 0)) {
			if((y < board[x].length)&&(y >= 0)) {
				return board[x][y];
			}
		}
		return null;
	}

	public boolean canSelect(int x, int y){
		if ((x < board.length)&&(x >= 0)) {
			if((y < board[x].length)&&(y >= 0)) {
				if ((pieceAt(x, y)) != null){
					if (hasMoved){
						return false;
					}
					else{
						if((fireTurn)^((pieceAt(x, y)).isFire())) {
							return false;
						}
						else{
							return true;
						}
					}
				}
				else{
					if (selection == null){
						return false;
					}
					if(validMove(selectionX, selectionY, x, y, selection)) {
						return true;
					}
					else{
						return false;
					}
				}
			}
		}
		return false;	
	}

	private boolean validMove(int xi, int yi, int xf, int yf, Piece p){
		if ((p.isFire())&&(!p.isKing())){
			if((yf - yi) <= 0){
				return false;
			}
		}
		if ((!p.isFire())&&(!p.isKing())){
			if((yf - yi) >= 0){
				return false;
			}
		}
		if ((Math.abs(xf-xi) == 1)&&(Math.abs(yf-yi) == 1)){
			if(hasMoved){
				return false;
			}
			//if (nextTo(p, xi, yi)){
			//	return false;
			//}
			return true;
		}
		if ((Math.abs(xf-xi) == 2)&&(Math.abs(yf-yi) == 2)){
			//if(hasMoved){
			//	if ((yi == 7) && (p.isFire)){
			//		return false;
			//	}
			//	if ((yi == 0) && (!p.isFire)){
			//		return false;
			//	}
		//	}
			if(hasMoved){
				if(!p.hasCaptured()){
					return false;
				}
			}
			if (pieceAt(((xf + xi)/2),((yf + yi)/2)) != null){
				if((fireTurn)^(pieceAt(((xf + xi)/2),((yf + yi)/2)).isFire())) {
					if(pieceAt(xf, yf) == null){
						return true;
					}
				}
			}
		}
		return false;
	}	

	public void select(int x, int y){
		if (pieceAt(x, y) != null){
			selection = pieceAt(x, y);
			selectionX = x;
			selectionY = y;
		}
		else if(selection != null){
			selection.move(x, y);
			selectionX = x;
			selectionY = y;
			hasMoved = true; 
		}
	}

	public void place(Piece p, int x, int y){
		int oldx = -1;
		int oldy = -1;
		boolean onBoard = false;
		if ((x < board.length)&&(x >= 0)) {
			if((y < board[x].length)&&(y >= 0)) {
				if (p != null){
					for(int i = 0; i < board.length; i++){
						for(int  j = 0; j < board[i].length; j++){
							if (p == board[i][j]){
								oldx = i;
								oldy = j;
								onBoard = true;
							}
							}
						}
						if (onBoard == true){
							remove(oldx, oldy);
						}
					}
					if ((pieceAt(x, y)) != null){
						remove(x, y);
					}
					board[x][y] = p;
				}
			}
		}
	

	private boolean nextTo(Piece p, int x, int y){
		if ((p.isFire())||(p.isKing())){
				if (((pieceAt(x + 1, y + 1)) != null)||((pieceAt(x - 1, y + 1)) != null)){
					return true;
				}
			}
		if ((!p.isFire())||(p.isKing())){
			if (((pieceAt(x + 1, y - 1)) != null)||((pieceAt(x - 1, y - 1)) != null)){
				return true;
			}
		}
		return false;
	}

	public boolean canEndTurn(){
		if (!hasMoved){
			return false;
		}
		//if ((selection.isFire)||(selection.isKing())){
		//	if ((canSelect(selection.x + 2, selection.y + 2))||(canSelect(selection.x - 2, selection.y + 2))){
		//		return false;
		//	}
		//}
		//if ((!selection.isFire)||(selection.isKing())){
		//	if ((canSelect(selection.x + 2, selection.y - 2))||(canSelect(selection.x - 2, selection.y - 2))){
		//		return false;
		//	}
		//}
		return true;
	}

	public void endTurn(){
		fireTurn = !fireTurn;
		hasMoved = false;
		selection.doneCapturing();
		selection = null;
		selectionY = -1;
		selectionX = -1;
	}

	public String winner(){
		int firePieces = 0;
		int waterPieces = 0;
		for(int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				Piece p = pieceAt(i, j);
				if (p != null)  {
					if (p.isFire()) {
						firePieces++;
					}
					else{
						waterPieces++;
					}
				}
			}
		}
		if ((firePieces == 0)&&(waterPieces == 0)){
			return "No one";
		}
		else if (firePieces == 0){
			return "Water";
		}
		else if (waterPieces == 0){
			return "Fire";
		}
		else{
			return null;
		}
	}

	private static void drawBoard(Board board2, int N, int selectX, int selectY) {
        StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
        if ((selectX >= 0)&&(selectY >= 0)){
        	StdDrawPlus.filledSquare(selectX + .5, selectY + .5, .5);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if ((i != selectX) || (j != selectY)){
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	}
                if (board2.pieceAt(i, j) != null){
                if (board2.pieceAt(i, j).isBomb()) {
                	if (board2.pieceAt(i, j).isFire()) {
                		if (board2.pieceAt(i, j).isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                		}
                		else{
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                		}
                    }
                    else{
                    	if (board2.pieceAt(i, j).isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                		}
                		else{
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                		}
                    }                    
                }
                else if (board2.pieceAt(i, j).isShield()) {
                	if (board2.pieceAt(i, j).isFire()) {
                		if (board2.pieceAt(i, j).isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                		}
                		else{
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                		}
                    }
                    else{
                    	if (board2.pieceAt(i, j).isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                		}
                		else{
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                		}
                    }     
                }
                else{
                	if (board2.pieceAt(i, j).isFire()) {
                		if (board2.pieceAt(i, j).isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                		}
                		else{
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		}
                    }
                    else{
                    	if (board2.pieceAt(i, j).isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                		}
                		else{
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                		}
                    }     
                }
            }
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        int selectX = -1;
        int selectY = -1;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(true) {
            drawBoard(b, N, selectX, selectY);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	selectX = (int) x;
                	selectY = (int) y;
                	b.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            		selectX = -1;
            		selectY = -1;
            	}
            }            
            StdDrawPlus.show(10);
        }
    }

		








}