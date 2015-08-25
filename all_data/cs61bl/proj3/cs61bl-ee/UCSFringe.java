import java.util.Comparator;
import java.util.PriorityQueue;

class UCSFringe extends Fringe {

    private class UCSComparator implements Comparator<State> {
        public int compare(State s1, State s2) {
            int s1Cost = s1.getMoveCount();
            int s2Cost = s2.getMoveCount();
            return s1Cost - s2Cost;
        }
    }

    public UCSFringe() {
        pq = new PriorityQueue<State>(100, new UCSComparator());
    }
}
