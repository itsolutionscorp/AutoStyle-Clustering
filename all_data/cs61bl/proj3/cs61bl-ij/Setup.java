import java.util.*;
import java.io.*;
 
public class Setup implements Comparable<Setup> {
    public static Solver owner;
     
    private short[][] pieces;
    private short[][] board; 
    public Double value;
    private Setup parent;
    private ArrayList<Setup> nextSetups = new ArrayList<Setup>();
    private boolean[] potentials;
    public int movedDirection;
    public Short movedIndex;
    public Integer hashed;
     
    public Setup(BufferedReader inputLines, int[] size, boolean isInitial) throws Exception {
        value = 1.0;
        String line;
        String[] lineSplit;
        short[] intSplit;
        this.board = new short[size[0]][size[1]];
        short currentPiece = 1;
        int currentX;
        int currentY;
        ArrayList<short[]> tempPieces = new ArrayList<short[]>();
        while ((line = inputLines.readLine()) != null) {
            if (line.equals("")) {
                while ((line = inputLines.readLine()) != null) {
                    if (!line.equals("")) Solver.crash();
                }
                break;
            }
            lineSplit = line.split(" ");
            if (lineSplit.length != 4) Solver.crash();
            intSplit = new short[]{Short.parseShort(lineSplit[0]), Short.parseShort(lineSplit[1]), Short.parseShort(lineSplit[2]), Short.parseShort(lineSplit[3])};
            if (intSplit[0] > intSplit[2] || intSplit[1] > intSplit[3]) Solver.crash();
            tempPieces.add(intSplit);
            currentX = intSplit[0];
            currentY = intSplit[1];
            while (currentX <= intSplit[2]) {
                while (currentY <= intSplit[3]) {
                    if (board[currentX][currentY] != 0) Solver.crash();
                    board[currentX][currentY] = currentPiece;
                    currentY++;
                }
                currentY = intSplit[1];
                currentX++;
            }
            currentPiece++;
        }
        pieces = new short[tempPieces.size()][];
        for (int i = 0; i < tempPieces.size(); i++) {
            pieces[i] = tempPieces.get(i);
        }
        potentials = new boolean[pieces.length+1];
        if (isInitial) {
            for (int i = 0; i < size[0]; i ++) {
                for (int j =0; j < size[1]; j ++) {
                    if (board[i][j] == 0) {
                        addAdjacents(i, j);
                    }
                }
            }
        }
        this.parent = null;
        this.movedIndex = 1;
        this.movedDirection = -1;
    }
     
    public Setup (short[][] pieces, short[][] board, Double v, Setup p, short movedIndex, int movedDirection) {
         
        this.pieces = pieces;
        this.board = board;
        this.value = v;
        this.parent = p;
        this.movedIndex = movedIndex;
        this.movedDirection = movedDirection;
        potentials = new boolean[pieces.length+1];
    }
     
     
    public Setup makeSetup(short index, int direction) {
        short[][] newPieces = new short[pieces.length][4];
        for (int i = 0; i < pieces.length; i++) {
            newPieces[i] = Arrays.copyOf(pieces[i], pieces[i].length);
        }
        short[][] newBoard = new short[board.length][board[0].length];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (board[x][y] != index) {
                    newBoard[x][y] = board[x][y];
                }
            }
        }
        short[] moved = new short[4];
        System.arraycopy(pieces[index-1], 0, moved, 0, 4);
        switch (direction) {
        case UP:
            moved[0] --;
            moved[2] --;
            break;
        case DOWN:
            moved[0] ++;
            moved[2] ++; 
            break;
        case LEFT:
            moved[1] --;
            moved[3] --;
            break;
        case RIGHT:
            moved[1] ++;
            moved[3] ++;
            break;
        }
        newPieces[index-1] = moved;
        for (int x = moved[0]; x <= moved[2]; x ++) {
            for (int y = moved[1]; y <= moved[3]; y++) {
                newBoard[x][y] = index;
            }
        }
         
        double v = value;
        Setup goal = owner.goal;
        for (int index2 = 0; index2 < newPieces.length; index2 ++) {
            short[] piece = newPieces[index2];
            short pieceNum = goal.board[piece[0]][piece[1]];
            if (pieceNum == 0) continue;
            short[] anotherPiece = goal.pieces[pieceNum-1];
            if (Arrays.equals(piece, anotherPiece)) {
                v -= 0.0001;
            }
        }
        if (index == movedIndex) {
            if (direction == movedDirection) {
                v -= 0.00001;
            }
        }
         
        return new Setup(newPieces, newBoard, v, this, index, direction);
    }
 
 
    public void updateNexts() throws Exception {
        for (short i = 0; i < potentials.length; i++) {
            if (potentials[i] == true) {
                move(i);
            }
        }
    }
 
    public void updatePotentials() {
        if (parent != null) {
            for (Setup sibling: parent.nextSetups) {
                potentials[sibling.movedIndex] = true;
            }
            short[] movedPiece = pieces[movedIndex-1];
            switch (movedDirection) {
            case RIGHT:
                for (int x = movedPiece[0]; x <= movedPiece[2]; x ++) {
                    addAdjacents(x, movedPiece[1]-1);
                }
                break;
            case LEFT:
                for (int x = movedPiece[0]; x <= movedPiece[2]; x ++) {
                    addAdjacents(x, movedPiece[3]+1);
                }
                break;
            case DOWN:
                for (int y = movedPiece[1]; y <= movedPiece[3]; y ++) {
                    addAdjacents(movedPiece[0]-1, y);
                }
                break;
            case UP:
                for (int y = movedPiece[1]; y <= movedPiece[3]; y ++) {
                    addAdjacents(movedPiece[2]+1, y);
                }
                break;
            }
        }
    }
     
    public void addAdjacents(int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return;
            if (x-1 >= 0 && board[x-1][y] != 0) {
                potentials[board[x-1][y]] = true;
            }
            if (y-1 >= 0 && board[x][y-1] != 0) {
                potentials[board[x][y-1]] = true;
            }
            if (x+1 < board.length && board[x+1][y] != 0) {
                potentials[board[x+1][y]] = true;
            }
            if (y+1 < board[0].length && board[x][y+1] != 0) {
                potentials[board[x][y+1]] = true;
            }
    }
 
    public void move(short i) throws Exception {
        short[] piece = pieces[i-1];
        if (movedIndex != null && movedIndex.equals(i)) {
            if (movedDirection != DOWN && canMoveUp(piece)) {
                nextSetups.add(makeSetup(i, UP));
            }
            if (movedDirection != UP && canMoveDown(piece)) {
                nextSetups.add(makeSetup(i, DOWN));
            }
            if (movedDirection != RIGHT && canMoveLeft(piece)) {
                nextSetups.add(makeSetup(i, LEFT));
            }
            if (movedDirection != LEFT && canMoveRight(piece)) {
                nextSetups.add(makeSetup(i, RIGHT));
            }
        } else {
            if (canMoveUp(piece)) {
                nextSetups.add(makeSetup(i, UP));
            }
            if (canMoveDown(piece)) {
                nextSetups.add(makeSetup(i, DOWN));
            }
            if (canMoveLeft(piece)) {
                nextSetups.add(makeSetup(i, LEFT));
            }
            if (canMoveRight(piece)) {
                nextSetups.add(makeSetup(i, RIGHT));
            }
        }
    }
     
    public boolean canMoveLeft(short[] piece) {
        if (piece[1]-1 < 0) return false;
            for (int i = piece[0]; i <= piece[2]; i++) {
                if (board[i][piece[1]-1] != 0) {
                    return false;
                }
            }
            return true;
    }
      
    public boolean canMoveRight(short[] piece) {
        if (piece[3]+1 >= board[0].length) return false;
            for (int i = piece[0]; i <= piece[2]; i++) {
                if (board[i][piece[3]+1] != 0) {
                    return false;
                }
            }
            return true;
    }
      
    public boolean canMoveUp(short[] piece) {
        if (piece[0]-1 < 0) return false;
            for (int i = piece[1]; i <= piece[3]; i++) {
                if (board[piece[0]-1][i] != 0) {
                    return false;
                }
            }
            return true;
    }
      
    public boolean canMoveDown(short[] piece) {
        if (piece[2]+1 >= board.length) return false;
            for (int i = piece[1]; i <= piece[3]; i++) {
                if (board[piece[2]+1][i] != 0) {
                    return false;
                }
            }
            return true;
    }
     
    private final int LEFT = 0;
    private final int RIGHT = 1;
    private final int UP = 2;
    private final int DOWN = 3;
     
 
     
     
    public short[][] pieces() {
        return pieces;
    }
     
    public short[][] board(){
        return board;
    }
 
 
    public Setup parent() {
        return parent;
    }
 
    public ArrayList<Setup> nextSetups() throws Exception {
        updateNexts();
        return nextSetups;
    }
     
    @Override // need change
    public boolean equals(Object obj) {
        Setup another = (Setup) obj;
        for (int index = 0; index < pieces.length; index ++) {
            short[] piece = pieces[index];
            int pieceNum = another.board[piece[0]][piece[1]];
            if (pieceNum == 0) return false;
            short[] anotherPiece = another.pieces[pieceNum-1];
            if (!Arrays.equals(piece, anotherPiece))
                return false;
        }
        return true;
    }
     
    @Override
    public int compareTo(Setup obj) {
        return value.compareTo(((Setup) obj).value);
    }
     
    @Override // Add all top left coordinates
    public int hashCode() {
        if (hashed !=  null)
            return hashed;
        int toReturn = 0;
        for (short[] i : pieces) {
            toReturn += i[0] + i[1];
        }
        hashed = toReturn;
        return toReturn;
    }
     
    @Override
    public String toString() {
        short[] prevPos = parent.pieces[movedIndex-1];
        short[] currPos = pieces[movedIndex-1];
 
        return prevPos[0] + " " + prevPos[1] + " " + currPos[0] + " " + currPos[1];
    }
 
}