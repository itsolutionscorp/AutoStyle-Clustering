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
    @Override
    public int hashCode() {
    	String s = "";
    	for (int i = 0; i < jugs.length; i++) {
    		s += jugs[i];
    	}
    	Integer rtn = Integer.parseInt(s);
    	return rtn;
    }
    
    @Override
    public boolean equals(Object o) {
    	if (!(o instanceof JugContents)) {
    		return false;
    	}
    	for (int i = 0; i < jugs.length; i++) {
    		if (jugs[i] != ((JugContents) o).jugs[i]) {
    			return false;
    		}
    	}
    	return true;
    }

}
