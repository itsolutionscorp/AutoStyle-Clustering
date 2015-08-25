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
    	int[] arr = {1, 2, 3, 4, 6, 5, 7};
    	
    	// This isOK call should return nothing because the array is sorted
        // up through the element at index 4.
        InsertionSort.isOK(arr, 4);
        
        // This isOK call should throw an exception because the element 5
        // makes the array up through the element at index 5 out of order.
        exception.expect(IllegalStateException.class);
        InsertionSort.isOK(arr, 6);    	

        // This isOK call should throw an exception because k is greater
        // than the length of the array.
        exception.expect(IllegalStateException.class);
        InsertionSort.isOK(arr, 8);
    }
    
    @Test
    public void isOK3() {

        // YOUR CODE HERE
    	int[] arr = {10, 5, 6, 7, 8, 9};
    	
    	// This isOK call should throw an exception because k is negative.
    	exception.expect(IllegalStateException.class);
        InsertionSort.isOK(arr, -1);
    	
    	// This isOK call should return nothing because the array is sorted
        // up through the element at index 0.
        InsertionSort.isOK(arr, 0);
        
        // This isOK call should throw an exception because the element 5
        // makes the array up through the element at index 1 out of order.
        exception.expect(IllegalStateException.class);
        InsertionSort.isOK(arr, 2);    	

    }

    @Test
    public void insert1() {
        int[] arr = {4, 5, 6, 2, 3, 9, 1, 7, 8, 0};
        
        for (int i = 0; i < arr.length - 1; i++) {
            InsertionSort.insert(arr, i);
        }
    }

    @Test
    public void insert2() {

        // YOUR CODE HERE
    	int[] arr = {1, 3, 4, 3, 7, 8, 4};
    	
    	for (int i = 0; i < arr.length - 1; i++) {
    		InsertionSort.insert(arr, i);
    	}
    	int[] beginning = {4, 3};
    	for (int i = 0; i < beginning.length - 1; i++) {
    		InsertionSort.insert(beginning, i);
    	}
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

        // YOUR CODE HERE
    	
    	int[] arr = {1, 10, 5, 9000, 6};
    	int[] result = InsertionSort.insertionSort(arr);
    	int[] check = {1, 5, 6, 10, 9000};
    	
        assertEquals(Arrays.toString(result), Arrays.toString(check));
        
        int[] arr2 = {-2, 3, 2, 2, 0, 4, 3, -2};
    	int[] result2 = InsertionSort.insertionSort(arr2);
    	int[] check2 = {-2, -2, 0, 2, 2, 3, 3, 4};
    	
        assertEquals(Arrays.toString(result2), Arrays.toString(check2));
        
        int[] arr3 = {0, 0};
    	int[] result3 = InsertionSort.insertionSort(arr3);
    	int[] check3 = {0, 0};
    	
        assertEquals(Arrays.toString(result3), Arrays.toString(check3));

    }

}
