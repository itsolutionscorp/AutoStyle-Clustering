/** 
 *  @author JiaHang Li
 */

public class Board {
  /**
   *  Board Class
   */
  private Piece[][] pieces;
  private int player;
  private Piece selected;
  private boolean moved;
  private int fire, water;
  private int selectedX, selectedY;

  public static void main(String[] args) {
    StdDrawPlus.setXscale(0, 8);
    StdDrawPlus.setYscale(0, 8);
    Board b = new Board(false);

    while (true) {
      b.drawBoard();
      if (StdDrawPlus.mousePressed()) {
        int x = (int) StdDrawPlus.mouseX();
        int y = (int) StdDrawPlus.mouseY();
        if (b.canSelect(x, y))
          b.select(x, y);
      }
      if (StdDrawPlus.isSpacePressed()) {
        b.endTurn();
      }
      if (b.winner() != null) {
        return;
      }
      StdDrawPlus.show(100);
    }
  }

  private void drawBoard() {
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        Piece piece = pieceAt(x, y);

        if (piece != null && piece == selected)
          StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
        else if (x == (int) StdDrawPlus.mouseX() && y == (int) StdDrawPlus.mouseY())
          StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
        else if (this.canSelect(x, y) && (this.selected == null || pieceAt(x, y) == null))
          StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
        else if ((x + y) % 2 == 0) 
          StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else
          StdDrawPlus.setPenColor(StdDrawPlus.BLACK);

        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        
        if (piece != null) {
          String img = "img/" + (piece.isBomb() ? "bomb" : (piece.isShield() ? "shield" : "pawn")) + "-" + (piece.isFire() ? "fire" : "water") + (piece.isKing() ? "-crowned" : "") + ".png";
          StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);
        }
      }
    }
  }

  public Board(boolean shouldBeEmpty) {
    pieces = new Piece[8][8];
    player = 0;
    selected = null;
    fire = water = 0;
    selectedX = selectedY = 0;
    moved = false;

    Piece piece;

    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        if (!shouldBeEmpty)
          place(createPieceAt(x, y), x, y);
        else 
          pieces[x][y] = null;
      }
    }
  }

  private Piece createPieceAt(int x, int y) {
    if (x < 0 || y < 0)
      return null;
    else if (y == 0 && x % 2 == 0)
      return new Piece(true, this, x, y, "pawn");
    else if (y == 1 && x % 2 == 1)
      return new Piece(true, this, x, y, "shield");
    else if (y == 2 && x % 2 == 0)
      return new Piece(true, this, x, y, "bomb");
    else if (y == 5 && x % 2 == 1)
      return new Piece(false, this, x, y, "bomb");
    else if (y == 6 && x % 2 == 0)
      return new Piece(false, this, x, y, "shield");
    else if (y == 7 && x % 2 == 1)
      return new Piece(false, this, x, y, "pawn");
    else return null;
  }

  private boolean outOfBound(int x, int y) {
    return (x >= 8 || y >= 8 || x < 0 || y < 0);
  }

  public Piece pieceAt(int x, int y) {
    if (outOfBound(x, y))
      return null;
    return pieces[x][y];
  }

  public boolean canSelect(int x, int y) {
    Piece piece = pieceAt(x, y);

    if (selected == null) {
      if (piece == null)
        return false;
      else if (piece.side() != player)
        return false;
      else return true;
    } else {
      if (piece == null) {
        if (validMove(selectedX, selectedY, x, y)) {
          if (selected.hasCaptured() && Math.abs(selectedX - x) == 2)
            return true;
          else return !moved;
        } else return false;
      } else if (piece.side() == player)
        return !moved; 
      else return false;
    }
  }

  private boolean validMove(int xi, int yi, int xf, int yf) {
		int xm = (xi + xf) >>> 1;
		int ym = (yi + yf) >>> 1;
    Piece piece = pieceAt(xm, ym);
    int direction = selected.isFire() ? 1 : -1;
    if (selected.isKing()) {
      if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1)
        return true;
      else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
        if (piece == null)
          return false;
        else if (piece.side() != player)
          return true;
        else return false;
      } else
        return false;
    } else {
      if (Math.abs(xi - xf) == 1 && yi - yf == -1 * direction)
        return true;
      else if (Math.abs(xi - xf) == 2 && yi - yf == -2 * direction) {
        if (piece == null)
          return false;
        else if (piece.side() != player)
          return true;
        else
          return false;
      } else
        return false;
    }
  }

  public void select(int x, int y) {
    Piece piece = pieceAt(x, y);
    selectedX = x;
    selectedY = y;

    if (piece == null) {
      moved = true;
      selected.move(x, y);
    } else
      selected = piece;
  }

  public void place(Piece p, int x, int y) {
    if (outOfBound(x, y) || p == null)
      return;
    if (pieces[x][y] != null)
      remove(x, y);
    pieces[x][y] = p;
    if (p.isFire()) fire++;
    else water++;
  }

  public Piece remove(int x, int y) {
    if (outOfBound(x, y))
      return null;

    Piece p = pieces[x][y];
    pieces[x][y] = null;
    if (p != null) {
      if (p.isFire()) fire--;
      else water--;
    }
    return p;
  }

  public boolean canEndTurn() {
    return selected != null && moved;
  }

  public void endTurn() { 
    if (canEndTurn()) {
      player = (1 - player);
      moved = false;
      selected.doneCapturing();
      selected = null;
      selectedX = selectedY = 0;
    }
  }

  public String winner() {
    if (fire == 0 && water != 0)
      return "Water";
    else if (water == 0 && fire != 0)
      return "Fire";
    else if (water == 0 && fire == 0)
      return "No one";
    else return null;
  }
}
