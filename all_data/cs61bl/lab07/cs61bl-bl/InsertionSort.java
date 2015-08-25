public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int[] list, int k) {
        int inserting = list[k];

        for (int n = 0; n < k; n += 1) {
            if (list[n] > inserting) {
                for (int j = k; j > n; j -= 1) {
                    list[j] = list[j - 1];
                }
                list[n] = inserting;
                // for (int a = 0; a < k + 1; a += 1) {
                //     System.out.println(list[a]);
                // }
                break;
            }
        }
    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
        if (k < 0 || k > list.length) {
            throw new IllegalStateException("k is invalid.");
        }
        for (int n = 1; n < k; n += 1) {
            if (list[n] < list[n-1]) {
                throw new IllegalStateException("Not in sorted order.");
            }
        }
    }

    public static int[] insertionSort(int[] list) {
        int[] rtn = new int[list.length];
        for (int k = 0; k < list.length; k++) {
            rtn[k] = list[k];
        }
        for (int k = 0; k < rtn.length; k++) {
            insert(rtn, k);
            try {
                isOK(rtn, k);
            } catch (IllegalStateException e) {
                System.err.println("inconsistency at position " + k);
            }
        }
        return rtn;
    }
    
    public static void main (String[] args) {
        int[] list = {3, 1, 7, 4, 5, 9, 2, 8, 6};
        list = insertionSort(list);
        for (int k = 0; k < list.length; k++) {
            System.out.print(list[k]);
        }
        System.out.println();
    }

}
