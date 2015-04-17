/*
 *  @author Hongzhe Li
 */
public class Board {
	private Piece[][] board;
	private int FireCount;
	private int WaterCount;
	private boolean finished;
	private boolean FireTurn;
	private boolean moved;
	private boolean selected;
	private int selectedX;
	private int selectedY;

	public Board(boolean shouldBeEmpty) {
		board = new Piece[8][8];//
		FireCount = 0;
		WaterCount = 0;
		finished = false;
		FireTurn = true;
		moved = false;
		selected = false;//
		selectedX = -1;
		selectedY = -1;

		if (!shouldBeEmpty){
		
			place(new Piece(true, this, 0, 0, "pawn"), 0, 0);
			place(new Piece(true, this, 2, 0, "pawn"), 2, 0);
			place(new Piece(true, this, 4, 0, "pawn"), 4, 0);
			place(new Piece(true, this, 6, 0, "pawn"), 6, 0);

			place(new Piece(true, this, 1, 1, "shield"), 1, 1);
			place(new Piece(true, this, 3, 1, "shield"), 3, 1);
			place(new Piece(true, this, 5, 1, "shield"), 5, 1);
			place(new Piece(true, this, 7, 1, "shield"), 7, 1);

			place(new Piece(true, this, 0, 2, "bomb"), 0, 2);
			place(new Piece(true, this, 2, 2, "bomb"), 2, 2);
			place(new Piece(true, this, 4, 2, "bomb"), 4, 2);
			place(new Piece(true, this, 6, 2, "bomb"), 6, 2);

			place(new Piece(false, this, 1, 5, "bomb"), 1, 5);
			place(new Piece(false, this, 3, 5, "bomb"), 3, 5);
			place(new Piece(false, this, 5, 5, "bomb"), 5, 5);
			place(new Piece(false, this, 7, 5, "bomb"), 7, 5);

			place(new Piece(false, this, 0, 6, "shield"), 0, 6);
			place(new Piece(false, this, 2, 6, "shield"), 2, 6);
			place(new Piece(false, this, 4, 6, "shield"), 4, 6);
			place(new Piece(false, this, 6, 6, "shield"), 6, 6);

			place(new Piece(false, this, 1, 7, "pawn"), 1, 7);
			place(new Piece(false, this, 3, 7, "pawn"), 3, 7);
			place(new Piece(false, this, 5, 7, "pawn"), 5, 7);
			place(new Piece(false, this, 7, 7, "pawn"), 7, 7);
			
		}
	}

	public Piece pieceAt(int x, int y) { //get piece by x,y
		if (x >= 0 && y >= 0 && x <= 7 && y <= 7){
			return board[x][y];
		}
		return null;
	}


	public boolean canSelect(int x, int y){
		Piece p = pieceAt(x, y);
		
		if (p != null){
			System.out.println("11");
			if (FireTurn == p.isFire()){
				
				if ((!selected) ||((selected) && (!moved))){
				
				return true;	
				}
			}
			
			return false;
		}else {  //corresponding to empty space
			
			if (pieceAt(selectedX, selectedY) != null){
				
				if (((selected) && (!moved) && validMove(selectedX, selectedY, x, y))
					||((selected) && (pieceAt(selectedX, selectedY).hasCaptured()) && (validMove(selectedX, selectedY, x, y)))) {
					
					return true;
				}
			}
			
		return false;
		}
	}




	public void select(int x, int y){
		Piece p = pieceAt(x, y);
		Piece p1 = pieceAt(selectedX, selectedY);
		//selected a piece
		selected = true;
		if (p != null){
			selectedX = x;
			selectedY = y;
		} else {
			if((p1 != null) && validMove(selectedX, selectedY, x, y)){
				p1.move(x, y);
				selectedX = x;
				selectedY = y;
				moved = true;
			}
		}
	 }

	public void place(Piece p, int x, int y){
		if ((x >= 0 && x <= 7 && y >= 0 && y <= 7) && p != null) {
			board[x][y] = p;
			if (p.isFire()){
				
				FireCount += 1;
			}else{
				
				WaterCount += 1;
			}
		}
	}

	public Piece remove(int x, int y) {

		Piece rm = pieceAt(x, y);
		if (x < 0 || y < 0 || x > 7 || y > 7) {
			System.out.println("out of bounds");
			return null;
		} else if (rm == null){
			System.out.print("no piece at [" + Integer.toString(x) + ", " + Integer.toString(y) + "]"); return null;
		}

		if (rm.isFire()) {
			
			FireCount -= 1;
		} else {
			
			WaterCount -= 1;
		}
		
		board[x][y] = null;
		
		return rm;
	}

	public boolean canEndTurn(){
		Piece p = pieceAt(selectedX, selectedY);
		if (p != null){
			if ((moved == true) || (p.hasCaptured())) return true;
		}
		return false;
	}

 
	public void endTurn() {
		if (canEndTurn()){
			Piece p =pieceAt(selectedX, selectedY);
			FireTurn = !FireTurn;
			moved = false;
			selected = false;
			selectedX = -1;
			selectedY = -1;
			if (p != null) p.doneCapturing();
		}
	}


	private boolean validMove(int xi, int yi, int xf, int yf){
		//wrong index
		if (xi < 0 || yi < 0 || xf < 0 || yf < 0 || xi > 7 || yi > 7 || xf > 7 || yf > 7 ){
			return false;
		}
		Piece p = pieceAt(xi, yi);
		Piece p1 = pieceAt(xf, yf);
		//empty index
		
		//already exists a piece
		
		if (pieceAt(xf, yf) != null) return false;
		//not diagnol
		
		if (Math.abs(xi - xf) != Math.abs(yi - yf)) return false;


		if(((pieceAt(selectedX, selectedY).isFire() && (yf < yi))
	 	  || (!pieceAt(selectedX, selectedY).isFire() && (yi < yf)))

	 	  && (!pieceAt(selectedX,selectedY).isKing()))  return false;


		//wrong direction
		if (yf == Math.abs(xf + 2) && xf == Math.abs(xf + 2)) return false;

		//if there's no piece inbetween, cannot move.
		if (Math.abs(xf-xi) == 2) {
		Piece middlePiece = pieceAt((xf + xi)/2, (yf + yi)/2);
		if (middlePiece == null) {return false;
			}
			if (middlePiece.isFire() == p.isFire()){
				return false;
			}
		}
		
		//eat yourself.
		
		//not able to move 0 or > 1 times
		if (Math.abs(xi-xf) > 2 || Math.abs(yi - yf) > 2 || yi == yf || xi == xf) return false;
		

		return true;


	}

	public String winner() {
		
		if (FireCount + WaterCount == 0) return "No one";

		if ((FireCount == 0)||(WaterCount == 0)){
			System.out.println("finished");
			finished = true;
		}
		
		if (!finished) return null;

		
		if (FireCount > WaterCount) {
			return "Fire";
		} else {
			return "Water";
		}
	}

	private static String getPiece(Piece p) {
		return "img/"+
		(p.isShield() ? "shield" : (p.isBomb()? "bomb" : "pawn"))
		+"-"+
		(p.isFire() ? "fire" : "water")+
		(p.isKing() ? "-crowned" : "")+
		".png";
	}


	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece p = pieceAt(i, j);
                if (p != null) {
                	if (pieceAt(selectedX, selectedY) == p) StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.picture(i + .5, j + .5, getPiece(p), 1, 1);
                    
                }
            }
        }
    }

	public static void main (String args[]){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);

  

        // Monitors for mouse presses. Wherever the mouse is pressed,
          //  a new piece appears.
        while(board.winner() == null) {
        	board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int)x, (int)y)) board.select((int)x, (int)y);
            }         
            if (StdDrawPlus.isSpacePressed()) board.endTurn();
            StdDrawPlus.show(100);
        }
        board.drawBoard(N);
        StdDrawPlus.show(100);
	}   
}