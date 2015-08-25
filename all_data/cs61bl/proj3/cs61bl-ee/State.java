import java.util.List;
import java.util.LinkedList;

class State extends Puzzle {
    // private List<Move> moves;
    private State parentState;
    private Move  move;
    private int moveCount;
    private List<Block> goalBlocks;
    public int distanceToGoal;
    public int rank;

    public State(Puzzle p, List<Block> goalBlocks) {
        super(p);
        // moves = new LinkedList<Move>();
        parentState = null;
        move = null;
        moveCount = 0;
        rank = 0;
        this.goalBlocks = goalBlocks;
        distanceToGoal = 0;
        for (Block g : goalBlocks) {
            for (Block b : p.getBlocks()) {
                if (g.isSameSize(b)) {
                    distanceToGoal += b.manhattanDistanceTo(g);
                }
            }
        }
    }

    public State(State p, Move m) {
        super(p);
        parentState = p;
        move = m;
        moveCount = p.moveCount + 1;
        this.distanceToGoal = p.distanceToGoal;
        this.goalBlocks = p.goalBlocks;
        this.rank = p.rank;
    }

    public void printMoves() {
        if (parentState != null) {
            parentState.printMoves();
            System.out.println(move);
        }
    }

    public int getMoveCount() {
        return moveCount;
    }

    public List<State> expand() {
        List<State> successors = new LinkedList<State>();
        for (Move m : generateValidMoves()) {
            State s = new State(this, m);
            s.applyMove(m);
            Block movedBlock = m.getBlock();
            for (Block g : goalBlocks) {
                if (g.isSameSize(movedBlock)) {
                    s.distanceToGoal += m.directionTo(g);
                    // s.rank += s.distanceToGoal;
                }
            }
            successors.add(s);
        }
        return successors;
    }
}
