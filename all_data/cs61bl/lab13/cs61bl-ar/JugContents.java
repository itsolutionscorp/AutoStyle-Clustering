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
    public int hashCode(){
    	return (int) (Math.pow(jugs[0], 1) + Math.pow(jugs[1], 2) +Math.pow(jugs[2], 3));
    }
    
    public boolean equals(Object other){
    	JugContents myOther = (JugContents) other;
    	return myOther.jugs[0] == jugs[0] && myOther.jugs[1] == jugs[1] && myOther.jugs[2] == jugs[2]; 
    }

}
