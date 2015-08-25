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
    public void insert1() {
        int[] arr = {4, 5, 6, 2, 3, 9, 1, 7, 8, 0};
        
        for (int i = 0; i < arr.length - 1; i++) {
            InsertionSort.insert(arr, i);
        }
        InsertionSort.isOK(arr, arr.length - 1);
    }

    @Test
    public void insert2() {

        int[] arr = {2,7,1,2,8,9,3,4,11};
        
        for (int i = 0; i < arr.length - 1; i++) {
            InsertionSort.insert(arr, i);
        }

        InsertionSort.isOK(arr, arr.length - 1);
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
    	int[] arr = {3, 1, 5, 4, 2};
    	InsertionSort.isOK(arr, 1);
    	InsertionSort.insertionSort(arr);
        InsertionSort.isOK(arr, arr.length - 1);
    }
    @Test
    public void insertionSort3() {
    	int[] arr = {4, 4, 5, 0, 3};
    	InsertionSort.insertionSort(arr);
        InsertionSort.isOK(arr, arr.length - 1);
    }
    @Test
    public void insertionSort4() {
    	int[] arr = {};
    	InsertionSort.insertionSort(arr);
        InsertionSort.isOK(arr, 0);
    }
    @Test
    public void insertionSort5() {
    	int[] arr = {-4, -2, 0, 4};
    	InsertionSort.insertionSort(arr);
        InsertionSort.isOK(arr, 3);
    }


}
