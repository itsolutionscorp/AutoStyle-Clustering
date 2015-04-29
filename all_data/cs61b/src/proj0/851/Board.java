// @author Noah Ryan Lopez
// CS61b-bgt
// Final Submission
public class Board {
	private static Piece[][] pieces;
	private Piece selected = null;
	private int selectedx;
	private int selectedy;
	private boolean moved = false;
	private String currentPlayer = "p1";


	public static void main(String args[]) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board p = new Board(false);
        while (p != null){
        	p.drawBoard();
        	if (StdDrawPlus.mousePressed()) {
        		double dx = StdDrawPlus.mouseX();
        		double dy = StdDrawPlus.mouseY();
        		int x = (int) dx;
        		int y = (int) dy;
        		if (x < 8 && y < 8 && p.pieces[x][y] != null && p.selected == null){
        			if (p.canSelect(x, y) == true){
        				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        				p.select(x, y);
        			}
        		}
        		else if (x < 8 && y < 8 && p.selected != null) {
        			if (p.canSelect(x, y) == true){
        				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        				p.select(x, y);
        			}
        		}
        	}
        	if (StdDrawPlus.isSpacePressed()){
        		if (p.canEndTurn() == true){
        			p.endTurn();
        		}
        	}
        	StdDrawPlus.show(100);
        }
	}

	public Board(boolean shouldBeEmpty) {
		pieces =  new Piece[8][8];
		if (shouldBeEmpty == false) {
			for (int y = 0; y < 8; y++){
				int x = 0;
				while (x < 8){
					if (y == 0 && x % 2 == 0) {
						pieces[x][y] = new Piece(true, this, x, y, "pawn");
					}
					else if (y == 1 && x % 2 != 0) {
						pieces[x][y] = new Piece(true, this, x, y, "shield");
					}
					else if (y == 2 && x % 2 == 0) {
						pieces[x][y] = new Piece(true, this, x, y, "bomb");
					}
					else if (y == 5 && x % 2 != 0) {
						pieces[x][y] = new Piece(false, this, x, y, "bomb");
					}
					else if (y == 6 && x % 2 == 0) {
						pieces[x][y] = new Piece(false, this, x, y, "shield");
					}
					else if (y == 7 && x % 2 != 0){
						pieces[x][y] = new Piece(false, this, x, y, "pawn");
					}
					x += 1;
				}
			}
		}
	}

	private static void drawBoard()	{
		for (int xpos = 0; xpos < 8; xpos++){
			int ypos = 0;
			while (ypos < 8) {
				if ((xpos + ypos) % 2 != 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				else if ((xpos + ypos) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				StdDrawPlus.filledSquare(xpos + .5, ypos + .5, .5);
				if (pieces[xpos][ypos] != null) {
					Piece piece = pieces[xpos][ypos];
					if (piece.isFire() == true) {
						if (piece.isShield() == false && piece.isBomb() == false) {
							if (piece.isKing() == false) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/pawn-fire.png", 1, 1);
							}
							else if (piece.isKing() == true) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
						}
						else if (piece.isShield() == true) {
							if (piece.isKing() == false) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/shield-fire.png", 1, 1);
							}
							else if (piece.isKing() == true) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/shield-fire-crowned.png", 1, 1);
							}
						}
						else {
							if (piece.isKing() == false) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/bomb-fire.png", 1, 1);
							}
							else if (piece.isKing() == true) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
						}
					}
					else if (piece.isFire() == false) {
						if (piece.isShield() == false && piece.isBomb() == false) {
							if (piece.isKing() == false) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/pawn-water.png", 1, 1);
							}
							else if (piece.isKing() == true) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/pawn-water-crowned.png", 1, 1);
							}
						}
						else if (piece.isShield() == true) {
							if (piece.isKing() == false) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/shield-water.png", 1, 1);
							}
							else if (piece.isKing() == true) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/shield-water-crowned.png", 1, 1);
							}
						}
						else {
							if (piece.isKing() == false) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/bomb-water.png", 1, 1);
							}
							else if (piece.isKing() == true) {
								StdDrawPlus.picture(xpos + .5, ypos + .5, "img/bomb-water-crowned.png", 1, 1);
							}
						}
					}
				}
				ypos += 1;
			}
		}
		return;
	}
	
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		else if (pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}
	public boolean canSelect(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		else if (pieces[x][y] != null) {
			if (pieces[x][y].isFire() == true && currentPlayer == "p1") {
				if ((selected == null) || moved == false){
					return true;
				}
				return false;
			}
			else if (pieces[x][y].isFire() == false && currentPlayer != "p1") {
				if ((selected == null) || moved == false) {
					return true;
				}
				return false;
			}
			return false;
		}
		else if (selected != null && selected.hasCaptured() == false && moved == false) {
			if ((((selectedx + 1) == x) && ((selectedy + 1) == y)) || (((selectedx - 1) == x) && ((selectedy + 1) == y))){
				if (selected.isFire() == true || selected.isKing() == true) {
					return true;
				}
				return false;
			}
			else if ((((selectedx + 1) == x) && ((selectedy - 1) == y)) || (((selectedx - 1) == x) && ((selectedy - 1) == y))) {
				if (selected.isFire() != true || selected.isKing() == true) {
					return true;
				}
				return false;
			}
			// Checks Fire
			else if ((((selectedx + 2) == x) && ((selectedy + 2) == y)) || (((selectedx - 2) == x) && ((selectedy + 2) == y))) {
				if (x == 0) {
						if (pieces[(x + 1)][y - 1] != null) {
							Piece check = pieces[(x + 1)][y - 1];
							if ((selected.isFire() == true || selected.isKing() == true) && (check.isFire() != true || selected.isKing() == true)) {
								if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
								}
								else if(selected.isKing() == true) {
									return false;
								}
								return true;
							}
						}
					}
				else if (x == 7) {
					if (pieces[(x - 1)][y - 1] != null) {
						Piece check = pieces[(x - 1)][y - 1];
						if ((selected.isFire() == true || selected.isKing() == true) && (check.isFire() != true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
				}
				else if ((pieces[(x - 1)][y - 1] != null) || (pieces[(x + 1)][y - 1] != null)){
					if (pieces[(x - 1)][y - 1] != null) {
						Piece check = pieces[(x - 1)][y - 1];
						if ((selected.isFire() == true || selected.isKing() == true) && (check.isFire() != true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}

					else if (pieces[(x - 1)][y - 1] == null){
						Piece check = pieces[(x + 1)][y - 1];
						if ((selected.isFire() == true || selected.isKing() == true) && (check.isFire() != true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
				}
				return false;
			}
			//checks water
			else if ((((selectedx + 2) == x) && ((selectedy - 2) == y)) || (((selectedx - 2) == x) && ((selectedy - 2) == y))) {
				if (x == 0) {
						if (pieces[(x + 1)][y + 1] != null) {
							Piece check = pieces[(x + 1)][y + 1];
							if ((selected.isFire() != true || selected.isKing() == true) && (check.isFire() == true || selected.isKing() == true)) {
								if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
								}
								else if(selected.isKing() == true) {
									return false;
								}
								return true;
								}
							}
						}
				else if (x == 7) {
					if (pieces[(x - 1)][y + 1] != null) {
						Piece check = pieces[(x - 1)][y + 1];
						if ((selected.isFire() != true || selected.isKing() == true) && (check.isFire() == true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
				}
				else if ((pieces[(x - 1)][y + 1] != null) || (pieces[(x + 1)][y + 1] != null)) {
					if (pieces[(x - 1)][y + 1] != null) {
						Piece check = pieces[(x - 1)][y + 1];
						if ((selected.isFire() != true || selected.isKing() == true) && (check.isFire() == true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
					else if (pieces[(x - 1)][y + 1] == null){
						Piece check = pieces[(x + 1)][y + 1];
						if ((selected.isFire() != true || selected.isKing() == true) && (check.isFire() == true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
				}
				return false;
			}
		}
		else if (moved == true && selected.hasCaptured() == true && selected.isBomb() != true) {
			if ((((selectedx + 2) == x) && ((selectedy + 2) == y)) || (((selectedx - 2) == x) && ((selectedy + 2) == y))) {
				if (x == 0) {
						if (pieces[(x + 1)][y - 1] != null) {
							Piece check = pieces[(x + 1)][y - 1];
							if ((selected.isFire() == true || selected.isKing() == true) && (check.isFire() != true || selected.isKing() == true)) {
								if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
								}
								else if(selected.isKing() == true) {
									return false;
								}
								return true;
							}
						}
					}
				else if (x == 7) {
					if (pieces[(x - 1)][y - 1] != null) {
						Piece check = pieces[(x - 1)][y - 1];
						if ((selected.isFire() == true || selected.isKing() == true) && (check.isFire() != true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
				}
				else if ((pieces[(x - 1)][y - 1] != null) || (pieces[(x + 1)][y - 1] != null)){
					if (pieces[(x - 1)][y - 1] != null) {
						Piece check = pieces[(x - 1)][y - 1];
						if ((selected.isFire() == true || selected.isKing() == true) && (check.isFire() != true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
					else if (pieces[(x - 1)][y - 1] == null){
						Piece check = pieces[(x + 1)][y - 1];
						if ((selected.isFire() == true || selected.isKing() == true) && (check.isFire() != true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
				}
				return false;
			}
			//checks water
			else if ((((selectedx + 2) == x) && ((selectedy - 2) == y)) || (((selectedx - 2) == x) && ((selectedy - 2) == y))) {
				if (x == 0) {
						if (pieces[(x + 1)][y + 1] != null) {
							Piece check = pieces[(x + 1)][y + 1];
							if ((selected.isFire() != true || selected.isKing() == true) && (check.isFire() == true || selected.isKing() == true)) {
								if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
								}
								else if(selected.isKing() == true) {
									return false;
								}
								return true;
								}
							}
						}
				else if (x == 7) {
					if (pieces[(x - 1)][y + 1] != null) {
						Piece check = pieces[(x - 1)][y + 1];
						if ((selected.isFire() != true || selected.isKing() == true) && (check.isFire() == true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
				}
				else if ((pieces[(x - 1)][y + 1] != null) || (pieces[(x + 1)][y + 1] != null)) {
					if (pieces[(x - 1)][y + 1] != null) {
						Piece check = pieces[(x - 1)][y + 1];
						if ((selected.isFire() != true || selected.isKing() == true) && (check.isFire() == true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
					else if (pieces[(x - 1)][y + 1] == null){
						Piece check = pieces[(x + 1)][y + 1];
						if ((selected.isFire() != true || selected.isKing() == true) && (check.isFire() == true || selected.isKing() == true)) {
							if (selected.isKing() == true && (selected.isFire() != check.isFire())){
									return true;
							}
							else if(selected.isKing() == true) {
								return false;
							}
							return true;
						}
					}
				}
				return false;
			}
		}
		return false;
	}
	
	public void select(int x, int y) {
		if ((x > 7) ||  (y > 7) || (x < 0) || (y < 0)) {
			return;
		}
		else if (pieces[x][y] != null) { 
			selected = pieces[x][y];
			selectedx = x;
			selectedy = y;
			return;
		}
		else if (pieces[x][y] == null) {
			selected.move(x, y);
			moved = true;
			selectedx = x;
			selectedy = y;
			return;

		}
		return;
	}


	public void place(Piece p, int x, int y) {
		if ((x > 7) ||  (y > 7) || (x < 0) || (y < 0)) {
			return;
		}
		pieces[x][y] = p;
		selected = p;	
	}

	public Piece remove(int x, int y){
		if ((x > 7) ||  (y > 7) || (x < 0) || (y < 0)) {
			System.out.println("Cannot remove Piece: Out of bounds");
			return null;
		}
		else if(pieces[x][y] == null) {
			System.out.println("Cannot remove Piece: Does not exist");
			return null;
		}
		Piece result = pieces[x][y];
		pieces[x][y] = null;
		return result;
	}

	public boolean canEndTurn() {
		return moved;

	}

	public void endTurn(){
		if (currentPlayer == "p1"){
			currentPlayer = "p2";
			moved = false;
			selected.doneCapturing();
			selected = null;
			return;
		}
		currentPlayer = "p1";
		moved = false;
		selected.doneCapturing();
		selected = null;
		return;
	}

	//Winner method goes here
	public String winner() {
		int y = 0;
		int x = 0;
		String result;
		int fireCount = 0;
		int waterCount = 0;
		while (x < 8) {
			y = 0;
			while (y < 8){
				if (pieceAt(x, y) != null){
					if (pieceAt(x, y).isFire() == true){
						fireCount += 1;
					}
					else if (pieceAt(x, y).isFire() != true){
						waterCount += 1;
					}
				}
				y += 1;
			}
			x += 1;
		}
		if (fireCount == 0 && waterCount != 0) {
			result = "Water";
			return result;
		}
		else if (fireCount != 0 && waterCount == 0) {
			result = "Fire";
			return result;
		}
		else if (fireCount == 0 && waterCount == 0){
			result = "No one";
			return result;
		}
		else {
			return null;
		}

	}
}