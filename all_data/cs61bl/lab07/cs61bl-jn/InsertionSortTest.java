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
    	
    		int[] arr0 = {1};
    		InsertionSort.isOK(arr0, 0);
    	
        int[] arr1 = {};
        InsertionSort.isOK(arr1, 0);
        
        int[] arr2 = {1, 1, 1, 1};
        InsertionSort.isOK(arr2, 2);
        
        int[] arr3 = {1, 2, 3};
        InsertionSort.isOK(arr3, 2);
        
        int[] arr4 = {3, 2, 1};
        exception.expect(IllegalStateException.class);
        InsertionSort.isOK(arr4, 1);
        
        

    }

    @Test
    public void insert1() {
        int[] arr = {4, 5, 6, 2, 3, 9, 1, 7, 8, 0};
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i);
        		InsertionSort.insert(arr, i);
        }
    }

    @Test
    public void insert2() {
    	
    		int[] arr = {1, 2, 4, 3};
    		int[] correct = {1, 2, 3, 4};
    		int[] copy = arr.clone();
    		InsertionSort.insert(copy, 0);
    		assertEquals(Arrays.toString(copy), Arrays.toString(arr));
    		InsertionSort.insert(copy, 1);
    		assertEquals(Arrays.toString(copy), Arrays.toString(arr));
    		InsertionSort.insert(copy, 3);
    		assertEquals(Arrays.toString(copy), Arrays.toString(correct));
    		
    		

    		int[] array = {1,2,3,4};
    		int[] dup = array.clone();
    		for (int i = 0; i < array.length; i++) {
            		InsertionSort.insert(array, i);
        }
    		assertEquals(Arrays.toString(array), Arrays.toString(dup));
    		
    		int[] one = {1, 2, 3, 2};
    		int[] two = {1, 2, 2, 3};
    		for (int i = 0; i < one.length; i++) {
        		InsertionSort.insert(one, i);
    		}
		assertEquals(Arrays.toString(one), Arrays.toString(two));
    		

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

        int[] arr = {1, 2, 3, 4, 6, 6};
        int[] unsorted = {6, 6, 2, 3, 1, 4};
        int[] result = InsertionSort.insertionSort(unsorted);
        assertEquals(Arrays.toString(result), Arrays.toString(arr));
        

    }

}
