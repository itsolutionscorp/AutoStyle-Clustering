public class Board{

	private Piece[][] board;
	private boolean isFireTurn;
	private boolean moved;

	private int xPiece;
	private int yPiece;

	private int potato;

	public Board(boolean shouldBeEmpty){
		board = new Piece[8][8];
		isFireTurn = true;
		moved = false;
		xPiece = -1;
		yPiece = -1;
		potato = 0;
		if(!shouldBeEmpty){
			multiplace(7, 1, "pawn", false);
			multiplace(6, 0, "shield", false);
			multiplace(5, 1, "bomb", false);
			multiplace(2, 0, "bomb", true);
			multiplace(1, 1, "shield", true);
			multiplace(0, 0, "pawn", true);
		}
	}

	private void multiplace(int row, int startCol, String type, boolean isFire){
		for(int i = startCol; i < 8; i += 2){
			place(new Piece(isFire, this, i, row, type), i, row);
		}
	}

	private static String getPieceFile(Piece p){
		String path;
		//append fire or water
		if(p.isFire()){
			path = "fire";
		}
		else{
			path = "water";
		}
		//append if King
		if(p.isKing()){
			path = path.concat("-crowned.png");
		}
		else{
			path = path.concat(".png");
		}
		//append type
		if(p.isBomb()){
			path = "bomb-".concat(path);
		}
		else if(p.isShield()){
			path = "shield-".concat(path);
		}
		else{
			path = "pawn-".concat(path);
		}
		
		path = "img/".concat(path);
		return path;	
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 - potato == 0) StdDrawPlus.setPenColor(255, 0, 255);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
                if(i == xPiece && j == yPiece){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (board[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getPieceFile(board[i][j]), 1, 1);
                }
            }
        }
        if(potato == 0){
            potato = 1;
        }
        else{
        	potato = 0;
        }
    }

	public static void main(String[] args){
		Board board = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(board.winner() == null){
			board.drawBoard(8);
			StdDrawPlus.show(25);

			if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                
                if(board.canSelect(x, y)){
                	board.select(x, y);
                }
            }
	        if(board.canEndTurn() && StdDrawPlus.isSpacePressed()){
	        	board.endTurn();
	        }       
		}
	}

	public Piece pieceAt(int x, int y){
		if(isOnBoard(x, y)){
			return board[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y){
		Piece square = pieceAt(x, y);
		if(square != null){
			return (square.isFire() == isFireTurn && !moved);
		}

		if(pieceAt(xPiece, yPiece) != null){
			if(!moved || pieceAt(xPiece, yPiece).hasCaptured()){
				return validMove(xPiece, yPiece, x, y);
			}
			else{
				return false;
			}
		}
		else
			return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if(!pieceAt(xPiece, yPiece).hasCaptured() && Math.abs(xf - xi) == 1 && ((yf ==  yi - 2*pieceAt(xPiece, yPiece).side() + 1)
			|| (yf ==  yi + 2*pieceAt(xPiece, yPiece).side() - 1 && pieceAt(xPiece, yPiece).isKing()))) {
			return true;
		}
		else if((Math.abs(xf - xi) == 2 && yf == yi - 4*pieceAt(xPiece, yPiece).side() + 2) ||
				(Math.abs(xf - xi) == 2 && yf == yi + 4*pieceAt(xPiece, yPiece).side() - 2 && pieceAt(xPiece, yPiece).isKing())) {
			return (pieceAt((int)((xi + xf)/2), (int)((yi + yf)/2)) != null 
				&& pieceAt((int)((xi + xf)/2), (int)((yi + yf)/2)).isFire() != isFireTurn);
		}
		else
			return false;
	}

	public void select(int x, int y){
		Piece square = pieceAt(x, y);
		Piece sel = pieceAt(xPiece, yPiece);
		if(square != null){
			xPiece = x;
			yPiece = y;
		}
		else{
			if(sel != null){
				sel.move(x, y);
				if(sel.isBomb()&&sel.hasCaptured()){
					xPiece = -1;
					yPiece = -1;
				}
				else{
					xPiece = x;
					yPiece = y;
				}
				moved = true;
			}
		}
	}

	private boolean isOnBoard(int x, int y){
		return(x >= 0 && x < 8 && y >= 0 && y < 8);
	}

	public void place(Piece p, int x, int y){
		if(p == null){
			return;
		}
		if(isOnBoard(x, y)){
			board[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		if(!isOnBoard(x, y)){
			System.out.println("dude. you went too far.");
			return null;
		}
		if(board[x][y] == null){
			System.out.println("dude. there's nothing there.");
			return null;
		}
		Piece removed = pieceAt(x, y);
		board[x][y] = null;
		return removed;
	}

 	public boolean canEndTurn(){
 		return moved;
 	}

 	public void endTurn(){
 		moved = false;
 		isFireTurn = !isFireTurn;
 		if(pieceAt(xPiece, yPiece) != null) pieceAt(xPiece, yPiece).doneCapturing();
 		xPiece = -1;
 		yPiece = -1;
 	}

 	public String winner(){
 		int numWaterPieces = 0;
 		int numFirePieces = 0;
 		for(int i = 0; i < 8; i++){
 			for(int j = 0; j < 8; j++){	
 				if(pieceAt(i, j) != null){
 					if(pieceAt(i, j).isFire())
 						numFirePieces++;
 					else
 						numWaterPieces++;
 				}
 			}
 		}
 		// System.out.println(numWaterPieces + ", " + numFirePieces);
 		if(numWaterPieces == 0 && numFirePieces == 0){
 			return "No one";
 		}
 		if(numWaterPieces == 0){
 			return "Fire";
 		}
 		if(numFirePieces == 0){
 			return "Water";
 		}
 		else{
 			return null;
 		}
 	}
}