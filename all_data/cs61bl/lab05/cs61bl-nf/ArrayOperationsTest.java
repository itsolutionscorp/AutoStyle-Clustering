import junit.framework.TestCase;

public class ArrayOperationsTest extends TestCase {
	
	public void testInsert() {
		int[] values = {1, 2, 3, 4, 5};
		ArrayOperations.insert (values, 0, 0);
		int[] afterInsert1 = {0, 1, 2, 3, 4};
		check (values, afterInsert1);

		ArrayOperations.insert (values, 4, 7);
		int[] afterInsert2 = {0, 1, 2, 3, 7};
		check (values, afterInsert2);
	}
	
	public void testDelete() {
		int[] values = {1, 2, 3, 4, 5};
		ArrayOperations.delete (values, 4);
		int[] afterDelete1 = {1, 2, 3, 4, 0};
		check (values, afterDelete1);

		values[4] = 5;
		ArrayOperations.delete (values, 2);
		int[] afterDelete2 = {1, 2, 4, 5, 0};
		check (values, afterDelete2);

		values[4] = 7;
		ArrayOperations.delete (values, 0);
		int[] afterDelete3 = {2, 4, 5, 7, 0};
		check (values, afterDelete3);
	}
	
	private void check (int[] array1, int[] array2) {
		assertTrue (array1.length == array2.length);
		for (int k = 0; k < array1.length; k++) {
			assertTrue (array1[k] == array2[k]);
		}
	}

}
