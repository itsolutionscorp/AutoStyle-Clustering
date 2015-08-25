public class Edge {
	Move move;
	Tray prevTray;
	Tray currentTray;
	double cost;

	public Edge(Tray pre, Tray nex, Move motion) {
		prevTray = pre;
		currentTray = nex;
		move = motion;
		cost = nex.cost;
	}

	@Override
	public int hashCode() {
		return prevTray.hashCode() + currentTray.hashCode() + move.hashCode();
	}

	@Override
	public boolean equals(Object m) {
		Edge k = (Edge) m;
		return k.hashCode() == hashCode() && prevTray.equals(k.prevTray)
				&& currentTray.equals(k.currentTray) && move.equals(k.move);
	}
}
