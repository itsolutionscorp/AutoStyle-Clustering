public class Board{

	private Piece[][] pieces;
	private static int N = 8;
    //private Piece previousPiece;
    private boolean hasMoved;
    private int previousPieceX = 8, previousPieceY = 8;
    private int side = 0;


	public static void main(String[] args){
		Board b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        b.drawBoard(N);
        //b.drawDefault(N);
        while (b.winner() == null) {
        	if (StdDrawPlus.mousePressed()) {
        		if (b.canSelect((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY())) {
        			b.select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
        		}
        	}
        	if (b.canEndTurn() && StdDrawPlus.isSpacePressed()) {
        		b.endTurn();
        	}
        	b.drawBoard(N);
        	StdDrawPlus.show(50);

        }

	}
	private boolean hasMoved() {
		return hasMoved;
	}



	// private boolean wasSelected() {
	// 	return wasSelected;
	// }

	private boolean yourTurn(Piece p) {
		return p.side() == side;
	}

	public Board(boolean shouldBeEmpty){
		/*if shouldBeEmpty true,
		* then initializes empty board
		* otherwise initialize default board*/
		pieces = new Piece[N][N];
		if (!shouldBeEmpty){
			for (int i = 0; i < 1; i++) {
            	for (int j = 0; j < N; j++){
            		if ((i+j) % 2 == 0){
            			pieces[j][i] = new Piece(true, this, j, i, "pawn");
            		}
            	}
            }
            for (int i = 1; i < 2; i++) {
            	for (int j = 0; j < N; j++){
            		if ((i+j) % 2 == 0) {
            			pieces[j][i] = new Piece(true, this, j, i, "shield");
            		}
            	}
            }
            for (int i = 2; i < 3; i++) {
            	for (int j = 0; j < N; j++){
            		if ((i+j) % 2 == 0) {
            			pieces[j][i] = new Piece(true, this, j, i, "bomb");
            		}
            	}
            }
            //default board positioning for WATER pieces
            for (int i = N-1; i < N; i++) {
            	for (int j = 0; j < N; j++){
            		if ((i+j) % 2 == 0){
            			pieces[j][i] = new Piece(false, this, j, i, "pawn");
            		}
            	}
            }
            for (int i = N-2; i < N-1; i++) {
            	for (int j = 0; j < N; j++){
            		if ((i+j) % 2 == 0) {
            			pieces[j][i] = new Piece(false, this, j, i, "shield");
            		}
            	}
            }
            for (int i = N-3; i < N-2; i++) {
            	for (int j = 0; j < N; j++){
            		if ((i+j) % 2 == 0) {
            			pieces[j][i] = new Piece(false, this, j, i, "bomb");
            		}
            	}
            }
		}
	}

	public Piece pieceAt(int x, int y){
		/* Gets piece at x,y
		* null if no piece
		* gets piece at position (x,y)
		* if (x,y) out of bounds, null*/
		if (outOfBounds(x, y)) {
			return null;
		}
		else {
			return pieces[x][y];
		}

	}

	public boolean canSelect(int x, int y){
		//check if your turn?
		/////////////////
		//checks if current piece is selected but not moved OR current piece hasn't been selected yet
		// if (pieceAt(x,y).isSelected && !currentSquare.hasMoved) || (!currentSquare.isSelected) {
		// 	currentPiece = previousPiece; //change currentPiece to currentLocation --> pieceAt these currLocs
		// 	previousPiece.wasSelected = true;
		// 	return true;
		// }
		// //Empty Square
		// if (previousPiece.wasSelected && !previousPiece.hasMoved) {
		// 	return (currentPiece.validMove(previousPieceX, previousPieceY, currentSquareX, currentSquareY));
		// 	//IS THIS RIGHT? x,y is params (piece's coords), cPX, cPY are the new blank space's coords

		// }

		Piece currentPiece = pieceAt(x, y);
		Piece previousPiece = null;
		if (!outOfBounds(previousPieceX, previousPieceY))
			previousPiece = pieceAt(previousPieceX, previousPieceY);
		boolean correctTurn;
		if (currentPiece != null) {
			correctTurn = yourTurn(currentPiece);
		}
		else if (previousPiece != null) {
			correctTurn = yourTurn(previousPiece);
		}
		else {
			correctTurn = false;
		}

		if (correctTurn) {
			if (previousPiece == null && (currentPiece != null)) {
				return !hasMoved;
			}
			if ((currentPiece !=null) && !hasMoved) {
				return true;
			}
			if ((previousPiece != null && !hasMoved) && (validMove(previousPieceX, previousPieceY, x, y) != 2)) {
				return true;
			}
			//CAPTURE if() REMAINING
			if (previousPiece != null && previousPiece.hasCaptured() && validMove(previousPieceX, previousPieceY, x, y) == 1) {
				return true;
			}
		}
		return false;
	}

	private int validMove(int xi, int yi, int xf, int yf){
		/* optional */
		//check if the xf, yf coords are valid, no regard to pieceAt
		Piece initial = pieceAt(xi, yi);
		if (!outOfBounds(xf,yf) && (pieceAt(xf,yf) == null)) { //within board
		//VACANT COORD UP L->R
			// if ( (Math.abs(xf-xi) == 1) && (Math.abs(yf-yi) == 1)
			// 	&& ((initial.side() == side || initial.isKing())))
			// 	return 0;
			if ((xf == xi + 1 && yf == yi + 1)  //checks coords
				&& (initial.isFire() || initial.isKing())) //checks that it is moving in the way it should for fire or water or it's a king
				return 0;

			else if ((xf == xi - 1 && yf == yi - 1)
				&& (!initial.isFire() || initial.isKing()))
				return 0;

			else if ((xf == xi - 1 && yf == yi + 1)
				&& (initial.isFire() || initial.isKing()))
				return 0;

			else if ((xf == xi + 1 && yf == yi - 1)
				&& (!initial.isFire() || initial.isKing()))
				return 0;

			// CAPTURE
			/////////////////// MORE CONCISE
			// else if ((Math.abs(xf-xi) == 2) && (Math.abs(yf-yi) == 2)
			// 	&& (pieceAt((xi+xf)/2, (yi+yf)/2) != null)
			// 	&& (initial.side() == side || initial.isKing())
			// 	&& (pieceAt((xi+xf)/2, (yi+yf)/2).isFire() != initial.isFire()))
			// 		return 1;

			////////////////////

			else if ((xf == xi + 2 && yf == yi + 2) //checks coords
				&& (pieceAt(xi + 1, yi + 1) != null)  //checks that there's a piece to capture, that it's the opposite team
				&& (initial.isFire() || initial.isKing()) //makes sure that the initial piece is moving in the right direction or is king
				&& (pieceAt(xi + 1, yi + 1).isFire() != initial.isFire()))
				return 1;

			else if ((xf == xi - 2 && yf == yi - 2)
				&& (pieceAt(xi - 1, yi - 1) != null)
				&& (!initial.isFire() || initial.isKing())
				&& (pieceAt(xi - 1, yi - 1).isFire() != initial.isFire()))
				return 1;

			else if ((xf == xi - 2 && yf == yi + 2)
				&& (pieceAt(xi - 1, yi + 1) != null)
				&& (initial.isFire() || initial.isKing())
				&& (pieceAt(xi - 1, yi + 1).isFire() != initial.isFire()))
				return 1;

			else if ((xf == xi + 2 && yf == yi - 2)
				&& (pieceAt(xi + 1, yi - 1) != null)
				&& (!initial.isFire() || initial.isKing())
				&& (pieceAt(xi + 1, yi - 1).isFire() != initial.isFire()))
				return 1;
		}

		return 2;

	}

	public void select(int x, int y){
		if (pieceAt(x, y) != null) {
			//previousPiece = pieceAt(x, y);
			previousPieceX = x;
			previousPieceY = y;
			//hasMoved = false;
		}
		if (pieceAt(x, y) == null) {
			pieceAt(previousPieceX, previousPieceY).move(x, y);
			previousPieceX = x;
			previousPieceY = y;
			hasMoved = true;
		}
	}

	public void place(Piece p, int x, int y){
		if (!outOfBounds(x, y)) {
			pieces[x][y] = p;
		}

		/*
		*/
	}

	public Piece remove(int x, int y){
		/* returns piece that was removed
		* if (x,y) out of bounds, returns null &
		* prints approp. msg.
		* same as if null for if no piece
		*/
		if ((outOfBounds(x,y))) {
			System.out.println("Out of bounds.");
			return null;
		}
		else {
			if (pieceAt(x, y) == null) {
				System.out.println("No piece to remove");
				return null;
			}
			else {
				Piece p = pieces[x][y];
				pieces[x][y] = null;
				return p;
			}
		}
	}

	public boolean canEndTurn(){
		return hasMoved;
	}

	public void endTurn(){
		side = 1 - side;
		hasMoved = false;
		//previousPiece = null;
		Piece p = pieceAt(previousPieceX, previousPieceY);
		if (p != null)
			p.doneCapturing();
		previousPieceX = 8;
		previousPieceY = 8;

	}

	public String winner(){
		/* return "Fire", "Water", or "No one"
		* if stalemate, return null
		*/
	int fireCount = 0;
	int waterCount = 0;

		for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++){
        		if (pieceAt(j, i) != null && pieceAt(j, i).isFire()) {
        			fireCount += 1;
        		}
        		else if (pieceAt(j, i) != null){
        			waterCount +=1;
        		}
        	}
        }
        if (fireCount == 0 && waterCount == 0)
        	return "No one";
        else if (waterCount == 0 && fireCount != 0)
        	return "Fire";
        else if (waterCount != 0 && fireCount == 0)
        	return "Water";
        else
        	return null;

	}

	private boolean outOfBounds(int x, int y){
		if ((x >= 0 && x < 8) && (y >= 0 && y < 8)) {
			return false;
		}
		else {
			return true;
		}
	}

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.PINK);
                if (i == previousPieceX && j == previousPieceY) {
                	StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                //if (pieces[i][j]) {
                //    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                //}

                Piece p = pieceAt(i, j);
                if (p != null) {
                	String url = "img/";
                	if (p.isShield()) {
                		url = url + "shield-";
                	}
                	else if (p.isBomb()) {
                		url = url + "bomb-";
                	}
                	else {
                		url = url + "pawn-";
                	}
                	if (p.isFire()) {
                		url = url + "fire";
                	}
                	else {
                		url = url + "water";
                	}
                	if (p.isKing()) {
                		url = url + "-crowned.png";
                	}
                	else {
                		url = url + ".png";
                	}
                	StdDrawPlus.picture(i + .5, j + .5, url, 1, 1);
                }
            }
        }
    }
    // private static void drawDefault(int N) {
    //         //default board positioning for FIRE pieces
    //         for (int i = 0; i < 1; i++) {
    //         	for (int j = 0; j < N; j++){
    //         		if ((i+j) % 2 == 0){
    //         			StdDrawPlus.picture(j + .5, i + .5, "img/pawn-fire.png", 1, 1);
    //         		}
    //         	}
    //         }
    //         for (int i = 1; i < 2; i++) {
    //         	for (int j = 0; j < N; j++){
    //         		if ((i+j) % 2 == 0) {
    //         			StdDrawPlus.picture(j + .5, i + .5, "img/shield-fire.png", 1, 1);
    //         		}
    //         	}
    //         }
    //         for (int i = 2; i < 3; i++) {
    //         	for (int j = 0; j < N; j++){
    //         		if ((i+j) % 2 == 0) {
    //         			StdDrawPlus.picture(j + .5, i + .5, "img/bomb-fire.png", 1, 1);
    //         		}
    //         	}
    //         }
    //         //default board positioning for WATER pieces
    //         for (int i = N-1; i < N; i++) {
    //         	for (int j = 0; j < N; j++){
    //         		if ((i+j) % 2 == 0){
    //         			StdDrawPlus.picture(j + .5, i + .5, "img/pawn-water.png", 1, 1);
    //         		}
    //         	}
    //         }
    //         for (int i = N-2; i < N-1; i++) {
    //         	for (int j = 0; j < N; j++){
    //         		if ((i+j) % 2 == 0) {
    //         			StdDrawPlus.picture(j + .5, i + .5, "img/shield-water.png", 1, 1);
    //         		}
    //         	}
    //         }
    //         for (int i = N-3; i < N-2; i++) {
    //         	for (int j = 0; j < N; j++){
    //         		if ((i+j) % 2 == 0) {
    //         			StdDrawPlus.picture(j + .5, i + .5, "img/bomb-water.png", 1, 1);
    //         		}
    //         	}
    //         }
    // }

}
