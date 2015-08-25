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
    	int[] arr = {1, 2, 3, 4, 5};
    	InsertionSort.isOK(arr, 4); 
        // YOUR CODE HERE
    	
    	exception.expect(IllegalArgumentException.class); 
    	InsertionSort.isOK(arr,  -1);
    	exception.expect(IllegalArgumentException.class);
    	InsertionSort.isOK(arr, 5);
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
    	int[] arr = {1, 2, 3, 5, 4};
    	int[] check = {1, 2, 3, 4, 5}; 
    	InsertionSort.insert(arr, 4);
    	    	
    	assertEquals (Arrays.toString(arr), Arrays.toString(check)); 
    	
    	int[] arr2 = {1, 3, 4, 2, 1}; 
    	int[] check2 = {1, 2, 3, 4, 1}; 
    	InsertionSort.insert(arr2,  3);
    	
    	assertEquals (Arrays.toString(arr2), Arrays.toString(check2));
    	
    	int[] arr3 = {1, 1, 1, 1};
    	int[] check3 = {1, 1, 1, 1}; 
    	InsertionSort.insert(arr3, 3);
    	
    	assertEquals (Arrays.toString(arr3), Arrays.toString(check3));
    	
    	int[] arr4 = {1, 2, 2, 4, 4, 3}; 
    	int[] check4 = {1, 2, 2, 3, 4, 4}; 
    	InsertionSort.insert(arr4, 5); 
    	
    	assertEquals(Arrays.toString(arr4), Arrays.toString(check4)); 
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
    	int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    	int[] result = InsertionSort.insertionSort(arr); 
    	int[] check = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
    	
    	assertEquals(Arrays.toString(result), Arrays.toString(check)); 
    	
    	int[] arr2 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    	int[] result2 = InsertionSort.insertionSort(arr2); 
    	int[] check2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    	
    	assertEquals(Arrays.toString(result2), Arrays.toString(check2));
    	
    	int[] arr3 = {1, 3, 2, 4, 3, 5, 4, 6}; 
    	int[] result3 = InsertionSort.insertionSort(arr3); 
    	int[] check3 = {1, 2, 3, 3, 4, 4, 5, 6}; 
    	
    	assertEquals(Arrays.toString(result3), Arrays.toString(check3)); 
    	
    	int[] arr4 = {6, 5, 5, 4, 4, 3, 3, 2, 2, 1};
    	int[] result4 = InsertionSort.insertionSort(arr4); 
    	int[] check4 = {1, 2, 2, 3, 3, 4, 4, 5, 5, 6}; 
    	
    	assertEquals(Arrays.toString(result4), Arrays.toString(check4));
    	
    }

}
