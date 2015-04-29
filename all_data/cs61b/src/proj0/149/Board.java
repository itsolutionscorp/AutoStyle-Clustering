/** 
 * @author Ianto Xi
 */
import java.awt.Color;
public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private Color color1 = new Color(200,200,200, 255);
    private Color color2 = new Color(150,150,150, 255);
    private int N = 8; 
    
    private int turn;
    private int xSelected;
    private int ySelected;
    private Piece selected;
    private boolean moved;


    public Board(boolean shouldBeEmpty){
        pieces = new Piece[N][N];
        turn = 0; 
        xSelected = -1; 
        ySelected = -1; 
        moved = false;

        if (!shouldBeEmpty){
            for(int i = 0; i < N/2; i += 1){
                //Fire
                place(new Piece(true, this, i*2, 0, "pawn"), i*2, 0);
                place(new Piece(true, this, i*2+1, 1, "shield"), i*2+1, 1);
                place(new Piece(true, this, i*2, 2, "bomb"), i*2, 2);

                //Water
                place(new Piece(false, this, i*2+1, N-1, "pawn"), i*2+1, N-1);
                place(new Piece(false, this, i*2, N-2, "shield"), i*2, N-2);
                place(new Piece(false, this, i*2+1, N-3, "bomb"), i*2+1, N-3);
            }
        }
    }

    public Piece remove(int x, int y){
        Piece toRemove = pieceAt(x,y);
        if(toRemove == null){
            System.out.print("Cannot remove piece: ");
            System.out.print(x); 
            System.out.print(" "); 
            System.out.print(y); 
            System.out.println();
            return null;
        }
        pieces[x][y] = null;
        return toRemove; 
    }

    public void select(int x, int y){
        xSelected = x; 
        ySelected = y; 

        //If you selected another piece (assuming canSelect) == 
        // if you did not select an empty square.

        if(pieceAt(x,y) != null){ 
            selected = pieceAt(x,y);

        //If you did select an empty square, move the selected piece 
        //to the selected square. 
        } else { 
            place(selected, xSelected, ySelected);
            moved = true; 

            if(selected.isBomb() && selected.hasCaptured()){
                remove(xSelected, ySelected); 
                xSelected = -1; 
                ySelected = -1; 
                selected = null; 
            }
        }
    }
    //Returns if player can select the piece of their cursor based on possible current
    //  selection, ability of pieces to move to selection, piece ownership, etc. 
    public boolean canSelect(int x, int y){
        // INVARIATES
        // 1. Cannot select out of bounds. 
        // 2. Cannot select opponent pieces. 
        
        // if TESTSELECT is out of bounds
        if(x >= N || y >= N){ //If TESTSELECT is out of bounds
            return false; 
        }
        
        //If TESTSELECT is a piece
        Piece testSelect = pieceAt(x,y); 
        if(testSelect != null){ 
            if (testSelect.side() != turn) //if TESTSELECT is not on side of current player
                return false; 
            if (!moved) //if no movement has occured yet this round. 
                return true; 
        } 

        //If User is selecting an empty square
        if(selected != null){//If piece already selected this round
            //Captures are always good if you can make one. 
            if(validCapture(selected, xSelected, ySelected, x, y))
                return true;
            //Can only move forward on first move attempt
            if(!moved && validAdvance(selected, xSelected, ySelected, x, y))//
                return true; 
        }
        return false; 
    }

    //Returns if piece is able to make valid step forward: one diagonal step, 
    //  right direction. 
    private boolean validAdvance(Piece p, int xi, int yi, int xf, int yf){
        //Make sure that pieces are only going one diagonal step
        if (Math.abs(xi - xf) != 1 || Math.abs(yi-yf) != 1){
            return false; 
        }
        return validDirection(p, xi, yi, xf, yf);
    }

    //Returns if piece is able to make a valid capture: over opponent, 
    //  two diagonal steps, right direction. 
    private boolean validCapture(Piece p, int xi, int yi, int xf, int yf){ 

        //Make sure that pieces are jumping. 
        if(Math.abs(xi - xf) != 2 || Math.abs(yi-yf) != 2){
            return false; 
        }

        //Ensure that piece captured is enemy piece
        Piece toCapture = forCapture(xi, yi, xf, yf);
        if(toCapture == null || toCapture.side() == turn){
            return false; 
        } 

        return validDirection(p, xi, yi, xf, yf);
    }

    //Returns if piece is going the valid direction for their Race 
    //  and Monarchy status
    private boolean validDirection(Piece p, int xi, int yi, int xf, int yf){

        if (p.isKing() || p.isFire()){
            if(yf > yi)                 //Fire goes up
                return true; 
        }  
        if (p.isKing() || !p.isFire()){
            if (yf < yi)                //Water goes down
                return true;                
        }
        return false; 
    }

    private Piece forCapture(int xi, int yi, int xf, int yf){
        int xMiddle = (xi + xf)/2;
        int yMiddle = (yi + yf)/2;
        return pieceAt(xMiddle, yMiddle);
    }

    public Piece pieceAt(int x, int y){
        if(x < N && y < N && x >= 0 && y >= 0)
            return pieces[x][y]; 
        else
            return null; 
    }

    public void place(Piece p, int x, int y){
        if(x < N && y < N && x >= 0 && y >= 0 && p != null){
            pieces[x][y] = p; 
            p.move(x,y);
        }
    }

    public boolean canEndTurn(){
        return moved; 
    }

    public void endTurn(){
        if(selected != null)
            selected.doneCapturing(); 
        turn = 1-turn; 
        xSelected = -1; 
        ySelected = -1; 
        selected = null; 
        moved = false;
    }

    public String winner(){
        int fire = 0; 
        int water = 0;
        Piece current;  
        for (int i = 0; i < N; i += 1){
            for (int j = 0; j < N; j = j += 1){
                current = pieceAt(i, j); 
                if (current != null){
                    if(current.isFire())
                        fire += 1; 
                    else
                        water += 1; 
                }
            }
        }
        if (water == 0 && fire == 0){ 
            return "No one";
        } else if(water == 0){
            return "Fire"; 
        } else if(fire == 0) {
            return "Water"; 
        } else {
            return null; 
        }
    }

    private String getImgLocation(Piece p){
        String location;  

        if(p.isKing())         location = "-crowned.png";
        else                   location = ".png";

        if(p.isFire())         location = "-fire" + location; 
        else                   location = "-water" + location; 

        if(p.isBomb())         location = "bomb" + location; 
        else if(p.isShield())  location = "shield" + location; 
        else                   location = "pawn" + location; 

        location = "img/" + location;
        return location; 
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i += 1){
            for (int j = 0; j < N; j = j += 1){
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(color1);
                else                  StdDrawPlus.setPenColor(color2);
                if(i == xSelected && j == ySelected)
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + 0.5, j+ 0.5, 0.5); 
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
                if(pieces[i][j] != null){   
                    Piece p = pieces[i][j]; 
                    String img = getImgLocation(p); 
                    StdDrawPlus.picture(i + 0.5, j + 0.5, img, 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) {

        Board gameBoard = new Board(false);

        StdDrawPlus.setXscale(0, gameBoard.N);
        StdDrawPlus.setYscale(0, gameBoard.N); 

        while(gameBoard.winner() == null){
            if (StdDrawPlus.mousePressed()){
                int x = (int) StdDrawPlus.mouseX(); 
                int y = (int) StdDrawPlus.mouseY(); 
                
                if(gameBoard.canSelect(x,y))
                    gameBoard.select(x, y);
            }
            if (StdDrawPlus.isSpacePressed() && gameBoard.canEndTurn())
                gameBoard.endTurn();
            
            gameBoard.drawBoard(gameBoard.N);
            StdDrawPlus.show(10);

        }
        System.out.println(gameBoard.winner());
    }
}