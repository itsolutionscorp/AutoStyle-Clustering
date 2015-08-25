import java.util.Arrays;

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
	
	public static void main (String[] args) {
		Comparable[] arr1 = new Comparable[5];
		TestPiece p0 = new TestPiece(0, "p0");
		TestPiece p1 = new TestPiece(5, "p1");
		TestPiece p2 = new TestPiece(7, "p2");
		TestPiece p3 = new TestPiece(9, "p3");
		TestPiece p4 = new TestPiece(5, "p4");
		arr1[0] = p0;
		arr1[1] = p1;
		arr1[2] = p2;
		arr1[3] = p3;
		arr1[4] = p4;
		for (int i = 0 ; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		selectionSort(arr1);
		System.out.println();
		for (int i = 0 ; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		
	}
}

class TestPiece implements Comparable<TestPiece> {
	private int value;
	private String repr;
	public TestPiece(int val, String rep) {
		value = val;
		repr = rep;
	}
	public String toString() {
		return repr;
	}
	@Override
	public int compareTo(TestPiece o) {
		// TODO Auto-generated method stub
		return Integer.compare(value, o.value);
	}
	
}
