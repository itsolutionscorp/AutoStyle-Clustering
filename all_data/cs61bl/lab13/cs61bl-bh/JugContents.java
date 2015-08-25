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
    
    public int hashCode() {
    	return jugs[0] + jugs[1] + jugs[2];
    }
    
    public boolean equals(Object obj) {
    	JugContents j = (JugContents) obj;
     	boolean check1 = jugs[0] == j.jugs[0];
    	boolean check2 = jugs[1] == j.jugs[1];
    	boolean check3 = jugs[2] == j.jugs[2];
    	return check1 && check2 && check3;
    }
    
    public static void main(String[] args) {
    	JugContents jc1 = new JugContents(5, 4, 2);
    	JugContents jc2 = new JugContents(5, 4, 2);
    	System.out.println(jc1.hashCode());
    	System.out.println(jc2.hashCode());
    }
    
}
