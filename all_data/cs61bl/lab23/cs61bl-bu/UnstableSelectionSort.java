import java.lang.reflect.Array;
import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = 0; j < arr.length; j++) {
			int latestPos = j;
			for (int k = j; k < arr.length; k++) {
				if (arr[latestPos].compareTo(arr[k]) == 1) {
					latestPos = k;
				}
			}
			if (j != latestPos) {
				Comparable temp = arr[j];
				arr[j] = arr[latestPos];
				for (int i = j+1; i < arr.length; i++) {
					if (i == latestPos) {
						arr[i] = temp;
						break;
					}
					Comparable s = arr[i];
					arr[i] = temp;
					temp = s;
				}
			}
		}
		
	}
	
//	public static void main(String[] args) {
//		testInt test = new testInt(1, "b");
//		testInt hi = new testInt(1, "a");
//		testInt yolo = new testInt(2, "d");
//		testInt asdf = new testInt(2, "z");
//		testInt hello = new testInt(4, "c");
//		testInt hi2 = new testInt(3, "b");
//		testInt hi3 = new testInt(3, "c");
//		Comparable[] arr = new Comparable[7];
//		arr[0] = test;
//		arr[1] = hi;
//		arr[2] = hi2;
//		arr[3] = yolo;
//		arr[4] = hello;
//		arr[5] = asdf;
//		arr[6] = hi3;
//		System.out.println("Before: ");
//		System.out.print(Arrays.toString(arr));
//		System.out.println();
//		System.out.println();
//		selectionSort(arr);
//		System.out.println();
//		System.out.println("After: ");
//		System.out.print(Arrays.toString(arr));
//		
//	}
}