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
    	return (int) (this.jugs[0]*Math.pow(8,0) + this.jugs[1]*Math.pow(8, 1) + this.jugs[2]*Math.pow(8, 2));
    }
    
    @Override
    public boolean equals(Object x) {
    	if (!(x instanceof JugContents)){
    		return false;
    	}
    	if (this.jugs[0] == ((JugContents) x).jugs[0] && this.jugs[1] == ((JugContents) x).jugs[1] && this.jugs[2] == ((JugContents) x).jugs[2]) {
    		return true;
    	}
    	return false;
    } 

}
