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
    	 if (jugs[0] != 0 && jugs [1] != 0 && jugs[2] != 0) {
    		 return jugs[0] * jugs[1] * jugs[2];
    	 }
    	 else if (jugs[0] != 0 && jugs[1] != 0) {
    		 return jugs[0] * jugs[1];
    	 }
    	 else if (jugs[0] != 0 && jugs[2] != 0) {
    		 return jugs[0] * jugs[2];
    	 }
    	 else if (jugs[1] != 0 && jugs[2] != 0) {
    		 return jugs[2] * jugs[1];
    	 }
    	 else {
    		 return jugs[0] + jugs[1] + jugs[2];
    	 }
    	 
    	 
    }
    
    public boolean equals(Object obj) {
    	if (obj instanceof JugContents) {
    		JugContents jugs2 = (JugContents) obj;
    		if (jugs[0] == jugs2.jugs[0] && jugs[1] == jugs2.jugs[1] && jugs[2] == jugs2.jugs[2]) {
    			return true;
    		}
    		return false;
    	}
    	return false;
    }

}
