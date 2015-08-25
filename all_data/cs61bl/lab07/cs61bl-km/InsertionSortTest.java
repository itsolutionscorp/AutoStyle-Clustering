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
    	int[] test_arr = {1,0,2,3,4}; // tests edge case where the reversed elements are at the front
    	exception.expect(IllegalStateException.class);
    	InsertionSort.isOK(test_arr, 1);
    	
    	int[] test_arr2 = {1,2,3,4,5,-1};								//test case where the first k-1 elements are okay, but the last number is not and is negative
    	InsertionSort.isOK(test_arr2, 4);
    	exception.expect(IllegalStateException.class);
    	InsertionSort.isOK(test_arr2,5);
    	
    	int[] test_arr3 = {1, 1, 2, 3, 2};								//test case with repeating numbers, and where the last two need to be swapped
    	InsertionSort.isOK(test_arr3, 3);
    	exception.expect(IllegalStateException.class);
    	InsertionSort.isOK(test_arr3,4);
    	
    	exception.expect(IllegalStateException.class);
    	InsertionSort.isOK(test_arr2, -1);								//when k < 0
    	exception.expect(IllegalStateException.class);
    	InsertionSort.isOK(test_arr2, 6);								// when k > length of array

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
    	int[] test_arr = {1,2,3,4,5,0}; // checks cases where element from back needs to go to front
    	InsertionSort.insert(test_arr, 5);
    	InsertionSort.isOK(test_arr, 5);
    	
    	int[] test_arr2 = { 0,1, 2,3, 4}; // checks case where the elements are all in order
    	InsertionSort.insert(test_arr2, 4);
    	InsertionSort.isOK(test_arr2, 4);
    	
    	int[] test_arr3 = {0,1, 2, 4, 3}; // checks case where the elements need to be swapped at back
    	InsertionSort.insert(test_arr3, 4);
    	InsertionSort.isOK(test_arr3, 4);
    	
    	int[] test_arr4 = { 0,1, 3, 4, 2}; // checks case where the element needs to go in middle
    	InsertionSort.insert(test_arr4, 4);
    	InsertionSort.isOK(test_arr4, 4);
    	
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
        int[] check1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8};
    	int [] arr1 = {8, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8};							//repeating numbers and bringing element from front to back
        int[] result1 = InsertionSort.insertionSort(arr1);
        assertEquals(Arrays.toString(result1), Arrays.toString(check1));
        
        int[] check2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        
    	int [] arr2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};						// an already sorted array								
        int[] result2 = InsertionSort.insertionSort(arr2);
        assertEquals(Arrays.toString(result2), Arrays.toString(check2));

    	int [] arr3 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};						//an inverted array									
        int[] result3 = InsertionSort.insertionSort(arr3);
        assertEquals(Arrays.toString(result3), Arrays.toString(check2));
        
    	int [] arr4 = {0, 1, 2, 3, 5, 4, 6, 7, 8, 9};									//swapped adjacent numbers in middle					
        int[] result4 = InsertionSort.insertionSort(arr4);
        assertEquals(Arrays.toString(result4), Arrays.toString(check2));
        
        int [] arr5 = {1,0, 2, 3, 4, 5, 6, 7, 8, 9};								//swapped adjacent numbers in front			
        int[] result5 = InsertionSort.insertionSort(arr5);
        assertEquals(Arrays.toString(result5), Arrays.toString(check2));
        
        int [] arr6 = {0, 1, 2, 3, 4, 5, 6, 7, 9, 8};							//swapped adjacent numbers in back					
        int[] result6 = InsertionSort.insertionSort(arr6);
        assertEquals(Arrays.toString(result6), Arrays.toString(check2));
        
        int [] check6 = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        int [] arr7 = {0, 1, 2, 3, 4, 5, 6, 7, 8, -1};								//bring an element from back to front and the negative element										
        int[] result7 = InsertionSort.insertionSort(arr7);
        assertEquals(Arrays.toString(result7), Arrays.toString(check6));
        
        int[] check3 = {0};
    	int [] arr8 = {0};											//one element										
        int[] result8 = InsertionSort.insertionSort(arr8);
        assertEquals(Arrays.toString(result8), Arrays.toString(check3));
        
        int[] check4 ={};
    	int [] arr9 = {};													//no element									
        int[] result9 = InsertionSort.insertionSort(arr9);
        assertEquals(Arrays.toString(result9), Arrays.toString(check4));
        
        int [] check5 = {0, 1, 2, 3, 4, 4, 5, 6, 7, 8, 9};
       	int [] arr10 = {0, 1, 2, 3, 4, 5,4, 6, 7, 8, 9};											
        int[] result10 = InsertionSort.insertionSort(arr10);
        assertEquals(Arrays.toString(result10), Arrays.toString(check5));
        
        int [] arr11 = {1, 0, 3, 2, 6, 5, 4, 9, 8, 7};	
        int [] arr12 = {1, 0, 3, 2, 6, 5, 4, 9, 8, 7};	
        int[] result11 = InsertionSort.insertionSort(arr11);
        assertEquals(Arrays.toString(result11), Arrays.toString(check2));
        assertEquals(Arrays.toString(arr11), Arrays.toString(arr12));				//doesn't do anything to original array

    }

}
