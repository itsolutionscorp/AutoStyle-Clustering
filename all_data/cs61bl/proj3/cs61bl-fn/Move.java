public class Move {
	int[] myMove;

	public Move(int[] move) {
		myMove = move;
	}

	@Override
	public int hashCode() {
		return myMove[0] * 11 + myMove[1] * 13 + myMove[2] * 17 + myMove[3]
				* 19;
	}

	@Override
	public boolean equals(Object m) {
		int[] t = ((Move) m).myMove;
		return hashCode() == m.hashCode() && myMove[0] == t[0]
				&& myMove[1] == t[1] && myMove[2] == t[2] && myMove[3] == t[3];
	}

	@Override
	public String toString() {
		return "[ " + myMove[0] + " " + myMove[1] + " " + myMove[2] + " "
				+ myMove[3] + "]";
	}
}
