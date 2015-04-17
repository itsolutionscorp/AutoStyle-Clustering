import java.lang.Math;
public class Board {

	private Piece[][] pieces;
	private Piece selected;
	private int player;
	private boolean made_move;
	private boolean made_bomb_capture;
	
	public static void main(String[] args){
		Board b = new Board(false);
		b.run();
	}

	private void run(){
		while (winner()==null){
			takeTurn();
		}

		System.out.println(winner() + " won the game!");
	}

	private void takeTurn(){
		while (true){
			render();
			if (StdDrawPlus.mousePressed() && !made_move && !made_bomb_capture){
				int x = (int)(StdDrawPlus.mouseX());
				int y = (int)(StdDrawPlus.mouseY());
				if (canSelect(x, y)){
					select(x, y);
				}
			}
			if (StdDrawPlus.isSpacePressed() && canEndTurn()){
				break;
			}
		}
		render();
		endTurn();
	}

	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty){
			pieces = generateEmpty();
		} else {
			pieces = generatePieces(this);
		}
		selected = null;
		player = 0;
		made_move = false;
		made_bomb_capture = false;
	}

	private void render(){
		drawBoard();
		drawSelected();
		drawPieces();
		StdDrawPlus.show(10);
	}

	private void drawBoard(){
		StdDrawPlus.setXscale(0.5,7.5);
		StdDrawPlus.setYscale(0.5,7.5);
		for (int row = 0; row <=7; row+=1){
			for (int col = 0; col <= 7; col +=1){
				if (row%2 == col%2){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(row+0.5, col+0.5, 0.5);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(row+0.5, col+0.5, 0.5);
				}
			}
		}
	}

	private void drawPieces(){
		for (int x = 0; x <= 7; x+=1){
			for (int y = 0; y<=7; y+=1){
				Piece p = pieces[x][y];
				if (p!=null){
					String s = getImgUrl(p);
					StdDrawPlus.picture(x+0.5, y+0.5, s, 1, 1);
				}
			}
		}
	}

	private void drawSelected(){
		if (selected!=null){
			int x = find_x(selected);
			int y = find_y(selected);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x+0.5, y+0.5, 0.5);
		}
	}

	private String getImgUrl(Piece p){
		String type = "";
		if (p.isBomb()){
			type = "bomb";
		} else if (p.isShield()){
			type = "shield";
		} else {
			type = "pawn";
		}
		String s = "./img/" + type + "-";
		
		if (p.side()==0){
			s = s+"fire";
		} else {
			s = s +"water";
		}

		if (p.isKing()){
			s = s+"-crowned";
		}
		s += ".png";
		return s;
	}

	private Piece[][] generateEmpty(){
		Piece[][] array = new Piece[8][8];
		for (int x = 0; x <=7; x+=1){
			for (int y = 0; y<=7; y+=1){
				array[x][y] = null;
			}
		}
		return array;
	}

	private Piece[][] generatePieces(Board b){
		Piece[][] array = new Piece[8][8];

		for (int x = 0; x<=7; x+=1){
			for (int y = 0; y <= 7; y+=1){
				if (y==0 && x%2==0){
					array[x][y] = new Piece(true, b, x, y, "pawn");
				} else if (y==1 && x%2==1){
					array[x][y] = new Piece(true, b, x, y, "shield");
				} else if (y ==2 && x%2==0){
					array[x][y] = new Piece(true, b, x, y, "bomb");
				} else if (y == 5 && x%2==1){
					array[x][y] = new Piece(false, b, x, y, "bomb");
				} else if (y == 6 && x%2==0){
					array[x][y] = new Piece(false, b, x, y, "shield");
				} else if (y == 7 && x%2==1){
					array[x][y] = new Piece(false, b, x, y, "pawn");
				} else {
					array[x][y] = null;
				}
			}
		}
		return array;
	}

	public Piece pieceAt(int x, int y){
		if (x > 7 || y > 7 || x < 0 || y < 0){
			return null;
		}
		return pieces[x][y];
	}

	private boolean onBoard(Piece p){
		for (int row = 0; row <= 7; row +=1){
			for (int col = 0; col <= 7; col+=1){
				if (pieceAt(row, col) == p){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean canSelect(int x, int y){
		Piece p = pieceAt(x, y);
		if (!onBoard(selected)){
			selected=null;
		}
		
		if (p==null){
			if (selected == null){
				return false;
			} else {
				return validMove(selected, x, y);
			}
		} else {
			if (selected != null && (selected.hasCaptured() || made_move || made_bomb_capture)){
				return false;
			}
			//added for bomb capture test
			if (made_bomb_capture){
				return false;
			}
			return (p.side()==player);
		}
	}

	private boolean validMove(Piece p, int xf, int yf){
		int xi = find_x(p);
		int yi = find_y(p);

		if (p == null){
			return false;
		} else if (p.isKing()){
			return (validFireMove(xi, xf, yi, yf, p.hasCaptured()) || validIceMove(xi, xf, yi, yf, p.hasCaptured()));
		} else if (p.side()==0){
			return validFireMove(xi, xf, yi, yf, p.hasCaptured());
		} else {
			return validIceMove(xi, xf, yi, yf, p.hasCaptured());
		}
	}

	private boolean validFireMove(int xi, int xf, int yi, int yf, boolean capturesOnly){
		if (Math.abs(xi-xf)==1 && yf-yi==1){
			return !capturesOnly && !made_move;
		} else if (Math.abs(xi-xf)==2 && yf-yi==2){
			int middlex = (xi+xf)/2;
			int middley = (yi+yf)/2;
			Piece p = pieceAt(middlex, middley);
			if (p != null && p.side()!=player){
				return true;
			}
		}
		return false;
	}

	private boolean validIceMove(int xi, int xf, int yi, int yf, boolean capturesOnly){
		if (Math.abs(xi-xf)==1 && yi-yf==1){
			return !capturesOnly && !made_move;
		} else if (Math.abs(xi-xf)==2 && yi-yf==2){
			int middlex = (xi+xf)/2;
			int middley = (yi+yf)/2;
			Piece p = pieceAt(middlex, middley);
			if (p != null && p.side()!=player){
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y){
		Piece p = pieceAt(x, y);
		if (p == null){
			if (Math.abs(find_x(selected)-x)==1){
				place(selected, x, y);
				made_move = true;
			} else {
				int ox = find_x(selected);
				int oy = find_y(selected);
				place(selected, x, y);
				if (selected.isBomb()){
					int middlex = (ox+x)/2;
					int middley = (oy+y)/2;
					remove(middlex, middley);
					removeSurrounding(x,y);
					made_bomb_capture = true;
				}
			}
			
		} else {
			selected = pieceAt(x, y);
		}
	}
	
	private void removeSurrounding(int x, int y){
		for (int row = x-1; row < x+2; row+=1){
			for (int col = y-1; col < y+2; col+=1){
				Piece p = pieceAt(row, col);
				if (p!=null && !p.isShield()){
					remove(row, col);
				}
			}
		}
	}

	private int find_x(Piece p){
		for (int x = 0; x <= 7; x+=1){
			for (int y = 0; y<=7; y+=1){
				if (pieceAt(x, y) == p){
					return x;
				}
			}
		}
		return -1;
	}

	private int find_y(Piece p){
		for (int x = 0; x <= 7; x+=1){
			for (int y = 0; y<=7; y+=1){
				if (pieceAt(x, y) == p){
					return y;
				}
			}
		}
		return -1;
	}

	public void place(Piece p, int x, int y){
		if (p != null && inBounds(x, y)){
			remove(find_x(p), find_y(p));
			pieces[x][y] = p;
			p.move(x, y);	
		}
	}
	
	private boolean inBounds(int x, int y){
		if (x > 7 || y > 7 || x < 0 || y < 0){
			return false;
		}
		return true;
	}

	public Piece remove(int x, int y){
		if (inBounds(x, y)){
			Piece p = pieceAt(x, y);
			pieces[x][y] = null;
			return p;
		}
		return null;
	}

	public boolean canEndTurn(){
		
		if (selected == null){
			return false;
		} else if (selected.hasCaptured() || made_move || made_bomb_capture){
			return true;
		} else {
			return false;
		}
	}

	public void endTurn(){
		selected = null;
		player = 1-player;
		made_move = false;
		made_bomb_capture = false;
		for (int x = 0; x<=7; x+=1){
			for (int y = 0; y<=7; y+=1){
				Piece p = pieceAt(x, y);
				if (p!=null){
					p.doneCapturing();
				}
			}
		}

	}

	public String winner(){
		int count = 0;
		Piece last = null;
		Piece temp = null;
		for (int x = 0; x <= 7; x+=1){
			for (int y = 0; y <=7; y+=1){
				temp = pieceAt(x, y);
				if (temp != null){
					count +=1;
					last = temp;
				}
			}
		}

		if (count == 0){
			return "No one";
		} else if (count == 1){
			if (last.side() == 0){
				return "Fire";
			} else {
				return "Water";
			}
		} else {
			return null;
		}
	}
}