//TODO: Appease the Autograder gods.
import static org.junit.Assert.*;
import org.junit.Test;
public class Board{
	//manages GUI
	private Piece[][] pieceArray = new Piece[8][8];
	private int[] selected = new int[2];
	private int player = 0;
	private Piece selectedPiece;
	private boolean someoneHasMoved = false;
		
		public static void main(String[] args){
			Board b = new Board(false);
			while(true){
				//Draw the board
				b.drawBoard();
				if(StdDrawPlus.mousePressed()){
					int x = (int)StdDrawPlus.mouseX();
		            int y = (int)StdDrawPlus.mouseY();
		            if(b.canSelect(x,y)){
		            	b.select(x,y);
		            }
		        }
		        
		        if(StdDrawPlus.isSpacePressed()){
		        	if(b.canEndTurn()){
		        		b.endTurn();
		        	} 
		        }
		        StdDrawPlus.show(10);
		        if(b.winner() != null) break;
			}
		System.out.println(b.winner());
		}

	public Board(boolean shouldBeEmpty){
		if(shouldBeEmpty){
			// for testing
		}
		else{
			pieceArray[0][0] = new Piece(true, this, 0,0, "pawn");
			pieceArray[2][0] = new Piece(true, this, 2,0, "pawn");
			pieceArray[4][0] = new Piece(true, this, 4,0, "pawn");
			pieceArray[6][0] = new Piece(true, this, 6,0, "pawn");
			pieceArray[1][1] = new Piece(true, this, 1,1, "shield");
			pieceArray[3][1] = new Piece(true, this, 3,1, "shield");
			pieceArray[5][1] = new Piece(true, this, 5,1, "shield");
			pieceArray[7][1] = new Piece(true, this, 7,1, "shield");
			pieceArray[0][2] = new Piece(true, this, 0,2, "bomb");
			pieceArray[2][2] = new Piece(true, this, 2,2, "bomb");
			pieceArray[4][2] = new Piece(true, this, 4,2, "bomb");
			pieceArray[6][2] = new Piece(true, this, 6,2, "bomb");
			pieceArray[1][5] = new Piece(false, this, 1,5, "bomb");
			pieceArray[3][5] = new Piece(false, this, 3,5, "bomb");
			pieceArray[5][5] = new Piece(false, this, 5,5, "bomb");
			pieceArray[7][5] = new Piece(false, this, 7,5, "bomb");
			pieceArray[0][6] = new Piece(false, this, 0,6, "shield");
			pieceArray[2][6] = new Piece(false, this, 2,6, "shield");
			pieceArray[4][6] = new Piece(false, this, 4,6, "shield");
			pieceArray[6][6] = new Piece(false, this, 6,6, "shield");
			pieceArray[1][7] = new Piece(false, this, 1,7, "pawn");
			pieceArray[3][7] = new Piece(false, this, 3,7, "pawn");
			pieceArray[5][7] = new Piece(false, this, 5,7, "pawn");
			pieceArray[7][7] = new Piece(false, this, 7,7, "pawn");
			selected[0] = -1;
			selected[1] = -1;
		}
	}

	public String winner(){
		int fireCounter=0;
		int waterCounter=0;
		for(int ii=0; ii < 8; ii++){
			for(int jj=0; jj<8; jj++){
				Piece toCheck = this.pieceArray[ii][jj];
				if(toCheck!=null){
					if (toCheck.isFire()) fireCounter+=1;
					else waterCounter+=1;
				}
			}

		}
		if(fireCounter==0 && waterCounter == 0) return "No one";
		if(waterCounter==0) return "Fire";
		if(fireCounter==0) return "Water";
		else return null;
	}

	public Piece pieceAt(int x, int y){
		if(x<0 || x>7 || y<0 || y>7) return null;
		return this.pieceArray[x][y];
	}
	
	private boolean validMove(int x, int y, int moveX, int moveY){ // not sure yet
		// System.out.println("Valid move is being called");
		Piece toMove = this.pieceAt(x,y);
		if(this.pieceAt(moveX, moveY) != null) return false;

		if(Math.abs(moveY-y)==1 && Math.abs(moveX-x) == 1){ // 1 away
			if (canEndTurn()) return false; // if any piece has moved disallow single hops
			return (toMove.isKing() || ((moveY > y) == toMove.isFire())); // if move direction is nice
		}

		if (this.someoneHasMoved && (toMove.hasCaptured() == false)) return false; //allow multicaptures

		if(Math.abs(moveY-y)==2 && Math.abs(moveX-x) == 2){// 2 away
			Piece toCapture = this.pieceAt((moveX+x)/2, (moveY+y)/2);
			if (toCapture == null || toCapture.side() == toMove.side()) return false;
			else return (toMove.isKing() || (moveY > y) == toMove.isFire());
		}
		return false;
	}

	public boolean canSelect(int x, int y){
		Piece toSelect = this.pieceAt(x,y);
		if(toSelect == null){
			// System.out.println("Trying to select");
			// System.out.println(this.selectedPiece);
			return this.selectedPiece!=null && this.validMove(selected[0],selected[1],x,y); //if moving some other piece to x,y
		}
		else{
			return (toSelect.side() == this.player && this.someoneHasMoved == false);//if canEndTurn, select only the multicapturer
		}
	}
	
	public void select(int x, int y){
		Piece toSelect = this.pieceAt(x,y);
		if(this.selectedPiece!= null && toSelect == null){ //
			if(this.validMove(this.selected[0], this.selected[1], x, y)){
				this.selected[0] = x;
				this.selected[1] = y;
				// System.out.print("About to move the selectedPiece. It is: ");
				// System.out.println(this.selectedPiece);
				selectedPiece.move(x, y);
				// System.out.print("Just moved the selectedPiece. It is: ");
				// System.out.println(this.selectedPiece);
				this.someoneHasMoved = true;
			}
		} 
		else{
			this.selected[0] = x;
			this.selected[1] = y;
			this.selectedPiece = toSelect;
		}
	}

	public boolean canEndTurn(){
		//if(this.canEnd) return true;
		if (this.someoneHasMoved) return true;
		return false;
	}

	public void endTurn(){
		// System.out.println("ending turn");
		//this.canEnd = false;
	 	this.someoneHasMoved = false;
		this.player = 1 - this.player;
		//System.out.println(this.player);
		if (selectedPiece!= null) selectedPiece.doneCapturing();
		selectedPiece = null;
		selected[0] = -1;
		selected[1] = -1;
	}

	public void place(Piece p, int x, int y){
		this.pieceArray[x][y] = p;
	}

	public Piece remove(int x, int y){
		if (x<0 || y<0 || x>7 || y>7){
			System.out.println("Index out of bounds for remove method");
			return null;
		}
		Piece toRemove = this.pieceArray[x][y];
		if (toRemove == selectedPiece){
			selectedPiece = null;
			selected[0] = -1;
			selected[1] = -1;
		}
		this.pieceArray[x][y] = null;
		// System.out.print("Removed: ");
		// System.out.println(toRemove);
		return toRemove;
	}


	private String pieceType(Piece p){
		if (p.isShield()){
			return "shield";
		}
		if (p.isBomb()){
			return "bomb";
		}
		else return "pawn";
	}

	private String image(Piece p){
		String link = "img/";
		link = link + pieceType(p);
		if(p.isFire()) link += "-fire";
		else link += "-water";
		if(p.isKing()) link += "-crowned";
		link += ".png";
		return link;
	}

	private void drawBoard(){
		StdDrawPlus.setScale(0,8);
		        for (int i = 0; i < 8; i++) {
		            for (int j = 0; j < 8; j++) {
		                if(i == this.selected[0] && j == this.selected[1]){
			                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);	
			            }
		                else{
		                	if ((i + j) % 2 == 0){
		                		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		                	}	
		                	else{
		                		StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
		                	}
		                }                  
		                //Whiten selected squares
		                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		                if (this.pieceArray[i][j] != null){
		                    StdDrawPlus.picture(i + .5, j + .5, this.image(this.pieceArray[i][j]), 1, 1);
		                }
		            }
		        }
		}


	public static class BoardTester {
        @Test
        public void testConstructor(){
        	Board b = new Board(true);
        	assertTrue(b != null);
        }

        @Test
        public void testPlace(){
        	Board b = new Board(true);
        	Piece testPiece = new Piece(true, b, 1, 1, "pawn");
        	b.place(testPiece, 5,5);
        	assertTrue(b.pieceAt(5,5) != null);
        }
        @Test
        public void testPieceAt(){
        	Board b = new Board(true);
        	Piece testPiece = new Piece(true, b, 1, 1, "pawn");
        	b.place(testPiece, 1, 1);
        	assertEquals(null, b.pieceAt(-1,-1));
        	assertEquals(null, b.pieceAt(5,5));
        	assertEquals(testPiece, b.pieceAt(1,1));
        }
        
        @Test
        public void testRemove() {
            Board b = new Board(false);
            b.select(0,0);
            assertEquals(b.pieceAt(0,0), b.remove(0,0));
            assertEquals(null, b.pieceAt(0,0));
            assertEquals(null, b.remove(8,8));
            assertEquals(null, b.selectedPiece);
        }
        @Test
        public void testCanEndTurn(){
        	Board b = new Board(true);
        	Piece test1 = new Piece(true, b, 2,2, "pawn");
        	b.place(test1, 2, 2);
        	b.select(2,2);
        	b.select(3,3);
        	assertEquals(true,b.canEndTurn());
        	b.endTurn();
        	assertEquals(b.canEndTurn(), false);
        }
        @Test
        public void testEndTurn(){
        	Board b = new Board(true);
        	Piece test1 = new Piece(false, b, 2,2, "pawn");
        	b.place(test1, 2, 2);
        	assertFalse(b.canSelect(2,2));
        	b.endTurn();
        	assertTrue(b.canSelect(2,2));
        }

        @Test
        public void testCanSelect(){
        	Board b = new Board(true);
        	Piece test1 = new Piece(true, b, 2,2, "pawn");
        	b.place(test1, 2, 2);
        	Piece test2 = new Piece(true, b, 4,4, "pawn");
        	b.place(test1, 4, 4);
        	assertTrue(b.canSelect(2,2));
        	assertFalse(b.canSelect(3,3));
        	assertTrue(b.canSelect(4,4));
        	b.player = 1;
        	assertFalse(b.canSelect(2,2));
        	b.player = 0;
        	b.someoneHasMoved = true;
        	assertFalse(b.canSelect(2,2));
        	test1 = new Piece(true, b, 2,2, "pawn");
        	test2 = new Piece(false, b, 3,3, "pawn");
        	b = new Board(true);
        	Piece test3 = new Piece(true, b, 6,6, "pawn");
        	Piece test4 = new Piece(true, b, 3,5, "pawn");
        	b.place(test1, 2, 2);
        	b.place(test2, 3, 3);
        	b.place(test3, 6, 6);
        	b.place(test4, 3, 5);
        	b.select(2,2);
        	assertFalse(b.canSelect(1,1));
        	assertTrue(b.canSelect(1, 3));
        	assertFalse(b.canSelect(3, 3));
        	assertTrue(b.canSelect(4, 4));
        	assertFalse(b.canSelect(5, 5));
        	test3.move(7,7);
        	test3.move(6,6);
        	b.select(6,6);        
        	assertEquals(true, b.canSelect(5,5));
        	b.select(2,2);
        	test1.move(4,4);
        	b.place(test1, 4,4);
        	b.select(4,4);
        }

        @Test
        public void testSelect(){
        	Board b = new Board(true);
        	Piece test1 = new Piece(true, b, 2,2, "pawn");
        	b.place(test1, 2, 2);
        	b.select(2,2);
        	assertEquals(b.selectedPiece, test1);
        	assertEquals(b.selected[0], 2);
        	b.select(3,3);
        	assertEquals(b.pieceAt(3,3), test1);
        	//b.drawBoard();
        }
        @Test
        public void testCapture(){
        	Board b = new Board(false);
        	b.remove(0,6);
        	b.pieceAt(1,1).move(2,4);
        	b.select(2,4);
        	b.pieceAt(2,4).move(0,6);
        	b.select(0,6);
        	//b.drawBoard();
        	assertTrue(null != b.pieceAt(0,6));
        	assertEquals(null, b.pieceAt(1,5));
        }

        @Test public void testWinner() {
        	Board b = new Board(true);
        	Piece test1 = new Piece(true, b, 2,2, "pawn");
        	Piece test2 = new Piece(true, b, 3,3, "pawn");
        	Piece test3 = new Piece(false, b, 4,4, "pawn");
        	b.place(test1, 2, 2);
        	b.place(test2, 3, 3);
        	b.place(test3, 4, 4);
        	assertEquals(null, b.winner());
        	b.remove(2,2);
        	assertEquals(null, b.winner());
        	b.remove(3,3);
        	assertEquals("Water", b.winner());
        	b.remove(4,4);
        	assertEquals("No one", b.winner());
        }
       	public static void runTests() {
            jh61b.junit.textui.runClasses(BoardTester.class);
        }
    }
}