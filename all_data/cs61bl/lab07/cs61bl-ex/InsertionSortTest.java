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
    	int[] arr = {1, 5, 4, 6, 8};
    	InsertionSort.isOK(arr, 1);
    	exception.expect(IllegalArgumentException.class);
    	InsertionSort.isOK(arr, 9);
    	exception.expect(IllegalArgumentException.class);
    	InsertionSort.isOK(arr, -1);
    }

    @Test
    public void insert1() {
        int[] arr = {4, 5, 6, 2, 3, 9, 1, 7, 8, 3};
        for (int i = 0; i < arr.length - 1; i++) {
            InsertionSort.insert(arr, i);
        }
        int[] arr2 = {1, 2, 3, 3, 4, 5, 6, 7, 8, 9};
    	assertEquals(Arrays.toString(arr), Arrays.toString(arr2));
    }

    @Test
    public void insert2() {
        // YOUR CODE HERE
    	int[] arr = {4, 5, 6, 2, 2, 2, 1, 8, 4, 7};
    	
    	for (int i = 0; i < arr.length - 1; i++) {
            InsertionSort.insert(arr, i);
        }
    	int[] arr2 = {1, 2, 2, 2, 4, 4, 5, 6, 7, 8};
    	assertEquals(Arrays.toString(arr), Arrays.toString(arr2));
    }

    @Test
    public void insertionSort1() {
        int[] arr = {4, 5, 6, 2, 3, 9, 1, 7, 8, 0};
        System.out.println(Arrays.toString(arr));
        int[] result = InsertionSort.insertionSort(arr);
        int[] check = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    	System.out.println(Arrays.toString(result));
    	System.out.println(Arrays.toString(check));
        
        assertEquals(Arrays.toString(result), Arrays.toString(check));
    }

    @Test
    public void insertionSort2() {
        // YOUR CODE HERE
        int[] arr = {4, 9, 6, 2, 3, 3, 1, 3, 3, 0};
        System.out.println(Arrays.toString(arr));
        int[] result = InsertionSort.insertionSort(arr);
        int[] check = {0, 1, 2, 3, 3, 3, 3, 4, 6, 9};
    	System.out.println(Arrays.toString(result));
    	System.out.println(Arrays.toString(check));
        
        assertEquals(Arrays.toString(result), Arrays.toString(check));
    }

}
