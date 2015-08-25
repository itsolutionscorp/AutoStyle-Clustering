class Block {
    private Cell[] cells; // Store where this block occupies
    private int topLeftY;
    private int topLeftX;
    private int bottomRightY;
    private int bottomRightX;

    public Block(int topY, int topX, int bottomY, int bottomX) {
        topLeftY = topY;
        topLeftX = topX;
        bottomRightY = bottomY;
        bottomRightX = bottomX;

        // Calculate how many cells we need
        cells = new Cell[(bottomY - topY + 1) * (bottomX - topX + 1)];

        // Create cells
        int counter = 0;
        for (int y = topY; y <= bottomY; y++) {
            for (int x = topX; x <= bottomX; x++) {
                cells[counter] = new Cell(x, y);
                counter += 1;
            }
        }
    }

    public Cell[] getCells() {
        return cells;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getBottomRightY() {
        return bottomRightY;
    }

    public int getBottomRightX() {
        return bottomRightX;
    }

    public int getWidth() {
        return bottomRightX - topLeftX + 1;
    }

    public int getHeight() {
        return bottomRightY - topLeftY + 1;
    }

    public boolean isSameSize(Block other) {
        return getWidth() == other.getWidth() && getHeight() == other.getHeight();
    }

    public int manhattanDistanceTo(Block other) {
        int yDelta = Math.abs(getTopLeftY() - other.getTopLeftY());
        int xDelta = Math.abs(getTopLeftX() - other.getTopLeftX());
        return yDelta + xDelta;
    }

    public int distanceTo(Block other) {
        int a = getTopLeftX() - other.getTopLeftX();
        int b = getTopLeftY() - other.getTopLeftY();
        return (int) Math.sqrt(a * a + b * b);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Block) {
            Block other = (Block) o;
            if (this.cells.length != other.cells.length) {
                return false;
            } else {
                for (int i = 0; i < this.cells.length; i++) {
                    if (!this.cells[i].equals(other.cells[i])) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for (Cell c : cells) {
            hash = hash * 17 + c.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Block: [ ");
        for (Cell cell : cells) {
            sb.append(cell.toString() + " ");
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println("Test");
        System.out.println("-----------------------");
        Block b = new Block(0, 0, 1, 0);
        System.out.println(b);
        b = new Block(1, 1, 2, 2);
        System.out.println(b);
    }
}
