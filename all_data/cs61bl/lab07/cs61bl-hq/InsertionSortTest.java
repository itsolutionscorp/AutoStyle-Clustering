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
    	int[] arr = {2, 4, 5, 8, 9, 11};
    	
    	// This isOk call should return nothing because all the elements are
    	// already sorted.
    	InsertionSort.isOK(arr, 5);

    }

    @Test
    public void insert1() {
        int[] arr = {4, 5, 6, 2, 3, 9, 1, 7, 8, 0};

        for (int i = 0; i < arr.length; i++) {
            InsertionSort.insert(arr, i);
        }
    }

    @Test
    public void insert2() {
    	int[] arr = {2, 6, 10, 3, 8, 6, 1, 4, 7, 3};
    	
    	// This array contains two pairs of matching integers (3 and 4).
    	// The array should be sorted so they are next to each other
    	// and should run without error.
    	for (int i = 0; i < arr.length - 1; i++) {
            InsertionSort.insert(arr, i);
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
    	int[] arr = {6, 9, 2, 5, 3, 6, 4, 8, 1, 9, 0, 7, 5};
        int[] result = InsertionSort.insertionSort(arr);
        int[] check = {0, 1, 2, 3, 4, 5, 5, 6, 6, 7, 8, 9, 9};
        
        // This array contains three pairs of matching integers (5, 6, and 9).
        // The array should be sorted so they are next to each other
    	// and should run without error.
        assertEquals(Arrays.toString(result), Arrays.toString(check));
    }

}
