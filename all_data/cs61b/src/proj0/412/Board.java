/**
 *   @author Daniel McNeela
 */

public class Board {
    private static int board_dimension = 8;
    private Piece[][] collection; 
    private boolean isFireTurn;
    private boolean hasMoved;
    private boolean hasSelected;
    private Piece selectedPiece;
    private int[] piecePosition;

    public Board(boolean shouldBeEmpty) {
	collection = new Piece[board_dimension][board_dimension];
	isFireTurn = true;
	hasMoved = false;
	hasSelected = false;
	piecePosition = new int[2];
	piecePosition[0] = -1;
	piecePosition[1] = -1;
	
	if (shouldBeEmpty) { 
	    for (int i = 0; i < board_dimension; i++) {
		for (int j = 0; j < board_dimension; j++) {
		    collection[i][j] = null;
		}
	    }
	}
	else {
	    for (int i = 0; i < board_dimension; i++) {
		for (int j = 0; j < board_dimension; j++) {
		    if (j == 0 && (i + j)%2 == 0) {
			collection[i][j] = new Piece(true, this, i, j, "pawn");
			this.place(collection[i][j], i, j);
			
		    }
		    if (j == 1 && (i + j)%2 == 0) {
			collection[i][j] = new Piece(true, this, i, j, "shield");
			this.place(collection[i][j], i, j);
			
		    }
		    if (j == 2 && (i + j)%2 == 0) {
			collection[i][j] = new Piece(true, this, i, j, "bomb");
			this.place(collection[i][j], i, j);
			
		    }
		    if (j == board_dimension-1 && (i + j)%2 != 0) {
			collection[i][j] = new Piece(false, this, i, j, "pawn");
			this.place(collection[i][j], i, j);
			
		    }
		    if (j == board_dimension -2 && (i + j)%2 != 0) {
			collection[i][j] = new Piece(false, this, i, j, "shield");
			this.place(collection[i][j], i, j);
			
		    }
		    if (j == board_dimension - 3 && (i + j)%2 != 0) {
			collection[i][j] = new Piece(false, this, i, j, "bomb");
			this.place(collection[i][j], i, j);
		    }
		}
	    }
	}
    }

    private void draw(Piece p, int x, int y) {
	if (p.isKing()) {
	    if (p.isFire()) {
		if (p.isShield()) {
		    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
		}
		else if (p.isBomb()) {
		    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
		}
		else {
		    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
		}
	    }
	    else {
		if (p.isShield()) {
		    StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
		}
		else if (p.isBomb()) {
		    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
		}
		else {
		    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
		}
	    }
	}
	else {
	    if (p.isFire()) {
		if (p.isShield()) {
		    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
		}
		else if (p.isBomb()) {
		    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
		}
		else {
		    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
		}
	    }
	    else {
		if (p.isShield()) {
		    StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
		}
		else if (p.isBomb()) {
		    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
		}
		else {
		    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
		}
	    }
	}
    }
    
    private void drawBoardEmpty() {
	for (double x = 0; x < board_dimension; x++) {
	    for (double y = 0; y < board_dimension; y++) {
		if ((x + y)%2 == 0) {
		    StdDrawPlus.picture(x+.5, y+.5, "img/josh2.jpg", 1, 1);
		}
		else {
		    StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
		    StdDrawPlus.filledSquare(x+.5, y+.5, .5);
		}
	    }
	}
    }

    private void drawBoard() {
	for (int x = 0; x < board_dimension; x++) {
	    for (int y = 0; y < board_dimension; y++) {
		if ((x + y)%2 == 0) {
		    StdDrawPlus.picture(x+.5, y+.5, "img/josh2.jpg", 1, 1);
		    if (collection[x][y] != null) {
			draw(collection[x][y], x, y);
		    }
		}
		else {
		    StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
		    StdDrawPlus.filledSquare(x+.5, y+.5, .5);
		    if (collection[x][y] != null) {
			draw(collection[x][y], x, y);
		    }
		}
	    }
	}
    }
   
    public Piece pieceAt(int x, int y) {
	if (x > board_dimension || y > board_dimension) {
	    return null;
	}
	return collection[x][y];	
    }

    public boolean canSelect(int x, int y) {
	if (pieceAt(x, y) != null) {
	    System.out.println("In Can Select Statement 1");
	    if (hasMoved) {
		System.out.println("In Can Select Statement 2");
		return false;
	    }
	    else if (!hasMoved) {
		System.out.println("In Can Select Statement 3");
		if (pieceAt(x, y).isFire() && isFireTurn) {
		    System.out.println("In Can Select Statement 4");
		    return true;
		    }
		else if (!pieceAt(x, y).isFire() && !isFireTurn) {
		    System.out.println("In Can Select Statement 5");
		    return true;
		}
		else {
		    System.out.println("In Can Select Statement 6");
		    return false;
		}
	    }
	}
	if (pieceAt(x, y) == null) {
	    System.out.println("In Can Select Statement 7");
	    if (hasSelected && selectedPiece == pieceAt(x, y) && !selectedPiece.hasCaptured()) {
		System.out.println("In Can Select Statement 8");
		if (validMove(x, y) || validCapture(x, y)) {
		    System.out.println("In Can Select Statement 9");
		    return true;
		}
	    }
	    else if(hasSelected && selectedPiece == pieceAt(x, y) && selectedPiece.hasCaptured()) {
		System.out.println("In Can Select Statement 10");
		if (validCapture(x, y)) {
		    System.out.println("In Can Select Statement 11");
		    return true;
		}
	    }      
	}
	System.out.println("In Can Select Statement 12");
	return false;
    }
    
    public void select(int x, int y) {
	if (pieceAt(x, y) == null) {
	    System.out.println("In Select Statement 1");
	    selectedPiece.move(x, y);
	    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	    draw(selectedPiece, x, y);
	    return;
	}
	else {
	    selectedPiece = pieceAt(x, y);
	    piecePosition[0] = x;
	    piecePosition[1] = y;
	    System.out.println("In Select Statement 2");
	    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	    draw(selectedPiece, x, y);
	    hasSelected = true;
	    return;
	}
    }

    public void place(Piece p, int x, int y) {
	if (x > board_dimension || y > board_dimension) {
	    return;
	}
	else {
	    collection[x][y] = p;
	}
    }

     public Piece remove(int x, int y) {
	 System.out.println("Remove is being called.");
	 if (x > board_dimension || y > board_dimension) {
	     System.out.println("Square is out of bounds.");
	     return null;
	 }
	 else if (collection[x][y] == null) {
	     System.out.println("This square does not contain a piece.");
	     return null;
	 }
	 else {
	     Piece removedPiece = collection[x][y];
	     collection[x][y] = null;
	     return removedPiece;
	 }
     }

    public boolean canEndTurn() {
	if (hasMoved) {
	    return true;
	}
	return false;
    }

    public void endTurn() {
	for (int i = 0; i < board_dimension; i++) {
	    for (int j = 0; j < board_dimension; j++) {
		if (collection[i][j] != null) {
		    collection[i][j].doneCapturing();
		}
	    }
	}
	if (isFireTurn) {
	    isFireTurn = false;
	    hasMoved = false;
	    selectedPiece = null;
	    hasSelected = false;
	}
	else {
	    isFireTurn = true;
	    hasMoved = false;
	    selectedPiece = null;
	    hasSelected = false;
	}
	return;
    }

    public String winner() {
	return null;
    }

    private boolean validMove(int x, int y) {
	if (!selectedPiece.isKing()) {
	    System.out.println("In Valid Move Statement 1");
	    if (selectedPiece.isFire()) {
		System.out.println("In Valid Move Statement 2");
		if (x == piecePosition[0] + 1 && y == piecePosition[1] + 1) {
		    System.out.println("In Valid Move Statement 3");
		    return true;
		}
		else if (x == piecePosition[0] - 1 && y == piecePosition[1] + 1) {
		    System.out.println("In Valid Move Statement 4");
		    return true;
		}
	    }
	    if (!selectedPiece.isFire()) {
		System.out.println("In Valid Move Statement 5");
		if (x == piecePosition[0] - 1 && y == piecePosition[1] - 1) {
		    System.out.println("In Valid Move Statement 6");
		    return true;
		}
		else if (x == piecePosition[1] + 1 && y == piecePosition[1] - 1) {
		    System.out.println("In Valid Move Statement 7");
		    return true;
		}
	    }
	}
	if (selectedPiece.isKing()) {
	    System.out.println("In Valid Move Statement 8");
	    if (selectedPiece.isFire()) {
		System.out.println("In Valid Move Statement 9");
		if (x == piecePosition[0] + 1 && y == piecePosition[1] - 1) {
		    System.out.println("In Valid Move Statement 10");
		    return true;
		}
		else if (x == piecePosition[0] - 1 && y == piecePosition[1] - 1) {
		    System.out.println("In Valid Move Statement 11");
		    return true;
		}
	    }
	    if (!selectedPiece.isFire()) {
		System.out.println("In Valid Move Statement 12");
		if (x == piecePosition[0] - 1 && y == piecePosition[1] + 1) {
		    System.out.println("In Valid Move Statement 13");
		    return true;
		}
		else if (x == piecePosition[0] + 1 && y == piecePosition[1] + 1) {
		    System.out.println("In Valid Move Statement 14");
		    return true;
		}
	    }
	}
	System.out.println("In Valid Move Statement 15");
	return false;
    }

    private boolean validCapture(int x, int y) {
	if (!selectedPiece.isKing()) {
	    System.out.println("In Valid Capture Statement 1");
	    if (selectedPiece.isFire()) {
		System.out.println("In Valid Capture Statement 2");
		if (x == piecePosition[0] + 2 && y == piecePosition[1] + 2 && pieceAt(x - 1, y - 1) != null && !pieceAt(x - 1, y - 1).isFire()) {
		    System.out.println("In Valid Capture Statement 3");
		    return true;
		}
		if (x == piecePosition[0] - 2 && y == piecePosition[1] + 2 && pieceAt(x + 1, y - 1) != null && !pieceAt(x + 1, y - 1).isFire()) {
		    System.out.println("In Valid Capture Statement 4");
		    return true;
		}
	    }
	    if (!selectedPiece.isFire()) {
		if (x == piecePosition[0] + 2 && y == piecePosition[1] - 2 && pieceAt(x - 1, y + 1) != null && pieceAt(x - 1, y + 1).isFire()) {
		    System.out.println("In Valid Capture Statement 5");
		    return true;
		}
		if (x == piecePosition[0] - 2 && y == piecePosition[1] - 2 && pieceAt(x + 1, y + 1) != null && pieceAt(x + 1, y + 1).isFire()) {
		    System.out.println("In Valid Capture Statement 6");
		    return true;
		}
	    }
	}
	if (selectedPiece.isKing()) {
	    System.out.println("In Valid Capture Statement 7");
	    if (selectedPiece.isFire()) {
		System.out.println("In Valid Capture Statement 8");
		if (x == piecePosition[0] + 2 && y == piecePosition[1] - 2 && pieceAt(x - 1, y + 1) != null && !pieceAt(x - 1, y + 1).isFire()) {
		    System.out.println("In Valid Capture Statement 9");
		    return true;
		}
		if (x == piecePosition[0] - 2 && y == piecePosition[1] - 2 && pieceAt(x + 1, y + 1) != null && !pieceAt(x + 1, y + 1).isFire()) {
		    System.out.println("In Valid Capture Statement 10");
		    return true;
		}
	    }
	    if (!selectedPiece.isFire()) {
		System.out.println("In Valid Capture Statement 11");
		if (x == piecePosition[0] + 2 && y == piecePosition[1] + 2 && pieceAt(x - 1, y - 1) != null && pieceAt(x - 1, y - 1).isFire()) {
		    System.out.println("In Valid Capture Statement 12");
		    return true;
		}
		if (x == piecePosition[0] - 2 && y == piecePosition[1] + 2 && pieceAt(x + 1, y - 1) != null && pieceAt(x + 1, y - 1).isFire()) {
		    System.out.println("In Valid Capture Statement 13");
		    return true;
		}
	    }
	}
	System.out.println("In Valid Capture Statement 14");
	return false;
    }
    
    public static void main(String args[]) {
    	StdDrawPlus.setXscale(0, board_dimension);
	StdDrawPlus.setYscale(0, board_dimension);
	Board initialGameBoard = new Board(false);
	while(initialGameBoard.winner() == null) {
	    initialGameBoard.drawBoard();
	    if (StdDrawPlus.mousePressed() == true) {
		System.out.println("In GUI Statement 1");
		double x_click = StdDrawPlus.mouseX();
		double y_click = StdDrawPlus.mouseY();
		System.out.println(x_click + " " +  y_click);
		if (initialGameBoard.canSelect((int)x_click, (int)y_click)) {
		    System.out.println("In GUI Statement 2");
		    initialGameBoard.select((int)x_click, (int)y_click);
		}
	    }
	    if (StdDrawPlus.isSpacePressed()) {
		if (initialGameBoard.canEndTurn()) {
		    initialGameBoard.endTurn();
		}
	    }
	    StdDrawPlus.show(10);
	}
	return;
    }
}
