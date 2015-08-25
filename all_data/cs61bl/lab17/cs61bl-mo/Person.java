import java.util.Comparator;

public class Person implements Comparable<Person>{

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
	@Override
	public int compareTo(Person arg0) {
		// TODO Auto-generated method stub
		if (this.myName.compareTo(arg0.myName) == 0) {
			return 0;
		}
		else if (this.myName.compareTo(arg0.myName) < 0) {
			return -1;
		}else {
			return 1;
		}
	}
//
//	@Override
//	public int compare(Person o1, Person o2) {
//		// TODO Auto-generated method stub
//		return o1.compareTo(o2);
//	}
//	
}
