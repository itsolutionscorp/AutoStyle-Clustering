import java.util.Comparator;


public class Status implements Comparator<Status>{
	Motion move;
	Tray prevTray;
	Tray currentTray;
	double Cost;
	

	public Status(Tray prevTray, Tray nextTray, Motion MoveOfThisRound) {
		this.prevTray = prevTray;
		currentTray = nextTray;
		move = MoveOfThisRound;
		Cost = nextTray.CostInDepth;
	}

	@Override
	public int hashCode() {
		return prevTray.hashCode() + currentTray.hashCode() + move.hashCode();
	}

	@Override
	public boolean equals(Object m) {
		Status k = (Status) m;
		return k.hashCode() == hashCode() && prevTray.equals(k.prevTray)
				&& currentTray.equals(k.currentTray) && move.equals(k.move);
	}


	@Override
	public int compare(Status arg0, Status arg1) {
		// TODO Auto-generated method stub
		if (arg0.Cost < arg1.Cost) {
			return -1;
		} else if (arg0.Cost > arg1.Cost) {
			return 1;
		} else {
			return 0;
		}
	}
}



