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
		Person p = us.new Person("wow", "lol", "45");
		Person p2 = us.new Person("jk", "nah", "37");
		
		
	}

}
