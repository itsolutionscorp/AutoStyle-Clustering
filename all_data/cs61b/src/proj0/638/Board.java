public class Board {
	private boolean selected = false;
	private Piece[][] pieceArray = new Piece[8][8];
	private int currentPlayer=0;
	private boolean moveTurn = false;
	private Piece selectedPiece;
	private int xPosSelect;
	private int yPosSelect;
	private int fireNumber=0;
	private int waterNumber=0;
	private boolean firePiecesMade;
	private boolean waterPiecesMade;
	private boolean boardNew=true;

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
		        if (pieceArray[i][j]!=null) {
		            StdDrawPlus.picture(i + .5, j + .5, getImageStr(pieceArray[i][j]), 1, 1);

		        }
		    }
		}
		if (moveTurn || selected) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(xPosSelect + .5, yPosSelect + .5, .5);
			if (pieceAt(xPosSelect,yPosSelect)!=null) {
				StdDrawPlus.picture(xPosSelect + .5, yPosSelect + .5, getImageStr(pieceArray[xPosSelect][yPosSelect]), 1, 1);
			}
		}
    }
   	private String getImageStr (Piece p) {

   		String s;
    	String ending=".png";
    	String crownedstring= "-crowned.png";
    	if (p.isShield()){
    		s="img/shield";
    	}
    	else if (p.isBomb()){
    		s="img/bomb";
    	}
    	else {
    		s="img/pawn";
    	}
    
        
       	if (p.isFire()){
        	s=s+"-fire";
        }
        else {
        	s=s+"-water";
        }
        if (p.isKing()){
        	s=s+"-crowned";
        }
        s=s+ending;

        return s;
   	}
	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b =  new Board(false);
        

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xInt = (int) x;
                int yInt = (int) y;
                if (b.canSelect(xInt,yInt)) {
                	b.select(xInt, yInt);
                }

            }
            if (StdDrawPlus.isSpacePressed()) {
            	//if (b.canEndTurn()) {
            	if (b.canEndTurn()){
            		b.endTurn();
            	}
            }
                

                //pieces--doesnt exist anymore--[(int) x][(int) y] = true;
                        
            StdDrawPlus.show(100);
        }
    }
	public Board (boolean shouldBeEmpty) {
		if (shouldBeEmpty==false) {
			boardNew=false;
			place(new Piece (true, this, 0, 0, "pawn"), 0 , 0);
			place(new Piece (true, this, 1, 1, "shield"), 1, 1);
			place(new Piece (true, this, 0, 2, "bomb"), 0 ,2);
			place(new Piece (false, this, 1, 7, "pawn"),1 ,7);
			place(new Piece (false, this, 0, 6, "shield"), 0 , 6);
			place(new Piece (false, this, 1, 5, "bomb"),1,5);

			place(new Piece (true, this, 2, 0, "pawn"),2,0);
			place(new Piece (true, this, 3, 1, "shield"),3,1);
			place(new Piece (true, this, 2, 2, "bomb"),2,2);
			place(new Piece (false, this, 3, 7, "pawn"),3,7);
			place(new Piece (false, this, 2, 6, "shield"),2,6);
			place(new Piece (false, this, 3, 5, "bomb"),3,5);

			place(new Piece (true, this, 4, 0, "pawn"),4,0);
			place(new Piece (true, this, 5, 1, "shield"),5,1);
			place(new Piece (true, this, 4, 2, "bomb"),4,2);
			place(new Piece (false, this, 5, 7, "pawn"),5,7);
			place(new Piece (false, this, 4, 6, "shield"),4,6);
			place(new Piece (false, this, 5, 5, "bomb"),5,5);

			place(new Piece (true, this, 6, 0, "pawn"),6,0);
			place(new Piece (true, this, 7, 1, "shield"),7,1);
			place(new Piece (true, this, 6, 2, "bomb"),6,2);
			place(new Piece (false, this, 7, 7, "pawn"),7,7);
			place(new Piece (false, this, 6, 6, "shield"),6,6);
			place(new Piece (false, this, 7, 5, "bomb"),7,5);


		
		}
		else {
			pieceArray = new Piece[8][8];
			boardNew=true;
		}
			
		
	}
				
				
	public Piece pieceAt(int x, int y){
		if (x>7 || y>7 || x<0 || y<0) {
			return null;
		}
		else if (pieceArray[x][y]!=null) {
			return pieceArray[x][y]; 
		}
		else {
			return null;
		}
	}
	private boolean validMove (Piece p, int xi, int yi, int xf, int yf) {
		if (xf>7 || xf<0 || yf>7 || yf<0 || xi>7 || xi<0 || yi>7 || yi<0) {
			return false;
		}
		if (abs(xf-xi)>2 || abs(yf-yi)>2){
			return false;
		}
		if (pieceArray[xf][yf]==null) {
			if (p.isKing()) {
				if (isDiagonal(xi,yi,xf,yf) && (abs(xf-xi)==1) && (abs(yf-yi)==1)) {
					return true;
				}
				//single capture
				else if (canSingleCapture(p,xi,yi,xf,yf)){
					return true;
				}
				//else if (validMove(p, xi, yi, xi+2, yi+2) && xcanSingleCapture(p, xi+2, yi+2, xf, yf)){
				//	return true;
				//}
				//need multi-capture
				/*if (multiCapCheck(p, xi, yi, xf, yf)){
					return true;
				}*/

			}
			else {
				if (p.isFire()) {
					if (yf>yi && isDiagonal(xi,yi,xf,yf) && abs(yf-yi)==1 && abs(xf-xi)==1) {
						return true;
					}
					if (canSingleCapture(p,xi,yi,xf,yf)){

						return true;
					}
					/*if (multiCapCheck(p, xi, yi, xf, yf)){
						return true;
					}*/
				}
				if (!p.isFire()){
					if (yf<yi && isDiagonal(xi,yi,xf,yf) && abs(yf-yi)==1 && abs(xf-xi)==1) {
						return true;
					}
					if (canSingleCapture(p,xi,yi,xf,yf)){
						
						return true;

					}
					/*if (multiCapCheck(p, xi, yi, xf, yf)){
						return true;
					}*/
				}
				
				//put in the nonKing multi-capture
				
			}
			
		}
		return false;
		//want to make it so that if it is a bomb, cut off there with only one, but if not, then do multi cap
	}
	/*private boolean multiCapCheck(Piece p, int xi, int yi, int xf, int yf) {
		if (xf>7 || xf<0 || yf>7 || yf<0 || xi>7 || xi<0 || yi>7 || yi<0) {
			return false;
		}
		if (xi==xf && yi==yf) {
			return true;
		}
		if (p.isKing() || p.isFire()) {
			if (canSingleCapture(p,xi,yi,xi+2, yi+2)) {
				return multiCapCheck(p, xi+2, yi+2, xf, yf);
			}
			if (canSingleCapture(p,xi,yi,xi-2, yi+2)){
				return multiCapCheck(p, xi-2, yi+2, xf, yf);
			}
		}
		if (p.isKing() || !p.isFire()) {
			if (canSingleCapture(p,xi,yi, xi+2, yi-2)){
				return multiCapCheck(p, xi+2, yi-2, xf, yf);
			}
			if (canSingleCapture(p,xi,yi,xi-2, yi+2)){
				return multiCapCheck(p, xi-2, yi-2, xf, yf);
			}

		}
		return canSingleCapture(p,xi,yi,xf,yf);
		
	}*/
	private int putBounds (int x) {
		if (x>7) {
			return 7;
		}
		else if (x<0) {
			return 0;
		}
		else {
			return x;
		}
	}
	private boolean canSingleCapture (Piece p, int xi, int yi, int xf, int yf) {
		//can a piece move to a certain spot with a capture?
		if (xf>7 || xf<0 || yf>7 || yf<0 || xi>7 || xi<0 || yi>7 || yi<0) {
			return false;
		}

		if (!isDiagonal (xi, yi, xf, yf) || (abs(xf-xi)==1 && abs(yf-yi)==1)) {
			return false;
			//may need to fix for multi-capture
		}
		xf=putBounds(xf);
		xi=putBounds(xi);
		yi=putBounds(yi);
		yf=putBounds(yf);

		//implement king and capture
		if (p.isKing()) {
			if ((xf == xi + 2) && (yf == yi+2)) { //moving to top right
				if (pieceArray[putBounds(xf-1)][putBounds(yf-1)]!=null && p.isFire()!=pieceArray[putBounds(xf-1)][putBounds(yf-1)].isFire()) {
					return true;
				}
				else {
					return false;
				}
			}
			if ((xf +2 == xi) && (yf == yi+2) ) { //moving to top left
				if (pieceArray[putBounds(xf+1)][putBounds(yf-1)]!=null && p.isFire()!=pieceArray[putBounds(xf+1)][putBounds(yf-1)].isFire()) {
					return true;
				}
				else {
					return false;
				}
			}
			if ( (xf == xi+2) && (yf + 2 == yi)) { //moving to bottom right
				if (pieceArray[putBounds(xf-1)][putBounds(yf+1)]!=null && p.isFire()!=pieceArray[putBounds(xf-1)][putBounds(yf+1)].isFire()) {
					return true;
				}
				else {
					return false;
				}
			}
			if ((xf+2== xi) && (yf + 2 == yi)) { //moving to bottom left
				if (pieceArray[putBounds(xf+1)][putBounds(yf+1)]!=null && p.isFire()!=pieceArray[putBounds(xf+1)][putBounds(yf+1)].isFire()) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		//CHECK ALL OF THESE TO MAKE SURE THEY DONT GO OUT OF BOUNDS
		else if (!p.isKing() && p.isFire()) {
			if ((xf ==xi + 2) && (yf == yi+2)) { //moving to top right
				if (pieceArray[putBounds(xf-1)][putBounds(yf-1)]!=null && p.isFire()!=pieceArray[putBounds(xf-1)][putBounds(yf-1)].isFire()) {
					return true;
				}
				else {
					return false;
				}
			}
			if ((xf +2 == xi) && (yf == yi+2) ) { //moving to top left
				if (pieceArray[putBounds(xf+1)][putBounds(yf-1)]!=null && p.isFire()!=pieceArray[putBounds(xf+1)][putBounds(yf-1)].isFire()) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		else if (!p.isKing() && !p.isFire()) {
			if ((xf == xi+2) && (yf + 2 == yi)) { //moving to bottom right
				if (pieceArray[putBounds(xf-1)][putBounds(yf+1)]!=null && p.isFire()!=pieceArray[putBounds(xf-1)][putBounds(yf+1)].isFire()) {
					return true;
				}
				else {
					return false;
				}
			}
			if ((xf+2== xi) && (yf + 2 == yi)) { //moving to bottom left
				if (pieceArray[putBounds(xf+1)][putBounds(yf+1)]!=null && p.isFire()!=pieceArray[putBounds(xf+1)][putBounds(yf+1)].isFire()) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	private int abs (int x) {
		if (x<0){
			return -x;
		}
		else {
			return x;
		}
	}

	private boolean isDiagonal (int xi, int yi, int xf, int yf) {
		if (xf>7 || xf<0 || yf>7 || yf<0) {
			return false;
		}
		if (abs(xf-xi)==abs(yf-yi)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean canSelect(int x, int y){
		/*True if player has not selected a piece
		or has selected a piece but did not move it*/
		/*empty square selectable if player has selected
		a piece which hasn't moved yet and is selecting
		a spot that is a valid move*/
	// 	In this turn, the player has selected a Puece, captured 
	// 	and selected a valid capture dest. When performing multi-captures
	// 	you should only select the active piece once; all other selections
	// 	should be valid destination points
		//Selecting a piece
		
		
		if (x>7 || x<0 || y>7 || y<0) {
			return false;
		}
		
			if ((pieceArray[x][y]!=null) && (!selected || !moveTurn) && currentPlayer==pieceArray[x][y].side()) { //also account for player turn
				return true;
			}
			//Selecting an empty space
			else if (selected && !moveTurn && pieceArray[x][y]==null && validMove(selectedPiece, xPosSelect, yPosSelect, x, y) 
				&& selectedPiece.side()==currentPlayer && pieceAt(xPosSelect,yPosSelect)==selectedPiece && !selectedPiece.hasCaptured()){
				return true; //also account for selected piece validly moving there and multi-capture
			}
		/*else if (pieceAt(xPosSelect,yPosSelect)!=null && pieceAt(xPosSelect,yPosSelect).hasCaptured() && moveTurn) {
            return true;
            //secret test 14 canSelect calls after a capture
        }*/
        	else if (moveTurn && selectedPiece!=null 
        		&& selectedPiece.hasCaptured() && canSingleCapture(selectedPiece, xPosSelect, yPosSelect,x , y)
        		&& selectedPiece.side()==currentPlayer && selectedPiece==pieceAt(xPosSelect,yPosSelect)) {
        		return true;
       		}
		
		else {
			/*int xi=xPosSelect;
			int yi=yPosSelect;
			Piece p = selectedPiece;
			if (selected && !moveTurn && pieceArray[x][y]==null) {
				return (canSelect(x+2, y+2) || canSelect(x-2, y-2) || canSelect(x-2, y+2) || canSelect(x+2, y+2));
			}
*/
			return false;
		}
		
		
	}

	public void select (int x, int y) {
		//Select the square at (x,y). assumes canSelect(x,y) returns true
		//recommended to color the background of selected square white on the GUI
		//via pen color funciton
		//For any piece to do a capture, must have been selected first
		//if you select a square with a piece, you prep that piece to move
		//if you select an empty Square, assuming canSelect returns true, should move it to that square
		
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		//HAVING StdDrawPlus may mess you up in autograder
                
		Piece p=selectedPiece;
		selected=true;
		if (pieceArray[x][y]!=null){
			selectedPiece= pieceArray[x][y];
			xPosSelect = x;
			yPosSelect = y;
			StdDrawPlus.picture(x + .5, y + .5, getImageStr(pieceArray[x][y]), 1, 1);
		}
		else if (selectedPiece!=null ) { //removed && pieceArray[x][y]==null
            selectedPiece.move(x,y);
            /*pieceArray[x][y]=selectedPiece;
            pieceArray[xPosSelect][yPosSelect]=null;*/

            moveTurn=true;
            xPosSelect=x;
            yPosSelect=y;

   		}
   		//assuming canSelect says ok
		else {
			System.out.println("Make sure you have a piece selected");
		}
		drawBoard(8);
		int xi=xPosSelect;
		int yi=xPosSelect;
		
		//if (moveTurn) {selectedPiece=null;}

		
	}

	public void place (Piece p, int x, int y){
		if (x>7 || y>7 || x<0 || y<0) {
			
		}
		else if (p==null) {

		}
		else {
			Piece oldPiece= pieceArray[putBounds(x)][putBounds(y)];
			pieceArray[putBounds(x)][putBounds(y)]=p;
			if (p!=null && p.isFire()) {
				firePiecesMade=true;
				fireNumber=fireNumber+1;
			}
			if (p!=null && !p.isFire()) {
				waterPiecesMade=true;
				waterNumber=waterNumber+1;
			}
		}

	}
	//Done
	public Piece remove (int x, int y){
		if (x>7 || y>7 || x<0 || y<0) {
			System.out.println("Out of bounds, cannnot remove");
			return null;
		}
		if (pieceArray[x][y]==null) {
			System.out.println("No piece to remove");
			return null;
		}
		else {
			Piece oldPiece = pieceArray[x][y];
			pieceArray[x][y]=null;
				if (oldPiece!=null && oldPiece.isFire()) {
					fireNumber=fireNumber-1;
				}
				if (oldPiece!=null && !oldPiece.isFire()) {
					waterNumber=waterNumber-1;
				}
			return oldPiece;
		}
	}
	public boolean canEndTurn() {
		if ((selectedPiece!=null && selectedPiece.hasCaptured()) || (selectedPiece!=null && moveTurn) || (selectedPiece!=null && (selectedPiece.hasCaptured() && moveTurn))) {
			return true;
		}
		else {
			return false;
		}


	}
	public void endTurn(){
		if (selectedPiece!=null) {
			selectedPiece.doneCapturing();
		}
		if (currentPlayer==0) {
			currentPlayer=1;
		}
		else if (currentPlayer==1) {
			currentPlayer=0;
		}
		moveTurn=false;
		selected=false;

		drawBoard(8);

	}
	public String winner(){
		if (fireNumber==0 && waterNumber==0) {
			//System.out.println("No one");
			return "No one";
			
		}
		else if (fireNumber==0 && firePiecesMade) {
			//System.out.println("Water");
			return "Water";
			
		}
		else if (waterNumber==0 && waterPiecesMade) {
			//System.out.println("Fire");
			return "Fire";
		
		}
		else if (fireNumber>0 && waterNumber>0) {
			return null;
		}
		else {
			//System.out.println("null");
			return null;

		}
	}
} 