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
    	int[] arr = {5,1, 4,6,9};
    	//This should return an exception because elements 0 to 1 are not sorted
    	InsertionSort.isOK(arr, 1);
    	
    	//The below should generate an exception
    	exception.expect(IllegalStateException.class);
    	InsertionSort.isOK(arr, -1);
    	
    	
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
    	int[] arr = {2,5, 3, 4};
    	InsertionSort.insert(arr, 2);
    	int[] check = {2,3,5, 4};
    	assertEquals(Arrays.toString(arr), Arrays.toString(check));
    	
    	int[] arr2 = {3,5, 2, 4};
    	InsertionSort.insert(arr2, 2);
    	int[] check2 = {2,3,5, 4};
    	assertEquals(Arrays.toString(arr2), Arrays.toString(check2));

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
    	int[] arr = {10,4,5,1,4};
    	int[] result = InsertionSort.insertionSort(arr);
    	int[] check = {1,4,4,5,10};
        assertEquals(Arrays.toString(result), Arrays.toString(check));
    	

    }

}
