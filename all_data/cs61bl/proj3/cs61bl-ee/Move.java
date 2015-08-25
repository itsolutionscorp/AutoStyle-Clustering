class Move {
    public enum Direction {UP, DOWN, LEFT, RIGHT};

    private Block block;
    private Direction direction;

    public Move(Block b, Direction d) {
        block = b;
        direction = d;
    }

    public Block getBlock() {
        return block;
    }

    public Direction getDirection() {
        return direction;
    }

    public int directionTo(Block other) {
        int gX = other.getTopLeftX();
        int gY = other.getTopLeftY();

        int thisX = block.getTopLeftX();
        int thisY = block.getTopLeftY();

        switch(direction) {
            case UP:
                if (gY < thisY) {
                    return -1;
                } else {
                    return 1;
                }
            case DOWN:
                if (gY > thisY) {
                    return -1;
                } else {
                    return 1;
                }
            case LEFT:
                if (gX < thisX) {
                    return -1;
                } else {
                    return 1;
                }
            case RIGHT:
                if (gX > thisX) {
                    return -1;
                } else {
                    return 1;
                }
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        int finalY = block.getTopLeftY();
        int finalX = block.getTopLeftX();
        switch(direction) {
            case UP:
                finalY -= 1;
                break;
            case DOWN:
                finalY += 1;
                break;
            case LEFT:
                finalX -= 1;
                break;
            case RIGHT:
                finalX += 1;
                break;
        }
        return String.format("%d %d %d %d", block.getTopLeftY(),
                                            block.getTopLeftX(),
                                            finalY,
                                            finalX);
    }
}
