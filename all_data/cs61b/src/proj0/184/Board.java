public class Board {
    private int size;
    private Piece[][] pieces;
    private Piece lastSelected;
    private int lastSelectedX;
    private int lastSelectedY;
    private boolean hasMoved;
    private int turn = 0;

    public Board(boolean shouldBeEmpty){
        size = 8;
        lastSelected = null;
        hasMoved = false; 
        pieces = new Piece[size][size];
        
        if(!shouldBeEmpty){
            for (int i = 0; i < size; i+=1){
                for (int j = 0; j < size; j++){
                    if(i == 0 && (j*2 < size)){
                        pieces[j*2][i] = new Piece(true, this, j*2, i, "pawn");
                    } else if(i == 1 && j*2+1 < size){
                        pieces[j*2+1][i] = new Piece(true, this, j*2 + 1, i, "shield");
                    } else if(i == 2 && j*2 < size){
                        pieces[j*2][i] = new Piece(true, this, j*2, i, "bomb");
                    } else if (i == size-1 && j*2+1 < size){
                        pieces[j*2+1][i] = new Piece(false, this, j*2+1, i, "pawn");
                    } else if (i == size-2 && j*2 < size){
                        pieces[j*2][i] = new Piece(false, this, j*2, i, "shield");
                    } else if (i == size-3 && j*2+1 < size){
                        pieces[j*2+1][i] = new Piece(false, this, j*2+1, i, "bomb");
                    }
                    
                }
            }
            
        } 
    }
    
    private void showEmptyBoard(int N){
        StdDrawPlus.setYscale(0, N);
        StdDrawPlus.setXscale(0, N);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }       
    }
    
    private void drawDefBoard(int N) {
        StdDrawPlus.setYscale(0, N);
        StdDrawPlus.setXscale(0, N);
        showEmptyBoard(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null){
                    StdDrawPlus.picture(i + .5, j + .5, imgLocation(pieces[i][j]), 1 ,1);
                }
            }
        }
    }
    
    public Piece pieceAt(int x, int y){
        if((x > size) || (y > size)){            
            return null;
        }
        if(pieces[x][y] != null){
            return pieces[x][y];
        } return null;
    }
    

    public void place(Piece p, int x, int y){
        if(x < size && y < size && p!=null){
            if (pieceAt(x, y) != null){
                remove(x, y);
            }
            for (int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    if(pieceAt(i, j) == p){
                        remove(i, j);
                    }
                }
            }
            pieces[x][y] = p;
        } 
    }
    
    public Piece remove(int x, int y){
        if(x>size || y>size){
            System.out.println("Parameters out of bounds.");
            return null;
        } else if (pieces[x][y] == null){
            System.out.println("No piece to remove.");
            return null;
        } else {
            Piece rPiece = pieces[x][y];
            pieces[x][y] = null;
            return rPiece;
        }
    }
    
    public boolean canSelect(int x, int y){
        if(pieces[x][y] != null && turn == pieces[x][y].side()){
            if (lastSelected == null || hasMoved == false){
                return true;
            }
        } else if(pieces[x][y] == null){
            return canMove(lastSelectedX, lastSelectedY, x, y);
        } 
        return false;  
    }

    public void select(int x, int y){     
        if (pieceAt(x, y) == null){
            lastSelected.move(x, y);
            lastSelectedX = x;
            lastSelectedY = y;
            
            hasMoved = true;
           
        } else {
            lastSelected = pieceAt(x, y);
            lastSelectedX = x;
            lastSelectedY = y; 
        }
    }
    
    private int captureHorizDir(int xi, int xf){
        if(xf-xi < 0){
            return xi-1;
        } return xi+1;
    }
    
    private int captureVertDir(int yi, int yf){
        if(yf-yi < 0){
            return yi-1;
        } return yi+1;
    }
    
    private boolean kingVertDirection(int xi, int yi, int xf, int yf){
        if(yf-yi == -2){
            return (pieces[captureHorizDir(xi, xf)][yi-1] != null);
        } else if(yf-yi == 2){
            return (pieces[captureHorizDir(xi, xf)][yi+1] != null);
        } else {
            return false;
        }
    }
    
    private boolean canMove(int xi, int yi, int xf, int yf){
       if(pieceAt(xi, yi) == null){
           return false;
       }
       if(pieceAt(xi, yi).side() != turn){
           return false;
       }
       if(pieceAt(xi, yi).isKing()) {
           if(Math.abs(xf-xi) == 2){
               return kingVertDirection(xi, yi, xf, yf);
           } else if(Math.abs(yf-yi) == 1 && !hasMoved){
               return Math.abs(xf-xi) == 1;
           } return false;
       } else {
           if(pieceAt(xi, yi).isFire()){
               if(Math.abs(xf-xi) == 1 && yf-yi == 1){
                   return true;
               } else if(Math.abs(xf-xi) == 2 && yf-yi == 2){
                   return (pieceAt(captureHorizDir(xi, xf), captureVertDir(yi, yf)) != null);
               } return false;
           } else {
               if(Math.abs(xf-xi) == 1 && yf-yi == -1){
                   return true;
               } else if(Math.abs(xf-xi) == 2 && yf-yi == -2){
                   return (pieceAt(captureHorizDir(xi, xf), captureVertDir(yi, yf)) != null);
               } return false;
           }
       }

    }

    public boolean canEndTurn() {
        if(hasMoved){
            return true;
        } 
        return false;
    }
    
    
    public void endTurn() {
        turn(lastSelected);
        lastSelected.doneCapturing();
        lastSelected = null;
        hasMoved = false;        
    }    
    
    public String winner() {
        int fiah = 0;
        int watah = 0;
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (pieceAt(i, j) != null){
                    if(pieceAt(i, j).isFire()){
                        fiah += 1;
                    } else if(!pieceAt(i, j).isFire()){
                        watah += 1;
                    }
                }
            }
        }
        
        if(watah == 0 && fiah > 0){
            return "Fire";
        } else if(fiah == 0 && watah > 0){
            return "Water";
        } else if(fiah == 0 && watah == 0){
            return "No one";
        } return null;

    }
    
    private void turn(Piece p) {
        turn = -1 * p.side() + 1;
    }
    
    
    
    //following method helps us find the image for Piece P
    
    private String imgLocation(Piece p){
       String type = "";
       String element = "";
       String isKing = "";
       if(p.isBomb()){type = "bomb";} 
       else if (p.isShield()) {type = "shield";} 
       else { type = "pawn";}
       
       if(p.isFire()){element = "fire";} 
       else {element = "water";}
       
       if(p.isKing()){isKing = "-crowned";} 
       else {isKing = "";}
       
       return "img/" + type + "-" + element + isKing + ".png";
    }
    
    
	public static void main(String[] args){
	    
        Board b = new Board(false);

        while(b.winner() == null){
            if(StdDrawPlus.mousePressed()){
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();

                if (b.canSelect(x, y)){
                    b.select(x, y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                }
            }
        
            b.drawDefBoard(b.size);
            StdDrawPlus.show(10);
                        
        }
        System.out.println(b.winner());
        return;
        

	}
 	
}