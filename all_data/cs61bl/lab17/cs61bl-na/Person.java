import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person>{

	private String myName;
	private ArrayList<PhoneNumber> number;

	public Person(String name) {
		this.myName = name;
		this.number = new ArrayList<PhoneNumber> ();
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
	public int compareTo(Person p){
		if(myName.compareTo(p.myName) == 0){
			return 0;
		} else if (myName.compareTo(p.myName) > 0){
			return 1;
		} else {
			return -1;
		}
	}
	
	public boolean equals(Person p){
		if(myName.hashCode() == p.myName.hashCode()){
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<PhoneNumber> getNumber(){
		return number;
	}
	
}
