import org.junit.Test;
import org.junit.runner.RunWith;
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
    	int[] arr = {1, 1, 1, 2, 2, 3, 1};
    	
    	InsertionSort.isOK(arr, 3);// test for non-decreasing, same elements
    	InsertionSort.isOK(arr, 5);
    	
    	exception.expect(Exception.class);
    	InsertionSort.isOK(arr, 10);//exceeds max list index
    	
    	exception.expect(Exception.class);
    	InsertionSort.isOK(arr, 0);//illegal k

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
    	int[] arr = {1};
    	InsertionSort.insert(arr, 0);
    	InsertionSort.isOK(arr, 1);
    	
    	int[] arr2 = {1, 1, 1};
    	InsertionSort.insert(arr2, 2);
    	InsertionSort.isOK(arr2, 3);
    	
    	int[] arr3 = {1, 2, 3};
    	exception.expect(Exception.class);
    	InsertionSort.insert(arr3, 4);
    	
    	exception.expect(Exception.class);
    	InsertionSort.insert(arr3, -4);
    
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
    	int[] arr = {1, 2, 1, 1, 3};
    	int[] result = InsertionSort.insertionSort(arr);
    	int[] check = {1, 1, 1, 2, 3};  
    	
        assertEquals(Arrays.toString(result), Arrays.toString(check));
    }

}
