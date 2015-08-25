import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) < 1) {
					latestPos = k;
				}
			}
			if (j != latestPos) {
				Comparable temp = arr[latestPos];
				for (int i = latestPos; i < j; i++) {
					arr[i] = arr[i+1];
				}
				arr[j] = temp;
			}
		}
	}

	public static void main(String[] args) {
		Comparable[] _array1 = {6,2,9,0,7,1,9,4,3,5,8,1,4,0,8,0,5,4,8,6,2};
		System.out.println(Arrays.toString(_array1));
		selectionSort(_array1);
		System.out.println(Arrays.toString(_array1));

		Comparable[] _array3 = {"a","b","d","f","c"};
		System.out.println(Arrays.toString(_array3));
		selectionSort(_array3);
		System.out.println(Arrays.toString(_array3));

		Test t1 = new Test("Three", 3);
		Test t2 = new Test("Six", 6);
		Test t3 = new Test("First Five", 5);
		Test t4 = new Test("Two", 2);
		Test t5 = new Test("Seven", 7);
		Test t6 = new Test("Four", 4);
		Test t7 = new Test("Second Five", 5);
		Test t8 = new Test("First Eight", 8);
		Test t9 = new Test("Second Eight", 8);
		Test t10 = new Test("One", 1);
		Comparable[] _array4 = {t1, t2, t3, t4, t5, t6, t7, t8, t9, t10};
		System.out.println(Arrays.toString(_array4));
		selectionSort(_array4);
		System.out.println(Arrays.toString(_array4));

	}

	public static class Test implements Comparable<Test> {

		private String myString;
		private int myVal;

		public Test(String name, int value) {
			myString = name;
			myVal = value;
		}

		public int compareTo(Test t) {
			return myVal - t.myVal;
		}

		public String toString() {
			return myString;
		}

	}
}
