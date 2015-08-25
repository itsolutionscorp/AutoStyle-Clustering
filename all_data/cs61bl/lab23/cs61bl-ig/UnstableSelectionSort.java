import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = j; k >= 1; k--) {
				//System.out.println(j + " " + k + " " + arr[latestPos] + " " + arr[k]);
				if (arr[latestPos].compareTo(arr[k]) < 1) {
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
	
	public static void main(String[] args) {
		
		Integer[] nums = new Integer[]{4, 2, 5, 6, 1};
		selectionSort(nums);
		System.out.println(Arrays.toString(nums));
		Test[] tests = new Test[]{new Test(1, 2), new Test(2, 3), new Test(3, 4)};
		selectionSort(tests);
		for(Test t : tests) {
			System.out.println(t.y);
		}
	}
	
	private static class Test implements Comparable<Test> {
		int x, y;
		public Test(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int compareTo(Test t2) {
			if(t2.x < x) {
				return -1;
			} else if(t2.x == x) {
				return 0;
			}
			return 1;
		}
	}
	

}
