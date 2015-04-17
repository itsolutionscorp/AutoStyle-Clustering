/** 
 *  @author David Liu
 *  @sid 25622346
 *  @code AIN
 */
public class Board{

    /** Location of pieces. */
    private Piece[][] pieces = null;

    private boolean startEmptyBoard = false;
    private boolean isEmpty = false;
    private boolean setUpPieces = true;
    private boolean isFireTurn = true;

    private boolean moved = false;
    private boolean hasCaptured = false;
    private boolean canCapture = true;

    private Piece selectedPiece = null;
    private int selectedPieceX = -1;
    private int selectedPieceY = -1;

    private int N = 8;
    private int firePieces = 0;
    private int waterPieces = 0;

    // Add a move counter to track 40-step Stalemate?


    // Constructor
    public Board(boolean shouldBeEmpty){
        this.pieces = new Piece[this.N][this.N];
        if (shouldBeEmpty){
            System.out.println("starting empty board!");
            this.isEmpty = true;
            this.setUpPieces = false;
            startEmptyBoard = true;
        }
        else {
            this.updateBoard();
        }
    }

    // THE HOLY MOTHER OF GOD MAIN METHOD!!!!
    // StdDrawPlus.isNPressed()
    public static void main(String[] args) {

        // Setup for the Board with Pieces.
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, b.N);
        StdDrawPlus.setYscale(0, b.N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while (b.winner() == null) {
                b.updateBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x,(int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                b.endTurn();
            }
            StdDrawPlus.show(10);
        }
        System.out.println(b.winner());
        System.out.println("gameover"); // Keep?asdf
    }

    /* ************************************************************************
        Public Methods
    ***************************************************************************/

    public Piece pieceAt(int x, int y) {
        // Check bounds of the coordinates, then if the board isn't empty and the piece position isn't empty, we're good.
        if (!this.inBounds(x,y)) {
            // System.out.println("Not in Bounds (pieceAt)");
            return null;
        }
        else{
            return this.pieces[x][y];
        }
    }

    public boolean canSelect(int x, int y){
        if ((this.pieceAt(x,y) != null) && (this.pieceAt(x,y).isFire() == (this.isFireTurn))) {
            System.out.println("Selecting a piece of the same side.");
            if (this.selectedPiece == null) {
                System.out.println("Selected was empty, allowed to reassign.");
                return true;
            }
            System.out.println("Selected already exists...");
            if (!this.moved){
                System.out.println("Selected hasn't moved yet. Allowed to reassign.");
                return true;
            }
        }
        

        if ((this.pieceAt(x,y) == null) && this.inBounds(x,y)){
            System.out.println("In Bounds Coordinates. Empty Square.");
            if ((this.selectedPiece != null) && (!this.moved)){
                System.out.println("Piece has been selected, but no move yet.");
                if (this.pieceAt(x,y) == null)  {
                    System.out.println("Empty spot being selected");
                    if (this.isValidMoveSquare(x,y,1)) {
                        System.out.println("Valid Move Square.");
                        return true;
                    }
                    if (this.isValidCaptureSquare(x,y)){
                        System.out.println("Valid Capture Square");
                        return true;
                    }
                }
            }
            if ((this.selectedPiece != null) && (this.hasCaptured)){
                System.out.println("Piece selected and captured.");
                if ((this.pieceAt(x,y) == null) && (this.isValidCaptureSquare(x,y))) {
                    System.out.println("Valid Empty Capture Square.");
                    return true;
                }
            }
        }
        return false;
    }

    public void select(int x, int y){
        // if you haven't move yet and haven't selected piece.
        if ((!this.moved) && (this.pieceAt(x,y) != null) && (this.pieceAt(x,y).isFire() == this.isFireTurn)) {
            System.out.println("Piece Selected.");
            this.selectedPiece = this.pieceAt(x,y);
            this.selectedPieceX = x;
            this.selectedPieceY = y;
        }
        else if ((this.selectedPiece == null)) {
            System.out.println("Can't move if you don't have a selected piece.");
            return;
        }

        // Moving from Square to Square.
        else if (this.isValidMoveSquare(x,y,1) && (!this.moved)) {
            this.selectedPiece.move(x,y);
            this.selectedPieceX = x;
            this.selectedPieceY = y;
            
            System.out.println("Moving."); //asdf
            this.moved = true;
            this.canCapture = false;
        }

        // Capturing, Jumping, and Special Bomb Capturing
        else if (this.isValidCaptureSquare(x,y) && (this.canCapture)) {
            int dumpX = (int) ((selectedPieceX + x)/2);
            int dumpY = (int) ((selectedPieceY + y)/2);

            this.remove(dumpX,dumpY);

            System.out.println("Jumping..."); //asdf
            if (this.selectedPiece.isBomb()) {
                System.out.println("TAKE COVER!!!");
                this.detonate(x+1,y+1);
                this.detonate(x+1,y-1);
                this.detonate(x-1,y-1);
                this.detonate(x-1,y+1);
                this.remove(selectedPieceX,selectedPieceY);
                this.moved = true;
                this.endTurn();
            }
            else {
                this.selectedPiece.move(x,y);
                this.selectedPieceX = x;
                this.selectedPieceY = y;
                this.moved = true;
                this.hasCaptured = true;
            }
            
        }
        System.out.println("You can't select here.");
    }

    public boolean canEndTurn() {
        // if ((this.selectedPiece != null) && (this.selectedPiece.hasCaptured() || this.moved) ) {
        //     return true;
        // }
        // else if (this.selectedPiece == null) && (this.moved){

        // }
        return this.moved || (this.hasCaptured);
    }

    public void endTurn(){
        if (this.canEndTurn()){
            System.out.println("Successfully ended turn.");
            if (this.selectedPiece != null){
                this.selectedPiece.doneCapturing();
            }

            this.isFireTurn = !this.isFireTurn;
            this.moved = false;
            this.hasCaptured = false;
            this.canCapture = true;

            this.selectedPiece = null;
            this.selectedPieceX = -1;
            this.selectedPieceY = -1;

            System.out.println(this.winner());
        }
    }

        public void place(Piece p, int x, int y){
        if ((this.pieceAt(x,y) != null) || !this.inBounds(x,y) || (p == null)) {  // inBounds is redundant.
            System.out.println("Piece already occupying space... OR"); //asdf
            System.out.println("The Index is out of bounds. Cannot place."); //asdf
            System.out.println("(probably not) You didn't create a Piece."); //asdf
            return;
        }

        if (p.isFire()){
            this.firePieces += 1;
        }
        else{
            this.waterPieces += 1;
        }
        this.pieces[x][y] = p;

        // System.out.println("Piece Selected.");
        // this.selectedPiece = p;
        // this.selectedPieceX = x;
        // this.selectedPieceY = y;

        this.startEmptyBoard = false;
        this.isEmpty = false;
        System.out.println("Successfully placed " + this.getType(p) + " at: ");
        System.out.println(x);
        System.out.println(y);
    }

    public Piece remove(int x, int y){
        if (this.pieceAt(x,y) == null) {
            System.out.println("This piece does not exist... OR...");
            return null;
        }

        if (this.pieceAt(x,y).isFire()){
            this.firePieces -= 1;
        }
        else {
            this.waterPieces -= 1;
        }
        if ((this.firePieces + this.waterPieces) == 0){
            this.isEmpty = true;
        }

        this.startEmptyBoard = false;
        Piece removed = this.pieceAt(x,y);
        this.pieces[x][y] = null;
        System.out.println("piece removed.");
        return removed;
    }

    public String winner(){
        System.out.println("calling winner...");
        System.out.println("waterpieces: " + this.waterPieces);
        System.out.println("firePieces: " + this.firePieces);

        if ((this.waterPieces == 0) && (this.firePieces == 0)) {
            return "no one";
        }
        else if (this.waterPieces == 0){
            return "fire";
        }
        else if (this.firePieces == 0) {
            return "water";
        }
        else
            return null;
    }

    /* ************************************************************************
        Custom Accessors Methods
    ***************************************************************************/

    private String getType(Piece p) {
        if (p == null){
            return "";
        }
        else if (p.isBomb()){
            return "bomb";
        }
        else if (p.isShield()){
            return "shield";
        }
        else {
            return "pawn";
        }
    }
    private String getSide(Piece p){
        if (p == null){
            return "";
        }
        else if (p.side() == 0){
            return "fire";
        }
        else{
            return "water";
        }
    }
    private String getStatus(Piece p){
        if (p.isKing()){
            return "-crowned";
        }
        else {
            return "";
        }
    }


    /* ************************************************************************
        Custom Helper Methods
    ***************************************************************************/

    private boolean isValidMoveSquare(int x, int y, int z){
        // Cannot move here if: No selected piece, piece already in existance, coordinates are out of bounds.
        if ((x < 0) || (y < 0) || (z < 0)){
            System.out.println("Bad Boundaries.");
            return false;
        }
        if (((x + y) % 2 == 1) || (this.pieceAt(x,y) == this.selectedPiece) || (this.selectedPiece == null) || (this.pieceAt(x,y) != null)) {
            System.out.println("Can't Move here.");
            return false;
        }
        if ((!(this.isFireTurn) && (selectedPieceY - y == z))  ||  ((this.isFireTurn) && (y - selectedPieceY == z)) && (Math.abs(x - selectedPieceX) == z) ) {
            System.out.println("Valid Move Square.");
            return true;
        }
        else if (this.selectedPiece.isKing() && (Math.abs(this.selectedPieceY - y) == z) ) {
            System.out.println("valid king");
            return true;
        }
        System.out.println(y);
        System.out.println(this.selectedPieceY);
        System.out.println("SY - y: " + (this.selectedPieceY - y));
        System.out.println("z: " + z);
        System.out.println("Can't move back or skip spaces.");
        return false;
    }

    private boolean isValidCaptureSquare(int x, int y) {
        boolean readyToMove = this.isValidMoveSquare(x,y,2);
        if (readyToMove){
            System.out.println("Ready To Move.");
            System.out.println((this.selectedPieceX + x)/2);
            System.out.println((this.selectedPieceY + y)/2);

            Piece checkSide = this.pieceAt( (int) ((this.selectedPieceX + x)/2), (int) ((this.selectedPieceY + y)/2) );
            if ((checkSide != null) && (checkSide.side() != selectedPiece.side())) {
                System.out.println("Valid Capture Square.");
                return true;
            }
            System.out.println("Selection is nonvalid Capture square.");
        }
        // System.out.println("Invalid CaptureSquare.");
        return false;
    }

    private boolean canMoveOrCapture(int x, int y){
        // Find the i +/- 1 and j +/- 1 of this coordinate
        // If those coordinates are in bounds and empty, return the boolean of valid spots.
        if (this.pieceAt(x,y) == null) {
            return false;
        }

        boolean captureNE = (this.isValidCaptureSquare(x+2,y+2));
        boolean captureSE = (this.isValidCaptureSquare(x+2,y-2));
        boolean captureSW = (this.isValidCaptureSquare(x-2,y-2));
        boolean captureNW = (this.isValidCaptureSquare(x-2,y+2));
        boolean hasCapture = captureNE || captureNW || captureSE || captureSW;

        boolean canMove = false;
        boolean movetopRight = (this.inBounds(x+1,y+1) && (this.pieceAt(x+1,y+1) == null));
        boolean movebottomRight = (this.inBounds(x+1,y-1) && (this.pieceAt(x+1,y-1) == null));
        boolean movetopLeft = (this.inBounds(x-1,y+1) && (this.pieceAt(x-1,y+1) == null));
        boolean movebottomLeft = (this.inBounds(x-1,y-1) && (this.pieceAt(x-1,y-1) == null));

        String s = this.getSide(this.pieceAt(x,y));
        if (s.equals("")){
            return false;
        }
        else if (s.equals("water")){
            canMove = movebottomLeft || movebottomRight;
        }
        else if (s.equals("fire")){
            canMove = movetopLeft || movetopRight;
        }
        else if (this.pieceAt(x,y).isKing()){
            canMove = movetopLeft || movetopRight || movebottomRight || movebottomLeft;
        }

        if (canMove || hasCapture) {
            System.out.println("Yes this returns true.");
        }
        return canMove || hasCapture;
    }

    private boolean inBounds(int x, int y){
        if ((x > (this.N -1)) || (y > (this.N-1)) || (y < 0) || (x < 0)){
            return false;
        }
        return true;
    }

    private void detonate(int x,int y){
        if ((this.pieceAt(x,y) != null) && (!this.pieceAt(x,y).isShield())) {
            if (this.pieceAt(x,y).isFire()){
                this.firePieces -= 1;
            }
            else {
                this.waterPieces -= 1;
            }
            this.remove(x,y);
            System.out.println("Boom!");
        }
    }

    private boolean noValidMoves(){
    // If this piece can be selected, (correct side) and if it has move/capture, then valids exist.
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if ((this.pieceAt(i,j) != null) && this.canMoveOrCapture(i,j) && (this.pieceAt(i,j).isFire() == this.isFireTurn)) {
                    return false;
                }
            }
        }
        System.out.println("No Valid Moves.");
        return true;
    }

    /* ************************************************************************
        Drawing Pieces and the Board
    ***************************************************************************/

    
    /** Initializes the pieces for the board. */
    // private void startEmptyBoard(){

    // }

    private void startBoard(int i, int j) {
        if (this.setUpPieces){
            if (j == 0){
                this.pieces[i][j] = new Piece(true, this, i, j, "pawn");
                this.firePieces += 1;
            }
            if (j == 1){
                this.pieces[i][j] = new Piece(true, this, i, j, "shield");
                this.firePieces += 1;
            }
            if (j == 2){
                this.pieces[i][j] = new Piece(true, this, i, j, "bomb");
                this.firePieces += 1;
            }
            if (j == 5){
                this.pieces[i][j] = new Piece(false, this, i, j, "bomb");
                this.waterPieces += 1;
            }
            if (j == 6){
                this.pieces[i][j] = new Piece(false, this, i, j, "shield");
                this.waterPieces += 1;
            }
            if (j == 7){
                this.pieces[i][j] = new Piece(false, this, i, j, "pawn");
                this.waterPieces += 1;
            }
        }
    }

    /* Updates the Board Each Turn. */
    private void updateBoard() {
        this.waterPieces = 0;
        this.firePieces = 0;
        StdDrawPlus.setXscale(0, this.N);
        StdDrawPlus.setYscale(0, this.N);
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {

                // Fill in the board.
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                    // If this is the start of a real game, initialize pieces.
                    this.startBoard(i,j);
                }
                // Color in Non-playable Squares.
                else {
                    this.pieces[i][j] = null;
                    StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                }

                // Special Case for Selected Tile
                if ((this.selectedPiece != null) && (this.pieces[i][j] == this.selectedPiece)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                }

                // Draw the square.
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                // Draw each piece on the Board. Board must exist, piece must exist, and must not be a "neutral" piece (default);
                if ((!this.isEmpty) && (this.pieces[i][j] != null)) {
                    this.drawPiece(this.pieces[i][j],i,j);
                }
            }
        }
        this.setUpPieces = false;
    }

    /* Draws each piece on the board (Called by drawBoard) */
    private void drawPiece(Piece p, int x, int y) {
        if (p != null) {
            String mytype = this.getType(p);
            String myside = this.getSide(p);
            String mystatus = this.getStatus(p);

            if (myside.equals("fire")) {
                this.firePieces += 1;
            }
            else if (myside.equals("water")) {
                this.waterPieces += 1;
            }
            StringBuilder dir = new StringBuilder("img/");
            dir.append(mytype);
            dir.append("-");
            dir.append(myside);
            dir.append(mystatus);
            dir.append(".png");

            // System.out.println(dir); //asdfasdf
            StdDrawPlus.picture(x + .5, y + .5, "img/" + mytype + "-" + myside + mystatus + ".png", 1, 1);
        }
        
    }

    /* ************************************************************************************
    WARNING: ENTERING TESTING ZONE. THESE METHODS SHOULD ***ONLY*** BE FOR TESTING PURPOSES.
    ***************************************************************************************/

    // public void update(){
    //     this.updateBoard(); //asdf
    // }
    
}