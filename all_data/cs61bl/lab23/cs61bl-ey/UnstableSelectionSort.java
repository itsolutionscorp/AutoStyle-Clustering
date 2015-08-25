import java.util.Arrays;
import java.util.Comparator;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == -1 || arr[latestPos].compareTo(arr[k]) == 0) {
					latestPos = k;
				}
			}
			if (j != latestPos) {
				Comparable temp = arr[j];
				arr[j] = arr[latestPos];
				arr[latestPos] = temp;
			}
		}
	}

	private static int randomDigit() {
		return (int) (10 * Math.random());
	}

	private class Person implements Comparable {
		private String myFirstName;
		private String myLastName;
		private String myNumber;

		public Person(String fName, String lName, String number) {
			myFirstName = fName;
			myLastName = lName;
			myNumber = number;
		}

		public String toString() {
			return myLastName + " " + myFirstName + " " + myNumber;
		}

		@Override
		public int compareTo(Object o) {
			return myLastName.compareTo(((Person)o).myLastName);
		}
	}

	public static void main(String[] args) {
		UnstableSelectionSort us = new UnstableSelectionSort();
		Person p = us.new Person("Parth", "Doshi", "4086664994");
		Person p2 = us.new Person("Parth", "Doshi", "4088889988");
		Person p3 = us.new Person("Parth", "Doshi", "4");
		Person p4 = us.new Person("Parth", "Doshi", "43");
		Person p5 = us.new Person("Parth", "Doshi", "3");
		Person p6 = us.new Person("Parth", "Doshi", "8");
		Person p7 = us.new Person("Parth", "Doshi", "9");
		Person p8 = us.new Person("Parth", "Doshi", "1");
		Person p9 = us.new Person("Parth", "Doshi", "2");
		Person p10 = us.new Person("Parth", "Doshi", "6");
		Person p11 = us.new Person("Parth", "Doshi", "7");
		Person p12 = us.new Person("Parth", "Doshi", "10");
		Comparable[] arr1 = new Comparable[10];
		arr1[0] = p;
		arr1[1] = p2;
		arr1[2] = p3;
		arr1[3] = p4;
		arr1[4] = p5;
		arr1[5] = p6;
		arr1[6] = p7;
		arr1[7] = p8;
		arr1[8] = p9;
		arr1[9] = p10;
		System.out.println("Original array: " + Arrays.toString(arr1));
		selectionSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}
	}

}
