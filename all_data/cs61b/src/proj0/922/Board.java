public class Board{

private static int N = 8;
private Piece[][] pieces;
private boolean pieceMoved = false;
private boolean pieceCaptured = false;
private boolean firePlaying = true;
private boolean pieceSelected = false;	
private int[] selection = new int[]{9, 9};

public static void main(String[] args){
	StdDrawPlus.setXscale(0, N);
	StdDrawPlus.setYscale(0, N);
	Board b = new Board(false); 
	while(true) {
		b.drawBoard(N);
		if (StdDrawPlus.mousePressed()) {
			int x = (int) StdDrawPlus.mouseX();
			int y = (int) StdDrawPlus.mouseY();
			// System.out.println("Mouse Pressed at Location: x = "+x_loc+" y = "+y_loc);
			if(b.canSelect(x, y)){b.select(x, y);}
			// System.out.println("selected: x = "+b.selection[0]+" y = "+b.selection[1]+" moved: "+b.pieceMoved+" captured: "+b.pieceCaptured+" piece selected: "+b.pieceSelected);
		}            
		if (StdDrawPlus.isSpacePressed()) {
			if(b.canEndTurn()){b.endTurn();}
		}
		if(b.pieceSelected){
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(b.selection[0] + .5, b.selection[1] + .5, .5);
		}    
		StdDrawPlus.show(2);
		if(b.winner() != null){return;}
	}
}

public Board(boolean shouldBeEmpty){
	pieces = new Piece[N][N];
	if(!shouldBeEmpty){initiateBoard();}
}

private void initiateBoard(){
	for(int i=0;i<N;i++){
		for(int j=0;j<N;j++){
			if(j==7 && (i%2)==1) pieces[i][j] = new Piece(false, this, i, j, "pawn");
			if(j==6 && (i%2)==0) pieces[i][j] = new Piece(false, this, i, j, "shield");
			if(j==5 && (i%2)==1) pieces[i][j] = new Piece(false, this, i, j, "bomb");
			if(j==2 && (i%2)==0) pieces[i][j] = new Piece(true, this, i, j, "bomb");
			if(j==1 && (i%2)==1) pieces[i][j] = new Piece(true, this, i, j, "shield");
			if(j==0 && (i%2)==0) pieces[i][j] = new Piece(true, this, i, j, "pawn");
		}
	}
}

private void drawBoard(int N) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			Piece p = pieces[i][j];
			if (p != null) {
				String pic = "img/"+(p.isBomb()? "bomb" : p.isShield()? "shield" : "pawn")+"-"+(p.isFire()? "fire" : "water")+(p.isKing()? "-crowned" : "")+".png";
				StdDrawPlus.picture(i + .5, j + .5, pic, 1, 1);
			}
		}
	}
}

public Piece pieceAt(int x, int y){
	return 0<=x && x<N && 0<=y && y<N? pieces[x][y] : null;
}

public void place(Piece p, int x, int y){
	if(0<=x && x<N && 0<=y && y<N){
		// System.out.println("X-Coord: "+x+" Y-Coord: " +y);
		pieces[x][y] = p;
	}
}

public Piece remove(int x, int y){
	if(0<=x && x<N && 0<=y && y<N){
		Piece r = pieces[x][y];
		pieces[x][y] = null;
		if(r==null){
			System.out.println("There is no piece at location: x = "+x+" y = "+y);
		}
		return r;
	}
	else{
		System.out.println("Board location out of bounds");
		return null;
	}
}

public boolean canEndTurn(){
	return pieceMoved;
}

public void endTurn(){
	firePlaying = !firePlaying; 
	pieceMoved = false;
	pieceCaptured = false;
	pieceSelected = false;
	if(pieceAt(selection[0], selection[1]) != null){
		pieceAt(selection[0], selection[1]).doneCapturing();
	}
	selection = new int[]{9, 9};

}

public String winner(){
	boolean fireRemains = false;
	boolean waterRemains = false;
	for(Piece x[] : pieces){
		for(Piece y : x){
			if(y != null){
				if(y.isFire()){fireRemains = true;}
				else{waterRemains = true;}
			}
		}
	}
	return (fireRemains && waterRemains) ? null : (fireRemains ? "Fire" : (waterRemains ? "Water" : "No one"));  
}

public boolean canSelect(int x, int y){
	Piece p = pieceAt(x,y);
	// System.out.println("canSelect called at: x = "+x+" y = "+y+" Piece = "+p);
	if(p != null){
		return (firePlaying != p.isFire()) ? false : (pieceSelected ? !pieceMoved : true);
	}
	else{
		if(pieceMoved && pieceCaptured){
			return validCapture(selection, x, y);
		}
		else if(!pieceMoved){ 
			return (validCapture(selection, x, y) || validMove(selection, x, y));
		}
		else{
			return false;
		}
	}
}	

private boolean validMove(int[] sel, int x, int y){
	Piece p = pieceAt(sel[0], sel[1]);
	boolean waterMove = (y == (sel[1] - 1)) && ((sel[0] == x - 1) || (sel[0] == x + 1));
	boolean fireMove = (y == (sel[1] + 1)) && ((sel[0] == x - 1) || (sel[0] == x + 1));
	if (p != null){
		return (p.isKing() ? (waterMove || fireMove) : (p.isFire() ? fireMove : waterMove)); 
	}
	return false;
}

private boolean validCapture(int[] sel, int x, int y){
	// System.out.println("ValidCapture called at: x = "+x+" y = "+y);
	Piece p = pieceAt(sel[0], sel[1]);
	boolean waterMove = (y == (sel[1] - 2)) && ((sel[0] == x - 2) || (sel[0] == x + 2));
	boolean fireMove = (y == (sel[1] + 2)) && ((sel[0] == x - 2) || (sel[0] == x + 2));
	int new_x_loc = (int)((x - sel[0])*1/2 + sel[0]);
	int new_y_loc = (int)((y - sel[1])*1/2 + sel[1]);
	// System.out.println("Captured x loc: "+new_x_loc+" y loc: "+new_y_loc);
	Piece p_captured = pieceAt(new_x_loc, new_y_loc); //calculates position of piece captured
	// System.out.println("FireMove: "+fireMove+" WaterMove: "+waterMove+ " Captured: "+p_captured);
	if (p_captured != null && p != null){
		if (p_captured.isFire() != p.isFire()){
			return (p.isKing() ? (waterMove || fireMove) : (p.isFire() ? fireMove : waterMove)); 
		}
	}
	return false;
}

public void select(int x, int y){
	Piece sel_p = pieceAt(selection[0], selection[1]);
	if(pieceAt(x,y)!=null){
		selection = new int[]{x,y};
		pieceSelected = true;
	}
	else if (sel_p != null){
		pieces[x][y] = remove(selection[0],selection[1]);
		sel_p.move(x,y);
		pieceMoved = true;
		pieceCaptured = sel_p.hasCaptured();
		selection = new int[]{x,y};
	}
}
}