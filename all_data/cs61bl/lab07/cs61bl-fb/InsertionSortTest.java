import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class InsertionSortTest extends TestCase {
        
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

        // element up to kth element in the list is ordered.
        int[] arr2 = {0,1,2,7,4,3,8};
        InsertionSort.isOK(arr2, 0);
        InsertionSort.isOK(arr2, 1);
        InsertionSort.isOK(arr2, 2);
        InsertionSort.isOK(arr2, 3);
        exception.expect(IllegalStateException.class);
        InsertionSort.isOK(arr2, 4);
        // Maxsize boundary when k is list - 1
        int[] arr3 = {0,1,2,3,4,5}; // length of the array == 6;
        InsertionSort.isOK(arr3, 6);
        //when k exceeds the boundary of the list
        exception.expect(IllegalStateException.class);
        InsertionSort.isOK(arr3, 7);
        // Minsize boundary where k is length ==1
        int[] arr4 = {1};
        InsertionSort.isOK(arr4, 1);
        // when K is negative
        exception.expect(IllegalStateException.class);
        InsertionSort.isOK(arr4, -1);
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
        //base case;
    	int [] arr4 = {0};
        InsertionSort.insert(arr4,0);
        //base case2;
        int [] arr5 = {1,0};
        InsertionSort.insert(arr5, 1);
        assertTrue(arr5[0] ==0 && arr5[1] ==1);
        
        

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

    	//base case;
    	int[] arr = {0};
        int[] result = InsertionSort.insertionSort(arr);
        int[] check = {0};
        
        assertEquals(Arrays.toString(result), Arrays.toString(check));

      //base case;
    	int[] arr2 = {9,8,7,6,5,4,3,2,1};
        int[] result2 = InsertionSort.insertionSort(arr2);
        int[] check2 = {1,2,3,4,5,6,7,8,9};
        
        assertEquals(Arrays.toString(result2), Arrays.toString(check2));
      //base case 3 when there are multiple elements with same number
        int[] arr3 = {0,1,0,0};
        int[] result3 = InsertionSort.insertionSort(arr3);
        int[] check3 = {0,0,0,1};
        
        assertEquals(Arrays.toString(result3), Arrays.toString(check3));
    }

}
