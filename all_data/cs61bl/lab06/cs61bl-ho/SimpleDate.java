import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {
	private int x, y;
	public SimpleDate(int a, int b){
		x=a;
		y=b;
	}
	
	// Note: search it in google or oracle
	public int compareTo (SimpleDate s){
		if (x>s.x || x==s.x && y>s.y){
			return 1;
		} else if (x==s.x && y==s.y){
			return 0;
		} else { return -1; }
	}
	
	// Note: remember to add toString !!!
	public String toString(){
		return x+"/"+y;
	}

    public static void main (String [ ] args) {
        SimpleDate [ ] dArray = new SimpleDate [4];
        dArray[0] = new SimpleDate (5, 2); // 5/2
        dArray[1] = new SimpleDate (2, 9); // 2/9
        dArray[2] = new SimpleDate (6, 3); // 6/3
        dArray[3] = new SimpleDate (1, 11); // 1/11
        Arrays.sort (dArray);
        for (int k=0; k<dArray.length; k++) {
            System.out.println(dArray [k]);
        }
    }
}
    // should print the dates in chronological order:
    // 1/11, 2/9, 5/2, 6/3