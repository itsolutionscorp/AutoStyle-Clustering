import java.util.HashSet;

public class JugContents {

    public int jugs[];	// contents of the three jugs.
    //private HashSet<int[]> mySet;
    
    public JugContents (int amt0, int amt1, int amt2) {
        jugs = new int [3];
        jugs[0] = amt0;
        jugs[1] = amt1;
        jugs[2] = amt2;
        //mySet = new HashSet<int[]>();
        //mySet.add(jugs);
    }
    
    public JugContents (JugContents b) {	
        jugs = new int [3];
        jugs[0] = b.jugs[0];
        jugs[1] = b.jugs[1];
        jugs[2] = b.jugs[2];
        //mySet = new HashSet<int[]>();
        //mySet.add(jugs);
    }
    public String toString() {
        return "Configuration = (" + jugs[0] + "," 
            + jugs[1] + "," + jugs[2] + ")";
    }
    
    // YOUR CODE HERE
    @Override
    public int hashCode() {
    	String myStr = "";
    	for (int i= 0; i<3; i++){
    	myStr += String.valueOf(jugs[i]);}
    	System.out.println(Integer.parseInt(myStr));
    	return Integer.parseInt(myStr);
    }
    	
    
    @Override
    public boolean equals (Object o) {
    	int[] o1 = ((JugContents) o).jugs; 
    	
    	if (jugs[0] == o1[0] && jugs[1] == o1[1] && jugs[2] == o1[2]){
    		return true;
    		
    	}
    	else{
    		return false;
    	}
    	
    }
    
}
