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
    
    // YOUR CODE HERE
    public boolean equals (Object o) {
    	if (!(o instanceof JugContents)) return false;
    	JugContents oJ = (JugContents)o;
    	for (int i = 0; i < jugs.length; i ++) {
    		if (jugs[i] != oJ.jugs[i]) return false;
    	}
    	return true;
    }
    
    public int hashCode () {
        return Integer.parseInt("" + jugs[0] + jugs[1] + jugs[2]);
    }
}
