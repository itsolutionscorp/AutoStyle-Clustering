import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {	
		
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) <= 0 ) {
					latestPos = k;
				}
			}
			if (j != latestPos) {			
				Comparable temp = arr[latestPos];
				for(int i=latestPos;i<j;i++){
					arr[i] = arr[i+1];
				}
				arr[j] = temp;
			}
		}
	}
		
//	public static void main(String[] args){
//		UnstableSelectionSort c=new UnstableSelectionSort();
//		testInt z = new testInt(4, "a", c);
//		testInt a = new testInt(5, "a",c);
//		testInt b = new testInt(4, "b",c);
//		testInt d = new testInt(2, "a",c);
//		testInt e = new testInt(3, "a",c);
//		testInt g = new testInt(2, "b",c);
//		testInt f = new testInt(1, "a",c);
//		testInt[] test=new testInt[7];
//		test[0]=z;
//		test[1]=a;
//		test[2]=b;
//		test[3]=d;
//		test[4]=e;
//		test[5]=g;
//		test[6]=f;
//		System.out.println(Arrays.toString(test));
//
//		selectionSort(test);
//		System.out.println(Arrays.toString(test));
//	}
}
