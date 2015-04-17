/**
* @author Anthony Dela Paz
*/

public class Board{
	private boolean isEmpty;
	private static final int N = 8;
	private Piece[][] pieces;
	private int turn = 0;
	private Piece selected;
	private int selectedX, selectedY;
	private boolean hasMoved = false;
	private int fires, waters;

	public Board(boolean shouldBeEmpty){
		isEmpty = shouldBeEmpty;
		pieces = new Piece[N][N];
		if(!isEmpty){
			for(int i = 0; i < N; i+=2){
				pieces[i][0] =new Piece(true, this, i, 0, "pawn");
				pieces[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
				pieces[i][2] = new Piece(true, this, i, 2, "bomb");
				fires += 3;
				pieces[i + 1][5] = new Piece(false, this, i + 1, 5, "bomb");
				pieces[i][6] = new Piece(false, this, i, 6, "shield");
				pieces[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
				waters += 3;
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if(x >= N || y >= N || x < 0 || y < 0){
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		Piece p = pieces[x][y];
		if((x + y) % 2 != 0){
			return false;
		}
		if(p == null){ //canSelect on empty space
			return (selected != null && //Checks if piece has been selected
				   	!hasMoved && //checks if piece has not moved
				   	validMove(selectedX, selectedY, x, y))|| //checks if empty space is valid
					(selected != null && // checks if piece has been selected
					 hasMoved && //checks if piece has moved
					 selected.hasCaptured() && //checks if piece has captured
					 validMove(selectedX, selectedY, x, y)); // checks if empty space is valid
		}
		else{ //canSelect piece
			return p.side() == turn && //checks if it is piece's turn
				   (selected == null || !hasMoved); //checks if there is not a piece
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		Piece p = pieces[xi][yi];
		if(p == null || pieceAt(xf, yf) != null){
			return false;
		}
		if(isAdjacent(p, xi, yi, xf, yf) && !selected.hasCaptured()){
			return pieces[xf][yf] == null;
		}
		else{
			return isCaptureSpace(p, xi, yi, xf, yf);
		}
	}

	private boolean isAdjacent(Piece p, int xi, int yi, int xf, int yf){
		if(p.isKing()){
			return (xf == xi + 1 || xf == xi - 1) &&
					(yf == yi + 1 || yf == yi - 1);
		}
		else if(p.side() == 0){
			return yf == yi + 1 &&
					(xf == xi + 1 || xf == xi - 1);
		}
		else{
			return yf == yi - 1 &&
					(xf == xi + 1 || xf == xi - 1);
		}
	}

	private boolean isCaptureSpace(Piece p, int xi, int yi, int xf, int yf){
		int xDif = xf - xi;
		int yDif = yf - yi;
		double xMid = xi + (xDif/2);
		double yMid = yi + (yDif/2);
		if(xMid < 0 || yMid < 0){
			return false;
		}
		if(p.isKing()){
			return (xDif == 2 || xDif == -2) &&
					(yDif == 2 || yDif == -2) &&
					pieces[(int)xMid][(int)yMid] != null &&
					pieces[(int)xMid][(int)yMid].side() != turn;
		}
		else if(p.side() == 0){
			return yDif == 2 &&
					(xDif == 2 || xDif == -2) &&
					pieces[(int)xMid][(int)yMid] != null &&
					pieces[(int)xMid][(int)yMid].side() != turn;
		}
		else{
			return yDif == -2 &&
					(xDif == 2 || xDif == -2) &&
					pieces[(int)xMid][(int)yMid] != null &&
					pieces[(int)xMid][(int)yMid].side() != turn;
		}
	}

	public void select(int x, int y){
		Piece p = pieces[x][y];
		selectedX = x;
		selectedY = y;
		if(p == null){
			selected.move(x, y);
			hasMoved = true;
		}
		else{
			selected = p;
		}
	}

	public void place(Piece p, int x, int y){
		if(p != null && x < N && y < N){
			pieces[x][y] = p;
			if(isEmpty){
				if(p.isFire()){
					fires += 1;
				}
				else{
					waters += 1;
				}
			}
		}
	}

	public Piece remove(int x, int y){
		if(x >= N || y>= N){
			return null;
		}
		Piece p = pieces[x][y];
		if(p == null){
			return null;
		}
		pieces[x][y] = null;
		if(p.isFire()){
			fires -= 1;
		}
		else{
			waters -= 1;
		}
		return p;
	}

	public boolean canEndTurn(){
		return hasMoved;
	}

	public void endTurn(){
		selected.doneCapturing();
		selected = null;
		selectedX = -1;
		selectedY = -1;
		hasMoved = false;
		if(turn == 0){
			turn = 1;
		}
		else{
			turn = 0;
		}
	}

	public String winner(){
		if(fires == 0 && waters == 0){
			return "No one";
		}
		if(fires == 0){
			return "Water";
		}
		if(waters == 0){
			return "Fire";
		}
		return null;
	}

	private void drawBoard(){
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        String imgStr = "";
        Piece piece;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (!isEmpty) {
					piece = pieces[i][j];
					if(piece != null){
						imgStr = "img/";
						if(piece.isShield()){
							imgStr = imgStr.concat("shield");
						}
						else if(piece.isBomb()){
							imgStr = imgStr.concat("bomb");
						}
						else{
							imgStr = imgStr.concat("pawn");
						}
						if(piece.isFire()){
							imgStr = imgStr.concat("-fire");
						}
						else{
							imgStr = imgStr.concat("-water");
						}
						if(piece.isKing()){
							imgStr = imgStr.concat("-crowned");
						}
						imgStr = imgStr.concat(".png");
						StdDrawPlus.picture(i + .5, j + .5, imgStr, 1, 1);
					}
                }
            }
        }
	}

	private void makeGUI(){
		while(true){
			StdDrawPlus.show(100);
			if(StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if(canSelect(x, y)){
					select(x, y);
				}
			}
			if(StdDrawPlus.isSpacePressed()){
				if(canEndTurn()){
					endTurn();
				}
			}
			drawBoard();
		}
	}

	public static void main(String[] args){
		Board board = new Board(false);
		//Board board = new Board(true);
		board.drawBoard();
		board.makeGUI();
	}
}