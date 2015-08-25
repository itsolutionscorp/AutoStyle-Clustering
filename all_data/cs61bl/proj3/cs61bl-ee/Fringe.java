import java.util.PriorityQueue;
abstract class Fringe {
    protected PriorityQueue<State> pq;

    public void add(State s) {
        pq.add(s);
    }

    public State removeFront() {
        return pq.poll();
    }

    public boolean isEmpty() {
        return pq.isEmpty();
    }

    public State[] toArray() {
        return pq.toArray(new State[pq.size()]);
    }
}
