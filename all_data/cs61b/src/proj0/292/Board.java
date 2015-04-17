public class Board {
	
	private Piece [][] pieces = new Piece [8][8];

	private boolean selectedPiece = false;
	private boolean hasMoved = false;
	private boolean canEndTurn = false;
	private int turn = 0;						//0 fire turn
	
	private int selectedPieceX = -1;
	private int selectedPieceY = -1;


	public Board(boolean shouldBeEmpty){
		//constructor for Board
		int N = 8;
		for (int i = 0; i < N; i++) {
           	for (int j = 0; j < N; j++){
           		this.pieces[i][j] = null;
           	}
        }
		
        if (shouldBeEmpty == false){			//false shouldBeEmpty
        	int j = 0;							//row 1
        	for (int i = 0; i < N; i++){		//iterate through row
        		if ((i % 2) == 0){				//every other square
        			pieces[i][j] = new Piece (true, this, i, j, "pawn");
        		}
        	}
        	j = 1;							//row 2
        	for (int i = 0; i < N; i++){		//iterate through row
        		if ((i % 2) == 0){				//every other square
        			pieces[i][j] = new Piece (true, this, i, j, "shield");
        		}
        	}
        	j = 2;							//row 3
        	for (int i = 0; i < N; i++){		//iterate through row
        		if ((i % 2) == 0){				//every other square
        			pieces[i][j] = new Piece (true, this, i, j, "bomb");
        		}
        	}
        	j = 5;							//row 6
        	for (int i = 0; i < N; i++){		//iterate through row
        		if ((i % 2) == 0){				//every other square
        			pieces[i][j] = new Piece (true, this, i, j, "bomb");
        		}
        	}
        	j = 6;							//row 7
        	for (int i = 0; i < N; i++){		//iterate through row
        		if ((i % 2) == 0){				//every other square
        			pieces[i][j] = new Piece (true, this, i, j, "shield");
        		}
        	}
        	j = 7;							//row 8
        	for (int i = 0; i < N; i++){		//iterate through row
        		if ((i % 2) == 0){				//every other square
        			pieces[i][j] = new Piece (true, this, i, j, "pawn");
        		}
        	}
        }

	}

	
	public Piece pieceAt(int x, int y){
		//gets piece position on board
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)){
			return null;
		}
		return pieces [x][y];
	}
/*
	public boolean canSelect(int x, int y){
		//returns true if the square (x, y) can be selected
		
	}
*/
	public void select(int x, int y){
		//selects the square at (x, y)
        if ((selectedPiece) && (pieceAt(x,y) == null)){
        	Piece spiece = pieceAt (selectedPieceX, selectedPieceY);
        	
        	spiece.move (x,y);
        	
        	selectedPieceX = x;
            selectedPieceY = y;
           
            
            canEndTurn = true;
            hasMoved = true;
            return;            
        }
        else{
        	selectedPieceX = x;
        	selectedPieceY = y;
            selectedPiece = true;
            return;	  
        }      	
    }

	public void place(Piece p, int x, int y){
		//places p at (x, y)
		if ((p == null) || (x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			return;
		}
		else {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		//executes a remove
		Piece rm = pieces [x][y];
		pieces[x][y] = null;
		return rm;
	}

	public boolean canEndTurn(){
		//returns whether or not current player can end turn
		return this.canEndTurn;


	}

	public void endTurn(){
		//called at end of each turn
			if (canEndTurn()){
			selectedPiece = false;
			canEndTurn = false;
			hasMoved = false;
			Piece spiece = pieceAt(selectedPieceX ,selectedPieceY);
			if (spiece == null){
				turn = 0;
			}
			if (turn == 0){
				turn = 1;
				} 
			else {
				spiece.doneCapturing();
			}
		}
	}

	public String winner(){
		//returns winner of game: fire, water, noone
		int N = 8;
		int nFire = 0;
		int nWater = 0;
		int i = 0;
		while (i < N){
			int j = 0;
			while (j < N){
				Piece p = pieceAt (i,j);
				if (p!= null){
					if (p.isFire()){
						nFire++;
					}
					else{
						nWater++;
					}
				j++;
				}
			i++;
			}
		}
		if ((nFire != 0) && (nWater != 0)){
			return null;
		}
		else if (nFire == 0){
			return "Water";
		}
		else if (nWater == 0){
			return "Fire";
		}
		else {
			return "No one";
		}
	}

	private void drawPiece (int x, int y){
		Piece p = pieceAt(x, y);
		if (p == null){
			if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			return;
		}
		if (p.isFire() && p.isShield()){
			if (!p.isKing()) {
				StdDrawPlus.picture(x + .5,  y + .5, "img/shield-fire.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(x + .5,  y + .5, "img/shield-fire-crowned.png", 1, 1);
			}
		}
		if (p.isFire() && p.isBomb()){
			if (!p.isKing()) {
				StdDrawPlus.picture(x + .5,  y + .5, "img/bomb-fire.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(x + .5,  y + .5, "img/bomb-fire-crowned.png", 1, 1);
			}
		}
		if (p.isFire() && !p.isKing()){
			if (!p.isKing()){
				StdDrawPlus.picture(x + .5,  y + .5, "img/pawn-fire.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(x + .5,  y + .5, "img/pawn-fire-crowned.png", 1, 1);
			}
		}

		if (!p.isFire() && p.isShield()){
			if (!p.isKing()) {
				StdDrawPlus.picture(x + .5,y + .5, "img/shield-water.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(x + .5,  y + .5, "img/shield-water-crowned.png", 1, 1);
			}	
		}
		if (!p.isFire() && p.isBomb()){
			if (!p.isKing()) {
				StdDrawPlus.picture(x + .5,  y + .5, "img/bomb-water.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(x + .5,  y + .5, "img/bomb-water-crowned.png", 1, 1);
			}
		}
		if (!p.isFire() && p.isKing()){
			if (!p.isKing()) {
				StdDrawPlus.picture(x + .5,  y + .5, "img/pawn-water.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(x + .5,  y + .5, "img/pawn-water-crowned.png", 1, 1);
			}
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if(this.pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, this.drawPiece(this.pieces[i][j]), 1, 1);
                }
            }
        }
    }

	public static void main(String args[]){
		//starts GUI version of game
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);

		/** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */

		Board b = new Board(false);

        while(true) {
        	if(StdDrawPlus.mousePressed()){
        		double x = StdDrawPlus.mouseX();
        		double y = StdDrawPlus.mouseY();
        		if(b.canSelect((int) x, (int) y)){
        			b.select((int) x, (int) y);
        		}
        	}
        	if(StdDrawPlus.isSpacePressed() && b.canEndTurn()){
            	b.endTurn();
        	}
            b.drawBoard(N);
            StdDrawPlus.show(25);
            
        }
    }
}