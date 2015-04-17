public class Board{

    private Piece[][] pieces;
    private boolean fireTurn;
    private Piece pieceSelected;
    private boolean selected;
    private int selectx;
    private int selecty; 
    private boolean pieceMoved;

	public Board(boolean shouldBeEmpty){
		pieces = new Piece[8][8];

		if(!shouldBeEmpty){
			for(int i = 0; i<8; i+=2){
				pieces[i][0] = new Piece(true, this, i, 0, "pawn");
				pieces[i+1][7] = new Piece(false, this, i+1, 7, "pawn");

				pieces[i+1][1] = new Piece(true, this, i+1, 1, "shield");
				pieces[i][6] = new Piece(false, this, i, 6, "shield");

				pieces[i][2] = new Piece(true, this, i, 2,  "bomb");
				pieces[i+1][5] = new Piece(false, this, i+1, 5, "bomb");
			}
		}
		
		fireTurn = true;
		pieceSelected = null;
		selectx = -1;
		selecty = -1;
		pieceMoved = false;
		selected = false;

	}
	public Piece pieceAt(int x, int y){
		if(x>7 || y>7 || x<0 || y<0)
			return null;
		return pieces[x][y];
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if(pieces[xi][yi] == null)
			return false;
		
		if (Math.abs(xi - xf)==1 && Math.abs(yf - yi) == 1 && pieces[xf][yf]==null){
			if(pieces[xi][yi].isKing())
				return true;
			if(pieces[xi][yi].isFire() && yf-yi == 1)
				return true;
			else if(!pieces[xi][yi].isFire() && yi-yf == 1)
				return true;
		}

		return validCapture(xi, yi, xf, yf);
	}

	private boolean validCapture(int xi, int yi, int xf, int yf){
		if(pieces[xi][yi] == null)
			return false;

		if (Math.abs(xi - xf) != 2 || Math.abs(xi - xf) != 2)
			return false;
		else{
			if(!pieces[xi][yi].isKing()){
				if(pieces[xi][yi].isFire() && yf-yi != 2)
					return false;
				else if(!pieces[xi][yi].isFire() && yi-yf != 2)
					return false;
			}
		}
		
		//average computations (to prevent overflow)
		int capturex = (xi+xf) >>> 1;
		int capturey = (yi+yf) >>> 1;
		
		return pieces[capturex][capturey] != null  && (pieces[capturex][capturey].isFire() ^ pieces[xi][yi].isFire());

	}

	public boolean canSelect(int x, int y){
		Piece square = pieces[x][y];
		if(square != null && square.isFire() != fireTurn)
			return false;

		if(pieceMoved){
			return validCapture(selectx, selecty, x, y);
		}

		if(square == null){
			return pieceSelected != null && validMove(selectx, selecty, x, y);
		}
		
		return true;
	}

	public void select(int x, int y){
		if(pieceSelected == null || pieceAt(x,y) != null){
			pieceSelected = pieces[x][y];
			selectx = x;
			selecty = y;
			selected = true;
		}else{
			pieceMoved = true;
			pieceSelected.move(x, y);
			selectx = x;
			selecty = y;
			selected = true;
		}
	}
	public void place(Piece p, int x, int y){
		if(x>7 || y>7 || x<0 || y<0)
			return;			
		pieces[x][y] = p;

	}
	public Piece remove(int x, int y){
		if(x>7 || y>7 || x<0 || y<0){
			System.out.println("Index out of bounds");
			return null;
		}
		Piece piece = pieces[x][y];

		if(piece == null){
			System.out.println("No piece at this position");
		}
		
		pieces[x][y] = null;
		return piece;
	}
	public boolean canEndTurn(){
		return pieceMoved;
	}
	public void endTurn(){
		pieceMoved = false;
		fireTurn = !fireTurn;
		pieceSelected.doneCapturing();
		pieceSelected = null;
		selected = false;
		selectx = -1;
		selecty = -1;
	}

	public String winner(){
		boolean isFirePiece = false;
		boolean isWaterPiece = false;
		for(int i = 0; i<8 && !(isFirePiece && isWaterPiece); i++){
			for(int j = 0; j<8  && !(isFirePiece && isWaterPiece); j++){
				if (pieces[j][i] != null){
					if(pieces[j][i].isFire())
						isFirePiece = true;
					else
						isWaterPiece = true;
				}
			}
		}
		if(!isFirePiece && !isWaterPiece){
			return "No one";
		}else if (!isFirePiece){
			return "Water";
		}else if (!isWaterPiece){
			return "Fire";
		}
		return null;
	}

	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (selected && i == selectx && j == selecty)
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);            		
            	else
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece piece = pieces[i][j];
                if (piece!=null) {
                    String picture = "img/";
                    
                    if(piece.isShield())
                    	picture+="shield-";
                    else if(piece.isBomb())
                    	picture += "bomb-";
                    else
                    	picture += "pawn-";

                    if(piece.isFire())
                    	picture += "fire";
                    else
                    	picture += "water";

                    if(piece.isKing())
                    	picture += "-crowned";

                    picture +=".png";
					StdDrawPlus.picture(i + .5, j + .5, picture, 1, 1);
                }
            }
        }
    }


	public static void main(String[] args){
		Board board = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        board.fireTurn = true;
        String winner;

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
        	if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(board.canSelect(x, y)){
            		board.select(x, y);
            	}
            }
            if (StdDrawPlus.isSpacePressed()){
            	if(board.canEndTurn()){
            		board.endTurn();
            	}
            }
            StdDrawPlus.show(100);
            board.drawBoard();
            winner = board.winner();
            if(winner != null){
            	System.out.println(winner);
            	break;
            }
        }                        
	}
}