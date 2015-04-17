
public class Board {
    private Piece[][] pieces;
    private String whoseTurn = "-fire";
	private boolean movedYet = false;
	private boolean hasSelected = false;
	private int selectedx = -10;
	private int selectedy= -10;
	private Piece movedPiece;
	private boolean canCapture;
	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty == true){
			pieces = new Piece[8][8];
		}
		else{
			pieces = new Piece[8][8];
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	            	if ((i % 2 == 0 && j % 2 == 0) || ((i % 2 != 0) && (j % 2 != 0)) ){
	            		if (j ==0 ){
	            			pieces[i][j] = new Piece(true, this, i, j, "pawn");
	            }
	            		if (j ==1){
	            			pieces[i][j] = new Piece(true, this, i, j, "shield");
	            		}
	            		if (j ==2){
	            			pieces[i][j] = new Piece(true, this, i, j, "bomb");
	            }
	            		if (j == 5){
	            			pieces[i][j] = new Piece(false, this, i, j, "bomb");
	            		}
	            		if (j ==6){
	            			pieces[i][j] = new Piece(false, this, i, j, "shield");
	            		}
	            		if (j == 7){
	            			pieces[i][j] = new Piece(false, this, i, j, "pawn");
	            		}
			//initialize board with default
		}
	}
	}
	}
	}
	private String pieceisFire(Piece piece1){
		if (piece1.side() == 0){
			return "-fire";
		}
		else{
			return "-water";
		}
	}
	private boolean OutOfBounds(int x,int y){
		return ((x < 0) || (x > 7) || (y < 0) || (y > 7));
	}
	private String kindOfPiece(Piece piece1){
		if (piece1.isShield()== true){
			return "shield";
		}
		if (piece1.isBomb()== true){
			return "bomb";
		}
		else{
			return "pawn";
		}
	}
	private String pieceisCrowned(Piece piece2){
		if (piece2.isKing()){
			return "-crowned";
		}
		else{
			return "";
		}
	}
	public Piece pieceAt(int x, int y){
		if (OutOfBounds(x,y)){
			return null;
	}
		Piece isPieceThere = pieces[x][y];
		if (isPieceThere == null){
			return null;
		}
		else{
			return isPieceThere;
		}
	}
	
	public Piece remove(int x, int y){
		if (OutOfBounds(x,y)){
			System.out.println("Yo dawg, that's out of bounds, man.");
			return null;
	}
		if (pieceAt(x,y) == null){
			System.out.println("Yo dawg, there's no piece to remove.");
			return null;
		}
		else{
			Piece returnedPiece = pieces[x][y];
			pieces[x][y] = null;
			return returnedPiece;
		}
	}
	public void place(Piece p, int x, int y){
		if (OutOfBounds(x,y)){
			return;
		}
		else{
			pieces[x][y] = p;
		}
	}		
	
	private boolean validMove(int xi, int yi, int xf, int yf){
		if (OutOfBounds(xi,yi)){
			return false;
		}
		if (OutOfBounds(xf,yf)){
			return false;
		}
		if (pieceAt(xi,yi) == null){
			return false;
		}
		int upY = yi + 1;
		int downY = yi -1;
		int rightX = xi + 1;
		int leftX = xi -1;
		if (pieceAt(xi,yi).isKing() || pieceAt(xi,yi).isFire()){
			if ((xf == rightX && yf == upY) || (xf == leftX && yf == upY)){
				return true;
			}
			if (pieceAt(rightX,upY) != null ){
				if ( (xf == rightX + 1) && (yf == upY + 1) && (pieceAt(rightX,upY).isFire() != pieceAt(xi,yi).isFire()) ){
					canCapture = true;
					return true;
				}
			}
			if (pieceAt(leftX,upY) != null ){
				if ( ( (xf == leftX - 1) &&  (yf == upY +1) && (pieceAt(leftX,upY).isFire() != pieceAt(xi,yi).isFire()) )){
					canCapture = true;
					return true;
				}
			}
		}
		if (pieceAt(xi,yi).isKing() || pieceAt(xi,yi).isFire()!= true ){
			if ((xf == leftX && yf == downY) || (xf == rightX && yf == downY)){
				return true;
			}
			if (pieceAt(rightX,downY) != null ){
				if  (( (xf == rightX + 1) && (yf == downY - 1) && (pieceAt(rightX,downY).isFire() != pieceAt(xi,yi).isFire()) ) ){
					canCapture = true;
					return true;
				}	
			}
			if (pieceAt(leftX,downY) != null ){
				if ( ( (xf == leftX - 1) &&  (yf == downY -1) && (pieceAt(leftX,downY).isFire() != pieceAt(xi,yi).isFire()) )){
					canCapture = true;
					return true;
				}
			}
		}

		return false;
	}
	
	
	
	public boolean canSelect(int x, int y){
		if (OutOfBounds(x,y)){
			return false;
		}
		if (pieceAt(x,y) != null ){
			if (hasSelected == false){
				if (pieceisFire(pieceAt(x,y)) == whoseTurn){
					return true;
				}
			}
			if (movedYet == false){
				if (pieceisFire(pieceAt(x,y)) == whoseTurn){
					return true;
				}
			}
		}
		if (pieceAt(x,y) == null){
			if (hasSelected == true){
				if (movedYet != true){
					if (pieceisFire(pieceAt(selectedx,selectedy)) == whoseTurn){
						return validMove(selectedx,selectedy,x,y);
					}
					return false;
				}
				if (movedYet){
					if(pieceAt(selectedx,selectedy) != null ){
						if ((pieceAt(selectedx,selectedy).hasCaptured())){
							if (pieceisFire(pieceAt(selectedx,selectedy)) == whoseTurn){
								canCapture = false;
								if (validMove(selectedx,selectedy,x,y) ){
									return canCapture;
								}

						}
						return false;
					}
					return false;
					}
				}
			}
		}
		return false;
}	
	public void select(int x, int y){
		if (pieceAt(x,y) != null){
			selectedx = x;
			selectedy = y;
			hasSelected = true;
		}
		if (pieceAt(x,y) == null ){
				movedPiece = remove(selectedx,selectedy);
				movedPiece.move(x,y);
				canCapture = false;
				if (pieceAt(x,y) == null){
						movedYet = true;
						selectedx = -10;
						selectedy = -10;
						return;
				}
				selectedx = x;
				selectedy = y;
				movedYet = true;	
				}
			}
	
	public boolean canEndTurn(){
		return movedYet;
	}
	public void endTurn(){
		if (canEndTurn()){
			int x = selectedx;
			int y= selectedy;
			selectedx = -10;
			selectedy =-10;
			canCapture = false;
			hasSelected = false;
			movedYet = false;
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	            		if(pieces[i][j] != null){
	            			pieces[i][j].doneCapturing();
	       }  }	        }
			if (whoseTurn == "-fire"){
				whoseTurn = "-water";
			}
			else{
				whoseTurn = "-fire";
			}		}
	}
	public String winner(){
		int counterWater = 0;
		int counterFire = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            		if(pieces[i][j] != null){
            			if (pieces[i][j].isFire()){
            				counterFire= counterFire + 1;
            			}
            			else{
            				counterWater= counterWater + 1;
            			}
            		}
            	}
	}
       if (counterWater == 0 && counterFire != 0){
    	   return "Fire";
       }
       if (counterFire == 0 && counterWater != 0){
		   return "Water";
       }
	   if ((counterFire ==0 && counterWater ==0)){
		   return "No one";
	   }
	   if (counterFire != 0 && counterWater != 0){
		   return null;
	   }
	   else{
		   return null;
	   }
	}

	

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + kindOfPiece(pieces[i][j]) + pieceisFire(pieces[i][j]) + pieceisCrowned(pieces[i][j])+ ".png", 1, 1);
                }
                if (i == selectedx && j == selectedy){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i+.5,j+.5,.5);
                	if(pieces[i][j] != null){
                		StdDrawPlus.picture(i + .5, j + .5, "img/" + kindOfPiece(pieces[i][j]) + pieceisFire(pieces[i][j]) + pieceisCrowned(pieces[i][j]) + ".png", 1, 1);
                }
                }
            }
        
        }
        
    }
    private void makeBoard(){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while(true) {
            drawBoard(8);
            StdDrawPlus.show(10);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                System.out.println(canSelect((int)x, (int)y ));
                if(canSelect((int)x,(int)y)){
                	select((int)x,(int)y);
            }
            }
            if (StdDrawPlus.isSpacePressed()){
            	endTurn();
            }
        }
    }

	public static void main(String[] args) {
		//Board Board1 = new Board(false);
		//Board1.makeBoard();

    	Board b = new Board(false);
    	b.makeBoard();
 	
	}
	}


