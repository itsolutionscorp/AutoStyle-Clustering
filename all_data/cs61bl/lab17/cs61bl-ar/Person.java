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
	
	
	public int compareTo(Person otherName){
		return  myName.charAt(0) - otherName.myName.charAt(0);
		
	}
	

	// TODO add additional methods
	
	
}
