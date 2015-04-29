import org.junit.Test;
import static org.junit.Assert.*;

public class Board{
	
	private boolean empty, hasSelectedP, hasMoved;
	private Piece[][] pieces;
	private int turn, selectedX, selectedY, N;
	private Piece selectedP;
	private String checkForEndGame;

	public Board(boolean shouldBeEmpty){
		empty = shouldBeEmpty;
		N = 8;
        pieces = new Piece[N][N];
        turn=0;
        if(!empty){
        	setPieces();
        }
	}

	private void setPieces(){
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				if(j==0 && (i%2==0)){
					pieces[i][j] = new Piece(true, this, i, j, "pawn");
	            	}
            	if(j==1 && (i%2!=0)){
					pieces[i][j] = new Piece(true, this, i, j, "shield");
            	}
            	if(j==2 && (i%2==0)){
            		pieces[i][j] = new Piece(true, this, i, j, "bomb");
            	}
            	if(j==5 && (i%2!=0)){
            		pieces[i][j] = new Piece(false, this, i, j, "bomb");
            	}
            	if(j==6 && (i%2==0)){
            		pieces[i][j] = new Piece(false, this, i, j, "shield");
            	}
            	if(j==7 && (i%2!=0)){
            		pieces[i][j] = new Piece(false, this, i, j, "pawn");
            	}
			}
		}
	}

	private boolean outOfBounds(int x, int y){
		if(x<0||x>7||y<0||y>7) return true;
		return false;
	}

	private void drawBoard(int N) {
    	for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
            	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	Piece p = pieces[i][j];
            	if(p!=null){
            		String image;
            		if(p.isFire()){
            			if(p.isShield()){
            				if(p.isKing()) image = "img/shield-fire-crowned.png";
            				else image = "img/shield-fire.png";
            			}
  						else if(p.isBomb()){
  							if(p.isKing()) image = "img/bomb-fire-crowned.png";
  							else image = "img/bomb-fire.png";
  						}
  						else{
  							if(p.isKing()) image = "img/pawn-fire-crowned.png";
  							else image = "img/pawn-fire.png";
  						}
  					}
  					else{
  						if(p.isShield()){
            				if(p.isKing()) image = "img/shield-water-crowned.png";
            				else image = "img/shield-water.png";
            			}
  						else if(p.isBomb()){
  							if(p.isKing()) image = "img/bomb-water-crowned.png";
  							else image = "img/bomb-water.png";
  						}
  						else{
  							if(p.isKing()) image = "img/pawn-water-crowned.png";
  							else image = "img/pawn-water.png";
  						}	          				
            		}
            		StdDrawPlus.picture(i + .5, j + .5, 
      						image, 1, 1);
            	}
            }
        }
    }

	public Piece pieceAt(int x, int y){
		if(outOfBounds(x,y)){
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		if(outOfBounds(x,y)){
			return false;
		}
		Piece p = pieces[x][y];
		if(p!=null){
			if(p.side()!=turn){
				return false;
			}
			if(!hasSelectedP || !hasMoved){
				return true;
			}
			return false;
		}
		else{
			if(!hasSelectedP){
				return false;
			}
			boolean canMove = validMove(x, y);
			if(canMove && !hasMoved){
				return true;
			}
			if(selectedP.hasCaptured()&&!selectedP.isBomb()){
				boolean multiCapture = checkCapture(x,y);
				if(multiCapture){
					return true;
				}
			}
			return false;
		}
	}

	private boolean validMove(int xf, int yf){
		int xi = selectedX, yi = selectedY;
		if(outOfBounds(xf, yf) || pieces[xf][yf]!=null){
			return false;
		}
		int xDiff = Math.abs(xi-xf);
		int yDiff;
		if(selectedP.isKing()){
			yDiff = Math.abs(yi-yf);
		}
		else if(selectedP.isFire()){
			yDiff=yf-yi;
		}
		else{
			yDiff = yi-yf;
		}
		return checkMove(xDiff, yDiff, xf, yf);
	}

		
	private boolean checkMove(int xDiff, int yDiff, int xf, int yf){
		if(xDiff==1 && yDiff==1){
			return true;
		}
		else if(xDiff==2 && yDiff==2){
			return checkCapture(xf, yf);
		}
		return false;
	}

	private boolean checkCapture(int x, int y){
		int xm = Math.max(selectedX,x)-1;
		int ym = Math.max(selectedY,y)-1;
		if(outOfBounds(xm, ym)){
			return false;
		}
		Piece captured = pieces[xm][ym];
		return captured!=null && captured.isFire()!=selectedP.isFire();
	}

	public void select(int x, int y){
		Piece temp = pieces[x][y];
		if(temp!=null){
			selectedX = x;
			selectedY = y;
			selectedP = pieces[selectedX][selectedY];
			hasSelectedP = true;
		}
		else if(selectedP!=null){
			selectedP.move(x,y);
			hasMoved = true;
			selectedX = x;
			selectedY = y;
		}
	}

	private void setBackground(int x, int y){
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		StdDrawPlus.show(20);
	}

	public void place(Piece p, int x, int y){
		if(p==null || outOfBounds(x, y)){
			return;
		}
		pieces[x][y]=p;
	}

	public Piece remove(int x, int y){
		if(outOfBounds(x,y)){
			System.out.println("The given space is out of bounds.");
			return null;
		}
		if(pieces[x][y]==null){
			System.out.println("No piece at ("+x+", "+y+") to remove.");
			return null;
		}
		Piece p = pieces[x][y];
		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn(){
		if(hasMoved){
			return true;
		}
		return false;
		
	}

	public void endTurn(){
		turn = 1-turn;
		if(selectedP!=null){
			selectedP.doneCapturing();
		}	
		selectedP = null;
		selectedX = -1;
		selectedY = -1;
		hasSelectedP = false;
		hasMoved = false;

	}

	public String winner(){
		
		int fireCount = 0, waterCount = 0;
		for(Piece[] row : pieces){
			for(Piece p : row){
				if(p!=null){
					if(p.isFire()){
						fireCount+=1;
					}
					else{
						waterCount+=1;
					}
				}
			}
		}
		if(waterCount == 0 && fireCount!=0){
			return "Fire";
		}
		else if (fireCount == 0 && waterCount!=0){
			return "Water";
		}
		else if (fireCount==0 && waterCount==0){
			return "No one";
		}
		return null;
	}

	public static void main(String[] args){
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, b.N);
        StdDrawPlus.setYscale(0, b.N);
        b.checkForEndGame = b.winner();
		while(b.checkForEndGame==null) {
            b.checkForEndGame = b.winner();
            b.drawBoard(b.N);
            if (StdDrawPlus.mousePressed()) {
				int x = (int) (StdDrawPlus.mouseX());
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x, y)){
                	b.select(x,y);
                	b.setBackground(x, y);
                }
            } 
            if(StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            }
            StdDrawPlus.show(10);
        }
        System.out.println("The winner is: "+b.checkForEndGame+"!");
	}

	public static class boardTests{

		@Test
		public void testDefaultSetUp(){
			Board b = new Board(false);
			assertTrue(b.pieceAt(0,0).isFire());
			assertTrue(b.pieceAt(5,5).isBomb());
			assertEquals(false, b.pieceAt(3,7).isKing());
			assertTrue(b.pieceAt(3,1).isShield());
			assertEquals(null, b.pieceAt(2,1));
		}

		@Test
		public void testSelect(){
			Board b = new Board(false);
			assertTrue(b.canSelect(2,2));
			b.select(2,2);
			assertFalse(b.canSelect(7,7));
			assertTrue(b.canSelect(4,2));
			assertTrue(b.canSelect(1,3));
			b.select(1,3);
			assertTrue(b.pieceAt(1,3).isFire());
			assertFalse(b.canSelect(1,3));
			assertFalse(b.canSelect(2,2));
			assertFalse(b.canSelect(4,2));
		}

		@Test
		public void testMultiCapture(){
			Board b = new Board(true);
			Piece p1 = new Piece(true, b, 3, 3, "shield");
			Piece p2 = new Piece(false, b, 4, 4, "pawn");
			Piece p3 = new Piece(false, b, 6, 6, "bomb");
			b.place(p1, 3, 3);
			b.place(p2, 4, 4);
			b.place(p3, 6, 6);
			assertTrue(b.canSelect(3,3));
			b.select(3,3);
			assertEquals(true, b.canSelect(5,5));
			b.select(5,5);
			assertFalse(b.canSelect(7,6));
			assertFalse(b.canSelect(6,6));
			assertTrue(b.canSelect(7,7));
			b.select(7,7);
			assertTrue(b.pieceAt(7,7).isKing());
		}

		@Test
		public void testCanEndTurn(){
			Board b = new Board(true);
			Piece p1 = new Piece(true, b, 3, 3, "shield");
			Piece p2 = new Piece(false, b, 4, 4, "pawn");
			Piece p3 = new Piece(false, b, 7, 7, "bomb");
			b.place(p1, 3, 3);
			b.place(p2, 4, 4);
			b.place(p3, 7, 7);
			assertFalse(b.canEndTurn());
			assertTrue(b.canSelect(3,3));
			b.select(3,3);
			assertFalse(b.canSelect(4,4));
			assertTrue(b.canSelect(5,5));
			b.select(5,5);
			assertTrue(b.canEndTurn());
			b.endTurn();
			assertFalse(b.canSelect(5,5));
			assertTrue(b.canSelect(7,7));
		}

		@Test
		public void testWinner(){
			Board b = new Board(true);
			Piece p1 = new Piece(true, b, 3, 3, "shield");
			Piece p2 = new Piece(false, b, 4, 4, "pawn");
			Piece p3 = new Piece(false, b, 6, 6, "bomb");
			b.place(p1, 3, 3);
			b.place(p2, 4, 4);
			assertEquals(null, b.winner());
			b.remove(4,4);
			assertEquals("Fire", b.winner());
		}

		@Test
		public void reselecting(){
			Board b = new Board(true);
			Piece p1 = new Piece(true, b, 3, 3, "shield");
			Piece p2 = new Piece(false, b, 4, 4, "pawn");
			Piece p3 = new Piece(false, b, 6, 6, "bomb");
			b.place(p1, 3, 3);
			b.place(p2, 4, 4);
			assertTrue(b.canSelect(3,3));
			b.select(3,3);
			assertTrue(b.canSelect(3,3));
		}

		@Test
		public void testFailedTestSolution(){
			Board b = new Board(true);
			Piece p1 = new Piece(false, b, 6, 6, "shield");
			Piece p2 = new Piece(false, b, 4, 4, "shield");
			Piece p3 = new Piece(true, b, 3, 3, "bomb");
			Piece p4 = new Piece(true, b, 4, 6, "bomb");
			b.place(p1, 6, 6);
			b.place(p2, 4, 4);
			b.place(p3, 3, 3);
			b.place(p4, 4, 6);
			b.select(3,3);
			b.select(5,5);
			assertNull(b.pieceAt(4,4));
			assertNull(b.pieceAt(4,6));
			assertTrue(b.pieceAt(6,6).isShield());
			assertFalse(b.canSelect(5,5));
			assertFalse(b.canSelect(7,7));
			

		}

		public static void runTests() {
            jh61b.junit.textui.runClasses(boardTests.class);
        }
	}
}