import java.util.Arrays;

public class Board {
	private Piece[][] pieces;
	private boolean[][] tfPieces;
	private boolean fireTurn;
	private Piece selected;
	private int[] select_coordinates = new int[2];
	private boolean hasMoved;
	private int firePieces = 0;
	private int waterPieces = 0;
	private static String[][] imgs = {{"img/pawn-fire.png","img/bomb-fire.png", "img/shield-fire.png",
									   "img/pawn-fire-crowned.png","img/bomb-fire-crowned.png", "img/shield-fire-crowned.png"},
								      {"img/pawn-water.png","img/bomb-water.png", "img/shield-water.png",
									   "img/pawn-water-crowned.png","img/bomb-water-crowned.png", "img/shield-water-crowned.png"}};

	public static void main(String[] args) {
		//Creates and Draws board
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		String win;
		while (true){
			drawBoard(b);
			StdDrawPlus.show(10);
			if (StdDrawPlus.mousePressed()) {
                double i = StdDrawPlus.mouseX();
                double j = StdDrawPlus.mouseY();
                int x = (int) i;
                int y = (int) j;
                if (b.canSelect(x, y)) b.select(x, y);
            }
            if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()) b.endTurn();
            }
            win = b.winner();
            if (win != null){
            	System.out.println(win);
            	break;
            }           
		}
	}

	private static void drawBoard(Board b){
		int temp_fire = 0;
		int temp_water = 0;
		Piece temp;
		int piece_value;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if ((b.selected != null) && (b.select_coordinates[0] == i) && (b.select_coordinates[1] == j))
                					  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (b.tfPieces[i][j]) {
                	temp = b.pieceAt(i,j);
                	if (temp.isBomb()) piece_value = 1;
                	else if (temp.isShield()) piece_value = 2;
                	else piece_value = 0;
                	if (temp.isKing()) piece_value += 3;
                    StdDrawPlus.picture(i + .5, j + .5, imgs[temp.side()][piece_value], 1, 1);
                }
            }
        }
	}

	public Board(boolean shouldBeEmpty){
		pieces = new Piece[8][8];
		tfPieces = new boolean[8][8];
		for (int k = 0; k < 8; k++){
			for (int l = 0; l < 8; l++){
				tfPieces[k][l] = false;
			}
		}

		fireTurn = true;
		if (!shouldBeEmpty) {
			waterPieces = 12;
			firePieces = 12;
			boolean isFire = true;
			String type;
			for (int row = 0; row < 8; row++){
				if (row > 3) isFire = false;
				type = determinePiece(row);
				if ((row > 2) && (row < 5)) continue;
				for (int column = 0; column < 8; column++){
					if ((row + column) % 2 == 0){
						pieces[column][row] = new Piece(isFire, this, column, row, type);
						tfPieces[column][row] = true;
					}
				}
			}
		}
	}

	private String determinePiece(int row){
		if (row == 0 || row == 7){
			return "pawn";
		} else if (row == 1 || row == 6){
			return "shield";
		} else {
			return "bomb";
		}
	}

	public Piece pieceAt(int x, int y){
		if (x > 7 || y > 7 || pieces[x][y] == null) return null;
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		if (pieceAt(x,y) != null && (pieceAt(x,y).isFire() == fireTurn)){
			return ((selected == null) || !(hasMoved));
		} else {
			if (selected == null) return false;
			if ((!hasMoved) || (selected.hasCaptured() && (Math.abs(select_coordinates[0] - x) == 2 
											    && Math.abs(select_coordinates[1] - y) == 2))){
				return validMove(select_coordinates[0], select_coordinates[1], x, y);
			}
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		Piece a = pieceAt(xi,yi);
		if (a == null) return false;
		// checks for right direction
		if (!a.isKing()){
			if ((yf - yi > 0) && (!a.isFire())) return false;
			if ((yf - yi < 0) && (a.isFire())) return false;
		}

    	if (Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1){
    		if (hasMoved) return false; 
    		return (pieceAt(xf,yf) == null);
    	} else if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2){
    		if (hasMoved && !(a.hasCaptured())) return false; //returns false if moved but not captured
    		int xm, ym;
    		xm = (xi+xf)/2;
    		ym = (yi+yf)/2;
    		if (pieceAt(xm,ym) == null) return false;
    		return ((pieceAt(xf,yf) == null) && (pieceAt(xm,ym).side() != a.side()));
    	}
    	return false;
	}

	public void select(int x, int y){
		if (selected == null || (!hasMoved && pieceAt(x,y) != null)){
			selected = pieceAt(x,y);
			select_coordinates[0] = x;
			select_coordinates[1] = y;
		} else {
        	selected.move(x,y);
        	select_coordinates[0] = x;
        	select_coordinates[1] = y;
        	hasMoved = true;
		}
	}

	private boolean onBoard(Piece p){
		for (Piece[] a: pieces){
			for (Piece b: a){
				if (b!=null && b.equals(p)) return true;
			}
		}
		return false;
	}

	public void place(Piece p, int x, int y){
		if ((p == null) || (x > 7) || (y > 7)) return;
		if (onBoard(p)){
			pieces[x][y] = p;
			tfPieces[x][y] = true;
			hasMoved = true;
		} else {
			tfPieces[x][y] = true;
			pieces[x][y] = p;
		}
		if (p.isFire()) firePieces++;
		else waterPieces++;
	}

	public Piece remove(int x, int y){
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)){
			System.out.println("Index out of bounds");
			return null;
		}
		if (pieceAt(x,y) == null){
			System.out.println("No piece at: " + x + ", " + y);
			return null;
		}
		Piece temp = pieceAt(x,y);
		if (temp.isFire()) firePieces--;
		else waterPieces--;
		pieces[x][y] = null;
		tfPieces[x][y] = false;
		return temp;
	}

	public boolean canEndTurn(){
		return hasMoved;
	}

	public void endTurn(){
		fireTurn = !fireTurn;
		selected.doneCapturing();
		selected = null;
		hasMoved = false;
	}

	public String winner(){
		if (firePieces == 0 && waterPieces == 0) return "No One";
		if (firePieces == 0) return "Water";
		if (waterPieces == 0) return "Fire";
		return null;
	}
}