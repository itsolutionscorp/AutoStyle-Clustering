import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

@RunWith(JUnit4.class)
public class InsertionSortTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void isOK1() {
		int[] arr = {1, 5, 4, 6, 8};

		// This isOK call should return nothing because the array is sorted
		// up through the element at index 1.
		InsertionSort.isOK(arr, 1);

		// This isOK call should throw an exception because the element 4
		// makes the array up through the element at index 3 out of order.
		exception.expect(IllegalStateException.class);
		InsertionSort.isOK(arr, 3);
	}

	@Test
	public void isOK2() {

		// YOUR CODE HERE
		int[] arr = {5, 3, 4, 2, 1};
		int[] arr2 = {0, 0, 0, 6, 8};
		int[] arr3 = {0, 1, 5, 9, 3};

		InsertionSort.isOK(arr2, 4);

		exception.expect(IllegalStateException.class);
		InsertionSort.isOK(arr, 3);

		InsertionSort.isOK(arr3, 3);

	}

	@Test
	public void isOK3() {

		// YOUR CODE HERE
		int[] arr = {1, 3, 4, 2, 1};
		int[] arr2 = {0, 0, 0, 0, 8};
		int[] arr3 = {0};

		InsertionSort.isOK(arr, 1);

		InsertionSort.isOK(arr2, 3);
		InsertionSort.isOK(arr3, 0);

		exception.expect(IllegalStateException.class);
		InsertionSort.isOK(arr, 4);
	}

	@Test
	public void insert1() {
		int[] arr = {4, 5, 6, 2, 3, 9, 1, 7, 8, 0};
		InsertionSort.insert(arr, 0);

		for (int i = 0; i < arr.length - 1; i++) {
			InsertionSort.insert(arr, i);

		}


	}

	@Test

	public void insert2() {
		int[] arr = {1, 2, 4, 3};
		int [] check = {1, 2, 3, 4};
		InsertionSort.insert(arr, 3);
		System.out.println(Arrays.toString(arr));


		assertEquals(Arrays.toString(arr), Arrays.toString(check));
	}

	@Test

	public void insert3() {

		int[] arr = {1, 2, 5, 9, 10, 23, 3};

		int [] check = {1, 2, 3, 5, 9, 10, 23};
		InsertionSort.insert(arr, 6);
		System.out.println(Arrays.toString(arr));


		assertEquals(Arrays.toString(arr), Arrays.toString(check));
	}
	@Test

	public void insert4() {
		int[] arr = {1, 2, 5, 9, 0};
		int [] check = {0, 1, 2, 5, 9};

		InsertionSort.insert(arr, 4);
		System.out.println(Arrays.toString(arr));

		assertEquals(Arrays.toString(arr), Arrays.toString(check));

	}
	@Test
	public void insert5() {
		int[] arr2 = {1, 2, 3, 4};
		int [] check2 = {1, 2, 3, 4};
		InsertionSort.insert(arr2, 2);

		assertEquals(Arrays.toString(arr2), Arrays.toString(check2));

		int[] arr3 = {2, 1};
		int [] check3 = {1, 2};
		InsertionSort.insert(arr3, 1);
		System.out.println(Arrays.toString(arr3));


		assertEquals(Arrays.toString(arr3), Arrays.toString(check3));



	}

	@Test
	public void insertionSort1() {
		int[] arr = {4, 5, 6, 2, 3, 9, 1, 7, 8, 0};
		int[] result = InsertionSort.insertionSort(arr);
		int[] check = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		assertEquals(Arrays.toString(result), Arrays.toString(check));
	}

	@Test   
	public void insertionSort2() {
		int[] arr = {1, 2, 3, 4, 0};
		int[] result = InsertionSort.insertionSort(arr);
		int[] check = {0, 1, 2, 3, 4};

		assertEquals(Arrays.toString(result), Arrays.toString(check));
		
		int[] arr2 = {0, 0, 0};
		int[] result2 = InsertionSort.insertionSort(arr2);
		int[] check2 = {0, 0, 0};

		assertEquals(Arrays.toString(result2), Arrays.toString(check2));
	}
	
	@Test   
	public void insertionSort3() {

		int[] arr = {1, 2, 3, 4, 0};
		int[] result = InsertionSort.insertionSort(arr);
		int[] check = {0, 1, 2, 3, 4};

		assertEquals(Arrays.toString(result), Arrays.toString(check));
		
		int[] arr2 = {0};
		int[] result2 = InsertionSort.insertionSort(arr2);
		int[] check2 = {0};

		assertEquals(Arrays.toString(result2), Arrays.toString(check2));
	}
	

	@Test   
	public void insertionSort4() {

		int[] arr = {1, 3, 2, 4, 0, 9};
		int[] result = InsertionSort.insertionSort(arr);
		int[] check = {0, 1, 2, 3, 4, 9};

		assertEquals(Arrays.toString(result), Arrays.toString(check));
		
	}

}
