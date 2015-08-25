import java.util.*;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {

		int orderedPos = 0;
		Comparable minSoFar = arr[0];
		while(orderedPos < arr.length) {
			int count = 0;
			int end = 0;
			
			//FIND MIN
			for (int j = orderedPos; j < arr.length; j++) {
				if (count == 0) {
					minSoFar = arr[j];
				} else {
					if (arr[j].compareTo(minSoFar) < 0) {
						minSoFar = arr[j];
						end = j;
					}
				}
				count ++;
			}
			swapPush(arr, minSoFar, orderedPos, end);
			orderedPos += 1;
			
		}
	}
		
	public static void swapPush(Comparable[] com, Comparable c, int start, int currPos) {

			Comparable save = com[start];
			com[start] = c;
			for (int i = start + 1; i <= currPos; i++) {
				Comparable temp = com[i];
				com[i] = save;
				save = temp;
			}
			
			
		}
		

	
//	public static void main(String[] args) {
//		thing[] lol = new thing[5];
//		lol[0]= new thing(1,"first");
//		lol[1]= new thing(5,"fifth");
//		lol[2]= new thing(1,"second");
//		lol[3]= new thing(3,"fourth");
//		lol[4]=new thing (1,"third");
//		for (thing t: lol){
//			System.out.println(t.str);
//			
//		}
//		selectionSort(lol);
//		System.out.println("after sort");
//		for (thing t: lol){
//			System.out.println(t.str);
//		}
//		
//		Integer fun[] = new Integer[]{5,4,2,3,1};
//		System.out.println(Arrays.toString(fun));
//		selectionSort(fun);
//		System.out.println(Arrays.toString(fun));
//
//		
//		
//	}
//	public static class thing implements Comparable{
//		public int no;
//		public String str;
//		public thing(int x, String s){
//			no=x;
//			str=s;
//		}
//		public int compareTo(Object x){
//			return no-((thing)x).no;
//		}
//	}
}
