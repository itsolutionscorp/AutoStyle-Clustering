public class Thing implements Comparable {
	public String myName;
	public int myValue;

	public Thing(int value, String name) {
		myName = name;
		myValue = value;
	}

	public int compareTo(Object otherThing) {
		Thing other = (Thing) otherThing;
		if (myValue == other.myValue) {
			return 0;
		} else if (myValue < other.myValue) {
			return -1;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		String rtn = "";
		rtn += myValue;
		rtn += myName;
		return rtn;
	}
}