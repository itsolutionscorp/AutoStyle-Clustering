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
    
    public int get(int index) {
    	return jugs[index];
    }
    
    public int hashCode() {
    	return get(0)*5281+get(1)*6619+get(2)*7621;
    }
    
    public boolean equals(Object other) {
    	try {
    		JugContents otherJug = (JugContents) other;
    		return jugs[0] == otherJug.get(0) && jugs[1] == otherJug.get(1) && jugs[2] == otherJug.get(2);
    	} catch (ClassCastException e) {
    		return false;
    	} catch (NullPointerException e) {
    		return false;
    	}
    }

}
