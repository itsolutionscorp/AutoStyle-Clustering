// Let's get this party staaarted.

import java.awt.*;

public class Board
{
    private static int size = 8;
    private Piece[][] pieces;
    private boolean currentPlayerIsFire;
    private Piece selectedPiece;
    private int selectedX;
    private int selectedY;
    private boolean selectedHasMoved;
    private int firePiecesLeft;
    private int waterPiecesLeft;

    private static final Color CALBLUE = new Color(4, 30, 66);
    private static final Color CALYELLOW = new Color(255, 199, 44);


    // Constructor
    public Board(boolean shouldBeEmpty)
    {
        pieces = new Piece[size][size];
        currentPlayerIsFire = true;
        if (!shouldBeEmpty) populateDefaultBoard(pieces);
    }

    public static void main(String[] args)
    {
        Board board = new Board(false);

        StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);

        while (true)
        {
            board.drawBoard(size);
            StdDrawPlus.show(100);

            if (StdDrawPlus.mousePressed())
            {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y)) board.select((int) x, (int) y);
            }
            if (StdDrawPlus.isSpacePressed())
            {
                if (board.canEndTurn()) board.endTurn();
            }
        }
    }

    public boolean canSelect(int x, int y)
    {
        if ((x > (size - 1)) || (y > (size - 1))) return false;
        if ((y < 0) || (x < 0)) return false;

        Piece piece = pieceAt(x, y);
        // if you're trying to select a piece...
        if (piece != null)
        {
            // Check to make sure piece is indeed owned by that player.
            if (piece.isFire() != currentPlayerIsFire)
            {
                System.out.println("Current player does not own that piece!");
                return false;
            }
            // Check to make sure player hasn't already moved another piece.
            if (selectedHasMoved)
            {
                System.out.println("Another piece has already moved this turn!");
                return false;
            } else return true;
        }
        // if you're trying to select an empty spot
        else
        {
            // check to make sure you've selected a piece
            if (selectedPiece == null) return false;
            // ensure selected piece still exists on the board
            if (pieceAt(selectedX, selectedY) != selectedPiece)
            {
                System.out.println("Whoa what? You broke it. There's no piece there to move.");
                return false;
            }
            // check to make sure the piece can move to new location
            if (!validMove(selectedX, selectedY, x, y)) return false;
            if (!selectedPiece.isKing())
            {
                if ((selectedPiece.isFire()) && (y < selectedY))
                {
                    System.out.println("Uncrowned piece can not move backwards!" + selectedY + y);
                    return false;
                }
                if ((!selectedPiece.isFire()) && (y > selectedY))
                {
                    System.out.println("Uncrowned piece can not move backwards!");
                    return false;
                }
            }
            return true;
        }
    }

    public void select(int x, int y)
    {
        Piece piece = pieceAt(x, y);
        if (piece != null)
        {
            // select a piece
            // current piece is stored be in selectedPiece, coordinates in selectedX and selectedY
            selectedPiece = piece;
            selectedX = x;
            selectedY = y;
        }
        else
        {
            // select an empty spot
            selectedHasMoved = true;
            selectedX = x;
            selectedY = y;

            //place(selectedPiece, x, y);
            selectedPiece.move(x, y);

            // if the bomb explodes, remove it as a selected piece
            if ((selectedPiece.isBomb()) && (pieceAt(x, y) != selectedPiece)) selectedPiece = null;
        }

    }

    public Piece remove(int x, int y)
    {
        if ((x > (size - 1)) || (y > (size - 1)))
        {
            System.out.println("Remove out of bounds");
            return null;
        }
        if ((x < 0) || (y < 0))
        {
            System.out.println("Remove out of bounds");
            return null;
        }
        Piece piece = pieceAt(x, y);
        if (piece == null)
        {
            System.out.println("No such piece there at " + x + ", " + y);
            return null;
        } else
        {
            if (piece.isFire()) firePiecesLeft = firePiecesLeft - 1;
            if (!piece.isFire()) waterPiecesLeft = waterPiecesLeft - 1;
            pieces[x][y] = null;
            return piece;
        }
    }

    public Piece pieceAt(int x, int y)
    {
        if ((x > (size - 1)) || (y > (size - 1))) return null;
        if ((x < 0) || (y < 0)) return null;

        else return pieces[x][y];
    }

    public void place(Piece p, int x, int y)
    {
        if (p == null) return;
        if ((x > (size - 1)) || (y > (size - 1))) return;
        if ((x < 0) || (y < 0)) return;
        else
        {
            if (p.isFire()) firePiecesLeft = firePiecesLeft + 1;
            if (!p.isFire()) waterPiecesLeft = waterPiecesLeft + 1;
            pieces[x][y] = p;
            //p.move(x, y);
        }
    }

    public void endTurn()
    {
        if (selectedPiece != null) selectedPiece.doneCapturing();
        selectedPiece = null;
        selectedHasMoved = false;
        nextPlayer();
        System.out.println("Changed player to " + ((currentPlayerIsFire) ? "Fire" : "Water"));
    }

    public boolean canEndTurn()
    {
        if (selectedHasMoved) return true;
        else return false;
    }

    public String winner()
    {
        if ((firePiecesLeft == 0) && (waterPiecesLeft == 0)) return "No one";
        if (firePiecesLeft == 0) return "Water";
        if (waterPiecesLeft == 0) return "Fire";
        else return null;
    }

    private void nextPlayer()
    {
        currentPlayerIsFire = !currentPlayerIsFire;
    }

    private boolean validMove(int xi, int yi, int xf, int yf)
    {
        // does NOT check if the piece can move forwards or backwards!
        int deltaX = xf - xi;
        int deltaY = yf - yi;

        // if destination is adjacent
        if ((Math.abs(deltaX) == 1) && (Math.abs(deltaY) == 1))
        {

            if (selectedHasMoved)
            {
                System.out.println("You can only move once unless capturing pieces!");
                return false;
            }
            if (pieceAt(xf, yf) == null) return true;
            else
            {
                System.out.println("There's already a piece there!");
                return false;
            }
        }

        // if destination is over another piece
        if ((Math.abs(deltaX) == 2) && (Math.abs(deltaY) == 2))
        {
            if ((selectedHasMoved) && (!pieceAt(xi, yi).hasCaptured()))
            {
                System.out.println("You can only move once!");
                return false;
            }

            // get piece in between current and destination, if any
            Piece pieceJumped = pieceInBetween(xi, yi, xf, yf);
            if (pieceJumped == null)
            {
                System.out.println("No piece to jump!");
                return false;
            }
            if (pieceJumped.isFire() == currentPlayerIsFire)
            {
                System.out.println("You can't jump over your own piece!");
                return false;
            } else return true;
        } else
        {
            System.out.println("You can not move there!");
            return false;
        }
    }

    private Piece pieceInBetween(int xi, int yi, int xf, int yf)
    {
        int deltaX = xf - xi;
        int deltaY = yf - yi;
        return pieceAt(xi + (deltaX / 2), yi + (deltaY / 2));
    }

    private void populateDefaultBoard(Piece[][] pieces)
    {
        // x
        for (int i = 0; i < size; i++)
        {
            // y
            // fire
            for (int j = 0; j < 3; j++)
            {
                if (j == 0) if (i % 2 == 0) place(new Piece(true, this, i, j, "pawn"), i, j);
                if (j == 1) if (i % 2 != 0) place(new Piece(true, this, i, j, "shield"), i, j);
                if (j == 2) if (i % 2 == 0) place(new Piece(true, this, i, j, "bomb"), i, j);
            }
            // water
            for (int j = (size - 3); j < size; j++)
            {
                if (j == (size - 3)) if (i % 2 != 0) place(new Piece(false, this, i, j, "bomb"), i, j);
                if (j == (size - 2)) if (i % 2 == 0) place(new Piece(false, this, i, j, "shield"), i, j);
                if (j == (size - 1)) if (i % 2 != 0) place(new Piece(false, this, i, j, "pawn"), i, j);
            }

        }
    }

    private String imgPath(Piece piece)
    {
        if (piece.isKing()) return "img/" + pieceTypePath(piece) + "-crowned.png";
        else return "img/" + pieceTypePath(piece) + ".png";
    }

    private String pieceTypePath(Piece piece)
    {
        String side = (piece.isFire()) ? "fire" : "water";
        if (piece.isBomb())
        {
            return "bomb-" + side;
        }
        if (piece.isShield())
        {
            return "shield-" + side;
        } else return "pawn-" + side;
    }

    private void drawBoard(int N)
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                // Draw Board
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(CALYELLOW);
                else StdDrawPlus.setPenColor(CALBLUE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                //  Draw Pieces
                if (pieceAt(i, j) != null)
                {
                    Piece thisPiece = pieceAt(i, j);
                    StdDrawPlus.picture(i + .5, j + .5, imgPath(thisPiece), 1, 1);
                }
            }
        }

        // Draw selected square
        if (selectedPiece != null)
        {
            StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
            StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
            Piece thisPiece = pieceAt(selectedX, selectedY);
            if (thisPiece != null) StdDrawPlus.picture(selectedX + .5, selectedY + .5, imgPath(thisPiece), 1, 1);
        }
    }
}