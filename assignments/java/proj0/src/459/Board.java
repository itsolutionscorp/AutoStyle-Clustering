import java.lang.Math;
public class Board{

	private boolean shouldBeEmpty;
	private Piece[][] pieces = new Piece[8][8];
	private boolean fireTurn = true; // Initially it is Fire's turn
	private Piece selectedPiece;
	private boolean turned = false; // Initially it haven't turned
	private boolean temp = false;

	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
		if(!shouldBeEmpty){
			for(int i = 0; i <=6; i += 2){
			new Piece(true, this, i, 0, "pawn");
			new Piece(true, this, i, 2, "bomb");
			new Piece(false,this, i, 6, "shield");
			new Piece(true, this, i + 1, 1, "shield");
			new Piece(false, this, i + 1, 5, "bomb");
			new Piece(false, this, i + 1, 7, "pawn");
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if ( x<0 || x>7 || y<0 || y>7){
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){ 

		if(temp){
			return false;
		}

		if (pieceAt(x,y) != null){
			if (selectedPiece != null){
				if(selectedPiece.hasCaptured() && pieces[x][y]==selectedPiece){
					return false;
				}	
			} 

			if(fireTurn && pieces[x][y].isFire() || !fireTurn && !pieces[x][y].isFire()){
				return true;
			}
			return false;
		}

		if (pieceAt(x,y) == null){
			if (selectedPiece == null){
				return false;
			}

			int i = 0;
			int j = 0; // Find selected piece's location

			mainLoop:
			for(i = 0; i < 8; i++){
				for(j = 0; j < 8;j++){
					if (pieces[i][j] == selectedPiece){
						break mainLoop;
					}
				}
			}

			if(selectedPiece.isKing()){
				if (fireTurn && (Math.abs(x-i) == 2) && (Math.abs(y-j) == 2) && pieces[(x+i)>>1][(y+j)>>1]!= null){
					if (!pieces[(x+i)/2][(y+j)/2].isFire()){
						return true;
					}
				}
				if (!fireTurn && (Math.abs(x-i) == 2) && (Math.abs(y-j) == 2) && pieces[(x+i)>>1][(y+j)>>1]!= null){
					if (pieces[(x+i)/2][(y+j)/2].isFire()){
						return true;
					}
				}
				if (!selectedPiece.hasCaptured()){
					return validMove(i,j,x,y,true);
				} 
			} else {
				if (fireTurn && (Math.abs(x-i) == 2) && (y-j == 2) && pieces[(x+i)>>1][(y+j)>>1]!= null){
					if (!pieces[(x+i)/2][(y+j)/2].isFire()){
						return true;
					}
				}
				if (!fireTurn && (Math.abs(x-i) == 2) && (y-j == -2) && pieces[(x+i)>>1][(y+j)>>1]!= null){
					if (pieces[(x+i)/2][(y+j)/2].isFire()){
						return true;
					}
				} 	
				if (!selectedPiece.hasCaptured()){
					return validMove(i,j,x,y, false);
				}
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf, boolean king){ // For regular one way move
		int dy = yf - yi;
		int dx = xf - xi;
		if (!king){
			if (fireTurn && dy == 1 && Math.abs(dx) == 1){
				return true;
			} else if(!fireTurn && dy == -1 && Math.abs(dx) == 1){
				return true;
			} else {
				return false;
			}			
		} else {
			if (Math.abs(dy) == 1 && Math.abs(dx) == 1){
				return true;
			} else {
				return false;
			}				
		}
	}

	public void select(int x, int y){
		if (pieces[x][y] != null){
			selectedPiece = pieces[x][y];
		} else {
			if(selectedPiece!=null){// only for purpose to pass test 1, turns out it helps me passed test 16
				selectedPiece.move(x,y);
				turned = true;
				if (!selectedPiece.hasCaptured()){ // Moved a single piece
					temp = true;
				} else if(selectedPiece.isBomb()){ // Bomb capture, and then should explode
					explode(selectedPiece);
					temp = true;
				}
			}
		} 
	}

	private void explode(Piece p){
		int i = 0;
		int j = 0; // Find selected piece's location

		mainLoop:
		for(i = 0; i < 8; i++){
			for(j = 0; j < 8;j++){
				if (pieces[i][j] == p){
					break mainLoop;
				}
			}
		}

		for (int a = i-1; a<=i+1; a++){
			for (int b = j-1; b<= j+1; b++){
				if(pieceAt(a,b)!= null){
					if(!pieceAt(a,b).isShield()){
						remove(a,b);
					}
				}
			}
		}
	}

	public void place(Piece p, int x, int y){
		if ( x<0 || x>7 || y<0 || y>7 || p == null){
			return;
		}
		// Check if the piece already exist on the board
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(pieces[i][j] == p){
					if(i == x && j == y){
						turned = false;
						return; 
					}
					pieces[i][j] = null;
				}
			}
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if ( x<0 || x>7 || y<0 || y>7 ){ // Out of bounds
			System.out.println("Out of bounds");
			return null;
		}
		if(pieces[x][y]==null){
			System.out.println("Piece doesn't exist");
			return null;
		}
		Piece tempP = pieceAt(x,y);
		pieces[x][y] = null;
		return tempP;
	}

	public boolean canEndTurn() {
		return turned;
	}

	public void endTurn(){
		fireTurn = !fireTurn;
		turned = false;
		if(selectedPiece != null){ // added this condition only for test 1
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
		temp = false;
	}

	public String winner(){
		boolean fireAlive = false;
		boolean waterAlive = false;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieceAt(i,j)!=null) {
					if(pieceAt(i,j).isFire()){
						fireAlive=true;
					} else {
						waterAlive = true;
					}
				}
			}
		}

		if (!fireAlive && !waterAlive){
			return "No one";
		}
		if (fireAlive && !waterAlive){
			return "Fire";
		}
		if (!fireAlive && waterAlive){
			return "Water";
		}			
		return null;
	}

	private void drawBoard(){
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
		}
	}

	private void update(){
		for (int a = 0; a < 8; a++){
			for (int b = 0; b < 8; b++){
				if (pieces[a][b] != null){
					drawPiece(pieces[a][b],a,b);
				}
			}
		}
	}

	private void drawPiece(Piece p, int x, int y){
		// p is the piece that will be drawed
		// x and y is its coordinate
		if(p.isFire()){
			if(p.isBomb()){
				if(p.isKing()){
					// Fire bomb king
					StdDrawPlus.picture(x+0.5, y+0.5, "img/bomb-fire-crowned.png",1,1);
				 return;
				}
				// Fire bomb
				StdDrawPlus.picture(x+0.5, y+0.5, "img/bomb-fire.png",1,1);
			} else if(p.isShield()){
				if(p.isKing()){
					// Fire shield king
					StdDrawPlus.picture(x+0.5, y+0.5, "img/shield-fire-crowned.png",1,1);
				 return;
				}				
				// Fire shield
				StdDrawPlus.picture(x+0.5, y+0.5, "img/shield-fire.png",1,1);
			}else{
				if(p.isKing()){
					// Fire pawn king
					StdDrawPlus.picture(x+0.5, y+0.5, "img/pawn-fire-crowned.png",1,1);
				 return;
				}				
				// Fire pawn
				StdDrawPlus.picture(x+0.5, y+0.5, "img/pawn-fire.png",1,1);
			}
		} else {
			if(p.isBomb()){
				if(p.isKing()){
					// Water bomb king
					StdDrawPlus.picture(x+0.5, y+0.5, "img/bomb-water-crowned.png",1,1);
				 return;
				}
				// Water bomb
				StdDrawPlus.picture(x+0.5, y+0.5, "img/bomb-water.png",1,1);
			} else if(p.isShield()){
				if(p.isKing()){
					// water shield king
					StdDrawPlus.picture(x+0.5, y+0.5, "img/shield-water-crowned.png",1,1);
				 return;
				}
				// water shield
				StdDrawPlus.picture(x+0.5, y+0.5, "img/shield-water.png",1,1);
			} else{
				if(p.isKing()){
					// Water pawn king
					StdDrawPlus.picture(x+0.5, y+0.5, "img/pawn-water-crowned.png",1,1);
				 return;
				}				
				// water pawn
				StdDrawPlus.picture(x+0.5, y+0.5, "img/pawn-water.png",1,1);
			}
		}
	}

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        b.drawBoard();
		b.update();
		// Above is board initiation
		int x = 1;
		int y = 0;
		while(b.winner() == null){
			if(StdDrawPlus.mousePressed()){
				x = (int) StdDrawPlus.mouseX();
				y = (int) StdDrawPlus.mouseY();
			}
			StdDrawPlus.show(10);
			if(b.pieceAt(x,y) != null){
				if (b.canSelect(x,y)){
					b.drawBoard();
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
					b.select(x,y);
					b.update();
				}
			} else { 
				if (b.canSelect(x,y)){
					b.select(x,y);
					b.drawBoard();
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
					if (b.selectedPiece.isBomb() && b.selectedPiece.hasCaptured()){
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
						StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);						
					}
					b.update();
				}
			}
			if(b.canEndTurn()){
				if (StdDrawPlus.isSpacePressed()){
					b.endTurn();
					b.drawBoard();
					b.update();
				}
			}
		}
		b.drawBoard();
		b.update();
		System.out.println("Winner belongs to " + b.winner().toLowerCase() + ".");
		return;
	}
}
