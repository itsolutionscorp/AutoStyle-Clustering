/** 
 *  @author Xiaoyi Cheng
 */

public class Board {
	private int N = 8;
	private static Piece[][] pieces;
	private boolean currPlayerIsFire = true;// true for "fire" and false for "water"
	private int s_x, s_y;// record last selected position
	private boolean selected = false;
	private boolean moved = false;
	private boolean hasCaptured;
	
	public static void main(String[] args) {
        int N = 8;
        int activeX = -1, activeY = -1;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new Piece[N][N];
        Board b = new Board(false);
        
   
		while(b.winner() == null){
			if(StdDrawPlus.isSpacePressed() == false){
				if(StdDrawPlus.mousePressed()){
					int x = (int) StdDrawPlus.mouseX();
					int y = (int) StdDrawPlus.mouseY();
					if(b.canSelect(x, y)){
						b.select(x, y);
						activeX = x;
						activeY = y;
					}
					b.drawBoard(N, activeX, activeY);
					for (int i = 0; i < N; i++) {
			            for (int j = 0; j < N; j++) {
			            	b.drawPiece(i, j);
			            }
			        }
					StdDrawPlus.show(25);
				}
				
			}else{
				if(b.canEndTurn()){
					b.endTurn();
					activeX = -1;
					activeY = -1;
					b.drawBoard(N, -1, -1);
					for (int i = 0; i < N; i++) {
			            for (int j = 0; j < N; j++) {
			            	b.drawPiece(i, j);
			            }
			        }
					StdDrawPlus.show(10);
				}
			}

		}
		System.out.println("Winner is " + b.winner());
		return;
	}

		
	
	/**
	 * Constructor for Board
	 */
	public Board(boolean shouldBeEmpty){
		pieces = new Piece[N][N];
		currPlayerIsFire = true;
		s_x = -1; 
		s_y = -1;
		if(shouldBeEmpty == true){
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	            	pieces[i][j] = null;
	            }
	        }
			drawBoard(N, -1, -1);//initializes an empty Board
		}else{
			//initializes a Board with the default configuration
			drawBoard(N, -1 , -1);
			//configureCheckers(N);
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	            	configurePiece(i, j);
	            	drawPiece(i, j);
	            }
			}
		}
	}
	/**
	 * helper method drawBoard
	 */
	private void drawBoard(int n, int activeX, int activeY) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if((i + j) % 2 == 0){
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }else{
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);    
            }
        }
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(activeX + .5, activeY + .5, .5);
    }
	
	private void configurePiece(int i, int j){
        if(i % 2 == 0 && j == 0){
        	pieces[i][j] = new Piece(true, this, i, j, "pawn");
        }
        if(i % 2 == 1 && j == 1){
            pieces[i][j] = new Piece(true, this, i, j, "shield");
        }
        if(i % 2 == 0 && j == 2){
        	pieces[i][j] = new Piece(true, this, i, j, "bomb");
        }
        if(i % 2 == 1 && j == N - 1){
        	pieces[i][j] = new Piece(false, this, i, j, "pawn");
        }
        if(i % 2 == 0 && j == N - 2){
        	pieces[i][j] = new Piece(false, this, i, j, "shield");
        }
        if(i % 2 == 1 && j == N - 3){
        	pieces[i][j] = new Piece(false, this, i, j, "bomb");
        }
	}
	
	private void drawPiece(int i, int j){           
		if(pieceAt(i, j) == null){
			return;
		}
		if(pieceAt(i, j).isKing()){//
        	if(pieceAt(i, j).isFire()){// fire king pieces
            	if(pieceAt(i, j).isBomb()){
            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
            	}else if(pieceAt(i, j).isShield()){
            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            	}else{
            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
            	}       
            }else{ // water king pieces
            	if(pieceAt(i, j).isBomb()){
            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            	}else if(pieceAt(i, j).isShield()){
            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            	}else{
            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            	} 
            }       	
        }else{
        	if(pieces[i][j].isFire()){// fire pieces, not king
            	if(pieces[i][j].isBomb()){
            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            	}else if(pieces[i][j].isShield()){
            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            	}else{
            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            	}       
            }else{ // water pieces, not king
            	if(pieces[i][j].isBomb()){
            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            	}else if(pieces[i][j].isShield()){
            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            	}else{
            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            	} 
            }       	
        }
		
	}
	
	public Piece pieceAt(int x, int y){
		if(x >= N || y >= N || x < 0 || y < 0 || pieces[x][y] == null){
			return null;
		}else{
			return pieces[x][y];
		}
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		if(!validBound(xi, yi) || !validBound(xf, yf) || pieceAt(xf, yf) != null)
			return false;
		if(pieceAt(xi, yi) != null){
			if(pieceAt(xi, yi).isKing() == false){//not a king
				if(pieceAt(xi, yi).isFire() == false){//water's turn
					if((Math.abs(xf - xi) == 1 && yi - yf == 1 && pieceAt(xf, yf) == null) || (Math.abs(xf - xi) == 2 && yi - yf == 2 && pieceAt((xi + xf)/2, (yi + yf)/2).isFire() == true)){
						return true;
					}else{
						return false;
					}
				}else{
					if((Math.abs(xi - xf) == 1 && yf - yi == 1 && pieceAt(xf, yf) == null) || (Math.abs(xi - xf) == 2 && yf - yi == 2 && pieceAt((xi + xf)/2, (yi + yf)/2).isFire() == false)){
						return true;
					}else{
						return false;
					}
				}
			}else{// is a king
				if((Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1 && pieceAt(xf, yf) == null) || 
						(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 && pieceAt((xi + xf)/2, (yi + yf)/2).isFire() != pieceAt(xi, yi).isFire())){
					return true;
				}else{
					return false;
				}	
			}
		}else{
			return false;
		}
	}
	
	private boolean validCaptureMove(int xi, int yi, int xf, int yf){
		if(!validBound(xf, yf) || pieceAt(xf, yf) != null)
			return false;
		if(pieceAt(xi, yi).isKing() == false){//not a king
			if(pieceAt(xi, yi).isFire() == false){//water's turn
				if(Math.abs(xf - xi) == 2 && yi - yf == 2 && pieceAt((xi + xf)/2, (yi + yf)/2).isFire() == true){
					return true;
				}else{
					return false;
				}
			}else{//fire turn
				if(Math.abs(xi - xf) == 2 && yf - yi == 2 && pieceAt((xi + xf)/2, (yi + yf)/2).isFire() == false){
					return true;
				}else{
					return false;
				}
			}
		}else{// is a king
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 && pieceAt((xi + xf)/2, (yi + yf)/2).isFire() != pieceAt(xi, yi).isFire()){
				return true;
			}else{
				return false;
			}	
		}	
	}

	private boolean validBound(int x, int y){
		if (x >= N || x < 0 || y >= N || y < 0){
			return false;
		}else{
			return true;
		}
	}
	
	//to be finished
	public boolean canSelect(int x, int y){
		if(validBound(x, y) == false){
			return false;
		}
		if(pieceAt(x, y) != null && currPlayerIsFire == pieceAt(x, y).isFire()){//a piece
			if(selected == false){
				return true;
			}else{
				if(moved == false){
					return true;
				}
			}

		}else{//if(pieceAt(x, y) == null)
			if(selected == true && validMove(s_x, s_y, x, y) && moved == false){
				return true;
			}
      			if(selected && moved && hasCaptured && validCaptureMove(s_x, s_y, x, y)){
				return true;
			}
		}
		return false;
	}
	
	//to be finished
	public void select(int x, int y){
		if(pieceAt(x, y) != null){//select a piece
        	selected = true;
        }else{//select an empty square and move there     	
        	pieces[s_x][s_y].move(x, y);
        	moved = true;
        	if(pieces[x][y] != null && pieces[x][y].hasCaptured()){
				hasCaptured = true;
			}
		}	
		s_x = x;
		s_y = y;
	}
	
	public void place(Piece p, int x, int y){
		if (p != null && validBound(x, y)){
				pieces[x][y] = p;
		}
	}
	
	public Piece remove(int x, int y){
		if(validBound(x, y) == false){
			System.out.println("invalid position!");
			return null;
		}else if(pieces[x][y] == null){
			System.out.println("no piece here to remove");
			return null;		
		}else{
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
	}
	
	public boolean canEndTurn(){
		return moved;
	}
	
	public void endTurn(){
		if(currPlayerIsFire == true){
			currPlayerIsFire = false;
		}else{
			currPlayerIsFire = true;
		}
		if(pieces[s_x][s_y] != null){
			pieces[s_x][s_y].doneCapturing();
		}
		selected = false;
		moved = false;
		hasCaptured = false;
		s_x = -1;
		s_y = -1;
	}
		
	
	public String winner(){
		int totFirePiece = 0;
		int totWaterPiece = 0;
		for(int i = 0; i < N; i++){
			for ( int j = 0; j < N; j++){
				if(pieces[i][j] != null ){
					if(pieces[i][j].isFire()){
						totFirePiece++;
					}else{
						totWaterPiece++;
					}
				}
			}
		}

		if(totFirePiece == 0 || totWaterPiece == 0){
			if(totFirePiece > 0){
				return "Fire";
			}else if(totWaterPiece > 0){
				return "Water";
			}else if(totFirePiece == totWaterPiece){
				return "No one";
			}else{
				return null;
			}
		}
		return null;
	}
}
