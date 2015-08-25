//package lab13;


public class JugContents {

	public int jugs[]; // contents of the three jugs.

	public JugContents(int amt0, int amt1, int amt2) {
		jugs = new int[3];
		jugs[0] = amt0;
		jugs[1] = amt1;
		jugs[2] = amt2;
	}

	public JugContents(JugContents b) {
		jugs = new int[3];
		jugs[0] = b.jugs[0];
		jugs[1] = b.jugs[1];
		jugs[2] = b.jugs[2];
	}

	public String toString() {
		return "Configuration = (" + jugs[0] + "," + jugs[1] + "," + jugs[2]
				+ ")";
	}

	// YOUR CODE HERE

	public boolean equals(Object obj) {
		System.out.println("result is is"+this.jugs[0]);
		if (this.jugs[0] == ((JugContents)obj).jugs[0]&&this.jugs[1] == ((JugContents)obj).jugs[1]&&this.jugs[2] == ((JugContents)obj).jugs[2]){
			
			return true;
		}

		return false;
		// object must be Test at this point
//		Test test = (Test) obj;
//		return num == test.num
//				&& (data == test.data || (data != null && data
//						.equals(test.data)));
	}

	public int hashCode() {
		int result=0;
    	for(int i=0;i<3;i++){
    		result=result*7+jugs[i];
    	}
        return result;
	}
}
