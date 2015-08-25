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
    public boolean equals(Object a) {
    	if (this.toString().equals(((JugContents) a).toString()))
    		return true;
    	return false;
    }
    public int hashCode() {
    	int result = 0;
    	for (int i = 0; i < 3; i++) {
    		result += jugs[i]*Math.pow(3, i);
    	}
    	return result;
    }
}
