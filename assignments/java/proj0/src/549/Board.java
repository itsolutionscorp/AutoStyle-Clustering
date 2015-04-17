
public class Board {
    /** Location of pieces. */

    private Piece[][] pieces; 
    private  boolean firePieceTurn; 
    private Piece selectedpiece; 
    private boolean hasMoved;
    private int xSelected; 
    private int ySelected; 
    private  boolean hasCaptured;  
    private static Board b; 
    
   

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(b.pieceAt(i, j) != null){
                if (b.pieceAt(i, j).isKing() && b.pieceAt(i, j).isFire() && !b.pieceAt(i,j).isShield() && !b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                }
                
                if (!b.pieceAt(i, j).isKing() && b.pieceAt(i, j).isFire() && !b.pieceAt(i,j).isShield() && !b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                }
                if (b.pieceAt(i, j).isKing() && !b.pieceAt(i, j).isFire() && !b.pieceAt(i,j).isShield() && !b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                }
              
                if (!b.pieceAt(i, j).isKing() && !b.pieceAt(i, j).isFire() && !b.pieceAt(i,j).isShield() && !b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                }
                if (b.pieceAt(i, j).isKing() && b.pieceAt(i, j).isFire() && b.pieceAt(i,j).isShield() && !b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                }
                if (!b.pieceAt(i, j).isKing() && b.pieceAt(i, j).isFire() && b.pieceAt(i,j).isShield() && !b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                }
                if (b.pieceAt(i, j).isKing() && !b.pieceAt(i, j).isFire() && b.pieceAt(i,j).isShield() && !b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                }
                if (!b.pieceAt(i, j).isKing() && !b.pieceAt(i, j).isFire() && b.pieceAt(i,j).isShield() && !b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                }
                if (b.pieceAt(i, j).isKing() && b.pieceAt(i, j).isFire() && !b.pieceAt(i,j).isShield() && b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
                if (!b.pieceAt(i, j).isKing() && b.pieceAt(i, j).isFire() && !b.pieceAt(i,j).isShield() && b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                }
                if (b.pieceAt(i, j).isKing() && !b.pieceAt(i, j).isFire() && !b.pieceAt(i,j).isShield() && b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                }
                if (!b.pieceAt(i, j).isKing() && !b.pieceAt(i, j).isFire() && !b.pieceAt(i,j).isShield() && b.pieceAt(i, j).isBomb()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                }
                
                }
            }
        }
    }
    public Board(boolean shouldBeEmpty){
    	firePieceTurn = true; 
    	if(shouldBeEmpty){
    	this.pieces = new Piece[8][8]; 
    }
    	else{
    		this.pieces = new Piece[8][8]; 
    	
    	
    		pieces[0][0] = new Piece(true, this, 0, 0, "pawn"); 
    		pieces[2][0] = new Piece(true, this, 2, 0, "pawn"); 
    		pieces[4][0] = new Piece(true, this, 4, 0, "pawn"); 
    		pieces[6][0] = new Piece(true, this, 6, 0, "pawn");  
    		pieces[1][1] = new Piece(true, this, 1, 1, "shield"); 
    		pieces[3][1] = new Piece(true, this, 3, 1, "shield"); 
    		pieces[5][1] = new Piece(true, this, 5, 1, "shield"); 
    		pieces[7][1] = new Piece(true, this, 7, 1, "shield"); 
    		pieces[0][2] = new Piece(true, this, 0, 2, "bomb"); 
    		pieces[2][2] = new Piece(true, this, 2, 2, "bomb"); 
    		pieces[4][2] = new Piece(true, this, 4, 2, "bomb"); 
    		pieces[6][2] = new Piece(true, this, 6, 2, "bomb");  
    		pieces[1][5] = new Piece(false, this, 1, 5, "bomb"); 
    		pieces[3][5] = new Piece(false, this, 3, 5, "bomb"); 
    		pieces[5][5] = new Piece(false, this, 5, 5, "bomb"); 
    		pieces[7][5] = new Piece(false, this, 7, 5, "bomb"); 
    		pieces[0][6] = new Piece(false, this, 0, 6, "shield"); 
    		pieces[2][6] = new Piece(false, this, 2, 6, "shield"); 
    		pieces[4][6] = new Piece(false, this, 4, 6, "shield"); 
    		pieces[6][6] = new Piece(false, this, 6, 6, "shield"); 
    		pieces[1][7] = new Piece(false, this, 1, 7, "pawn"); 
    		pieces[3][7] = new Piece(false, this, 3, 7, "pawn"); 
    		pieces[5][7] = new Piece(false, this, 5, 7, "pawn"); 
    		pieces[7][7] = new Piece(false, this, 7, 7, "pawn"); 
    				
    		
    	}
    }
    
    public Piece pieceAt(int x, int y){
    	if (x < 8 && y < 8 && x >= 0 && y >= 0){
    		if (pieces[x][y]== null){
    			return null; 
    		}
    		else{
    	return pieces[x][y]; 
    		}
    	}
    	return null; 
    }
    
    public boolean canSelect(int x, int y){
    	System.out.println("can select is being executed"); 
    	//If there is a piece at x, y
    	System.out.println(pieceAt(x, y) + "so there is a piece"); 
    	//System.out.println("the piece is a shield " + this.pieceAt(x,y).isShield()); 
    	if (this.pieceAt(x, y) != null){
    		System.out.println(firePieceTurn + " is it the same as " + this.pieceAt(x, y).isFire()); 
    	if (firePieceTurn == this.pieceAt(x, y).isFire()){
    		if (selectedpiece == null || (selectedpiece != null && !hasMoved)){
    			System.out.println("it can infact be selected"); 
    			return true; 
    		}
    	}
    	}
    	//If there is not a piece at x, y
    	else if (this.pieceAt(x, y) == null){
    		System.out.println("there is no piece in this position"); 
    		//System.out.println(selectedpiece + " " + validMove(x, y) + " " + x + " " + y); 
    		if (selectedpiece != null && !hasMoved && (validMove(x, y) || validCapture(x, y))){
    			System.out.println("is it a valid capture move? yessssss"); 
    		return true; 
    		}
    		if(selectedpiece != null && selectedpiece.hasCaptured() && validCapture(x, y)){
    			return true; 
    		}
    		
    	
    		//System.out.println(selectedpiece + " " + selectedpiece.hasCaptured());
    		else if (selectedpiece != null){
    			System.out.println(selectedpiece + " " + selectedpiece.hasCaptured());
    		if (selectedpiece != null && selectedpiece.hasCaptured() && validCapture(x, y)){
    			System.out.println("recapturing"); 
    			return true; 
    			//you should only select an active piece once all other selections should be valid destination points
    			
    		}
    		}
    	}
    	System.out.println("it can't be selected"); 
    	return false; 
    }
    	
    

    private boolean validMove(int x, int y){
    	//Lets talk about fire pieces first 
    	System.out.println("is it valid move"); 
    	
    	if (selectedpiece.isFire()) {
    		System.out.println("is fire piece"); 
    		//move into empty position 
    		if (Math.abs(ySelected -y) ==1 && y-1 == ySelected && this.pieceAt(x, y) == null){
    			return true; 
    		}
    		
    		//King into empty position 
    		else if(this.pieceAt(xSelected, ySelected).isKing() && Math.abs(x -xSelected)==1 && Math.abs(y -ySelected) == 1 && this.pieceAt(x, y)== null){
    			return true; 
    		}
    		System.out.println("fire piece can't move anywhere"); 
    		return false; 
    	}
    	
    		// King capturing 
    		
    	
    	//Now water pieces (!selectedpiece.isFire())
    	else {
    		//move water into empty position 
    		System.out.println("xSelected ySelected x y"); 
    		System.out.println(xSelected + " " + ySelected + " " + x + " "+ y + this.pieceAt(x,y)); 
    		if (Math.abs(xSelected - x)==1 && y + 1 == ySelected && this.pieceAt(x, y) == null){
    			System.out.println("this is true"); 
    			return true; 
    		}
    		//capturing move 
    		//top left is fire           //top right is fire 
    		
    	
    		//water King into empty 
    		else if(this.pieceAt(xSelected, ySelected).isKing() && Math.abs(x -xSelected)==1 && Math.abs(y -ySelected) == 1 && this.pieceAt(x, y)== null){
    			return true;} 
    		//water king capturing 
    		
    	}
    	
    	return false; 
    	}
    
    //help from George He
    private  boolean validCapture(int x, int y){
    	System.out.println("reached the fxn valid capture"); 
    	if (!correctDirection(x, y)){
    		System.out.println("not moving in correct direction");
    	return false; 
    	}
    	else if(xSelected == x || y == ySelected){
    		return false; 
    	}
    	//System.out.println("piece should be null: " + this.pieceAt(x, y) == null +" " + xSelected + " " + ySelected + " "+ x + " " + y); 
    	else if(this.pieceAt(x, y) == null && Math.abs(xSelected - x)==2 && Math.abs(ySelected - y ) ==2){
    		
    		Piece centerPiece = centerPiece(xSelected, ySelected, x, y); 
    		if (centerPiece != null){
    		if (centerPiece.isFire() != selectedpiece.isFire()){
    			return true; 
    		
    		}
    		}
    	}
    	else{
    		System.out.println(selectedpiece.hasCaptured() + "xselected " +  xSelected + " x " + x + " y selected " + ySelected + "y"); 
    		 if (selectedpiece.hasCaptured() && this.pieceAt(x, y)== null && Math.abs(xSelected - x) == 4 && Math.abs(ySelected - y) ==4){
    			System.out.println("double capture"); 
    		
    		Piece centerPiece2 = centerPiece(xSelected, ySelected, x, y); 
    		if (centerPiece2 != null){
    		if (centerPiece2.isFire() != selectedpiece.isFire()){
    			return true; 
    	}
    		}
    		
    	}
    	}
    	System.out.println("valid capture returns false"); 
    	return false; 
    }
    //help from George He 
    private boolean correctDirection(int x,  int y){
    	System.out.println("correct direction is being executed"); 
    	System.out.println("xSelected" + xSelected + "ySelected" + ySelected); 
    	//if (this.pieceAt(xSelected, ySelected)!=null){
    		//System.out.println("pieceAt(xSelected, ySelected) is false"); 
    	if (selectedpiece.isKing()){
    		return true; 
    	}
    	else if (selectedpiece.isFire()){
    		if (y > ySelected) return true; 
    			
    	}
    	else if (!selectedpiece.isFire()){
    	
    		if (y < ySelected) {
    			System.out.println("water is moving in right direction"); 
    			return true; 
    		}
    		}
    			
    	//}
    	
    	
		return false; 
    }
    //Help from George He
    private Piece centerPiece(int xinitial, int yinitial, int xne, int yne){
    	int centerX = (xinitial + xne)/ 2; 
    	int centerY = (yinitial + yne)/2; 
    	return this.pieceAt(centerX, centerY); 
    }
    	
    
    public void select(int x, int y ){
    	//for any piece to perform a capture that piece must have been selected first 
    	 System.out.println("the coordinates " + x + " " + y + "have been selected."); 
  
    	 StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	 StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    	 if (this.pieceAt(x, y) != null ){
    		 selectedpiece = this.pieceAt(x, y); 
    		 xSelected = x; 
    		 ySelected = y; 
    	 }
    	 if (this.pieceAt(x, y) == null ){
    		 if (selectedpiece !=null){
    			
    		this.pieceAt(xSelected, ySelected).move(x, y); 
    		xSelected = x; 
    		ySelected = y; 
    		hasMoved = true; 
    		 }
    	 }
    	
    }
    public void place(Piece p, int x, int y){
    	//out of bounds
    	if (x >= 8 || y >= 8 || x < 0 || y < 0){
            return; 
        }
        if (this.pieceAt(x, y) != null){
            
            pieces[x][y] = p; 
           // selectedpiece = null; 
            
            
            
        }
        else if (this.pieceAt(x, y) == null){
          
            pieces[x][y] = p; 
            //selectedpiece = null; 
            
        }
    	
    }
    private boolean isNull(int x, int y){
    	if (this.pieceAt(x, y) == null){ 
    		return true; 
    	}
    	return false; 
    	}
    	

    public Piece remove(int x, int y){
    	Piece PieceOutput = this.pieceAt(x, y); 
        //if out of bounds
        if (x >= 8 || y >= 8 || x < 0 || y < 0){
            System.out.println("given coordinates out of bounds, please try again!"); 
            return null; 
        }
            // no piece 
        if(this.pieceAt(x, y) == null){
        	System.out.println("x is " + x + "y is " + y); 
        	System.out.println(this.pieceAt(x, y));
            System.out.println("No piece in given position, please try again!"); 
            return null; 

        }
        else {
        	
        	pieces[x][y] = null; 
          
        }
        return PieceOutput; 
    }
    public  boolean canEndTurn() {
    	                 
        if(hasMoved){
            return true; 
        }
        return false; 

    }
    public   void endTurn(){
    	System.out.println("ending turn"); 
    	if(firePieceTurn && !hasCaptured){
    		System.out.println("no longer firepiceturn"); 
    		firePieceTurn = false; 
    		
    	}
    	else if(!firePieceTurn&& !hasCaptured){
    		System.out.println("turn is being changed"); 
    		firePieceTurn = true; 
    	}
    	if (this.pieceAt(xSelected, ySelected)!= null){
    	this.pieceAt(xSelected, ySelected).doneCapturing(); // called at the end of each turn on the piece that has moved 
    	}
    	hasCaptured = false;
    	hasMoved = false; 
    	selectedpiece = null; 
    	
    }
    public String winner() {
    	boolean firepiece = false; 
    	boolean waterpiece = false; 
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (!isNull(i, j)){
            	if (this.pieceAt(i, j).isFire()){
            		firepiece = true; 
            	}
            	if (this.pieceAt(i, j)!= null && !this.pieceAt(i, j).isFire()){
            		waterpiece = true; 
            	}
            	}
            }
    	}
    	if (firepiece && !waterpiece)
    	{
    		return "Fire"; }
    	if (waterpiece && !firepiece){
    		return "water";}
    	if(!waterpiece && !firepiece){
    		return "No one"; 
    	}
   	return null; 
    	}
    	
    


    public static void main(String[] args) {
    	b = new Board(false); 
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
    

        

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                System.out.println("Mouse was clicked"); 
                if(b.canSelect((int) x, (int) y)){
                	System.out.println("x is " + x + " y is " + y); 
                	b.select((int) x,(int)y); 
                }
            }
                if (StdDrawPlus.isSpacePressed()){
             
            	 System.out.println("space was pressed"); 
            	 //SPACE BAR IS NOT BEING PRESSED 
                	if (b.canEndTurn() ){
                		System.out.println("can end turn"); 
                	b.endTurn(); 
                	System.out.println("ended turn"); 
                	}
                }
                if(b.winner()!=null){
                	System.out.println(b.winner()); 
                	break; 
                }
                StdDrawPlus.show(100);
            }            
     
            
        }
    }
    
