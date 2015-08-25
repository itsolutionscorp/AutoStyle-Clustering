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
		return "Configuration = (" + jugs[0] + "," + jugs[1] + "," + jugs[2]
				+ ")";
	}

	// YOUR CODE HERE

	public int hashCode() {
		// int hash = 0;
		// for (int index = 0; index < 3; index++) {
		// hash += Math.pow(jugs[index], index);
		// }
		// return hash;

		return this.toString().hashCode();
	}

}
