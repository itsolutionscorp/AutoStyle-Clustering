import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) <= 0) {
					latestPos = k;
				}
			}
			if (j != latestPos) {
				Comparable temp = arr[j];
				arr[j] = arr[latestPos];
				arr[latestPos] = temp;
			}
		}
	}
	
	private class Toa implements Comparable{
		String name;
		String element;
		
		public Toa(String n, String e) {
			name = n;
			element = e;
		}
		
		@Override
		public int compareTo(Object o) {
			Toa toCompare = (Toa) o;
			return name.compareTo(toCompare.name);
		}
		
		@Override
		public String toString() {
			return element;
		}
	}
	
	public static void main(String[] args) {
		UnstableSelectionSort u = new UnstableSelectionSort();
		Toa tahu = u.new Toa("Lewa", "Fire");
		Toa lewa = u.new Toa("Lewa", "Air");
		Toa kopaka = u.new Toa("Lewa", "Ice");
		Toa pohatu = u.new Toa("Lewa", "Stone");
		Toa onua = u.new Toa("Lewa", "Earth");
		Toa gali = u.new Toa("Lewa", "Water");
		Toa takanuva = u.new Toa("Lewa", "Light");
		Toa potato = u.new Toa("Lewa", "Food");
		Toa poser = u.new Toa("Lewa", "Jungle");
		Toa[] arr1 = new Toa[] {tahu, poser, kopaka, pohatu, onua, gali, takanuva, potato, lewa};
		System.out.println("Original array: " + Arrays.toString(arr1));
		selectionSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}
	}
}
