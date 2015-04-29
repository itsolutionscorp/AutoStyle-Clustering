import java.lang.Math;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board{
    private int N = 8;
	private Piece [][] pieces = new Piece[N][N];
    private int turn = 0;
    private Piece selected_piece = null;
    private boolean hasMoved = false;
    private boolean empty;

    public Board(boolean shouldBeEmpty)
    {
        this.empty = shouldBeEmpty;

        if (shouldBeEmpty == false)
        {
            this.initializePieces();
        }
    }

    private String [] images = {

        "img/pawn-fire", "img/bomb-fire", "img/shield-fire", "img/pawn-fire-crowned", "img/bomb-fire-crowned", "img/shield-fire-crowned", 
        "img/pawn-water", "img/bomb-water", "img/shield-water", "img/pawn-water-crowned", "img/bomb-water-crowned", "img/shield-water-crowned"
    };

    private void initializePieces()
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if ((j == 0) && (i % 2 == 0))
                {
                    pieces[j][i] = new Piece(true, this, i, j, "pawn");
                }

                else if ((j == 1) && (i % 2 == 1))
                {
                    pieces[j][i] = new Piece(true, this, i, j, "shield");
                }

                else if ((j == 2) && (i % 2 == 0))
                {
                    pieces[j][i] = new Piece(true, this, i, j, "bomb");
                }

                else if ((j == 5) && (i % 2 == 1))
                {
                    pieces[j][i] = new Piece(false, this, i, j, "bomb");
                }

                else if ((j == 6) && (i % 2 == 0))
                {
                    pieces[j][i] = new Piece(false, this, i, j, "shield");
                }

                else if ((j == 7) && (i % 2 == 1))
                {
                    pieces[j][i] = new Piece(false, this, i, j, "pawn");
                }

                else
                {
                    pieces[j][i] = null;
                }
            }
        }
    }

	private void drawBoard() {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                Piece piece = pieces[j][i];

                if (piece != null)
                {
                    StdDrawPlus.picture(getX(piece) + .5, getY(piece) + .5, getImage(piece) + ".png" , 1, 1);
                }
            }
        }

        StdDrawPlus.show(100);
    }

    private void drawEmptyBoard()
    {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        StdDrawPlus.show(100);
    }


    //My own method
    private void highlightSquare(int x, int y)
    {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        StdDrawPlus.show(100);
        this.drawBoard();
    }

    //My own method
    private int getX(Piece p)
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[j][i] == p)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    //My own method
    private int getY(Piece p)
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (pieces[j][i] == p)
                {
                    return j;
                }
            }
        }    
        return -1;
    }

    //My own method
    private String getImage(Piece p)
    {
        int index = 0;
        if (p.isFire() == false)
        {
            index += 6;
        }

        if (p.isKing())
        {
            index += 3;
        }

        if (p.isBomb())
        {
            index += 1;
        }

        if (p.isShield())
        {
            index += 2;
        }

        return images[index];
    }

    public Piece pieceAt(int x, int y)
    {
    	if (x > 7 || y > 7)
    	{
    		return null;
    	}
    	
    	return pieces[y][x];
    }

    public void place(Piece p, int x, int y)
    {
        pieces[y][x] = p;
    }

    public Piece remove(int x, int y)
    {
        Piece p = pieceAt(x, y);
        pieces[y][x] = null;
        return p;
    }

    //Next three methods all have to do with selecting/moving pieces
    public boolean canSelect(int x, int y)
    {
        Piece piece = pieceAt(x, y);
    
        if (piece == null)
        {
            if ((selected_piece != null) && (hasMoved == false))
            {
                //System.out.println("desired x: " + x + " desired y: " + y);
                return this.validMove(getX(selected_piece), getY(selected_piece), x, y);
            }

            else if ((selected_piece != null) && (selected_piece.hasCaptured() == true))
            {
                return this.validMove(getX(selected_piece), getY(selected_piece), x, y);
            }

            else
            {
                return false;
            }
        }

        else 
        {
            if (turn == 0)
            {
                if (piece.isFire() == false)
                {
                    return false;
                }

                else
                {
                    if ((selected_piece == null) || ((selected_piece != null) && (hasMoved == false)))
                    {
                        return true;
                    }
                }
            }

            else if (turn == 1)
            {
                if (piece.isFire() == true)
                {
                    return false;
                }

                else
                {
                    if ((selected_piece == null) || ((selected_piece != null) && (this.hasMoved == false)))
                    {
                        return true;
                    }
                }
            }

             return false;
        }

    } 
    
    //private boolean nullCheck

    private boolean validMove(int xi, int yi, int xf, int yf)
    {
        if ((xf > 7) || (yf > 7) || (xf < 0) || (yf < 0))
        {
            return false;
        }

        ///If it is fire's turn
        if (turn == 0)
        {
            if (selected_piece.isKing())
            {
                if (selected_piece.hasCaptured() == false) 
                {
                    if (((Math.abs(xf - xi) == 1)) && ((Math.abs(yf - yi) == 1)))
                    {
                        if (pieceAt(xf, yf) == null)
                        {
                            return true;
                        }

                        return false;

                    }
                }

                if ((xf - xi == 2) && (yf - yi == -2))
                {
                    if (pieceAt(xf - 1, yf + 1) == null)
                    {
                        return false;
                    }

                    else if (pieceAt(xf - 1, yf + 1).isFire() == false)
                    {
                        return true;
                    }

                    return false;
                }

                else if ((xf - xi == -2) && (yf - yi == -2))
                {
                    if (pieceAt(xf + 1, yf + 1) == null)
                    {
                        return false;
                    }

                    else if (pieceAt(xf + 1, yf + 1).isFire() == false)
                    {
                        return true;
                    }

                    return false;
                }

                else if ((xf - xi == 2) && (yf - yi == 2))
                {
                    if (pieceAt(xi + 1, yi + 1) == null)
                    {
                        return false;
                    }

                    else if (pieceAt(xi + 1, yi + 1).isFire() == false)
                    {
                        return true;
                    }

                    return false;
                }   

                else if ((xf - xi == -2) && (yf - yi == 2))
                {
                    if (pieceAt(xi - 1, yi + 1) == null)
                    {
                        return false;
                    }

                    if (pieceAt(xi - 1, yi + 1).isFire() == false)
                    {
                        return true;
                    }

                    return false;
                }   
            }

            else if (selected_piece.isKing() == false)
            {
                if (selected_piece.hasCaptured() == false) 
                {
                    if ((xf - xi == 1) && (yf - yi == 1))
                    {
                        if (pieceAt(xf, yf) == null)
                        {
                            return true;
                        }

                        return false;
                    }   

                    else if ((xf - xi == -1) && (yf - yi == 1))
                    {
                        if (pieceAt(xf, yf) == null)
                        {
                            return true;
                        }

                        return false;
                    }   

                }
                
                if ((xf - xi == 2) && (yf - yi == 2))
                {
                    if (pieceAt(xi + 1, yi + 1) == null)
                    {
                        return false;
                    }

                    else if (pieceAt(xi + 1, yi + 1).isFire() == false)
                    {
                        return true;
                    }

                    return false;
                }   

                else if ((xf - xi == -2) && (yf - yi == 2))
                {
                    if (pieceAt(xi - 1, yi + 1) == null)
                    {
                        return false;
                    }

                    else if (pieceAt(xi - 1, yi + 1).isFire() == false)
                    {
                        return true;
                    }

                    return false;
                }   

                return false;
            }
        }
        
    //If it is water's turn
    else if (turn == 1)
    {
        if (selected_piece.isKing())
        {
            if (selected_piece.hasCaptured() == false) //added new
            {
                if (((Math.abs(xf - xi) == 1)) && ((Math.abs(yf - yi)) == 1))
                {
                    if (pieceAt(xf, yf) == null)
                    {
                        return true;
                    }

                    return false;
                }
            }  
        
            if ((xf - xi == 2) && (yf - yi == 2))
            {
                if (pieceAt(xi + 1, yi + 1) == null)
                {
                    return false;
                }

                else if (pieceAt(xi + 1, yi + 1).isFire() == true)
                {
                    return true;
                }

            }   

            else if ((xf - xi == -2) && (yf - yi == 2))
            {
                if (pieceAt(xi - 1, yi + 1) == null)
                {
                    return false;
                }

                else if (pieceAt(xi - 1, yi + 1).isFire() == true)
                {
                    return true;
                }

                return false;
            }   

            if ((xf - xi == 2) && (yf - yi == -2))
            {
                if (pieceAt(xf - 1, yf + 1) == null)
                {
                    return false;
                }

                else if (pieceAt(xf - 1, yf + 1).isFire() == true)
                {
                    return true;
                }

                return false;
            }

            else if ((xf - xi == -2) && (yf - yi == -2))
            {
                if (pieceAt(xf + 1, yf + 1) == null)
                {
                    return false;
                }

                if (pieceAt(xf + 1, yf + 1).isFire() == true)
                {
                    return true;
                }

                return false;
            }

        }

        else if (selected_piece.isKing() == false)
        {
            if (selected_piece.hasCaptured() == false)
            {
                if ((xf - xi == 1) && (yf - yi == -1))
                {
                    if (pieceAt(xf, yf) == null)
                    {
                        return true;
                    }
                    return false;                    
                }   

                else if ((xf - xi == -1) && (yf - yi == -1))
                {
                    if (pieceAt(xf, yf) == null)
                    {
                        return true;
                    }
                    return false;
                   
                }   
            }

            if ((xf - xi == 2) && (yf - yi == -2))
            {
                if (pieceAt(xf - 1, yf + 1) == null)
                {
                    return false;
                }

                else if (pieceAt(xf - 1, yf + 1).isFire() == true)
                {
                    return true;
                }

                return false;
            }

            else if ((xf - xi == -2) && (yf - yi == -2))
            {
                if (pieceAt(xf + 1, yf + 1) == null)
                {
                    return false;
                }

                else if (pieceAt(xf + 1, yf + 1).isFire() == true)
                {
                    return true;
                }

                return false;
            }
            return false;   
            }

        return false;
        }

        return false;
    }

        
    public void select(int x, int y)
    {
        int xf = x;
        int yf = y;
        
        if (pieceAt(x, y) == null)
        {
            if (selected_piece == null)
            {
                System.out.println("Please select piece");
            }

            else
            {
                int xi = getX(selected_piece);
                int yi = getY(selected_piece);
                //System.out.println("Selected blank square");
            
                if (((Math.abs(xf - xi)) == 2) && ((Math.abs(yf - yi)) == 2))
                {
                    if ((xf - xi == 2) && (yf - yi == 2))
                    {
                        jumpingPieces(selected_piece, xf, yf, "upRight");
                    }

                    else if ((xf - xi == -2) && (yf - yi == 2))
                    {
                        jumpingPieces(selected_piece, xf, yf, "upLeft");
                    }

                    else if ((xf - xi == 2) && (yf - yi == -2))
                    {
                        jumpingPieces(selected_piece, xf, yf, "downRight");
                    }

                    else if ((xf - xi == -2) && (yf - yi == -2))
                    {
                        jumpingPieces(selected_piece, xf, yf, "downLeft");
                    }
                }

                else
                {
                    place(selected_piece, xf, yf);
                    selected_piece.move(xf, yf);
                    selected_piece = remove(xi, yi);
                }
               
                hasMoved = true;
            }
        }
    

        else
        {
           //this.highlightSquare(x, y);
           selected_piece = pieces[y][x];
        }
        
    }

    private void jumpingPieces(Piece selected, int xf, int yf, String direction)
    {
        int initial_x = getX(selected_piece);
        int initial_y = getY(selected_piece);
    
        place(selected_piece, xf, yf);
        selected_piece.move(xf, yf);
        //selected_piece.doneCapturing();
        remove(initial_x, initial_y);
        
        if (direction.equals("upRight"))
        {
            Piece skipped_piece = remove(initial_x + 1, initial_y + 1);
        }

        else if (direction.equals("upLeft"))
        {
            Piece skipped_piece = remove(initial_x - 1, initial_y + 1);
        }

        else if (direction.equals("downRight"))
        {
            Piece skipped_piece = remove(initial_x + 1, initial_y - 1);
        }

        else if (direction.equals("downLeft"))
        {
            Piece skipped_piece = remove(initial_x - 1, initial_y - 1);
        }

        if (selected_piece.isBomb() == true)
        {
            bombAll(getX(selected_piece), getY(selected_piece));
            remove(getX(selected_piece),  getY(selected_piece));
        }
    }

    private void bombAll(int x, int y)
    {

        Piece[] surroundingPieces = new Piece[8];

        if ((x == 7) && (y == 7))
        {
            surroundingPieces[0] = pieces[y][x-1];
            surroundingPieces[1] = pieces[y-1][x];
            surroundingPieces[2] = pieces[y-1][x-1];
        }

        else if ((x == 7) && (y == 0))
        {
            surroundingPieces[0] = pieces[y+1][x];
            surroundingPieces[1] = pieces[y][x-1];
            surroundingPieces[2] = pieces[y+1][x-1];
        }

        else if ((x == 0) && (y == 7))
        {
            surroundingPieces[0] = pieces[y][x+1];
            surroundingPieces[1] = pieces[y-1][x+1];
            surroundingPieces[2] = pieces[y-1][x];
        }

        else if ((x == 0) && (y == 0))
        {
            surroundingPieces[0] = pieces[y+1][x+1];
            surroundingPieces[1] = pieces[y][x+1];
            surroundingPieces[2] = pieces[y+1][x];
        }

        else if (x == 0)
        {
            surroundingPieces[0] = pieces[y][x+1];
            surroundingPieces[1] = pieces[y+1][x+1];
            surroundingPieces[2] = pieces[y-1][x+1];
            surroundingPieces[3] = pieces[y-1][x];
            surroundingPieces[4] = pieces[y+1][x];
        }

        else if (x == 7)
        {
            surroundingPieces[0] = pieces[y][x-1];
            surroundingPieces[1] = pieces[y+1][x-1];
            surroundingPieces[2] = pieces[y-1][x-1];
            surroundingPieces[3] = pieces[y-1][x];
            surroundingPieces[4] = pieces[y+1][x];
        }

        else if (y == 0)
        {         
            surroundingPieces[0] = pieces[y][x+1];
            surroundingPieces[1] = pieces[y+1][x+1];
            surroundingPieces[2] = pieces[y+1][x];
            surroundingPieces[3] = pieces[y+1][x-1];
            surroundingPieces[4] = pieces[y][x-1];
            
        }

        else if (y == 7)
        {
            surroundingPieces[0] = pieces[y][x+1];
            surroundingPieces[1] = pieces[y-1][x+1];
            surroundingPieces[2] = pieces[y-1][x];
            surroundingPieces[3] = pieces[y-1][x-1];
            surroundingPieces[4] = pieces[y][x-1];
        }

        else
        {
            surroundingPieces[0] = pieces[y+1][x+1];
            surroundingPieces[1] = pieces[y-1][x+1];
            surroundingPieces[2] = pieces[y-1][x-1];
            surroundingPieces[3] = pieces[y+1][x-1];

            surroundingPieces[4] = pieces[y+1][x];
            surroundingPieces[5] = pieces[y-1][x];
            surroundingPieces[6] = pieces[y][x+1];
            surroundingPieces[7] = pieces[y][x-1];
        }

        for (Piece surroundingPiece: surroundingPieces)
        {
            if (surroundingPiece != null)
            {
                if (surroundingPiece.isShield() == false)
                {
                    remove(getX(surroundingPiece), getY(surroundingPiece));
                }
                
            }
        }

    }

    
    public boolean canEndTurn()
    {
        if (hasMoved == true)
        {
            return true; 
        }

        else
        {
            return false;
        }
       
    }

    public void endTurn()
    {
        if (canEndTurn() == true)
        {
            if (turn == 0)
            {
                turn = 1;
            }

            else if (turn == 1)
            {
                turn = 0;
            }

            hasMoved = false;
            selected_piece.doneCapturing();
            selected_piece = null;
        }
    }

    public String winner()
    {
        int waterCount = 0; //
        int fireCount = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

            if (pieces[j][i] != null)
            {
                if (pieces[j][i].isFire() == true)
                {
                    fireCount++;
                }
                else if (pieces[j][i].isFire() == false)
                {
                    waterCount++;
                }
            }
            }
        }

        if ((fireCount == 0) && (waterCount == 0))
        {
            return "No one";
        }

        else if (fireCount == 0)
        {
            return "Water";
        }

        else if (waterCount == 0)
        {
            return "Fire";
        }

        return null;
        
    }

    //Main Method
	public static void main(String[] args) {
        
        Board checkers = new Board(false);

        if (checkers.empty == true)
        {
            checkers.drawEmptyBoard();
        }

        else if (checkers.empty == false)
        {
            checkers.drawBoard();
        }
    
        while (true)
        {
            if (StdDrawPlus.mousePressed() == true)
            {
                int xCord = (int)StdDrawPlus.mouseX();
                int yCord = (int)StdDrawPlus.mouseY();
                if (checkers.canSelect(xCord, yCord))
                {
                    checkers.select(xCord, yCord);
                }
                checkers.drawBoard();
            }

            if (StdDrawPlus.isSpacePressed() == true)
            {
                if (checkers.hasMoved == true)
                {
                    checkers.endTurn();
                }
            }

        }

    }

    // @Override
    // public String toString() {
    //     StringBuilder s = new StringBuilder();
    //     for (int y = 7; y >= 0; y--) {
    //         for (int x = 0; x < pieces[y].length; x++) {
    //             if (pieces[y][x] == null) {
    //                 s.append(" [ " + x + "," + y + " ] ");
    //             } else {
    //                 s.append(pieces[y][x]); 
    //             }

    //         }
    //         s.append("\n");
    //     }
    //     return s.toString();
    // }


	
}