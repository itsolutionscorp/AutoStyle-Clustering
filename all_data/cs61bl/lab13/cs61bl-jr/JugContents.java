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
    
    public boolean equals(Object a) {
		JugContents other = (JugContents) a;
		return (jugs[0] == other.jugs[0]) && (jugs[1] == other.jugs[1]) && (jugs[2] == other.jugs[2]);
	}
    
    public int hashCode() {
    	int a = 1;
    	int code = 0;
    	for (int i = 0; i < 3; i++) {
    		code += jugs[i]*a;
    		a *= 3;
    	}
    	return code;
    }
}
