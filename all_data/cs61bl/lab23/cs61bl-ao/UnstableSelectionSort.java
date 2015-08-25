public class UnstableSelectionSort {

	
	
	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == -1 || arr[latestPos].compareTo(arr[k]) == 0) {
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
	
	public static class FileEntry implements Comparable {
		public String myName;
		public int mySize;
		
		public FileEntry (String name, int size) {
			myName = name;
			mySize = size;
		}

		@Override
		public int compareTo(Object o) {
			FileEntry f = (FileEntry) o;
			return myName.compareTo(f.myName);
		}
	}
	
	public static void main(String[] args) {
		FileEntry[] arr = new FileEntry[5];	
		
		FileEntry a = new FileEntry("d", 13);		
		FileEntry b = new FileEntry("d", 11);
		FileEntry c = new FileEntry("b", 12);
		FileEntry d = new FileEntry("a", 17);
		FileEntry e = new FileEntry("a", 19);
		
		arr[0] = a;
		arr[1] = b;
		arr[2] = c;
		arr[3] = d;
		arr[4] = e;
		
		selectionSort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(((FileEntry)arr[i]).mySize);
		}
	}
}
