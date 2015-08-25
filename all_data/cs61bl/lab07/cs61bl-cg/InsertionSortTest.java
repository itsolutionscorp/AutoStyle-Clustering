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

        int[] k = {2, 3, 7,9};
        exception.expect(IllegalStateException.class);
        InsertionSort.isOK(k, 3);

        int[] yarr = {2,1};
        exception.expect(IllegalStateException.class);
        InsertionSort.isOK(yarr, 2);
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

    	int[] yarr = {4, 8, 9};
        
        for (int i = 0; i < yarr.length - 1; i++) {
            InsertionSort.insert(yarr, i);
            
        }

    }

    @Test
    public void insertionSort1() {
    	int[] yarr = {3,2,2};
        int[] yresult = InsertionSort.insertionSort(yarr);
        System.out.println(Arrays.toString(yresult) + "y");
        int[] ycheck = {0,1,2};
        
        assertEquals(Arrays.toString(yresult), Arrays.toString(ycheck));
        
        int[] arr = {4, 5, 6, 2, 3, 9, 1, 7, 8, 0};
        int[] result = InsertionSort.insertionSort(arr);
        System.out.println(Arrays.toString(result)+ "a");
        int[] check = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        assertEquals(Arrays.toString(result), Arrays.toString(check));
        
        int[] x = {1};
        int[] xresult = InsertionSort.insertionSort(x);
        System.out.println(Arrays.toString(xresult) + "x");
        int[] xcheck = {0};
        
        assertEquals(Arrays.toString(xresult), Arrays.toString(xcheck));
        
        
        
        int[] zarr = {2,1};
        int[] zresult = InsertionSort.insertionSort(zarr);
        System.out.println(Arrays.toString(zresult) + "z");
        int[] zcheck = {0,1};
        
        assertEquals(Arrays.toString(zresult), Arrays.toString(zcheck));
    }

    @Test
    public void insertionSort2() {

        // YOUR CODE HERE

    }

}
