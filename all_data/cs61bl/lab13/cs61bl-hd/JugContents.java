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
    
    	return ("" + jugs[0] +"a"+ jugs[1]+"a"+ jugs[2]).hashCode();
    }
    @Override
    public boolean equals(Object j) {
    	if(j instanceof JugContents){
    	if(this.hashCode() == j.hashCode()) return true;
    	}
    	return false;
    }
}
