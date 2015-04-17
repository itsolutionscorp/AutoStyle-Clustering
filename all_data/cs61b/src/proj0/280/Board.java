
public class Board{
//	variables
	private Piece[][] pieces;
	private boolean notDraw;
	private int turn = 0;
	private Piece selected;
	private int selectedX;
	private int selectedY;
	private boolean hasMoved = false;

//	board constructor
	public Board (boolean ShouldBeEmpty){		
		notDraw = ShouldBeEmpty;
		pieces = new Piece[8][8];	
		if (!notDraw){
			fillPieces(this, 8);
		}
	}
	
//	draw board
	private void draw(int N) {
		if (true){
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i + j) % 2 == 0){
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}else{
						StdDrawPlus.setPenColor(StdDrawPlus.RED);					
					}
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		            if (true){
		            	if (pieces[i][j] != null){
		            		if(selected != null && pieces[i][j] == selected){
		    	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		    					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		            		}
		                	String s = imageName(pieces[i][j]);
		                	StdDrawPlus.picture(i+0.5, j+0.5, s, 1, 1);
		                }
					}
				}			
			}
		}		
	}
	
//	fill the board with starting pieces
	private void fillPieces(Board b, int N){
		if (!notDraw){
			for (int i = 0; i < N/2; i++){
				pieces[2*i][0] = new Piece(true, b, 2*i, 0, "pawn");
				pieces[2*i+1][1] = new Piece(true, b, 2*i+1, 1, "shield");
				pieces[2*i][2] = new Piece(true, b, 2*i, 2, "bomb");
				pieces[2*i+1][5] = new Piece(false, b, 2*i+1, 5, "bomb");
				pieces[2*i][6] = new Piece(false, b, 2*i, 6, "shield");
				pieces[2*i+1][7] = new Piece(false, b, 2*i+1, 7, "pawn");
			}
		}		
	}
	
//	a method to efficiently make a valid image name with directory path
	private String imageName(Piece p){
		String s = "img/";
		if (p.isBomb()){
			s+="bomb";			
		}
		if (p.isShield()){		
			s+= "shield";
		}
		if (!p.isShield() && !p.isBomb()){
			s+= "pawn";
		}
		if (p.isFire()){
			s+= "-fire";	
		}else{
			s+= "-water";
		}
		if (p.isKing()){
			s+= "-crowned.png";			
		}else{
			s+=".png";
		}
		return s;		
	}
	
//	to find the piece at such location/null if no piece found
	public Piece pieceAt(int x, int y){
		if (checkBound(x, y)){
			return pieces[x][y];
		}
		return null;
	}
	
//	a method to check if the location is valid
	private boolean checkBound(int x, int y){
		if (x>=0 && x<8 && y>=0 && y<8)
			return true;
		return false;
	}
	
//	to check if such a spot can be selected
	public boolean canSelect(int x, int y){
		if (!checkBound(x, y)){
			return false;
		}
		Piece p = pieceAt(x, y);
//		if the player selected a spot with a piece
		if (p!= null){
			return p.side() == turn && !hasMoved;
		}else{
			if (selected == null){
				return false;
			}else{
//				player has selected a piece and an empty space and hasn't moved 
				if (!hasMoved){
					return validMove(selectedX, selectedY, x, y);
				}
//				player has captured a piece and want to capture another piece
				if (selected.hasCaptured()){
					return Math.abs(x-selectedX) == 2 && validMove(selectedX, selectedY, x, y);
				}
				return false;
			}
		}
	}

	


	
//	already assume x,y refer to an empty space, check if such a move is valid
	private boolean validMove(int selectedX, int selectedY, int x, int y){
		if (selected.isKing()){
			return (validFireMove(selectedX, selectedY, x, y) || validWaterMove(selectedX, selectedY, x, y));
		}
		if (selected.isFire()){
			return validFireMove(selectedX, selectedY, x, y);
		}
		if (!selected.isFire()){
			return validWaterMove(selectedX, selectedY, x, y);
		}
		return false;
	}
	
//	water moves; fall down
	private boolean validWaterMove(int selectedX, int selectedY, int x, int y){
		if (selectedY-y==1 && Math.abs(selectedX-x)==1){
			return true;
		}
		if (selectedY-y==2){
			if (selectedX-x==2 && pieceAt(selectedX-1, selectedY-1) != null && pieceAt(selectedX-1, selectedY-1).side() != turn){
				return true;
			}
			if (x-selectedX==2 && pieceAt(x-1, selectedY-1) != null && pieceAt(x-1, selectedY-1).side() != turn){
				return true; 
			}
		}
		return false;
	}
	
//	fire moves; rise up
	private boolean validFireMove(int selectedX, int selectedY, int x, int y){
		if (y-selectedY==1 && Math.abs(selectedX-x)==1){
			return true;
		}
		if (y-selectedY==2){
			if (selectedX-x==2 && pieceAt(selectedX-1, y-1) != null && pieceAt(selectedX-1, y-1).side() != turn){
				return true;
			}
			if (x-selectedX==2 && pieceAt(x-1, y-1) != null && pieceAt(x-1, y-1).side() != turn){
				return true;
			}
		}
		return false;
	}
	
//	Assuming canSelect is true, select said piece and perform appropriate actions
	public void select(int x, int y){
		Piece p = pieceAt(x, y);
		if (p == null && selected != null){
			selected.move(x, y);
			this.hasMoved = true;
		}else{
			selected = p;			
		}
		selectedX = x;
		selectedY = y;
	}


//	Called in Piece, to put a piece in destination spot.
	public void place(Piece p, int x, int y){
		if (checkBound(x, y)){
			pieces[x][y] = p;
		}else{
			System.out.println("null piece");
		}
	}
	
//	Remove a piece from the board
	public Piece remove(int x, int y){
		if (!checkBound(x, y)){
			System.out.print("Index out of bounds, can't remove");
			return null;
		}
		Piece toRemove = pieceAt(x, y);
		if (toRemove == null){
			System.out.print("no piece in place, can't remove");
			return null;
		}else{
			pieces[x][y] = null;
			return toRemove;
		}
	}
	
//	check if this player can end his turn
	public boolean canEndTurn(){
		if (this.hasMoved){
			return true;
		}
		return false;
	}
	
//	Assuming canEndTurn, then perform the "resetting" of board variables to end player's turn
	public void endTurn(){
		this.turn = 1 - this.turn;
		selected.doneCapturing();
		selected = null;
		this.hasMoved = false;
	}
	
	
	public String winner(){
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieceAt(i, j)!=null){
					if (pieces[i][j].isFire())
						fireCount += 1;
					else
						waterCount += 1;
				}
			}
		}
		if (waterCount == 0 && fireCount == 0){
			return "No one";
		}
		if(waterCount == 0){
			return "Fire";
		}
		if(fireCount == 0){
			return "Water";
		}else{
			return null;
		}
	}

	
	
	
	
	
	

	public static void main(String[] args){
//		set scale
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);

//		create a board
		Board b = new Board(false);
		
		while (true){
			b.draw(N);
			if (StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (!b.canSelect(x, y)){
					continue;
				}
 				b.select(x, y);
				
			}
			
			if (StdDrawPlus.isSpacePressed()){
				if (b.canEndTurn()){
					b.endTurn();
					System.out.println(b.winner());
				}
			}		
			StdDrawPlus.show(100);
		}
	}
}

