import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;

class AStarFringe extends Fringe {
    private List<Block> goalBlocks;

    private class AStarComparator implements Comparator<State> {
        public int compare(State s1, State s2) {
            // int s1Heuristic = 0;
            // int s2Heuristic = 0;

            // for (Block g : goalBlocks) {
            //     int s1Min = Integer.MAX_VALUE;
            //     for (Block b : s1.getBlocks()) {
            //         if (b.isSameSize(g)) {
            //             s1Min = Math.min(s1Min, b.distanceTo(g));
            //         }
            //     }
            //     s1Heuristic += s1Min;

            //     int s2Min = Integer.MAX_VALUE;
            //     for (Block b : s2.getBlocks()) {
            //         if (b.isSameSize(g)) {
            //             s2Min = Math.min(s2Min, b.distanceTo(g));
            //         }
            //     }
            //     s1Heuristic += s2Min;
            // }

            int h1 = s1.distanceToGoal;
            int h2 = s2.distanceToGoal;
            int s1MoveCount = s1.getMoveCount();
            int s2MoveCount = s2.getMoveCount();

            return h1 + s1MoveCount - h2 - s2MoveCount;
        }
    }

    public AStarFringe(List<Block> goalBlocks) {
        this.goalBlocks = goalBlocks;
        pq = new PriorityQueue<State>(100, new AStarComparator());
    }
}
