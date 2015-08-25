public class JugContents {

	public int jugs[]; // contents of the three jugs.

	public JugContents(int amt0, int amt1, int amt2) {
		jugs = new int[3];
		jugs[0] = amt0;
		jugs[1] = amt1;
		jugs[2] = amt2;
	}

	public JugContents(JugContents b) {
		jugs = new int[3];
		jugs[0] = b.jugs[0];
		jugs[1] = b.jugs[1];
		jugs[2] = b.jugs[2];
	}

	public String toString() {
		return "Configuration = (" + jugs[0] + "," + jugs[1] + "," + jugs[2] + ")";
	}

	// YOUR CODE HERE
	public int hashCode() {
		return jugs[0] * 1000000 + jugs[1] * 10000 + jugs[2];
	}

	public boolean equals(Object b) {
		if (!(b instanceof JugContents)) {
			return false;
		} else {
			JugContents Contents = (JugContents) b;
			return (jugs[0] == Contents.jugs[0] && 
					jugs[1] == Contents.jugs[1] &&
					jugs[2] == Contents.jugs[2]);
		}
	}
}
