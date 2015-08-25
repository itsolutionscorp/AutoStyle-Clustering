public class Person implements Comparable{

	private String myName;

	public Person(String name) {
		this.myName = name;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
	}
	
	// TODO add additional methods
	public int compareTo(Object p) {
		int compare = ((Person) p).myName.length();
		int myLength = myName.length();
		if (compare == myLength) {
			return 0;
		} else if (compare > myLength) {
			return -1;
		} else {
			return 1;
		}
	}
}
