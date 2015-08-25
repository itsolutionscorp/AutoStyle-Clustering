import java.util.HashSet;

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
    	// return jugs[0] + jugs[1] + jugs[2];
    	return (int) (Math.pow(2, jugs[0]) + Math.pow(3, jugs[1]) +
    			Math.pow(5, jugs[2]));
    }
    
    public boolean equals(Object o) {
    	if (!(o instanceof JugContents)) return false;
    	JugContents other = (JugContents) o;
    	return jugs[0] == other.jugs[0] && jugs[1] == other.jugs[1] &&
    			jugs[2] == other.jugs[2];
    }
    
    public static void main(String[] args) {
    	JugContents j1 = new JugContents(1, 2, 3);
    	JugContents j2 = new JugContents(1, 2, 3);
    	JugContents j3 = new JugContents(1, 2, 4);
    	System.out.println(j1.equals(j2));
    	System.out.println(j1.equals(j3));
//    	j1.addState(j2);
//    	System.out.println(j1.exists(j2));
//    	System.out.println(j1.exists(new JugContents(1, 2, 3)));
//    	System.out.println(j1.exists(new JugContents(1, 2, 3)));
//    	System.out.println(j1.exists(new JugContents(1, 2, 4)));
    }

}
