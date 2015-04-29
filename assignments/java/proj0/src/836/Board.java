
public class Board{
	public boolean shouldBeEmpty;
	private boolean moved, fireTurn, firePieces, waterPieces;
	private static Piece[][] pieces;
	private Piece selected;
	private String panda;
	private double i, j;
	public void main(String args[]){
		Board b = new Board(true);
		while(b.winner() == null){
			for (int a = 0; a < 8; a++){
				for (int c = 0; c < 8; c++) {
					if ((a + c) % 2 == 0){
						StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
					}else{
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
					StdDrawPlus.filledSquare(a + .5, c + .5, .5);
					if (b.pieces[a][c]){
						if(b.pieces[a][c].isBomb()){
							b.panda = "bomb-";
						}else if(b.pieces[a][c].isShield()){
							panda = "shield-";
						}else{
							panda = "pawn-";
						}
						if(b.pieces[a][c].isFire()){
							panda = panda + "fire";
						}else{
							panda = panda + "water";
						}
						if(b.pieces[a][c].isKing()){
							panda = panda + "-crowned";
						}
						StdDrawPlus.picture(a + .5, c + .5, "img/" + panda + ".png", 1, 1);
					}
				}
			}
			if(b.selected == null){
				if (StdDrawPlus.mousePressed()) {
					i = StdDrawPlus.mouseX();
					j = StdDrawPlus.mouseY();
					if(b.canSelect((int) i,(int) j)){
						b.select((int) i,(int) j);
					}
				}
			}else{
				if (StdDrawPlus.mousePressed()) {
					i = StdDrawPlus.mouseX();
					j = StdDrawPlus.mouseY();
					if(b.canSelect((int) i,(int) j)){
						if(b.validMove((int) i, (int) j)){
							if(max(selected.x, (int) i) - min(selected.x, (int) i) == 2){
								b.remove(max(selected.x, (int) i) - 1, max(selected.y, (int) j) - 1);
								selected.hasCaptured() = true;
							}
							b.place(selected, (int) i, (int) j);
							selected.move((int) i, (int) j);
							b.moved = true;
							b.select((int) i,(int) j);
							
						}
					}
				}
			}
			if(b.canEndTurn() && StdDrawPlus.isSpacePressed()){
				b.endTurn();
			}
			StdDrawPlus.show(100);
		}
    }
	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
		if(!shouldBeEmpty){
			for(int a = 0; a < 8; a++){
				for(int b = 0; b < 8; b++){
					if(a == 0){
						if(b % 2 != 0){
							pieces[a][b] = new Piece(false, this, a, b, "pawn");
						}
					}else if(a == 1){
						if(b % 2 == 0){
							pieces[a][b] = new Piece(false, this, a, b, "shield");
						}
					}else if(a == 2){
						if(b % 2 != 0){
							pieces[a][b] = new Piece(false, this, a, b, "bomb");
						}
					}else if(a == 5){
						if(b % 2 == 0){
							pieces[a][b] = new Piece(true, this, a, b, "bomb");
						}
					}else if(a == 6){
						if(b % 2 != 0){
							pieces[a][b] = new Piece(true, this, a, b, "shield");
						}
					}else if(a == 7){
						if(b % 2 == 0){
							pieces[a][b] = new Piece(true, this, a, b, "pawn");
						}
					}
				}
			}
		}
	}
	public Piece pieceAt(int x, int y){
		return pieces[x][y];
	}
	public boolean canSelect(int x, int y){
		if(fireTurn && pieceAt(x, y).isFire || (!fireTurn && !pieceAt(x, y).isFire)){
			return (pieceAt(x, y) && moved == false) || (!pieceAt(x, y) && selected && (moved == false || pieceAt(x, y).hasCaptured()));
		}
		return false;
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		if(pieces[xf][yf]){
			return false;
		}else if(max(xf, xi) - min(xf, xi) == 1 && max(yf, yi) - min(yf, yi) == 1){
			return true;
		}else if(max(xf, xi) - min(xf, xi) == 2 && max(yf, yi) - min(yf, yi) == 2 && pieceAt(max(xf, xi) - 1, max(yf, yi) - 1)){
			return pieceAt(max(xf, xi) - 1, max(yf, yi) - 1).isFire() != pieceAt(xi, yi).isFire();
		}
		return false;
	}
	public void select(int x, int y){
		if(pieces[x][y]){
			selected = pieceAt(x, y);
		}else{
			pieces[x][y] = selected;
			selected = pieceAt(x, y);
		}
	}
	public void place(Piece p, int x, int y){
		if(p){
			pieceAt(x, y) = p;
		}
	}
	public Piece remove(int x, int y){
		if(pieceAt(x, y)){
			Piece z = pieceAt(x, y);
			this.pieces[x][y] = null;
			return z;
		}
		return null;
	}
	public boolean canEndTurn(){
		return moved;
	}
	public void endTurn(){
		fireTurn = !fireTurn;
		selected.doneCapturing();
		selected = null;
		moved = false;
	}
	public String winner(){
        for (int a = 0; a < 8; a++){
            for (int b = 0; b < 8; b++) {
				if(pieceAt[a][b]){
					if(pieceAt[a][b].isFire()){
						
					}
				}
			}
		}
		if(firePieces && waterPieces){
			return null;
		}else if(firePieces && !waterPieces){
			return "fire";
		}else if(waterPieces && !firePieces){
			return "water";
		}else{
			return "no one";
		}
	}
}