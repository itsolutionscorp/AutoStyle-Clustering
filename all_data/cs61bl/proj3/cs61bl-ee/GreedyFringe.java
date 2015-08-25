import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;

class GreedyFringe extends Fringe {
    private List<Block> goalBlocks;

    private class GreedyComparator implements Comparator<State> {
        public int compare(State s1, State s2) {
            int h1 = s1.distanceToGoal;
            int h2 = s2.distanceToGoal;

            return h1 - h2;
            // if (h1 < h2) {
            //     return -1000;
            // } else if (h1 > h2) {
            //     return 1;
            // } else {
            //     return 0;
            // }

            // int h1 = goalBlocks.size();
            // int h2 = goalBlocks.size();
            // for (Block g : goalBlocks) {
            //     if (s1.containsBlock(g)) {
            //         h1--;
            //     }
            //     if (s2.containsBlock(g)) {
            //         h2--;
            //     }
            // }

            // if (h1 == h2) {
            //     return 0;
            // } else if (h1 < h2) {
            //     return -10;
            // } else {
            //     return 10;
            // }

            // int s1AvgDistToGoal = 0;
            // int s2AvgDistToGoal = 0;

            // for (Block goal : goalBlocks) {
            //     int count = 0;
            //     int subDist = Integer.MAX_VALUE;
            //     for (Block b : s1.getBlocks()) {
            //         if (b.isSameSize(goal) && b.distanceTo(goal) < subDist) {
            //             subDist = b.distanceTo(goal);
            //             count += 1;
            //         }
            //     }
            //     if (count > 0) {
            //         s1AvgDistToGoal += subDist;
            //     } else {
            //         s1AvgDistToGoal += 100;
            //     }


            //     count = 0;
            //     subDist = Integer.MAX_VALUE;
            //     for (Block b : s2.getBlocks()) {
            //         if (b.isSameSize(goal) && b.distanceTo(goal) < subDist) {
            //             subDist = b.distanceTo(goal);
            //             count += 1;
            //         }
            //     }
            //     if (count > 0) {
            //         s2AvgDistToGoal += subDist;
            //     } else {
            //         s2AvgDistToGoal += 100;
            //     }
            // }

            // return s2AvgDistToGoal - s1AvgDistToGoal;
        }
    }

    public GreedyFringe(List<Block> goalBlocks) {
        this.goalBlocks = goalBlocks;
        pq = new PriorityQueue<State>(100, new GreedyComparator());
    }
}
