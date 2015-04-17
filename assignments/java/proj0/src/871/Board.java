/**
 * @author Adrian Jimenez
 */

public class Board {
public static boolean shouldBeEmpty, hasMoved, selected, playerTurn=true, endGame;
public static boolean[][] hasPieces = new boolean[8][8];
public static Piece[][] Pieces = new Piece[8][8]; 
public static int fire, water;
public static Piece a;

	/** Constructor for a Board.
	 *  Initializes an empty Board, if shouldBeEmpty is true.
	 *  Initializes a Board with the default configuration, if false.
	 */
	public Board(boolean startShouldBeEmpty) {
		shouldBeEmpty = startShouldBeEmpty;
		if (!shouldBeEmpty) 
        	startingPieces();
        //boardCreator();
	}

	/** Starts a new game of Checkers61b in GUI mode, 
	  * and DOESN'T return until game is over */
	public static void main(String args[]) {
		Board B = new Board(false);
		// Board B = new Board(true); 
		B.boardCreator();
		System.out.println("The winner is" + B.winner());
	}

	private void boardCreator(){
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);		
		drawBoard(8);
		startingPieces();
		/* Tracks mouse */
		while(!endGame) {
           	if (StdDrawPlus.mousePressed()) {
           		drawBoard(8);
               	double x = StdDrawPlus.mouseX();
               	double y = StdDrawPlus.mouseY();
               	//StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
               	if (canSelect((int) x, (int) y)){
               		select((int) x, (int) y);
               		StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
               	}

           	}            
           	StdDrawPlus.show(100);
           	endturn();
       	}
	}

	private void startingPieces() {
		for (int x =0; x<8; x+=2) {
       		Pieces[x][0] = new Piece(true, this, x, 0, "Pawn");
       		Pieces[x+1][1] = new Piece(true, this, x+1, 1, "Shield");
       		Pieces[x][2] = new Piece(true, this, x, 2, "Bomb"); 
			Pieces[x+1][7] = new Piece(false, this, x+1, 7, "Pawn"); 
			Pieces[x][6] = new Piece(false, this, x, 6, "Shield");
			Pieces[x+1][5] = new Piece(false, this, x+1, 5, "Bomb");
       	}
       	fire = 12;
       	water = 12;
	}

	/* Draws an 8x8 game board. */
    private static void drawBoard(int N) {
        for (int i = 0; i < N; i+=1) {
            for (int j = 0; j < N; j+=1) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
            }
        }
        drawPieces(N);
	}

	/* Draws the pieces. */
	public static void drawPieces(int N) {
       	for (int x=0; x<N; x+=1){
       		for (int y=0; y<N; y+=1){
       			if (Pieces[x][y] != null){
       				StdDrawPlus.picture(x+.5, y+.5, Pieces[x][y].convertType(), 1.0, 1.0);
       			}
       		}
       	}
	}

	/** Gets the piece at position (x,y) on the board, 
	 *  or null if there is no piece. If (x,y) out of bounds, returns null.*/
	public static Piece pieceAt(int x, int y) {
		if (x>=8 || y>=8 || x<0 || y<0) 
			return null;
		else if (hasPieces[x][y])
			return Pieces[x][y];
		return null;
	}

	/** Returns true if there is a piece at the piece (x,y) 
	 *  and it can be selected. */
	public static boolean canSelect(int x, int y) {
		if ((!hasMoved && (hasPieces[x][y] && Pieces[x][y].isFire==playerTurn))){ 
				System.out.println("canSelect");
				return true;	}		
		if (!hasPieces[x][y] && (selected && (!hasMoved && Pieces[a.x][a.y].validMove(x, y)))) {
				System.out.println("canSelect");
				return true;}
		if (!hasPieces[x][y] && (selected && Pieces[a.x][a.y].hasCaptured && Pieces[a.x][a.y].validMove(x,y) && 
				(hasPieces[(a.x+x)/2][(a.y+y)/2] && Pieces[(a.x+x)/2][(a.y+y)/2].isFire!=playerTurn))) {
				System.out.println("canSelect");
				return true;
		}
		System.out.println("cannotSelect");
		return false;
	}

	/* Selects the piece at (x, y) if possible. */ 
	public static void select(int x, int y) {
		if (canSelect(x, y)) {
			if (hasPieces[x][y]) {
				selected=true;
			    a=Pieces[x][y];}
		    else {
		    	a.move(x, y);
		        selected=false;}
		}
	}

	/** Places the piece on the selected position. */
	public void place(Piece p, int x, int y) {
		if (x<0 || y<0 || x>7 || y>7 || p==null)
			return;
		hasPieces[p.x][p.y]=false;
		Pieces[p.x][p.y]=null;
		hasPieces[x][y]=true;
		p.x=x;
		p.y=y;
		Pieces[x][y]=p;
	}

	/*Executes a remove.*/
	public Piece remove(int x, int y){
		if (x<0 || y<0 || x>7 || y>7){
			String s1 = "(x, y) out of bounds at (";
			s1 = (new StringBuilder()).append(s1).append(x).append(", ").append(y).append(")!").toString();
			System.out.println(s1);
			return null; 
		}
		Piece piece = Pieces[x][y];
		if (piece==null){
			String s2 = "No piece to be removed at (";
			s2 = (new StringBuilder()).append(s2).append(x).append(", ").append(y).append(")!").toString();
			System.out.println(s2);
			return null;
		}
		else{
			Pieces[x][y]=null;
			return piece;
		}
	}

	/** Returns whether or not the current player can end their turn. */
	public static boolean canEndTurn() {
		if (hasMoved){
			return true;
		}
		return false;
	}

	/** Switches the players at the end of a turn. */
	public static void endturn() {
		if (canEndTurn() && StdDrawPlus.isSpacePressed()) {
			playerTurn = !playerTurn;
			hasMoved=false;
			for (int i = 0; i <8; i+=1) {
            	for (int j = 0; j < 8; j+=1) {
            		if (pieceAt(i, j) != null){
            			Pieces[i][j].hasCaptured = false;
            		}
            	}	
			}
		}
	}

	/** Returns the winner of the game. "Fire", "Water", 
	 *  "No one" (tie/no piecs on the board), or null */
	public String winner() {
		if (fire==0){
			return "Water";
		}
		if (water==0){
			return "Fire";
		}
		if (fire==0 && water==0){
			return "No One";
		}
		if (!endGame){
			return null;
		}
		return "";
	}
}
