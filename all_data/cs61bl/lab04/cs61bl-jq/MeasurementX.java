import java.util.*;

public class MeasurementX {
	int myFeet;
	int myInches;

	public MeasurementX(int feet, int inches) {
		myFeet = feet;
		myInches = inches;
	}

	public String toString() {
		System.out.println("\tMeasurement toString called");
		return new String(myFeet + "\' " + myInches + "\"");
	}

	public boolean equals(MeasurementX m) {
		System.out.println("\tMeasurement equals called with Measurement argument");
		return this.toString().equals(m.toString());
	}
	public boolean equals(Object obj) {
		System.out.println("\tMeasurement equals called with Object argument");
		return this.toString().equals(obj.toString());
	}

	public static void main(String[] args) {
		ArrayList<MeasurementX> a = new ArrayList<MeasurementX>();
		a.add(new MeasurementX(3, 5));
		a.add(new MeasurementX(6, 3));

		MeasurementX m1 = new MeasurementX(2, 7);
		a.add(m1);

		if (a.contains(m1)) {
			System.out.println("CORRECT: a contains 2' 7\"");
		} else {
			System.out.println("INCORRECT: a doesn't contain 2' 7\"");
		}

		MeasurementX m2 = new MeasurementX(10, 9);
		a.add(new MeasurementX(10, 9));

		if (a.contains(m2)) {
			System.out.println("CORRECT: a contains 10' 9\"");
		} else {
			System.out.println("INCORRECT: a doesn't contain 10' 9\"");
		}
	}

}
