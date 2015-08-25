public class JugContents {

    public int jugs[];	// contents of the three jugs.
    
    public JugContents (int amt0, int amt1, int amt2) {
        jugs = new int [3];
        jugs[0] = amt0;
        jugs[1] = amt1;
        jugs[2] = amt2;
    }
    
    public JugContents (JugContents b) {
        jugs = new int [3];
        jugs[0] = b.jugs[0];
        jugs[1] = b.jugs[1];
        jugs[2] = b.jugs[2];
    }
    
    public String toString() {
        return "Configuration = (" + jugs[0] + "," 
            + jugs[1] + "," + jugs[2] + ")";
    }
    
    public boolean equals(Object obj) {
		JugContents theOther = (JugContents) obj;
		return (jugs[0] == theOther.jugs[0]) && (jugs[1] == theOther.jugs[1])
				&& (jugs[2] == theOther.jugs[2]);
	}

    
	public int hashCode() {
		int base = 1;
		int hash = 0;
		for (int c = 0; c < 3; c++) {
			hash = hash + jugs[c] * base;
			base = base * 3;
		}
		return hash;
	}

}
