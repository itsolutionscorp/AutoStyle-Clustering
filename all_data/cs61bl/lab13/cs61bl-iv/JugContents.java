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
    
    public int hashCode() {
    	int baseNumber = jugs[0] + jugs[1] + jugs[2];
    	int result = 0;
    	for (int i = 0; i < jugs.length; i++) {
    		result += jugs[i] * Math.pow(baseNumber, jugs.length-1-i);
    	}
    	return result;
    }
    
    public boolean equals(Object obj) {
    	if (!(obj instanceof JugContents)) {
    		return false;
    	}
    	return (this.hashCode() == ((JugContents) obj).hashCode());
    }
}
