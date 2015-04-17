import java.lang.Math.*;

public class Board {
	private static boolean boardEmpty;
	private static boolean[][] pieces;
	private static Piece [][] pieceLocation;
	private String type;
	private static final int size = 8;
	private int selectX;
	private int selectY;
	private boolean selectedSquare;
	private boolean fireTurn;
	private boolean moved;
	private boolean firePieceExist;
	private boolean waterPieceExist;

	private void drawBoard(int size){
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i + j) % 2 == 0){ 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else if (i == selectX && j == selectY){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(!boardEmpty){
	                if (pieces[i][j]) {
	                	if(pieceLocation[i][j].isShield()){
	                		type = "shield";
	                	} else if (pieceLocation[i][j].isBomb()){
	                		type = "bomb";
	                	} else{
	                		type = "pawn";
	                	}
	                	if (pieceLocation[i][j].isFire()){
	                		if (pieceLocation[i][j].isKing()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-fire-crowned.png", 1, 1);
	                		} else{
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-fire.png", 1, 1);
	                    	}
	                	} else{
	                		if (pieceLocation[i][j].isKing()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-water-crowned.png", 1, 1);
	                		} else{
	                			StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-water.png", 1, 1);
	                		}
	                	}

	                }
	            }
            }
        }
	}

	public static void main (String args[]){
        StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);
        Board b = new Board(false);
		
        while(true) {
            b.drawBoard(size);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x, y)){
                	b.select(x, y);
                }
            } if (StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            } 
            if(b.winner() != null){
            	b.winner();
            	break;
            }
            StdDrawPlus.show(10);
        }
	}
	
	public Board(boolean shouldBeEmpty){
		boardEmpty = shouldBeEmpty;
		pieces = new boolean[size][size];
        pieceLocation = new Piece[size][size];
        selectedSquare = false;
        fireTurn = true;
        moved = false;
        selectX = 0;
        selectY = 0;
        firePieceExist = false;
        waterPieceExist = false;
        if (!shouldBeEmpty){
        	for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if((i + j) % 2 == 0 && j == 0){
                	this.pieceLocation[i][j] = new Piece(true, this, i, j, "pawn");
                	this.pieces[i][j] = true;
                }
                if((i + j) % 2 == 0 && j == size - 1){
                	this.pieceLocation[i][j] = new Piece(false, this, i, j, "pawn");
                	this.pieces[i][j] = true;
                }
                if((i + j) % 2 == 0 && j == 1){
                	this.pieceLocation[i][j] = new Piece(true, this, i, j, "shield");
                	this.pieces[i][j] = true;
                }
                if((i + j) % 2 == 0 && j == size - 2){
                	this.pieceLocation[i][j] = new Piece(false, this, i, j, "shield");
                	this.pieces[i][j] = true;
                }
                if((i + j) % 2 == 0 && j == 2){
                	this.pieceLocation[i][j] = new Piece(true, this, i, j, "bomb");
                	this.pieces[i][j] = true;
                }
                if((i + j) % 2 == 0 && j == size - 3){
                	this.pieceLocation[i][j] = new Piece(false, this, i, j, "bomb");
                	this.pieces[i][j] = true;
                }
            }
        }
        }
	}
	
	public Piece pieceAt(int x, int y){
		if (x < size && y < size && pieces[x][y]){
			return pieceLocation[x][y];
		} else{
		return null;
		}
	}
	
	public boolean canSelect(int x, int y){
		if (pieces[x][y] && fireTurn == pieceLocation[x][y].isFire()){
			if(!selectedSquare){
				return true;
			} else if(selectedSquare && !moved){
				return true;
			}
		}
		if(!pieces[x][y] && selectedSquare && !moved && validMove(selectX, selectY, x, y)){
			return true;
		}
		if(!pieces[x][y] && pieces[selectX][selectY] && selectedSquare && pieceLocation[selectX][selectY].hasCaptured() && validMove(selectX, selectY, x, y)){
			return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if(xf < size && yf < size && !pieces[xf][yf]){
			if(Math.abs(xf-xi) == 1 && Math.abs(yf - yi) == 1 && !pieceLocation[xi][yi].hasCaptured()){
				if(pieceLocation[xi][yi].isKing()){
					return true;
				} if(!pieceLocation[xi][yi].isKing() && yf - yi > 0 && pieceLocation[xi][yi].isFire()){
					return true;
				} if(!pieceLocation[xi][yi].isKing() && yf - yi < 0 && !pieceLocation[xi][yi].isFire()){
					return true;
				}
			}	
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2){
				if(pieces[(xf + xi)/2][(yf + yi)/2] && pieceLocation[(xf + xi)/2][(yf + yi)/2].isFire() != pieceLocation[xi][yi].isFire()){
					if(pieceLocation[xi][yi].isKing()){
						return true;
					} if(!pieceLocation[xi][yi].isKing() && yf - yi > 0 && pieceLocation[xi][yi].isFire()){
						return true;
					} if(!pieceLocation[xi][yi].isKing() && yf - yi < 0 && !pieceLocation[xi][yi].isFire()){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void select(int x, int y){
		if (pieces[x][y]){
			selectX = x;
			selectY = y;
			selectedSquare = true;
		}
		if (!pieces[x][y] && pieces[selectX][selectY]){
			if(selectedSquare){
				moved = true;
				pieceLocation[selectX][selectY].move(x, y);
				selectX = x;
				selectY = y;
			}
		}
	}
	
	public void place(Piece p, int x, int y){
		if(p != null && x < size && y < size){
			for (int i = 0; i < size; i++){
				for (int j = 0; j < size; j++){
					if (pieceLocation[i][j] == (p)){
						remove(i, j);
					}
				}
			}
			pieces[x][y] = true;
			pieceLocation[x][y] = p;
		}
	}
	
	public Piece remove(int x, int y){
		if (x >= size || y >= size){
			System.out.println("Out of bounds");
			return null;
		} if (!pieces[x][y]){
			System.out.println("No Piece at " + x + ", " + y);
			return null;
		}
		Piece removedPiece = pieceLocation[x][y];	
		pieceLocation[x][y] = null;
		pieces[x][y] = false;
		return removedPiece;
	}
	
	public boolean canEndTurn(){
		if(moved){
			return true;
		} 
		for(int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				if (pieces[i][j] && pieceLocation[i][j].hasCaptured()){
					return true;
				}
			}
		}
		return false;
	}
	
	public void endTurn(){
			fireTurn = !fireTurn;
			moved = false;
			selectedSquare = false;
			firePieceExist = false;
			waterPieceExist = false;
			for(int i = 0; i < size; i++){
				for (int j = 0; j < size; j++){
					if (pieces[i][j]){
						pieceLocation[i][j].doneCapturing();
					}
				}
			}
	}
	
	public String winner(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(pieces[i][j]){
					if(pieceLocation[i][j].isFire()){
						firePieceExist = true;
					} if(!pieceLocation[i][j].isFire()){
						waterPieceExist = true;
					}
				}
			}
		}

		if(firePieceExist && !waterPieceExist){
			return "Fire";
		} else if (waterPieceExist && !firePieceExist){
			return "Water";
		} else if (!firePieceExist && !waterPieceExist){
			return "No one";
		} else{
			return null;
		}
	}
}