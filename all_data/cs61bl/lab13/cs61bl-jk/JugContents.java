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
    
    @Override
    public String toString() {
    	// Example: [Configuration = (8,5,3)]
        return "Configuration = (" + jugs[0] + "," 
            + jugs[1] + "," + jugs[2] + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
    	JugContents another = (JugContents)obj;
    	
    	// Three jugs are all equal
    	if (jugs[0] == another.jugs[0] && jugs[1] == another.jugs[1] && jugs[2] == another.jugs[2]) {
    		return true;
    	}
    	return false;
    		
    }
    
    public int hashCode() {
    	return toString().hashCode();
    }
    
    // YOUR CODE HERE

}
