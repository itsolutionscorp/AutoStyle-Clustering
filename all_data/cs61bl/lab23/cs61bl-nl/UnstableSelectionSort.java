public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == -1) {
					latestPos = k;
					continue;
				}
				if (arr[latestPos].compareTo(arr[k]) == 0) {
					latestPos = k;
				}
			}
			if (j != latestPos) {
				Comparable temp = arr[latestPos];
				for (int i = latestPos; i < j; i++) {
					arr[i] = arr[i + 1];
				}
				arr[j] = temp;
			}
		}

	}

	public static class ComparableTestObject implements Comparable {

		public String myString;
		public int myInt;

		public ComparableTestObject(String s, int i) {
			myInt = i;
			myString = s;
		}

		public int compareTo(Object o) {
			if (o instanceof ComparableTestObject) {
				if (myString.compareTo(((ComparableTestObject) o).myString) > 0) {
					return 1;
				} else if (myString.compareTo(((ComparableTestObject) o).myString) < 0) {
					return -1;
				} else if (myString.compareTo(((ComparableTestObject) o).myString) == 0) {
					return 0;
				}
			}
			return 0;
		}

		public String toString() {
			return myString + "\n" + myInt + "\n";
		}
	}

	public static void main(String[] args) {

		ComparableTestObject[] test = new ComparableTestObject[10];
		for (int i = 0; i < test.length; i++) {
			if (i % 2 == 0 && i != 4) {
				if (i == 2) {
					test[i] = new ComparableTestObject("first", 1);
				} else if (i == 6) {
					test[i] = new ComparableTestObject("first", 11);
				} else if (i == 8) {
					test[i] = new ComparableTestObject("first", 111);
				} else {
					test[i] = new ComparableTestObject("first", 1 * i);
				}

			} else if (i == 4) {
				test[i] = new ComparableTestObject("second", 2);
			} else if (i == 5) {
				test[i] = new ComparableTestObject("third", 3);
				;
			} else {
				test[i] = new ComparableTestObject("fourth", 4);
			}
		}
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}

		System.out.println("Sorted: ");
		selectionSort(test);
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	}
}
