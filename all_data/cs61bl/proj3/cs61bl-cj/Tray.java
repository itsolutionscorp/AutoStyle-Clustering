import java.util.HashSet;

public class Tray implements Comparable{

    private Piece[][] board;
    private HashSet<Piece> myPieces;
    private int maxX;
    private int maxY;
    private HashSet<Piece> goalPieces;
    String move;

    public Tray(int n, int m) {
        board = new Piece[n][m];
        maxX = n; maxY = m;
    }

    public Tray(Tray initialTray) {
        maxX = initialTray.maxX;
        maxY = initialTray.maxY;
        myPieces = new HashSet<Piece>(); // create totally new object
        board = new Piece[maxX][maxY];
        goalPieces = initialTray.goalPieces;

        for (Piece p : initialTray.myPieces) { // Doing copy
            Piece newPiece = new Piece(p.getTopLeftX(), p.getTopLeftY(), p.getBottomRightX(), p.getBottomRightY());
            myPieces.add(newPiece);
            for (int i = newPiece.getTopLeftX(); i < newPiece.getBottomRightX() + 1; i++) {
                for (int j = newPiece.getTopLeftY(); j < newPiece.getBottomRightY() + 1; j++) {
                    board[i][j] = newPiece;
                }
            }
        }
    }

    public Tray(int n, int m, HashSet<Piece> pieces, HashSet<Piece> goalPieces) {

        board = new Piece[n][m];
        myPieces = pieces;
        maxX = n; maxY = m;
        this.goalPieces = goalPieces;

        for (Piece p : pieces) {
            for (int i = p.getTopLeftX(); i < p.getBottomRightX() + 1; i++) {
                for (int j = p.getTopLeftY(); j < p.getBottomRightY() + 1; j++) {
                    board[i][j] = p;
                }
            }
        }
    }

    public boolean canMove(Piece p) {
        int up = canMoveUp(p);
        int down = canMoveDown(p);
        int left = canMoveLeft(p);
        int right = canMoveRight(p);
        if (up == 0 && down == 0 && left == 0 && right == 0) {
            return false;
        }
        return true;
    }

    public int canMoveUp(Piece p) {
        if (p.getTopLeftX() == 0) {
            return 0;
        }

        for (int j = p.getBottomRightY(); j >= p.getTopLeftY(); j--) {
            if (board[p.getTopLeftX() - 1][j] != null) {
                return 0;
            }
        }
        return 1;
    }

    public int canMoveDown(Piece p) {
        if (p.getBottomRightX() == maxX - 1) {
            return 0;
        }

        for (int j = p.getBottomRightY(); j >= p.getTopLeftY(); j--) {
            if (board[p.getBottomRightX() + 1][j] != null) {
                return 0;
            }
        }

        return 1;
    }

    public int canMoveLeft(Piece p) {
        if (p.getTopLeftY() == 0) {
            return 0;
        }

        for (int i = p.getBottomRightX(); i > p.getTopLeftX() - 1; i--) {
            if (board[i][p.getTopLeftY() - 1] != null) {
                return 0;
            }
        }

        return 1;
    }

    public int canMoveRight(Piece p) {
        if (p.getBottomRightY() == maxY - 1) {
            return 0;
        }

        for (int i = p.getBottomRightX(); i > p.getTopLeftX() - 1; i--) {
            if (board[i][p.getBottomRightY() + 1] != null) {
                return 0;
            }
        }

        return 1;
    }

    public Tray moveUpOrDown(Piece p, int space) {

        Tray toReturn = new Tray(this);
        Piece pCopy = new Piece(p);

        for (int i = p.getTopLeftX(); i <= p.getBottomRightX(); i++) {
            for (int j = p.getTopLeftY(); j <= p.getBottomRightY(); j++) {
                toReturn.board[i][j] = null;
            }
        }

        for (int i = p.getTopLeftX() - space; i <= p.getBottomRightX() - space; i++) {
            for (int j = p.getBottomRightY(); j >= p.getTopLeftY(); j--) {
                toReturn.board[i][j] = pCopy;
            }
        }

        // need to modify myPieces in new return Tray
        toReturn.myPieces.remove(pCopy); // make sure remove the correct piece
        pCopy.setTopLeftX(pCopy.getTopLeftX() - space);
        pCopy.setBottomRightX(pCopy.getBottomRightX() - space);
        toReturn.myPieces.add(pCopy);
        return toReturn;

    }

    // left = 1; right = -1
    public Tray moveLeftOrRight(Piece p, int space) {
        // Tray toReturn = this;
        Tray toReturn = new Tray(this);
        Piece pCopy = new Piece(p);

        for (int i = p.getTopLeftX(); i <= p.getBottomRightX(); i++) {
            for (int j = p.getTopLeftY(); j <= p.getBottomRightY(); j++) {
                toReturn.board[i][j] = null;
//                if(space==1)
//                    System.out.println("i  "+i+"j  "+j);  //TODO
            }
        }

        for (int i = p.getTopLeftX(); i <= p.getBottomRightX(); i++) {
//            System.out.println("did i go here");   // TODO
            for (int j = p.getTopLeftY() - space; j <= p.getBottomRightY() - space; j++) {
                toReturn.board[i][j] = pCopy;
//                if(space==1)
//                    System.out.println("i     "+i+"j     "+j);  //TODO
            }
        }
        toReturn.myPieces.remove(pCopy); // make sure remove the correct piece
        pCopy.setTopLeftY(pCopy.getTopLeftY() - space);
        pCopy.setBottomRightY(pCopy.getBottomRightY() - space);
        toReturn.myPieces.add(pCopy);
        return toReturn;
    }

    public String getMoveString() {
        return move;
    }

    public HashSet<Tray> move(Piece p) {

        HashSet<Tray> possibleTrays = new HashSet<Tray>();

        if (canMoveUp(p) != 0) {
//            System.out.println("moving up");   // TODO
            int initialTopLeftX = p.getTopLeftX();
            int initialTopLeftY = p.getTopLeftY();

            int finalTopLeftX = initialTopLeftX - 1;
            int finalTopLeftY = initialTopLeftY;

            Tray newTray = this.moveUpOrDown(p, 1);
            newTray.move = initialTopLeftX + " " + initialTopLeftY + " " + finalTopLeftX + " " + finalTopLeftY;
            
            possibleTrays.add(newTray);

        }
        if (canMoveLeft(p) != 0) {
//            System.out.println("moving left");   // TODO
            int initialTopLeftX = p.getTopLeftX();
            int initialTopLeftY = p.getTopLeftY();

            int finalTopLeftX = initialTopLeftX;
            int finalTopLeftY = initialTopLeftY - 1;

            Tray newTray = this.moveLeftOrRight(p, 1);
            newTray.move = initialTopLeftX + " " + initialTopLeftY + " " + finalTopLeftX + " " + finalTopLeftY;
            
            possibleTrays.add(newTray);

        }
        
        if (canMoveDown(p) != 0) {
//            System.out.println("moving down");   // TODO
            int initialTopLeftX = p.getTopLeftX();
            int initialTopLeftY = p.getTopLeftY();

            int finalTopLeftX = initialTopLeftX + 1;
            int finalTopLeftY = initialTopLeftY;

            Tray newTray = this.moveUpOrDown(p, -1);
            newTray.move = initialTopLeftX + " " + initialTopLeftY + " " + finalTopLeftX + " " + finalTopLeftY;
            
            possibleTrays.add(newTray);

        }

        
        if (canMoveRight(p) != 0) {
//            System.out.println("moving right");   // TODO
            int initialTopLeftX = p.getTopLeftX();
            int initialTopLeftY = p.getTopLeftY();

            int finalTopLeftX = initialTopLeftX;
            int finalTopLeftY = initialTopLeftY + 1;

            Tray newTray = this.moveLeftOrRight(p, -1);
            newTray.move = initialTopLeftX + " " + initialTopLeftY + " " + finalTopLeftX + " " + finalTopLeftY;

            possibleTrays.add(newTray);
            
        }

        return possibleTrays;
    }

    public HashSet<Piece> myPieces() {
        return myPieces;
    }

    public boolean checkFinal(HashSet<Piece> goal) {
        for (Piece p : goal) {
            if (!myPieces.contains(p)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = 0;
        for (Piece p : myPieces) {
            result += p.hashCode();
        }
        return result;
    }

    public String toString() {
        String result = "";
        for (Piece p : myPieces) {
            result += p.toString() + "\n";
        }
        return result;
    }
    
    public int maxX(){
    	return maxX;
    }
    
    public int maxY(){
    	return maxY;
    }
    
    public Piece pieceAt(int x, int y){
    	return board[x][y];
    }

    public boolean equals(Object o) {
        Tray t = (Tray) o;
        if (maxX != t.maxX || maxY != t.maxY)
            return false;
        if (myPieces.size() != t.myPieces.size())
            return false;
        if (!t.myPieces.containsAll(myPieces))
            return false;
        return true;
    }
    
    public int rankMe(HashSet<Piece> goalPieces) {

        int count = 0;
        for(Piece p : myPieces) {
        	if (goalPieces.contains(p)) {
        		count++;
        	}
        }
        return count;
    }

    
    public int rankMe2(HashSet<Piece> goalPieces) {
    	int count = 0;
    	
    	
    	
    	return count;
    }
        
    @Override
    public int compareTo(Object otherObject) {
    	Tray otherTray = (Tray) otherObject;
        if (this.rankMe(goalPieces) < otherTray.rankMe(goalPieces)) {
        	return -1;
        } else if (this.rankMe(goalPieces) == otherTray.rankMe(goalPieces)) {
        	return 0;
        } else {
        	return 1;
        }
    }

}