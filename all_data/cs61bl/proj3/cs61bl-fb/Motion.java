
public class Motion {
	int[] myMove;

	public Motion(int[] move) {
		myMove = move;
	}

	@Override
	public int hashCode() {
		return myMove[0] * 251 + myMove[1] * 223 + myMove[2] * 257 + myMove[3]
				* 229;
	}

	@Override
	public boolean equals(Object m) {
		int[] t = ((Motion) m).myMove;
		return hashCode() == m.hashCode() && myMove[0] == t[0]
				&& myMove[1] == t[1] && myMove[2] == t[2] && myMove[3] == t[3];
	}

	@Override
	public String toString() {
		return "[ " + myMove[0] + " " + myMove[1] + " " + myMove[2] + " "
				+ myMove[3] + "]";
	}
}
