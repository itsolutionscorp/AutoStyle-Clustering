public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == 0) {
					latestPos = k;
				} else if (arr[latestPos].compareTo(arr[k]) < 0) {
					latestPos = k;
				}
			}
			if (arr[j].compareTo(arr[latestPos]) < 0) {
				Comparable temp = arr[latestPos];
				int k = latestPos;
				while (k < j) {
					arr[k] = arr[k + 1];
					k ++;
				}
				arr[j] = temp;
			} else if (j != latestPos) {
				Comparable temp = arr[latestPos];
				int k = latestPos;
				while (k < j) {
					arr[k] = arr[k + 1];
					k ++;
				}
				arr[j - 1] = temp;
			}
		}
		
	}
	
	public class tPair implements Comparable<tPair> {
		public String _key;
		public String _val;
		
		public tPair(String key, String val) {
			_key = key;
			_val = val;
		}
		
		@Override
		public int compareTo(tPair t) {
			return _key.compareTo(t._key);
		}
		
		@Override
		public String toString() {
			return "(" + _key + "," + _val + ")";
		}
	}
	
	public static void main(String[] args) {
		UnstableSelectionSort u = new UnstableSelectionSort();
		tPair[] arr = new tPair[16];
		arr[0] = u.new tPair("ab", "ab1");
		arr[1] = u.new tPair("bc", "bc1");
		arr[2] = u.new tPair("cd", "cd1");
		arr[3] = u.new tPair("ab", "ab2");
		arr[4] = u.new tPair("bc", "bc2");
		arr[5] = u.new tPair("ab", "ab3");
		arr[6] = u.new tPair("bc", "bc3");
		arr[7] = u.new tPair("bc", "bc4");
		arr[8] = u.new tPair("ab", "ab4");
		arr[9] = u.new tPair("cd", "cd2");
		arr[10] = u.new tPair("ab", "ab5");
		arr[11] = u.new tPair("ab", "ab6");
		arr[12] = u.new tPair("bc", "bc5");
		arr[13] = u.new tPair("ab", "ab7");
		arr[14] = u.new tPair("bc", "bc6");
		arr[15] = u.new tPair("cd", "cd3");
		selectionSort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]._val);
		}
	}
}

